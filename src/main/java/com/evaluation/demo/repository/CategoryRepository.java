package com.evaluation.demo.repository;

import com.evaluation.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>
{
    Category getByCategoryName(String categoryName);
}
