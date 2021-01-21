package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeIndexController {
    private final RecipeService recipeService;

    public RecipeIndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping ({"recipe", "", "/", "index"})
    public String listOfRecipes(){
        return "recipe_index";
    }
}
