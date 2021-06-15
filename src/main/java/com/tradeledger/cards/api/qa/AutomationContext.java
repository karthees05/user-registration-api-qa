package com.tradeledger.cards.api.qa;

import io.restassured.response.Response;

public class AutomationContext {

    private Response response;

    public AutomationContext() {
    }

    public Response getResponse() {
        return response;
    }
}