package guru.springframework5.sfg_recipe_project.controllers;

import guru.springframework5.sfg_recipe_project.commands.RecipeCommand;
import guru.springframework5.sfg_recipe_project.services.ImageService;
import guru.springframework5.sfg_recipe_project.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class ImageControllerTest {
    private ImageController imageController;

    @Mock
    private ImageService imageService;
    @Mock
    private RecipeService recipeService;

    MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        imageController = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void openChangeImageForm() throws Exception {
        //given
        RecipeCommand cmd = new RecipeCommand();
        cmd.setId(1L);
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(cmd);

        //when & then
        mockMvc.perform(get("/recipe/1/changeimage"))
               .andExpect(status().isOk())
               .andExpect(view().name("recipe/image/changeimageform"));
        verify(recipeService, times(1)).findRecipeCommandById(1L);
    }

    @Test
    public void uploadNewImage() throws Exception{
        MockMultipartFile mockMultipartFile = new MockMultipartFile("imgFile",
                                                             "testing.txt",
                                                                "text/plain",
                                                                          "Spring Framework Guru".getBytes());
        mockMvc.perform(multipart("/recipe/1/uploadnewimage").file(mockMultipartFile))
               .andExpect(status().is3xxRedirection())
               .andExpect(header().string("Location", "/recipe/1/show"));
        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    public void renderImageFromDB() throws Exception {
        //given
        RecipeCommand cmd = new RecipeCommand();
        cmd.setId(1L);

        String s = "SpringFrameworkGuru";
        Byte[] byteObjs = new Byte[s.getBytes().length];
        int i = 0;
        for (byte b :s.getBytes()){
            byteObjs[i++] = b;
        }
        cmd.setImage(byteObjs);
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(cmd);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/renderImage"))
                                                  .andExpect(status().isOk())
                                                  .andReturn().getResponse();
        byte[] bytes = response.getContentAsByteArray();
        assertEquals(s.getBytes().length, bytes.length);
    }
}
