package guru.springframework5.sfg_recipe_project.converters;

import guru.springframework5.sfg_recipe_project.commands.UnitOfMeasureCommand;
import guru.springframework5.sfg_recipe_project.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {
    UnitOfMeasureToUnitOfMeasureCommand converter;
    private final Long LONG_ID = new Long(1L);
    private final String DESC = "UOM description";

    @BeforeEach
    public void setUp(){
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void IsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    public void IsEmptyTest(){
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert(){
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_ID);
        uom.setDescription(DESC);
        //when
        UnitOfMeasureCommand cmd = converter.convert(uom);
        //then
        assertNotNull(cmd);
        assertEquals(LONG_ID, cmd.getId());
        assertEquals(DESC, cmd.getDescription());
    }
}
