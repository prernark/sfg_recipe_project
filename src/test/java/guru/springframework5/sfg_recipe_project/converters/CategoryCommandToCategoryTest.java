package guru.springframework5.sfg_recipe_project.converters;

import guru.springframework5.sfg_recipe_project.commands.CategoryCommand;
import guru.springframework5.sfg_recipe_project.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryCommandToCategoryTest {
    CategoryCommandToCategory converter;

    private final Long LONG_ID = new Long(1L);
    private final String DESC = "UOM description";

    @BeforeEach
    public void setUp(){
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void IsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    public void IsEmptyTest(){
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert(){
        //given
        CategoryCommand cmd = new CategoryCommand();
        cmd.setId(LONG_ID);
        cmd.setDescription(DESC);
//        cmd.setRecipeSet(recipeConverter.convert());
        //when
        Category catg = converter.convert(cmd);
        //then
        assertNotNull(catg);
        assertEquals(LONG_ID, catg.getId());
        assertEquals(DESC, catg.getDescription());
//        assertNotNull(catg.getRecipeSet());
    }
}
