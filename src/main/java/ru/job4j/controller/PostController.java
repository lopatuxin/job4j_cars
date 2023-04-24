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
    public String getPosts() {
        return "posts/list";
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
                         Model model) {
        Owner owner = new Owner(1, name, user);
        ownerService.add(owner);
        Car car = new Car(
                1, mark, engineService.findById(id).get(), Set.of(owner));
        carService.add(car);
        post.setCar(car);
        post.setUser(user);
        postService.add(post, Arrays.stream(file).map(f -> {
            try {
                return new FileDto(f.getOriginalFilename(),
                        f.getBytes());
            } catch (IOException e) {
                model.addAttribute("message", e.getMessage());
                return "errors/404";
            }
        }).toArray(FileDto[]::new));
        return "posts/list";
    }
}
