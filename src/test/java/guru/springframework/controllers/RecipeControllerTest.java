package guru.springframework.controllers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {
	@Mock
	RecipeService recipeService;

	@InjectMocks
	RecipeController controller;
	
	MockMvc mockMvc;
	
    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

	@Test
	public void testGetRecipe() throws Exception {

		Recipe recipe = new Recipe(); 
		recipe.setId(1L);

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		when(recipeService.findById(anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/1/show")).andExpect(status().isOk()).andExpect(view().name("recipe/show"));
	}
	
	 @Test
	    public void testGetNewRecipeForm() throws Exception {
	        RecipeCommand command = new RecipeCommand();

	        mockMvc.perform(get("/recipe/new"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("recipe/recipeform"))
	                .andExpect(model().attributeExists("recipe"));
	    }

	    @Test
	    public void testPostNewRecipeForm() throws Exception {
	        RecipeCommand command = new RecipeCommand();
	        command.setId(2L);

	        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

	        mockMvc.perform(post("/recipe")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//	                .param("id", "")
//	                .param("description", "some string")
	        )
	                .andExpect(status().is3xxRedirection())
	                .andExpect(view().name("redirect:/recipe/2/show"));
	    }

	    @Test
	    public void testGetUpdateView() throws Exception {
	        RecipeCommand command = new RecipeCommand();
	        command.setId(2L);

	        when(recipeService.findCommandById(anyLong())).thenReturn(command);

	        mockMvc.perform(get("/recipe/2/update"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("recipe/recipeform"))
	                .andExpect(model().attributeExists("recipe"));
	    }
}
