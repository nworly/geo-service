package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest {

    private GeoService geoService;

    @BeforeEach
    public void setup() {
        geoService = new GeoServiceImpl();
    }

    @Test
    void shouldReturnRussianLocation() {
        String ipRus = "172.183.178.40";
        Location expectedRuLocation = new Location("Moscow", Country.RUSSIA, null, 0);

        Assertions.assertEquals(expectedRuLocation, geoService.byIp(ipRus));
    }

    @Test
    void shouldReturnAmericanLocation() {
        String ipAm = "96.5.241.35";
        Location expectedAmLocation = new Location("New York", Country.USA, null, 0);

        Assertions.assertEquals(expectedAmLocation, geoService.byIp(ipAm));
    }

    @Test
    void shouldReturnLocationMoscow() {
        Location expectedMoscowLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);

        Assertions.assertEquals(expectedMoscowLocation, geoService.byIp(GeoServiceImpl.MOSCOW_IP));
    }

    @Test
    void shouldReturnLocationNY() {
        Location expectedNYLocation = new Location("New York", Country.USA, " 10th Avenue", 32);

        Assertions.assertEquals(expectedNYLocation, geoService.byIp(GeoServiceImpl.NEW_YORK_IP));
    }

    @Test
    void shouldReturnLocalhostLocation() {
        Location expectedLocalhostLocation = new Location(null, null, null, 0);

        Assertions.assertEquals(expectedLocalhostLocation, geoService.byIp(GeoServiceImpl.LOCALHOST));
    }
}