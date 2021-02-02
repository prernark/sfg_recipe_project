package guru.springframework5.sfg_recipe_project.converters;

import guru.springframework5.sfg_recipe_project.commands.NotesCommand;
import guru.springframework5.sfg_recipe_project.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotesToNotesCommandTest {
    NotesToNotesCommand converter;

    private final Long LONG_ID = new Long(1L);
    private final String NOTES = "Recipe Notes";
//    private final Recipe RECIPE = new Recipe(); //JT NOT SETTING THIS

    @BeforeEach
    public void setUp(){
        converter = new NotesToNotesCommand();
    }

    @Test
    public void IsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    public void IsEmptyTest(){
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert(){
        //given
        Notes notes = new Notes();
        notes.setId(LONG_ID);
        notes.setRecipeNotes(NOTES);
//        cmd.setRecipe(new RecipeToRecipeCommand().convert(RECIPE));

        //when
        NotesCommand cmd = converter.convert(notes);
        //then
        assertNotNull(cmd);
//        assertNotNull(notes.getRecipe());
        assertEquals(LONG_ID, cmd.getId());
        assertEquals(NOTES, cmd.getRecipeNotes());
//        assertNotNull(notes.getRecipeSet());
    }
}
