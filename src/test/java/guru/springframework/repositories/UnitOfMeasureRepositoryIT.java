package guru.springframework.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import guru.springframework.domain.UnitOfMeasure;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@BeforeEach
	public void setUp() {
		
	}
	
	@Test
	void testFindByDescription() {
		Optional<UnitOfMeasure> uomOpt = unitOfMeasureRepository.findByDescription("Teaspoon");
		assertEquals("Teaspoon", uomOpt.get().getDescription());
	}
	
	@Test
	void testFindByDescriptionCup() {
		Optional<UnitOfMeasure> uomOpt = unitOfMeasureRepository.findByDescription("Cup");
		assertEquals("Cup", uomOpt.get().getDescription());
	}

}
