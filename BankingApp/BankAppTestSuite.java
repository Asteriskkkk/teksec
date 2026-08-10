package com.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        BankAccountTest.class,
        TransactionServiceTest.class,
        NotificationServiceTest.class
})
public class BankAppTestSuite {
}
