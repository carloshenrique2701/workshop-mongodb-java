package com.memoryjava.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class URL {

    public static String decodeParam(String text)  {
       try{
           return URLDecoder.decode(text, "UTF-8");
       } catch (UnsupportedEncodingException e) {
           return "";
       }
    }

    public static Instant convertDate(String textDate, Instant defaultValue) {
        // Define o formatador para o padrão recebido (yyyy-MM-dd)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // Converte o texto em um LocalDate (apenas data, sem hora)
            LocalDate localDate = LocalDate.parse(textDate, formatter);
            // Define o início do dia no fuso horário UTC (ou mude para ZoneId.systemDefault())
            return localDate.atStartOfDay(ZoneId.of("UTC")).toInstant();

        } catch (DateTimeParseException e) {
            // Captura a exceção correta do pacote java.time
            return defaultValue;
        }
    }

}
