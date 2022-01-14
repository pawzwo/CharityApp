package com.charity.charityapp.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> showAllCategories() {
        return categoryRepository.showAllCategories();
    }

    @Override
    public List<String> getAllCategoriesNames() {
        return showAllCategories().stream().map(Category::getName).collect(Collectors.toList());
    }
}
