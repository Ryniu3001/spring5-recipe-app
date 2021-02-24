package guru.springframework.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {

	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage() {

		Optional<Category> categoryOpt = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> unitOfMeasureOpt = unitOfMeasureRepository.findByDescription("Teaspoon");

		System.out.println("Cat Id is: " + categoryOpt.get().getId());
		System.out.println("Unit of measure id: " + unitOfMeasureOpt.get().getId());

		return "index";
	}
}
