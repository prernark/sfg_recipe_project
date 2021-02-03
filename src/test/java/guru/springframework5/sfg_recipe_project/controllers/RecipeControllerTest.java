package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {
    private final Long LONG_ID = 1L;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    RecipeController recipeController;
    MockMvc mockMVC;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        recipeController = new RecipeController(recipeService);
        mockMVC = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void showRecipe() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(LONG_ID);

        //when
        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMVC.perform(post("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void getNewRecipeForm() throws Exception {
        RecipeCommand cmd = new RecipeCommand();
        mockMVC.perform(post("/recipe/new"))
               .andExpect(status().isOk())
               .andExpect(view().name("recipe/recipeform"))
               .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void getUpdateView() throws Exception {
        RecipeCommand cmd = new RecipeCommand();
        cmd.setId(LONG_ID);

        when (recipeService.findRecipeCommandById(anyLong())).thenReturn(cmd);

        mockMVC.perform(post("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void postNewRecipeForm() throws Exception {
        RecipeCommand cmd = new RecipeCommand();
        cmd.setId(LONG_ID);

        when(recipeService.saveRecipeCommand(any())).thenReturn(cmd);

        mockMVC.perform(post("/recipe/")
               .contentType(MediaType.APPLICATION_FORM_URLENCODED)
               .param("id", "")
               .param("description", "some string")
             )
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/recipe/"+LONG_ID+"/show"));
    }

}
