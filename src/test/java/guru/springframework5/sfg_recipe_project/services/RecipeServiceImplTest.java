package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes(){
        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 0); //success
        //now if we want to return a recipe, we can ask mockito to return oe in which case size will be 1

        Recipe recipe = new Recipe();
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(recipe);
        Mockito.when(recipeService.getRecipes()).thenReturn(recipeSet);
        recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), recipeSet.size()); //success

        //to verify for mockito interactions
        //so to verify that findAll() on repository was called only twice, once before first assert and then before 2nd assert
        verify(recipeRepository, times(2)).findAll();

    }
}