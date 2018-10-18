package com.test.job.repository;

import com.test.job.entity.Image;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ImageRepositoryImpl extends AbstractRepository implements ImageRepository {

    @Override
    @SuppressWarnings("unchecked")
    public List getAllImage() {
        return getSesion().createCriteria(Image.class).list();
    }

    @Override
    public Image getById(Integer id) {
        return (Image) getSesion().get(Image.class, id);
    }

    @Override
    public Integer saveImage(Image image) {
        return (Integer) getSesion().save(image);
    }

    @Override
    public void updateImage(Image image) {
        getSesion().update(image);
    }

    @Override
    public void deleteImage(Image image) {
        getSesion().delete(image);
    }
}
