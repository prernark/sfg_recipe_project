package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    //When View on recipe_index.html is clicked
    @GetMapping("/recipe/{id}/show") //{id} is a variable which will take the id value from the URL
    public String showRecipe(@PathVariable String id, Model model){ //The id var is a String but we will convert it to Long
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    //When Update on recipe_index.html is clicked
    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    //    @RequestMapping (name="recipe", method= RequestMethod.POST) //older method. Below new way
    @PostMapping("/recipe/") //this string has to be the same as html entry th:action="@{/recipe/}"
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+savedCommand.getId()+"/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id "+id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}
