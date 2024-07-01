package com.recipe.analyzer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.recipe.analyzer.models.Recipe;
import com.recipe.analyzer.services.RecipeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/unique-recipe-count")
    public ResponseEntity<?> getUniqueRecipeCount(@RequestBody List<Recipe> recipes) {
        int count = recipeService.countUniqueRecipes(recipes);
        Map<String, Integer> response = new HashMap<>();
        response.put("unique_recipe_count", count);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/count-per-recipe")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getCountPerRecipe(@RequestBody List<Recipe> recipes) {
        List<Map<String, Object>> response = recipeService.countRecipesOccurrences(recipes);
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("count_per_recipe", response);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/count-per-postcode")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getcountDeliveriesPerPostcode(
            @RequestBody List<Recipe> recipes) {
        List<Map<String, Object>> response = recipeService.countDeliveriesPerPostcode(recipes);
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("count_per_postcode", response);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/busiest-postcode")
    public ResponseEntity<Map<String, Object>> getBusiestPostcode(@RequestBody List<Recipe> recipes) {
        Map<String, Object> busiestPostcode = recipeService.findBusiestPostcode(recipes);
        Map<String, Object> result = new HashMap<>();
        result.put("busiest_postcode", busiestPostcode);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/search-recipes")
    public ResponseEntity<?> searchRecipes(@RequestBody List<Recipe> recipes, @RequestParam List<String> keywords) {
        List<String> searchResult = recipeService.findRecipesByKeywords(recipes, keywords);
        Map<String, Object> result = new HashMap<>();
        result.put("match_by_name", searchResult);
        return ResponseEntity.ok(result);
    }
}
