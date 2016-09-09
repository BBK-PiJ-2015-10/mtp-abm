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
TestMapCreatorFactoryCSV.class,TestMapCreatorFactorySQL.class,
TestPeriodMakerImplCSV.class,TestPeriodMakerImplSQL.class,
TestBpaCostsMakerImplCSV.class,TestBpaCostsMakerImplSQL.class,
TestBpaCostCalculatorImpl.class,TestBpaClientWeightsCalculatorImpl.class,
TestClientCostsImpl.class,
TestReportSummaryImpl.class,TestReportDetailedImpl.class,TestReportGeneratorImpl.class,
TestFileValidatorImplBpaMap.class
})
public class AllABCTests {
	
}
