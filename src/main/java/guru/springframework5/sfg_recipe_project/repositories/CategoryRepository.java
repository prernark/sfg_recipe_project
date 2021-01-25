package guru.springframework5.sfg_recipe_project.repositories;

import guru.springframework5.sfg_recipe_project.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    //Earlier Spring Data would return null but now it will return Optional type
    Optional<Category> findByDescription(String description);
}
