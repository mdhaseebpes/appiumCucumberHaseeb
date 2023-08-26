package com.qa.steps;


import com.qa.utils.DriverManager;
import com.qa.utils.VideoManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;

import java.io.IOException;

public class Hooks {
    @Before
    public void beforeScenario() throws Exception {
        new DriverManager().initializeDriver();
        new VideoManager().startRecording();

    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

            new VideoManager().stopRecording(scenario.getName());
        }

    }
}
