package com.test.job.service;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<ImageDao> getAllImage();

    void saveImage(MultipartFile file, String title, String description, String category);

    ImageDao getById(Integer id);

    void updateImage(MultipartFile file, String title, String description, String category, Integer id);

    void deleteImage(Integer id);

    @Data
    @Builder
    class ImageDao  {

        private Integer id;
        private String title;
        private String description;
        private String category;
        private MultipartFile images;
    }
}
