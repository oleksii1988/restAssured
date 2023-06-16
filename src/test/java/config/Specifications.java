package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {



public static ResponseSpecification responseSpec200(){
    return new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();


}

    public static ResponseSpecification responseSpec400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();


    }

    public static ResponseSpecification responseSpec404(){
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();


    }

    public static ResponseSpecification responseSpec409(){
        return new ResponseSpecBuilder()
                .expectStatusCode(409)
                .build();


    }

    public static ResponseSpecification responseSpec412(){
        return new ResponseSpecBuilder()
                .expectStatusCode(412)
                .build();


    }

    public static ResponseSpecification responseSpec500(){
        return new ResponseSpecBuilder()
                .expectStatusCode(500)
                .build();


    }

public static void installSpecifications(RequestSpecification request, ResponseSpecification response){
    RestAssured.requestSpecification = request;
    RestAssured.responseSpecification = response;
}


}
