import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    Response response;

    @Test
    @DisplayName("addToCartTest")

    void addToCartAsNewUserTest() {
        int counter = 0;
        String sMin = "", sMax = "";

        for (int i = 0; i <= 9; i++) {
            response = given()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .cookie("Nop.customer=6cf3b53a-8867-4572-bb4a-3e6e4863504d;")
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
                    //.body("updatetopcartsectionhtml", is("(14)"))
                    .extract().response();

            if (i == 0) {
                sMin = response.path("updatetopcartsectionhtml");
            }

            if (i == 9) {
                sMax = response.path("updatetopcartsectionhtml");
            }
        }

        sMin = sMin.substring(1, sMin.length()-1);
        sMax = sMax.substring(1, sMax.length()-1);
        assertEquals(9,Integer.valueOf(sMax) - Integer.valueOf(sMin));

    }

}