package getRequest.participant;

import com.atlas.common.enums.Country;
import config.Specifications;
import jdbc.CategoryDAO;
import jdbc.ParticipantDAO;
import jdbc.SportDAO;
import modelDB.CategoryModel;
import modelDB.MappingModel;
import modelDB.ParticipantModel;
import modelDB.SportModel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestDto.category.MapRequest;
import responsDto.participant.CreateParticipantResponse;

import java.sql.SQLException;


import static io.restassured.RestAssured.given;

public class MapParticipantTest {

    SportDAO sportDAO = new SportDAO();
    SportModel sportFootball = new SportModel(SPORT_FOOTBALL_ID, SPORT_FOOTBALL_NAME);
    private static final Integer SPORT_FOOTBALL_ID = 9999;
    private static final String SPORT_FOOTBALL_NAME = "Football Test";

    CategoryDAO categoryDAO = new CategoryDAO();
    CategoryModel categoryUkraineFootball = new CategoryModel(CATEGORY_UKRAINE_ID_FOOTBALL, CATEGORY_NAME_UKRAINE, SPORT_FOOTBALL_ID, REGION_ID);
    private static final String CATEGORY_NAME_UKRAINE = "Ukraine Test";
    private static final Integer CATEGORY_UKRAINE_ID_FOOTBALL = 10000;
    private static final Integer REGION_ID = 30;

    ParticipantDAO participantDAO = new ParticipantDAO();
    ParticipantModel participantModelDynamo = new ParticipantModel(PARTICIPANT_ID, PARTICIPANT_NAME, COUNTRY_DYNAMO, SPORT_FOOTBALL_ID, CATEGORY_UKRAINE_ID_FOOTBALL);
    private static final Integer PARTICIPANT_ID = 444444444;
    private static final String PARTICIPANT_NAME = "FC Dynamo Kiev Test";
    private static final String COUNTRY_DYNAMO = "UA";
    private static final Integer NON_EXISTENT_PARTICIPANT_ID = 777777;
    private static final String PROVIDER = "lsport";
    private static final String EXTERNAL_ID = "lsport:12345678";



    private static final String URL_ENDPOINT = "/participant:map";


    public MapParticipantTest() throws SQLException {
    }

    @BeforeClass
    void createSportCategoryTournamentForTest() {
        sportDAO.createNewSport(sportFootball);
        categoryDAO.createCategory(categoryUkraineFootball);
        participantDAO.createParticipant(participantModelDynamo);

    }

    @AfterClass
    void deleteAll() {
        participantDAO.deleteMapping(participantModelDynamo.getId());
        participantDAO.delete(participantModelDynamo.getId());
        categoryDAO.delete(categoryUkraineFootball.getId());
        sportDAO.delete(sportFootball.getId());

    }

    @Test(testName = "Mapping participant and checking create entity in db")
    public void map_NewParticipant_EntityMustBeCreatedIndDB()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(PARTICIPANT_ID)
                .setProvider(PROVIDER)
                .build();

        MappingModel mappingModel = new MappingModel(EXTERNAL_ID,PARTICIPANT_ID,PROVIDER,PARTICIPANT_NAME);
        CreateParticipantResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), PARTICIPANT_ID);
        Assert.assertEquals(response.getName(), PARTICIPANT_NAME);
        Assert.assertEquals(response.getCountry(), Country.UA);
        Assert.assertTrue(participantDAO.getMap(response.getMappedId()));
        Assert.assertEquals(mappingModel.getMappedId(), participantDAO.getMapParticipant(response.getMappedId()).getMappedId());

    }

    @Test(testName = "Mapping participant and checking create entity in db")
    public void map_NewParticipant_WithNonExistParticipant()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(NON_EXISTENT_PARTICIPANT_ID)
                .setProvider(PROVIDER)
                .build();

        CreateParticipantResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getCountry());

    }

    @Test(testName = "Map participant when field external_id not present in request")
    public void map_Participant_WithoutFieldExternalId_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setMappedId(PARTICIPANT_ID)
                .setProvider(PROVIDER)
                .build();

        CreateParticipantResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getCountry());

    }

    @Test(testName = "Map participant when field mapped_id not present in request")
    public void map_Tournament_WithoutFieldMappedId_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setProvider(PROVIDER)
                .build();

        CreateParticipantResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getCountry());

    }

    @Test(testName = "Map participant when field provider not present in request")
    public void map_Tournament_WithoutFieldProvider_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(PARTICIPANT_ID)
                .build();

        CreateParticipantResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getCountry());

    }
}
