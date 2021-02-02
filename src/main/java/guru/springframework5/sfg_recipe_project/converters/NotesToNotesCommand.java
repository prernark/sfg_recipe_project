package guru.springframework5.sfg_recipe_project.converters;

import com.sun.istack.Nullable;
import guru.springframework5.sfg_recipe_project.commands.NotesCommand;
import guru.springframework5.sfg_recipe_project.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
//    JT NOT SETTING THIS
//    RecipeToRecipeCommand recipeConverter;
//
//    public NotesToNotesCommand(RecipeToRecipeCommand recipeConverter) {
//        this.recipeConverter = recipeConverter;
//    }

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if (notes == null) {
            return null;
        }
        final NotesCommand command = new NotesCommand();
        command.setId(notes.getId());
        command.setRecipeNotes(notes.getRecipeNotes());
//        command.setRecipe(recipeConverter.convert(notes.getRecipe()));
        return command;
    }
}
