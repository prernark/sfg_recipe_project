package guru.springframework5.sfg_recipe_project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {
    Category category; //= new Category(); or below in new method as @Before

    @BeforeEach
    public void setup(){
        category = new Category();
    }

    @Test
    void getId() {
        Long id = 4L;
        category.setId(id);
        assertEquals(category.getId(), id);
    }

    @Test
    void getDescription() {
        String desc = "Italian";
        category.setDescription(desc);
        assertEquals(desc, category.getDescription());
    }

    @Test
    void getRecipeSet() {
        /*Set<Recipe> recipeSet = new HashSet<>();
        Recipe recipe = new Recipe();
        recipe.setCookTime(0);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setDescription("Perfect Guacamole");
        recipe.setPrepTime(10);
        recipe.setServings(4);
        recipe.setSource("Elise Bauer");
        recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/#recipe159");
        recipe.setDirections("Prepare Guacamole");

        Notes notes = new Notes();
        notes.setRecipeNotes("Recipe Notes");
        recipe.setNotes(notes);
        recipeSet.add(recipe);

        recipe = new Recipe();
        recipe.setCookTime(20);
        recipe.setDifficulty(Difficulty.HARD);
        recipe.setDescription("Puran Poli");
        notes = new Notes();
        notes.setRecipeNotes("Puran Poli Notes");
        recipe.setNotes(notes);
        recipeSet.add(recipe);

        category.setRecipeSet(recipeSet);
        assertEquals(category.getRecipeSet().size(), recipeSet.size());
        assertEquals(category.getRecipeSet().stream().spliterator().getComparator().), recipeSet.size());*/
    }
}