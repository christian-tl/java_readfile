package com.work;

import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    static ApplicationContext context;

    public static void main(String[] args) throws Exception {
        //keepAlive();
        System.out.println("Hello World!");
        context = AppConfig.AnnotationConfig();
        work();
    }

    private static void keepAlive() {
        (new Thread() {
            public void run() {
                while (true) {
                    try {
                        Class ex = App.class;
                        synchronized (App.class) {
                            App.class.wait();
                        }
                    } catch (InterruptedException var4) {
                    }
                }

            }
        }).start();
    }

    static void work() throws IOException {
        ReadFile.readFileUsingBufferReader();
    }
}
