package ru.job4j.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileDto {

    private String name;

    private byte[] content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
