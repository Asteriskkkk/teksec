import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlacementReportTest {

    private PlacementReport placementReport;

    @BeforeEach
    void setUp() {
        System.out.println("Starting placement eligibility test...");
        placementReport = new PlacementReport();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Completed placement eligibility test.");
        placementReport = null;
    }

    @Test
    void testHighlyEligibleRange() throws InvalidStudentException {
        assertAll(
                () -> assertEquals("HIGHLY ELIGIBLE", placementReport.evaluatePlacementEligibility(8.0)),
                () -> assertEquals("HIGHLY ELIGIBLE", placementReport.evaluatePlacementEligibility(9.25)),
                () -> assertEquals("HIGHLY ELIGIBLE", placementReport.evaluatePlacementEligibility(10.0))
        );
    }

    @Test
    void testEligibleRange() throws InvalidStudentException {
        assertAll(
                () -> assertEquals("ELIGIBLE", placementReport.evaluatePlacementEligibility(6.5)),
                () -> assertEquals("ELIGIBLE", placementReport.evaluatePlacementEligibility(7.2)),
                () -> assertEquals("ELIGIBLE", placementReport.evaluatePlacementEligibility(7.99))
        );
    }

    @Test
    void testMarginalEligibilityRange() throws InvalidStudentException {
        assertAll(
                () -> assertEquals("MARGINAL ELIGIBILITY", placementReport.evaluatePlacementEligibility(5.0)),
                () -> assertEquals("MARGINAL ELIGIBILITY", placementReport.evaluatePlacementEligibility(5.8)),
                () -> assertEquals("MARGINAL ELIGIBILITY", placementReport.evaluatePlacementEligibility(6.49))
        );
    }

    @Test
    void testLowerBoundaryValues() throws InvalidStudentException {
        assertAll(
                () -> assertEquals("MARGINAL ELIGIBILITY", placementReport.evaluatePlacementEligibility(5.0)),
                () -> assertEquals("ELIGIBLE", placementReport.evaluatePlacementEligibility(6.5)),
                () -> assertEquals("HIGHLY ELIGIBLE", placementReport.evaluatePlacementEligibility(8.0))
        );
    }

    @Test
    void testUpperBoundaryValues() throws InvalidStudentException {
        assertAll(
                () -> assertEquals("MARGINAL ELIGIBILITY", placementReport.evaluatePlacementEligibility(6.49)),
                () -> assertEquals("ELIGIBLE", placementReport.evaluatePlacementEligibility(7.99)),
                () -> assertEquals("HIGHLY ELIGIBLE", placementReport.evaluatePlacementEligibility(10.0))
        );
    }

    @Test
    void testInvalidCgpaBelowMinimumThrowsException() {
        assertThrows(InvalidStudentException.class, () -> placementReport.evaluatePlacementEligibility(4.99));
        assertThrows(InvalidStudentException.class, () -> placementReport.evaluatePlacementEligibility(0.0));
        assertThrows(InvalidStudentException.class, () -> placementReport.evaluatePlacementEligibility(-1.0));
    }

    @Test
    void testInvalidCgpaAboveMaximumThrowsException() {
        assertThrows(InvalidStudentException.class, () -> placementReport.evaluatePlacementEligibility(10.01));
        assertThrows(InvalidStudentException.class, () -> placementReport.evaluatePlacementEligibility(11.0));
    }
}