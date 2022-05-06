package category;
import com.atlas.common.enums.Language;
import config.Specifications;
import jdbc.CategoryDAO;
import jdbc.SportDAO;
import modelDB.CategoryModel;
import modelDB.SportModel;
import org.testng.Assert;
import org.testng.annotations.*;
import requestDto.category.UpdateCategoryRequest;
import responsDto.category.CreateCategoryResponse;
import java.sql.SQLException;
import java.util.Map;

import static io.restassured.RestAssured.given;



public class UpdateCategoryTest {

    SportDAO sportDAO = new SportDAO();
    SportModel sportFootball = new SportModel(SPORT_ID_FOOTBALL, SPORT_NAME_FOOTBALL);
    private static final Integer SPORT_ID_FOOTBALL = 9999;
    private static final String SPORT_NAME_FOOTBALL = "Football Test";

    SportModel sportBasketball = new SportModel(SPORT_ID_BASKETBALL, SPORT_NAME_BASKETBALL);
    private static final Integer SPORT_ID_BASKETBALL = 9998;
    private static final String SPORT_NAME_BASKETBALL = "Basketball Test";

    SportModel sportTennis = new SportModel(SPORT_ID_TENNIS, SPORT_NAME_TENNIS);
    private static final Integer SPORT_ID_TENNIS = 9997;
    private static final String SPORT_NAME_TENNIS = "Tennis Test";


    private static final Integer MODEL_1_CATEGORY_ID = 1000000;
    private static final Integer MODEL_2_CATEGORY_ID = 1000001;
    private static final Integer MODEL_3_CATEGORY_ID = 1000002;

    private static final Integer UKRAINE_REGION_ID = 11001;
    private static final Integer ENGLAND_REGION_ID = 11000;
    private static final Integer NON_EXISTING_REGION_ID = 11111;

    private static final String UKRAINE_CATEGORY_NAME = "Ukraine Test";
    private static final String ENGLAND_CATEGORY_NAME = "England Test";

    private static final Map<Language, String> UKRAINE_TRANSLATIONS = Map.of
            (Language.RUS, "Украина Тест", Language.UKR, "Україна Тест", Language.POR, "Ucraniana Teste");

    private static final Map<Language, String> ENGLAND_TRANSLATIONS = Map.of
            (Language.RUS, "Англия Тест", Language.UKR, "Англія Тест", Language.POR, "Inglaterra Teste");

    private static final String URL_ENDPOINT = "/category/1000000:update";

    CategoryDAO categoryDAO = new CategoryDAO();


    CategoryModel category1 = new CategoryModel(MODEL_1_CATEGORY_ID,ENGLAND_CATEGORY_NAME,SPORT_ID_FOOTBALL,ENGLAND_REGION_ID);
    CategoryModel category2 = new CategoryModel(MODEL_2_CATEGORY_ID,ENGLAND_CATEGORY_NAME,SPORT_ID_BASKETBALL,ENGLAND_REGION_ID);
    CategoryModel category3 = new CategoryModel(MODEL_3_CATEGORY_ID,"Venera",SPORT_ID_TENNIS,UKRAINE_REGION_ID);



    public UpdateCategoryTest() throws SQLException {
    }


    @BeforeClass
    void createCategoryForTest(){
        sportDAO.createNewSport(sportFootball);
        sportDAO.createNewSport(sportBasketball);
        sportDAO.createNewSport(sportTennis);
        categoryDAO.createCategory(category1);
        categoryDAO.createCategory(category2);
        categoryDAO.createCategory(category3);
    }
    @AfterClass
    void deleteCategory(){
        categoryDAO.delete(MODEL_1_CATEGORY_ID);
        categoryDAO.delete(MODEL_2_CATEGORY_ID);
        categoryDAO.delete(MODEL_3_CATEGORY_ID);
        sportDAO.delete(sportFootball.getId());
        sportDAO.delete(sportBasketball.getId());
        sportDAO.delete(sportTennis.getId());
    }


