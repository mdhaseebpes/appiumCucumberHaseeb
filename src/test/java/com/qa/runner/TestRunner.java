package com.qa.runner;

import com.qa.utils.AppiumServerManager;
import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import io.cucumber.testng.*;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.*;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;


@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.qa.steps"},
        plugin = {"pretty",
                "summary",
               // "com.qa.listener.CucumberExtentReportListener:",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        snippets = CAMELCASE,
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests{

    private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();

    public static TestNGCucumberRunner getRunner(){
        return testNGCucumberRunner.get();
    }

    private static void setRunner(TestNGCucumberRunner testNGCucumberRunner1){
        testNGCucumberRunner.set(testNGCucumberRunner1);
    }

    @BeforeClass
    public static void initialize() throws Exception {
        GlobalParams params = new GlobalParams();
        params.initializeGlobalParams();

        ThreadContext.put("ROUTINGKEY", params.getPlatformName() + "_"
                + params.getDeviceName());

        new AppiumServerManager().startServer();
    }

    @AfterClass
    public static void quit(){
        DriverManager driverManager = new DriverManager();
        if(driverManager.getDriver() != null){
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }
        AppiumServerManager serverManager = new AppiumServerManager();
        if(serverManager.getServer() != null){
            serverManager.getServer().stop();
        }
    }

}
