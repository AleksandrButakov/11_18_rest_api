import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestAPI {

        /*
    curl 'http://demowebshop.tricentis.com/addproducttocart/details/16/1' \
    -H 'Accept: **' \
            -H 'Accept-Language: en-US,en;q=0.9,ru;q=0.8' \
            -H 'Connection: keep-alive' \
            -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8' \
            -H 'Cookie: Nop.customer=6cf3b53a-8867-4572-bb4a-3e6e4863504d;
             __utmz=78382081.1650200489.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ARRAffinity=1818b4c81d905377ced20e7ae987703a674897394db6e97dc1316168f754a687; __utmc=78382081; _ym_uid=1650336537544507447; _ym_d=1650336537; _ym_isad=1; __utma=78382081.2090069837.1650200489.1650336535.1650340980.4; __utmt=1; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=16&RecentlyViewedProductIds=72; __atuvc=8%7C16; __atuvs=625e34736d87f02b004; __utmb=78382081.9.10.1650340980' \
            -H 'Origin: http://demowebshop.tricentis.com' \
            -H 'Referer: http://demowebshop.tricentis.com/build-your-own-computer' \
            -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36' \
            -H 'X-Requested-With: XMLHttpRequest' \
            --data-raw 'product_attribute_16_5_4=14&product_attribute_16_6_5=15&product_attribute_16_3_6=18&product_attribute_16_4_7=44&product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=1' \
            --compressed \
            --insecure
     */


    /*
    Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
          .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
          .header("Cookie", "ARRAffinity=1818b4c81d905377ced20e7ae987703a674897394db6e97dc1316168f754a687; Nop.customer=3d26c045-d2eb-4a90-ae9f-fae62387cda0")
          .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
          .asString();
     */

    @Test
    @DisplayName("01_addToCartTest")
    void addToCartAsNewUserTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=6cf3b53a-8867-4572-bb4a-3e6e4863504d;")

        //Nop.customer=6cf3b53a-8867-4572-bb4a-3e6e4863504d; __utmz=78382081.1650200489.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ARRAffinity=1818b4c81d905377ced20e7ae987703a674897394db6e97dc1316168f754a687; __utmc=78382081; _ym_uid=1650336537544507447; _ym_d=1650336537; _ym_isad=1; __utma=78382081.2090069837.1650200489.1650336535.1650340980.4; __utmt=1; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=16&RecentlyViewedProductIds=72; __atuvc=8%7C16; __atuvs=625e34736d87f02b004; __utmb=78382081.9.10.1650340980

                .body("product_attribute_16_5_4=14" +
                        "&product_attribute_16_6_5=15" +
                        "&product_attribute_16_3_6=18" +
                        "&product_attribute_16_4_7=44" +
                        "&product_attribute_16_8_8=22" +
                        "&addtocart_16.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/16/1")
                .then()
                .log().all()  // команда выводит весь контент в чат
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your \u003ca href=\"/cart\"\u003eshopping cart\u003c/a\u003e"))
                .body("updatetopcartsectionhtml", is("(10)"));

    }

    @Test
    @DisplayName("02_addToCartTest")
    void addToCartTest() {

        String response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=3d26c045-d2eb-4a90-ae9f-fae62387cda0;")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()  // команда выводит весь контент в чат
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your \u003ca href=\"/cart\"\u003eshopping cart\u003c/a\u003e"))
                .body("updatetopcartsectionhtml", is("(27)"))
                .extract().response().asString();

        System.out.println();
        System.out.println("Full answer:");
        System.out.println(response);

    }

    @Test
    @DisplayName("03_addToCartTest33")
    void addToCartTest33() {
        Integer cartSize = 0;

        ValidatableResponse response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=3d26c045-d2eb-4a90-ae9f-fae62387cda0;")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()  // команда выводит весь контент в чат
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your \u003ca href=\"/cart\"\u003eshopping cart\u003c/a\u003e"));

        // assertThat(response.extract().path("updatetopcartsectionhtml").toString())
//                .body("updatetopcartsectionhtml", is("(24)"))
//                .extract().response().asString();

        System.out.println();
        System.out.println("Full answer:");
        System.out.println(response);
    }

/*
    Пример рабочего теста:
 */

}