package getRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetDataType {
    private final static Logger LOGGER = LoggerFactory.getLogger(GetDataType.class);

    private static final String URL = "https://mapping-service-feed-test.k8s-hz.atlas-iac.com/bo/mapping/";

    @Test
    @Ignore
    public void getMarketTypeById() {
        Response resp = RestAssured.get(URL + "market-type/346");
        String respons = resp.body().asString();
        int code = resp.getStatusCode();
        Assert.assertEquals(code, 200);
        LOGGER.info("Status code is {}", code);

    }

    @Test
    @Ignore
    public void validateMarketType() {

        String  marketData = given().
                when()
               .contentType(ContentType.JSON)
                .get(URL+"event/2003827")
                .then().log().all()
                .extract().body().jsonPath().getString("mappedId");
           Assert.assertEquals(marketData, "2003827");
    }




}
