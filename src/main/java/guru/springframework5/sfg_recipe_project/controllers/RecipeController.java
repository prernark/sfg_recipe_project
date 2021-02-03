package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping ("/recipe/show/{id}") //{id} is a variable which will take the id value from the URL
    public String showRecipe(@PathVariable String id, Model model){ //The id var is a String but we will convert it to Long
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @RequestMapping ("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    //    @RequestMapping (name="recipe", method= RequestMethod.POST) //older method. Below new way
    @PostMapping
    @RequestMapping("/recipe/") //this string has to be the same as html entry th:action="@{/recipe/}"
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
//        System.out.println("Saved id "+savedCommand.getId());
//        String url = "redirect:/recipe/show/"+savedCommand.getId();
//        System.out.println("URL "+url);
        return "redirect:/recipe/show/"+savedCommand.getId();
    }

}
