package com.work;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * In this example, We will cover as the different option to read a file with Java. We will explore following options to read a file.
 *
 * 1.BufferReader.
 * 2.FileChannel
 * 3.DataInputStream
 * 4.Scanner
 * 5.StreamTokenizer
 *
 * we see the different option to read a file.
 * We have the option to use BufferedReader to read line by line or use a scanner to read a file with Java.
 * We also look at the new API with Java 7 which provides a more powerful and clean approach to read a file.
 * In the end of this example, we discussed how to use the new Stream API to read a file in Java 8.
 *
 * 可以读取大文件的方法有：
 * 1.Using Java BufferedReader
 * 2.Using Java 8 Stream API. Files.lines(path)
 * 3.Using Java Scanner
 * 4.Streaming File Using Apache Commons IO : IOUtils.lineIterator , FileUtils.lineIterator .
 *
 */
public class ReadFile {

    static String FILE_PATH = "src/main/resources/test.txt";


    /**
     * Read with BufferedReader
     *
     * The BufferReader is the most common and simple way to read a file with Java.
     *
     * 最常用的方法
     * 可以读取大文件
     */
    public static void readFileUsingBufferReader() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
        String line = null;

        /*The readLine() method return NULL when reached at the end of the file*/
        while ((line = bufferedReader.readLine()) != null) {
            // perform your logic with the data
            System.out.println(line);
        }
    }


    /**
     * We're going to use a java.util.Scanner to run through the contents of the file and retrieve lines serially, one by one:
     * This solution will iterate through all the lines in the file
     * allowing for processing of each line, without keeping references to them, and in conclusion, without keeping them in memory
     *
     *  可以读取大文件
     */
    public static void readFileUsingScanner() throws IOException {

        Scanner scanner = new Scanner(new File(FILE_PATH),StandardCharsets.UTF_8.name());
        //set the delimiter.We want to read entire line in one go.You can set as per your requirement
        scanner.useDelimiter("\\n");
        while(scanner.hasNext()){
            System.out.println(scanner.next());
        }

        /*Scanner读取文件另一种写法*/
        InputStream inputStream = new FileInputStream(FILE_PATH);
        try(Scanner fileScanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())){
            while (fileScanner.hasNextLine()){
                System.out.println(fileScanner.nextLine());
            }
        }
    }


    /**
     * Read File Using StreamTokenizer
     *
     * The Java StreamTokenizer class tokenize the data into tokens.
     * Let’s take a simple example of “Hello World”, each word in StreamTokenizer is a separate token.
     * The StreamTokenizer class provides several options to read different type of data.
     * Let’s look at some important fields
     *
     * sval – If the token is String. This field provides String value for the token.
     * nval – Number value for the token.
     * ttype – The type of token read (word, number, end of line).
     *
     */
    public static void readFileStreamTokenizer() throws IOException {

        // init the file and StreamTokenizer
        FileReader reader = new FileReader(FILE_PATH);
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        // we will iterate through the output until end of file
        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {

            // we will work on the data based on the data type
            if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
                System.out.println(tokenizer.sval);
            } else if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                System.out.println(tokenizer.nval);
            } else if (tokenizer.ttype == StreamTokenizer.TT_EOL) {
                System.out.println();
            }
        }
    }


    /**
     * Read UTF-8 File
     *
     * Not all files are simple enough especially when we are working on the multilingual applications.
     * Let’s see how to read a file which contains the special characters.
     *
     */
    public static void readUTF8FileData() throws IOException {

        // create a Buffer reader without encoding and pass encoding information
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH)));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }


    /**
     * Read File With Java 7
     *
     * Java 7 came with several changes to the way we read and write file (especially the NIO).
     * The first option is to use the readAllBytes() method.
     * It reads all the bytes from the file.
     * This will ensure to close the file when all bytes have been read, or it throws I/O error.
     *
     */
    public static void readFileReadAllBytes() throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
        System.out.println(data);
    }


    /**
     * Read File With Java 7
     *
     * Read files using the new Files class and BufferReader option
     *
     */
    public static void readFileBufferReader() {

        // define a data holder to store the data read from the file
        Path path = Paths.get(FILE_PATH);
        StringBuilder fileContent = new StringBuilder();
        try {

            BufferedReader br = Files.newBufferedReader(path);
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line);
                fileContent.append(System.lineSeparator());
            }
            System.out.println(fileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Read Java File With Java 8
     *
     * Java 8 provides some interesting features which includes the stream API.
     * Let’s see how to use the Java 8 stream API to read file efficiently
     *
     * This method read the file line by line using Java 8 stream API.
     *
     * 可以读取大文件
     */
    public static void readLineByLine() throws IOException {

        // create the path
        Path path = Paths.get(FILE_PATH);

        try (Stream< String > stream = Files.lines(path)) {
            // read one line at a time
            stream.forEach(System.out::println);
        }
    }


    /**
     * Read Java File With Java 8
     *
     * Read all content line by line but add them to a StringBuilder
     *
     * 可以读取大文件
     */
    public static void readLineByLineWithBuilder() throws IOException {

        StringBuilder sb = new StringBuilder();
        Path path = Paths.get(FILE_PATH);

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(s ->  sb.append(s).append("\n"));
        }
        System.out.println(sb.toString());
    }


    /**
     * Apache Commons IO Utils
     *
     * Apache Commons provides the convenient method to work with files.
     * Here is a clean and efficient way to as how to read a file in Java using Apache Commons IO Utils.
     *
     */
    public static void readFileByApacheIO() throws IOException {

        try(FileInputStream inputStream = new FileInputStream(FILE_PATH)){

            /*这个方法读取文件到内存，只适合小文件*/
            String s = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            System.out.println(s);

            /*这个方法读取文件到内存，只适合小文件*/
            List<String> linesOfIoUtils = IOUtils.readLines(new FileInputStream(new File(FILE_PATH)), StandardCharsets.UTF_8.name());
            for(String str : linesOfIoUtils){
                System.out.println(str);
            }

            /*这个方法流式读取，可以读大文件*/
            LineIterator lines = IOUtils.lineIterator(new FileInputStream(new File(FILE_PATH)), StandardCharsets.UTF_8.name());
            while (lines.hasNext()){
                System.out.println(lines.nextLine());
            }
        }
    }


    /**
     * Apache FileUtils
     *
     */
    public static void readFileByApacheFileUtils() throws IOException {

        List<String> fileContent= FileUtils.readLines(new File(FILE_PATH), StandardCharsets.UTF_8);
        for(String line : fileContent){
            System.out.println(line);
        }

        /*这个方法流式读取，可以读大文件*/
        LineIterator fileContents = FileUtils.lineIterator(new File(FILE_PATH), StandardCharsets.UTF_8.name());
        while(fileContents.hasNext()){
            System.out.println(fileContents.nextLine());
        }
    }


    /**
     * Read file with Guava
     *
     */
    public static void readFileByGuava() throws IOException {

        /*这个方法读小文件*/
        List<String> fileContent = com.google.common.io.Files.readLines(new File(FILE_PATH), StandardCharsets.UTF_8);
        for(String line : fileContent){
            System.out.println(line);
        }

    }


    /**
     * Read File Using FileChannel
     *
     * The FileChannel is a good option to read large files in Java.
     * To work with FileChannel, keep in mind the following sequence:
     *
     * 1.Open the file FileChannel.
     * 2.Set the buffer size.
     * 3.Read file data from the FileChannel.
     *
     * 这个方法实测非常不好，性能非常慢
     */
    public static void readFileUsingFileChannel() throws IOException {
        // open filechannel
        RandomAccessFile file = new RandomAccessFile(FILE_PATH, "r");;
        FileChannel channel = file.getChannel();

        //set the buffer size
        int bufferSize = 1024 * 100;
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);

        // read the data from filechannel
        while (channel.read(buffer) != -1) {
            System.out.println(new String(buffer.array()));
        }

        // close both channel and file
        channel.close();
        file.close();
    }


    /**
     * DataInputStream
     *
     * If the file contains binary or primitive data, DataInputStream might be a good choice for us.
     *
     * 实测中，发现读纯文本文件有问题
     */
    public static void readFileUsingDataInputStream() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(FILE_PATH));
        while (dataInputStream.available() > 0) {
            System.out.println(dataInputStream.readUTF());
        }
        dataInputStream.close();
    }

}
