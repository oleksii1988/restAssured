package tournament;
import config.Specifications;
import jdbc.CategoryDAO;
import jdbc.SportDAO;
import jdbc.TournamentDAO;
import modelDB.CategoryModel;
import modelDB.MappingModel;
import modelDB.SportModel;
import modelDB.TournamentModel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestDto.category.MapRequest;
import responsDto.tournament.CreateTournamentResponse;
import responsDto.tournament.UnmapTournamentResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class UnmapTournamentTest {


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
    TournamentModel superLeague = new TournamentModel(TOURNAMENT_ID, CATEGORY_ID, TOURNAMENT_NAME,SPORT_FORM_ID);
    private static final Integer TOURNAMENT_ID = 99999999;
    private static final String TOURNAMENT_NAME = "Ukraine Super League Test";
    private static final Integer SPORT_FORM_ID = 1;

    private static final String PROVIDER = "lsport";
    private static final String EXTERNAL_ID = "lsport:12345678";
    private static final Integer NON_EXISTENT_TOURNAMENT_ID = 777777;
    private static final String NON_EXISTENT_EXTERNAL_ID = "lsport:123487965425";

    MappingModel mappingModel = new MappingModel(EXTERNAL_ID,TOURNAMENT_ID,PROVIDER,TOURNAMENT_NAME);


    private static final String URL_ENDPOINT = "/tournament:unmap";


    CreateTournamentResponse.Periods firstHalf = new CreateTournamentResponse.Periods(2, 1, 2700);
    CreateTournamentResponse.Periods breakTime = new CreateTournamentResponse.Periods(68, 2, 0);
    CreateTournamentResponse.Periods secondHalf = new CreateTournamentResponse.Periods(3, 3, 2700);

    public UnmapTournamentTest() throws SQLException {
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
        tournamentDAO.createMapping(mappingModel);

    }

    @AfterClass
    void deleteAll(){
        tournamentDAO.delete(categoryUkraine.getId());
        categoryDAO.delete(categoryUkraine.getId());
        sportDAO.delete(sportFootball.getId());

    }


    @Test(testName = "Unmap tournament and checking not present entity in db")
    public void unmap_Tournament() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(TOURNAMENT_ID)
                .setProvider(PROVIDER)
                .build();

        UnmapTournamentResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapTournamentResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), TOURNAMENT_ID);
        Assert.assertEquals(response.getName(), TOURNAMENT_NAME);
        Assert.assertEquals(response.getSportFormBoDto().getPeriods(),getExpectedSportForm());
        Assert.assertTrue(response.getProviderMappings().stream()
                .noneMatch((data -> Objects.equals(data.getExternalId(), EXTERNAL_ID))));
        Assert.assertFalse(tournamentDAO.getMap(response.getMappedId()));

    }

    @Test(testName = "Unmap tournament with unvalidated tournamentId")
    public void unmap_Tournament_WithNonExistentTournamentId_ExpectedCode404() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(NON_EXISTENT_TOURNAMENT_ID)
                .setProvider(PROVIDER)
                .build();

        UnmapTournamentResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapTournamentResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getTranslations());
        Assert.assertNull(response.getCategory());
        Assert.assertNull(response.getSportFormBoDto());
        Assert.assertNull(response.getProviderMappings());

    }


    @Test(testName = "Unmap tournament with unvalidated externalId")
    public void unmap_Tournament_WithNonExistentExternalId_ExpectedCode404() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(NON_EXISTENT_EXTERNAL_ID)
                .setMappedId(TOURNAMENT_ID)
                .setProvider(PROVIDER)
                .build();

        UnmapTournamentResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapTournamentResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getTranslations());
        Assert.assertNull(response.getCategory());
        Assert.assertNull(response.getSportFormBoDto());
        Assert.assertNull(response.getProviderMappings());

    }







}
