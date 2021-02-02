package guru.springframework5.sfg_recipe_project.converters;

import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.domain.Category;
import guru.springframework5.sfg_recipe_project.domain.Ingredient;
import guru.springframework5.sfg_recipe_project.domain.Notes;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.enums.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeToRecipeCommandTest {
    private RecipeToRecipeCommand converter;

    private final Long LONG_ID = new Long(1L);
    private final String DESC = "Recipe Desc";
    private final String DIRECTIONS = "Directions";
    private final String URL = "URL";
    private final String SOURCE = "Source";
    private final Integer SERVES = new Integer(4);
    private final Integer PREPTIME = new Integer(10);
    private final Integer COOKTIME = new Integer(15);
    private final Difficulty DIFFICULTY = Difficulty.EASY;
    private final Long CATG_ID1 = new Long(1L);
    private final Long CATG_ID2 = new Long(2L);
    private final Long INGR_ID1 = new Long(3L);
    private final Long INGR_ID2 = new Long(4L);
    private final Long NOTES_ID = new Long(9L);


    @BeforeEach
    public void setUp(){
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
                                              new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                                              new NotesToNotesCommand());
    }

    @Test
    public void isNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    public void isEmptyTest(){
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert(){
        //given
        Recipe recipe = new Recipe();
        recipe.setId(LONG_ID);
        recipe.setCookTime(COOKTIME);
        recipe.setDescription(DESC);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setPrepTime(PREPTIME);
        recipe.setServings(SERVES);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Category catg = new Category();
        catg.setId(CATG_ID1);
        recipe.getCategorySet().add(catg);
        catg = new Category();
        catg.setId(CATG_ID2);
        recipe.getCategorySet().add(catg);

        Ingredient ingr = new Ingredient();
        ingr.setId(INGR_ID1);
        recipe.getIngredientSet().add(ingr);
        ingr = new Ingredient();
        ingr.setId(INGR_ID2);
        recipe.getIngredientSet().add(ingr);

        //when
        RecipeCommand cmd = converter.convert(recipe);

        //then
        assertNotNull(cmd);
        assertEquals(LONG_ID, cmd.getId());
        assertEquals(COOKTIME, cmd.getCookTime());
        assertEquals(PREPTIME, cmd.getPrepTime());
        assertEquals(DESC, cmd.getDescription());
        assertEquals(DIFFICULTY, cmd.getDifficulty());
        assertEquals(DIRECTIONS, cmd.getDirections());
        assertEquals(SERVES, cmd.getServings());
        assertEquals(SOURCE, cmd.getSource());
        assertEquals(URL, cmd.getUrl());
        assertNotNull(cmd.getNotes());
        assertNotNull(cmd.getCategorySet());
        assertNotNull(cmd.getIngredientSet());
        assertEquals(NOTES_ID, cmd.getNotes().getId());
        assertEquals(2, cmd.getCategorySet().size());
        assertEquals(2, cmd.getIngredientSet().size());
    }
}
