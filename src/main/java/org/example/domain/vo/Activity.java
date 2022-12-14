package org.example.domain.vo;

public class Activity {
    private String description;
    private final String srcHost;
    private final String dstHost;

    public Activity(String description, String srcHost, String dstHost) {
        this.description = description;
        this.srcHost = description.split(">")[0];
        this.dstHost = description.split(">")[1];
    }

    public Activity(String srcHost, String dstHost) {
        this.srcHost = srcHost;
        this.dstHost = dstHost;
    }
}
