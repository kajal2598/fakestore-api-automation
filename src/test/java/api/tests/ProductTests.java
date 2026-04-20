package api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import api.endpoints.Routes;
import api.models.Product;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import java.util.List;

public class ProductTests {

    @Test(priority = 1)
    public void testGetAllProducts() {
        Response res = given().when().get(Routes.products);
        
        res.then().statusCode(200);
        
        List<String> names = res.jsonPath().getList("title");
        System.out.println("--- PRODUCT CATALOG ---");
        names.stream().limit(10).forEach(name -> System.out.println("Product: " + name));
        
        assert names.size() >= 10;
    }

    @DataProvider(name = "ids")
    public Object[][] getIds() {
        return new Object[][] { {1}, {2}, {5} };
    }

    @Test(priority = 2, dataProvider = "ids")
    public void testGetProductById(int id) {
        given()
            .pathParam("id", id)
        .when()
            .get(Routes.single_product)
        .then()
            .statusCode(200)
            .body("id", equalTo(id))
            .body("category", notNullValue())
            .body("price", notNullValue())
            .assertThat()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("product_schema.json"));
    }

    @Test(priority = 3)
    public void testPostProduct() {
        Product payload = new Product("Wireless Mouse", 799.0, "High quality", "electronics", "http://img.com");

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post(Routes.products)
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("title", equalTo("Wireless Mouse"));
    }

    @Test(priority = 4)
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

    @Test(priority = 5)
    public void testDeleteProduct() {
        given()
            .pathParam("id", 1)
        .when()
            .delete(Routes.single_product)
        .then()
            .statusCode(200);
    }
}