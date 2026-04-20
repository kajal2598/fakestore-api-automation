package api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import api.base.BaseTest;
import api.endpoints.Routes;
import api.models.Product;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import java.util.List;

/**
 * ProductTests handles the functional validation of FakeStore API endpoints.
 * It extends BaseTest to inherit logging and reporting configurations.
 */
public class ProductTests extends BaseTest {

    @Test(priority = 1, description = "Verify that the product list contains at least 10 items.")
    public void testGetAllProducts() {
        // Perform GET request
        Response response = given().when().get(Routes.products);

        // Assert success status
        Assert.assertEquals(response.getStatusCode(), 200);

        // Extract list of titles using JSONPath to verify quantity
        List<String> titles = response.jsonPath().getList("title");
        Assert.assertTrue(titles.size() >= 10, "Inventory check failed: less than 10 products found.");
    }

    /**
     * DataProvider to supply multiple IDs for testing the single-product endpoint.
     * Demonstrates Data-Driven Testing (DDT).
     */
    @DataProvider(name = "productIds")
    public Object[][] productIds() {
        return new Object[][] { {1}, {2}, {5} };
    }

    @Test(priority = 2, dataProvider = "productIds", description = "Validate product details and JSON schema for multiple IDs.")
    public void testGetProductById(int id) {
        given()
            .pathParam("id", id) // Dynamic ID injection
        .when()
            .get(Routes.single_product)
        .then()
            .statusCode(200)
            .body("id", equalTo(id))
            .body("category", notNullValue())
            // Validate the response against the schema file in src/test/resources
            .assertThat()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("product_schema.json"));
    }

    @Test(priority = 3, description = "Verify creation of a new product using POJO serialization.")
    public void testAddNewProduct() {
        // Initialize the POJO with test data
        Product newProduct = new Product("Wireless Mouse", 799.0, "Tech specs", "electronics", "http://img.com");

        given()
            .contentType(ContentType.JSON)
            .body(newProduct) // Object is automatically converted to JSON
        .when()
            .post(Routes.products)
        .then()
            .statusCode(201)
            .body("title", equalTo("Wireless Mouse"));
    }

    @Test(priority = 4, description = "Verify that an existing product's price can be updated.")
    public void testUpdateProduct() {
        String updatePayload = "{\"price\": 899}";

        given()
            .pathParam("id", 1)
            .contentType(ContentType.JSON)
            .body(updatePayload)
        .when()
            .put(Routes.single_product)
        .then()
            .statusCode(200)
            .body("price", equalTo(899));
    }

    @Test(priority = 5, description = "Verify successful deletion of a product.")
    public void testDeleteProduct() {
        given()
            .pathParam("id", 1)
        .when()
            .delete(Routes.single_product)
        .then()
            .statusCode(200);
    }
}