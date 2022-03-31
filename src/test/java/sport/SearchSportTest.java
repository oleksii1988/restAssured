package sport;

import config.Specifications;
import dto.SearchSorting;
import org.testng.Assert;
import org.testng.annotations.Test;
import requestDto.category.SearchRequest;
import responsDto.sport.SearchSportResponse;

import java.util.Objects;

import static io.restassured.RestAssured.given;

public class SearchSportTest {

    private static final Integer SPORT_ID = 1;
    private static final String SPORT_NAME = "Football";

    private static final String URL_ENDPOINT = "/sport:search";


    @Test(testName = "Checking filter by sport name")
    public void search_SportName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest(new SearchRequest.Filter(SPORT_NAME));
        SearchSportResponse responseSport = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchSportResponse.class);
        SearchSportResponse.Data response = responseSport.getData().get(0);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getName(), SPORT_NAME);
        Assert.assertEquals(response.getMappedId(), SPORT_ID);
    }

    @Test(testName = "Checking filter is empty")
    public void search_SportNameWhenFilterIsEmpty_SearchSportFootball() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest(new SearchRequest.Filter());
        SearchSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchSportResponse.class);


        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().anyMatch(data -> Objects.equals(data.getName(),SPORT_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(data -> Objects.equals(data.getMappedId(),SPORT_ID)));

    }
 // TODO Уточнить у Вани или Леши почему сортировка не работает через автотесттест но работает через сваггер.
    @Test(testName = "Checking sorting when filter is empty")
    public void search_CheckSortingWhenFilterIsEmptySortedById() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchSorting.Sorting sorting = new SearchSorting.Sorting("ASC","name",0);
        SearchSportResponse response = given()
                .body(sorting)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchSportResponse.class);


        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().anyMatch(data -> Objects.equals(data.getName(),SPORT_NAME)));
        Assert.assertTrue(response.getData().stream().anyMatch(data -> Objects.equals(data.getMappedId(),SPORT_ID)));

    }



}