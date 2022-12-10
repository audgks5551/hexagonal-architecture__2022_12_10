package org.example;

import org.example.adapter.input.RouterViewCLIAdapter;

public class Main {
    public static void main(String[] args) {
        var cli = new RouterViewCLIAdapter();
        var type = "EDGE";
        System.out.println(cli.obtainRelatedRouters(type));
    }
}