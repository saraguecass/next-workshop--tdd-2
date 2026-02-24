package com.example.ordersapi.steps;

import com.example.ordersapi.adapters.inbound.rest.dto.PlaceResponseDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaceSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private Map<String, Object> placeRequest;
    private Long placeId;
    private ResponseEntity<?> response;

    // ---------- GIVEN ----------

    @Given("a place with name {string}, city {string} and postal code {int}")
    public void givenPlace(String name, String city, int codPostal) {
        placeRequest = new HashMap<>();
        placeRequest.put("name", name);
        placeRequest.put("city", city);
        placeRequest.put("codPostal", codPostal);
    }

    @Given("an existing place with name {string}, city {string} and postal code {int}")
    public void givenExistingPlace(String name, String city, int codPostal) {

        Map<String, Object> request = new HashMap<>();
        request.put("name", name);
        request.put("city", city);
        request.put("codPostal", codPostal);

        ResponseEntity<PlaceResponseDto> createResponse =
                restTemplate.postForEntity("/places", request, PlaceResponseDto.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();

        this.placeId = createResponse.getBody().id();
    }

    // ---------- WHEN ----------

    @When("the place is saved")
    public void whenPlaceIsSaved() {
        ResponseEntity<PlaceResponseDto> createResponse =
                restTemplate.postForEntity("/places", placeRequest, PlaceResponseDto.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();

        this.placeId = createResponse.getBody().id();
    }

    @When("the place is deleted")
    public void whenPlaceIsDeleted() {
        restTemplate.delete("/places/{id}", placeId);
    }

    @When("I request a place with id {long}")
    public void whenRequestPlaceById(Long id) {
        response = restTemplate.getForEntity("/places/{id}", String.class, id);
    }

    @When("I request all places")
    public void whenRequestAllPlaces() {
        response = restTemplate.getForEntity("/places", PlaceResponseDto[].class);
    }

    @When("I delete a place with id {long}")
    public void whenDeletePlaceById(Long id) {
        restTemplate.delete("/places/{id}", id);
        response = restTemplate.getForEntity("/places/{id}", String.class, id);
    }

    @When("I create a place with invalid payload")
    public void whenCreateInvalidPlace() {
        response = restTemplate.postForEntity("/places", "invalid-json", String.class);
    }

    // ---------- THEN ----------

    @Then("the place is persisted successfully")
    public void thenPlacePersistedSuccessfully() {
        ResponseEntity<PlaceResponseDto> getResponse =
                restTemplate.getForEntity("/places/{id}", PlaceResponseDto.class, placeId);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().id()).isEqualTo(placeId);
    }

    @Then("the place no longer exists")
    public void thenPlaceNoLongerExists() {
        ResponseEntity<String> getResponse =
                restTemplate.getForEntity("/places/{id}", String.class, placeId);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Then("the response status should be {int}")
    public void thenResponseStatusShouldBe(Integer status) {
        assertThat(response.getStatusCode().value()).isEqualTo(status);
    }
}
