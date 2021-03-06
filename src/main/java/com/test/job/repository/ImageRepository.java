package com.test.job.repository;

import com.test.job.entity.Image;

import java.util.List;


public interface ImageRepository  {

    List<Image> getAllImage();

    void saveImage(Image image);

    Image getById(Integer id);

    void updateImage(Image image);

    void deleteImage(Image image);
}
