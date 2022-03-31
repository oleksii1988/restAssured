package category;

import config.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;
import responsDto.category.CreateCategoryResponse;

import static io.restassured.RestAssured.given;

public class GetCategoryTest {

    private static final Integer SPORT_ID = 1;
    private static final Integer CATEGORY_ID = 171;
    private static final String SPORT_NAME = "Football";
    private static final Integer NON_EXISTENT_CATEGORY_ID = 99999;
    private static final Integer REGION_ID = 141;
    private static final String CATEGORY_NAME = "Ethiopia";

    private static final String URL_ENDPOINT = "/category/";




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
