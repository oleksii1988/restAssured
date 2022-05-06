package event;

import config.Specifications;
import jdbc.EventDAO;
import modelDB.EventModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import requestDto.event.UpdateEventRequest;
import responsDto.event.UpdateEventResponse;
import java.sql.SQLException;
import java.time.Instant;

import static io.restassured.RestAssured.given;

public class UpdateEventTest {

    private static final String URL_ENDPOINT = "/event/";


    EventDAO eventDAO = new EventDAO();

    final EventModel GET_UNSUSPENDED_EVENT = eventDAO.getLsportMappedEventsWithoutLiveAndNotStartedStatusFromDB();

    final Long time = Instant.now().toEpochMilli();
    final Long UPDATE_TIME = time + 90000000;


    public UpdateEventTest() throws SQLException {
    }


    @Test(testName = "Update start date event and suspend TRUE")
    public void update_StartDateAndSuspendTrue() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateEventRequest postRequest = new UpdateEventRequest(UPDATE_TIME,true);

        UpdateEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT+GET_UNSUSPENDED_EVENT.getId()+":update")
                .then().log().all()
                .extract().body().as(UpdateEventResponse.class);
        EventModel GET_SUSPEND = eventDAO.getEventByIdFromDB(GET_UNSUSPENDED_EVENT.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStartDate(), UPDATE_TIME);
        Assert.assertEquals(response.getId(), GET_UNSUSPENDED_EVENT.getId());
        Assert.assertTrue(GET_SUSPEND.getSuspended());

    }

    @Test(testName = "Update event suspend TRUE")
    public void update_EventSuspendTrue() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateEventRequest postRequest = new UpdateEventRequest(true);

        UpdateEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT+GET_UNSUSPENDED_EVENT.getId()+":update")
                .then().log().all()
                .extract().body().as(UpdateEventResponse.class);
        EventModel GET_SUSPEND = eventDAO.getEventByIdFromDB(GET_UNSUSPENDED_EVENT.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), GET_UNSUSPENDED_EVENT.getId());
        Assert.assertTrue(GET_SUSPEND.getSuspended());

    }

    @Test(testName = "Update event suspend FALSE")
    public void update_EventSuspendFalse() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateEventRequest postRequest = new UpdateEventRequest(false);

        UpdateEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT+GET_UNSUSPENDED_EVENT.getId()+":update")
                .then().log().all()
                .extract().body().as(UpdateEventResponse.class);
        EventModel GET_SUSPEND = eventDAO.getEventByIdFromDB(GET_UNSUSPENDED_EVENT.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), GET_UNSUSPENDED_EVENT.getId());
        Assert.assertFalse(GET_SUSPEND.getSuspended());

    }

    @Test(testName = "Update start date in event")
    public void update_StartDate() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateEventRequest postRequest = new UpdateEventRequest(UPDATE_TIME);

        UpdateEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT+GET_UNSUSPENDED_EVENT.getId()+":update")
                .then().log().all()
                .extract().body().as(UpdateEventResponse.class);


        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), GET_UNSUSPENDED_EVENT.getId());
        Assert.assertEquals(response.getStartDate(), UPDATE_TIME);


    }

    @Test(testName = "Update start date event and suspend FALSE")
    public void update_StartDateAndSuspendFalse() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateEventRequest postRequest = new UpdateEventRequest(UPDATE_TIME,false);

        UpdateEventResponse response = given()
                .body(postRequest)
                .when()
                .post(URL_ENDPOINT+GET_UNSUSPENDED_EVENT.getId()+":update")
                .then().log().all()
                .extract().body().as(UpdateEventResponse.class);
        EventModel GET_SUSPEND = eventDAO.getEventByIdFromDB(GET_UNSUSPENDED_EVENT.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStartDate(), UPDATE_TIME);
        Assert.assertEquals(response.getId(), GET_UNSUSPENDED_EVENT.getId());
        Assert.assertFalse(GET_SUSPEND.getSuspended());

    }


}
