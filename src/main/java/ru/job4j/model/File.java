package ru.job4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;

    @EqualsAndHashCode.Include
    private String path;

    @Column(name = "post_id")
    private int postId;
}
