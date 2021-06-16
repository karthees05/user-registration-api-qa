package com.tradeledger.cards.api.qa.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty:target/cucumber-pretty.txt",
                "html:target/cucumber-html-report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber-result.xml",
                "usage:usage/cucumber-usage.json"},
        features = "src/test/resources/features",
        glue = {"com.tradeledger.cards.api.qa.steps"},
        tags = "@api_smoke_test_invalid_headers")

public class RunCuke {
}
