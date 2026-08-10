import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VehicleRegistrationValidatorTest {
    
    private VehicleRegistrationValidator validator;
    
    @BeforeEach
    public void setUp() {
        validator = new VehicleRegistrationValidator();
        System.out.println("Setting up test...");
    }
    
    @AfterEach
    public void cleanUp() {
        System.out.println("Cleaning up test...");
    }
    
    // Valid input tests
    @Test
    @Order(1)
    public void testValidRegistrationAB123456() {
        assertTrue(validator.validateVehicleRegistration("AB12 3456"), 
                   "Valid registration AB12 3456 should return true");
    }
    
    @Test
    @Order(2)
    public void testValidRegistrationCD987654() {
        assertTrue(validator.validateVehicleRegistration("CD98 7654"), 
                   "Valid registration CD98 7654 should return true");
    }
    
    @Test
    @Order(3)
    public void testValidRegistrationXY505050() {
        assertTrue(validator.validateVehicleRegistration("XY50 5050"), 
                   "Valid registration XY50 5050 should return true");
    }
    
    // Invalid input tests - missing space
    @Test
    @Order(4)
    public void testInvalidRegistrationMissingSpace() {
        assertFalse(validator.validateVehicleRegistration("AB123456"), 
                    "Registration without space should return false");
    }
    
    // Invalid input tests - lowercase letters
    @Test
    @Order(5)
    public void testInvalidRegistrationLowercaseLetters() {
        assertFalse(validator.validateVehicleRegistration("ab12 3456"), 
                    "Registration with lowercase letters should return false");
    }
    
    // Invalid input tests - lowercase mixed
    @Test
    @Order(6)
    public void testInvalidRegistrationMixedCaseLetters() {
        assertFalse(validator.validateVehicleRegistration("Ab12 3456"), 
                    "Registration with mixed case letters should return false");
    }
    
    // Invalid input tests - wrong format
    @Test
    @Order(7)
    public void testInvalidRegistrationWrongFormat() {
        assertFalse(validator.validateVehicleRegistration("12AB 3456"), 
                    "Registration with digits before letters should return false");
    }
    
    // Invalid input tests - missing digits
    @Test
    @Order(8)
    public void testInvalidRegistrationMissingDigits() {
        assertFalse(validator.validateVehicleRegistration("AB12 345"), 
                    "Registration with insufficient digits should return false");
    }
    
    // Invalid input tests - extra digits
    @Test
    @Order(9)
    public void testInvalidRegistrationExtraDigits() {
        assertFalse(validator.validateVehicleRegistration("AB12 34567"), 
                    "Registration with extra digits should return false");
    }
    
    // Invalid input tests - null
    @Test
    @Order(10)
    public void testInvalidRegistrationNull() {
        assertFalse(validator.validateVehicleRegistration(null), 
                    "Null registration should return false");
    }
    
    // Invalid input tests - empty string
    @Test
    @Order(11)
    public void testInvalidRegistrationEmptyString() {
        assertFalse(validator.validateVehicleRegistration(""), 
                    "Empty registration should return false");
    }
    
    // Invalid input tests - only letters
    @Test
    @Order(12)
    public void testInvalidRegistrationOnlyLetters() {
        assertFalse(validator.validateVehicleRegistration("ABCD EFGH"), 
                    "Registration with only letters should return false");
    }
    
    // Invalid input tests - only digits
    @Test
    @Order(13)
    public void testInvalidRegistrationOnlyDigits() {
        assertFalse(validator.validateVehicleRegistration("12 3456"), 
                    "Registration with only digits should return false");
    }
}
