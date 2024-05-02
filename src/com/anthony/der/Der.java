package com.anthony.der;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Der {
    //checks whether an error has occurred
    static boolean hadError = false;
    public static void main(String[] args) throws IOException {
        if (args.length > 1){
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1){
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }
    //reads the file and runs it
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if (hadError) System.exit(65);
    }

    //runs the prompt from terminal
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for(;;){
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError = false;
        }
    }

    //runs the source code
    private static void run(String source){
        Lexer scanner = new Lexer(source);
        List<Token> tokens = scanner.scanTokens();

        for (Token token : tokens){
            System.out.println(token);
        }
    }

    //error handling
    static void error(int line, String message){
        report(line, "", message);
    }

    //reports the error
    private static void report(int line, String where, String message){
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
}
