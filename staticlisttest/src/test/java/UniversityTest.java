import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UniversityTest {

    @Test
    void coursesListShouldReturnNonNullList() {
        assertNotNull(University.coursesList());
    }

    @Test
    void coursesListShouldContainExpectedNumberOfCourses() {
        List<String> expectedCourses = List.of(
                "Computer Science",
                "Mathematics",
                "Physics",
                "Chemistry"
        );

        assertIterableEquals(expectedCourses, University.coursesList());
    }

    @Test
    void coursesListShouldContainExpectedCourseNamesInOrder() {
        List<String> expectedCourses = List.of(
                "Computer Science",
                "Mathematics",
                "Physics",
                "Chemistry"
        );

        assertIterableEquals(expectedCourses, University.coursesList());
    }
}