package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);

}
