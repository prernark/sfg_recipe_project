package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.converters.IngredientToIngredientCommand;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findbyRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOp = recipeRepository.findById(recipeId);
        if (recipeOp.isEmpty()){
            //todo impl error handling
            log.error("Recipe not found");
//            return null;
        }

        Recipe recipe = recipeOp.get();
        Optional<IngredientCommand> ingrCmdOp = recipe.getIngredientSet()
                                                    .stream()
                                                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                                                    .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                                                    .findFirst();
        if (ingrCmdOp.isEmpty()){
            //todo impl error handling
            log.error("Recipe not found");
//            return null;
        }
        return ingrCmdOp.get();
    }
}
