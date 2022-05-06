package tournament;
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
import responsDto.tournament.CreateTournamentResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class GetTournamentTest {

    SportDAO sportDAO = new SportDAO();
    SportModel sportFootball = new SportModel(SPORT_ID, SPORT_NAME);
    private static final Integer SPORT_ID = 9999;
    private static final String SPORT_NAME = "Football Test";

    CategoryDAO categoryDAO = new CategoryDAO();
    CategoryModel categoryUkraine = new CategoryModel(CATEGORY_ID, CATEGORY_NAME, SPORT_ID, REGION_ID);
    private static final String CATEGORY_NAME = "Ukraine Test";
    private static final Integer CATEGORY_ID = 10000;
    private static final Integer REGION_ID = 30;

    TournamentDAO tournamentDAO = new TournamentDAO();
    TournamentModel superLeague = new TournamentModel(TOURNAMENT_ID, CATEGORY_ID, TOURNAMENT_NAME, SPORT_FORM_ID);
    private static final Integer TOURNAMENT_ID = 99999999;
    private static final Integer UNKNOWN_TOURNAMENT_ID = 8888888;
    private static final String TOURNAMENT_NAME = "Ukraine Super League Test";
    private static final Integer SPORT_FORM_ID = 1;
    private static final String SPORT_FORM_NAME = "Sport Form Soccer";


    private static final String URL_ENDPOINT = "/tournament/";


    CreateTournamentResponse.Periods firstHalf = new CreateTournamentResponse.Periods(2, 1, 2700);
    CreateTournamentResponse.Periods breakTime = new CreateTournamentResponse.Periods(68, 2, 0);
    CreateTournamentResponse.Periods secondHalf = new CreateTournamentResponse.Periods(3, 3, 2700);

    public GetTournamentTest() throws SQLException {
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
        categoryDAO.createCategory(categoryUkraine);
        tournamentDAO.createTournament(superLeague);

    }

    @AfterClass
    void deleteAll() {
        tournamentDAO.delete(categoryUkraine.getId());
        categoryDAO.delete(categoryUkraine.getId());
        sportDAO.delete(sportFootball.getId());

    }

    @Test(testName = "Get tournament by id")
    public void get_TournamentById() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateTournamentResponse response = given()
                .when()
                .get(URL_ENDPOINT + TOURNAMENT_ID)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), TOURNAMENT_ID);
        Assert.assertEquals(response.getName(), TOURNAMENT_NAME);
        Assert.assertEquals(response.getCategory().getMappedId(), CATEGORY_ID);
        Assert.assertEquals(response.getCategory().getRegionId(), REGION_ID);
        Assert.assertEquals(response.getCategory().getName(), CATEGORY_NAME);
        Assert.assertEquals(response.getCategory().getSport().getName(), SPORT_NAME);
        Assert.assertEquals(response.getCategory().getSport().getMappedId(), SPORT_ID);
        Assert.assertEquals(response.getSportFormBoDto().getId(), SPORT_FORM_ID);
        Assert.assertEquals(response.getSportFormBoDto().getSportId(), 1);
        Assert.assertEquals(response.getSportFormBoDto().getName(), SPORT_FORM_NAME);
        Assert.assertFalse(response.getSportFormBoDto().getPeriods().isEmpty());
        Assert.assertEquals(response.getSportFormBoDto().getPeriods(), getExpectedSportForm());
        Assert.assertTrue(tournamentDAO.getTournamentInDB(response.getMappedId()));
        Assert.assertEquals(tournamentDAO.getTournamentByIdPresentInCategory(response.getMappedId()),CATEGORY_ID);

    }

    @Test(testName = "Get tournament by non-exist id")
    public void get_TournamentByNonExistTournamentId_ExpectedCode404() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        CreateTournamentResponse response = given()
                .when()
                .get(URL_ENDPOINT + UNKNOWN_TOURNAMENT_ID)
                .then().log().all()
                .extract().body().as(CreateTournamentResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getTranslations());
        Assert.assertNull(response.getCategory());
        Assert.assertNull(response.getSportFormBoDto());

    }
}
