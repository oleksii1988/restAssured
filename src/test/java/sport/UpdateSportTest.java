package sport;

import com.atlas.common.enums.Language;
import config.Specifications;
import jdbc.SportDAO;
import modelDB.SportModel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestDto.sport.UpdateSportRequest;
import responsDto.sport.MapSportResponse;


import java.sql.SQLException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateSportTest {


    private static final Integer SPORT_ID = 9999;
    private static final String SPORT_NAME = "Football Test";
    private static final String UPDATE_SPORT_NAME = "Football Super Test";
    private static final String URL_ENDPOINT = "/sport/9999:update";

    SportModel sportFootball = new SportModel(SPORT_ID, SPORT_NAME);
    SportDAO sportDAO = new SportDAO();

    public UpdateSportTest() throws SQLException {
    }

    @BeforeClass
    void createSportCategoryTournamentForTest() {
        sportDAO.createNewSport(sportFootball);

    }

    @AfterClass
    void deleteAll(){
        sportDAO.delete(sportFootball.getId());

    }


    @Test(testName = "Update sport name and translations ")
    public void update_SportName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateSportRequest updateSport = new UpdateSportRequest.SportRequestImpl()
                .setName(UPDATE_SPORT_NAME)
                .setTranslations(Map.of(Language.UKR,"Футбол Тест",Language.ENG, "Football Test",Language.POR, "Teste de futebol")).build();
        MapSportResponse response = given()
                .body(updateSport)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapSportResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getName(), UPDATE_SPORT_NAME);
        Assert.assertEquals(response.getMappedId(), SPORT_ID);
        Assert.assertNotEquals(response.getName(),sportFootball.getName());
        Assert.assertEquals(response.getTranslations().get(Language.UKR),"Футбол Тест");
        Assert.assertEquals(response.getTranslations().get(Language.ENG),"Football Test");
        Assert.assertEquals(response.getTranslations().get(Language.POR),"Teste de futebol");

    }


    @Test(testName = "Update sport name and check in database")
    public void update_SportNameAndCheckInDB_ExpectedNotEqualsObjectFromDbAndTestObject() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateSportRequest updateSport = new UpdateSportRequest.SportRequestImpl()
                .setName(UPDATE_SPORT_NAME)
                .build();
        MapSportResponse response = given()
                .body(updateSport)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapSportResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getName(), UPDATE_SPORT_NAME);
        Assert.assertEquals(response.getMappedId(), SPORT_ID);
        Assert.assertNotEquals(sportDAO.getSportById(response.getMappedId()),sportFootball);


    }

}
