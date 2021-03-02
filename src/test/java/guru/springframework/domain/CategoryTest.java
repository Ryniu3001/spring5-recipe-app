package guru.springframework.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {

	Category category;
	
	@BeforeEach
	public void setUp() {
		category = new Category();
	}
	
	@Test
	void testGetId() {
		Long id = 4l;
		category.setId(id);
		assertEquals(id, category.getId());
	}

	@Test
	void testGetDescription() {
		//fail("Not yet implemented");
	}

	@Test
	void testGetRecipes() {
		//fail("Not yet implemented");
	}

}
