package com.lucsalmd.catalogapi.controller;

import com.lucsalmd.catalogapi.exception.dto.BusinessError;
import com.lucsalmd.catalogapi.model.dto.CategoryRequestDTO;
import com.lucsalmd.catalogapi.model.entity.Category;
import com.lucsalmd.catalogapi.service.CategoryService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Categories Operations")
public class CategoryController {

    @Autowired
    private CategoryService service;


    @PostMapping("/{ownerId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Category.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "404",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "500",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))})})
    public Category createCategory(@PathVariable String ownerId, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return service.createCategory(ownerId, categoryRequestDTO);
    }

    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Category.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "404",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "500",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))})})
    public Category updateCategory(@PathVariable String categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return service.updateCategory(categoryId, categoryRequestDTO);
    }

    @DeleteMapping("/{categoryId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Category.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "404",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "500",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))})})
    public void deleteCategory(@PathVariable String categoryId) {
        service.deleteCategory(categoryId);
    }

    @GetMapping("/{ownerId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Category.class)))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "404",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "500",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))})})
    public List<Category> getCategories(@PathVariable String ownerId) {
        return service.getCategoriesByOwner(ownerId);
    }
}
