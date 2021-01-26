package guru.springframework5.sfg_recipe_project.services;

import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j //Lombok logging
@Service
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I am in the service implementation");
        Set<Recipe> set = new HashSet<>();
//        recipeRepository.findAll().forEach(recipe -> {
//            set.add(recipe);
//        });
        recipeRepository.findAll().forEach(set::add);
        return set;
    }
}
