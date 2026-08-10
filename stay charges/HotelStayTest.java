import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    public void testStandardRoomWithPositiveNights() {
        double result = HotelStay.calculateStayCost("Standard", 5);
        assertEquals(500, result, "Standard room should cost 100 per night");
    }
    
    @Test
    @Order(2)
    public void testDeluxeRoomWithPositiveNights() {
        double result = HotelStay.calculateStayCost("Deluxe", 3);
        assertEquals(600, result, "Deluxe room should cost 200 per night");
    }
    
    @Test
    @Order(3)
    public void testSuiteRoomWithPositiveNights() {
        double result = HotelStay.calculateStayCost("Suite", 2);
        assertEquals(800, result, "Suite room should cost 400 per night");
    }
    
    // Invalid input tests
    @Test
    @Order(4)
    public void testInvalidRoomType() {
        double result = HotelStay.calculateStayCost("Penthouse", 5);
        assertEquals(0, result, "Invalid room type should return 0");
    }
    
    @Test
    @Order(5)
    public void testZeroNights() {
        double result = HotelStay.calculateStayCost("Standard", 0);
        assertEquals(0, result, "Zero nights should return 0 regardless of room type");
    }
    
    @Test
    @Order(6)
    public void testNegativeNights() {
        double result = HotelStay.calculateStayCost("Deluxe", -5);
        assertEquals(0, result, "Negative nights should return 0 regardless of room type");
    }
    
    @Test
    @Order(7)
    public void testNegativeNightsWithInvalidRoom() {
        double result = HotelStay.calculateStayCost("Invalid", -3);
        assertEquals(0, result, "Negative nights with invalid room should return 0");
    }
    
    // Case-insensitive room type tests
    @Test
    @Order(8)
    public void testStandardRoomLowercaseInput() {
        double result = HotelStay.calculateStayCost("standard", 4);
        assertEquals(400, result, "Lowercase room type should work correctly");
    }
    
    @Test
    @Order(9)
    public void testDeluxeRoomMixedCaseInput() {
        double result = HotelStay.calculateStayCost("DeLuXe", 2);
        assertEquals(400, result, "Mixed case room type should work correctly");
    }
    
    @Test
    @Order(10)
    public void testSuiteRoomUppercaseInput() {
        double result = HotelStay.calculateStayCost("SUITE", 1);
        assertEquals(400, result, "Uppercase room type should work correctly");
    }
    
    // Edge cases
    @Test
    @Order(11)
    public void testSingleNightStandardRoom() {
        double result = HotelStay.calculateStayCost("Standard", 1);
        assertEquals(100, result, "Single night should work correctly");
    }
    
    @Test
    @Order(12)
    public void testLargeNumberOfNights() {
        double result = HotelStay.calculateStayCost("Suite", 100);
        assertEquals(40000, result, "Large number of nights should calculate correctly");
    }
}
