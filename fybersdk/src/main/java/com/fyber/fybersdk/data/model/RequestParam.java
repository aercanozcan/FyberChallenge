package com.fyber.fybersdk.data.model;

/**
 * Created by Ercan on 2/10/2016.
 */
public class RequestParam implements Comparable<RequestParam>{

    private String name;
    private String value;

    public RequestParam() {
    }

    public RequestParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(RequestParam another) {
        return name.compareTo(another.getName());
    }

    @Override
    public String toString() {
        return name+"="+value;
    }
}
