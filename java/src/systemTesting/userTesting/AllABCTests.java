package systemTesting.userTesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
//@SuiteClasses({ TestABCSystemImpl.class, TestUserSpace.class, TestCreateUserSpaceMakerImpl.class,TestConfigurationManager.class })
@SuiteClasses({ TestABCSystemImpl.class, TestCreateUserSpaceMakerImpl.class, TestUserSpace.class})
public class AllABCTests {
	

}
