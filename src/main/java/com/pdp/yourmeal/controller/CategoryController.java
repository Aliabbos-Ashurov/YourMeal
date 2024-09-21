package com.pdp.yourmeal.controller;

import com.pdp.yourmeal.dto.CategoryDTO;
import com.pdp.yourmeal.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  12:07
 **/
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Get all categories", description = "Retrieves a list of all categories.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of categories")
    @GetMapping("/get")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }


    @Operation(summary = "Get a category by ID", description = "Retrieves a category by its unique ID.")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(
            @Parameter(description = "ID of the category to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @Operation(summary = "Get a category by title", description = "Retrieves a category by its title.")
    @GetMapping("/get/by-title/{title}")
    public ResponseEntity<CategoryDTO> getCategoryByTitle(
            @Parameter(description = "Title of the category to retrieve", required = true)
            @PathVariable String title) {
        return ResponseEntity.ok(categoryService.findByTitle(title));
    }
}
