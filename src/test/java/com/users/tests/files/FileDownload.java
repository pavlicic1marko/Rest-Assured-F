package com.users.tests.files;

import io.restassured.RestAssured;
import org.junit.Test;

public class FileDownload {

    @Test
    public void downloadFileTest(){

        byte[] file = RestAssured.given()
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .get("https://files.testfile.org/PDF/20MB-TESTFILE.ORG.pdf").then().log().headers().extract().asByteArray();

    }
}
