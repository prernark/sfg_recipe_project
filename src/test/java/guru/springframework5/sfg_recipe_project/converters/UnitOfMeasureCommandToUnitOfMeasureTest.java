package guru.springframework5.sfg_recipe_project.converters;

import guru.springframework5.sfg_recipe_project.commands.UnitOfMeasureCommand;
import guru.springframework5.sfg_recipe_project.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
    UnitOfMeasureCommandToUnitOfMeasure converter;
    private final Long LONG_ID = new Long(1L);
    private final String DESC = "UOM description";

    @BeforeEach
    public void setUp(){
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void IsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    public void IsEmptyTest(){
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert(){
        //given
        UnitOfMeasureCommand cmd = new UnitOfMeasureCommand();
        cmd.setId(LONG_ID);
        cmd.setDescription(DESC);
        //when
        UnitOfMeasure uom = converter.convert(cmd);
        //then
        assertNotNull(uom);
        assertEquals(LONG_ID, uom.getId());
        assertEquals(DESC, uom.getDescription());
    }
}
