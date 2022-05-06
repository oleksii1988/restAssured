package event.searchEvenEndPoint.longshot;

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

public class SearchLongShotMappedEventsTest {


    private static final String URL_ENDPOINT = "/event:search";

    EventDAO eventDAO = new EventDAO();

    public SearchLongShotMappedEventsTest() throws SQLException {
    }

    final EventModel GET_ALL_STATUS_EVENT_MODEL = eventDAO.getLongShotMappedEventsWithAllStatusFromDB();
    final EventModel GET_NOT_STARTED_STATUS_EVENT_MODEL = eventDAO.getLongShotMappedEventsWithNotStartedStatusFromDB();
    final EventModel GET_LIVE_STATUS_EVENT_MODEL = eventDAO.getLongShotMappedEventsWithLiveStatusFromDB();
    final EventModel GET_FINISHED_STATUS_EVENT_MODEL = eventDAO.getLongShotMappedEventsWithFinishedStatusFromDB();



    @Test(testName = "Search mapped event with ALL status by sportId")
    public void search_bySportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with ALL status by categoryId")
    public void search_byCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with ALL status by tournamentId")
    public void search_byTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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


    @Test(testName = "Search mapped event with ALL status by name")
    public void search_byName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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


    @Test(testName = "Search mapped event with ALL status by mappedId event")
    public void search_byMappedId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setMappedId(GET_ALL_STATUS_EVENT_MODEL.getMapped_id())
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
                .equals(dataType.getMappedId(), GET_ALL_STATUS_EVENT_MODEL.getMapped_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_ALL_STATUS_EVENT_MODEL.getProvider())));

    }

    @Test(testName = "Search mapped event with ALL status by sportId and categoryId")
    public void search_bySportIdCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with ALL status by sportId, categoryId, tournamentId")
    public void search_bySportIdCategoryIdTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with ALL status by sportId, categoryId, tournamentId, name")
    public void search_bySportIdCategoryIdTournamentIdName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with NOT_STARTED status by sportId")
    public void search_byNotStartedStatusAndSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setSportId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with NOT_STARTED status by categoryId")
    public void search_byNotStartedStatusAndCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setCategoryId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with NOT_STARTED status by tournamentId")
    public void search_byNotStartedStatusAndTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setTournamentId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with NOT_STARTED status by name event")
    public void search_byNotStartedStatusAndNameEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setName(GET_NOT_STARTED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with NOT_STARTED status by mappedId")
    public void search_byNotStartedStatusAndMappedIdEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setMappedId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getMapped_id())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getMappedId(), GET_NOT_STARTED_STATUS_EVENT_MODEL.getMapped_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())));

    }

    @Test(testName = "Search mapped event with NOT_STARTED status by sportId and categoryId")
    public void search_byNotStartedStatusAndSportIdCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setSportId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with NOT_STARTED status by sportId, categoryId, tournamentId")
    public void search_byNotStartedStatusAndSportIdCategoryIdTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setSportId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with NOT_STARTED status by sportId, categoryId, tournamentId, name")
    public void search_byNotStartedStatusAndSportIdCategoryIdTournamentIdAndEventName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setSportId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_NOT_STARTED_STATUS_EVENT_MODEL.getTournament_id())
                .setName(GET_NOT_STARTED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_NOT_STARTED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_NOT_STARTED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with LIVE status by sportId")
    public void search_byLiveStatusAndSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with LIVE status by categoryId")
    public void search_byLiveStatusAndCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with LIVE status by tournamentId")
    public void search_byLiveStatusAndTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with LIVE status by name event")
    public void search_byLiveStatusAndNameEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with LIVE status by mappedId")
    public void search_byLiveStatusAndMappedIdEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setMappedId(GET_LIVE_STATUS_EVENT_MODEL.getMapped_id())
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
                .equals(dataType.getMappedId(), GET_LIVE_STATUS_EVENT_MODEL.getMapped_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_LIVE_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_LIVE_STATUS_EVENT_MODEL.getStatus())));

    }

    @Test(testName = "Search mapped event with LIVE status by sportId and categoryId")
    public void search_byLiveStatusAndSportIdCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with LIVE status by sportId, categoryId, tournamentId")
    public void search_byLiveStatusAndSportIdCategoryIdTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with LIVE status by sportId, categoryId, tournamentId, name")
    public void search_byLiveStatusAndSportIdCategoryIdTournamentIdAndEventName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
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

    @Test(testName = "Search mapped event with FINISHED status by sportId")
    public void search_byFinishedStatusAndSportId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setSportId(GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with FINISHED status by categoryId")
    public void search_byFinishedStatusAndCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setCategoryId(GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with FINISHED status by tournamentId")
    public void search_byFinishedStatusAndTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setTournamentId(GET_FINISHED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with FINISHED status by name event")
    public void search_byFinishedStatusAndNameEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setName(GET_FINISHED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with FINISHED status by mappedId")
    public void search_byFinishedStatusAndMappedIdEvent() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setMappedId(GET_FINISHED_STATUS_EVENT_MODEL.getMapped_id())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
                .build();
        SearchEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(SearchEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getMappedId(), GET_FINISHED_STATUS_EVENT_MODEL.getMapped_id())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getProvider()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getProvider())));
        Assert.assertTrue(response.getData().stream().allMatch(dataType -> Objects
                .equals(dataType.getStatus()
                        , GET_FINISHED_STATUS_EVENT_MODEL.getStatus())));

    }

    @Test(testName = "Search mapped event with FINISHED status by sportId and categoryId")
    public void search_byFinishedStatusAndSportIdCategoryId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setSportId(GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with FINISHED status by sportId, categoryId, tournamentId")
    public void search_byFinishedStatusAndSportIdCategoryIdTournamentId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setSportId(GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_FINISHED_STATUS_EVENT_MODEL.getTournament_id())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
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

    }

    @Test(testName = "Search mapped event with FINISHED status by sportId, categoryId, tournamentId, name")
    public void search_byFinishedStatusAndSportIdCategoryIdTournamentIdAndEventName() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        SearchEventRequest postRequest = new SearchEventRequest.EventBuilderImpl()
                .setMapped(true)
                .setSportId(GET_FINISHED_STATUS_EVENT_MODEL.getSport_id())
                .setCategoryId(GET_FINISHED_STATUS_EVENT_MODEL.getCategory_id())
                .setTournamentId(GET_FINISHED_STATUS_EVENT_MODEL.getTournament_id())
                .setName(GET_FINISHED_STATUS_EVENT_MODEL.getName())
                .setProvider(GET_FINISHED_STATUS_EVENT_MODEL.getProvider())
                .setStatus(GET_FINISHED_STATUS_EVENT_MODEL.getStatus())
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

    }

}
