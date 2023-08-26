package com.qa.utils;

public class GlobalParams {

    private static ThreadLocal<String> platformName = new ThreadLocal<String>();
    private static ThreadLocal<String> udid = new ThreadLocal<String>();
    private static ThreadLocal<String> deviceName = new ThreadLocal<String>();
    private static ThreadLocal<String> systemPort = new ThreadLocal<String>();

    private static ThreadLocal<String> chromeDriverPort = new ThreadLocal<String>();
    private static ThreadLocal<String> wdaLocalPort = new ThreadLocal<String>();
    private static ThreadLocal<String> webkitDebugProxyPort = new ThreadLocal<String>();

    public  String getPlatformName() {
        return platformName.get();
    }

    public void setPlatformName(String platformName2) {
       platformName.set(platformName2);
    }

    public String getUdid() {
        return udid.get();
    }

    public  void setUdid(String udid2) {
        udid.set(udid2);
    }

    public  String getDeviceName() {
        return deviceName.get();
    }

    public  void setDeviceName(String deviceName2) {
        deviceName.set(deviceName2);
    }

    public  String getSystemPort() {
         return systemPort.get();
    }

    public  void setSystemPort(String systemPort2) {
        systemPort.set(systemPort2);
    }

    public  String getChromeDriverPort() {
        return chromeDriverPort.get();
    }

    public void setChromeDriverPort(String chromeDriverPort2) {
       chromeDriverPort.set(chromeDriverPort2);
    }

    public  String getWdaLocalPort() {
        return wdaLocalPort.get();
    }

    public  void setWdaLocalPort(String wdaLocalPort2) {
        wdaLocalPort.set(wdaLocalPort2);
    }

    public  String getWebkitDebugProxyPort() {
        return webkitDebugProxyPort.get();
    }

    public  void setWebkitDebugProxyPort(String webkitDebugProxyPort2) {
       webkitDebugProxyPort.set(webkitDebugProxyPort2);
    }

    public void initializeGlobalParams(){
        GlobalParams params = new GlobalParams();
        params.setPlatformName(System.getProperty("platformName"));
        params.setUdid(System.getProperty("udid"));
        params.setDeviceName(System.getProperty("deviceName"));

        switch(params.getPlatformName().toLowerCase()){
            case "android":
                    params.setSystemPort(System.getProperty("systemPort", "10002"));
                    params.setChromeDriverPort(System.getProperty("chromeDriverPort", "11002"));
                break;
            case "ios":
                params.setWdaLocalPort(System.getProperty("wdaLocalPort", "10001"));
                params.setWebkitDebugProxyPort(System.getProperty("webkitDebugProxyPort", "11001"));
                break;
            default:
                throw new IllegalStateException("Invalid Platform Name!");
        }
    }
}
