import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWeather(String message, Model model) throws IOException {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=your_id_from_openWeather");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }

            try (InputStream inputStream = connection.getInputStream()) {
                Scanner in = new Scanner(inputStream);
                String result = "";
                while (in.hasNext()) {
                    result += in.nextLine();
                }

                JSONObject object = new JSONObject(result);
                model.setName(object.getString("name"));

                JSONObject main = object.getJSONObject("main");
                model.setTemp(main.getDouble("temp"));
                model.setHumidity(main.getDouble("humidity"));

                JSONArray getArray = object.getJSONArray("weather");
                for (int i = 0; i < getArray.length(); i++) {
                    JSONObject obj = getArray.getJSONObject(i);
                    model.setIcon((String) obj.get("icon"));
                    model.setMain((String) obj.get("main"));
                }

                return "City: " + model.getName() + "\n" +
                        "Temperature: " + model.getTemp() + "C" + "\n" +
                        "Humidity:" + model.getHumidity() + "%" + "\n" +
                        "Main: " + model.getMain() + "\n" +
                        "http://openweathermap.org/img/w/" + model.getIcon() + ".png";
            }
        } catch (MalformedURLException e) {
            throw new IOException("Invalid URL", e);
        }
    }
}
