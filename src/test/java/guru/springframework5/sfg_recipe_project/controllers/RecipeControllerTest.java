package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {
    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    RecipeController recipeController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        recipeController = new RecipeController(recipeService);

    }

    @Test
    public void showRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        MockMvc mockMVC = MockMvcBuilders.standaloneSetup(recipeController).build();
        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMVC.perform(MockMvcRequestBuilders.post("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

}
