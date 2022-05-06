package provider;

import config.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;
import responsDto.provider.SettlementProviderResponse;


import static io.restassured.RestAssured.given;

public class SettlementProviderTest {

    private static final String URL_ENDPOINT = "/provider/settlement";




    @Test(testName = "Get event by mappedId")
    public void get_EventByMappedId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SettlementProviderResponse response = given()
                .when()
                .get(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SettlementProviderResponse.class);

        Assert.assertNotNull(response);

    }


}
