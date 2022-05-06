package getRequest.participant;
import com.atlas.common.enums.Country;
import com.atlas.common.enums.Language;
import config.Specifications;
import jdbc.CategoryDAO;
import jdbc.ParticipantDAO;
import jdbc.SportDAO;
import modelDB.CategoryModel;
import modelDB.SportModel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestDto.participant.CreateParticipantRequest;
import responsDto.participant.CreateParticipantResponse;
import java.sql.SQLException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateParticipantTest {


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
    private static final String PARTICIPANT_NAME = "FC Dynamo Kiev Test";
    private static final String ALTERNATIVE_PARTICIPANT_NAME = "Dynamo Kiev Test";
    private static final Map<Language, String> TRANSLATIONS_PARTICIPANT = Map.of
            (Language.RUS, "Динамо Киев", Language.UKR, "Динамо Киiв", Language.POR, "Dínamo Kiev");


    private static final String URL_ENDPOINT = "/participant:create";


    public CreateParticipantTest() throws SQLException {
    }

    @BeforeClass
    void createSportCategoryTournamentForTest() {
        sportDAO.createNewSport(sportFootball);
        categoryDAO.createCategory(categoryUkraineFootball);

    }

    @AfterClass
    void deleteAll() {
        categoryDAO.delete(categoryUkraineFootball.getId());
        sportDAO.delete(sportFootball.getId());

    }


    @Test(testName = "Creating new participant and check generate mapped_id and check this present participant in the category table in the database")
    public void create_participantAndCheckAllFieldsForResponse() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateParticipantRequest createParticipant = new CreateParticipantRequest.ParticipantBuilderImpl()
                .setCategoryId(CATEGORY_UKRAINE_ID_FOOTBALL)
                .setName(PARTICIPANT_NAME)
                .setAlternativeName(ALTERNATIVE_PARTICIPANT_NAME)
                .setCountry(Country.UA)
                .setTranslations(TRANSLATIONS_PARTICIPANT)
                .build();

        CreateParticipantResponse response = given()
                .body(createParticipant)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMappedId());
        Assert.assertEquals(response.getName(), PARTICIPANT_NAME);
        Assert.assertEquals(response.getAlternativeName(),ALTERNATIVE_PARTICIPANT_NAME);
        Assert.assertEquals(response.getCountry(),Country.UA);
        Assert.assertEquals(response.getTranslations().get(Language.UKR), "Динамо Киiв");
        Assert.assertEquals(response.getTranslations().get(Language.RUS), "Динамо Киев");
        Assert.assertEquals(response.getTranslations().get(Language.POR), "Dínamo Kiev");
        Assert.assertEquals(participantDAO.getParticipantByIdPresentInCategory(response.getMappedId()),CATEGORY_UKRAINE_ID_FOOTBALL);
        participantDAO.delete(response.getMappedId());

    }

    @Test(testName = "Create new participant without categoryId")
    public void create_participantWithoutCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateParticipantRequest createParticipant = new CreateParticipantRequest.ParticipantBuilderImpl()
                .setName(PARTICIPANT_NAME)
                .setAlternativeName(ALTERNATIVE_PARTICIPANT_NAME)
                .setCountry(Country.UA)
                .setTranslations(TRANSLATIONS_PARTICIPANT)
                .build();

        CreateParticipantResponse response = given()
                .body(createParticipant)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMappedId());
        Assert.assertEquals(response.getName(), PARTICIPANT_NAME);
        Assert.assertEquals(response.getAlternativeName(),ALTERNATIVE_PARTICIPANT_NAME);
        Assert.assertEquals(response.getCountry(),Country.UA);
        Assert.assertEquals(response.getTranslations().get(Language.UKR), "Динамо Киiв");
        Assert.assertEquals(response.getTranslations().get(Language.RUS), "Динамо Киев");
        Assert.assertEquals(response.getTranslations().get(Language.POR), "Dínamo Kiev");
        Assert.assertTrue(participantDAO.getParticipantInDB(response.getMappedId()));
        participantDAO.delete(response.getMappedId());

    }

    @Test(testName = "Create new participant without field name")
    public void create_participantWithOutNameExpectedCode400() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        CreateParticipantRequest createParticipant = new CreateParticipantRequest.ParticipantBuilderImpl()
                .setCategoryId(CATEGORY_UKRAINE_ID_FOOTBALL)
                .setAlternativeName(ALTERNATIVE_PARTICIPANT_NAME)
                .setCountry(Country.UA)
                .setTranslations(TRANSLATIONS_PARTICIPANT)
                .build();

        CreateParticipantResponse response = given()
                .body(createParticipant)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateParticipantResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getAlternativeName());
        Assert.assertNull(response.getCountry());
        Assert.assertNull(response.getTranslations());
    }


}
