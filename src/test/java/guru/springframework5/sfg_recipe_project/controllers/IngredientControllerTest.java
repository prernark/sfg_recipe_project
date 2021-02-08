package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.services.IngredientService;
import guru.springframework5.sfg_recipe_project.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class IngredientControllerTest {
    IngredientController ingredientController;

    @Mock
    RecipeService recipeService;
    @Mock
    IngredientService ingredientService;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        ingredientController = new IngredientController(recipeService, ingredientService);

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test //Viewing ingredients
    public void listIngredients() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(get("/recipe/1/ingredients"))
               .andExpect(view().name("recipe/ingredient/list"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("recipe"));

        //then
        verify(recipeService, times(1)).findRecipeCommandById(anyLong());
    }

    @Test
    public void showRecipeIngredient() throws Exception {
        //given
        IngredientCommand cmd = new IngredientCommand();
        cmd.setId(1L);
        //when
        when(ingredientService.findbyRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(cmd);

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
               .andExpect(status().isOk())
               .andExpect(view().name("recipe/ingredient/show"))
               .andExpect(model().attributeExists("ingredient"));

        verify(ingredientService,times(1)).findbyRecipeIdAndIngredientId(anyLong(), anyLong());
    }

}
