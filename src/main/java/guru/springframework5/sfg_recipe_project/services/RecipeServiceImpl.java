package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.converters.RecipeCommandToRecipe;
import guru.springframework5.sfg_recipe_project.converters.RecipeToRecipeCommand;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j //Lombok logging
@Service
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I am in the service implementation");
        Set<Recipe> set = new HashSet<>();
//        recipeRepository.findAll().forEach(recipe -> {
//            set.add(recipe);
//        });
        recipeRepository.findAll().forEach(set::add);
        return set;
    }

    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isEmpty())
            throw new RuntimeException("Recipe Not Found");
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        //recipe below is still a POJO and not a Hibernate obj so its detached from Hibernate context
        Recipe recipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(recipe); //under the covers, if its new it will create else update
        log.debug("Saved Recipe Id "+savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findRecipeCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }
}
