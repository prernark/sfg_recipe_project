package guru.springframework5.sfg_recipe_project.converters;

import com.sun.istack.Nullable;
import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.domain.Ingredient;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;
//    JT NOT SETTING THIS
//    RecipeCommandToRecipe recipeConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
//                                         , RecipeCommandToRecipe recipeConverter) {
        this.uomConverter = uomConverter;
//        this.recipeConverter = recipeConverter;
    }

    @Synchronized
    @Nullable
    @Override

    public Ingredient convert(IngredientCommand command) {
        if (command == null) {
            return null;
        }
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(command.getId());
//        ingredient.getRecipe().setId(command.getRecipeId());
        ingredient.setDescription(command.getDescription());
        ingredient.setAmount(command.getAmount());
        ingredient.setUnitOfMeasure(uomConverter.convert(command.getUnitOfMeasure()));
//        ingredient.setRecipe(recipeConverter.convert(command.getRecipe()));
        Recipe recipe = new Recipe();
        recipe.setId(command.getRecipeId());
        ingredient.setRecipe(recipe);

        return ingredient;
    }
}
