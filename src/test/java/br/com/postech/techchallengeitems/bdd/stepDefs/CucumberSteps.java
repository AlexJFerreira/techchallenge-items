package br.com.postech.techchallengeitems.bdd.stepDefs;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import br.com.postech.techchallengeitems.adapters.controller.rest.request.ItemRegistrationRequest;
import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import java.math.BigDecimal;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberSteps {
  private Response response;
  private ItemRegistrationRequest request;

  @LocalServerPort
  private Integer port;

  private static final String HOST = "http://localhost:";
  private static final String BASE_URL_PREFIX = "/techchallenge/items";

  @Given("client wants to register a new item to sell")
  public void clientWantsToRegisterANewItemToSell() {
    request = new ItemRegistrationRequest();
    request.setDescription("Coca Cola");
    request.setName("refrigerante");
    request.setPrice(new BigDecimal("10.00"));
    request.setType(ItemType.BEVERAGE);
  }

  @When("client calls item registration endpoint")
  public void clientCallsItemRegistrationEndpoint() {
    response = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post(HOST + port + BASE_URL_PREFIX);
  }

  @Then("client receives item registration data")
  public void clientReceivesItemRegistrationData() {
    response.then()
        .statusCode(HttpStatus.CREATED.value())
        .body(matchesJsonSchemaInClasspath("./schemas/itemRegistrationResponse.json"));;
  }
}
