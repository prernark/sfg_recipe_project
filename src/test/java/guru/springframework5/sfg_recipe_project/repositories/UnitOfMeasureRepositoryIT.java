package guru.springframework5.sfg_recipe_project.repositories;

import guru.springframework5.sfg_recipe_project.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Integration Test
@ExtendWith(SpringExtension.class)
@DataJpaTest //Could do a Spring Boot test but this will bring up an embedded db and it will configure Spring Data JPA
class UnitOfMeasureRepositoryIT {

    @Autowired //Spring will do a dependency injection on Integration Test here
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> uomOp = unitOfMeasureRepository.findByDescription("Cup");
        assertEquals(uomOp.get().getDescription(),"Cup");
    }

    @Test
    //Note this test runs much faster as it runs in the same session as above test and the Spring Context is already there. No Reload
    void findByDescriptionPinch() {
        Optional<UnitOfMeasure> uomOp = unitOfMeasureRepository.findByDescription("Pinch");
        assertEquals(uomOp.get().getDescription(),"Pinch");
    }
}
