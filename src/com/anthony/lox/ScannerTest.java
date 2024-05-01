package com.anthony.lox;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScannerTest {
    @Test
    public void testScanTokens() {
        Scanner scanner = new Scanner("var x = 5;");
        List<Token> tokens = scanner.scanTokens();

        assertEquals(6, tokens.size());
        assertEquals(TokenType.VAR, tokens.get(0).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(1).type);
        assertEquals(TokenType.EQUAL, tokens.get(2).type);
        assertEquals(TokenType.NUMBER, tokens.get(3).type);
        assertEquals(TokenType.SEMICOLON, tokens.get(4).type);
        assertEquals(TokenType.EOF, tokens.get(5).type);
    }

    @Test
    public void testScanTokensWithStrings() {
        Scanner scanner = new Scanner("print \"Hello, World!\";");
        List<Token> tokens = scanner.scanTokens();

        assertEquals(4, tokens.size());
        assertEquals(TokenType.PRINT, tokens.get(0).type);
        assertEquals(TokenType.STRING, tokens.get(1).type);
        assertEquals(TokenType.SEMICOLON, tokens.get(2).type);
        assertEquals("Hello, World!", tokens.get(1).literal);
    }

    @Test
    public void testScanTokensWithComments() {
        Scanner scanner = new Scanner("var x = 5; // This is a comment");
        List<Token> tokens = scanner.scanTokens();

        assertEquals(6, tokens.size());
        assertEquals(TokenType.VAR, tokens.get(0).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(1).type);
        assertEquals(TokenType.EQUAL, tokens.get(2).type);
        assertEquals(TokenType.NUMBER, tokens.get(3).type);
        assertEquals(TokenType.SEMICOLON, tokens.get(4).type);
    }

    @Test
    public void testScanTokensWithOperators() {
        Scanner scanner = new Scanner("a + b * c - 10 / 2;");
        List<Token> tokens = scanner.scanTokens();

        assertEquals(11, tokens.size());
        assertEquals(TokenType.IDENTIFIER, tokens.get(0).type);
        assertEquals(TokenType.PLUS, tokens.get(1).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(2).type);
        assertEquals(TokenType.STAR, tokens.get(3).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(4).type);
        assertEquals(TokenType.MINUS, tokens.get(5).type);
        assertEquals(TokenType.NUMBER, tokens.get(6).type);
        assertEquals(TokenType.SLASH, tokens.get(7).type);
        assertEquals(TokenType.NUMBER, tokens.get(8).type);
        assertEquals(TokenType.SEMICOLON, tokens.get(9).type);
    }

    @Test
    public void testScanTokensWithKeywords() {
        Scanner scanner = new Scanner("if (x < 5) { print x; } else { print \"Too large!\"; }");
        List<Token> tokens = scanner.scanTokens();

        assertEquals(18, tokens.size());
        assertEquals(TokenType.IF, tokens.get(0).type);
        assertEquals(TokenType.LEFT_PAREN, tokens.get(1).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(2).type);
        assertEquals(TokenType.LESS, tokens.get(3).type);
        assertEquals(TokenType.NUMBER, tokens.get(4).type);
        assertEquals(TokenType.RIGHT_PAREN, tokens.get(5).type);
        assertEquals(TokenType.LEFT_BRACE, tokens.get(6).type);
        assertEquals(TokenType.PRINT, tokens.get(7).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(8).type);
        assertEquals(TokenType.SEMICOLON, tokens.get(9).type);
        assertEquals(TokenType.RIGHT_BRACE, tokens.get(10).type);
        assertEquals(TokenType.ELSE, tokens.get(11).type);
        assertEquals(TokenType.LEFT_BRACE, tokens.get(12).type);
        assertEquals(TokenType.PRINT, tokens.get(13).type);
        assertEquals(TokenType.STRING, tokens.get(14).type);
        assertEquals(TokenType.SEMICOLON, tokens.get(15).type);
        assertEquals(TokenType.RIGHT_BRACE, tokens.get(16).type);
        assertEquals(TokenType.EOF, tokens.get(17).type);
    }

    @Test
    public void testScanTokensWithWhitespaceAndNewlines() {
        Scanner scanner = new Scanner("var\nx\n=\n10\n;\n");
        List<Token> tokens = scanner.scanTokens();

        assertEquals(6, tokens.size());
        assertEquals(TokenType.VAR, tokens.get(0).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(1).type);
        assertEquals(TokenType.EQUAL, tokens.get(2).type);
        assertEquals(TokenType.NUMBER, tokens.get(3).type);
        assertEquals(TokenType.SEMICOLON, tokens.get(4).type);
    }

}