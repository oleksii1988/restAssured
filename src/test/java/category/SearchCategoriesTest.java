package category;

import com.atlas.common.enums.Language;
import dto.PagingRequest;
import requestDto.category.SearchRequest;
import responsDto.category.SearchCategoryResponse;
import config.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class SearchCategoriesTest {
    private static final String NAME_CATEGORY = "England";
    private static final Integer SPORT_ID = 1;
    private static final Integer REGION_ID = 6;
    private static final String SPORT_NAME = "Football";
    private static final Integer CATEGORY_ID = 325;
    private static final Integer TOTAL_ITEMS = 500;
    private static final String  PARTIAL_CATEGORY_NAME = "Eng";
    private static final Integer INCORRECT_SPORT_ID = 999999;
    private static final String INCORRECT_CATEGORY_NAME = "Bla Bla Bla";





    private static final String URL_ENDPOINT = "/category:search";


    @Test(testName = "Checking filter by category name and sportId")
    public void search_UseNameAndSportId_FullInfoByCategory() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setName(NAME_CATEGORY)
                .setSportId(SPORT_ID)
                .build();
        SearchCategoryResponse responseCategories = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchCategoryResponse.class);
        SearchCategoryResponse.DataType response = responseCategories.getData().get(0);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getName(), NAME_CATEGORY);
        Assert.assertEquals(response.getRegionId(), REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID);
        Assert.assertEquals(response.getSport().getName(), SPORT_NAME);
        Assert.assertEquals(response.getMappedId(), CATEGORY_ID);
    }

    @Test(testName = "Checking a single region_id by category name")
    public void search_whenUseNameAndCheckRegionId_() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setName(NAME_CATEGORY)
                .build();
        SearchCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getRegionId(),REGION_ID)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getName(),NAME_CATEGORY)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getMappedId(),CATEGORY_ID)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getSport().getMappedId(),SPORT_ID)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getSport().getName(),SPORT_NAME)));

        //TODO Разобраться почему приходит null по переводам
        /*for(int i = 0; i<response.getData().size(); i++){
            SearchCategoryResponse.DataType responseForLoop = response.getData().get(i);
            Assert.assertTrue(responseForLoop.getTranslations().entrySet().stream().anyMatch(data-> Objects.equals(data.getValue(),"England")));

        }*/


    }

    @Test(testName = "Search category by partial name search by first 3 characters")
    public void search_categoryByPartialName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setName(PARTIAL_CATEGORY_NAME)
                .build();
        SearchCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(), NAME_CATEGORY)));

    }

    @Test(testName = "Search by incorrect sportId")
    public void search_ByIncorrectSportIdAndValidName_ExpectedResponseIsEmpty() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setName(NAME_CATEGORY)
                .setSportId(INCORRECT_SPORT_ID)
                .build();
        SearchCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchCategoryResponse.class);

        Assert.assertTrue(response.getData().isEmpty());
    }

    @Test(testName = "Search by incorrect category name")
    public void search_ByIncorrectCategoryNameAndValidSportId_ExpectedResponseIsEmpty() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setName(INCORRECT_CATEGORY_NAME)
                .setSportId(SPORT_ID)
                .build();
        SearchCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchCategoryResponse.class);

        Assert.assertTrue(response.getData().isEmpty());
    }

    @Test(testName = "Search category by partial name and sportId search by first 3 characters")
    public void search_categoryByPartialNameAndBySportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setName(PARTIAL_CATEGORY_NAME)
                .setSportId(SPORT_ID)
                .build();
        SearchCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(), NAME_CATEGORY)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getSport().getMappedId(),SPORT_ID)));
    }

    @Test(testName = "Checking filter by sportId")
    public void search_WhenUseSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
                .setSportId(SPORT_ID)
                .build();
        SearchCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getRegionId(),REGION_ID)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),NAME_CATEGORY)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getMappedId(),CATEGORY_ID)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getSport().getMappedId(),SPORT_ID)));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects.equals(dataType.getSport().getName(),SPORT_NAME)));



    }


    @Test(testName = "Checking filter when is empty")
    public void search_WhenFilterIsEmpty() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchRequest postRequest = new SearchRequest.SearchRequestBuilderImpl()
                .build();
        SearchCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchCategoryResponse.class);


        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getRegionId(),REGION_ID)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getName(),NAME_CATEGORY)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getMappedId(),CATEGORY_ID)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getSport().getMappedId(),SPORT_ID)));
        Assert.assertTrue(response.getData().stream().anyMatch(dataType -> Objects.equals(dataType.getSport().getName(),SPORT_NAME)));


    }

    @Test(testName = "Checking items count per page")
    public void search_WhenFilterIsEmptyCheckingCountPerPage() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        PagingRequest postRequest = new PagingRequest.PagingBuilderImpl()
                .setItemsPerPage(1000)
                .setTotalItems(TOTAL_ITEMS)
                .build();

        SearchCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchCategoryResponse.class);


        Assert.assertNotNull(response.getData().stream().sorted());
        Assert.assertEquals(response.getData().size(),1000);


    }
}
