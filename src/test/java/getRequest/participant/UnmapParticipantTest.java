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
import responsDto.participant.UnmapParticipantResponse;

import java.sql.SQLException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class UnmapParticipantTest {

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
    ParticipantModel participantModelDynamo = new ParticipantModel(PARTICIPANT_ID, PARTICIPANT_NAME, COUNTRY_UKRAINE, SPORT_FOOTBALL_ID, CATEGORY_UKRAINE_ID_FOOTBALL);
    MappingModel mappingModelDynamo = new MappingModel(EXTERNAL_ID_DYNAMO, PARTICIPANT_ID,PROVIDER_LSPORT, PARTICIPANT_NAME);
    private static final Integer PARTICIPANT_ID = 444444444;
    private static final String PARTICIPANT_NAME = "FC Dynamo Kiev Test";
    private static final String COUNTRY_UKRAINE = "UA";

    MappingModel mappingModelShkhtar = new MappingModel(EXTERNAL_ID_SHAKHTAR,PARTICIPANT_ID,PROVIDER_BETCONSTRUCT, PARTICIPANT_NAME);
    private static final Integer NON_EXISTENT_PARTICIPANT_ID = 777777;
    private static final String PROVIDER_LSPORT = "lsport";
    private static final String PROVIDER_BETCONSTRUCT = "betconstruct";
    private static final String EXTERNAL_ID_DYNAMO = "lsport:12345678";
    private static final String EXTERNAL_ID_SHAKHTAR = "betconstruct:98789654";
    private static final String INVALIDATE_EXTERNAL_ID = "lsport:000000000";



    private static final String URL_ENDPOINT = "/participant:unmap";


    public UnmapParticipantTest() throws SQLException {
    }

    @BeforeClass
    void createSportCategoryTournamentForTest() {
        sportDAO.createNewSport(sportFootball);
        categoryDAO.createCategory(categoryUkraineFootball);
        participantDAO.createParticipant(participantModelDynamo);
        participantDAO.createMapping(mappingModelDynamo);
        participantDAO.createMapping(mappingModelShkhtar);


    }

    @AfterClass
    void deleteAll() {
        participantDAO.deleteMapping(PARTICIPANT_ID);
        participantDAO.delete(participantModelDynamo.getId());
        categoryDAO.delete(categoryUkraineFootball.getId());
        sportDAO.delete(sportFootball.getId());

    }


    @Test(testName = "Unmap participant and check in table participant mapping")
    public void unmapParticipant_removeMappingCheckInDBEntityNotExist()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID_DYNAMO)
                .setMappedId(PARTICIPANT_ID)
                .setProvider(PROVIDER_LSPORT)
                .build();

        UnmapParticipantResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapParticipantResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), PARTICIPANT_ID);
        Assert.assertEquals(response.getName(), PARTICIPANT_NAME);
        Assert.assertEquals(response.getCountry(),Country.UA);
        Assert.assertTrue(response.getProviderMappings().stream()
                .noneMatch((data -> Objects.equals(data.getExternalId(), EXTERNAL_ID_DYNAMO))));
        Assert.assertTrue(response.getProviderMappings().stream()
                .allMatch((data -> Objects.equals(data.getExternalId(), EXTERNAL_ID_SHAKHTAR))));
        participantDAO.deleteMapping(PARTICIPANT_ID);
        Assert.assertFalse(participantDAO.getMap(response.getMappedId()));

    }


    @Test(testName = "Unmap participant use invalidate participant mappedId")
    public void unmapParticipant_useInvalidateMappedId_ExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID_DYNAMO)
                .setMappedId(NON_EXISTENT_PARTICIPANT_ID)
                .setProvider(PROVIDER_LSPORT)
                .build();

        UnmapParticipantResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapParticipantResponse.class);

        Assert.assertNull(response.getProviderMappings());
        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());

    }



    @Test(testName = "Unmap participant use invalidate externalId")
    public void unmapParticipant_useInvalidateExternalId_ExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(INVALIDATE_EXTERNAL_ID)
                .setMappedId(PARTICIPANT_ID)
                .setProvider(PROVIDER_LSPORT)
                .build();

        UnmapParticipantResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapParticipantResponse.class);

        Assert.assertNull(response.getProviderMappings());
        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());

    }











}
