package dateTime;

import com.google.gson.Gson;
import jdk.jfr.Description;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.ZoneId;
import java.util.*;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.time.Instant;
import java.time.ZonedDateTime;

public class DateTimeTest {
    Gson gson;
    OkHttpClient client;
    String endPoint = "/api/timezone/";
    final String baseURL = "http://worldtimeapi.org";



    @BeforeClass
    public void before() throws IOException {
        gson = new Gson();
        client = new OkHttpClient();

    }
    @BeforeTest
    public void beforeTest() {
    }
    public String getRandomParams() throws IOException {
//        String endPoint = "/api/timezone";
        client = new OkHttpClient();
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
    @Description("Check correctness DateTime")
        // check what? what's check datetime
    void checkCorrectnessDateTime() throws IOException {
        SoftAssert sa = new SoftAssert();
        String param;
//        Request request;
        Response response;
        TimezoneAreaDTO timezoneAreaDTO;
        param = getRandomParams();
        Request request = new Request.Builder()
                .url(baseURL + endPoint + param).addHeader("auth", "12345")
                .get()
                .build();
        response = client.newCall(request).execute();
        timezoneAreaDTO = gson.fromJson(response.body().string(), TimezoneAreaDTO.class);
        long responseUnixTime = timezoneAreaDTO.unixtime;
        long localUnixTimeInSeconds = System.currentTimeMillis() / 1000L;
        ZoneId zoneId = ZoneId.of(param);
        Instant instant = Instant.ofEpochSecond(responseUnixTime); // Convert Unix time to Instant
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId); // Convert Instant to ZonedDateTime with the specified time zone
        String timezoneAreaDTODateTimeString = timezoneAreaDTO.datetime;
        String timezoneAreaDTODateTimeString2 = timezoneAreaDTODateTimeString.substring(0, 19);
        String zoneDateTimeString = zonedDateTime.toString();
        String zoneDateTimeString2 = zoneDateTimeString.substring(0, 19);

        sa.assertEquals(responseUnixTime / 10, localUnixTimeInSeconds / 10);
        sa.assertEquals(response.code(), 200);
        sa.assertEquals(timezoneAreaDTODateTimeString2, zoneDateTimeString2);
        sa.assertAll();

    }
    @Test
    void POST_Test() {
        final MediaType jsonHeader = MediaType.get("application/json; charset=utf-8");
        client = new OkHttpClient();
//        String json = "Hello w";
        String str = "{ \"name2\": \"John Dou\", \"email\": \"johndoe@example.com\" }";
        RequestBody requestBody = RequestBody.create(jsonHeader, str);

        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            Map map = gson.fromJson(response.body().string(), Map.class);
            Assert.assertTrue(map.containsKey("id"));
            //map.containsKey("id")
            //System.out.println("Ok");
            //System.out.println(response.body().string());
        } catch (IOException e) {

            //System.out.println("Error");
//            e.printStackTrace();
        }
    }
    @Test
    void GET_Test() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts/1")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    void PUT_Test(){

        final MediaType JSON = MediaType.get("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();
            // Example JSON payload
            String json = "{ \"id\": 1, \"title\": \"New Title\", \"body\": \"New Body\", \"userId\": 1 }";
            RequestBody requestBody = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/posts/1")
                    .put(requestBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                System.out.println(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();

        }
    }
    @Test
    void DELETE_Test(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts/1")
                .delete()
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



