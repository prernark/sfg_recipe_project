package guru.springframework5.sfg_recipe_project.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
//    private Set<RecipeCommand> recipeSet; //JT is not setting this
}
