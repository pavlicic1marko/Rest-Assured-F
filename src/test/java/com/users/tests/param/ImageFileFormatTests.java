package com.users.tests.param;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.users.requests.factory.ProductsRequestFactory;
import com.users.tags.Regression;
import com.users.tests.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class ImageFileFormatTests extends BaseClass {

    ProductsRequestFactory productsRequestFactory = new ProductsRequestFactory();


    @DataProvider
    public static String[] getImagesForUploadPaths(){
        return  new String[]
                {"src/main/resources/images/formatA.gif",
                "src/main/resources/images/calendar-icons.svg",
                "src/main/resources/images/images.png",
                "src/main/resources/images/Transformer.jpg"};
    }

    @Category(Regression.class)
    @Story("Product Images")
    @DisplayName("Upload all image formats")
    @Feature("Product")
    @Tag("Regression")
    @UseDataProvider("getImagesForUploadPaths")
    @Test
    public void testAllSupportedImageFormats(String imagePath){
        System.out.println(imagePath);


        //create product
        int productId = productsRequestFactory.createProduct().then().log().all().statusCode(200).extract().path("_id");

        //upload image
        productsRequestFactory.uploadProductImage(productId, imagePath).then().log().all().statusCode(200);

        //downloadImage and check
        String productIdWithImage = String.valueOf(productId);
        String imageUrl = productsRequestFactory.getProductById(productIdWithImage).then().log().all().statusCode(200).extract().path("image"); //get image url

        byte[] fileDownloaded =  productsRequestFactory.downloadProductImage(imageUrl);

        File inputFileImage = new File(imagePath);
        byte[] byteArrayOfLocalImage = new byte[(int) inputFileImage.length()];
        try (FileInputStream inputStream = new FileInputStream(inputFileImage)) {
            inputStream.read(byteArrayOfLocalImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertTrue(Arrays.equals(byteArrayOfLocalImage, fileDownloaded));

    }
}
