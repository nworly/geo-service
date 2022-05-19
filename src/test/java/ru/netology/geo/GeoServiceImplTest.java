package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest {

    @Test
    void test_byIp_return_appropriate_location() {
        GeoService geoService = new GeoServiceImpl();

        String ipRus = "172.183.178.40";
        String ipAm = "96.5.241.35";

        Location expectedRuLocation = new Location("Moscow", Country.RUSSIA, null, 0);
        Location expectedAmLocation = new Location("New York", Country.USA, null, 0);
        Location expectedMoscowLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location expectedNYLocation = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location expectedLocalhostLocation = new Location(null, null, null, 0);

        Assertions.assertEquals(expectedRuLocation, geoService.byIp(ipRus));
        Assertions.assertEquals(expectedAmLocation, geoService.byIp(ipAm));
        Assertions.assertEquals(expectedMoscowLocation, geoService.byIp(GeoServiceImpl.MOSCOW_IP));
        Assertions.assertEquals(expectedNYLocation, geoService.byIp(GeoServiceImpl.NEW_YORK_IP));
        Assertions.assertEquals(expectedLocalhostLocation, geoService.byIp(GeoServiceImpl.LOCALHOST));
    }
}