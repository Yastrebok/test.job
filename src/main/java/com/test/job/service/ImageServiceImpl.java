package com.test.job.service;

import com.test.job.entity.Image;
import com.test.job.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<ImageDao> getAllImage() {
        return imageRepository.getAllImage().stream()
                .map(this::convertToDao)
                .collect(Collectors.toList());
    }

    @Override
    public void saveImage(MultipartFile file, String title, String description, String category) {
        imageRepository.saveImage(convertToImage(file, title, description, category, 0));
    }

    @Override
    public ImageDao getById(Integer id) {
        return convertToDao(imageRepository.getById(id));
    }

    @Override
    public void updateImage(MultipartFile file, String title, String description, String category,Integer id) {
        imageRepository.updateImage(convertToImage(file, title, description, category, id));
    }

    @Override
    public void deleteImage(Integer id) {
        imageRepository.deleteImage(imageRepository.getById(id));
    }

    private Image convertToImage(MultipartFile file, String title, String description, String category,Integer id) {
        Image newImage = null;
        try {
            newImage = Image.builder()
                    .id(id)
                    .title(title)
                    .description(description)
                    .category(category)
                    .image(file.getBytes())
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newImage;
    }

    private ImageDao convertToDao(Image image) {
        return ImageDao.builder()
                .id(image.getId())
                .title(image.getTitle())
                .description(image.getDescription())
                .category(image.getCategory())
                .build();
    }
}
