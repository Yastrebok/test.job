package com.test.job.service;

import com.test.job.entity.Image;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<Image> getAllImage();

    void saveImage(Image image);

    ImageDao getById(Integer id);

    void updateImage(ImageDao image);

    void deleteImage(ImageDao image);

    @Data
    @Builder
    class ImageDao  {

        private Integer id;
        private String title;
        private String description;
        private String category;
        private byte[] image;
        private MultipartFile images;
    }
}
