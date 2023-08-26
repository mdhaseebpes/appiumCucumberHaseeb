package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class DriverManager {

    private static Logger logger = LogManager.getLogger(DriverManager.class);
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public AppiumDriver getDriver(){
        return driver.get();
    }


    public void setDriver(AppiumDriver driver1){
        driver.set(driver1);
    }

    public void initializeDriver() throws Exception {
        AppiumDriver driver = null;
        GlobalParams params = new GlobalParams();
        PropertyManager props = new PropertyManager();

        if(driver == null){
            try{
               logger.info("initializing Appium driver");
                switch(params.getPlatformName().toLowerCase()){
                    case "android":
                        driver = new AndroidDriver(new AppiumServerManager().getServer().getUrl(), new CapabilityManager().getCaps());
                        break;
                    case "ios":
                        driver = new IOSDriver(new AppiumServerManager().getServer().getUrl(), new CapabilityManager().getCaps());
                        break;
                }
                if(driver == null){
                    throw new Exception("driver is null. ABORT!!!");
                }
                logger.info("Driver is initialized");
                this.driver.set(driver);
            } catch (IOException e) {
                e.printStackTrace();
                logger.fatal("Driver initialization failure. ABORT !!!!" + e.toString());
                throw e;
            }
        }

    }

}
