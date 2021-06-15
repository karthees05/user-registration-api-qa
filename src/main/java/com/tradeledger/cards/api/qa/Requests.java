package com.tradeledger.cards.api.qa;


import com.tradeledger.cards.api.qa.Utilities.Constants;
import com.tradeledger.cards.api.qa.Utilities.CustomException;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;


public class Requests {
    RequestSpecification httpRequest = RestAssured.given();

    public void selectEndPoint(final String userEndPoint) {
        if ("eligibility-check".equalsIgnoreCase(userEndPoint)) {
            if (Constants.LOCAL_OR_REAL_SERVICE_URI.equalsIgnoreCase("dev")) {
                httpRequest.baseUri(Constants.REAL_SERVICE_URI + Constants.ELIGIBILITY_CHECK_END_POINT);
            } else if (Constants.LOCAL_OR_REAL_SERVICE_URI.equalsIgnoreCase("dev-int")) {
                httpRequest.baseUri(Constants.REAL_SERVICE_URI + Constants.ELIGIBILITY_CHECK_END_POINT);
            } else if (Constants.LOCAL_OR_REAL_SERVICE_URI.equalsIgnoreCase("sit")) {
                httpRequest.baseUri(Constants.REAL_SERVICE_URI + Constants.ELIGIBILITY_CHECK_END_POINT);
            } else if (Constants.LOCAL_OR_REAL_SERVICE_URI.equalsIgnoreCase("bench")) {
                httpRequest.baseUri(Constants.REAL_SERVICE_URI + Constants.ELIGIBILITY_CHECK_END_POINT);
            } else if (Constants.LOCAL_OR_REAL_SERVICE_URI.equalsIgnoreCase("production")) {
                httpRequest.baseUri(Constants.REAL_SERVICE_URI + Constants.ELIGIBILITY_CHECK_END_POINT);
            } else if (Constants.LOCAL_OR_REAL_SERVICE_URI.equalsIgnoreCase("local")) {
                httpRequest.baseUri(Constants.LOCAL_SERVICE_URI + Constants.ELIGIBILITY_CHECK_END_POINT);
            }
        } else {
            throw new CustomException("Invalid URL");
        }
    }

    public Response postRequest(final JSONObject payload, final String path) {
        httpRequest.header("Accept", "application/json");
        httpRequest.header("Content-Type", "application/json");
        return httpRequest.body(payload.toJSONString()).request(Method.POST, path);
    }
}
