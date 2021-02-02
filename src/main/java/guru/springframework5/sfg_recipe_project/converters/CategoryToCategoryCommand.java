package guru.springframework5.sfg_recipe_project.converters;

import com.sun.istack.Nullable;
import guru.springframework5.sfg_recipe_project.commands.CategoryCommand;
import guru.springframework5.sfg_recipe_project.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
//    JT NOT SETTING THIS
//    RecipeToRecipeCommand recipeConverter;
//
//    public CategoryToCategoryCommand(RecipeToRecipeCommand recipeConverter) {
//        this.recipeConverter = recipeConverter;
//    }

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if (category == null) {
            return null;
        }
        final CategoryCommand command = new CategoryCommand();
        command.setId(category.getId());
        command.setDescription(category.getDescription());
//        if (category.getRecipeSet() != null && category.getRecipeSet().size() > 0){
//            category.getRecipeSet()
//                    .forEach(recipe ->
//                            command.getRecipeSet().add(recipeConverter.convert(recipe)));
//        }
        return command;
    }
}
