package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @ParameterizedTest
    @MethodSource("nonRussianCountries")
    void test_locale_return_appropriate_answer(Country country) {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expectedRU = "Добро пожаловать";
        String expectedEN = "Welcome";

        Assertions.assertEquals(expectedRU, localizationService.locale(Country.RUSSIA));
        Assertions.assertEquals(expectedEN, localizationService.locale(country));
    }

    private static List<Country> nonRussianCountries() {
        return Arrays.asList(Country.USA, Country.BRAZIL, Country.GERMANY);
    }
}