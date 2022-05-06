package sport;

import config.Specifications;
import dto.SortingRequest;
import jdbc.SportDAO;
import modelDB.SportModel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestDto.category.SearchRequest;
import responsDto.sport.SearchSportResponse;

import java.sql.SQLException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class SearchSportTest {



    private static final String UNKNOWN_SPORT_NAME = "Litrball";
    private static final Integer SPORT_ID = 9999;
    private static final String SPORT_NAME = "Football Test";
    private static final String PARTIAL_SPORT_NAME = "Foot";
    SportModel sportFootball = new SportModel(SPORT_ID, SPORT_NAME);

    private static final String URL_ENDPOINT = "/sport:search";

    SportDAO sportDAO = new SportDAO();



    public SearchSportTest() throws SQLException {
    }

    @BeforeClass
    void createSportCategoryTournamentForTest() {
        sportDAO.createNewSport(sportFootball);

    }

    @AfterClass
    void deleteAll(){
        sportDAO.delete(sportFootball.getId());

    }


    @Test(testName = "Checking filter by sport name")
    public void search_SportName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setName(SPORT_NAME)
                .build();
        SearchSportResponse responseSport = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchSportResponse.class);
        SearchSportResponse.DataType response = responseSport.getData().get(0);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getName(), SPORT_NAME);
        Assert.assertEquals(response.getMappedId(), SPORT_ID);
    }

    @Test(testName = "Checking filter by partial sport name")
    public void search_byPartialSportName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setName(PARTIAL_SPORT_NAME)
                .build();
        SearchSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchSportResponse.class);

        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),SPORT_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getMappedId(),SPORT_ID)));
    }

    @Test(testName = "Checking filter is empty")
    public void search_SportNameWhenFilterIsEmpty_SearchSportFootball() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
                .build();
        SearchSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchSportResponse.class);


        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().anyMatch(data -> Objects.equals(data.getName(), SPORT_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(data -> Objects.equals(data.getMappedId(), SPORT_ID)));

    }

    // TODO Уточнить у Вани или Леши почему сортировка не работает через автотесттест но работает через сваггер.
    @Test(testName = "Checking sorting when filter is empty")
    public void search_CheckSortingWhenFilterIsEmptySortedById() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SortingRequest sorting = new SortingRequest.SortingBuilderImpl().setFieldName("id").setDirection("DESC").setOrder(1).build();
        SearchSportResponse response = given()
                .body(sorting)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchSportResponse.class);


        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().anyMatch(data -> Objects.equals(data.getName(), SPORT_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(data -> Objects.equals(data.getMappedId(), SPORT_ID)));

    }

   @Test(testName = "Checking filter when unknown name sport")
    public void search_SportUnknownNameWhen_ExpectedEmptyData() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
       SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
               .setName(UNKNOWN_SPORT_NAME)
               .build();
        SearchSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchSportResponse.class);


        Assert.assertTrue(response.getData().isEmpty());

    }
}