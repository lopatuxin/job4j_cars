package ru.job4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "auto_post")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String description;
    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private List<File> files = new ArrayList<>();
}
