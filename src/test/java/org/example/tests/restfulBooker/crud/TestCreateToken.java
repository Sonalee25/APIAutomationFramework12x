package org.example.tests.restfulBooker.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {

    @Test(groups = "reg" , priority = 1)
    @TmsLink("https://bugz.atlassian.net/browse/BUG-19")
    @Owner("Sonali")
    @Description("TC#1 - Create Token and verify")
    public void testTokenPOST() {

        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when()
                .body(payloadManager.setAuthPayload()).post();

        //Extration(Json String response to java object)
        String token = payloadManager.getTokenFromJSON(response.asString());
        System.out.println(token);

        //Validation of req
        assertActions.verifyStringKeyNotNull(token);
    }

    @Test(groups = "reg" , priority = 1)
    @TmsLink("https://bugz.atlassian.net/browse/BUG-19")
    @Owner("Sonali")
    @Description("TC#2 - Create an Invalid Token and verify")
    public void testTokenPOST_Negative() {

        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when()
                .body("{}").post();

        //Extration(Json String response to java object)
        String invalid_reason = payloadManager.getInvalidResponse(response.asString());
        System.out.println(invalid_reason);

//        Validation of req
        assertActions.verifyStringKey(invalid_reason, "Bad credentials");
    }

}
