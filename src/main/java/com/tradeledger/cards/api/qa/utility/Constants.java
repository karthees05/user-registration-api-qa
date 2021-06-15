package com.tradeledger.cards.api.qa.utility;

public class Constants {

    public static final String REAL_SERVICE_URI = System.getProperties().getProperty("service.uri");
    public static final String LOCAL_SERVICE_URI = "http://localhost:8080";
    public static final String LOCAL_OR_REAL_SERVICE_URI = System.getProperties().getProperty("service.profile");
    public static final String ELIGIBILITY_CHECK_END_POINT = "/eligibility/check";

}


