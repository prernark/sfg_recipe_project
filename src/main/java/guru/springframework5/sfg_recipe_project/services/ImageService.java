package guru.springframework5.sfg_recipe_project.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public void saveImageFile(Long recipeId, MultipartFile file);
}
