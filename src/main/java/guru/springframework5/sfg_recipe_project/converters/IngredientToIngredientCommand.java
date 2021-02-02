package guru.springframework5.sfg_recipe_project.converters;

import com.sun.istack.Nullable;
import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;
//    JT NOT SETTING THIS
//    RecipeToRecipeCommand recipeConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
//                                         , RecipeToRecipeCommand recipeConverter) {
        this.uomConverter = uomConverter;
//        this.recipeConverter = recipeConverter;
    }

    @Synchronized
    @Nullable
    @Override

    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        final IngredientCommand command = new IngredientCommand();
        command.setId(ingredient.getId());
        command.setDescription(ingredient.getDescription());
        command.setAmount(ingredient.getAmount());
        command.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
//        command.setRecipe(recipeConverter.convert(ingredient.getRecipe()));
        return command;
    }
}