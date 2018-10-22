package com.test.job.repository;

import com.test.job.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepositoryImpl extends AbstractRepository<Integer, Image> implements ImageRepository {

    @Override
    @SuppressWarnings("unchecked")
    public List<Image> getAllImage() {
        return (List<Image>) createEntityCriteria().list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Image getById(Integer id) {
        return (Image) getSession().get(Image.class, id);
    }

    @Override
    public void saveImage(Image image) {
        persist(image);
    }

    @Override
    public void updateImage(Image image) {
        getSession().update(image);
    }

    @Override
    public void deleteImage(Image image) {
        getSession().delete(image);
    }
}
