package com.realestateapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomApartmentGeneratorTest {

    private static final double MAX_MULTIPLIER = 4.0;

    @Nested
    public class GeneratorDefaultParamsTests {
        private RandomApartmentGenerator randomApartmentGenerator;

        @BeforeEach
        public void setup() {
            this.randomApartmentGenerator = new RandomApartmentGenerator();
        }

        @RepeatedTest(10)
        public void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
            // given
            double minArea = 30.0;
            double maxArea = minArea * MAX_MULTIPLIER;
            BigDecimal minPricePerSquareMeter = new BigDecimal(3000.0);
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

            // when
            Apartment apartment = randomApartmentGenerator.generate();

            // then
            BigDecimal maxApartmentPrice = new BigDecimal(apartment.getArea()).multiply(maxPricePerSquareMeter);
            BigDecimal minApartmentPrice = new BigDecimal(apartment.getArea()).multiply(minPricePerSquareMeter);
            assertAll(
                    () -> assertTrue(apartment.getArea() >= minArea),
                    () -> assertTrue(apartment.getArea() <= maxArea),
                    () -> assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0),
                    () -> assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0)
            );
        }
    }

    @Nested
    public class GeneratorCustomParamsTests {
        private RandomApartmentGenerator randomApartmentGenerator;
        private double minArea = 15.0;
        private BigDecimal minPricePerSquareMeter = new BigDecimal(5000.0);

        @BeforeEach
        public void setup() {
            this.randomApartmentGenerator = new RandomApartmentGenerator(minArea, minPricePerSquareMeter);
        }

        @RepeatedTest(10)
        public void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice() {
            // given
            double maxArea = minArea * MAX_MULTIPLIER;
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

            // when
            Apartment apartment = randomApartmentGenerator.generate();

            // then
            BigDecimal maxApartmentPrice = new BigDecimal(apartment.getArea()).multiply(maxPricePerSquareMeter);
            BigDecimal minApartmentPrice = new BigDecimal(apartment.getArea()).multiply(minPricePerSquareMeter);
            assertAll(
                    () -> assertTrue(apartment.getArea() >= minArea),
                    () -> assertTrue(apartment.getArea() <= maxArea),
                    () -> assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0),
                    () -> assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0)
            );
        }
    }
}