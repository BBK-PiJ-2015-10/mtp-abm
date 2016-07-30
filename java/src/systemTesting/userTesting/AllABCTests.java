package systemTesting.userTesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestABCSystemImpl.class,
TestUserSpace.class, TestUserSpaceMakerImpl.class,
TestConfigurationMakerImpl.class,
TestConfigurationManager.class,TestConfigurationMapperImpl.class,TestMapCreatorImpl.class,
TestPeriodMakerImpl.class,
TestBpaCostsMakerImpl.class,TestBpaCostCalculatorImpl.class,TestBpaClientWeightsCalculatorImpl.class,
TestClientCostsImpl.class,
TestReportSummaryImpl.class,TestReportDetailedImpl.class,TestReportGeneratorImpl.class
})
public class AllABCTests {
	
}
