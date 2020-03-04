package com.work;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile2 {

    static String filePath = "src/main/resources/test.txt";

    public static void read1() {

        String string = null;
        try {
            // 在给定从中读取数据的文件名的情况下创建一个新 FileReader
            FileReader fr = new FileReader(filePath);
            // 创建一个使用默认大小输入缓冲区的缓冲字符输入流
            BufferedReader br = new BufferedReader(fr);
            while (null != (string = br.readLine())) {
                System.out.println(string);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read2() {
        try {
            FileInputStream fin = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(fin);
            BufferedReader buffReader = new BufferedReader(reader);
            String strTmp = "";
            while ((strTmp = buffReader.readLine()) != null) {
                System.out.println(strTmp);
            }
            buffReader.close();
            reader.close();
            fin.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void read3() {
        try {
            FileInputStream fin = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fin);
            InputStreamReader reader = new InputStreamReader(bis,"utf-8");
            BufferedReader in = new BufferedReader(reader, 10 * 1024 * 1024);//10M缓存
            // FileWriter fw = new FileWriter(outputFile);
            while (in.ready()) {
                String line = in.readLine();
                System.out.println(line);
                //fw.append(line + " ");
            }
            in.close();
            //fw.flush();
            //fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Scanner + try-with-resources
     */
    public static void read4() {
        try (Scanner scanner = new Scanner(new File(filePath))) {

            while (scanner.hasNext()){
                System.out.println(scanner.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * BufferedReader + try-with-resources
     */
    public static void read5() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * BufferedReader + Stream
     * A new method lines() has been added since 1.8, it lets BufferedReader returns content as Stream.
     */
    public static void read6() {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {

            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);
    }

    /**
     * Java 8 Read File + Stream
     */
    public static void read7() {
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            stream.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Java 8 Read File + Stream + Extra
     */
    public static void read8() {
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            //1. filter line 3
            //2. convert all content to upper case
            //3. convert it into a List
            list = stream
                    .filter(line -> !line.startsWith("line3"))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);
    }

    public static void read9() {

    }


}
