package com.recipe.analyzer.services;

import org.springframework.stereotype.Service;

import com.recipe.analyzer.models.Recipe;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    public int countUniqueRecipes(List<Recipe> recipes) {
        return (int) recipes.stream()
                .map(Recipe::getRecipe)
                .distinct()
                .count();
    }

    public List<Map<String, Object>> countRecipesOccurrences(List<Recipe> recipes) {
        Map<String, Long> recipeCount = recipes.stream()
                .collect(Collectors.groupingBy(Recipe::getRecipe, Collectors.counting()));

        return recipeCount.entrySet().stream()
                .map(entry -> {
                    Map<String, Object> map = new java.util.HashMap<>();
                    map.put("recipe", entry.getKey());
                    map.put("count", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }

    public Map<String, Object> findBusiestPostcode(List<Recipe> recipes) {
        Map<String, Long> postcodeCount = recipes.stream()
                .collect(Collectors.groupingBy(Recipe::getPostcode, Collectors.counting()));

        Map.Entry<String, Long> busiest = postcodeCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new RuntimeException("No postcodes found"));

        Map<String, Object> result = new java.util.HashMap<>();
        result.put("postcode", busiest.getKey());
        result.put("delivery_count", busiest.getValue());
        return result;
    }

    public List<Map<String, Object>> countDeliveriesPerPostcode(List<Recipe> recipes) {
        Map<String, Long> postcodeCount = recipes.stream()
                .collect(Collectors.groupingBy(Recipe::getPostcode, Collectors.counting()));

        return postcodeCount.entrySet().stream()
                .map(entry -> {
                    Map<String, Object> map = new java.util.HashMap<>();
                    map.put("postcode", entry.getKey());
                    map.put("count", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<String> findRecipesByKeywords(List<Recipe> recipes, List<String> keywords) {
        return recipes.stream()
                .map(Recipe::getRecipe)
                .filter(recipeName -> keywords.stream()
                        .anyMatch(keyword -> recipeName.contains(keyword)))
                .sorted()
                .collect(Collectors.toList());
    }
}
