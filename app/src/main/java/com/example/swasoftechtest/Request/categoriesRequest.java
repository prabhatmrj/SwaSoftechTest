package com.example.swasoftechtest.Request;

public class categoriesRequest {

    String id,category;

    public categoriesRequest(String id, String category) {
        this.id = id;
        this.category = category;
    }

    public categoriesRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
