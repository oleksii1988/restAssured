package tournament;

import com.atlas.common.enums.Language;
import config.Specifications;
import jdbc.CategoryDAO;
import jdbc.SportDAO;
import jdbc.TournamentDAO;
import modelDB.CategoryModel;
import modelDB.SportModel;
import modelDB.TournamentModel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestDto.tournament.CreateTournamentRequest;
import responsDto.tournament.CreateTournamentResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateTournamentTest {

    CategoryDAO categoryDAO = new CategoryDAO();
    CategoryModel categoryUkraineFootball = new CategoryModel(CATEGORY_UKRAINE_ID, CATEGORY_NAME_UKRAINE, SPORT_FOOTBALL_ID, REGION_ID);
    private static final String CATEGORY_NAME_UKRAINE = "Ukraine Test";
    private static final Integer CATEGORY_UKRAINE_ID = 10000;
    private static final Integer REGION_ID = 7777;

    CategoryModel categoryUEFAFootball = new CategoryModel(UPDATE_CATEGORY_ID, CATEGORY_NAME, SPORT_FOOTBALL_ID, ENGLAND_REGION_ID);
    private static final String CATEGORY_NAME = "UEFA Test";
    private static final Integer UPDATE_CATEGORY_ID = 20000;
    private static final Integer ENGLAND_REGION_ID = 9999;

    SportDAO sportDAO = new SportDAO();
    SportModel sportFootball = new SportModel(SPORT_FOOTBALL_ID, SPORT_FOOTBALL_NAME);
    private static final Integer SPORT_FOOTBALL_ID = 9999;
    private static final String SPORT_FOOTBALL_NAME = "Football Test";

    TournamentDAO tournamentDAO = new TournamentDAO();
    TournamentModel superLeague = new TournamentModel(TOURNAMENT_ID, CATEGORY_UKRAINE_ID, TOURNAMENT_NAME, SPORT_FORM_ID);
    private static final Integer TOURNAMENT_ID = 99999999;
    private static final String TOURNAMENT_NAME = "Ukraine Super League Test";
    private static final String TOURNAMENT_UPDATE_NAME = "Ukraine League Test";
    private static final Integer SPORT_FORM_ID = 1;
    private static final Integer UPDATE_SPORT_FORM_ID = 14;
    private static final Map<Language, String> TRANSLATIONS_TOURNAMENT = Map.of
            (Language.RUS, "Украинская Лига Тест", Language.UKR, "Українська Ліга Тест", Language.POR, "Liga Ucraniana Teste");

    private static final Integer INCORRECT_CATEGORY_ID = 999999999;

    private static final String URL_ENDPOINT = "/tournament/99999999:update";

    CreateTournamentResponse.Periods firstHalf = new CreateTournamentResponse.Periods(2, 1, 2400);
    CreateTournamentResponse.Periods breakTime = new CreateTournamentResponse.Periods(68, 2, 0);
    CreateTournamentResponse.Periods secondHalf = new CreateTournamentResponse.Periods(3, 3, 2400);

    public UpdateTournamentTest() throws SQLException {
    }

    public List<CreateTournamentResponse.Periods> getExpectedSportForm() {

        List<CreateTournamentResponse.Periods> periodsList = new ArrayList<>();
        periodsList.add(firstHalf);
        periodsList.add(breakTime);
        periodsList.add(secondHalf);


        return periodsList;
    }

    @BeforeClass
    void createSportCategoryTournamentForTest() {
        sportDAO.createNewSport(sportFootball);
        categoryDAO.createCategory(categoryUkraineFootball);
        categoryDAO.createCategory(categoryUEFAFootball);
        tournamentDAO.createTournament(superLeague);

    }

    @AfterClass
    void deleteAll(){
        tournamentDAO.delete(categoryUkraineFootball.getId());
        tournamentDAO.delete(categoryUEFAFootball.getId());
        categoryDAO.delete(categoryUkraineFootball.getId());
        categoryDAO.delete(categoryUEFAFootball.getId());
        sportDAO.delete(sportFootball.getId());
    }


    @Test(testName = "Update all fields on tournament")
    public void update_tournamentAllFields() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setName(TOURNAMENT_UPDATE_NAME)
                .setCategoryId(UPDATE_CATEGORY_ID)
                .setSportFormId(UPDATE_SPORT_FORM_ID)
                .setTranslations(TRANSLATIONS_TOURNAMENT).build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),TOURNAMENT_ID);
        Assert.assertEquals(response.getName(),TOURNAMENT_UPDATE_NAME);
        Assert.assertEquals(response.getCategory().getMappedId(),UPDATE_CATEGORY_ID);
        Assert.assertEquals(response.getSportFormBoDto().getId(),UPDATE_SPORT_FORM_ID);
        Assert.assertEquals(response.getTranslations().get(Language.UKR),"Українська Ліга Тест");
        Assert.assertEquals(response.getTranslations().get(Language.POR),"Liga Ucraniana Teste");
        Assert.assertEquals(response.getTranslations().get(Language.RUS),"Украинская Лига Тест");
        Assert.assertEquals(response.getSportFormBoDto().getPeriods(),getExpectedSportForm());
        Assert.assertEquals(tournamentDAO.getTournamentByIdPresentInCategory(response.getMappedId()),UPDATE_CATEGORY_ID);

    }

    @Test(testName = "Update field name on tournament without fields categoryId, sportFormId,translations")
    public void update_nameByTournament_ExpectedCode400() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setName(TOURNAMENT_UPDATE_NAME)
                .build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getCategory());
        Assert.assertNull(response.getTranslations());
        Assert.assertNull(response.getSportFormBoDto());

    }

    @Test(testName = "Update categoryId on tournament without fields name sportFormId translations")
    public void update_categoryIdByTournament_ExpectedCode400() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setCategoryId(UPDATE_CATEGORY_ID)
                .build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getCategory());
        Assert.assertNull(response.getTranslations());
        Assert.assertNull(response.getSportFormBoDto());


    }


    @Test(testName = "Update categoryId on tournament and check in DB is present tournament in category table in DB")
    public void update_categoryIdByTournament() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setCategoryId(UPDATE_CATEGORY_ID)
                .setTranslations(TRANSLATIONS_TOURNAMENT)
                .setName(TOURNAMENT_NAME)
                .setSportFormId(SPORT_FORM_ID)
                .build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),TOURNAMENT_ID);
        Assert.assertEquals(tournamentDAO.getTournamentByIdPresentInCategory(response.getMappedId()),UPDATE_CATEGORY_ID);

    }

    @Test(testName = "Update categoryId on tournament without fields name sportFormId translations")
    public void update_TournamentNonExistCategoryIdExpectedCode404() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        CreateTournamentRequest createTournament = new CreateTournamentRequest.TournamentBuilderImpl()
                .setCategoryId(INCORRECT_CATEGORY_ID)
                .setSportFormId(UPDATE_SPORT_FORM_ID)
                .setName(TOURNAMENT_UPDATE_NAME)
                .setTranslations(TRANSLATIONS_TOURNAMENT)
                .build();

        CreateTournamentResponse response = given()
                .body(createTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getCategory());
        Assert.assertNull(response.getTranslations());
        Assert.assertNull(response.getSportFormBoDto());


    }



}
