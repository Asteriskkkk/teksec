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
    
    // Invalid input tests - missing digits before space
    @Test
    @Order(7.5)
    public void testInvalidRegistrationMissingDigitsBeforeSpace() {
        assertFalse(validator.validateVehicleRegistration("AB1 3456"), 
                    "Registration with only 1 digit before space should return false");
    }
    
    // Invalid input tests - only letters before space
    @Test
    @Order(7.6)
    public void testInvalidRegistrationOnlyLettersBeforeSpace() {
        assertFalse(validator.validateVehicleRegistration("ABCD 3456"), 
                    "Registration with only letters before space should return false");
    }
    
    // Invalid input tests - missing digits after space
    @Test
    @Order(8.5)
    public void testInvalidRegistrationMissingDigitsAfterSpace() {
        assertFalse(validator.validateVehicleRegistration("AB12 345"), 
                    "Registration with only 3 digits after space should return false");
    }
    
    // Invalid input tests - missing single digit after space
    @Test
    @Order(9.5)
    public void testInvalidRegistrationMissingSingleDigitAfterSpace() {
        assertFalse(validator.validateVehicleRegistration("AB12 12"), 
                    "Registration with only 2 digits after space should return false");
    }
    
    // Invalid input tests - extra digits
    @Test
    @Order(10.5)
    public void testInvalidRegistrationExtraDigits() {
        assertFalse(validator.validateVehicleRegistration("AB12 34567"), 
                    "Registration with extra digits should return false");
    }
    
    // Invalid input tests - null
    @Test
    @Order(11.5)
    public void testInvalidRegistrationNull() {
        assertFalse(validator.validateVehicleRegistration(null), 
                    "Null registration should return false");
    }
    
    // Invalid input tests - empty string
    @Test
    @Order(12.5)
    public void testInvalidRegistrationEmptyString() {
        assertFalse(validator.validateVehicleRegistration(""), 
                    "Empty registration should return false");
    }
    
    // Invalid input tests - only letters
    @Test
    @Order(13.5)
    public void testInvalidRegistrationOnlyLetters() {
        assertFalse(validator.validateVehicleRegistration("ABCD EFGH"), 
                    "Registration with only letters should return false");
    }
    
    // Invalid input tests - only digits
    @Test
    @Order(14.5)
    public void testInvalidRegistrationOnlyDigits() {
        assertFalse(validator.validateVehicleRegistration("12 3456"), 
                    "Registration with only digits should return false");
    }
}
