package event;

import config.Specifications;
import jdbc.EventDAO;
import modelDB.EventModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import responsDto.event.UpdateEventResponse;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class GetEventTest {

    private static final String URL_ENDPOINT = "/event/";

    EventDAO eventDAO = new EventDAO();

    final EventModel GET_EVENT_MODEL = eventDAO.getLsportMappedEventsWithNotStartedStatusWithoutOutrightFromDB();
    private static final Integer NON_EXIST_MAPPED_ID = 987654321;

    public GetEventTest() throws SQLException {
    }

    @Test(testName = "Get event by mappedId")
    public void get_EventByMappedId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        UpdateEventResponse response = given()
                .when()
                .get(URL_ENDPOINT+GET_EVENT_MODEL.getMapped_id())
                .then().log().all()
                .extract().body().as(UpdateEventResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getMappedId(), GET_EVENT_MODEL.getMapped_id());
        Assert.assertEquals(response.getName(), GET_EVENT_MODEL.getName());
        Assert.assertEquals(response.getStatus(), GET_EVENT_MODEL.getStatus());
        Assert.assertNotNull(response.getStartDate());


    }

    @Test(testName = "Get event by non-exist mappedId")
    public void get_EventByNonExistMappedId() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec404());
        UpdateEventResponse response = given()
                .when()
                .get(URL_ENDPOINT+NON_EXIST_MAPPED_ID)
                .then().log().all()
                .extract().body().as(UpdateEventResponse.class);


    }


}