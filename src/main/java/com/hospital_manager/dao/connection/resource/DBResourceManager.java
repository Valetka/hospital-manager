package com.hospital_manager.dao.connection.resource;

import java.util.ResourceBundle;

public final class DBResourceManager {

    private static final String DEFAULT_BUNDLE = "postgres";

    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle;

    public static DBResourceManager getInstance(){
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }

    public void setBundle(String bundleFile) {
        bundle = ResourceBundle.getBundle(bundleFile);
    }
}
