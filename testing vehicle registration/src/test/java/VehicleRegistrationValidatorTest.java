import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VehicleRegistrationValidatorTest {

    private VehicleRegistrationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new VehicleRegistrationValidator();
    }

    @AfterEach
    void tearDown() {
        validator = null;
    }

    @Test
    void validateVehicleRegistrationShouldReturnTrueForValidRegistration() {
        assertTrue(validator.validateVehicleRegistration("AB12 3456"));
    }

    @Test
    void validateVehicleRegistrationShouldReturnFalseWhenSpaceIsMissing() {
        assertFalse(validator.validateVehicleRegistration("AB123456"));
    }

    @Test
    void validateVehicleRegistrationShouldReturnFalseForLowercaseLetters() {
        assertFalse(validator.validateVehicleRegistration("ab12 3456"));
    }

    @Test
    void validateVehicleRegistrationShouldReturnFalseForWrongLetterCount() {
        assertFalse(validator.validateVehicleRegistration("A12 3456"));
    }

    @Test
    void validateVehicleRegistrationShouldReturnFalseForWrongDigitCount() {
        assertFalse(validator.validateVehicleRegistration("AB12 345"));
    }

    @Test
    void validateVehicleRegistrationShouldReturnFalseForWrongFormat() {
        assertFalse(validator.validateVehicleRegistration("AB-12-3456"));
    }
}