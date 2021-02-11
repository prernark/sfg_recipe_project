package guru.springframework5.sfg_recipe_project.converters;

import com.sun.istack.Nullable;
import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter,
                                 IngredientCommandToIngredient ingredientConverter,
                                 NotesCommandToNotes notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand command) {
        if (command == null) {
            return null;
        }
        final Recipe recipe = new Recipe();
        recipe.setId(command.getId());
        recipe.setDescription(command.getDescription());
        recipe.setDirections(command.getDirections());
        recipe.setUrl(command.getUrl());
        recipe.setSource(command.getSource());
        recipe.setServings(command.getServings());
        recipe.setPrepTime(command.getPrepTime());
        recipe.setCookTime(command.getCookTime());
        recipe.setDifficulty(command.getDifficulty());
        recipe.setImage(command.getImage());
        recipe.setNotes(notesConverter.convert(command.getNotes()));
        if (command.getCategorySet() != null && command.getCategorySet().size() > 0){
            command.getCategorySet()
                   .forEach(categoryCommand ->
                                recipe.getCategorySet().add(categoryConverter.convert(categoryCommand)));
        }
        if (command.getIngredientSet() != null && command.getIngredientSet().size() > 0){
            command.getIngredientSet()
                    .forEach(ingredientCommand ->
                            recipe.getIngredientSet().add(ingredientConverter.convert(ingredientCommand)));
        }
        return recipe;
    }
}
