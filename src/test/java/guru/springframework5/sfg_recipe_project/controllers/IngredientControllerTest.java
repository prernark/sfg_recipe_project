package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.commands.IngredientCommand;
import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.services.IngredientService;
import guru.springframework5.sfg_recipe_project.services.RecipeService;
import guru.springframework5.sfg_recipe_project.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    IngredientService ingredientService;
    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController ingredientController;
    MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
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

    @Test
    public void openEditIngredientForm() throws Exception{
        IngredientCommand cmd = new IngredientCommand();
        cmd.setId(1L);
        when(ingredientService.findbyRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(cmd);
//        Set<UnitOfMeasureCommand> uomSet = new HashSet<>();
//        when(unitOfMeasureService.findAll()).thenReturn(uomSet);
        when(unitOfMeasureService.findAll()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/1/ingredient/8/update"))
               .andExpect(status().isOk())
               .andExpect(view().name("recipe/ingredient/editIngredientForm"))
               .andExpect(model().attributeExists("ingredient"))
               .andExpect(model().attributeExists("uomList"));
        verify(ingredientService, times(1)).findbyRecipeIdAndIngredientId(anyLong(), anyLong());
        verify(unitOfMeasureService, times(1)).findAll();
    }

    @Test
    public void openNewIngredientForm() throws Exception{
        when(unitOfMeasureService.findAll()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/1/ingredient/new"))
               .andExpect(status().isOk())
               .andExpect(view().name("recipe/ingredient/editIngredientForm"))
               .andExpect(model().attributeExists("uomList"))
               .andExpect(model().attributeExists("ingredient"));
        verify(unitOfMeasureService, times(1)).findAll();
    }

    @Test
    public void saveOrUpdateIngredient() throws Exception{
        IngredientCommand cmd = new IngredientCommand();
        cmd.setId(8L);
        cmd.setRecipeId(1L);
        when(ingredientService.saveIngredientCommand(any())).thenReturn(cmd);

        mockMvc.perform(post("/recipe/1/ingredient")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("IngredientCommand", ""))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/recipe/1/ingredient/8/show"));

        verify(ingredientService, times(1)).saveIngredientCommand(any());
    }
}
