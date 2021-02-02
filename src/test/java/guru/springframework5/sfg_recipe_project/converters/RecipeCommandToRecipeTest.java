package guru.springframework5.sfg_recipe_project.converters;

import guru.springframework5.sfg_recipe_project.commands.CategoryCommand;
import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.commands.NotesCommand;
import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.enums.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeCommandToRecipeTest {
    private RecipeCommandToRecipe converter;

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
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                                              new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                                              new NotesCommandToNotes());
    }

    @Test
    public void isNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    public void isEmptyTest(){
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert(){
        //given
        RecipeCommand cmd = new RecipeCommand();
        cmd.setId(LONG_ID);
        cmd.setCookTime(COOKTIME);
        cmd.setDescription(DESC);
        cmd.setDifficulty(DIFFICULTY);
        cmd.setDirections(DIRECTIONS);
        cmd.setPrepTime(PREPTIME);
        cmd.setServings(SERVES);
        cmd.setSource(SOURCE);
        cmd.setUrl(URL);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        cmd.setNotes(notesCommand);

        CategoryCommand catgCommand = new CategoryCommand();
        catgCommand.setId(CATG_ID1);
        cmd.getCategorySet().add(catgCommand);
        catgCommand = new CategoryCommand();
        catgCommand.setId(CATG_ID2);
        cmd.getCategorySet().add(catgCommand);

        IngredientCommand ingrCommand = new IngredientCommand();
        ingrCommand.setId(INGR_ID1);
        cmd.getIngredientSet().add(ingrCommand);
        ingrCommand = new IngredientCommand();
        ingrCommand.setId(INGR_ID2);
        cmd.getIngredientSet().add(ingrCommand);

        //when
        Recipe recipe = converter.convert(cmd);

        //then
        assertNotNull(recipe);
        assertEquals(LONG_ID, recipe.getId());
        assertEquals(COOKTIME, recipe.getCookTime());
        assertEquals(PREPTIME, recipe.getPrepTime());
        assertEquals(DESC, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVES, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertNotNull(recipe.getNotes());
        assertNotNull(recipe.getCategorySet());
        assertNotNull(recipe.getIngredientSet());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategorySet().size());
        assertEquals(2, recipe.getIngredientSet().size());
    }
}
