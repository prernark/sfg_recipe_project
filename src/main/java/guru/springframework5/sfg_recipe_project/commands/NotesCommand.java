package guru.springframework5.sfg_recipe_project.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
    private Long Id;
//    private RecipeCommand recipe; JT is not setting this
    private String recipeNotes;
}
