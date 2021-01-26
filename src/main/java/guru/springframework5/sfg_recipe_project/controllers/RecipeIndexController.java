package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RecipeIndexController {
    private final RecipeService recipeService;

    public RecipeIndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping ({"recipe", "", "/", "index"})
    public String listOfRecipes(Model model){
        log.debug("In RecipeIndexController RequestMapping method");
        model.addAttribute("recipes", recipeService.getRecipes());

        /*Optional<Category> catgOP = categoryRepository.findByDescription("Italian");
        Optional<UnitOfMeasure> uomOP = unitOfMeasureRepository.findByDescription("Pinch");
        if (catgOP.isPresent()){
            System.out.println("Category id = " + catgOP.get().getId());
        }
        else {
            System.out.println("Category not found");}
        if (!uomOP.isEmpty()) {
            System.out.println("UOM Id = " + uomOP.get().getId());
        }
        else{
            System.out.println("Unit Of Measure not found");}
        */

        return "recipe_index";
    }
}
