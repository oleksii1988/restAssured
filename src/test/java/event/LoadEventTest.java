package event;

import config.Specifications;
import jdbc.EventDAO;
import modelDB.EventModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import requestDto.event.LoadEventRequest;
import responsDto.event.LoadEventResponse;

import java.sql.SQLException;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class LoadEventTest {


    private static final String URL_ENDPOINT = "/event:load";

    EventDAO eventDAO = new EventDAO();

    final EventModel GET_NOT_STARTED_EVENT_LSPORT = eventDAO.getLsportMappedEventsWithNotStartedStatusWithoutOutrightFromDB();
    final EventModel GET_LIVE_EVENT_LSPORT = eventDAO.getLsportMappedEventsWithLiveStatusFromDB();
    final EventModel GET_FINISHED_EVENT_LSPORT = eventDAO.getLsportMappedEventsWithFinishedStatusWithoutOutrightFromDB();

    final EventModel GET_NOT_STARTED_EVENT_LONGSHOT = eventDAO.getLongShotMappedEventsWithNotStartedStatusFromDB();
    final EventModel GET_LIVE_UNMAPPED_EVENT_LONGSHOT = eventDAO.getLongShotUnmappedEventsWithLiveStatusFromDB();
    final EventModel GET_FINISHED_EVENT_LONGSHOT = eventDAO.getLongShotMappedEventsWithFinishedStatusFromDB();

    public LoadEventTest() throws SQLException {
    }


    @Test(testName = "Load Lsport event with NOT_STARTED, LIVE, FINISHED status")
    public void load_LsportEventWithLiveNotStartedFinishedStatus_ExpectedResponseAllEventSuccessfulWithoutFailed() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        LoadEventRequest postRequest = new LoadEventRequest(Set.of(
                GET_NOT_STARTED_EVENT_LSPORT.getExternal_id(),
                GET_LIVE_EVENT_LSPORT.getExternal_id(),
                GET_FINISHED_EVENT_LSPORT.getExternal_id()));

        LoadEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(LoadEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_NOT_STARTED_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_LIVE_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_FINISHED_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().contains(GET_LIVE_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().contains(GET_FINISHED_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getLive().getFailed().isEmpty());
        Assert.assertTrue(response.getPrematch().getFailed().isEmpty());



    }

    @Test(testName = "Load Lsport event with LIVE status")
    public void load_LsportEventWithLiveStatus_ExpectedResponseEventSuccessfulWithoutFailed() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        LoadEventRequest postRequest = new LoadEventRequest(Set.of(
                GET_LIVE_EVENT_LSPORT.getExternal_id()));

        LoadEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(LoadEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_LIVE_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().contains(GET_LIVE_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getLive().getFailed().isEmpty());
        Assert.assertTrue(response.getPrematch().getFailed().isEmpty());

    }

    @Test(testName = "Load Lsport event with NOT_STARTED status")
    public void load_LsportEventWithNotStartedStatus_ExpectedResponseEventSuccessfulWithoutFailed() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        LoadEventRequest postRequest = new LoadEventRequest(Set.of(
                GET_NOT_STARTED_EVENT_LSPORT.getExternal_id()));

        LoadEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(LoadEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_NOT_STARTED_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().isEmpty());
        Assert.assertTrue(response.getLive().getFailed().isEmpty());
        Assert.assertTrue(response.getPrematch().getFailed().isEmpty());

    }

    @Test(testName = "Load Lsport event with FINISHED status")
    public void load_LsportEventWithFinishedStatus_ExpectedResponseEventSuccessfulWithoutFailed() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        LoadEventRequest postRequest = new LoadEventRequest(Set.of(
                GET_FINISHED_EVENT_LSPORT.getExternal_id()));

        LoadEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(LoadEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_FINISHED_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().contains(GET_FINISHED_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getLive().getFailed().isEmpty());
        Assert.assertTrue(response.getPrematch().getFailed().isEmpty());

    }

    @Test(testName = "Load Longshot event with NOT_STARTED, LIVE, FINISHED")
    public void load_LongShotEventWithLiveNotStartedFinishedStatus_ExpectedResponseAllEventSuccessfulWithoutFailed() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        LoadEventRequest postRequest = new LoadEventRequest(Set.of(
                GET_NOT_STARTED_EVENT_LONGSHOT.getExternal_id(),
                GET_LIVE_UNMAPPED_EVENT_LONGSHOT.getExternal_id(),
                GET_FINISHED_EVENT_LONGSHOT.getExternal_id()));

        LoadEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(LoadEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_NOT_STARTED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_FINISHED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().contains(GET_FINISHED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_LIVE_UNMAPPED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().contains(GET_LIVE_UNMAPPED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getLive().getFailed().isEmpty());
        Assert.assertTrue(response.getPrematch().getFailed().isEmpty());



    }

    @Test(testName = "Load Longshot event with LIVE status")
    public void load_LongShotEventWithLiveStatus_ExpectedResponseEventSuccessfulWithoutFailed() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        LoadEventRequest postRequest = new LoadEventRequest(Set.of(
                GET_LIVE_UNMAPPED_EVENT_LONGSHOT.getExternal_id()));

        LoadEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(LoadEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_LIVE_UNMAPPED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().contains(GET_LIVE_UNMAPPED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getLive().getFailed().isEmpty());
        Assert.assertTrue(response.getPrematch().getFailed().isEmpty());

    }

    @Test(testName = "Load Longshot event with NOT_STARTED status")
    public void load_LongShotEventWithNotStartedStatus_ExpectedResponseEventSuccessfulWithoutFailed() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        LoadEventRequest postRequest = new LoadEventRequest(Set.of(
                GET_NOT_STARTED_EVENT_LONGSHOT.getExternal_id()));

        LoadEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(LoadEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_NOT_STARTED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().isEmpty());
        Assert.assertTrue(response.getLive().getFailed().isEmpty());
        Assert.assertTrue(response.getPrematch().getFailed().isEmpty());

    }

    @Test(testName = "Load Longshot event with FINISHED status")
    public void load_LongShotEventWithFinishedStatus_ExpectedResponseEventSuccessfulWithoutFailed() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        LoadEventRequest postRequest = new LoadEventRequest(Set.of(
                GET_FINISHED_EVENT_LONGSHOT.getExternal_id()));

        LoadEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(LoadEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_FINISHED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().contains(GET_FINISHED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getLive().getFailed().isEmpty());
        Assert.assertTrue(response.getPrematch().getFailed().isEmpty());

    }

    @Test(testName = "Load Longshot and Lsport event with FINISHED status")
    public void load_LongShotAndLsportEventWithFinishedStatus_ExpectedResponseEventSuccessfulWithoutFailed() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        LoadEventRequest postRequest = new LoadEventRequest(Set.of(
                GET_FINISHED_EVENT_LSPORT.getExternal_id(),
                GET_FINISHED_EVENT_LONGSHOT.getExternal_id()));

        LoadEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(LoadEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_FINISHED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().contains(GET_FINISHED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_FINISHED_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getLive().getSuccessful().contains(GET_FINISHED_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getLive().getFailed().isEmpty());
        Assert.assertTrue(response.getPrematch().getFailed().isEmpty());

    }

    @Test(testName = "Load Longshot and Lsport event with NOT_STARTED status")
    public void load_LongShotAndLsportEventWithNotStartedStatus_ExpectedResponseEventSuccessfulWithoutFailed() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        LoadEventRequest postRequest = new LoadEventRequest(Set.of(
                GET_NOT_STARTED_EVENT_LSPORT.getExternal_id(),
                GET_NOT_STARTED_EVENT_LONGSHOT.getExternal_id()));

        LoadEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT)
                .then().log().all()
                .extract().body().as(LoadEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_NOT_STARTED_EVENT_LONGSHOT.getExternal_id()));
        Assert.assertTrue(response.getPrematch().getSuccessful().contains(GET_NOT_STARTED_EVENT_LSPORT.getExternal_id()));
        Assert.assertTrue(response.getLive().getFailed().isEmpty());
        Assert.assertTrue(response.getPrematch().getFailed().isEmpty());

    }



}
