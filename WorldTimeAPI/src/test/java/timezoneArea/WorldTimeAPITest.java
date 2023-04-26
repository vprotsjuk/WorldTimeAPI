package timezoneArea;//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import jdk.jfr.Description;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//import timezone.Timezones;
//
//import java.lang.reflect.Type;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//public class WorldTimeAPITest {
//    final String baseURL = "http://worldtimeapi.org";
//
//
//
//
//    @Test
//    @Description("Verify that the EndPoint /api/timezone" +
//            "returns 387 zones")
//    void getListOfTimeZones() {
//        String endPoint = "/api/timezone";
//        Timezones timezoneDTO = new Timezones();
//        Response response = RestAssured.get(baseURL + endPoint);
//        String responseBody = response.asString();
//        Gson gson = new Gson();
//        Timezones timezoneDTO1 = gson.fromJson(responseBody, Timezones.class);
//
//        Type type = new TypeToken<List<String>>(){}.getType();
//        List<String> responseDTO = gson.fromJson(responseBody, type);
//        SoftAssert softAssert = new SoftAssert();
////        softAssert.assertEquals(responseDTO.size(), 386);
//        softAssert.assertTrue(responseDTO.containsAll(timezoneDTO.expectedZones));
//        softAssert.assertAll();
////        Assert.assertEquals(hachCode, responseDTO.hashCode());
////        MatcherAssert.assertThat(timezoneAreaLocationDTO.expectedZones, Matchers.containsInAnyOrder(responseDTO));
//
////        System.out.println(responseDTO.size());
////        System.out.println(responseDTO);
////        System.out.println(responseBody);
////        String firstUserName = JsonPath.from(responseBody).get("yimezone[0].name");
////        String firstUserName = JsonPath.from(responseBody).get(baseURL + endPoint);
////        System.out.println(firstUserName);
//
////        String expectedData = "[\"Africa/Abidjan\",\"Africa/Accra\",\"Africa/Algiers\",";
////        Assert.assertTrue(response.getBody().asString().startsWith(expectedData));
////        Assert.assertTrue(response.getBody().asString().endsWith("Pacific/Wake\",\"Pacific/Wallis\",\"WET\"]"));
//    }
//    @Test
//    @Description("Verify that the EndPoint /api/timezone/Europe returns a list of available European Time zones")
//    void getListOfAvailableEuropeanTimeZones(){
//        String endPoint = "/api/timezone/Europe";
//        Response response = RestAssured.get(baseURL + endPoint );
//        String expectedData = "[\"Europe/Amsterdam\",\"Europe/Andorra";
//        Assert.assertTrue(response.getBody().asString().startsWith(expectedData));
//        Assert.assertTrue(response.getBody().asString().endsWith("Europe/Zaporozhye\",\"Europe/Zurich\"]"));
//    }
//    @Test
//    @Description("Verify that the EndPoint /api/timezone/America/Argentina/Salta returns the information about Argentina/Salta")
//    void getInfoAboutArgentinaSalta(){
//        String endPoint = "/api/timezone/America/Argentina/Salta";
//        Response response = RestAssured.get(baseURL + endPoint);
//
////        int unixtime = response.getBody().jsonPath().get("$.unixtime");
////        System.out.println(unixtime);
//        Assert.assertTrue(response.getBody().asString().contains("Salta"));
//
//
//        LocalTime expectedTime = LocalTime.now().withNano(0);
//        LocalDate expectedDate = LocalDate.now();
//        System.out.println(expectedTime);
//        System.out.println(expectedDate);
//        System.out.println("Time : " + response.getTime());
//
////        datetime: 2023-02-17T21:08:36.224319-08:00
//
//
////        Assert.assertEquals(response.getTime(), );
////
////                System.out.println("Time : " + response.getTime());
////
////
////        Response localTime = RestAssured.getTimezone(timezone);
////        Assert.assertEquals(response.getDatetime().toLocalTime().withNano(0), expectedTime);
////        Assert.assertEquals(response.getDatetime().toLocalDate(), expectedDate);
//    }
//    @Test@Description("Verify that EndPoint /api/timezone/Europe/London.txt returns information about London in String Format")
//    void getInfoAboutLondonAsString(){
//        String endPoint = "/api/timezone/Europe/London.txt";
//        Response response = RestAssured.get(baseURL + endPoint);
//        Assert.assertTrue(response.getBody().asString().contains("2023"));
//    }
//    @Test
//    @Description("Verify that EndPoint with local Ip Returns Local lInfo")
//    void getInfoAboutLocalIP(){
//        String endPoint = "/api/ip/98.45.132.65.txt";
//        Response response = RestAssured.get(baseURL + endPoint);
//        System.out.println("Body : " + response.getBody().asString());
//        Assert.assertTrue(response.getBody().asString().contains("Los_Angeles"));
//        System.out.println("Body : " + response.getBody().asString());
//
//    }
//    @Test
//    @Description("Verify that the EndPoint /api/timezone/America/Argentina/S get Error")
//    void getErrorAboutArgentinaS(){
//        String endPoint = "/api/timezone/America/Argentina/S";
//        Response response = RestAssured.get(baseURL + endPoint);
//        int statusCode = response.statusCode();
//        Assert.assertEquals(statusCode, 404);
//        //        int statusCode = response.statusCode();
////        Assert.assertEquals(statusCode, 200);
////        Assert.assertNotNull(response.getBody().asString());
////        System.out.println("Status Code : " + response.getStatusCode());
////        System.out.println("Header : " + response.getHeader("content-type"));
////        System.out.println("Time : " + response.getTime());
////        System.out.println("Response : " + response.asString());
////        System.out.println("Body : " + response.getBody().asString());
//    }
//}
