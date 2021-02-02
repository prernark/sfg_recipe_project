package guru.springframework5.sfg_recipe_project.converters;

import guru.springframework5.sfg_recipe_project.commands.CategoryCommand;
import guru.springframework5.sfg_recipe_project.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryToCategoryCommandTest {
    CategoryToCategoryCommand converter;

    private final Long LONG_ID = new Long(1L);
    private final String DESC = "UOM description";

    @BeforeEach
    public void setUp(){
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void IsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    public void IsEmptyTest(){
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert(){
        //given
        Category catg = new Category();
        catg.setId(LONG_ID);
        catg.setDescription(DESC);
//        catg.setRecipeSet(recipeConverter.convert());
        //when
        CategoryCommand cmd = converter.convert(catg);
        //then
        assertNotNull(cmd);
        assertEquals(LONG_ID, cmd.getId());
        assertEquals(DESC, cmd.getDescription());
//        assertNotNull(cmd.getRecipeSet());
    }

}
