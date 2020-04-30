package com.ekattorit.kickytailor.database;

import com.ekattorit.kickytailor.models.ProductModel;

import java.util.List;

public interface FirebaseDatabaseManagerInterface {

    public void getAllProducts(List<ProductModel> allProducts);

}
