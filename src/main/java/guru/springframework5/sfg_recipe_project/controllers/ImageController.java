package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.services.ImageService;
import guru.springframework5.sfg_recipe_project.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{recipeId}/changeimage")
    public String openChangeImageForm(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(recipeId)));
        return "recipe/image/changeimageform";
    }

    @PostMapping("/recipe/{recipeId}/uploadnewimage")
    public String uploadNewImage(@PathVariable String recipeId, @RequestParam("imgFile") MultipartFile file){
        imageService.saveImageFile(Long.valueOf(recipeId), file);
        return "redirect:/recipe/"+recipeId+"/show";
    }
}
