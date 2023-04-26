package timezone;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jdk.jfr.Description;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TimezoneTest {
    final String baseURL = "http://worldtimeapi.org";

    OkHttpClient client = new OkHttpClient();

    @Test
    @Description("Check that response have 350 timezones ")
    void getListOfTimeZones() throws Exception {
        String endPoint = "/api/timezone";
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();

        Request request = new Request.Builder()
                .url(baseURL + endPoint)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        List<String> responseDTO = gson.fromJson(responseBody, type);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseDTO.size(), Timezones.expectedZones.size());
        softAssert.assertTrue(responseDTO.containsAll(Timezones.expectedZones));
        softAssert.assertAll();
    }

    @Test
    @Description("Check that response does not have duplicates")
    void checkDuplicates() throws Exception {
        String endPoint = "/api/timezone";
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();

        Request request = new Request.Builder()
                .url(baseURL + endPoint)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        List<String> responseDTO = gson.fromJson(responseBody, type);

        Set<String> set = new HashSet<>(responseDTO);
        Assert.assertEquals(set.size(), Timezones.expectedZones.size());
    }
}
