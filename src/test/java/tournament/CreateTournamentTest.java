package tournament;

import com.atlas.common.enums.Language;
import config.Specifications;
import jdbc.CategoryDAO;
import jdbc.SportDAO;
import jdbc.TournamentDAO;
import modelDB.CategoryModel;
import modelDB.SportModel;
import org.testng.Assert;
import org.testng.annotations.*;
import requestDto.tournament.CreateTournamentRequest;
import responsDto.tournament.CreateTournamentResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateTournamentTest {

    TournamentDAO tournamentDAO = new TournamentDAO();
    private static final String TOURNAMENT_NAME = "Ukrainian Premier League";
    private static final Integer SPORT_FORM_ID = 1;
    private static final Integer SPORT_FORM_FROM_ANOTHER_SPORT = 29;
    private static final Integer NON_EXIST_SPORT_FORM_ID = 99999;
    private static final Map<Language, String> TRANSLATIONS_TOURNAMENT = Map.of
            (Language.RUS, "Украинская Лига", Language.UKR, "Українська Ліга", Language.POR, "Liga Ucraniana");

    CategoryDAO categoryDAO = new CategoryDAO();
    CategoryModel categoryForTest = new CategoryModel(CATEGORY_ID, CATEGORY_NAME, SPORT_ID, REGION_ID, TRANSLATIONS_CATEGORY);
    private static final String CATEGORY_NAME = "Ukraine Test";
    private static final Integer CATEGORY_ID = 10000;
    private static final Integer NON_EXIST_CATEGORY_ID = 99999;
    private static final Integer REGION_ID = 30;
    private static final Map<String, String> TRANSLATIONS_CATEGORY = Map.of("ENG", "Ukraine", "POR", "Ucrânia");

    SportDAO sportDAO = new SportDAO();
    SportModel sportForTest = new SportModel(SPORT_ID, SPORT_NAME, TRANSLATIONS_SPORT);
    private static final Integer SPORT_ID = 9999;
    private static final String SPORT_NAME = "Football Test";
    private static final String SPORT_FORM_NAME = "Sport Form Soccer";
    private static final Map<String, String> TRANSLATIONS_SPORT = Map.of("ENG", "Football Test", "POR", "Teste de Futebol");

    private static final String URL_ENDPOINT = "/tournament:create";


    CreateTournamentResponse.Periods firstHalf = new CreateTournamentResponse.Periods(2, 1, 2700);
    CreateTournamentResponse.Periods breakTime = new CreateTournamentResponse.Periods(68, 2, 0);
    CreateTournamentResponse.Periods secondHalf = new CreateTournamentResponse.Periods(3, 3, 2700);

    public CreateTournamentTest() throws SQLException {
    }

    public List<CreateTournamentResponse.Periods> getExpectedSportForm() {

        List<CreateTournamentResponse.Periods> periodsList = new ArrayList<>();
        periodsList.add(firstHalf);
        periodsList.add(breakTime);
        periodsList.add(secondHalf);


        return periodsList;
    }


    @BeforeClass
    void createSportAndCategoryForTests() {
        sportDAO.createNewSport(sportForTest);
        categoryDAO.createCategory(categoryForTest);
    }

    @AfterClass
    void deleteSportAndCategoryForTests() {
        categoryDAO.delete(categoryForTest.getId());
        sportDAO.delete(sportForTest.getId());
    }


    @Test(testName = "Creating tournament and check create entity in db and validating all fields from response")
    public void create_tournament_CheckEntityIDdb() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setName(TOURNAMENT_NAME)
                .setCategoryId(CATEGORY_ID)
                .setSportFormId(SPORT_FORM_ID)
                .setTranslations(TRANSLATIONS_TOURNAMENT).build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMappedId());
        Assert.assertEquals(response.getName(), TOURNAMENT_NAME);
        Assert.assertEquals(response.getCategory().getMappedId(), CATEGORY_ID);
        Assert.assertEquals(response.getCategory().getRegionId(), REGION_ID);
        Assert.assertEquals(response.getCategory().getName(), CATEGORY_NAME);
        Assert.assertEquals(response.getCategory().getSport().getName(), SPORT_NAME);
        Assert.assertEquals(response.getCategory().getSport().getMappedId(), SPORT_ID);
        Assert.assertTrue(response.getTranslations().containsValue("Українська Ліга"));
        Assert.assertEquals(response.getSportFormBoDto().getId(), SPORT_FORM_ID);
        Assert.assertEquals(response.getSportFormBoDto().getSportId(), 1);
        Assert.assertEquals(response.getSportFormBoDto().getName(), SPORT_FORM_NAME);
        Assert.assertEquals(response.getSportFormBoDto().getPeriods(),getExpectedSportForm());
        Assert.assertTrue(tournamentDAO.getTournamentInDB(response.getMappedId()));
        tournamentDAO.delete(categoryForTest.getId());


    }



    @Test(testName = "Creating tournament without sport-form and check create entity in db")
    public void create_tournament_WithoutSportForm_ExpectedCreateNewTournament() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setName(TOURNAMENT_NAME)
                .setCategoryId(CATEGORY_ID)
                .setTranslations(TRANSLATIONS_TOURNAMENT).build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMappedId());
        Assert.assertEquals(response.getName(), TOURNAMENT_NAME);
        Assert.assertEquals(response.getCategory().getMappedId(), CATEGORY_ID);
        Assert.assertEquals(response.getCategory().getRegionId(), REGION_ID);
        Assert.assertEquals(response.getCategory().getName(), CATEGORY_NAME);
        Assert.assertEquals(response.getCategory().getSport().getName(), SPORT_NAME);
        Assert.assertEquals(response.getCategory().getSport().getMappedId(), SPORT_ID);
        Assert.assertTrue(response.getTranslations().containsValue("Українська Ліга"));
        Assert.assertNull(response.getSportFormBoDto());
        Assert.assertTrue(tournamentDAO.getTournamentInDB(response.getMappedId()));
        tournamentDAO.delete(categoryForTest.getId());

    }


    @Test(testName = "Create a tournament and check if the tournament is present in the category table in the database")
    public void create_tournament_andCheckingInDbIsPresentInCategoryTable() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setName(TOURNAMENT_NAME)
                .setCategoryId(CATEGORY_ID)
                .setTranslations(TRANSLATIONS_TOURNAMENT).build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMappedId());
        Assert.assertEquals(response.getName(), TOURNAMENT_NAME);
        Assert.assertEquals(response.getCategory().getMappedId(), CATEGORY_ID);
        Assert.assertTrue(tournamentDAO.getTournamentInDB(response.getMappedId()));
        Assert.assertEquals(tournamentDAO.getTournamentByIdPresentInCategory(response.getMappedId()),CATEGORY_ID);
        tournamentDAO.delete(categoryForTest.getId());


    }

    @Test(testName = "Creating tournament without categoryId")
    public void create_tournament_WithoutCategoryExpectedCode400() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setName(TOURNAMENT_NAME)
                .setSportFormId(SPORT_FORM_ID)
                .setTranslations(TRANSLATIONS_TOURNAMENT).build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNull(response.getCategory());


    }

    @Test(testName = "Creating tournament without field tournament name ")
    public void create_tournament_WithoutNameExpectedCode400() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setCategoryId(CATEGORY_ID)
                .setSportFormId(SPORT_FORM_ID)
                .setTranslations(TRANSLATIONS_TOURNAMENT).build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNull(response.getCategory());


    }


    @Test(testName = "Creating tournament with non-exist category")
    public void create_tournament_WithNoneExistCategoryExpectedCode404() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setCategoryId(NON_EXIST_CATEGORY_ID)
                .setSportFormId(SPORT_FORM_ID)
                .setName(CATEGORY_NAME)
                .setTranslations(TRANSLATIONS_TOURNAMENT).build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNull(response.getCategory());

    }

    @Test(testName = "Creating tournament with non-exist sportForm")
    public void create_tournament_WithNoneExistSportFormExpectedCode404() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setCategoryId(CATEGORY_ID)
                .setSportFormId(NON_EXIST_SPORT_FORM_ID)
                .setName(CATEGORY_NAME)
                .setTranslations(TRANSLATIONS_TOURNAMENT).build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNull(response.getCategory());

    }


    @Test(testName = "Creating a tournament with a sport form belonging to another sport")
    public void create_tournament_WithASportFormBelongingToAnotherSport_ExpectedCode412() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec412());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setCategoryId(CATEGORY_ID)
                .setSportFormId(SPORT_FORM_FROM_ANOTHER_SPORT)
                .setName(CATEGORY_NAME)
                .setTranslations(TRANSLATIONS_TOURNAMENT).build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNull(response.getCategory());


    }


}
