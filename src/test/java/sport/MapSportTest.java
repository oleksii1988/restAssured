package sport;

import config.Specifications;
import jdbc.SportDAO;
import model.MappingModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import requestDto.category.MapRequest;
import responsDto.sport.MapSportResponse;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class MapSportTest {

    private static final String EXTERNAL_ID = "lsport:687888";
    private static final Integer SPORT_ID = 9;
    private static final String PROVIDER = "lsport";
    private static final Integer NON_EXISTENT_SPORT_ID = 99999;
    private static final String SPORT_NAME = "Horse Racing";

    private static final String URL_ENDPOINT = "/sport:map";

    SportDAO sportDAO = new SportDAO();

    public MapSportTest() throws SQLException {
    }


    // TODO: Checking in the database of a mapped category from several providers

    @Test(testName = "Mapping new sport with flag single is FALSE and checking create entity in db")
    public void map_NewSport_WithSingleFalse_EntityMustBeCreatedIndDB()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest(EXTERNAL_ID,SPORT_ID,PROVIDER,false);
        MappingModel mappingModel = new MappingModel(EXTERNAL_ID,SPORT_ID,PROVIDER,SPORT_NAME);
        MapSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapSportResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), SPORT_ID);
        Assert.assertEquals(response.getName(), SPORT_NAME);
        Assert.assertTrue(sportDAO.getMap(response.getMappedId()));
        Assert.assertEquals(mappingModel, sportDAO.getMapSport(response.getMappedId()));
        sportDAO.deleteMapping(SPORT_ID);


    }


    @Test(testName = "Mapping new sport with flag single is TRUE and checking create entity in db")
    public void map_NewSport_WithSingleTrue_EntityMustNotBeCreatedIndDB()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest(EXTERNAL_ID,SPORT_ID,PROVIDER,true);
        MappingModel mappingModel = new MappingModel(EXTERNAL_ID,SPORT_ID,PROVIDER,SPORT_NAME);
        MapSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapSportResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), SPORT_ID);
        Assert.assertEquals(response.getName(), SPORT_NAME);
        Assert.assertFalse(sportDAO.getMap(response.getMappedId()));
        sportDAO.deleteMapping(SPORT_ID);
    }








}
