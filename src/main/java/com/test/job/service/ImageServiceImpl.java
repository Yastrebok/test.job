package com.test.job.service;

import com.test.job.entity.Image;
import com.test.job.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAllImage() {
        return imageRepository.getAllImage();
    }

    @Override
    public void saveImage(Image image) {
        imageRepository.saveImage(image);
    }

    @Override
    public ImageDao getById(Integer id) {
        return convertToDao(imageRepository.getById(id));
    }

    @Override
    public void updateImage(ImageDao image) {
        imageRepository.updateImage(convertToImage(image));
    }

    @Override
    public void deleteImage(ImageDao image) {
        imageRepository.deleteImage(convertToImage(image));
    }

        private Image convertToImage(ImageDao imageDao) {
            Image newImage = null;
        try {
                newImage = Image.builder()
                        .id(imageDao.getId())
                        .title(imageDao.getTitle())
                        .description(imageDao.getDescription())
                        .category(imageDao.getCategory())
                        .image( imageDao.getImages().getBytes())
    //                    .image(newBlob(imageDao.getImages()))
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newImage;
        }

//    private Blob newBlob(MultipartFile image) {
//        Blob blob = new Blob() {
//            @Override
//            public OutputStream setBinaryStream(long pos) throws SQLException {
//                return null;
//            }
//
//        };
//        try {
//            return blob.setBinaryStream( image);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


//    private Blob newBlob(byte[] image) {
//        Blob blob = new Blob() {
//            @Override
//            public OutputStream setBinaryStream(long pos) throws SQLException {
//                return null;
//            }
//
//        };
//        try {
//            return blob.setBinaryStream( image);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private ImageDao convertToDao(Image image) {
            return ImageDao.builder()
                    .id(image.getId())
                    .title(image.getTitle())
                    .description(image.getDescription())
                    .category(image.getCategory())
//                    .image(convertToByte(image.getImage()))
                    .build();
        }

    private byte[] convertToByte(Blob image) {
        try {
            return image.getBytes(0, (int) image.length());
        } catch (SQLException e) {
            return new byte[1];
        }
    }
}
