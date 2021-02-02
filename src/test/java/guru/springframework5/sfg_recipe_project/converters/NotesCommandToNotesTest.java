package guru.springframework5.sfg_recipe_project.converters;

import guru.springframework5.sfg_recipe_project.commands.NotesCommand;
import guru.springframework5.sfg_recipe_project.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotesCommandToNotesTest {
    NotesCommandToNotes converter;

    private final Long LONG_ID = new Long(1L);
    private final String NOTES = "Recipe Notes";
//    private final Recipe RECIPE = new Recipe(); //JT NOT SETTING THIS

    @BeforeEach
    public void setUp(){
        converter = new NotesCommandToNotes();
    }

    @Test
    public void IsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    public void IsEmptyTest(){
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert(){
        //given
        NotesCommand cmd = new NotesCommand();
        cmd.setId(LONG_ID);
        cmd.setRecipeNotes(NOTES);
//        cmd.setRecipe(new RecipeToRecipeCommand().convert(RECIPE));

        //when
        Notes notes = converter.convert(cmd);
        //then
        assertNotNull(notes);
//        assertNotNull(notes.getRecipe());
        assertEquals(LONG_ID, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());
//        assertNotNull(notes.getRecipeSet());
    }
}
