package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.model.CuisineCategory;
import com.groupthree.culinarycompanion.model.Photo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CuisineCategoryDAOStub implements ICuisineCategoryDAO {
    private List<CuisineCategory> cuisineCategories = new ArrayList<>();
    private int nextCategoryId = 1;

    @Override
    public void save(CuisineCategory cuisineCategory) {
        cuisineCategory.setId(nextCategoryId++);
        cuisineCategories.add(cuisineCategory);
    }

    @Override
    public List<CuisineCategory> findAllCuisineCategories() {
        return cuisineCategories;
    }

    @Override
    public CuisineCategory findCuisineById(int id) {
        for (CuisineCategory category : cuisineCategories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    @Override
    public CuisineCategory findCuisineByName(String name) {
        for (CuisineCategory category : cuisineCategories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    @PostConstruct
    public void initDefaultCuisineCategory() {
        CuisineCategory category1 = new CuisineCategory();
        category1.setId(nextCategoryId++);
        category1.setName("Chinese");

        CuisineCategory category2 = new CuisineCategory();
        category2.setId(nextCategoryId++);
        category2.setName("American");



        List<Photo> photos1 = new ArrayList<>();
        Photo photo1 = new Photo();
        photo1.setPhotoId(1);
        photo1.setPhotoName("Chinese");
        photo1.setPhotoPath("Chinese.png");
        photos1.add(photo1);
        category1.setPhotos(photos1);

        List<Photo> photos2 = new ArrayList<>();
        Photo photo2 = new Photo();
        photo2.setPhotoId(1);
        photo2.setPhotoName("American");
        photo2.setPhotoPath("American.png");
        photos2.add(photo2);
        category2.setPhotos(photos2);



        cuisineCategories.add(category1);
        cuisineCategories.add(category2);

    }


}

