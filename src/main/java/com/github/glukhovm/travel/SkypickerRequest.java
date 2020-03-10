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

public class SkypickerRequest {

    private RequestDto requestOptions;


    public SkypickerRequest(RequestDto requestOptions) {
        this.requestOptions = requestOptions;
    }

    public String getResponse() {
        //String requestURL = "https://api.skypicker.com/flights?fly_from="+requestOptions.getDepartureAirport()+"&fly_to=LED&date_from=01/06/2020&date_to=01/07/2020&partner=picky&curr=RUB&limit=1";
        String requestURL = String.format("https://api.skypicker.com/flights?fly_from=%s&fly_to=%s&date_from=%s&date_to=%s&partner=picky&curr=RUB&limit=1" //Message.Format
                , requestOptions.getDepartureAirport()
                , requestOptions.getArrivalAirport()
                , requestOptions.getDepartureTime()
                , requestOptions.getReturnTime()
        );
        String temp = null;
        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(new HttpGet(requestURL))
        ) {
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                if (entity != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                    SkypickerResponseDto responseDto = objectMapper.readValue(entity.getContent(), SkypickerResponseDto.class);
                    System.out.println(responseDto);
                    temp = responseDto.toString();
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
        return temp;
    }
}
