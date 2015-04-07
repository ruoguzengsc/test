package snapchat.tools;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonParser;


/**
 * Created by ruogu.zeng on 4/1/15.
 */
public class LoginServlet extends HttpServlet {
    protected final String APPLICATION_ID = "com.snapchat.android.nightly";
    protected final String DSIG = "1c1d95e4cacbd554e464";
    protected final String DTOKEN1I = "00001:WUTmZCd2TpOVi/OZf6J60wKbCKPSVwUYOOcGeDXLBFrXHMsN6/YyTKAEwjwgv7+W";
    protected final String PTOKEN = "APA91bEU0SnmBC8ZagoYKpZnRGak-asenPE30hjuPqEljjVV4qLKk9VaGF1EaNY3LZPm1PQxbvPmBTX6XAyOTcrB1hkLSTERMK_n5lf7gQ--et3EEIuYjN5c9K3F1HaGNBIiP76JQRBounhiFRjZ4R1zj2i45kkhzw";
    protected final String REQ_TOKEN = "930d745cebf1adf86e1d59e8f2a58f5405e94d8b19e11a87a9b46814d4c518ab";
    protected final String TIMESTAMP = "1427929707228";
    protected final String USERNAME = "ruogu1";
    protected final String PASSWORD = "sc123456";

    protected final String HTTPS = "https://";
    protected final String BASE_URL = "feelinsonice-hrd.appspot.com";
    protected final String ENDPOINT = "/loq/login";

    protected boolean getLoggedAttribute(String response){
        JsonElement jsonElement = new JsonParser().parse(response);
        JsonObject  jsonObject = jsonElement.getAsJsonObject();
        jsonObject = jsonObject.getAsJsonObject("updates_response");
        if (jsonObject == null) {
            return false;
        }
        JsonPrimitive jsonPrimitive= jsonObject.getAsJsonPrimitive("logged");
        if (jsonPrimitive == null) {
            return false;
        }
        return jsonPrimitive.getAsBoolean();
    }

    protected boolean login(String username, String password) throws IOException {
        URL urlObject = new URL(HTTPS + BASE_URL + ENDPOINT);
        HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en");
        con.setRequestProperty("Accept-Locale", "en_US");
        con.setRequestProperty("User-Agent", "Snapchat/9.5.0.26 ALPHA (SAMSUNG-SGH-I337; Android 4.4.4#I337UCUFNJ4#19; gzip)");
        con.setRequestProperty("X-Snapchat-Client-Auth-Token:", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6ImRlOTU1OTNhNTUzNDhkNmY0M2Q5OTllODAxNGFkZTNmMjBlNTQ5ODIifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUu" +
                "Y29tIiwic3ViIjoiMTAzNDEwNjk5MTk4MjMxODc2MDE1IiwiYXpwIjoiNjk0ODkzOTc5MzI5LXJpNWVjM291cjBicGg4bTVxdWYxODQxbWZvbWcwaHZmLmFwcHMuZ2" +
                "9vZ2xldXNlcmNvbnRlbnQuY29tIiwiZW1haWwiOiJ6ZW5ncnVvZ3VAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF1ZCI6IjY5NDg5Mzk3OTMyOS1s" +
                "NTlmM3BobDQyZXQ5Y2xwb28yOTZkOHJhcW9samw2cC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImlhdCI6MTQyNzkyODk5NCwiZXhwIjoxNDI3OTMyNjI0fQ" +
                ".hT2dkxmiG6gnPLvp5Ciw8S1u-f9II3Ok0L_m2CJ2ko625TR9aOZdgHY4Q03H3FDACaxuJMA3ar_hFVIsbZQr-xddAtKBH5bYP9thLshCTobVI3Qo6rJMZIRyfbAst" +
                "vxBpw3JOo36obPq71hEmwWSWXl0z-jeSgUZT6saZmVDQXQ");
        con.setRequestProperty("Host", BASE_URL);

        //Raw POST request body
        /*
        String urlParameters = "application_id=com.snapchat.android.nightly&dsig=1c1d95e4cacbd554e464&dtoken1i=00001:WUTmZCd2TpOVi/OZf6J60wKbCKPSVwUYOOcGeDXLBFrXHMsN6/YyTKAEwjwgv7+W&password=sc123456&"+
                "ptoken=APA91bEU0SnmBC8ZagoYKpZnRGak-asenPE30hjuPqEljjVV4qLKk9VaGF1EaNY3LZPm1PQxbvPmBTX6XAyOTcrB1hkLSTERMK_n5lf7gQ--et3EEIuYjN5c9K3F1HaGNBIiP76JQRBounhiFRjZ4R1zj2i45kkhzw&" +
                "req_token=930d745cebf1adf86e1d59e8f2a58f5405e94d8b19e11a87a9b46814d4c518ab&timestamp=1427929707228&username=ruogu1";
        */

        String urlParameters =
                "application_id=" + APPLICATION_ID +
                        "&dsig=" + DSIG +
                        "&dtoken1i=" + DTOKEN1I +
                        "&password=" + password +
                        "&ptoken" + PTOKEN +
                        "&req_token=" + REQ_TOKEN +
                        "&timestamp=" + TIMESTAMP +
                        "&username=" + username ;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        log("response code :  " + con.getResponseCode());

        return getLoggedAttribute(response.toString());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (login(USERNAME, PASSWORD)) {
            resp.getWriter().println("success");
        }
        else {
            resp.getWriter().println("failed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (login(USERNAME, PASSWORD)) {
            resp.getWriter().println("success");
        }
        else {
            resp.getWriter().println("failed");
        }

        /*
        JsonElement jelement = new JsonParser().parse(response.toString());
        JsonObject  jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("updates_response");
        */

        /*
        if (login(HOST + ENDPOINT, req.getParameter("username"), req.getParameter("password"))) {
            resp.getWriter().println("success");
        }
        else {
            resp.getWriter().println("failed");
        }
        */
    }
}
