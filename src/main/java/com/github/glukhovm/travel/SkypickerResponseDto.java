package com.github.glukhovm.travel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkypickerResponseDto {
    private String currency;
    private List<OptionDto> data;

    public static String epochToUtc(long epochTime) { //DateUtil named!!!
        LocalDateTime localDateTime = LocalDateTime
                .ofInstant(Instant.ofEpochSecond(epochTime), ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String userFriendlyTime = localDateTime.format(formatter);
        return userFriendlyTime;
    }

    @Override
    public String toString() {
        if (data.isEmpty()) {
            return "Ошибка: Проверь введенные даты!";
        } else {
            String tickets = "Цена: " + data.get(0).price + " " + currency + ", маршрут: " + "\n";
            for (int i = 0; i < data.get(0).getRoute().size(); i++) {
                tickets += data.get(0).route.get(i).flyFrom + " -> " + data.get(0).route.get(i).flyTo + "\n" +
                        "Из: " + data.get(0).route.get(i).cityFrom + "\n" +
                        "Время вылета: " + epochToUtc(data.get(0).route.get(i).dTime) + "\n" +
                        "в : " + data.get(0).route.get(i).cityTo + "\n" +
                        "Время прилета: " + epochToUtc(data.get(0).route.get(i).aTime) + "\n" +
                        "Код авиакомпании: " + data.get(0).route.get(i).airline + "\n" + "\n";
            }
            return tickets;
        }
    }

    //data
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class OptionDto {
        private int price;
        private List<RouteDto> route;
    }

    //route
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class RouteDto {
        @JsonProperty("dTime")
        private long dTime;
        @JsonProperty("aTime")
        private long aTime;
        private String cityFrom;
        private String cityTo;
        private String flyFrom;
        private String flyTo;
        private String airline;
    }
}



