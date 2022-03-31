package category;

import jdbc.CategoryDAO;
import model.MappingModel;
import org.testng.annotations.BeforeTest;
import requestDto.category.MapRequest;
import responsDto.category.UnmapCategoryResponse;
import config.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class UnmapCategoryTest {

    private static final String EXTERNAL_ID = "absolute:1:Ethiopia";
    private static final Integer CATEGORY_ID = 171;
    private static final String PROVIDER = "absolute";
    private static final Integer NON_EXISTENT_CATEGORY_ID = 99999;
    private static final String NON_EXISTENT_EXTERNAL_ID = "absolute:1:Hogvards";
    private static final Integer REGION_ID = 141;
    private static final String CATEGORY_NAME = "Ethiopia";

    private static final String URL_ENDPOINT = "/category:unmap";
    CategoryDAO categoryDAO = new CategoryDAO();
    MappingModel createMapping = new MappingModel(EXTERNAL_ID,CATEGORY_ID,PROVIDER,CATEGORY_NAME);

    public UnmapCategoryTest() throws SQLException {
    }

    @BeforeTest
    void setCreateMapping(){
        categoryDAO.create(createMapping);
    }

    @Test(testName = "Unmap category and checking not present entity in db")
    public void unmap_Category() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest(EXTERNAL_ID, CATEGORY_ID, PROVIDER);
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
        MapRequest postRequest = new MapRequest(EXTERNAL_ID,NON_EXISTENT_CATEGORY_ID,PROVIDER);
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
        MapRequest postRequest = new MapRequest(NON_EXISTENT_EXTERNAL_ID,CATEGORY_ID,PROVIDER);
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