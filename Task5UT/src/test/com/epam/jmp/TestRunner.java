package test.com.epam.jmp;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.com.epam.jmp.dao.AccountTest;
import test.com.epam.jmp.dao.BankTest;
import test.com.epam.jmp.dao.CurrecyRatioTest;
import test.com.epam.jmp.dao.PersonTest;
import test.com.epam.jmp.dao.TransferTest;


@RunWith(Suite.class)
@SuiteClasses({ PersonTest.class, CurrecyRatioTest.class, BankTest.class, AccountTest.class, TransferTest.class })
public class TestRunner {

}
