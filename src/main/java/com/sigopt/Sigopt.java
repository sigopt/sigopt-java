package com.sigopt;

public abstract class Sigopt {
	public static volatile String clientToken;
	public static volatile String userToken;
	public static volatile String apiBase = "https://api.sigopt.com/v0";
	public static volatile String apiVersion = "v0";

	public static final String AUTH_HEADER = null;
	public static final String CLIENT_AUTH_PARAM = "client_token";
	public static final String USER_AUTH_PARAM = "user_token";
	public static final String VERSION = "1.0.0";
	public static final String SUPPORT_EMAIL = "support@sigopt.com";
	public static final String DOCS_URL = "https://sigopt.com/docs/overview";

}
