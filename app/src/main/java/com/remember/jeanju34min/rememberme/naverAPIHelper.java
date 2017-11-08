package com.remember.jeanju34min.rememberme;

/**
 * Created by jeanju34.min on 2017-11-08.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class naverAPIHelper {
    public static final String Client_ID = "ZJ1y6CeOb34QcINVhtdq";
    public static final String Client_Secret = "1pOph4e9vk";
    static HttpURLConnection con;

    public static void example() {
        String clientId = Client_ID;//애플리케이션 클라이언트 아이디값";
        String clientSecret = Client_Secret;//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("그린팩토리", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);
           con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            new Thread() {
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

                        System.out.println(response.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
