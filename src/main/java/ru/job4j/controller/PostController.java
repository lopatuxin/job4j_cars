package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dto.FileDto;
import ru.job4j.model.*;
import ru.job4j.service.CarService;
import ru.job4j.service.EngineService;
import ru.job4j.service.OwnerService;
import ru.job4j.service.PostService;

import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@ThreadSafe
@Controller
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    private final EngineService engineService;
    private final CarService carService;
    private final OwnerService ownerService;

    @GetMapping("posts")
    public String getPosts(Model model) {
        List<File> files = postService.findAll().stream()
                        .map(post -> post.getFiles().get(0))
                                .toList();
        model.addAttribute("files", files);
        model.addAttribute("posts", postService.findAll());
        return "posts/list";
    }

    @GetMapping("posts/{id}")
    public String getPost(Model model, @PathVariable int id) {
        var optionalPost = postService.findById(id);
        if (optionalPost.isEmpty()) {
            model.addAttribute("message", "Объявление с указанным идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute("post", optionalPost.get());
        return "posts/one";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("engine", engineService.findAll());
        return "posts/create";
    }

    @PostMapping("posts/create")
    public String create(@ModelAttribute Post post,
                         @RequestParam("owner.name") String name,
                         @SessionAttribute User user,
                         @RequestParam("engine.id") int id,
                         @RequestParam("car.name") String mark,
                         @RequestParam MultipartFile[] file,
                         @RequestParam("price") String price,
                         Model model) {
        Owner owner = new Owner(1, name, user);
        ownerService.add(owner);
        Car car = new Car(
                1, mark, price, engineService.findById(id).get(), Set.of(owner));
        carService.add(car);
        post.setCar(car);
        post.setUser(user);
        post.setStatus(false);
        postService.add(post, Arrays.stream(file).map(f -> {
            try {
                return new FileDto(f.getOriginalFilename(),
                        f.getBytes());
            } catch (IOException e) {
                model.addAttribute("message", e.getMessage());
                return "errors/404";
            }
        }).toArray(FileDto[]::new));
        return "redirect:/posts";
    }

    @GetMapping("posts/sell/{id}")
    public String sell(Model model, @PathVariable int id, @SessionAttribute User user) {
        var postOptional = postService.findById(id);
        if (postOptional.isEmpty()) {
            model.addAttribute("message", "No post with the given ID is found.");
            return "errors/404";
        }
        if (!postOptional.get().getUser().equals(user)) {
            model.addAttribute("message",
                    "The status of a post can only be changed by the user who created it.");
            return "errors/404";
        }
        postService.updateStatus(postOptional.get());
        return "redirect:/posts";
    }
}
