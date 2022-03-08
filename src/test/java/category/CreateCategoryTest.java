package category;

import RequestDto.CreateCategoryRequest;
import ResponsDto.CreateCategoryResponse;
import config.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class CreateCategoryTest {

    private static final String NAME_CATEGORY = "Congo";
    private static final Integer SPORT_ID = 9;
    private static final String SPORT_NAME = "Horse Racing";

    private static final String URL_ENDPOINT = "/category:create";

    @Test(testName = "Creating new category and checking generate mapped_id and region_id")
    public void create_newCategory() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responsSpec200());
        CreateCategoryRequest postRequest = new CreateCategoryRequest(new CreateCategoryRequest.Category(NAME_CATEGORY, SPORT_ID));
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMappedId());
        Assert.assertNotNull(response.getRegionId());
        Assert.assertEquals(response.getSport().getMappedId(),SPORT_ID);
        Assert.assertEquals(response.getSport().getName(),SPORT_NAME);
        Assert.assertEquals(response.getName(),NAME_CATEGORY);


    }
}