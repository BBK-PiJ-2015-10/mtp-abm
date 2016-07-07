package systemTesting.userTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestABCSystemImpl.class,
TestUserSpace.class, TestUserSpaceMakerImpl.class,
TestConfigurationMakerImpl.class,
TestConfigurationManager.class,TestConfigurationMapperImpl.class,TestMapCreatorImpl.class,
TestPeriodMakerImpl.class
})
public class AllABCTests {

}
