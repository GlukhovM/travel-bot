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
    private static String requestURL = "https://api.skypicker.com/flights?fly_from=CEK&fly_to=LED&date_from=01/06/2020&date_to=01/07/2020&partner=picky&curr=RUB&limit=3";

    public static void main(String[] args) {
        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(new HttpGet(requestURL))
        ) {
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("Oh no, Error: " + response.getStatusLine().toString() + "\n" + IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8));
            } else {
                //System.out.println("Status line: " + response.getStatusLine()); // do not output
                if (entity != null) {
                    String data = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8);
                    //System.out.println("Data :" + data); // do not output

                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                    ResponseDto ResponseDto = objectMapper.readValue(data, ResponseDto.class);
                    System.out.println(ResponseDto);
                }
            }
        } catch (Exception cause) {
            cause.printStackTrace();
        }
    }
}
