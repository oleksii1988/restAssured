package sport;

import config.Specifications;
import jdbc.SportDAO;
import modelDB.MappingModel;
import modelDB.SportModel;
import org.testng.Assert;
import org.testng.annotations.*;
import requestDto.category.MapRequest;
import responsDto.sport.UnmapSportResponse;

import java.sql.SQLException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class UnmapSportTest {

    private static final String EXTERNAL_ID = "betconstruct:789977";
    private static final String PROVIDER = "betconstruct";
    private static final Integer NON_EXISTENT_SPORT_ID = 999999;
    private static final String NON_EXISTENT_EXTERNAL_ID = "absolute:1:Hogvards";
    private static final Integer SPORT_ID = 9999;
    private static final String SPORT_NAME = "Football Test";

    private static final String URL_ENDPOINT = "/sport:unmap";

    SportModel sportFootball = new SportModel(SPORT_ID, SPORT_NAME);
    SportDAO sportDAO = new SportDAO();
    MappingModel createMapping = new MappingModel(EXTERNAL_ID,SPORT_ID,PROVIDER,SPORT_NAME);

    public UnmapSportTest() throws SQLException {
    }


    @BeforeClass
    void createSportCategoryTournamentForTest() {
        sportDAO.createNewSport(sportFootball);
        sportDAO.createMapping(createMapping);

    }

    @AfterClass
    void deleteAll(){
        sportDAO.delete(sportFootball.getId());

    }

    @Test(testName = "Unmap sport and checking not present entity in db")
    public void unmap_Sport() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(SPORT_ID)
                .setProvider(PROVIDER)
                .build();

        UnmapSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapSportResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(),SPORT_ID);
        Assert.assertEquals(response.getName(),SPORT_NAME);
        Assert.assertTrue(response.getProviderMappings().stream()
                .noneMatch((data-> Objects.equals(data.getExternalId(),EXTERNAL_ID))));
       Assert.assertFalse(sportDAO.getMap(response.getMappedId()));

    }


    @Test(testName = "Unmap sport with unvalidated sportId")
    public void unmap_Sport_WithNonExistentSportId_ExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(NON_EXISTENT_SPORT_ID)
                .setProvider(PROVIDER)
                .build();
        UnmapSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapSportResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());

    }

    @Test(testName = "Unmap sport with unvalidated external_id")
    public void unmap_Sport_WithNonExistentExternalId_ExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(NON_EXISTENT_EXTERNAL_ID)
                .setMappedId(SPORT_ID)
                .setProvider(PROVIDER)
                .build();

        UnmapSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapSportResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());

    }
    //TODO Подумать как сделать тест на размапливание несуществующей сущности
    @Ignore
    @Test(testName = "Unmap sport which was not mapped")
    public void unmap_SportWhichWasNotMapped_ExpectedCode404()  {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        MapRequest postRequest = new MapRequest.MapRequestBuilderImpl()
                .setExternalId(EXTERNAL_ID)
                .setMappedId(SPORT_ID)
                .setProvider(PROVIDER)
                .build();

        UnmapSportResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(UnmapSportResponse.class);

        Assert.assertNull(response.getMappedId());
        Assert.assertNull(response.getName());

    }



}
