import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Bank App Test Suite")
@SelectClasses({BankAccountTest.class, TransactionServiceTest.class, NotificationServiceTest.class})
class BankAppTestSuite {
}