package sport;

import com.atlas.common.enums.Language;
import config.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;
import responsDto.sport.MapSportResponse;


import static io.restassured.RestAssured.given;

public class GetSportTest {

    private static final Integer SPORT_ID = 1;
    private static final String SPORT_NAME = "Football";
    private static final String URL_ENDPOINT = "/sport/";
    private static final Integer UNKNOWN_SPORT_ID = 99;


    @Test(testName = "Get sport by id and checking the main translations")
    public void get_SportById() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapSportResponse response = given()
                .when()
                .get(URL_ENDPOINT+SPORT_ID)
                .then().log().all()
                .extract().body().as(MapSportResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getName(), SPORT_NAME);
        Assert.assertEquals(response.getMappedId(), SPORT_ID);
        /*Assert.assertTrue(response.getTranslations().entrySet()
                .stream().anyMatch(data-> Objects.equals(data.getValue(),"كرة القدم")));*/
        Assert.assertEquals(response.getTranslations().get(Language.UKR), "Футбол");
        Assert.assertEquals(response.getTranslations().get(Language.RUS), "Футбол");
        Assert.assertEquals(response.getTranslations().get(Language.FAS), "فوتبال");
        Assert.assertEquals(response.getTranslations().get(Language.DEU), "Fußball");
        Assert.assertEquals(response.getTranslations().get(Language.TUR), "Futbol");
        Assert.assertEquals(response.getTranslations().get(Language.HIN), "फ़ुटबॉल");
        Assert.assertEquals(response.getTranslations().get(Language.FRA), "Football");
        Assert.assertEquals(response.getTranslations().get(Language.POR), "Futebol");
        Assert.assertEquals(response.getTranslations().get(Language.SPA), "Fútbol");
        Assert.assertEquals(response.getTranslations().get(Language.HYE), "Ֆուտբոլ");

    }


    @Test(testName = "Get sport by non-exist sportID")
    public void get_SportByNonExistSportId_ExpectedCode404() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapSportResponse response = given()
                .when()
                .get(URL_ENDPOINT + UNKNOWN_SPORT_ID)
                .then().log().all()
                .extract().body().as(MapSportResponse.class);

        Assert.assertNull(response.getName());
        Assert.assertNull(response.getMappedId());



    }

}
