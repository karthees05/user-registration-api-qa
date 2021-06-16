package com.tradeledger.cards.api.qa.steps;

import com.tradeledger.cards.api.qa.contexts.AutomationContext;
import com.tradeledger.cards.api.qa.requests.Requests;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class StepDefinitions {
    AutomationContext automationContext;
    Response response;
    RequestSpecification httpRequest = RestAssured.given();
    Requests Requests = new Requests();

    public StepDefinitions(AutomationContext context) {
        this.automationContext = context;
        response = automationContext.getResponse();
    }

    @Given("^the API end point (.*) is available to use")
    public void checkApiEndPoint(final String userEndPoint) {
        Requests.selectEndPoint(userEndPoint);
    }

    @When("^user POST a request with valid data (.*) , (.*) , (.*)$")
    public void postRequest(final Object name, final Object address, final Object email) {
        JSONObject inputData = getJsonObject(name, address, email);
        response = Requests.postRequest(inputData, "/");
    }

    @Then("^user gets success response (.*) code$")
    public void checkResponseCode(final int expectedStatusCode) {
        assertThat(response.statusCode(), is(equalTo(expectedStatusCode)));
    }

    @And("^API (.*) contains the correct schema structure$")
    public void validateSchema(final String operationType) {
        if (operationType.equalsIgnoreCase("request")) {
            httpRequest.then().body(matchesJsonSchemaInClasspath("datafiles/users_request_schema.json"));
        } else {
            response.then().body(matchesJsonSchemaInClasspath("datafiles/users_response_schema.json"));
        }
    }

    @And("^the response contains card type (.*)$")
    public void validateCardType(final String card) {
        assertThat((response.jsonPath().get("eligibleCards")).toString(), containsString(card));
    }

    @When("^user POST a request without a valid field (.*) , (.*) , (.*)$")
    public void postRequestWithoutValid(final Object name, final Object address, final Object email) {
        JSONObject inputData = getJsonObject(name, address, email);

        for (Map.Entry<String, Object> entry : inputData.entrySet()) {
            if (entry.getValue().equals("")) {
                inputData.remove(entry.getKey());
                break;
            }
        }
        response = Requests.postRequest(inputData, "/");
    }

    private JSONObject getJsonObject(Object name, Object address, Object email) {
        JSONObject inputData = new JSONObject();
        inputData.put("name", name.toString());
        inputData.put("address", address.toString());
        inputData.put("email", email.toString());
        return inputData;
    }

    @When("^user POST a request without a key (.*) from the valid keys (.*) , (.*) , (.*)$")
    public void postRequestInvalid(final String keyToRemove, final Object name, final Object address, final Object email) {

        JSONObject inputData = getJsonObject(name, address, email);

        for (Map.Entry<String, Object> entry : inputData.entrySet()) {
            if (entry.getKey().equals(keyToRemove)) {
                inputData.remove(entry.getKey());
                break;
            }
        }
        response = Requests.postRequest(inputData, "/");
    }
    @When("^user POST a request with valid data (.*) , (.*) , (.*) and invalid header (.*) , (.*)$")
    public void postRequestInvalidHeaders(final Object name, final Object address, final Object email, final String headerkey, final String headervalue) {
        JSONObject inputData = getJsonObject(name, address, email);
        response = Requests.postRequestWithInvalidHeaders(inputData, "/", headerkey, headervalue);
    }
}
