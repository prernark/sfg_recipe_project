package guru.springframework5.sfg_recipe_project.converters;

import com.sun.istack.Nullable;
import guru.springframework5.sfg_recipe_project.commands.CategoryCommand;
import guru.springframework5.sfg_recipe_project.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
//    JT IS NOT SETTING THe SET OF RECIPES ON CATEGORY
//    RecipeCommandToRecipe recipeConverter;
//
//    public CategoryCommandToCategory(RecipeCommandToRecipe recipeConverter) {
//        this.recipeConverter = recipeConverter;
//    }

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand == null) {
            return null;
        }
        final Category catg = new Category();
        catg.setId(categoryCommand.getId());
        catg.setDescription(categoryCommand.getDescription());
//        if (categoryCommand.getRecipeSet() != null && categoryCommand.getRecipeSet().size() > 0){
//            categoryCommand.getRecipeSet()
//                           .forEach(command ->
//                                        catg.getRecipeSet().add(recipeConverter.convert(command)));
//        }
        return catg;
    }
}
