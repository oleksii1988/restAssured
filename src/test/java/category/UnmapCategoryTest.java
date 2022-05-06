package category;
import jdbc.CategoryDAO;
import jdbc.SportDAO;
import modelDB.CategoryModel;
import modelDB.MappingModel;
import modelDB.SportModel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import requestDto.category.MapRequest;
import responsDto.category.UnmapCategoryResponse;
import config.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.SQLException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class UnmapCategoryTest {

    private static final String EXTERNAL_ID = "lsport:687888:12345";
    private static final String PROVIDER = "lsport";
    private static final String NON_EXISTENT_EXTERNAL_ID = "absolute:1:Hogvards";


    SportDAO sportDAO = new SportDAO();
    SportModel sportFootball = new SportModel(SPORT_ID, SPORT_NAME);
    private static final Integer SPORT_ID = 9999;
    private static final String SPORT_NAME = "Football Test";

    CategoryDAO categoryDAO = new CategoryDAO();
    CategoryModel categoryForTest = new CategoryModel(CATEGORY_ID,CATEGORY_NAME, SPORT_ID, REGION_ID);
    MappingModel createMapping = new MappingModel(EXTERNAL_ID,CATEGORY_ID,PROVIDER,CATEGORY_NAME);
    private static final Integer CATEGORY_ID = 10000;
    private static final Integer REGION_ID = 300000;
    private static final String CATEGORY_NAME = "Ukraine";
    private static final Integer NON_EXISTENT_CATEGORY_ID = 99999;

    private static final String URL_ENDPOINT = "/category:unmap";


    public UnmapCategoryTest() throws SQLException {
    }

    @BeforeClass
    void createSportAndCategory() {
        sportDAO.createNewSport(sportFootball);
        categoryDAO.createCategory(categoryForTest);
        categoryDAO.createMapping(createMapping);

    }

    @AfterClass

    void deleteSportAndCategory() {
        categoryDAO.delete(categoryForTest.getId());
        sportDAO.delete(sportFootball.getId());

    }

    @Test(testName = "Unmap category and checking not present entity in db")
    public void unmap_Category() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(CATEGORY_ID)
                .setProvider(PROVIDER)
                .build();

        UnmapCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),CATEGORY_ID);
        Assert.assertEquals(response.getName(),CATEGORY_NAME);
        Assert.assertEquals(response.getRegionId(),REGION_ID);
        Assert.assertTrue(response.getProviderMappings().stream()
                .noneMatch((data-> Objects.equals(data.getExternalId(),EXTERNAL_ID))));

    }

    @Test(testName = "Unmap category with unvalidated category_id")
    public void unmap_Category_WithNonExistentCategoryId_ExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(NON_EXISTENT_CATEGORY_ID)
                .setProvider(PROVIDER)
                .build();

        UnmapCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapCategoryResponse.class);

        Assert.assertNull(response.getRegionId());
        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());

    }

    @Test(testName = "Unmap category with unvalidated external_id")
    public void unmap_Category_WithNonExistentExternalId_ExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(NON_EXISTENT_EXTERNAL_ID)
                .setMappedId(CATEGORY_ID)
                .setProvider(PROVIDER)
                .build();

        UnmapCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapCategoryResponse.class);

        Assert.assertNull(response.getRegionId());
        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());

    }

}