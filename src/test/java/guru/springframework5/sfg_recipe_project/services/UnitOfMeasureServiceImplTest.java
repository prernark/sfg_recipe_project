package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.commands.UnitOfMeasureCommand;
import guru.springframework5.sfg_recipe_project.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework5.sfg_recipe_project.domain.UnitOfMeasure;
import guru.springframework5.sfg_recipe_project.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {
    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;
//    @Mock
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

    UnitOfMeasureService unitOfMeasureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void findAll() {
        //given
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        Set<UnitOfMeasure> set = new HashSet<>();
        set.add(uom1);
        set.add(uom2);
        when(unitOfMeasureRepository.findAll()).thenReturn(set);

//        NOTE THAT unitOfMeasureToUnitOfMeasureCommand IS NOT MOCK
//        THE REASON IS THAT findAll() uses convert for each obect in set which is difficult to mock
//        HENCE DIDNT MOCK IT AND INITIALISED IT INSTEAD TO NOT GET A NPE
//        AND HENCE BELOW NOT NEEDED
    //        UnitOfMeasureCommand cmd = new UnitOfMeasureCommand();
    //        when(unitOfMeasureToUnitOfMeasureCommand.convert(any())).thenReturn(cmd);

        //when
        Set<UnitOfMeasureCommand> cmdSet = unitOfMeasureService.findAll();

        //then
        assertNotNull(cmdSet);
        assertEquals(set.size(), cmdSet.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}