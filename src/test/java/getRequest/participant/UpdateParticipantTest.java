package getRequest.participant;

import com.atlas.common.enums.Country;
import config.Specifications;
import jdbc.CategoryDAO;
import jdbc.ParticipantDAO;
import jdbc.SportDAO;
import modelDB.CategoryModel;
import modelDB.ParticipantModel;
import modelDB.SportModel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestDto.participant.CreateParticipantRequest;
import responsDto.participant.CreateParticipantResponse;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class UpdateParticipantTest {

    SportDAO sportDAO = new SportDAO();
    SportModel sportFootball = new SportModel(SPORT_FOOTBALL_ID, SPORT_FOOTBALL_NAME);
    private static final Integer SPORT_FOOTBALL_ID = 9999;
    private static final String SPORT_FOOTBALL_NAME = "Football Test";

    CategoryDAO categoryDAO = new CategoryDAO();
    CategoryModel categoryUkraineFootball = new CategoryModel(CATEGORY_UKRAINE_ID_FOOTBALL, CATEGORY_NAME_UKRAINE, SPORT_FOOTBALL_ID, REGION_ID);
    private static final String CATEGORY_NAME_UKRAINE = "Ukraine Test";
    private static final Integer CATEGORY_UKRAINE_ID_FOOTBALL = 10000;
    private static final Integer REGION_ID = 30;

    private static final Integer PARTICIPANT_ID = 444444444;
    private static final String PARTICIPANT_NAME = "FC Dynamo Kiev Test";
    private static final String ALTERNATIVE_NAME = "Dynamo Test";
    private static final String COUNTRY_DYNAMO = "UA";

    ParticipantDAO participantDAO = new ParticipantDAO();
    ParticipantModel participantModelDynamo =  ParticipantModel.builder()
            .id(PARTICIPANT_ID)
            .full_name(PARTICIPANT_NAME)
            .alternative_name(ALTERNATIVE_NAME)
            .country(COUNTRY_DYNAMO)
            .sport_id(SPORT_FOOTBALL_ID)
            .category_id(CATEGORY_UKRAINE_ID_FOOTBALL)
            .build();

    private static final String UPDATE_PARTICIPANT_NAME = "FC Manchester United Test";
    private static final String UPDATE_ALTERNATIVE_NAME = "Manchester Utd Test";


    private static final String URL_ENDPOINT = "/participant/444444444:update";
    private static final String INVALIDATED_URL_ENDPOINT = "/participant/777777777:update";


    public UpdateParticipantTest() throws SQLException {
    }

    @BeforeClass
    void createSportCategoryTournamentForTest() {
        sportDAO.createNewSport(sportFootball);
        categoryDAO.createCategory(categoryUkraineFootball);
        participantDAO.createParticipant(participantModelDynamo);

    }

    @AfterClass
    void deleteAll() {
        participantDAO.delete(participantModelDynamo.getId());
        categoryDAO.delete(categoryUkraineFootball.getId());
        sportDAO.delete(sportFootball.getId());

    }

    @Test(testName = "Update name and alternative name")
    public void update_participant() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateParticipantRequest createParticipant = new CreateParticipantRequest.ParticipantBuilderImpl()
                .setName(UPDATE_PARTICIPANT_NAME)
                .setAlternativeName(UPDATE_ALTERNATIVE_NAME)
                .build();

        CreateParticipantResponse response = given()
                .body(createParticipant)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), PARTICIPANT_ID);
        Assert.assertEquals(response.getName(), UPDATE_PARTICIPANT_NAME);
        Assert.assertEquals(response.getAlternativeName(), UPDATE_ALTERNATIVE_NAME);
        Assert.assertEquals(response.getCountry(), Country.UA);
        Assert.assertNotEquals(response.getName(), participantModelDynamo.getFull_name());

    }

    @Test(testName = "Update participant with non-exist id")
    public void update_participantWithNonExistId_ExpectedCode404() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        CreateParticipantRequest createParticipant = new CreateParticipantRequest.ParticipantBuilderImpl()
                .setName(UPDATE_PARTICIPANT_NAME)
                .setAlternativeName(UPDATE_ALTERNATIVE_NAME)
                .build();

        CreateParticipantResponse response = given()
                .body(createParticipant)
                .when()
                .post(INVALIDATED_URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getAlternativeName());
        Assert.assertNull(response.getCountry());
        Assert.assertNull(response.getTranslations());


    }



}
