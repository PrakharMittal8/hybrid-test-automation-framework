package com.automation.api;

import com.automation.utils.ApiUtils;
import io.restassured.response.Response;
import org.json.JSONObject;

public class LoginAPI {

    public static Response login(String email, String password) {

        JSONObject requestBody = new JSONObject();

        requestBody.put("userEmail", email);
        requestBody.put("userPassword", password);

        return ApiUtils.getRequestSpec()
                .body(requestBody.toString())
                .when()
                .post("/api/ecom/auth/login");
    }
}