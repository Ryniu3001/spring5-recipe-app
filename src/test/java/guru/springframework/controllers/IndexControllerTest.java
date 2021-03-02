package guru.springframework.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

class IndexControllerTest {

	@Mock
	private RecipeService recipeService;
	
	@Mock
	Model model;
	
	private IndexController controller;
	
	@BeforeEach
	private void setUp() {
		MockitoAnnotations.openMocks(this);
		controller = new IndexController(recipeService);
	}
	
	@Test
	void testGetIndexPage() {
		//given
		Set<Recipe> recipes = new HashSet<Recipe>();
		recipes.add(new Recipe());
		recipes.add(new Recipe());
		when(recipeService.getRecipes()).thenReturn(recipes);
		
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		//when
		String val = controller.getIndexPage(model);
		
		//then
		assertEquals(val, "index");
		verify(model, times(1)).addAttribute(ArgumentMatchers.eq("recipes"), argumentCaptor.capture());
		Set<Recipe> setInController = argumentCaptor.getValue();
		assertEquals(recipes.size(), setInController.size());
	}

}
