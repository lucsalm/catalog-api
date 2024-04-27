package com.lucsalmd.catalogapi.controller;

import com.lucsalmd.catalogapi.exception.dto.BusinessError;
import com.lucsalmd.catalogapi.model.dto.ProductRequestDTO;
import com.lucsalmd.catalogapi.model.entity.Category;
import com.lucsalmd.catalogapi.model.entity.Product;
import com.lucsalmd.catalogapi.service.ProductService;
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
import java.util.Set;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "Product Operations")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/{ownerId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "404",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "422",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "500",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))})})
    public Product createProduct(@PathVariable String ownerId, @RequestBody ProductRequestDTO productRequestDTO){
        return service.createProduct(ownerId,productRequestDTO);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "404",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "422",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "500",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))})})
    public Product updateProduct(@PathVariable String productId, @RequestBody ProductRequestDTO productRequestDTO){
        return service.updateProduct(productId,productRequestDTO);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "404",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "500",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))})})
    public void deleteProduct(@PathVariable String productId){
        service.deleteProduct(productId);
    }
    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema =@Schema(implementation = Product.class)))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "404",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))}),
            @ApiResponse(responseCode = "500",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessError.class))})})
    public List<Product> getProductsByOwner(@PathVariable String ownerId){
        return service.getProductsByOwner(ownerId);
    }

}
