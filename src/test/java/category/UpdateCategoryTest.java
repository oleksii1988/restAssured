package category;
import config.Specifications;
import jdbc.CategoryDAO;
import model.CategoryModel;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import requestDto.category.UpdateCategoryRequest;
import responsDto.category.CreateCategoryResponse;
import java.sql.SQLException;
import static io.restassured.RestAssured.given;


public class UpdateCategoryTest {

    private static final Integer MODEL_1_SPORT_ID = 9;
    private static final Integer MODEL_2_SPORT_ID = 34;

    private static final Integer MODEL_1_CATEGORY_ID = 1000000;
    private static final Integer MODEL_2_CATEGORY_ID = 1000001;
    private static final Integer MODEL_3_CATEGORY_ID = 1000002;

    private static final String MODEL_1_SPORT_NAME = "Horse Racing";

    private static final Integer VALID_REGION_ID = 11001;
    private static final Integer TEST_REGION_ID = 11000;
    private static final Integer NON_EXISTING_REGION_ID = 11111;

    private static final String VALID_CATEGORY_NAME = "Earth";
    private static final String TEST_CATEGORY_NAME = "Mars";

    private static final String URL_ENDPOINT = "/category/1000000:update";

    CategoryDAO categoryDAO = new CategoryDAO();

    CategoryModel category1 = new CategoryModel(MODEL_1_CATEGORY_ID,TEST_CATEGORY_NAME,MODEL_1_SPORT_ID,TEST_REGION_ID);
    CategoryModel category2 = new CategoryModel(MODEL_2_CATEGORY_ID,TEST_CATEGORY_NAME,MODEL_2_SPORT_ID,TEST_REGION_ID);
    CategoryModel category3 = new CategoryModel(MODEL_3_CATEGORY_ID,"Venera",12,VALID_REGION_ID);


    public UpdateCategoryTest() throws SQLException {
    }


    @BeforeSuite
    void createCategoryForTest(){
        categoryDAO.createCategory(category1);
        categoryDAO.createCategory(category2);
        categoryDAO.createCategory(category3);
    }
    @AfterTest
    void deleteCategory(){
        categoryDAO.delete(MODEL_1_CATEGORY_ID);
        categoryDAO.delete(MODEL_2_CATEGORY_ID);
        categoryDAO.delete(MODEL_3_CATEGORY_ID);
    }


    @Test(testName = "Updating regionId field")
    public void update_Category_UpdateRegionId()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateCategoryRequest postRequest = new UpdateCategoryRequest(new UpdateCategoryRequest.Category(TEST_CATEGORY_NAME, VALID_REGION_ID));
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),MODEL_1_CATEGORY_ID);
        Assert.assertEquals(response.getRegionId(),VALID_REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), MODEL_1_SPORT_ID);
        Assert.assertEquals(response.getSport().getName(), MODEL_1_SPORT_NAME);
        Assert.assertEquals(response.getName(), TEST_CATEGORY_NAME);
        Assert.assertNotEquals(response.getRegionId(), category1.getRegion_id());

    }


    @Test(testName = "Update name field and validate categories with common region expected:" +
            "Expected result: All categories with a common region_id must change the name")
    public void update_Category_FiledName()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateCategoryRequest postRequest = new UpdateCategoryRequest(new UpdateCategoryRequest.Category(VALID_CATEGORY_NAME, TEST_REGION_ID));
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),MODEL_1_CATEGORY_ID);
        Assert.assertEquals(response.getRegionId(),TEST_REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), MODEL_1_SPORT_ID);
        Assert.assertEquals(response.getSport().getName(), MODEL_1_SPORT_NAME);
        Assert.assertEquals(response.getName(), VALID_CATEGORY_NAME);
        Assert.assertNotEquals(response.getName(), category1.getName());
        Assert.assertEquals(response.getName(), categoryDAO.getCategoryInDB(MODEL_2_CATEGORY_ID).getName());

    }

    @Test(testName = "Updating name and regionId fields")
    public void update_Category_UpdateNameAndRegionId()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateCategoryRequest postRequest = new UpdateCategoryRequest(new UpdateCategoryRequest.Category(VALID_CATEGORY_NAME, VALID_REGION_ID));
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),MODEL_1_CATEGORY_ID);
        Assert.assertEquals(response.getRegionId(),VALID_REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), MODEL_1_SPORT_ID);
        Assert.assertEquals(response.getSport().getName(), MODEL_1_SPORT_NAME);
        Assert.assertEquals(response.getName(), VALID_CATEGORY_NAME);
        Assert.assertNotEquals(response.getName(), category1.getName());
    }

    @Test(testName = "Updating non-existing regionId")
    public void update_Category_WhenNonExistingRegionIdExpectedCode()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec412());
        UpdateCategoryRequest postRequest = new UpdateCategoryRequest(new UpdateCategoryRequest.Category(TEST_CATEGORY_NAME, NON_EXISTING_REGION_ID));
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

    @Test(testName = "Updating name field without regionId field")
    public void update_Category_UpdateNameWithoutRegionId()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateCategoryRequest postRequest = new UpdateCategoryRequest(new UpdateCategoryRequest.Category(VALID_CATEGORY_NAME));
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),MODEL_1_CATEGORY_ID);
        Assert.assertEquals(response.getRegionId(),VALID_REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), MODEL_1_SPORT_ID);
        Assert.assertEquals(response.getSport().getName(), MODEL_1_SPORT_NAME);
        Assert.assertEquals(response.getName(), VALID_CATEGORY_NAME);
        Assert.assertNotEquals(response.getName(), category1.getName());
    }


}