package com.github.glukhovm.travel;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestMapper {

    public String convertDtoToUrl(RequestDto requestDto) {
        String flyFrom = requestDto.getDepartureAirport();
        String flyTo = requestDto.getArrivalAirport();
        String dateFrom = requestDto.getDepartureTime();
        String dateTo = dateFrom;

        if (dateFrom.isEmpty()) {
            LocalDate dateNow = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dateFrom = dateNow.format(formatter);
            dateTo = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd/MM/yyyy")).plusMonths(1).format(formatter);
        }

        String returnFrom = requestDto.getReturnTime();

        return MessageFormat.format("https://api.skypicker.com/flights?" +
                        "fly_from={0}&" + //required
                        "fly_to={1}&" +
                        "date_from={2}&" + //required
                        "date_to={3}&" + //required
                        "return_from={4}&" +
                        "return_to={4}&" +
                        "&locale=ru&partner=picky&curr=RUB&limit=1"
                , flyFrom
                , flyTo
                , dateFrom
                , dateTo
                , returnFrom
        );
    }
}
