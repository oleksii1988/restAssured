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
import requestDto.category.SearchRequest;
import responsDto.participant.SearchParticipantResponse;
import java.sql.SQLException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class SearchParticipantTest {


    SportDAO sportDAO = new SportDAO();
    SportModel sportFootball = new SportModel(SPORT_FOOTBALL_ID, SPORT_FOOTBALL_NAME);
    private static final Integer SPORT_FOOTBALL_ID = 9999;
    private static final String SPORT_FOOTBALL_NAME = "Football Test";
    private static final Integer NONEXISTENT_SPORT_ID = 987789987;

    SportModel sportBasketball = new SportModel(SPORT_BASKETBALL_ID, SPORT_BASKETBALL_NAME);
    private static final Integer SPORT_BASKETBALL_ID = 9998;
    private static final String SPORT_BASKETBALL_NAME = "Basketball Test";

    CategoryDAO categoryDAO = new CategoryDAO();
    CategoryModel categoryUkraineFootball = new CategoryModel(CATEGORY_UKRAINE_ID_FOOTBALL, CATEGORY_NAME_UKRAINE, SPORT_FOOTBALL_ID, UKRAINE_REGION_ID);
    private static final String CATEGORY_NAME_UKRAINE = "Ukraine Test";
    private static final Integer CATEGORY_UKRAINE_ID_FOOTBALL = 10000;
    private static final Integer UKRAINE_REGION_ID = 30;

    CategoryModel categoryEnglandFootball = new CategoryModel(CATEGORY_ENGLAND_ID_FOOTBALL, CATEGORY_NAME_ENGLAND, SPORT_FOOTBALL_ID, ENGLAND_REGION_ID);
    private static final String CATEGORY_NAME_ENGLAND = "England Test";
    private static final Integer CATEGORY_ENGLAND_ID_FOOTBALL = 20000;
    private static final Integer ENGLAND_REGION_ID = 6;

    CategoryModel categoryUSABasketball = new CategoryModel(CATEGORY_USA_ID_BASKETBALL, CATEGORY_NAME_USA_BASKETBALL, SPORT_BASKETBALL_ID, USA_REGION_ID);
    private static final String CATEGORY_NAME_USA_BASKETBALL = "United State Test";
    private static final Integer CATEGORY_USA_ID_BASKETBALL = 30000;
    private static final Integer USA_REGION_ID = 57;
    private static final Integer NONEXISTENT_CATEGORY_ID = 989898989;


    ParticipantDAO participantDAO = new ParticipantDAO();
    ParticipantModel participantModelDynamo = new ParticipantModel(DYNAMO_ID_FOOTBALL, PARTICIPANT_NAME_DYNAMO, COUNTRY_DYNAMO, SPORT_FOOTBALL_ID, CATEGORY_UKRAINE_ID_FOOTBALL);
    private static final Integer DYNAMO_ID_FOOTBALL = 444444444;
    private static final String PARTICIPANT_NAME_DYNAMO = "FC Dynamo Kiev Test";
    private static final String PARTIAL_NAME_D = "FC Dynamo";
    private static final String COUNTRY_DYNAMO = "UA";

    ParticipantModel participantModelLiverpool = new ParticipantModel(LIVERPOOL_ID_FOOTBALL, PARTICIPANT_NAME_LIVERPOOL, COUNTRY_LIVERPOOL, SPORT_FOOTBALL_ID, CATEGORY_ENGLAND_ID_FOOTBALL);
    private static final Integer LIVERPOOL_ID_FOOTBALL = 555555555;
    private static final String PARTICIPANT_NAME_LIVERPOOL = "FC Liverpool Test";
    private static final String COUNTRY_LIVERPOOL = "GB";

    ParticipantModel participantModelLakers = new ParticipantModel(LAKERS_ID_BASKETBALL, PARTICIPANT_NAME_LAKERS, COUNTRY_LAKERS, SPORT_BASKETBALL_ID, CATEGORY_USA_ID_BASKETBALL);
    private static final Integer LAKERS_ID_BASKETBALL = 77777777;
    private static final String PARTICIPANT_NAME_LAKERS = "Los Angels Lakers Test";
    private static final String COUNTRY_LAKERS = "US";


    private static final String URL_ENDPOINT = "/participant:search";


    public SearchParticipantTest() throws SQLException {
    }

    @BeforeClass
    void createSportCategoryTournamentForTest() {
        sportDAO.createNewSport(sportFootball);
        sportDAO.createNewSport(sportBasketball);
        categoryDAO.createCategory(categoryUkraineFootball);
        categoryDAO.createCategory(categoryEnglandFootball);
        categoryDAO.createCategory(categoryUSABasketball);
        participantDAO.createParticipant(participantModelDynamo);
        participantDAO.createParticipant(participantModelLiverpool);
        participantDAO.createParticipant(participantModelLakers);


    }

    @AfterClass
    void deleteAll() {
        participantDAO.delete(participantModelLakers.getId());
        participantDAO.delete(participantModelLiverpool.getId());
        participantDAO.delete(participantModelDynamo.getId());
        categoryDAO.delete(categoryUkraineFootball.getId());
        categoryDAO.delete(categoryEnglandFootball.getId());
        categoryDAO.delete(categoryUSABasketball.getId());
        sportDAO.delete(sportFootball.getId());
        sportDAO.delete(sportBasketball.getId());

    }


    @Test(testName = "Searching by participant use all fields by filter")
    public void search_participantUseAllFieldsExpectedResponseTheOneItems() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest searchRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setCategoryId(CATEGORY_UKRAINE_ID_FOOTBALL)
                .setName(PARTICIPANT_NAME_DYNAMO)
                .setSportId(SPORT_FOOTBALL_ID)
                .build();

        SearchParticipantResponse responseParticipant = given()
                .body(searchRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchParticipantResponse.class);
        SearchParticipantResponse.DataType response = responseParticipant.getData().get(0);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getName(), PARTICIPANT_NAME_DYNAMO);
        Assert.assertEquals(response.getMappedId(), DYNAMO_ID_FOOTBALL);
        Assert.assertEquals(response.getCountry(), Country.UA);

    }

    @Test(testName = "Searching by participant use partial participant name")
    public void search_participantUsePartialParticipantName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest searchRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setName(PARTIAL_NAME_D)
                .build();

        SearchParticipantResponse response = given()
                .body(searchRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchParticipantResponse.class);


        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(), PARTICIPANT_NAME_DYNAMO)));


        for (int i = 0; i < response.getData().size(); i++) {

            SearchParticipantResponse.DataType responseForLoop = response.getData().get(0);

            Assert.assertTrue(responseForLoop.getTranslations().containsValue("FK Dinamo Kiyev"));
            Assert.assertEquals(responseForLoop.getCountry(),Country.UA);
        }

    }

    @Test(testName = "Searching by participant use sportId and categoryId")
    public void search_participantUseSportIdCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest searchRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setSportId(SPORT_FOOTBALL_ID)
                .setCategoryId(CATEGORY_ENGLAND_ID_FOOTBALL)
                .build();

        SearchParticipantResponse response = given()
                .body(searchRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchParticipantResponse.class);


        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(), PARTICIPANT_NAME_DYNAMO)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(), PARTICIPANT_NAME_LAKERS)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getName(), PARTICIPANT_NAME_LIVERPOOL)));

    }


    @Test(testName = "Searching by participant use sportId")
    public void search_participantUseSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest searchRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setSportId(SPORT_BASKETBALL_ID)
                .build();

        SearchParticipantResponse response = given()
                .body(searchRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchParticipantResponse.class);


        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getName(), PARTICIPANT_NAME_LAKERS)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(), PARTICIPANT_NAME_DYNAMO)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(), PARTICIPANT_NAME_LIVERPOOL)));

    }

    @Test(testName = "Searching by participant use categoryId")
    public void search_participantUseCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest searchRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setCategoryId(CATEGORY_UKRAINE_ID_FOOTBALL)
                .build();

        SearchParticipantResponse response = given()
                .body(searchRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchParticipantResponse.class);


        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getName(), PARTICIPANT_NAME_DYNAMO)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(), PARTICIPANT_NAME_LIVERPOOL)));
        Assert.assertTrue(response.getData().stream().noneMatch(dataType -> Objects.equals(dataType.getName(), PARTICIPANT_NAME_LAKERS)));

    }

    @Test(testName = "Searching by participant use nonexistent categoryId")
    public void search_participantUseNoneExistentCategoryId_ExpectedEmptyResponse() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest searchRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setCategoryId(NONEXISTENT_CATEGORY_ID)
                .setSportId(SPORT_FOOTBALL_ID)
                .build();

        SearchParticipantResponse response = given()
                .body(searchRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchParticipantResponse.class);


        Assert.assertTrue(response.getData().isEmpty());
    }


    @Test(testName = "Searching by participant use nonexistent sportId")
    public void search_participantUseNoneExistentSportId_ExpectedEmptyResponse() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest searchRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setCategoryId(CATEGORY_UKRAINE_ID_FOOTBALL)
                .setSportId(NONEXISTENT_SPORT_ID)
                .build();

        SearchParticipantResponse response = given()
                .body(searchRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchParticipantResponse.class);


        Assert.assertTrue(response.getData().isEmpty());
    }


}
