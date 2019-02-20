package com.twy.projectframework.net;

import com.google.gson.Gson;
import com.twy.network.business.Net;
import com.twy.projectframework.BuildConfig;

public class NetConfigMsg {
    public static String baseUrl;

    private static String test_url = "http://94.191.92.69:8080/Upload/";
    private static String on_line_url = "http://94.191.92.69:8080/Upload/";

    static {
        switch (BuildConfig.BUILD_TYPE){
            case "release":
                baseUrl = on_line_url;
                break;
            case "debug":
                baseUrl = test_url;
                break;
        }
    }

    private static ITestServices service;

    public static ITestServices getService(){
        if(service==null){
            Net net = new Net.Builder()
                    .setConverterFactory(new ResponseConvertFactory(new Gson()))
                    .baseUrl(baseUrl)
                    //.setHttpService(new OkHttpService())
                    .build();
            service = net.create(ITestServices.class);
        }
        return service;
    }
}
