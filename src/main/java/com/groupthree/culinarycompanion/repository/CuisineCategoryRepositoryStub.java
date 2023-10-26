package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.model.CuisineCategory;
import com.groupthree.culinarycompanion.model.Photo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CuisineCategoryRepositoryStub implements CuisineCategoryRepository {
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

        CuisineCategory category3 = new CuisineCategory();
        category3.setId(nextCategoryId++);
        category3.setName("Indian");

        CuisineCategory category4 = new CuisineCategory();
        category4.setId(nextCategoryId++);
        category4.setName("Japanese");



        List<Photo> photos1 = new ArrayList<>();
        Photo photo1 = new Photo();
        photo1.setPhotoId(1);
        photo1.setPhotoName("Chinese");
        photo1.setPhotoPath("/upload/Chinese.png");
        photos1.add(photo1);
        category1.setPhotos(photos1);

        List<Photo> photos2 = new ArrayList<>();
        Photo photo2 = new Photo();
        photo2.setPhotoId(1);
        photo2.setPhotoName("American");
        photo2.setPhotoPath("/upload/American.png");
        photos2.add(photo2);
        category2.setPhotos(photos2);

        List<Photo> photos3 = new ArrayList<>();
        Photo photo3 = new Photo();
        photo3.setPhotoId(1);
        photo3.setPhotoName("Indian");
        photo3.setPhotoPath("/upload/Indian.png");
        photos3.add(photo3);
        category3.setPhotos(photos3);

        List<Photo> photos4 = new ArrayList<>();
        Photo photo4 = new Photo();
        photo4.setPhotoId(1);
        photo4.setPhotoName("Japanese");
        photo4.setPhotoPath("/upload/Japanese.png");
        photos4.add(photo4);
        category4.setPhotos(photos4);



        cuisineCategories.add(category1);
        cuisineCategories.add(category2);
        cuisineCategories.add(category3);
        cuisineCategories.add(category4);

    }


}

