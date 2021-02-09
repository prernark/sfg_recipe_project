package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> findAll();
}
