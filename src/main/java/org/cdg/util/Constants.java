package org.cdg.util;

import java.util.Arrays;

public class Constants {

    public enum HttpMethods{
       GET("GET"),POST("POST"),PUT("PUT"),DELETE("DELETE");
        private final String name;
        private HttpMethods(String name) {
            this.name = name;

        }
        public String getName() {
            return this.name;
        }
        public static HttpMethods fromName(String methodName) {
            HttpMethods[] var1 = values();
            int length = var1.length;
            HttpMethods httpMethod =Arrays.stream(values()).filter(x->x.getName().equals(methodName)).findFirst().orElseThrow();

          return httpMethod;


        }
    }
}
