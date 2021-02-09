package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.converters.IngredientCommandToIngredient;
import guru.springframework5.sfg_recipe_project.converters.IngredientToIngredientCommand;
import guru.springframework5.sfg_recipe_project.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework5.sfg_recipe_project.domain.Ingredient;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
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

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand cmd) {
        //why cant we just do IngredientRepository.save for updating ingredients????
        Optional<Recipe> recipeOptional = recipeRepository.findById(cmd.getRecipeId());
        if (recipeOptional.isEmpty()){
            log.error("Recipe with id "+cmd.getRecipeId()+" not found");
            return new IngredientCommand(); //DIDNT UNDERSTAND THIS??????????????????????????
        }
        Recipe recipe = recipeOptional.get();
        Optional<Ingredient> ingredientOp = recipe.getIngredientSet()
                                                  .stream()
                                                  .filter(ingredient -> ingredient.getId().equals(cmd.getId()))
                                                  .findFirst();
        if (ingredientOp.isEmpty()){ //ingredient for recipe not found so add NEW ingredient
            recipe.addIngredients(ingredientCommandToIngredient.convert(cmd));
        }
        else{ //ingredient found to update it
            Ingredient ingrFound = ingredientOp.get();
            ingrFound.setAmount(cmd.getAmount());
            ingrFound.setDescription(cmd.getDescription());
            ingrFound.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure.convert(cmd.getUnitOfMeasure()));
            ingrFound.setRecipe(recipe);
        }

        Recipe recipeSaved = recipeRepository.save(recipe); //due to cascade will save ingredients as well

        return ingredientToIngredientCommand.convert(recipeSaved.getIngredientSet()
                                                                .stream()
                                                                .filter(ingredient -> ingredient.getId().equals(cmd.getId()))
                                                                .findFirst()
                                                                .get());
    }
}
