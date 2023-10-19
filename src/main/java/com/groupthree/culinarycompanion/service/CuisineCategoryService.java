package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dao.CuisineCategoryDAOStub;
import com.groupthree.culinarycompanion.dao.ICuisineCategoryDAO;
import com.groupthree.culinarycompanion.dao.IRecipeDAO;
import com.groupthree.culinarycompanion.model.CuisineCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuisineCategoryService implements ICuisineCategoryService{


    private ICuisineCategoryDAO cuisineCategoryDao;

    public CuisineCategoryService(ICuisineCategoryDAO cuisineCategoryDao) {
        this.cuisineCategoryDao = cuisineCategoryDao;
    }

    @Override
    public List<CuisineCategory> getAllCuisineCategories() {
        return cuisineCategoryDao.findAllCuisineCategories();
    }

    @Override
    public void addCuisineCategory(CuisineCategory cuisineCategory) {
        cuisineCategoryDao.save(cuisineCategory);
    }

    @Override
    public CuisineCategory getCuisineByName(String name){
        return cuisineCategoryDao.findCuisineByName(name);
    }

    @Override
    public CuisineCategory getCuisineById(int id){
        return cuisineCategoryDao.findCuisineById(id);
    }

}

