package com.evaluation.demo.service;

import com.evaluation.demo.dto.CategoryDto;
import com.evaluation.demo.entity.Category;
import com.evaluation.demo.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    private List<Category> categoryRecords;
    private List<CategoryDto> categoryDtos;
    @InjectMocks
    private CategoryServiceImplementation mockCategoryServiceImplementation;

    @Mock
    private CategoryRepository mockCategoryRepository;

    @Before
    public void setup() {
        // mock database category records
        categoryRecords = new ArrayList<>();
        categoryDtos = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setCategoryID(i);
            category.setCategoryName(i + "Category Name");

            categoryRecords.add(category);

            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryID(i);
            categoryDto.setCategoryName(i + "Category Name");

            categoryDtos.add(categoryDto);
        }
    }

    @Test
    public void getCategoryByName() {
        Mockito.when(mockCategoryRepository.getByCategoryName(categoryRecords.get(1).getCategoryName())).thenReturn(categoryRecords.get(1));

        Category target = mockCategoryServiceImplementation.getCategoryByName(categoryRecords.get(1).getCategoryName());

        Assert.assertEquals(target, categoryRecords.get(1));
    }

    @Test
    public void getAllCategories() {
        Mockito.when(mockCategoryRepository.findAll()).thenReturn(categoryRecords);

        List<CategoryDto> categoryDtoList = mockCategoryServiceImplementation.getAllCategories();

        Assert.assertEquals(categoryDtoList.size(), categoryDtos.size());
    }
}
