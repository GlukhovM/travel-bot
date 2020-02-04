package com.github.glukhovm.travel;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.nio.charset.StandardCharsets;

public class GetRequest {
    private static final String requestURL = "https://api.skypicker.com/flights?fly_from=CEK&fly_to=LED&date_from=01/06/2020&date_to=01/07/2020&partner=picky&curr=RUB&limit=3";

    public static void main(String[] args) {
        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(new HttpGet(requestURL))
        ) {
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                if (entity != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                    ResponseDto responseDto = objectMapper.readValue(entity.getContent(), ResponseDto.class);
                    System.out.println(responseDto);
                } else {
                    System.out.println(response.getStatusLine());
                }
            } else {
                if (entity != null) {
                    System.out.println("Oh no, Error: " + response.getStatusLine() + "\n" + IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8));
                } else {
                    System.out.println(response.getStatusLine());
                }
            }
        } catch (Exception cause) {
            cause.printStackTrace();
        }
    }
}
