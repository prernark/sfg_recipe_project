package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.commands.UnitOfMeasureCommand;
import guru.springframework5.sfg_recipe_project.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework5.sfg_recipe_project.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> findAll() {
//        Set<UnitOfMeasureCommand> set = new HashSet<>();
//        unitOfMeasureRepository.findAll()
//                               .forEach(uom -> {
//                                    UnitOfMeasureCommand cmd = unitOfMeasureToUnitOfMeasureCommand.convert(uom);
//                                    set.add(cmd);
//                               });
//        return set;

        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(),false)
                            .map(x->unitOfMeasureToUnitOfMeasureCommand.convert(x)) //OR .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                            .collect(Collectors.toSet());

    }
}
