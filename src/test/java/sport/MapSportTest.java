package sport;

import config.Specifications;
import jdbc.SportDAO;
import modelDB.MappingModel;
import modelDB.SportModel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestDto.category.MapRequest;
import responsDto.sport.MapSportResponse;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class MapSportTest {


    SportModel sportFootball = new SportModel(SPORT_ID, SPORT_NAME);
    private static final Integer SPORT_ID = 9999;
    private static final String SPORT_NAME = "Football Test";
    private static final String EXTERNAL_ID = "lsport:687888789";
    private static final String PROVIDER = "lsport";
    private static final Integer NON_EXISTENT_SPORT_ID = 999999;

    private static final String URL_ENDPOINT = "/sport:map";

    SportDAO sportDAO = new SportDAO();



    public MapSportTest() throws SQLException {
    }

    @BeforeClass
    void createSportCategoryTournamentForTest() {
        sportDAO.createNewSport(sportFootball);

    }

    @AfterClass
    void deleteAll(){
        sportDAO.delete(sportFootball.getId());

    }


    // TODO: Checking in the database of a mapped SPORT from several providers

    @Test(testName = "Mapping sport with flag single is FALSE and checking create entity in db")
    public void map_NewSport_WithSingleFalse_EntityMustBeCreatedIndDB()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(SPORT_ID)
                .setProvider(PROVIDER)
                .setSingle(false)
                .build();

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


    @Test(testName = "Mapping sport with flag single is TRUE and checking create entity in db")
    public void map_NewSport_WithSingleTrue_EntityMustNotBeCreatedIndDB()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(SPORT_ID)
                .setProvider(PROVIDER)
                .setSingle(true)
                .build();

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

    @Test(testName = "Mapping sport with flag single is TRUE and sportId non-exist")
    public void map_NewSport_WithSingleTrue_WhenSportIdNonExistExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(NON_EXISTENT_SPORT_ID)
                .setProvider(PROVIDER)
                .setSingle(true)
                .build();

        MapSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapSportResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());


    }


    @Test(testName = "Mapping sport with flag single is FALSE and sportId non-exist")
    public void map_NewSport_WithSingleFalse_WhenSportIdNonExistExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(NON_EXISTENT_SPORT_ID)
                .setProvider(PROVIDER)
                .setSingle(false)
                .build();

        MapSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapSportResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());


    }

    @Test(testName = "Map sport when field external_id not present in request")
    public void map_Sport_WithoutFieldExternalId_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setMappedId(SPORT_ID)
                .setProvider(PROVIDER)
                .setSingle(false)
                .build();

        MapSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapSportResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());

    }

    @Test(testName = "Map sport when field mapped_id not present in request")
    public void map_Sport_WithoutFieldMappedId_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setProvider(PROVIDER)
                .setSingle(true)
                .build();

        MapSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapSportResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());

    }

    @Test(testName = "Map sport when field provider not present in request")
    public void map_Sport_WithoutFieldProvider_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(SPORT_ID)
                .setSingle(true)
                .build();

        MapSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapSportResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());

    }




}
