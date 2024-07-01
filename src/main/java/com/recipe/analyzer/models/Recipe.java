package com.recipe.analyzer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Recipe {
    @JsonProperty("postcode")
    private String postcode;

    @JsonProperty("recipe")
    private String recipe;

    @JsonProperty("delivery")
    private String delivery;

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

}
