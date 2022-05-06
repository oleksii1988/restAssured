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
import responsDto.participant.CreateParticipantResponse;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class GetParticipantTest {


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

    private static final String URL_ENDPOINT = "/participant/";


    public GetParticipantTest() throws SQLException {
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

    @Test(testName = "Get participant by Id")
    public void get_Participant()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateParticipantResponse response = given()
                .when()
                .get(URL_ENDPOINT+PARTICIPANT_ID)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), PARTICIPANT_ID);
        Assert.assertEquals(response.getName(), PARTICIPANT_NAME);
        Assert.assertEquals(response.getCountry(), Country.UA);
        Assert.assertTrue(participantDAO.getParticipantInDB(response.getMappedId()));

    }

    @Test(testName = "Get participant with non-exist id")
    public void get_ParticipantWithNonExistId_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        CreateParticipantResponse response = given()
                .when()
                .get(URL_ENDPOINT+NON_EXISTENT_PARTICIPANT_ID)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNotNull(response);


    }



}
