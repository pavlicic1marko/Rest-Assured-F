package com.users.tests.files;

import io.restassured.RestAssured;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileDownload {

    @Test
    public void downloadFileTest(){

        File inputFile = new File("src/main/resources/20MB-TESTFILE.ORG.pdf");
        int expectedFilesize = (int)inputFile.length();



        byte[] fileDownloaded = RestAssured.given()
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .get("https://files.testfile.org/PDF/20MB-TESTFILE.ORG.pdf").then().log().headers().extract().asByteArray();

        assertThat(expectedFilesize,equalTo(fileDownloaded.length));
    }

    @Test
    public  void uploadFile(){

        File inputFile = new File("src/main/resources/Transformer.jpg");

        RestAssured.given()
                .multiPart("image", inputFile)
                .post("http://127.0.0.1:8000/api/users/images/upload")
                .then().log().all();

    }
}
