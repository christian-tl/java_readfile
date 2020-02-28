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
        ReadFile.readFileUsingBufferReader();//======34864
//        ReadFile.readFileUsingScanner();//======110266
//        ReadFile.readUTF8FileData();//======34174
//        ReadFile.readFileReadAllBytes();//======OutOfMemoryError
//        ReadFile.readFileBufferReader();//======OutOfMemoryError
//        ReadFile.readLineByLine();//======34731
//        ReadFile.readFileByApacheIO();//======34540
//        ReadFile.readFileByApacheFileUtils();//======35194
    }
}
