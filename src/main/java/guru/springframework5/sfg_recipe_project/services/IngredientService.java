package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findbyRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand cmd);
}
