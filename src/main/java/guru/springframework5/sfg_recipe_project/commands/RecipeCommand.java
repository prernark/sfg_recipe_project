package guru.springframework5.sfg_recipe_project.commands;

import guru.springframework5.sfg_recipe_project.enums.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private Byte[] image;
    private Set<IngredientCommand> ingredientSet = new HashSet<>();
    private NotesCommand notes;
    private Set<CategoryCommand> categorySet = new HashSet<>();
}

