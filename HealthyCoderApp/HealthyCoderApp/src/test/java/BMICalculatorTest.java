import com.healthycoderapp.BMICalculator;
import com.healthycoderapp.Coder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest {

    private String environment = "dev";

    @Nested
    public class isDietRecommendedTest {

        @Test
        @DisplayName(">>>> sample method display name")
        public void should_ReturnTrue_When_DietRecommended() {
            // given
            double weight = 89.0;
            double height = 1.72;

            // when
            boolean recommended = BMICalculator.isDietRecommended(weight,height);

            //then
            assertTrue(recommended);
        }

        @Test
        public void should_ReturnFalse_When_DietNotRecommended() {
            // given
            double weight = 50.0;
            double height = 1.92;

            // when
            boolean recommended = BMICalculator.isDietRecommended(weight,height);

            //then
            assertFalse(recommended);
        }

        @Test
        public void should_ThrowArithmeticException_When_HeightZero() {
            // given
            double weight = 50.0;
            double height = 0.0;

            // when
            Executable executable = () -> BMICalculator.isDietRecommended(weight,height);

            //then
            assertThrows(ArithmeticException.class, executable);
        }
    }

    @Nested
    public class FindCoderWithWorstBMITest {
        @Test
        public void should_ReturnCodeWithWorstBMI_when_CoderListNotEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80,60.0));
            coders.add(new Coder(1.82,98.0));
            coders.add(new Coder(1.82,67.7));

            // when
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertAll(
                    () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                    () -> assertEquals(98.0, coderWorstBMI.getWeight())
            );
        }

        @Test
        public void should_ReturnCodeWithWorstBMI_In1Ms_when_CoderListHas10000Elements() {
            assumeTrue(environment.equals("prod"));
            // given
            List<Coder> coders = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                coders.add(new Coder(1.0 + i, 10.0 + i));
            }

            // when
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertTimeout(Duration.ofMillis(6), executable);
        }

        @Test
        public void should_ReturnNullWorstBMI_when_CoderListEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();

            // when
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertNull(coderWorstBMI);
        }
    }

    @Nested
    public class GetBMIScoreTest {
        @Test
        public void should_ReturnCorrectBMIScoreArray_when_CoderListNotEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80,60.0));
            coders.add(new Coder(1.82,98.0));
            coders.add(new Coder(1.82,67.7));

            double[] expectedValue = {18.52, 29.59, 20.44};

            // when
            double[] bmiScores = BMICalculator.getBMIScores(coders);

            //then
            assertArrayEquals(expectedValue, bmiScores);
        }
    }

    @Nested
    public class TestsWithParameterized {
        // ----------------Parameterized Test with one Value Source ----------------
        @ParameterizedTest
        @ValueSource(doubles ={80.0, 89.0, 95.0, 110.0})
        public void returnTrue_When_DietRecommended_ParameterizedWithOneValue(Double coderWeight) {
            System.out.println("----------- Parameterized Test with one Value Source -----------");
            // given
            double weight = coderWeight;
            double height = 1.72;

            // when
            boolean recommended = BMICalculator.isDietRecommended(weight,height);

            //then
            assertTrue(recommended);
        }

        // ---------------- Parameterized Test with multi Value Source ----------------
        @ParameterizedTest(name = "weight = {0}, height = {1}")
        @CsvSource(value = {"80.0, 1.72", "89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
        public void returnTrue_When_DietRecommended_ParameterizedWithMultiValues(Double coderWeight,
                                                                                 Double coderHeight) {
            System.out.println("------------ Parameterized Test with multi Value Sources ----------");
            // given
            double weight = coderWeight;
            double height = coderHeight;

            // when
            boolean recommended = BMICalculator.isDietRecommended(weight,height);

            //then
            assertTrue(recommended);
        }

        // ---------------- Parameterized Test with file CSV Source ----------------
        @ParameterizedTest(name = "weight = {0}, height = {1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
        public void returnTrue_When_DietRecommended_ParameterizedWith_CSV_File(Double coderWeight,
                                                                               Double coderHeight) {
            System.out.println("------------ Parameterized Test with file CSV Source ----------");
            // given
            double weight = coderWeight;
            double height = coderHeight;

            // when
            boolean recommended = BMICalculator.isDietRecommended(weight,height);

            //then
            assertTrue(recommended);
        }
    }
}