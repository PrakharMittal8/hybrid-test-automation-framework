package com.automation.api;

import com.automation.utils.ApiUtils;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class ProductAPI {

    public static Response getAllProducts(String token) {

        JSONObject requestBody = new JSONObject();

        requestBody.put("productName", "");
        requestBody.put("minPrice", JSONObject.NULL);
        requestBody.put("maxPrice", JSONObject.NULL);
        requestBody.put("productCategory", new JSONArray());
        requestBody.put("productSubCategory", new JSONArray());
        requestBody.put("productFor", new JSONArray());

        return ApiUtils.getRequestSpec(token)
                .body(requestBody.toString())
                .when()
                .post("/api/ecom/product/get-all-products");
    }

    public static Response addProductToCart(
            String token,
            String userId,
            String productId,
            String productName
    ) {

        JSONObject productObject = new JSONObject();
        productObject.put("_id", productId);
        productObject.put("productName", productName);

        JSONObject requestBody = new JSONObject();
        requestBody.put("_id", userId);
        requestBody.put("product", productObject);

        return ApiUtils.getRequestSpec(token)
                .body(requestBody.toString())
                .when()
                .post("/api/ecom/user/add-to-cart");
    }
}