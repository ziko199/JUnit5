import com.healthycoderapp.Coder;
import com.healthycoderapp.DietPlan;
import com.healthycoderapp.DietPlanner;
import com.healthycoderapp.Gender;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("before Db Connection or init Server");
    }

    @AfterAll
    public static void AfterAll() {
        System.out.println("to close connection or stop Server");
    }

    @BeforeEach
    public void setup() {
        this.dietPlanner = new DietPlanner(20,30,50);
    }

    @AfterEach
    public void afterEach() {
        System.out.println("A unit test was finished");
    }

    @Test
    public void should_ReturnCorrectDietPlan_When_CorrectCoder() {
        // given
        Coder coder = new Coder(1.82, 75.0,26, Gender.MALE);
        DietPlan expected = new DietPlan(2202,110,73,275);

        // when
        DietPlan actual = dietPlanner.calculateDiet(coder);

        //then
        // assertEquals(expected, actual); doesn't work
        assertAll(
                () -> assertEquals(expected.getCalories(), actual.getCalories()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getFat(), actual.getFat()),
                () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate())
        );
    }

    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    public void should_ReturnCorrectDietPlan_When_CorrectCoder_RepeatedTest() {
        // given
        Coder coder = new Coder(1.82, 75.0,26, Gender.MALE);
        DietPlan expected = new DietPlan(2202,110,73,275);

        // when
        DietPlan actual = dietPlanner.calculateDiet(coder);

        //then
        // assertEquals(expected, actual); doesn't work
        assertAll(
                () -> assertEquals(expected.getCalories(), actual.getCalories()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getFat(), actual.getFat()),
                () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate())
        );
    }
}