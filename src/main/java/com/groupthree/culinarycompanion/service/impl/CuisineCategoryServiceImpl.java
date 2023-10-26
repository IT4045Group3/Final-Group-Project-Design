package com.groupthree.culinarycompanion.service.impl;

import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.repository.CuisineCategoryRepository;
import com.groupthree.culinarycompanion.dto.CuisineCategoryDTO;
import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.entity.CuisineCategory;
import com.groupthree.culinarycompanion.entity.Photo;
import com.groupthree.culinarycompanion.repository.PhotoRepository;
import com.groupthree.culinarycompanion.service.ICuisineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuisineCategoryServiceImpl implements ICuisineCategoryService {


    @Autowired
    private CuisineCategoryRepository cuisineCategoryRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<CuisineCategory> getAllCuisineCategories() {
        return cuisineCategoryRepository.findAll();
    }

    @Override
    public CuisineCategory addCuisineCategory(CuisineCategory cuisineCategory) {
       return cuisineCategoryRepository.save(cuisineCategory);
    }

    @Override
    public CuisineCategory getCuisineByName(String name) {
        return cuisineCategoryRepository.findByName(name);
    }

    @Override
    public CuisineCategory getCuisineById(int id) {
        return cuisineCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public void addPhotoInCategory(int cuisineCategoryId, String imagePath, String imageName) {
        CuisineCategory cuisineCategory = cuisineCategoryRepository.findById(cuisineCategoryId).orElse(null);
        if (cuisineCategory != null) {
            Photo photo = new Photo();
            photo.setPhotoPath(imagePath);
            photo.setPhotoName(imageName);
            photo.setCuisineCategory(cuisineCategory);
            photoRepository.save(photo);
        }
    }
}


