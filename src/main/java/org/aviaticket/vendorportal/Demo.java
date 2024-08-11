package org.aviaticket.vendorportal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger log = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        System.out.println("print");

        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        
    }
    private static void add (int a,int b) {
        int result = a+b;
        //a+b = result
        System.out.println(a + "+" + b + "+" + result);
        log.info("a + b = result",a,b,result);

    }

}
