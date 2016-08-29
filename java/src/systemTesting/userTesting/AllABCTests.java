package systemTesting.userTesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestABCSystemImpl.class,
TestUserSpace.class, TestUserSpaceMakerImpl.class,
TestConfigurationMakerImplCSV.class,TestConfigurationMakerImplSQL.class,
TestConfigurationManagerCSV.class,TestConfigurationMapperImplCSV.class,TestMapCreatorImplCSV.class,
TestConfigurationManagerSQL.class,TestConfigurationMapperImplSQL.class,TestMapCreatorImplSQL.class,
TestPeriodMakerImplCSV.class,
TestBpaCostsMakerImplCSV.class,TestBpaCostCalculatorImpl.class,TestBpaClientWeightsCalculatorImpl.class,
TestClientCostsImpl.class,
TestReportSummaryImpl.class,TestReportDetailedImpl.class,TestReportGeneratorImpl.class
})
public class AllABCTests {
	
}
