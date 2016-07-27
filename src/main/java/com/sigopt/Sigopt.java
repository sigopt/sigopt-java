package com.sigopt;

import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.InetSocketAddress;

public abstract class Sigopt {
    public static final String VERSION = "3.4.0";
    public static final String apiVersion = "v1";

    public static volatile String clientToken;

    private static volatile String apiBase = "https://api.sigopt.com";
    private static volatile Proxy connectionProxy = null;
    private static volatile PasswordAuthentication proxyCredential = null;

    public static void setApiBase(String apiBase) {
        Sigopt.apiBase = apiBase;
    }

    public static String getApiBase() {
        return Sigopt.apiBase;
    }

    public static void setConnectionProxy(String host, int port) {
        Sigopt.setConnectionProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port)));
    }

    public static void setConnectionProxy(Proxy proxy) {
        Sigopt.connectionProxy = proxy;
    }

    public static Proxy getConnectionProxy() {
        return Sigopt.connectionProxy;
    }

    public static void setProxyCredential(PasswordAuthentication auth) {
        Sigopt.proxyCredential = auth;
    }

    public static PasswordAuthentication getProxyCredential() {
        return Sigopt.proxyCredential;
    }
}
