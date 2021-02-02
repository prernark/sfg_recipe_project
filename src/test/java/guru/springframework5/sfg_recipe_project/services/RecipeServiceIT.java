package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.converters.RecipeCommandToRecipe;
import guru.springframework5.sfg_recipe_project.converters.RecipeToRecipeCommand;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.enums.Difficulty;
import guru.springframework5.sfg_recipe_project.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@ExtendWith(SpringExtension.class)
//normally we use @DataJpaTest which brings in a light Spring context and hence when we run the test
//it cant find the ServiceImpl and gives error. Therefore we are using @SpringBootTest as it brings the whole context for us
//based on our Spring Boot Configuration
@SpringBootTest
public class RecipeServiceIT {

    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSavedRecipe(){
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        System.out.println("Is Repository.findAll empty? "+(recipes.iterator().hasNext()?"No":"Yes"));
//        log.debug("Is Repository.findAll empty? "+(recipes.iterator().hasNext()?"No":"Yes"));
        Recipe recipe = recipes.iterator().next();
        System.out.println("First recipe Desc & Difficulty - "+recipe.getDescription()+" "+recipe.getDifficulty());
//        log.debug("First recipe Desc & Difficulty - "+recipe.getDescription()+" "+recipe.getDifficulty());
        RecipeCommand cmd = recipeToRecipeCommand.convert(recipe);

        //when
        cmd.setDifficulty(Difficulty.MODERATE);
        RecipeCommand savedRecipeCmd = recipeService.saveRecipeCommand(cmd);

        //then
        assertNotNull(savedRecipeCmd);
        System.out.println("recipe.getDifficulty() - "+recipe.getDifficulty());
        System.out.println("savedRecipeCmd.getDifficulty() - "+savedRecipeCmd.getDifficulty());
        assertEquals(recipe.getDifficulty(), savedRecipeCmd.getDifficulty());
        assertEquals(recipe.getDescription(), savedRecipeCmd.getDescription());
        assertEquals(recipe.getId(), savedRecipeCmd.getId());
        assertEquals(recipe.getCategorySet().size(), savedRecipeCmd.getCategorySet().size());
        assertEquals(recipe.getIngredientSet().size(), savedRecipeCmd.getIngredientSet().size());
    }

}


