package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.converters.IngredientToIngredientCommand;
import guru.springframework5.sfg_recipe_project.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework5.sfg_recipe_project.domain.Ingredient;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private IngredientService ingredientService;
    @Mock
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    @Mock
    private RecipeRepository recipeRepository;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @BeforeEach
    void setUp() {
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findbyRecipeIdAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingr1 = new Ingredient();
        ingr1.setId(1L);
        Ingredient ingr2 = new Ingredient();
        ingr2.setId(2L);
        Ingredient ingr3 = new Ingredient();
        ingr3.setId(3L);
        recipe.addIngredients(ingr1);
        recipe.addIngredients(ingr2);
        recipe.addIngredients(ingr3);

        Optional<Recipe> recipeOptional = Optional.ofNullable(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand cmd = new IngredientCommand();
        cmd.setId(3L);
        when(ingredientToIngredientCommand.convert(any())).thenReturn(cmd);

        //when
        IngredientCommand cmd1 = ingredientService.findbyRecipeIdAndIngredientId(1L,3L);

        //then
        assertNotNull(cmd1);
        assertEquals(1L, cmd1.getRecipeId());
        assertEquals(3L, cmd1.getId());

        verify(recipeRepository, times(1)).findById(1L);
        verify(recipeRepository, never()).findAll();
        verify(ingredientToIngredientCommand, times(1)).convert(any());
    }
}