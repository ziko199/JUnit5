import com.healthycoderapp.ActivityCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ActivityCalculatorTest {

    @Test
    void should_ReturnBad_When_AvgBelow20() {
        //given
        int weeklyCardioMins = 40;
        int weeklyWorkouts = 1;
        String expectedValue = "bad";
        //when
        String actualValue = ActivityCalculator.rateActivityLevel(
                weeklyCardioMins, weeklyWorkouts);

        //then
        assertEquals(expectedValue,actualValue);
    }

    @Test
    void should_ReturnAverage_When_AvgBetween20And40() {
        int weeklyCardioMins = 40;
        int weeklyWorkouts = 3;
        String expectedValue = "average";
        //when
        String actualValue = ActivityCalculator.rateActivityLevel(
                weeklyCardioMins, weeklyWorkouts);

        //then
        assertEquals(expectedValue,actualValue);
    }

    @Test
    void should_ReturnGood_When_AvgAbove40() {
        int weeklyCardioMins = 40;
        int weeklyWorkouts = 7;
        String expectedValue = "good";
        //when
        String actualValue = ActivityCalculator.rateActivityLevel(
                weeklyCardioMins, weeklyWorkouts);

        //then
        assertEquals(expectedValue,actualValue);
    }

    @Test
    void should_ThrowException_When_InputBelowZero() {
        int weeklyCardioMins = -40;
        int weeklyWorkouts = 1;

        //when
        Executable executable = () ->  {
            ActivityCalculator.rateActivityLevel(weeklyCardioMins, weeklyWorkouts);
        };
        //then
        assertThrows(RuntimeException.class, executable);
    }
}