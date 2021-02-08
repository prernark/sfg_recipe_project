package guru.springframework5.sfg_recipe_project.converters;

import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.domain.Ingredient;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientToIngredientCommandTest {
    IngredientToIngredientCommand converter;

    private final Long LONG_ID = new Long(1L);
    private final String DESC = "UOM description";
    private final BigDecimal AMOUNT = new BigDecimal(1);
    private final Recipe RECIPE = new Recipe();
    private final Long UOM_ID = new Long(2L);

    @BeforeEach
    public void setUp(){
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void IsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    public void IsEmptyTest(){
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convert(){
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(LONG_ID);
        ingredient.setDescription(DESC);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUnitOfMeasure(uom);
        Recipe recipe = new Recipe();
        recipe.setId(LONG_ID);
        ingredient.setRecipe(recipe);
//        cmd.setRecipe(RECIPE);
//        cmd.setRecipeSet(uomConverter.convert());

        //when
        IngredientCommand cmd = converter.convert(ingredient);
        //then
        assertNotNull(cmd);
        assertNotNull(cmd.getUnitOfMeasure());
        assertEquals(LONG_ID, cmd.getId());
        assertEquals(LONG_ID, cmd.getRecipeId());
        assertEquals(DESC, cmd.getDescription());
        assertEquals(AMOUNT, cmd.getAmount());
        assertEquals(UOM_ID, cmd.getUnitOfMeasure().getId());
//        assertNotNull(catg.getRecipeSet());
    }

    @Test
    public void convertWithNullUOM() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(LONG_ID);
        ingredient.setDescription(DESC);
        ingredient.setAmount(AMOUNT);
//        cmd.setRecipe(RECIPE);
//        cmd.setRecipeSet(uomConverter.convert());

        //when
        IngredientCommand cmd = converter.convert(ingredient);
        //then
        assertNotNull(cmd);
        assertNull(cmd.getUnitOfMeasure());
        assertEquals(LONG_ID, cmd.getId());
        assertEquals(DESC, cmd.getDescription());
        assertEquals(AMOUNT, cmd.getAmount());
//        assertEquals(UOM_ID, cmd.getUnitOfMeasure().getId());
//        assertNotNull(catg.getRecipeSet());
    }

}
