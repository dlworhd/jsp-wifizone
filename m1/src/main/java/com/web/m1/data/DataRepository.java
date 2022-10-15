package com.web.m1.data;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class DataRepository {

    DataDAO dataDAO = new DataDAO();
    List<DataDAO> dataList = new ArrayList<>();


    final String KEY = "465a4d786c646c77313133524e43717a";
    final String URL = "http://openapi.seoul.go.kr:8088";
    final String MAIN_SERVICE = "TbPublicWifiInfo";
    final String SUB_SERVICE = "row";


    public String importData(String fileType, String service, String start, String end) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
        urlBuilder.append("/" + URLEncoder.encode(KEY, "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" + URLEncoder.encode(fileType, "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode(service, "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode(start, "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode(end, "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
        urlBuilder.append("/" + URLEncoder.encode("LAT", "UTF-8")); /* 서비스별 추가 요청인자들*/
        urlBuilder.append("/" + URLEncoder.encode("LNT", "UTF-8")); /* 서비스별 추가 요청인자들*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String str = rd.readLine();

        rd.close();
        conn.disconnect();

        return str;
    }

    public JSONObject parsingToJSONObject(String str) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(str);
    }

    public JSONObject JSONObjectByService(JSONObject baseJSONObject, String service) {
        return (JSONObject) baseJSONObject.get(service);
    }

    public String getKEY() {
        return KEY;
    }

    public String getURL() {
        return URL;
    }

    public String getMAIN_SERVICE() {
        return MAIN_SERVICE;
    }

    public String getSUB_SERVICE() {
        return SUB_SERVICE;
    }

    public JSONArray JSONArrayByService(JSONObject baseJSONObject, String service) {
        return (JSONArray) baseJSONObject.get(service);
    }

    public List<DataDAO> JSONArrayConvertToArrayList(JSONArray jsonArray) {
        ArrayList<JSONObject> list = new ArrayList<>();
        for (int k = 0; k < jsonArray.size(); k++) {
            JSONObject tempJson = (JSONObject) jsonArray.get(k);
            list.add(tempJson);
        }

        for (int i = 0; i < list.size(); i++) {
            DataDAO data = new DataDAO();
            data.setX_SWIFI_MGR_NO((String) list.get(i).get("X_SWIFI_MGR_NO"));
            data.setX_SWIFI_WRDOFC((String) list.get(i).get("X_SWIFI_WRDOFC"));
            data.setX_SWIFI_MAIN_NM((String) list.get(i).get("X_SWIFI_MAIN_NM"));
            data.setX_SWIFI_ADRES1((String) list.get(i).get("X_SWIFI_ADRES1"));
            data.setX_SWIFI_ADRES2((String) list.get(i).get("X_SWIFI_ADRES2"));
            data.setX_SWIFI_INSTL_FLOOR((String) list.get(i).get("X_SWIFI_INSTL_FLOOR"));
            data.setX_SWIFI_INSTL_TY((String) list.get(i).get("X_SWIFI_INSTL_TY"));
            data.setX_SWIFI_INSTL_MBY((String) list.get(i).get("X_SWIFI_INSTL_MBY"));
            data.setX_SWIFI_SVC_SE((String) list.get(i).get("X_SWIFI_SVC_SE"));
            data.setX_SWIFI_CMCWR((String) list.get(i).get("X_SWIFI_CMCWR"));
            data.setX_SWIFI_CNSTC_YEAR((String) list.get(i).get("X_SWIFI_CNSTC_YEAR"));
            data.setX_SWIFI_INOUT_DOOR((String) list.get(i).get("X_SWIFI_INOUT_DOOR"));
            data.setX_SWIFI_REMARS3((String) list.get(i).get("X_SWIFI_REMARS3"));
            data.setLAT((String) list.get(i).get("LAT"));
            data.setLNT((String) list.get(i).get("LNT"));
            data.setWORK_DTTM((String) list.get(i).get("WORK_DTTM"));
            dataList.add(data);
        }

        return dataList;
    }

    public void setDist(double LAT, double LNT){ //현재 좌표를 넣음
        for (int i = 0; i < dataList.size(); i++) {
            double LAT2 = Double.parseDouble(dataList.get(i).getLAT());
            double LNT2 = Double.parseDouble(dataList.get(i).getLNT());
            double d = distance(LAT, LNT, LAT2, LNT2);
            dataList.get(i).setDist(d);
        }
    }



    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1609.344;

        return dist; //단위 meter
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
