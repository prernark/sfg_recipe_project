package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.commands.UnitOfMeasureCommand;
import guru.springframework5.sfg_recipe_project.converters.IngredientCommandToIngredient;
import guru.springframework5.sfg_recipe_project.converters.IngredientToIngredientCommand;
import guru.springframework5.sfg_recipe_project.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework5.sfg_recipe_project.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework5.sfg_recipe_project.domain.Ingredient;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.domain.UnitOfMeasure;
import guru.springframework5.sfg_recipe_project.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private IngredientService ingredientService;
    @Mock
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        this.unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand,
                                                      ingredientCommandToIngredient, unitOfMeasureCommandToUnitOfMeasure);
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
        cmd.setRecipeId(1L);
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

    @Test
    public void saveIngredientCommandNullRecipe(){
        //given
        Recipe recipe = null;
        Optional<Recipe> recipeOptional1 = Optional.ofNullable(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional1);

        //when
        IngredientCommand cmd = ingredientService.saveIngredientCommand(new IngredientCommand());

        //then
        assertNotNull(cmd);
        assertNull(cmd.getId());
    }

    @Test
    public void saveIngredientCommand(){
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
        Optional<Recipe> recipeOp = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOp);

        when(recipeRepository.save(any())).thenReturn(recipe);

        IngredientCommand cmd = new IngredientCommand();
        cmd.setId(3L);
        cmd.setRecipeId(1L);
        cmd.setAmount(new BigDecimal(100));
        when(ingredientToIngredientCommand.convert(any())).thenReturn(cmd);

        //when
        IngredientCommand ingrCmd = ingredientService.saveIngredientCommand(cmd);

        //then
        assertNotNull(ingrCmd);
        assertEquals(3L, ingrCmd.getId());
        assertEquals(1L, ingrCmd.getRecipeId());
        assertEquals(new BigDecimal(100), ingrCmd.getAmount());
        verify(recipeRepository, times(1)).findById(1L);
        verify(recipeRepository, never()).findAll();
        verify(unitOfMeasureCommandToUnitOfMeasure, times(1)).convert(any());
        verify(recipeRepository, times(1)).save(any());
        verify(recipeRepository, never()).saveAll(any());
        verify(ingredientToIngredientCommand, times(1)).convert(any());
    }

    @Test
    public void saveNewIngredient(){
        IngredientCommand cmd = new IngredientCommand();
        cmd.setRecipeId(1L);
        cmd.setAmount(BigDecimal.valueOf(12));
        cmd.setDescription("Sugar");
        UnitOfMeasureCommand uomCmd = new UnitOfMeasureCommand();
        uomCmd.setId(1L);
        cmd.setUnitOfMeasure(uomCmd);

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setIngredientSet(new HashSet<>());
        Optional<Recipe> recipeOp = Optional.of(recipe);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(10L);
        ingredient.setAmount(BigDecimal.valueOf(12));
        ingredient.setDescription("Sugar");
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(1L);
        ingredient.setUnitOfMeasure(uom);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOp);
        when(ingredientCommandToIngredient.convert(any())).thenReturn(ingredient);
        when(recipeRepository.save(any())).thenReturn(recipe);
        when(ingredientToIngredientCommand.convert(any())).thenReturn(cmd);

        //when
        IngredientCommand savedCmd = ingredientService.saveIngredientCommand(cmd);

        //then
        assertNotNull(savedCmd);
        assertNull(savedCmd.getId());
        assertEquals(cmd.getRecipeId(), savedCmd.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
        verify(recipeRepository, times(1)).save(any());
        verify(ingredientToIngredientCommand, times(1)).convert(any());
        verify(ingredientCommandToIngredient, times(1)).convert(any());
    }

    @Test
    public void deleteIngredientById(){
        //given
        Long idToDelete = 1L;
        Long recipeId = 1L;

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.addIngredients(ingredient);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(any());

        //when
        ingredientService.deleteIngredientById(recipeId, idToDelete);
        //then
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }
}