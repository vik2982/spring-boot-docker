package com.va.spring.rest.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.va.spring.rest.model.FootballTeam;

public class StepDefinition {

  String getUri;
  RestTemplate restTemplate = new RestTemplate();
  String footballTeamName = "spurs";
  FootballTeam footballTeam;

  @Given("^I Set GET service api endpoint$")
  public void setGetEndpoint() {
    getUri = "http://localhost:" + System.getProperty("test.server.port") + "/" + footballTeamName;

  }

  @When("^I Send a GET HTTP request$")
  public void sendGetRequest() {
    ResponseEntity<FootballTeam> response = restTemplate.getForEntity(getUri, FootballTeam.class);
    footballTeam = response.getBody();
  }

  @Then("^I receive valid Response$")
  public void receiveValidResponse() {
    assertEquals(footballTeamName, footballTeam.getName());

  }


}