    @Test(testName = "Updating regionId field")
    public void update_Category_UpdateRegionId()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateCategoryRequest postRequest = new UpdateCategoryRequest.CategoryBuilderImpl()
                .setName(ENGLAND_CATEGORY_NAME)
                .setRegionId(UKRAINE_REGION_ID)
                .setTranslations(ENGLAND_TRANSLATIONS)
                .build();
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),MODEL_1_CATEGORY_ID);
        Assert.assertEquals(response.getRegionId(),UKRAINE_REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID_FOOTBALL);
        Assert.assertEquals(response.getSport().getName(), SPORT_NAME_FOOTBALL);
        Assert.assertEquals(response.getName(), ENGLAND_CATEGORY_NAME);
        Assert.assertEquals(response.getTranslations().get(Language.UKR),"Англія Тест");
        Assert.assertEquals(response.getTranslations().get(Language.POR),"Inglaterra Teste");
        Assert.assertEquals(response.getTranslations().get(Language.RUS),"Англия Тест");
        Assert.assertNotEquals(response.getRegionId(), category1.getRegion_id());

    }


    @Test(testName = "Update name field and validate categories with common region expected:" +
            "Expected result: All categories with a common region_id must change the name")
    public void update_Category_FiledName_CheckInDbChangeName()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateCategoryRequest postRequest = new UpdateCategoryRequest.CategoryBuilderImpl()
                .setName(UKRAINE_CATEGORY_NAME)
                .setRegionId(ENGLAND_REGION_ID)
                .setTranslations(UKRAINE_TRANSLATIONS)
                .build();
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),MODEL_1_CATEGORY_ID);
        Assert.assertEquals(response.getRegionId(),ENGLAND_REGION_ID );
        Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID_FOOTBALL);
        Assert.assertEquals(response.getSport().getName(), SPORT_NAME_FOOTBALL);
        Assert.assertEquals(response.getName(), UKRAINE_CATEGORY_NAME);
        Assert.assertNotEquals(response.getName(), category1.getName());
        Assert.assertEquals(response.getTranslations().get(Language.UKR),"Україна Тест");
        Assert.assertEquals(response.getTranslations().get(Language.POR),"Ucraniana Teste");
        Assert.assertEquals(response.getTranslations().get(Language.RUS),"Украина Тест");
        Assert.assertEquals(response.getName(), categoryDAO.getCategoryInDB(MODEL_2_CATEGORY_ID).getName());

    }

    @Test(testName = "Updating name and regionId fields")
    public void update_Category_UpdateNameAndRegionId()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateCategoryRequest postRequest = new UpdateCategoryRequest.CategoryBuilderImpl()
                .setName(UKRAINE_CATEGORY_NAME)
                .setRegionId(UKRAINE_REGION_ID)
                .setTranslations(UKRAINE_TRANSLATIONS)
                .build();
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),MODEL_1_CATEGORY_ID);
        Assert.assertEquals(response.getRegionId(),UKRAINE_REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID_FOOTBALL);
        Assert.assertEquals(response.getSport().getName(), SPORT_NAME_FOOTBALL);
        Assert.assertEquals(response.getName(), UKRAINE_CATEGORY_NAME);
        Assert.assertEquals(response.getTranslations().get(Language.UKR),"Україна Тест");
        Assert.assertEquals(response.getTranslations().get(Language.POR),"Ucraniana Teste");
        Assert.assertEquals(response.getTranslations().get(Language.RUS),"Украина Тест");
        Assert.assertNotEquals(response.getName(), category1.getName());
    }

    @Test(testName = "Updating non-existing regionId")
    public void update_Category_WhenNonExistingRegionIdExpectedCode412()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec412());
        UpdateCategoryRequest postRequest = new UpdateCategoryRequest.CategoryBuilderImpl()
                .setName(ENGLAND_CATEGORY_NAME)
                .setRegionId(NON_EXISTING_REGION_ID)
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

    @Test(testName = "Updating name field without regionId field")
    public void update_Category_UpdateNameWithoutRegionId()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateCategoryRequest postRequest = new UpdateCategoryRequest.CategoryBuilderImpl()
                .setName(UKRAINE_CATEGORY_NAME)
                .setTranslations(UKRAINE_TRANSLATIONS)
                .build();
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),MODEL_1_CATEGORY_ID);
        Assert.assertEquals(response.getRegionId(),UKRAINE_REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID_FOOTBALL);
        Assert.assertEquals(response.getSport().getName(), SPORT_NAME_FOOTBALL);
        Assert.assertEquals(response.getName(), UKRAINE_CATEGORY_NAME);
        Assert.assertEquals(response.getTranslations().get(Language.UKR),"Україна Тест");
        Assert.assertEquals(response.getTranslations().get(Language.POR),"Ucraniana Teste");
        Assert.assertEquals(response.getTranslations().get(Language.RUS),"Украина Тест");
        Assert.assertNotEquals(response.getName(), category1.getName());
    }



}