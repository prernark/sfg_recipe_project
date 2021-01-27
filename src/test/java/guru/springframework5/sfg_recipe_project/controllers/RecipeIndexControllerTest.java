package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.domain.Recipe;
import guru.springframework5.sfg_recipe_project.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RecipeIndexControllerTest {
    RecipeIndexController recipeIndexController;
    @Mock
    RecipeServiceImpl recipeService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeIndexController = new RecipeIndexController(recipeService);
    }

    @Test
    void listOfRecipes() {
        assertEquals(recipeIndexController.listOfRecipes(model),"recipe_index");
        //Since we called above method the 2 mocks were invoked only once hence 1 interaction each
        verify(recipeService, times(1)).getRecipes();
        //Matcher required so using eq matcher
        verify(model, times(1)).addAttribute(eq("recipes"),anySet());
    }

    @Test
    void verifyMocks(){
        //Since we are not calling the listOfRecipes method on Controller the 2 mocks are called 0 times. NO interaction
        verify(recipeService, times(0)).getRecipes();
        verify(model, times(0)).addAttribute(eq("recipes"),anySet());
    }

    @Test
    void testMockitoArgumentCaptor(){

        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = recipeIndexController.listOfRecipes(model);

        //then
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        //Before we can use getValue() below, we MUST verify using argumentCaptor.capture()
        Set<Recipe> recipeSet = argumentCaptor.getValue();
        assertEquals(recipeSet.size(), 2);
    }

    @Test
    void testMockMVC() throws Exception {
        //MockMvcBuilders can create either a 'webAppContextSetup' mockmvc which brings in a SpringContext which means
        //our test will no longer be a Unit Test. Or it can create 'standalone' mockmvc which is light and fast
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeIndexController).build();

        //JUnit4 uses get("/") instead of MockMvcRequestBuilders.post("/")
        mockMvc.perform(MockMvcRequestBuilders.post("/")) //or post "/recipe","/index". MUST HAVE atleast '/'
                .andExpect(status().isOk())
                .andExpect(view().name("recipe_index"));
    }

}