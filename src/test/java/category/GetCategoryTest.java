package category;

import config.Specifications;
import jdbc.CategoryDAO;
import jdbc.SportDAO;
import modelDB.CategoryModel;
import modelDB.SportModel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import responsDto.category.CreateCategoryResponse;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class GetCategoryTest {



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

    private static final String URL_ENDPOINT = "/category/";

    public GetCategoryTest() throws SQLException {
    }

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

    @Test(testName = "Get category by id and check categoryId, categoryName, regionId, sportId, sport_name")
    public void get_CategoryById()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateCategoryResponse response = given()
                .when()
                .get(URL_ENDPOINT+CATEGORY_ID)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getRegionId(),REGION_ID);
        Assert.assertEquals(response.getMappedId(),CATEGORY_ID);
        Assert.assertEquals(response.getSport().getMappedId(),SPORT_ID);
        Assert.assertEquals(response.getName(),CATEGORY_NAME);
        Assert.assertEquals(response.getSport().getName(),SPORT_NAME);

    }

    @Test(testName = "Get category by invalidate categoryId")
    public void get_CategoryByInvalidateCategoryId_ExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        CreateCategoryResponse response = given()
                .when()
                .get(URL_ENDPOINT+NON_EXISTENT_CATEGORY_ID)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNull(response.getRegionId());
        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getSport());
        Assert.assertNull(response.getName());

    }

}
