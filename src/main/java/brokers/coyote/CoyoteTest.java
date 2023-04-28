package brokers.coyote;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CoyoteTest {
    @Test
    public void getLatLng() {
        //String city, String state
        RestAssured.baseURI = "https://nominatim.openstreetmap.org/";
        RestAssured.basePath = "search";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParam("city", "Chicago")
                .queryParam("state", "IL")
                .queryParam("country", "USA")
                .queryParam("format", "json")
                .queryParam("addressdetails", 1)
                .queryParam("limit", 1)
                .when()
                .get()
                .then()
                .statusCode(200)
               // .log().all()
                .extract().response();

       // CoyotePojo parsedResponse = response.as(CoyotePojo.class);
        JsonPath jsonPath = response.jsonPath();
        List<String> lat = jsonPath.get("lat");
        List<String> lon = jsonPath.get("lon");
        System.out.println(lat +"\n"+lon);
    }
}
