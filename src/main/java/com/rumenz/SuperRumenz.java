package com.rumenz;

public class SuperRumenz {

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "SuperRumenz{" +
                "key='" + key + '\'' +
                '}';
    }
}
