package guru.springframework5.sfg_recipe_project.converters;

import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.commands.UnitOfMeasureCommand;
import guru.springframework5.sfg_recipe_project.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientCommandToIngredientTest {
    IngredientCommandToIngredient converter;

    private final Long LONG_ID = new Long(1L);
    private final String DESC = "UOM description";
    private final BigDecimal AMOUNT = new BigDecimal(1);
//    private final Recipe RECIPE = new Recipe();
    private final Long UOM_ID = new Long(2L);

    @BeforeEach
    public void setUp(){
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void IsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    public void IsEmptyTest(){
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert(){
        //given
        IngredientCommand cmd = new IngredientCommand();
        cmd.setId(LONG_ID);
        cmd.setDescription(DESC);
        cmd.setAmount(AMOUNT);
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        cmd.setUnitOfMeasure(uomCommand);
//        cmd.setRecipe(RECIPE);

        //when
        Ingredient ingredient = converter.convert(cmd);
        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(LONG_ID, ingredient.getId());
        assertEquals(DESC, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
//        assertNotNull(catg.getRecipeSet());
    }

    @Test
    public void convertWithNullUOM(){
        //given
        IngredientCommand cmd = new IngredientCommand();
        cmd.setId(LONG_ID);
        cmd.setDescription(DESC);
        cmd.setAmount(AMOUNT);
       //when
        Ingredient ingredient = converter.convert(cmd);
        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(LONG_ID, ingredient.getId());
        assertEquals(DESC, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }
}
