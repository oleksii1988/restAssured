package category;

import jdbc.SportDAO;
import modelDB.CategoryModel;
import modelDB.SportModel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import requestDto.category.MapRequest;
import responsDto.category.CreateCategoryResponse;
import config.Specifications;
import jdbc.CategoryDAO;
import modelDB.MappingModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class MapCategoryTest {


    SportDAO sportDAO = new SportDAO();
    SportModel sportFootball = new SportModel(SPORT_ID, SPORT_NAME);
    private static final Integer SPORT_ID = 9999;
    private static final String SPORT_NAME = "Football Test";

    CategoryDAO categoryDAO = new CategoryDAO();
    CategoryModel categoryForTest = new CategoryModel(CATEGORY_ID,CATEGORY_NAME, SPORT_ID, REGION_ID);
    private static final Integer CATEGORY_ID = 10000;
    private static final Integer REGION_ID = 300000;
    private static final String CATEGORY_NAME = "Ukraine";
    private static final Integer NON_EXISTENT_CATEGORY_ID = 99999;

    private static final String EXTERNAL_ID = "lsport:687888:12345";
    private static final String PROVIDER = "lsport";

    private static final String URL_ENDPOINT = "/category:map";

    public MapCategoryTest() throws SQLException {
    }

    // TODO: Checking in the database of a mapped category from several providers

    @BeforeClass
    void createSportAndCategory() {
        sportDAO.createNewSport(sportFootball);
        categoryDAO.createCategory(categoryForTest);

    }

    @AfterClass

    void deleteSportAndCategory() {
        categoryDAO.delete(categoryForTest.getId());
        sportDAO.delete(sportFootball.getId());

    }

    @Test(testName = "Mapping new category with flag single is FALSE and checking create entity in db")
    public void map_NewCategory_WithSingleFalse_EntityMustBeCreatedIndDB() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(CATEGORY_ID)
                .setProvider(PROVIDER)
                .setSingle(false)
                .build();

        MappingModel mappingModel = new MappingModel(EXTERNAL_ID, CATEGORY_ID, PROVIDER, CATEGORY_NAME);
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), CATEGORY_ID);
        Assert.assertEquals(response.getRegionId(), REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID);
        Assert.assertEquals(response.getSport().getName(), SPORT_NAME);
        Assert.assertEquals(response.getName(), CATEGORY_NAME);
        Assert.assertTrue(categoryDAO.getMap(response.getMappedId()));
        Assert.assertEquals(mappingModel, categoryDAO.getMapCategory(response.getMappedId()));
        categoryDAO.deleteMapping(CATEGORY_ID);

    }


    @Test(testName = "Mapping new category with flag single is TRUE and entity must not be created in the db")
    public void map_NewCategory_WithSingleTrue_EntityMustNotBeCreatedIndDB()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(CATEGORY_ID)
                .setProvider(PROVIDER)
                .setSingle(true)
                .build();
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),CATEGORY_ID);
        Assert.assertEquals(response.getRegionId(),REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID);
        Assert.assertEquals(response.getSport().getName(), SPORT_NAME);
        Assert.assertEquals(response.getName(),CATEGORY_NAME);
        Assert.assertFalse(categoryDAO.getMap(response.getMappedId()));
        categoryDAO.deleteMapping(CATEGORY_ID);

    }

    @Test(testName = "Mapping category with non-existent category_id")
    public void map_Category_WithNonExistentCategoryId_ExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(NON_EXISTENT_CATEGORY_ID)
                .setProvider(PROVIDER)
                .setSingle(false)
                .build();
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

    @Test(testName = "Map category when field external_id not present in request")
    public void map_Category_WithoutFieldExternalId_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setMappedId(CATEGORY_ID)
                .setProvider(PROVIDER)
                .setSingle(true)
                .build();

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

    @Test(testName = "Map category when field mapped_id not present in request")
    public void map_Category_WithoutFieldMappedId_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setProvider(PROVIDER)
                .setSingle(true)
                .build();

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

    @Test(testName = "Map category when field provider not present in request")
    public void map_Category_WithoutFieldProvider_ExpectedCode400()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(CATEGORY_ID)
                .setSingle(true)
                .build();

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
