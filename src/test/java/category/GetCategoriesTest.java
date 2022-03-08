package category;

import RequestDto.SearchCategoryRequest;
import ResponsDto.SearchCategoryResponse;
import config.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class GetCategoriesTest {
    private static final String NAME_CATEGORY = "England";
    private static final Integer SPORT_ID = 1;
    private static final Integer REGION_ID = 6;
    private static final String SPORT_NAME = "Football";
    private static final Integer CATEGORY_ID = 325;
    private static final Integer PER_PAGE = 15;

    private static final String URL_ENDPOINT = "/category:search";


    @Test(testName = "Checking filter by category name and sportId")
    public void search_UseNameAndSportId_FullInfoByCategory() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responsSpec200());
        SearchCategoryRequest postRequest = new SearchCategoryRequest(new SearchCategoryRequest.Filter(NAME_CATEGORY, SPORT_ID));
        SearchCategoryResponse responseCategories = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchCategoryResponse.class);
        SearchCategoryResponse.Data response = responseCategories.getData().get(0);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getName(), NAME_CATEGORY);
        Assert.assertEquals(response.getRegionId(), REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID);
        Assert.assertEquals(response.getSport().getName(), SPORT_NAME);
        Assert.assertEquals(response.getMappedId(), CATEGORY_ID);
    }

    @Test(testName = "Checking a single region_id by category name")
    public void search_whenUseNameAndCheckRegionId_() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responsSpec200());
        SearchCategoryRequest postRequest = new SearchCategoryRequest(new SearchCategoryRequest.Filter(NAME_CATEGORY));
        List<SearchCategoryResponse.Data> responseRegionId = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().jsonPath().getList("data", SearchCategoryResponse.Data.class);


        for (int i = 0; i < responseRegionId.size(); i++) {
            SearchCategoryResponse.Data response = responseRegionId.get(i);
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getRegionId(), REGION_ID, String.format("Incorrect regionId in %s category_id %d", response.getSport().getName(), response.getMappedId()));
            Assert.assertEquals(response.getName(), NAME_CATEGORY);
        }

    }

    @Test(testName = "Checking filter by sportId")
    public void search_WhenUseSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responsSpec200());
        SearchCategoryRequest postRequest = new SearchCategoryRequest(new SearchCategoryRequest.Filter(SPORT_ID));
        List<SearchCategoryResponse.Data> sportIdResponse = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().jsonPath().getList("data", SearchCategoryResponse.Data.class);


        for (int i = 0; i < sportIdResponse.size(); i++) {
            SearchCategoryResponse.Data response = sportIdResponse.get(i);
            boolean hasExpectedNameCategory = sportIdResponse.stream()
                    .anyMatch(data -> Objects.equals(data.getName(), NAME_CATEGORY));

            Assert.assertNotNull(response);
            Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID);
            Assert.assertEquals(response.getSport().getName(), SPORT_NAME);
            Assert.assertTrue(hasExpectedNameCategory);
        }

    }


    @Test(testName = "Checking filter when is empty")
    public void search_WhenFilterIsEmpty() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responsSpec200());
        SearchCategoryRequest postRequest = new SearchCategoryRequest(new SearchCategoryRequest.Filter());
        List<SearchCategoryResponse.Data> emptyFilterResponse = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().jsonPath().getList("data", SearchCategoryResponse.Data.class);


        boolean hasResponsеExpectedObjects = emptyFilterResponse.stream()
                .anyMatch(data -> Objects.equals(data.getRegionId(), REGION_ID)
                        && Objects.equals(data.getMappedId(), CATEGORY_ID)
                        && Objects.equals(data.getSport().getName(), SPORT_NAME)
                        && Objects.equals(data.getSport().getMappedId(), SPORT_ID)
                        && Objects.equals(data.getName(), NAME_CATEGORY));
        Assert.assertTrue(hasResponsеExpectedObjects);


    }

    @Test(testName = "Checking items count per page")
    public void search_WhenFilterIsEmptyCheckingCountPerPage() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responsSpec200());
        SearchCategoryRequest postRequest = new SearchCategoryRequest(new SearchCategoryRequest.Paging(null,PER_PAGE,null,null));
        List<SearchCategoryResponse.Data> emptyFilterResponse = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().jsonPath().getList("data", SearchCategoryResponse.Data.class);

        for (int i = 0; i < emptyFilterResponse.size(); i++) {
            SearchCategoryResponse.Data response = emptyFilterResponse.get(i);

            Assert.assertNotNull(response);
            Assert.assertEquals(emptyFilterResponse.size(),PER_PAGE);



        }


    }
}
