package com.qa.utils;

import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class CapabilityManager {
    private static Logger logger = LogManager.getLogger(CapabilityManager.class);

    public DesiredCapabilities getCaps() throws IOException {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();

        try{
            logger.info("getting capabilities");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
            caps.setCapability(MobileCapabilityType.UDID, params.getUdid());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());

            switch(params.getPlatformName().toLowerCase()){
                case "android":
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
                    caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
                    caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
                    caps.setCapability("systemPort", params.getSystemPort());
                    caps.setCapability("chromeDriverPort", params.getChromeDriverPort());
                    //String androidAppUrl = getClass().getResource(props.getProperty("androidAppLocation")).getFile();
                    String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                            + File.separator + "resources" + File.separator + "apps" + File.separator + "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
                    logger.info("appUrl is" + androidAppUrl);
                    caps.setCapability("app", androidAppUrl);
                    break;
                case "ios":
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("iOSAutomationName"));
                    //String iOSAppUrl = getClass().getResource(props.getProperty("iOSAppLocation")).getFile();
                    String iOSAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                            + File.separator + "resources" + File.separator + "apps" + File.separator + "SwagLabsMobileApp.app";
                    logger.info("appUrl is" + iOSAppUrl);
                    caps.setCapability("bundleId", props.getProperty("iOSBundleId"));
                    caps.setCapability("wdaLocalPort", params.getWdaLocalPort());
                    caps.setCapability("webkitDebugProxyPort", params.getWebkitDebugProxyPort());
                    caps.setCapability("app", iOSAppUrl);
                    break;
            }
            return caps;
        } catch(Exception e){
            e.printStackTrace();
            logger.fatal("Failed to load capabilities. ABORT!!" + e.toString());
            throw e;
        }
    }

}
