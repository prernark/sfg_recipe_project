package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.domain.Category;
import guru.springframework5.sfg_recipe_project.domain.UnitOfMeasure;
import guru.springframework5.sfg_recipe_project.repositories.CategoryRepository;
import guru.springframework5.sfg_recipe_project.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class RecipeIndexController {
//    private final RecipeService recipeService;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
//    public RecipeIndexController(RecipeService recipeService) {
//        this.recipeService = recipeService;
//    }


    public RecipeIndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping ({"recipe", "", "/", "index"})
    public String listOfRecipes(){
        Optional<Category> catgOP = categoryRepository.findByDescription("Italian");
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
        return "recipe_index";
    }
}
