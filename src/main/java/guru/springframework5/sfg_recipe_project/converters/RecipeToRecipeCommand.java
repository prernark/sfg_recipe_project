package guru.springframework5.sfg_recipe_project.converters;

import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter,
                                 IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        final RecipeCommand command = new RecipeCommand();
        command.setId(recipe.getId());
        command.setDescription(recipe.getDescription());
        command.setDirections(recipe.getDirections());
        command.setUrl(recipe.getUrl());
        command.setSource(recipe.getSource());
        command.setServings(recipe.getServings());
        command.setPrepTime(recipe.getPrepTime());
        command.setCookTime(recipe.getCookTime());
        command.setDifficulty(recipe.getDifficulty());
        command.setImage(recipe.getImage());
        command.setNotes(notesConverter.convert(recipe.getNotes()));
        if (recipe.getCategorySet() != null && recipe.getCategorySet().size() > 0){
            recipe.getCategorySet()
                  .forEach(category ->
                               command.getCategorySet().add(categoryConverter.convert(category)));
        }
        if (recipe.getIngredientSet() != null && recipe.getIngredientSet().size() > 0){
            recipe.getIngredientSet()
                    .forEach(ingredient ->
                            command.getIngredientSet().add(ingredientConverter.convert(ingredient)));
        }
        return command;
    }
}
