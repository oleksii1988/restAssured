package tournament;

import config.Specifications;
import dto.SortingRequest;
import jdbc.CategoryDAO;
import jdbc.SportDAO;
import jdbc.TournamentDAO;
import modelDB.CategoryModel;
import modelDB.SportModel;
import modelDB.TournamentModel;
import org.testng.Assert;
import org.testng.annotations.*;
import requestDto.tournament.SearchTournamentRequest;
import responsDto.tournament.CreateTournamentResponse;
import responsDto.tournament.SearchTournamentResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class SearchTournamentTest {

    CategoryDAO categoryDAO = new CategoryDAO();
    CategoryModel categoryUkraineFootball = new CategoryModel(CATEGORY_UKRAINE_ID_FOOTBALL, CATEGORY_NAME_UKRAINE, SPORT_FOOTBALL_ID, REGION_ID);
    private static final String CATEGORY_NAME_UKRAINE = "Ukraine Test";
    private static final Integer CATEGORY_UKRAINE_ID_FOOTBALL = 10000;
    private static final Integer REGION_ID = 30;

    CategoryModel categoryEnglandFootball = new CategoryModel(CATEGORY_ENGLAND_ID_FOOTBALL, CATEGORY_NAME_ENGLAND_FOOTBALL, SPORT_FOOTBALL_ID, ENGLAND_REGION_ID);
    private static final String CATEGORY_NAME_ENGLAND_FOOTBALL = "England Test";
    private static final Integer CATEGORY_ENGLAND_ID_FOOTBALL = 20000;
    private static final Integer ENGLAND_REGION_ID = 6;

    CategoryModel categoryUkraineBasket = new CategoryModel(CATEGORY_UKRAINE_ID_BASKETBALL, CATEGORY_NAME_UKRAINE, SPORT_BASKETBALL_ID, REGION_ID);
    private static final Integer CATEGORY_UKRAINE_ID_BASKETBALL = 30000;

    SportDAO sportDAO = new SportDAO();
    SportModel sportFootball = new SportModel(SPORT_FOOTBALL_ID, SPORT_FOOTBALL_NAME);
    private static final Integer SPORT_FOOTBALL_ID = 9999;
    private static final String SPORT_FOOTBALL_NAME = "Football Test";


    SportModel sportBasketball = new SportModel(SPORT_BASKETBALL_ID, SPORT_BASKETBALL_NAME);
    private static final Integer SPORT_BASKETBALL_ID = 9998;
    private static final String SPORT_BASKETBALL_NAME = "Basketball Test";

    TournamentDAO tournamentDAO = new TournamentDAO();
    TournamentModel superLeague = new TournamentModel(TOURNAMENT_SUPER_LEAGUE_ID, CATEGORY_UKRAINE_ID_FOOTBALL,TOURNAMENT_SUPER_LEAGUE_NAME ,SPORT_FORM_ID);
    private static final Integer TOURNAMENT_SUPER_LEAGUE_ID = 99999999;
    private static final String TOURNAMENT_SUPER_LEAGUE_NAME = "Ukraine Super League Test";
    private static final Integer SPORT_FORM_ID = 1;

    TournamentModel premierLeague = new TournamentModel(TOURNAMENT_PREMIER_LEAGUE_ID, CATEGORY_UKRAINE_ID_FOOTBALL,TOURNAMENT_PREMIER_LEAGUE_NAME,SPORT_FORM_ID);
    private static final String TOURNAMENT_PREMIER_LEAGUE_NAME = "Ukraine Premier League Test";
    private static final Integer TOURNAMENT_PREMIER_LEAGUE_ID = 99999998;

    TournamentModel nationalCupEngland = new TournamentModel(NATIONAL_CUP_ENGLAND_ID, CATEGORY_ENGLAND_ID_FOOTBALL,NATIONAL_CUP_ENGLAND_NAME,SPORT_FORM_ID);
    private static final String NATIONAL_CUP_ENGLAND_NAME = "National Cup England Test";
    private static final Integer NATIONAL_CUP_ENGLAND_ID = 99999997;

    TournamentModel ukraineBasketCup = new TournamentModel(BASKET_TOURNAMENT_ID, CATEGORY_UKRAINE_ID_BASKETBALL,BASKET_TOURNAMENT_NAME,BASKET_SPORT_FORM_ID);
    private static final String BASKET_TOURNAMENT_NAME = "Ukraine Basket Cup Test";
    private static final Integer BASKET_TOURNAMENT_ID = 99999996;
    private static final Integer BASKET_SPORT_FORM_ID = 2;

    private static final String PARTIAL_NAME_OF_TOURNAMENT = "Ukraine";

    private static final String INCORRECT_TOURNAMENT_NAME = "Bla Bla Bla";
    private static final Integer INCORRECT_SPORT_ID = 123456654;
    private static final Integer INCORRECT_CATEGORY_ID = 999999999;

    private static final String URL_ENDPOINT = "/tournament:search";

    CreateTournamentResponse.Periods firstHalf = new CreateTournamentResponse.Periods(2, 1, 2700);
    CreateTournamentResponse.Periods breakTime = new CreateTournamentResponse.Periods(68, 2, 0);
    CreateTournamentResponse.Periods secondHalf = new CreateTournamentResponse.Periods(3, 3, 2700);

    public SearchTournamentTest() throws SQLException {
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
        sportDAO.createNewSport(sportBasketball);
        categoryDAO.createCategory(categoryUkraineFootball);
        categoryDAO.createCategory(categoryEnglandFootball);
        categoryDAO.createCategory(categoryUkraineBasket);
        tournamentDAO.createTournament(superLeague);
        tournamentDAO.createTournament(premierLeague);
        tournamentDAO.createTournament(nationalCupEngland);
        tournamentDAO.createTournament(ukraineBasketCup);

    }

    @AfterClass
    void deleteAll(){
        tournamentDAO.delete(categoryUkraineFootball.getId());
        tournamentDAO.delete(categoryEnglandFootball.getId());
        tournamentDAO.delete(categoryUkraineBasket.getId());
        categoryDAO.delete(categoryUkraineFootball.getId());
        categoryDAO.delete(categoryEnglandFootball.getId());
        categoryDAO.delete(categoryUkraineBasket.getId());
        sportDAO.delete(sportFootball.getId());
        sportDAO.delete(sportBasketball.getId());
    }


    @Test(testName = "Search tournament with used all fields in request")
    public void search_FullFilter_ExpectedOneEntity() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchTournamentRequest searchTournament = new SearchTournamentRequest.FilterBuilderImpl()
                .setName(TOURNAMENT_SUPER_LEAGUE_NAME)
                .setCategoryId(CATEGORY_UKRAINE_ID_FOOTBALL)
                .setSportId(SPORT_FOOTBALL_ID)
                .build();
        SearchTournamentResponse response = given()
                .body(searchTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_SUPER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_PREMIER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),NATIONAL_CUP_ENGLAND_NAME)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getMappedId(), CATEGORY_UKRAINE_ID_FOOTBALL)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getName(), CATEGORY_NAME_UKRAINE)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getRegionId(),REGION_ID)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getName(), SPORT_FOOTBALL_NAME)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(), SPORT_FOOTBALL_ID)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getSportFormBoDto().getPeriods(),getExpectedSportForm())));



    }



    @Test(testName = "Search tournament with used categoryId and sportId")
    public void search_AllTournamentsByCategoryIdAndSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchTournamentRequest searchTournament = new SearchTournamentRequest.FilterBuilderImpl()
                .setCategoryId(CATEGORY_UKRAINE_ID_FOOTBALL)
                .setSportId(SPORT_FOOTBALL_ID)
                .build();

        SearchTournamentResponse response = given()
                .body(searchTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_PREMIER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_SUPER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getMappedId(), CATEGORY_UKRAINE_ID_FOOTBALL)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getName(), CATEGORY_NAME_UKRAINE)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getRegionId(),REGION_ID)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getName(), SPORT_FOOTBALL_NAME)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(), SPORT_FOOTBALL_ID)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getSportFormBoDto().getPeriods(),getExpectedSportForm())));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),NATIONAL_CUP_ENGLAND_NAME)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getCategory().getName(), CATEGORY_NAME_ENGLAND_FOOTBALL)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getCategory().getRegionId(),ENGLAND_REGION_ID)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(), SPORT_BASKETBALL_ID)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getCategory().getMappedId(), CATEGORY_UKRAINE_ID_BASKETBALL)));

    }


    @Test(testName = "Search tournament with used categoryId")
    public void search_AllTournamentsByCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchTournamentRequest searchTournament = new SearchTournamentRequest.FilterBuilderImpl()
                .setCategoryId(CATEGORY_UKRAINE_ID_FOOTBALL)
                .build();

        SearchTournamentResponse response = given()
                .body(searchTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_PREMIER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_SUPER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getMappedId(), CATEGORY_UKRAINE_ID_FOOTBALL)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getName(), CATEGORY_NAME_UKRAINE)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getRegionId(),REGION_ID)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(), SPORT_FOOTBALL_ID)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getSportFormBoDto().getPeriods(),getExpectedSportForm())));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),NATIONAL_CUP_ENGLAND_NAME)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getCategory().getName(), CATEGORY_NAME_ENGLAND_FOOTBALL)));

        }



    @Test(testName = "Search by the full name of the tournament")
    public void search_ByFullTournamentNameExpectedOneItems() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchTournamentRequest searchTournament = new SearchTournamentRequest.FilterBuilderImpl()
                .setName(TOURNAMENT_PREMIER_LEAGUE_NAME)
                .build();

        SearchTournamentResponse response = given()
                .body(searchTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_PREMIER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getName(), CATEGORY_NAME_UKRAINE)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getRegionId(),REGION_ID)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(),SPORT_FOOTBALL_ID)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getSportFormBoDto().getPeriods(),getExpectedSportForm())));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),BASKET_TOURNAMENT_NAME)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_SUPER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),NATIONAL_CUP_ENGLAND_NAME)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getCategory().getName(), CATEGORY_NAME_ENGLAND_FOOTBALL)));

    }

    @Test(testName = "Search by partial name of the tournament")
    public void search_ByPartialNameOfTournamentName_ExpectedAllMatchesByName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchTournamentRequest searchTournament = new SearchTournamentRequest.FilterBuilderImpl()
                .setName(PARTIAL_NAME_OF_TOURNAMENT)
                .build();

        SearchTournamentResponse response = given()
                .body(searchTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_PREMIER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_SUPER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),BASKET_TOURNAMENT_NAME)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),NATIONAL_CUP_ENGLAND_NAME)));


    }

    @Test(testName = "Search tournament with used sportId")
    public void search_AllTournamentsBySportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchTournamentRequest searchTournament = new SearchTournamentRequest.FilterBuilderImpl()
                .setSportId(SPORT_FOOTBALL_ID)
                .build();
        SearchTournamentResponse response = given()
                .body(searchTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_PREMIER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_SUPER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),NATIONAL_CUP_ENGLAND_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getCategory().getMappedId(), CATEGORY_UKRAINE_ID_FOOTBALL)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getCategory().getMappedId(), CATEGORY_ENGLAND_ID_FOOTBALL)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getCategory().getName(), CATEGORY_NAME_UKRAINE)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(), SPORT_FOOTBALL_ID)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getSportFormBoDto().getPeriods(),getExpectedSportForm())));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(), SPORT_BASKETBALL_ID)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getCategory().getName(), BASKET_TOURNAMENT_NAME)));

    }

    @Test(testName = "Search tournament with used sportID and partial name")
    public void search_AllTournamentsBySportIdName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchTournamentRequest searchTournament = new SearchTournamentRequest.FilterBuilderImpl()
                .setSportId(SPORT_FOOTBALL_ID)
                .setName(PARTIAL_NAME_OF_TOURNAMENT)
                .build();

        SearchTournamentResponse response = given()
                .body(searchTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_PREMIER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_SUPER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(), SPORT_FOOTBALL_ID)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),NATIONAL_CUP_ENGLAND_NAME)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(), SPORT_BASKETBALL_ID)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),BASKET_TOURNAMENT_NAME)));


    }

    @Test(testName = "Search tournament with used categoryID and partial name")
    public void search_AllTournamentsByCategoryIdAndPartialName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchTournamentRequest searchTournament = new SearchTournamentRequest.FilterBuilderImpl()
                .setCategoryId(CATEGORY_UKRAINE_ID_FOOTBALL)
                .setName(PARTIAL_NAME_OF_TOURNAMENT)
                .build();

        SearchTournamentResponse response = given()
                .body(searchTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_PREMIER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_SUPER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getMappedId(), CATEGORY_UKRAINE_ID_FOOTBALL)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getCategory().getRegionId(), REGION_ID)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),NATIONAL_CUP_ENGLAND_NAME)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(), SPORT_BASKETBALL_ID)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(),BASKET_TOURNAMENT_NAME)));


    }



    @Test(testName = "Search tournament with used empty filter")
    public void search_AllTournamentsByEmptyFilterUseSorting() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SortingRequest sortingRequest = new SortingRequest.SortingBuilderImpl()
                .setDirection("DESC")
                .setFieldName("id")
                .setOrder(1)
                .build();
        SearchTournamentResponse response = given()
                .body(sortingRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_SUPER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),TOURNAMENT_PREMIER_LEAGUE_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),NATIONAL_CUP_ENGLAND_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),BASKET_TOURNAMENT_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(), SPORT_BASKETBALL_ID)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getCategory().getSport().getMappedId(), SPORT_FOOTBALL_ID)));

    }


    @Test(testName = "Search tournament with used incorrect sportId")
    public void search_AllTournamentsByIncorrectSportId_ExpectedEmptyResponse() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchTournamentRequest searchTournament = new SearchTournamentRequest.FilterBuilderImpl()
                .setSportId(INCORRECT_SPORT_ID)
                .setName(TOURNAMENT_PREMIER_LEAGUE_NAME)
                .setCategoryId(CATEGORY_UKRAINE_ID_FOOTBALL)
                .build();

        SearchTournamentResponse response = given()
                .body(searchTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertTrue(response.getData().isEmpty());

    }

    @Test(testName = "Search tournament with used incorrect categoryID")
    public void search_AllTournamentsByIncorrectCategoryId_ExpectedEmptyResponse() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchTournamentRequest searchTournament = new SearchTournamentRequest.FilterBuilderImpl()
                .setSportId(SPORT_FOOTBALL_ID)
                .setName(TOURNAMENT_PREMIER_LEAGUE_NAME)
                .setCategoryId(INCORRECT_CATEGORY_ID)
                .build();

        SearchTournamentResponse response = given()
                .body(searchTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertTrue(response.getData().isEmpty());

    }

    @Test(testName = "Search tournament with used incorrect tournament name")
    public void search_AllTournamentsByIncorrectTournamentName_ExpectedEmptyResponse() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchTournamentRequest searchTournament = new SearchTournamentRequest.FilterBuilderImpl()
                .setSportId(SPORT_FOOTBALL_ID)
                .setName(INCORRECT_TOURNAMENT_NAME)
                .setCategoryId(CATEGORY_UKRAINE_ID_FOOTBALL)
                .build();

        SearchTournamentResponse response = given()
                .body(searchTournament)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchTournamentResponse.class);


        Assert.assertTrue(response.getData().isEmpty());

    }



}