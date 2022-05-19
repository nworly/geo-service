package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.AdditionalMatchers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


class MessageSenderImplTest {

    @ParameterizedTest
    @MethodSource("russianIPs")
    void test_send_russian_text(String ip) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ArgumentMatchers.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String expectedAnswer = "Добро пожаловать";

        Assertions.assertEquals(expectedAnswer, messageSender.send(headers));
    }

    @ParameterizedTest
    @MethodSource("americanIPs")
    void test_send_english_text(String ip) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ArgumentMatchers.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(AdditionalMatchers.not(ArgumentMatchers.eq(Country.RUSSIA))))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String expectedAnswer = "Welcome";

        Assertions.assertEquals(expectedAnswer, messageSender.send(headers));
    }

    private static List<String> russianIPs() {
        return Arrays.asList(
                "172.187.178.37",
                "172.188.178.38",
                "172.182.178.39",
                "172.183.178.40",
                "172.189.178.41",
                "172.181.178.42",
                "172.182.178.43",
                "172.192.178.44",
                "172.194.178.45"
        );
    }

    private static List<String> americanIPs() {
        return Arrays.asList(
                "96.5.241.31",
                "96.5.241.32",
                "96.5.241.33",
                "96.5.241.34",
                "96.5.241.35",
                "96.5.241.36",
                "96.5.241.37",
                "96.5.241.38"
        );
    }
}