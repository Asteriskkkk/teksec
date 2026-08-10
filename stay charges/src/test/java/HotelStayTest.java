import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HotelStayTest {
    
    @BeforeEach
    public void setUp() {
        // Setup code before each test runs
        System.out.println("Setting up test...");
    }
    
    @AfterEach
    public void cleanUp() {
        // Cleanup code after each test finishes
        System.out.println("Cleaning up test...");
    }
    
    // Valid input tests for different room types
    @Test
    public void testStandardRoomWithPositiveNights() {
        double result = HotelStay.calculateStayCost("Standard", 5);
        assertEquals(500, result, "Standard room should cost 100 per night");
    }
    
    @Test
    public void testDeluxeRoomWithPositiveNights() {
        double result = HotelStay.calculateStayCost("Deluxe", 3);
        assertEquals(600, result, "Deluxe room should cost 200 per night");
    }
    
    @Test
    public void testSuiteRoomWithPositiveNights() {
        double result = HotelStay.calculateStayCost("Suite", 2);
        assertEquals(800, result, "Suite room should cost 400 per night");
    }
    
    // Invalid input tests
    @Test
    public void testInvalidRoomType() {
        double result = HotelStay.calculateStayCost("Penthouse", 5);
        assertEquals(0, result, "Invalid room type should return 0");
    }
    
    @Test
    public void testZeroNights() {
        double result = HotelStay.calculateStayCost("Standard", 0);
        assertEquals(0, result, "Zero nights should return 0 regardless of room type");
    }
    
    @Test
    public void testNegativeNights() {
        double result = HotelStay.calculateStayCost("Deluxe", -5);
        assertEquals(0, result, "Negative nights should return 0 regardless of room type");
    }
    
    @Test
    public void testNegativeNightsWithInvalidRoom() {
        double result = HotelStay.calculateStayCost("Invalid", -3);
        assertEquals(0, result, "Negative nights with invalid room should return 0");
    }
    
    // Case-insensitive room type tests
    @Test
    public void testStandardRoomLowercaseInput() {
        double result = HotelStay.calculateStayCost("standard", 4);
        assertEquals(400, result, "Lowercase room type should work correctly");
    }
    
    @Test
    public void testDeluxeRoomMixedCaseInput() {
        double result = HotelStay.calculateStayCost("DeLuXe", 2);
        assertEquals(400, result, "Mixed case room type should work correctly");
    }
    
    @Test
    public void testSuiteRoomUppercaseInput() {
        double result = HotelStay.calculateStayCost("SUITE", 1);
        assertEquals(400, result, "Uppercase room type should work correctly");
    }
    
    // Edge cases
    @Test
    public void testSingleNightStandardRoom() {
        double result = HotelStay.calculateStayCost("Standard", 1);
        assertEquals(100, result, "Single night should work correctly");
    }
    
    @Test
    public void testLargeNumberOfNights() {
        double result = HotelStay.calculateStayCost("Suite", 100);
        assertEquals(40000, result, "Large number of nights should calculate correctly");
    }
}
