package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

public static RequestSpecification requestSpec(){
    return new RequestSpecBuilder()
            .setBaseUri("https://mapping-service-feed-test.k8s-hz.atlas-iac.com/bo/mapping")
            .setContentType(ContentType.JSON)
            .build();
}

public static ResponseSpecification responsSpec200(){
    return new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();


}

public static void installSpecifications(RequestSpecification request, ResponseSpecification response){
    RestAssured.requestSpecification = request;
    RestAssured.responseSpecification = response;
}


}
