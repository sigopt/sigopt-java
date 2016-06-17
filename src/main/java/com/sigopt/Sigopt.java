package com.sigopt;

public abstract class Sigopt {
    public static volatile String clientToken;
    public static volatile String apiBase = "https://api.sigopt.com/v1";
    public static volatile String apiVersion = "v1";

    public static final String CLIENT_AUTH_PARAM = "sample_client_token";
    public static final String VERSION = "2.1.0";
    public static final String SUPPORT_EMAIL = "support@sigopt.com";
    public static final String DOCS_URL = "https://sigopt.com/docs/overview";

}
