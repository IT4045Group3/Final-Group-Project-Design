package com.groupthree.culinarycompanion.service.impl;

import com.groupthree.culinarycompanion.entity.Cuisine;
import com.groupthree.culinarycompanion.repository.CuisineCategoryRepository;
import com.groupthree.culinarycompanion.entity.Photo;
import com.groupthree.culinarycompanion.repository.PhotoRepository;
import com.groupthree.culinarycompanion.service.ICuisineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuisineCategoryServiceImpl implements ICuisineCategoryService {


    @Autowired
    private CuisineCategoryRepository cuisineCategoryRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<Cuisine> getAllCuisineCategories() {
        return cuisineCategoryRepository.findAll();
    }

    @Override
    public Cuisine addCuisineCategory(Cuisine cuisine) {
       return cuisineCategoryRepository.save(cuisine);
    }

    @Override
    public Cuisine getCuisineByName(String name) {
        return cuisineCategoryRepository.findByName(name);
    }

    @Override
    public Cuisine getCuisineById(int id) {
        return cuisineCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public void addPhotoInCategory(int cuisineCategoryId, String imagePath, String imageName) {
        Cuisine cuisine = cuisineCategoryRepository.findById(cuisineCategoryId).orElse(null);
        if (cuisine != null) {
            Photo photo = new Photo();
            photo.setPhotoPath(imagePath);
            photo.setPhotoName(imageName);
            photo.setCuisine(cuisine);
            photoRepository.save(photo);
        }
    }
}


