package event.searchEvenEndPoint.lsport;

import config.Specifications;
import jdbc.EventDAO;
import modelDB.EventModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import requestDto.event.SearchEventRequest;
import responsDto.event.SearchEventResponse;

import java.sql.SQLException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class SearchLsportUnmappedEventsTest {

    private static final String URL_ENDPOINT = "/event:search";

    EventDAO eventDAO = new EventDAO();

    public SearchLsportUnmappedEventsTest() throws SQLException {
    }

    final EventModel GET_ALL_STATUS_EVENT_MODEL = eventDAO.getLsportUnmappedEventsWithAllStatusFromDB();
    final EventModel GET_NOT_STARTED_STATUS_EVENT_MODEL = eventDAO.getLsportUnmappedEventsWithNotStartedStatusWithoutOutrightFromDB();
    final EventModel GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL = eventDAO.getLsportUnmappedEventsWithNotStartedStatusWithOutrightFromDB();
    final EventModel GET_LIVE_STATUS_EVENT_MODEL = eventDAO.getLsportUnmappedEventsWithLiveStatusFromDB();
    final EventModel GET_FINISHED_STATUS_EVENT_MODEL = eventDAO.getLsportUnmappedEventsWithFinishedStatusWithoutOutrightFromDB();
    final EventModel GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL = eventDAO.getLsportUnmappedEventsWithFinishedStatusWithOutrightFromDB();



    @Test(testName = "Search unmapped event with ALL status by sportId")
    public void search_bySportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_ALL_STATUS_EVENT_MODEL.getSport_id())
                .setProvider(GET_ALL_STATUS_EVENT_MODEL.getProvider())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getSport()
                        .getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_ALL_STATUS_EVENT_MODEL.getProvider())));

    }

    @Test(testName = "Search unmapped event with ALL status by categoryId")
    public void search_byCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setCategoryId(GET_ALL_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_ALL_STATUS_EVENT_MODEL.getProvider())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_ALL_STATUS_EVENT_MODEL.getProvider())));

    }

    @Test(testName = "Search unmapped event with ALL status by tournamentId")
    public void search_byTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setTournamentId(GET_ALL_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_ALL_STATUS_EVENT_MODEL.getProvider())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_ALL_STATUS_EVENT_MODEL.getProvider())));

    }


    @Test(testName = "Search unmapped event with ALL status by name")
    public void search_byName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setName(GET_ALL_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_ALL_STATUS_EVENT_MODEL.getProvider())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName(), GET_ALL_STATUS_EVENT_MODEL.getName())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_ALL_STATUS_EVENT_MODEL.getProvider())));

    }


    @Test(testName = "Search unmapped event with ALL status by sportId and categoryId")
    public void search_bySportIdCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_ALL_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_ALL_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_ALL_STATUS_EVENT_MODEL.getProvider())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport().getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_ALL_STATUS_EVENT_MODEL.getProvider())));

    }

    @Test(testName = "Search unmapped event with ALL status by sportId, categoryId, tournamentId")
    public void search_bySportIdCategoryIdTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setProvider(GET_ALL_STATUS_EVENT_MODEL.getProvider())
                .setSportId(GET_ALL_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_ALL_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_ALL_STATUS_EVENT_MODEL.getTournament_id())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getSport()
                        .getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_ALL_STATUS_EVENT_MODEL.getProvider())));

    }

    @Test(testName = "Search unmapped event with ALL status by sportId, categoryId, tournamentId, name")
    public void search_bySportIdCategoryIdTournamentIdName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setProvider(GET_ALL_STATUS_EVENT_MODEL.getProvider())
                .setSportId(GET_ALL_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_ALL_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_ALL_STATUS_EVENT_MODEL.getTournament_id())
                .setName(GET_ALL_STATUS_EVENT_MODEL.getName())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getSport()
                        .getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_ALL_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName()
                        , GET_ALL_STATUS_EVENT_MODEL.getName())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright FALSE by sportId")
    public void search_byNotStartedStatusAndSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getSport()
                        .getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright FALSE by categoryId")
    public void search_byNotStartedStatusAndCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setCategoryId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright FALSE by tournamentId")
    public void search_byNotStartedStatusAndTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setTournamentId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright FALSE by name event")
    public void search_byNotStartedStatusAndNameEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setName(GET_NOT_STARTED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getName())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright FALSE by sportId and categoryId")
    public void search_byNotStartedStatusAndSportIdCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright FALSE by sportId, categoryId, tournamentId")
    public void search_byNotStartedStatusAndSportIdCategoryIdTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright FALSE by sportId, categoryId, tournamentId, name")
    public void search_byNotStartedStatusAndSportIdCategoryIdTournamentIdAndEventName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())
                .setName(GET_NOT_STARTED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getName())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright TRUE by sportId")
    public void search_OutrightEventByNotStartedStatusAndSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setProvider(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getSport()
                        .getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright TRUE by categoryId")
    public void search_OutrightEventByNotStartedStatusAndCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setCategoryId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright TRUE by tournamentId")
    public void search_OutrightEventByNotStartedStatusAndTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setTournamentId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright TRUE by name event")
    public void search_OutrightEventByNotStartedStatusAndNameEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setName(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getName())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright TRUE by mappedId")
    public void search_OutrightEventByNotStartedStatusAndMappedIdEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setMappedId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getMapped_id())
                .setProvider(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getMapped_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright TRUE by sportId and categoryId")
    public void search_OutrightEventByNotStartedStatusAndSportIdCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright TRUE by sportId, categoryId, tournamentId")
    public void search_OutrightEventByNotStartedStatusAndSportIdCategoryIdTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with NOT_STARTED status and outright TRUE by sportId, categoryId, tournamentId, name")
    public void search_OutrightEventByNotStartedStatusAndSportIdCategoryIdTournamentIdAndEventName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())
                .setName(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getName())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_NOT_STARTED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with LIVE status by sportId")
    public void search_byLiveStatusAndSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_LIVE_STATUS_EVENT_MODEL.getSport_id())
                .setProvider(GET_LIVE_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_LIVE_STATUS_EVENT_MODEL.getStatus())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getSport()
                        .getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_LIVE_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_LIVE_STATUS_EVENT_MODEL.getStatus())));

    }

    @Test(testName = "Search unmapped event with LIVE status by categoryId")
    public void search_byLiveStatusAndCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setCategoryId(GET_LIVE_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_LIVE_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_LIVE_STATUS_EVENT_MODEL.getStatus())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_LIVE_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_LIVE_STATUS_EVENT_MODEL.getStatus())));

    }

    @Test(testName = "Search unmapped event with LIVE status by tournamentId")
    public void search_byLiveStatusAndTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setTournamentId(GET_LIVE_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_LIVE_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_LIVE_STATUS_EVENT_MODEL.getStatus())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_LIVE_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_LIVE_STATUS_EVENT_MODEL.getStatus())));

    }

    @Test(testName = "Search unmapped event with LIVE status by name event")
    public void search_byLiveStatusAndNameEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setName(GET_LIVE_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_LIVE_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_LIVE_STATUS_EVENT_MODEL.getStatus())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName(), GET_LIVE_STATUS_EVENT_MODEL.getName())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_LIVE_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_LIVE_STATUS_EVENT_MODEL.getStatus())));

    }

    @Test(testName = "Search unmapped event with LIVE status by sportId and categoryId")
    public void search_byLiveStatusAndSportIdCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_LIVE_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_LIVE_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_LIVE_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_LIVE_STATUS_EVENT_MODEL.getStatus())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_LIVE_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_LIVE_STATUS_EVENT_MODEL.getStatus())));

    }

    @Test(testName = "Search unmapped event with LIVE status by sportId, categoryId, tournamentId")
    public void search_byLiveStatusAndSportIdCategoryIdTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_LIVE_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_LIVE_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_LIVE_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_LIVE_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_LIVE_STATUS_EVENT_MODEL.getStatus())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_LIVE_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_LIVE_STATUS_EVENT_MODEL.getStatus())));

    }

    @Test(testName = "Search unmapped event with LIVE status by sportId, categoryId, tournamentId, name")
    public void search_byLiveStatusAndSportIdCategoryIdTournamentIdAndEventName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_LIVE_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_LIVE_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_LIVE_STATUS_EVENT_MODEL.getTournament_id())
                .setName(GET_LIVE_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_LIVE_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_LIVE_STATUS_EVENT_MODEL.getStatus())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName()
                        , GET_LIVE_STATUS_EVENT_MODEL.getName())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_LIVE_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_LIVE_STATUS_EVENT_MODEL.getStatus())));

    }

    @Test(testName = "Search unmapped event with FINISHED and outright FALSE  status by sportId")
    public void search_byFinishedStatusAndSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getSport()
                        .getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with FINISHED status and outright FALSE by categoryId")
    public void search_byFinishedStatusAndCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setCategoryId(GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with FINISHED status and outright FALSE by tournamentId")
    public void search_byFinishedStatusAndTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setTournamentId(GET_FINISHED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with FINISHED status and outright FALSE by name event")
    public void search_byFinishedStatusAndNameEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setName(GET_FINISHED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName(), GET_FINISHED_STATUS_EVENT_MODEL.getName())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }


    @Test(testName = "Search unmapped event with FINISHED status and outright FALSE by sportId and categoryId")
    public void search_byFinishedStatusAndSportIdCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with FINISHED status and outright FALSE by sportId, categoryId, tournamentId")
    public void search_byFinishedStatusAndSportIdCategoryIdTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_FINISHED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with FINISHED status and outright FALSE by sportId, categoryId, tournamentId, name")
    public void search_byFinishedStatusAndSportIdCategoryIdTournamentIdAndEventName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_FINISHED_STATUS_EVENT_MODEL.getTournament_id())
                .setName(GET_FINISHED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getName())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with FINISHED status and outright TRUE by sportId")
    public void search_OutrightEventByFinishedStatusAndSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setProvider(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getSport()
                        .getMappedId(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with FINISHED status and outright TRUE by categoryId")
    public void search_OutrightEventByFinishedStatusAndCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setCategoryId(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament()
                        .getCategory().getMappedId(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with FINISHED status and outright TRUE by tournamentId")
    public void search_OutrightEventByFinishedStatusAndTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setTournamentId(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with FINISHED status and outright TRUE by name event")
    public void search_OutrightEventByFinishedStatusAndNameEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setName(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getName())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }


    @Test(testName = "Search unmapped event with FINISHED status and outright TRUE by sportId and categoryId")
    public void search_OutrightEventByFinishedStatusAndSportIdCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with FINISHED status and outright TRUE by sportId, categoryId, tournamentId")
    public void search_OutrightEventByFinishedStatusAndSportIdCategoryIdTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }

    @Test(testName = "Search unmapped event with FINISHED status and outright TRUE by sportId, categoryId, tournamentId, name")
    public void search_OutrightEventByFinishedStatusAndSportIdCategoryIdTournamentIdAndEventName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(false)
                .setSportId(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getTournament_id())
                .setName(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .setOutright(GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getSport()
                        .getMappedId(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getSport_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getCategory().getMappedId(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getCategory_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getTournament().getMappedId(), GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getTournament_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getName()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getName())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getStatus())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getOutright()
                        , GET_OUTRIGHT_FINISHED_STATUS_EVENT_MODEL.getOutright())));

    }



}
