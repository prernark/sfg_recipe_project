package guru.springframework5.sfg_recipe_project.converters;

import com.sun.istack.Nullable;
import guru.springframework5.sfg_recipe_project.commands.NotesCommand;
import guru.springframework5.sfg_recipe_project.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
//    JT NOT SETTING THIS
//    RecipeCommandToRecipe recipeConverter;
//
//    public NotesCommandToNotes(RecipeCommandToRecipe recipeConverter) {
//        this.recipeConverter = recipeConverter;
//    }

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand command) {
        if (command == null) {
            return null;
        }
        final Notes notes = new Notes();
        notes.setId(command.getId());
        notes.setRecipeNotes(command.getRecipeNotes());
//        notes.setRecipe(recipeConverter.convert(command.getRecipe()));
        return notes;
    }
}