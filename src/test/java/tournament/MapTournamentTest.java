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
import responsDto.tournament.MapTournamentResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class MapTournamentTest {


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


    private static final String URL_ENDPOINT = "/tournament:map";


    CreateTournamentResponse.Periods firstHalf = new CreateTournamentResponse.Periods(2, 1, 2700);
    CreateTournamentResponse.Periods breakTime = new CreateTournamentResponse.Periods(68, 2, 0);
    CreateTournamentResponse.Periods secondHalf = new CreateTournamentResponse.Periods(3, 3, 2700);

    public MapTournamentTest() throws SQLException {
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
    void deleteAll(){
        tournamentDAO.delete(categoryUkraine.getId());
        categoryDAO.delete(categoryUkraine.getId());
        sportDAO.delete(sportFootball.getId());

    }


    @Test(testName = "Mapping tournament with flag single is FALSE and checking create entity in db")
    public void map_NewTournament_WithSingleFalse_EntityMustBeCreatedIndDB()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(TOURNAMENT_ID)
                .setProvider(PROVIDER)
                .setSingle(false)
                .build();

        MappingModel mappingModel = new MappingModel(EXTERNAL_ID,TOURNAMENT_ID,PROVIDER,TOURNAMENT_NAME);
        MapTournamentResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapTournamentResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), TOURNAMENT_ID);
        Assert.assertEquals(response.getName(), TOURNAMENT_NAME);
        Assert.assertEquals(response.getCategory().getMappedId(), CATEGORY_ID);
        Assert.assertEquals(response.getCategory().getName(), CATEGORY_NAME);
        Assert.assertEquals(response.getCategory().getRegionId(),REGION_ID);
        Assert.assertEquals(response.getCategory().getSport().getMappedId(),SPORT_ID);
        Assert.assertEquals(response.getCategory().getSport().getName(),SPORT_NAME);
        Assert.assertEquals(response.getSportFormBoDto().getPeriods(),getExpectedSportForm());
        Assert.assertTrue(tournamentDAO.getMap(response.getMappedId()));
        Assert.assertEquals(mappingModel, tournamentDAO.getMapTournament(response.getMappedId()));
        tournamentDAO.deleteMapping(superLeague.getId());


    }

    @Test(testName = "Mapping tournament with flag single is TRUE and checking create entity in db")
    public void map_NewTournament_WithSingleTrue_EntityMustNotBeCreatedIndDB()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(TOURNAMENT_ID)
                .setProvider(PROVIDER)
                .setSingle(true)
                .build();

        MapTournamentResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapTournamentResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), TOURNAMENT_ID);
        Assert.assertEquals(response.getName(), TOURNAMENT_NAME);
        Assert.assertEquals(response.getCategory().getMappedId(), CATEGORY_ID);
        Assert.assertEquals(response.getCategory().getName(), CATEGORY_NAME);
        Assert.assertEquals(response.getCategory().getRegionId(),REGION_ID);
        Assert.assertEquals(response.getCategory().getSport().getMappedId(),SPORT_ID);
        Assert.assertEquals(response.getCategory().getSport().getName(),SPORT_NAME);
        Assert.assertEquals(response.getSportFormBoDto().getPeriods(),getExpectedSportForm());
        Assert.assertFalse(tournamentDAO.getMap(response.getMappedId()));

    }

    @Test(testName = "Mapping tournament with flag single is FALSE and used non-exist tournamentId")
    public void map_NewTournament_WithSingleFalse_andNonExistTournamentIdExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(NON_EXISTENT_TOURNAMENT_ID)
                .setProvider(PROVIDER)
                .setSingle(false)
                .build();

        MapTournamentResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapTournamentResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getTranslations());
        Assert.assertNull(response.getCategory());
        Assert.assertNull(response.getSportFormBoDto());

    }

    @Test(testName = "Map tournament when field external_id not present in request")
    public void map_Tournament_WithoutFieldExternalId_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setMappedId(TOURNAMENT_ID)
                .setProvider(PROVIDER)
                .setSingle(false)
                .build();

        MapTournamentResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapTournamentResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getTranslations());
        Assert.assertNull(response.getCategory());
        Assert.assertNull(response.getSportFormBoDto());

    }

    @Test(testName = "Map tournament when field mapped_id not present in request")
    public void map_Tournament_WithoutFieldMappedId_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setProvider(PROVIDER)
                .setSingle(false)
                .build();

        MapTournamentResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapTournamentResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getTranslations());
        Assert.assertNull(response.getCategory());
        Assert.assertNull(response.getSportFormBoDto());

    }

    @Test(testName = "Map tournament when field provider not present in request")
    public void map_Tournament_WithoutFieldProvider_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(TOURNAMENT_ID)
                .setSingle(false)
                .build();

        MapTournamentResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(MapTournamentResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());
        Assert.assertNull(response.getTranslations());
        Assert.assertNull(response.getCategory());
        Assert.assertNull(response.getSportFormBoDto());

    }



}
