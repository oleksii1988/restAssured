package category;
import RequestDto.CreateCategoryRequest;
import ResponsDto.CreateCategoryResponse;
import config.Specifications;
import jdbc.CategoryDAO;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class CreateCategoryTest {



    private static final String NEW_CATEGORY_NAME = "Ukraine";
    private static final Integer SPORT_ID = 9;
    private static final String SPORT_NAME = "Horse Racing";
    private static final Integer NONEXISTENT_SPORT_ID = 99;
    private static final String EXISTING_CATEGORY_NAME = "France";

    private static final String URL_ENDPOINT = "/category:create";
    CategoryDAO categoryDAO = new CategoryDAO();
    public CreateCategoryTest() throws SQLException {
    }

    @Test(testName = "Creating new category and checking generate mapped_id and region_id")
    public void creating_NewCategory()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateCategoryRequest postRequest = new CreateCategoryRequest(new CreateCategoryRequest.Category(NEW_CATEGORY_NAME, SPORT_ID));
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMappedId());
        Assert.assertNotNull(response.getRegionId());
        Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID);
        Assert.assertEquals(response.getSport().getName(), SPORT_NAME);
        Assert.assertEquals(response.getName(), NEW_CATEGORY_NAME);
        categoryDAO.delete(response.getMappedId());



    }



    @Test(testName = "Creating new category with sport_id null expected code 400")
    public void create_NewCategory_WhenSportIdIsNull_expectedResponseCode400() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        CreateCategoryRequest postRequest = new CreateCategoryRequest(new CreateCategoryRequest.Category(NEW_CATEGORY_NAME));
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);
        Assert.assertNull(response.getRegionId());
        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getSport());
        Assert.assertNull(response.getName());


    }

    @Test(testName = "Creating new category with name category is null expected code 400")
    public void create_NewCategory_WhenCategoryNameIsNull_expectedResponseCode400() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        CreateCategoryRequest postRequest = new CreateCategoryRequest(new CreateCategoryRequest.Category(SPORT_ID));
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);
        Assert.assertNull(response.getRegionId());
        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getSport());
        Assert.assertNull(response.getName());


    }

    @Test(testName = "Creating new category when sportId nonexistent expected code 404")
    public void create_NewCategory_WhenSportIdNotFound_expectedResponseCode404() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        CreateCategoryRequest postRequest = new CreateCategoryRequest(new CreateCategoryRequest.Category(NEW_CATEGORY_NAME,NONEXISTENT_SPORT_ID));
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);
        Assert.assertNull(response.getRegionId());
        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getSport());
        Assert.assertNull(response.getName());


    }

    @Test(testName = "Creating a category that already exists")
    public void create_CategoryThatAlreadyExists_expectedResponseCode409() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec409());
        CreateCategoryRequest postRequest = new CreateCategoryRequest(new CreateCategoryRequest.Category(EXISTING_CATEGORY_NAME, SPORT_ID));
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNull(response.getRegionId());
        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getSport());
        Assert.assertNull(response.getName());



    }




}