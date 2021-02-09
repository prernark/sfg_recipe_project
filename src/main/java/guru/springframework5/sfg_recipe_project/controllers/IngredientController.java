package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.services.IngredientService;
import guru.springframework5.sfg_recipe_project.services.RecipeService;
import guru.springframework5.sfg_recipe_project.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping ("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model){
        log.debug("Viewing Ingredients list for recipe id "+id);
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

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/update") //open the edit Ingredient form
    public String openEditIngredientForm(@PathVariable String recipeId, @PathVariable String id, Model model){
        log.debug("Opening the particular ingredient "+id+" for recipe "+recipeId+" in Update mode");
        model.addAttribute("ingredient",
                            ingredientService.findbyRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.findAll());
        return "recipe/ingredient/editIngredientForm";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient") //UPDATE THE PARTICULAR INGREDIENT
    public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand cmd, @PathVariable String recipeId ){
        log.debug("Updating Ingredient for Recipe "+recipeId);
        IngredientCommand savedCmd = ingredientService.saveIngredientCommand(cmd);
        return "redirect:/recipe/"+savedCmd.getRecipeId()+"/ingredient/"+savedCmd.getId()+"/show";
    }
}
