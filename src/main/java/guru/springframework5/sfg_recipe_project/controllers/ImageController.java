package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.services.ImageService;
import guru.springframework5.sfg_recipe_project.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @GetMapping ("/recipe/{recipeId}/renderImage")
    public void renderImageFromDB(@PathVariable String recipeId, HttpServletResponse response) throws IOException {
        RecipeCommand cmd = recipeService.findRecipeCommandById(Long.valueOf(recipeId));
        if (cmd.getImage() != null) {
            byte[] bytes = new byte[cmd.getImage().length];
            int i = 0;
            for (Byte b : cmd.getImage()) {
                bytes[i++] = b;
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(bytes);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
