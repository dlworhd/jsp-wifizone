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


public class JSONParsing {



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

    //JSON Data -> JSONObject로 파싱
    public JSONObject parsingToJSONObject(String str) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(str);
    }


    public JSONObject JSONObjectByService(JSONObject baseJSONObject, String service) {
        return (JSONObject) baseJSONObject.get(service);
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

    public List<JSONObject> JSONArrayConvertToArrayList(JSONArray jsonArray) {
        ArrayList<JSONObject> list = new ArrayList<>();
        Calculator calculator = new Calculator();
        for (int k = 0; k < jsonArray.size(); k++) {
            JSONObject tempJson = (JSONObject) jsonArray.get(k);
            list.add(tempJson);
        }
        return list;
    }


    public List<WifiData> JSONToDBdataArray(List<JSONObject> list){

        List<WifiData> dataList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            WifiData data = new WifiData();
            data.setMgrNum((String) list.get(i).get("X_SWIFI_MGR_NO"));
            data.setFc((String) list.get(i).get("X_SWIFI_WRDOFC"));
            data.setMainNum((String) list.get(i).get("X_SWIFI_MAIN_NM"));
            data.setAdd1((String) list.get(i).get("X_SWIFI_ADRES1"));
            data.setAdd2((String) list.get(i).get("X_SWIFI_ADRES2"));
            data.setInstlFloor((String) list.get(i).get("X_SWIFI_INSTL_FLOOR"));
            data.setInstlTy((String) list.get(i).get("X_SWIFI_INSTL_TY"));
            data.setInstlMby((String) list.get(i).get("X_SWIFI_INSTL_MBY"));
            data.setSvc((String) list.get(i).get("X_SWIFI_SVC_SE"));
            data.setCmcwr((String) list.get(i).get("X_SWIFI_CMCWR"));
            data.setCstcYear((String) list.get(i).get("X_SWIFI_CNSTC_YEAR"));
            data.setInoutDoor((String) list.get(i).get("X_SWIFI_INOUT_DOOR"));
            data.setRemarS3((String) list.get(i).get("X_SWIFI_REMARS3"));
            data.setLat2(Double.parseDouble((String) list.get(i).get("LAT")));
            data.setLnt2(Double.parseDouble((String) list.get(i).get("LNT")));
            data.setWorkDttm((String) list.get(i).get("WORK_DTTM"));

            dataList.add(data);
        }

        return dataList;
    }


}
