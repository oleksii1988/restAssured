package category;
import com.atlas.common.enums.Language;
import jdbc.SportDAO;
import modelDB.CategoryModel;
import modelDB.SportModel;
import org.testng.annotations.*;
import requestDto.category.CreateCategoryRequest;
import responsDto.category.CreateCategoryResponse;
import config.Specifications;
import jdbc.CategoryDAO;
import org.testng.Assert;


import java.sql.SQLException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateCategoryTest {



    private static final String  KNOWN_CATEGORY_NAME = "Ukraine";
    private static final String  UNKNOWN_CATEGORY_NAME = "Mars";
    private static final Integer REGION_ID = 30;
    private static final Integer NONEXISTENT_SPORT_ID = 99;
    private static final Map<Language, String> TRANSLATIONS_KNOWN_CATEGORY = Map.of
            (Language.RUS, "Украина", Language.UKR, "Україна", Language.POR, "Ucrânia");
    private static final Map<Language, String> TRANSLATIONS_UNKNOWN_CATEGORY = Map.of
            (Language.RUS, "Марс", Language.UKR, "Марс", Language.POR, "Marte");

    SportDAO sportDAO = new SportDAO();
    SportModel sportFootball = new SportModel(SPORT_ID, SPORT_NAME);
    private static final Integer SPORT_ID = 9999;
    private static final String SPORT_NAME = "Football Test";

    private static final String URL_ENDPOINT = "/category:create";

    CategoryDAO categoryDAO = new CategoryDAO();
    CategoryModel categoryForTest = new CategoryModel(CATEGORY_ID,KNOWN_CATEGORY_NAME, SPORT_ID, REGION_ID);
    private static final Integer CATEGORY_ID = 10000;


    public CreateCategoryTest() throws SQLException {
    }

    @BeforeClass
    void createSportForTest() {
        sportDAO.createNewSport(sportFootball);

    }

    @AfterClass

    void deleteSport() {
        sportDAO.delete(sportFootball.getId());

    }

    @Test(testName = "Creating new category with name Ukraine expected generate regionId 30")
    public void creating_NewCategory_WithKnownCategoryName_ExpectedRegionId30()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateCategoryRequest postRequest = new CreateCategoryRequest.CreateTournamentBuilderImpl()
                .setName(KNOWN_CATEGORY_NAME)
                .setSportId(SPORT_ID)
                .setTranslations(TRANSLATIONS_KNOWN_CATEGORY)
                .build();
        CreateCategoryResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(CreateCategoryResponse.class);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMappedId());
        Assert.assertEquals(response.getName(), KNOWN_CATEGORY_NAME);
        Assert.assertEquals(response.getRegionId(),REGION_ID);
        Assert.assertEquals(response.getSport().getMappedId(), SPORT_ID);
        Assert.assertEquals(response.getSport().getName(), SPORT_NAME);
        Assert.assertEquals(response.getMappedId(),categoryDAO.getCategoryInDB(response.getMappedId()).getId());
        Assert.assertEquals(response.getName(),categoryDAO.getCategoryInDB(response.getMappedId()).getName());
        Assert.assertEquals(response.getRegionId(),categoryDAO.getCategoryInDB(response.getMappedId()).getRegion_id());
        Assert.assertEquals(response.getSport().getMappedId(),categoryDAO.getCategoryInDB(response.getMappedId()).getSport_id());
        Assert.assertEquals(response.getTranslations().get(Language.UKR),"Україна");
        Assert.assertEquals(response.getTranslations().get(Language.POR),"Ucrânia");
        Assert.assertEquals(response.getTranslations().get(Language.RUS),"Украина");
        categoryDAO.delete(response.getMappedId());



    }

    @Test(testName = "Creating a new category and check generate new mappedId and regionID")
    public void creating_NewCategoryWithKnownName()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        CreateCategoryRequest postRequest = new CreateCategoryRequest.CreateTournamentBuilderImpl()
                .setName(UNKNOWN_CATEGORY_NAME)
                .setSportId(SPORT_ID)
                .setTranslations(TRANSLATIONS_UNKNOWN_CATEGORY)
                .build();
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
        Assert.assertEquals(response.getTranslations().get(Language.UKR),"Марс");
        Assert.assertEquals(response.getTranslations().get(Language.POR),"Marte");
        Assert.assertEquals(response.getTranslations().get(Language.RUS),"Марс");
        categoryDAO.delete(response.getMappedId());

    }



    @Test(testName = "Creating new category with sport_id null expected code 400")
    public void create_NewCategory_WhenSportIdIsNull_expectedResponseCode400() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        CreateCategoryRequest postRequest = new CreateCategoryRequest.CreateTournamentBuilderImpl()
                .setName(KNOWN_CATEGORY_NAME)
                .setTranslations(TRANSLATIONS_KNOWN_CATEGORY)
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

    @Test(testName = "Creating new category with name category is null expected code 400")
    public void create_NewCategory_WhenCategoryNameIsNull_expectedResponseCode400() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec400());
        CreateCategoryRequest postRequest = new CreateCategoryRequest.CreateTournamentBuilderImpl()
                .setSportId(SPORT_ID)
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

    @Test(testName = "Creating new category when sportId nonexistent expected code 404")
    public void create_NewCategory_WhenSportIdNotFound_expectedResponseCode404() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        CreateCategoryRequest postRequest = new CreateCategoryRequest.CreateTournamentBuilderImpl()
                .setName(KNOWN_CATEGORY_NAME)
                .setSportId(NONEXISTENT_SPORT_ID)
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

    @AfterMethod
    void deleteCategory() {
        categoryDAO.delete(categoryForTest.getId());

    }

    @Test(testName = "Creating a category that already exists")
    public void create_CategoryThatAlreadyExists_expectedResponseCode409() {
        categoryDAO.createCategory(categoryForTest);
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec409());
        CreateCategoryRequest postRequest = new CreateCategoryRequest.CreateTournamentBuilderImpl()
                .setName(KNOWN_CATEGORY_NAME)
                .setSportId(SPORT_ID)
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
