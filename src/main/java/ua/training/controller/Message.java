package ua.training.controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class Message {
    private static Message instance;
    private ResourceBundle resource;
    private static Locale defaultLocale = new Locale("uk", "UA");
    private static final String BUNDLE_NAME = "I18n.pagecontent";
    public static final String EXCEPTION = "EXCEPTION";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";

    public static Message getInstance() {
        if (instance == null) {
            instance = new Message();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME, defaultLocale);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
