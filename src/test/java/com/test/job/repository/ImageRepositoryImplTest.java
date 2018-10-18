package com.test.job.repository;

import com.test.job.Application;
import com.test.job.entity.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class ImageRepositoryImplTest {
    private static Image newIm = Image.builder()
            .id(1)
            .category("1")
            .description("wow")
            .title("ere")
            .build();

    @Autowired
    public ImageRepository imageRepository;

    @Test
    public void TestCrudImage() {

        assertNull("Image is present ", imageRepository.getById(newIm.getId()));
        Integer newId = imageRepository.saveImage(newIm);

        assertNotNull("Image not saved", imageRepository.getById(newId));

        Image savedImage = imageRepository.getById(newIm.getId());
        savedImage.setCategory("2");
        imageRepository.updateImage(savedImage);
        assertEquals("Image not update ", savedImage.getCategory(), "2");

        assertNotNull("Repository is Empty", imageRepository.getAllImage());
        assertEquals("List size not compare", imageRepository.getAllImage().size(), Collections.singleton(newIm).size());

        imageRepository.deleteImage(savedImage);
        assertNull("Image not delete", imageRepository.getById(savedImage.getId()));
    }
}