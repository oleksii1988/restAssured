package category;
import requestDto.category.CreateCategoryRequest;
import responsDto.CreateCategoryResponse;
import config.Specifications;
import jdbc.CategoryDAO;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class CreateCategoryTest {



    private static final String KNOWN_CATEGORY_NAME = "Ukraine";
    private static final String UNKNOWN_CATEGORY_NAME = "Hogwards";
    private static final Integer KNOWN_REGION_ID = 30;
    private static final Integer SPORT_ID = 9;
    private static final String SPORT_NAME = "Horse Racing";
    private static final Integer NONEXISTENT_SPORT_ID = 99;
    private static final String EXISTING_CATEGORY_NAME = "France";

    private static final String URL_ENDPOINT = "/category:create";
    CategoryDAO categoryDAO = new CategoryDAO();
    public CreateCategoryTest() throws SQLException {
    }


    @Test(testName = "Creating new category and checking generate mapped_id and generate new region_id",priority = 1)
    public void creating_NewCategory_WithUnknownCategoryName()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateCategoryRequest postRequest = new CreateCategoryRequest(new CreateCategoryRequest.Category(UNKNOWN_CATEGORY_NAME, SPORT_ID));
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
        Assert.assertEquals(response.getName(), UNKNOWN_CATEGORY_NAME);
        Assert.assertEquals(response.getMappedId(),categoryDAO.getCategoryInDB(response.getMappedId()).getId());
        Assert.assertEquals(response.getName(),categoryDAO.getCategoryInDB(response.getMappedId()).getName());
        Assert.assertEquals(response.getRegionId(),categoryDAO.getCategoryInDB(response.getMappedId()).getRegion_id());
        Assert.assertEquals(response.getSport().getMappedId(),categoryDAO.getCategoryInDB(response.getMappedId()).getSport_id());
        categoryDAO.delete(response.getMappedId());

    }


    @Test(testName = "Creating a new category with a known regionId")
    public void creating_NewCategoryWithKnownName()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateCategoryRequest postRequest = new CreateCategoryRequest(new CreateCategoryRequest.Category(KNOWN_CATEGORY_NAME, SPORT_ID));
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMappedId());
        Assert.assertEquals(response.getRegionId(),KNOWN_REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID);
        Assert.assertEquals(response.getSport().getName(), SPORT_NAME);
        Assert.assertEquals(response.getName(), KNOWN_CATEGORY_NAME);
        Assert.assertEquals(response.getMappedId(),categoryDAO.getCategoryInDB(response.getMappedId()).getId());
        Assert.assertEquals(response.getName(),categoryDAO.getCategoryInDB(response.getMappedId()).getName());
        Assert.assertEquals(response.getRegionId(),categoryDAO.getCategoryInDB(response.getMappedId()).getRegion_id());
        Assert.assertEquals(response.getSport().getMappedId(),categoryDAO.getCategoryInDB(response.getMappedId()).getSport_id());
        categoryDAO.delete(response.getMappedId());

    }



    @Test(testName = "Creating new category with sport_id null expected code 400")
    public void create_NewCategory_WhenSportIdIsNull_expectedResponseCode400() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        CreateCategoryRequest postRequest = new CreateCategoryRequest(new CreateCategoryRequest.Category(KNOWN_CATEGORY_NAME));
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
        CreateCategoryRequest postRequest = new CreateCategoryRequest(new CreateCategoryRequest.Category(KNOWN_CATEGORY_NAME,NONEXISTENT_SPORT_ID));
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

    @Test(testName = "Creating a category that already exists",priority = 2)
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