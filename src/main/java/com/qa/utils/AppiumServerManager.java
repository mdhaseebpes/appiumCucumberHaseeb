package com.qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashMap;

public class AppiumServerManager {

    private static Logger logger = LogManager.getLogger(AppiumServerManager.class);

    private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();

    public AppiumDriverLocalService getServer(){
        return server.get();
    }

    public void startServer(){
        AppiumDriverLocalService server = getAppiumServiceDefault();
        server.start();
        if(!server.isRunning()){
            logger.info("Appium server not started ABORT !!");
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started ABORT !!");
        }
        server.clearOutPutStreams();
        this.server.set(server);
        logger.info("Appium server started");
    }

    //for Mac
    public AppiumDriverLocalService getAppiumServiceDefault(){
        GlobalParams params = new GlobalParams();
        HashMap<String,String> environment = new HashMap<>();
        environment.put("PATH" ,"/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin:/usr/local/opt/node@20/bin:/Users/mohammedhaseebalikhan/Desktop/Maven/apache-maven-3.8.2/bin:/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin:/Users/mohammedhaseebalikhan/Library/Android/sdk/tools:/Users/mohammedhaseebalikhan/Library/Android/sdk/platform-tools:" +
                "/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin" + System.getenv("PATH"));
        environment.put("ANDROID_HOME","/Users/mohammedhaseebalikhan/Library/Android/sdk");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment)
                .withLogFile(new File(params.getPlatformName() + "_"+
                         params.getDeviceName() + "_"+ File.separator + "server.log")));
    }
}
