package utcOffset;

import com.google.gson.Gson;
import jdk.jfr.Description;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UTCOffset {

    final String baseURL = "http://worldtimeapi.org";

    public String getRandomParams() throws IOException {
        String endPoint = "/api/timezone";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseURL + endPoint)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        String[] parsedResponseBody = responseBody.split(",");
        Random random = new Random();
        int r1 = random.nextInt(0, parsedResponseBody.length);
        List<String> listOfParam2 = new ArrayList<>();
        List<String> listOfParam3 = new ArrayList<>();
        String one = parsedResponseBody[r1];
        String param = "";
        String param2 = "";
        String param3 = "";
        char ch = '/';
        //here we will randomly create {param}, {param2} and {param3}
        int index = one.indexOf(ch);
        if (index != -1) {
            param = one.substring(1, index);
            param2 = one.substring(index + 1, one.length() - 1);
            listOfParam2.add(param2);
            int r2 = random.nextInt(0, listOfParam2.size());
            param2 = listOfParam2.get(r2);
        } else {
            param = one;
        }
        int index2 = param2.indexOf(ch);
        if (index2 != -1) {
            param3 = param2.substring(index2 + 1);
            listOfParam3.add(param3);
            int r3 = random.nextInt(0, listOfParam3.size());
            param2 = param2.substring(0, index2);
            param3 = listOfParam3.get(r3);
        }

        if (param.equals(param2)) {
            param2 = null;
        }
        if (param2.equals(param3)) {
            param3 = null;
        }

        listOfParam2.add(param2);
        listOfParam2.remove("");
        listOfParam2.remove(null);
        listOfParam3.add(param3);
        listOfParam3.remove("");
        listOfParam3.remove(null);

        String result = param;
        if (param2 != null) {
            result = result + "/" + param2;
        }
        if ((param3 != null)) {
            result = result + "/" + param3;
        }
        if (result.charAt(result.length() - 1) == '/') {
            result = result.substring(0, result.length() - 1);
        }
//        System.out.println("Result = " +result);
        return result;
    }


    @Test
    @Description("Get Real Unixtime and compare with response Unixtime")
    void checkUtcOffset() throws IOException {
        String endPoint = "/api/timezone/";
        SoftAssert sa = new SoftAssert();
        Gson gson = new Gson();
        String param = getRandomParams();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseURL + endPoint + param)
                .build();
        Response response = client.newCall(request).execute();
        TimezoneAreaDTO timezoneAreaDTO = gson.fromJson(response.body().string(), TimezoneAreaDTO.class);
        int statusCode = response.code();
        sa.assertEquals(statusCode, 200);
        ZoneId zoneId = ZoneId.of(param);
        System.out.println("getRandomParams() " +param);
        System.out.println("zoneId " +zoneId);
        String zoneOffset = zoneId.getRules().getOffset(java.time.Instant.now()).toString();
        System.out.println("timezoneAreaDTO.utc_offset " +timezoneAreaDTO.utc_offset);
        System.out.println("zoneOffset "+ zoneOffset);
        sa.assertTrue(timezoneAreaDTO.utc_offset.equals(zoneOffset));
        sa.assertAll();
    }


}

///        System.out.println(responseDTO.size());
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
//