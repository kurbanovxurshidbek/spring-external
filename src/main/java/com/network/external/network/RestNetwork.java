package com.network.external.network;

import com.network.external.model.AligoBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RestNetwork is designed for all kind of external APIs
 */
public class RestNetwork {
    private static RestTemplate restTemplate = new RestTemplate();

    private static <T> HttpEntity entity(T input, HttpHeaders headers) {
        return new HttpEntity<T>(input, headers);
    }

    public static String get(String apiUrl, Map<String, ?> params) {
        try {
            return restTemplate.exchange(apiUrl, HttpMethod.GET, entity("", headers(false)), String.class, params).getBody();
        } catch (HttpStatusCodeException e) {
            return null;
        }
    }

    public static <T> String post(String apiUrl, T body) {
        try {
            return restTemplate.exchange(apiUrl, HttpMethod.POST, entity(body, headers(false)), String.class).getBody();
        } catch (HttpStatusCodeException e) {
            return null;
        }
    }

    public static <T> String post(String apiUrl, T body, HttpHeaders headers) {
        try {
            return restTemplate.exchange(apiUrl, HttpMethod.POST, entity(body, headers), String.class).getBody();
        } catch (HttpStatusCodeException e) {
            return null;
        }
    }

    public static <T> String post(String apiUrl, T body, Map<String, ?> params) {
        try {
            return restTemplate.exchange(apiUrl, HttpMethod.POST, entity(body, headers(false)), String.class, params).getBody();
        } catch (HttpStatusCodeException e) {
            return null;
        }
    }

    public static <T> String post(String apiUrl, MultiValueMap<String, String> formData) {
        try {
            return restTemplate.exchange(apiUrl, HttpMethod.POST, entity(formData, headers(true)), String.class).getBody();
        } catch (HttpStatusCodeException e) {
            return null;
        }
    }

    /* External Http Headers */
    private static HttpHeaders headers(boolean isFormData) {
        HttpHeaders headers = new HttpHeaders();
        if (!isFormData) {
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        } else {
            headers.setAccept(List.of(MediaType.APPLICATION_FORM_URLENCODED));
        }
        return headers;
    }

    public static HttpHeaders headersKeygen() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("ApiKey", "54738d3f9a85d609c22de0e1f365f194dad0808d8fe3eaf1eb");
        headers.add("TOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfX2NpX2xhc3RfcmVnZW5lcmF0ZSI6MTY3NzU4OTQ4OSwidXNlciI6ImV4dGdjMWEyYXA2IiwiaWF0IjoxNjc3NTg5NDg5LCJleHAiOjE2Nzc1OTMwODl9.FgfL9XflKhPI_dys75BMOeRGUlf1Iv3n8Ntt7dCbpSo");
        return headers;
    }

    /* External Http APIs */
    public static String API_ALIGO_SMS = "https://apis.aligo.in/send/";
    public static String API_BUSINESS_NUMBER = "http://api.odcloud.kr/api/nts-businessman/v1/status";
    public static String API_AUTH_TOKEN = "https://www.cookiepayments.com/payAuth/token";
    public static String API_BILL_KEYGEN = "https://www.cookiepayments.com/Subscribe/billkeygen";

    /* External Http Parameters */
    public static Map<String, ?> paramsEmpty() {
        return new HashMap<>();
    }

    public static MultiValueMap<String, String> paramsAligo(AligoBody aligoBody) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("key", aligoBody.getKey());
        map.add("user_id", aligoBody.getUser_id());
        map.add("sender", aligoBody.getSender());
        map.add("receiver", aligoBody.getReceiver());
        map.add("msg", aligoBody.getMsg());
        map.add("testmode_yn", aligoBody.getTestmode_yn());
        return map;
    }

    public static Map<String, String> paramsBusiness() {
        Map<String, String> map = new HashMap<>();
        map.put("serviceKey", "0YtQXA8on6K65ywxHGiY7nGq3iNOBv9faxgAYtqFJv4hDMAD6Tad5cXsXCyOuUwJ6kg2GaAVSVLQ75Gi7aWm4Q%3D%3D");
        map.put("returnType", "JSON");
        return map;
    }

}
