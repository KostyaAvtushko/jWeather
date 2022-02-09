package ru.jWeather;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherController {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private String result = "";

    public String weatherRequest(String city) throws Exception{
        HttpGet request = new HttpGet("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=de45be9a3632b0b8dbdfcea3fe09004b");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String result = "";
            if(entity != null){
                result = EntityUtils.toString(entity);
            }
            try  {
                JSONObject json = new JSONObject(result);
                String temp = json.getJSONObject("main").getString("temp");
                Double doubleTemp = Double.parseDouble(temp) - 273.15;
                String resultTemp = Double.toString(Math.round(doubleTemp));
                String feelsLikeTemp = json.getJSONObject("main").getString("feels_like");
                Double doubleFeelsLikeTemp = Double.parseDouble(feelsLikeTemp) - 273.15;
                String resultFeelsLikeTemp = Double.toString(Math.round(doubleFeelsLikeTemp));
                String wind = json.getJSONObject("wind").getString("speed");
                result = ":fog:Сейчас температура в городе: "+ resultTemp + "℃"
                        + "\n:snowflake:Ощущается как: " + resultFeelsLikeTemp + "℃"
                        + "\n:dash:Скорость ветра: " + wind +"м/c";

            }catch (JSONException e){
                result = "Такого города нет в нашей базе :)";
            }
            return  result;
        }
    }
}
