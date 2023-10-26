package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.repository.CuisineCategoryRepository;
import com.groupthree.culinarycompanion.dto.CuisineCategoryDTO;
import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.model.CuisineCategory;
import com.groupthree.culinarycompanion.model.Photo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuisineCategoryService implements ICuisineCategoryService{


    private CuisineCategoryRepository cuisineCategoryDao;

    public CuisineCategoryService(CuisineCategoryRepository cuisineCategoryDao) {
        this.cuisineCategoryDao = cuisineCategoryDao;
    }

    @Override
    public List<CuisineCategory> getAllCuisineCategories() {
        return cuisineCategoryDao.findAllCuisineCategories();
    }

    @Override
    public void addCuisineCategory(CuisineCategoryDTO cuisineCategory) {
        cuisineCategoryDao.save(mapDTOToModel(cuisineCategory));
    }

    @Override
    public CuisineCategory getCuisineByName(String name){
        return cuisineCategoryDao.findCuisineByName(name);
    }

    @Override
    public CuisineCategory getCuisineById(int id){
        return cuisineCategoryDao.findCuisineById(id);
    }

    @Override
    public CuisineCategoryDTO mapModelToDTO(CuisineCategory cuisineCategory) {
        CuisineCategoryDTO cuisineCategoryDTO = new CuisineCategoryDTO();
        cuisineCategoryDTO.setId(cuisineCategory.getId());
        cuisineCategoryDTO.setName(cuisineCategory.getName());

        List<PhotoDTO> photoDTOs = new ArrayList<>();
        for (Photo photo : cuisineCategory.getPhotos()) {
            PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setPhotoId(photo.getPhotoId());
            photoDTO.setPhotoName(photo.getPhotoName());
            photoDTO.setPhotoPath(photo.getPhotoPath());
            photoDTOs.add(photoDTO);
        }
        cuisineCategoryDTO.setPhotos(photoDTOs);

        return cuisineCategoryDTO;
    }

    @Override
    public CuisineCategory mapDTOToModel(CuisineCategoryDTO cuisineCategoryDTO) {
        CuisineCategory cuisineCategory = new CuisineCategory();
        cuisineCategory.setId(cuisineCategoryDTO.getId());
        cuisineCategory.setName(cuisineCategoryDTO.getName());

        List<Photo> photos = new ArrayList<>();
        for (PhotoDTO photoDTO : cuisineCategoryDTO.getPhotos()) {
            Photo photo = new Photo();
            photo.setPhotoId(photoDTO.getPhotoId());
            photo.setPhotoName(photoDTO.getPhotoName());
            photo.setPhotoPath(photoDTO.getPhotoPath());
            photos.add(photo);
        }
        cuisineCategory.setPhotos(photos);

        return cuisineCategory;
    }


}

