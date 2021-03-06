package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.converters.RecipeCommandToRecipe;
import guru.springframework5.sfg_recipe_project.converters.RecipeToRecipeCommand;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeById(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.ofNullable(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);
        assertNotNull(recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipes(){
        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 0); //success
        //now if we want to return a recipe, we can ask mockito to return oe in which case size will be 1

        Recipe recipe = new Recipe();
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(recipe);
        when(recipeService.getRecipes()).thenReturn(recipeSet);
        recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), recipeSet.size()); //success

        //to verify for mockito interactions
        //so to verify that findAll() on repository was called only twice, once before first assert and then before 2nd assert
        verify(recipeRepository, times(2)).findAll();
    }

    @Test
    public void findRecipeCommandById(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand cmd = new RecipeCommand();
        cmd.setId(1L);
        when(recipeToRecipeCommand.convert(any())).thenReturn(cmd);

        RecipeCommand cmd1 = recipeService.findRecipeCommandById(1L);
        assertNotNull(cmd1);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
        verify(recipeToRecipeCommand, times(1)).convert(any());
    }

    @Test
    public void saveRecipeCommand(){
        RecipeCommand cmd = new RecipeCommand();
        cmd.setId(1L);
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeRepository.save(any())).thenReturn(recipe);
        when(recipeCommandToRecipe.convert(any())).thenReturn(recipe);
        when(recipeToRecipeCommand.convert(any())).thenReturn(cmd);

        RecipeCommand savedCmd = recipeService.saveRecipeCommand(cmd);
        assertNotNull(savedCmd);
        assertEquals(1L, savedCmd.getId());
        verify(recipeCommandToRecipe, times(1)).convert(any());
        verify(recipeToRecipeCommand, times(1)).convert(any());
        verify(recipeRepository, times(1)).save(any());
    }

    @Test
    public void deleteById(){
        //given
        Long idToDelete = 2L;

        //when (no when() as method has void return type
        recipeService.deleteById(idToDelete);

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

}