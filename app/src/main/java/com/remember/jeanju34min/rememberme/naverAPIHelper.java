package com.remember.jeanju34min.rememberme;

/**
 * Created by jeanju34.min on 2017-11-08.
 */

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class naverAPIHelper {
    public static final String Client_ID = "ZJ1y6CeOb34QcINVhtdq";
    public static final String Client_Secret = "1pOph4e9vk";
    static JSONObject mJObject;
    static HttpURLConnection con;

    public static void search() {
        String clientId = Client_ID;//애플리케이션 클라이언트 아이디값";
        String clientSecret = Client_Secret;//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("망원동 몽글몽글", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/local.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);
           con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            new Thread() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                public void run() {
                    int responseCode = 0;
                    try {
                        responseCode = con.getResponseCode();
                        BufferedReader br;
                        if (responseCode == 200) { // 정상 호출
                            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        } else {  // 에러 발생
                            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                        }
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = br.readLine()) != null) {
                            response.append(inputLine);
                        }
                        br.close();

                        mJObject = new JSONObject(response.toString());
                        int count = mJObject.getInt("total");

                        if(count > 0){
                            // TODO : count만큼 돌아서 검색결과 전달
                            // TODO : naver API가 가게 정보 URL 제공하지 않음 -> kakao에서는 제공해준다고 하나 회사에서 확인할 수 없음
                        }

                        System.out.println(response.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getTitle(int pos) throws JSONException {
        JSONArray jarray = mJObject.getJSONArray("items");
        JSONObject jObject = jarray.getJSONObject(pos);  // JSONObject 추출
        String title = jObject.getString("title");

        if(title.contains("<b>")){
            title = title.replace("<b>","");
            //Log.v("MY_TAG", "<b> contains? "+title.contains("<b>")+" "+title);
        }
        if(title.contains("</b>")){
            title = title.replace("</b>","");
        }

        return title;
    }
    public String getAddress(int pos) throws JSONException {
        JSONArray jarray = mJObject.getJSONArray("items");
        JSONObject jObject = jarray.getJSONObject(pos);  // JSONObject 추출
        String address = jObject.getString("address");

        if(address.contains("<b>")){

            address.replace("<b>","");
        }
        if(address.contains("</b>")){
            address.replace("</b>","");
        }

        return address;
    }
    public int getMapx(int pos) throws JSONException {
        JSONArray jarray = mJObject.getJSONArray("items");
        JSONObject jObject = jarray.getJSONObject(pos);  // JSONObject 추출

        return jObject.getInt("mapx");
    }
    public int getMapy(int pos) throws JSONException {
        JSONArray jarray = mJObject.getJSONArray("items");
        JSONObject jObject = jarray.getJSONObject(pos);  // JSONObject 추출

        return jObject.getInt("mapy");
    }


}
