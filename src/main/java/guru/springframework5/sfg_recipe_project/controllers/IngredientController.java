package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.services.IngredientService;
import guru.springframework5.sfg_recipe_project.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping ("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model){
        log.debug("Viewing Ingredients for recipe id "+id);
        //use Command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
        return ("recipe/ingredient/list");
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingrId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingrId, Model model){
        log.debug("Showing ingredient "+ingrId+" for recipe "+recipeId);
        model.addAttribute("ingredient",
                           ingredientService.findbyRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingrId)));
        return "recipe/ingredient/show";

    }
}
