package com.realestateapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRaterTest {

    @Nested
    @DisplayName("for rateApartment Methods: ")
    public class RateApartment {

        @Test
        public void should_ReturnZero_When_CheapThresholdBelowZero() {
            //given
            Apartment apartment = new Apartment(2.0,new BigDecimal(1000.0));
            int expectedValue = 0;

            //when
            int actualValue = ApartmentRater.rateApartment(apartment);

            //then
            assertEquals(expectedValue, actualValue);
        }

        @Test
        public void should_ReturOne_When_CheapThresholdAboveZero_And_ModerateThresholdBelowZero() {
            //given
            Apartment apartment = new Apartment(1.0,new BigDecimal(6000.0));
            int expectedValue = 1;

            //when
            int actualValue = ApartmentRater.rateApartment(apartment);

            //then
            assertEquals(expectedValue, actualValue);
        }

        @Test
        public void should_ReturnTwo_When_Cheap_And_ModerateThresholdAboveZero() {
            //given
            Apartment apartment = new Apartment(1.0,new BigDecimal(100000.0));
            int expectedValue = 2;

            //when
            int actualValue = ApartmentRater.rateApartment(apartment);

            //then
            assertEquals(expectedValue, actualValue);
        }

        @Test
        public void should_ReturnErrorValue_When_InCorrectApartment() {
            //given
            Apartment apartment = new Apartment(0.0,new BigDecimal(100000.0));
            int expectedValue = -1;

            //when
            int actualValue = ApartmentRater.rateApartment(apartment);

            //then
            assertEquals(expectedValue, actualValue);
        }


        @ParameterizedTest
        @CsvSource(value = {"72.0, 250000.0, 0", "48.0, 350000.0, 1", "30.0, 600000.0, 2"})
        void should_ReturnCorrectRating_When_CorrectApartment(Double area, Double price, int rating) {
            //given
            Apartment apartment = new Apartment(area, new BigDecimal(price));
            int expectedValue = rating;

            //when
            int actualValue = ApartmentRater.rateApartment(apartment);

            //then
            assertEquals(expectedValue, actualValue);
        }
    }


    @Nested
    @DisplayName("for calculateAverageRating Methods: ")
    public class CalculateAverageRatingTest {

        @Test
        public void should_ThrowException_When_EmptyApartmentsList() {
            //given
            List<Apartment> apartments = new ArrayList<>();

            //when
            Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);

            //then
            assertThrows(RuntimeException.class, executable);
        }

        @Test
        public void should_ReturnZero_When_CorrectApartmentList() {
            //given
            List<Apartment> apartments = new ArrayList<>();
            apartments.add(new Apartment(1,new BigDecimal(1000.0)));
            apartments.add(new Apartment(2,new BigDecimal(2000.0)));
            apartments.add(new Apartment(3,new BigDecimal(3000.0)));

            double expectedValue = 0;
            //when
            double actualValue = ApartmentRater.calculateAverageRating(apartments);
            //then
            assertEquals(expectedValue,actualValue);
        }

        @Test
        public void should_CalucateAverageRanting_When_CorrectApartmentList() {
            //given
            List<Apartment> apartments = new ArrayList<>();
            apartments.add(new Apartment(5,new BigDecimal(80000.0)));
            apartments.add(new Apartment(2,new BigDecimal(70000.0)));
            apartments.add(new Apartment(3,new BigDecimal(90000.0)));

            double expectedValue = 2;
            //when
            double actualValue = ApartmentRater.calculateAverageRating(apartments);
            //then
            assertEquals(expectedValue,actualValue);
        }
    }
}