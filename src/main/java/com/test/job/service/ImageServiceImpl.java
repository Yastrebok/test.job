package com.test.job.service;

import com.test.job.entity.Image;
import com.test.job.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAllImage() {
        return imageRepository.getAllImage();
    }

    @Override
    public Integer saveImage(Image image) {
        return imageRepository.saveImage(image);
    }

    @Override
    public Image getById(Integer id) {
        return imageRepository.getById(id);
    }

    @Override
    public void updateImage(Image image) {
        imageRepository.updateImage(image);
    }

    @Override
    public void deleteImage(Image image) {
        imageRepository.deleteImage(image);
    }
}
