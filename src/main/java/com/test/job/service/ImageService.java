package com.test.job.service;

import com.test.job.entity.Image;

import java.util.List;

public interface ImageService {

    List<Image> getAllImage();

    Integer saveImage(Image image);

    Image getById(Integer id);

    void updateImage(Image image);

    void deleteImage(Image image);
}
