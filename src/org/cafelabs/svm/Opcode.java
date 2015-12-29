package org.cafelabs.svm;

import java.util.HashMap;
import java.util.Map;

final class Opcode {

    static Map<String, Integer> mnemonics = new HashMap<>();
    static Map<Integer, String> opcodes = new HashMap<>();

    static {

        mnemonics.put("NOP", 0x00);
        mnemonics.put("PUSH", 0xA0);
        mnemonics.put("LOAD", 0xA1);
        mnemonics.put("STORE", 0xA2);
        mnemonics.put("READ", 0xA3);
        mnemonics.put("WRITE", 0xA4);
        mnemonics.put("READI", 0xA5);
        mnemonics.put("WRITEI", 0xA6);
        mnemonics.put("POP", 0xA7);
        mnemonics.put("DUP", 0xA8);
        mnemonics.put("SWAP", 0xA9);

        mnemonics.put("CP", 0xB1);
        mnemonics.put("OP", 0xB2);
        mnemonics.put("DP", 0xB3);
        mnemonics.put("RP", 0xB4);
        mnemonics.put("SP", 0xB5);
        mnemonics.put("LOP", 0xB6);
        mnemonics.put("LDP", 0xB7);
        mnemonics.put("LRP", 0xB8);
        mnemonics.put("LSP", 0xB9);

        mnemonics.put("ADD", 0xC0);
        mnemonics.put("SUB", 0xC1);
        mnemonics.put("MUL", 0xC2);
        mnemonics.put("DIV", 0xC3);
        mnemonics.put("MOD", 0xC4);
        mnemonics.put("NEG", 0xC5);
        mnemonics.put("ABS", 0xC6);
        mnemonics.put("RND", 0xC7);

        mnemonics.put("LSH", 0xD0);
        mnemonics.put("RSH", 0xD1);
        mnemonics.put("AND", 0xD2);
        mnemonics.put("OR", 0xD3);
        mnemonics.put("XOR", 0xD4);
        mnemonics.put("NOT", 0xD5);
        mnemonics.put("EQ", 0xD6);
        mnemonics.put("NEQ", 0xD7);
        mnemonics.put("GR", 0xD8);
        mnemonics.put("GRE", 0xD9);
        mnemonics.put("LS", 0xDA);
        mnemonics.put("LSE", 0xDB);

        mnemonics.put("JMP", 0xE1);
        mnemonics.put("JMZ", 0xE2);
        mnemonics.put("JMN", 0xE3);
        mnemonics.put("CALL", 0xE4);
        mnemonics.put("RET", 0xE5);

        mnemonics.put("DUMP", 0xDDDDDDDD);
        mnemonics.put("DEBUG", 0xEEEEEEEE);
        mnemonics.put("HALT", 0xFFFFFFFF);

        mnemonics.put("SETPIXEL", 0xAA);

        opcodes.put(0xA0, "PUSH");
        opcodes.put(0xA7, "POP");
        opcodes.put(0xDDDDDDDD, "DUMP");
        opcodes.put(0x00, "NOP");
        opcodes.put(0xA3, "READ");
        opcodes.put(0xA4, "WRITE");
        opcodes.put(0xA5, "READI");
        opcodes.put(0xA6, "WRITEI");
        opcodes.put(0xA9, "SWAP");
        opcodes.put(0xC0, "ADD");
        opcodes.put(0xC1, "SUB");
        opcodes.put(0xC2, "MUL");
        opcodes.put(0xC3, "DIV");
        opcodes.put(0xC4, "MOD");
        opcodes.put(0xC5, "NEG");
        opcodes.put(0xC6, "ABS");
        opcodes.put(0xC7, "RND");
        opcodes.put(0xE1, "JMP");
        opcodes.put(0xE2, "JMZ");
        opcodes.put(0xE3, "JMN");
        opcodes.put(0xE4, "CALL");
        opcodes.put(0xE5, "RET");
        opcodes.put(0xD2, "AND");
        opcodes.put(0xD3, "OR");
        opcodes.put(0xD4, "XOR");
        opcodes.put(0xD6, "EQ");
        opcodes.put(0xD7, "NEQ");
        opcodes.put(0xB1, "CP");
        opcodes.put(0xB2, "OP");
        opcodes.put(0xB3, "DP");
        opcodes.put(0xB4, "RP");
        opcodes.put(0xB5, "SP");
        opcodes.put(0xB6, "LOP");
        opcodes.put(0xB7, "LDP");
        opcodes.put(0xB8, "LRP");
        opcodes.put(0xB9, "LSP");
        opcodes.put(0xEEEEEEEE, "DEBUG");
        opcodes.put(0xA1, "LOAD");
        opcodes.put(0xA2, "STORE");
        opcodes.put(0xD0, "LSH");
        opcodes.put(0xD1, "RSH");
        opcodes.put(0xD8, "GR");
        opcodes.put(0xD9, "GRE");
        opcodes.put(0xDA, "LS");
        opcodes.put(0xDB, "LSE");
        opcodes.put(0xAA, "SETPIXEL");
        opcodes.put(0xFFFFFFFF, "HALT");
        opcodes.put(0xA8, "DUP");
    }

    static final int PUSH = 0xA0;
    static final int POP = 0xA7;
    static final int HALT = 0xFFFFFFFF;
    static final int NOP = 0x00;
    static final int ADD = 0xC0;
    static final int DUMP = 0xDDDDDDDD;
    static final int READ = 0xA3;
    static final int WRITE = 0xA4;
    static final int READI = 0xA5;
    static final int WRITEI = 0xA6;
    static final int SWAP = 0xA9;
    static final int SUB = 0xC1;
    static final int MUL = 0xC2;
    static final int DIV = 0xC3;
    static final int MOD = 0xC4;
    static final int NEG = 0xC5;
    static final int ABS = 0xC6;
    static final int RND = 0xC7;
    static final int JMP = 0xE1;
    static final int JMZ = 0xE2;
    static final int JMN = 0xE3;
    static final int CALL = 0xE4;
    static final int RET = 0xE5;
    static final int AND = 0xD2;
    static final int OR = 0xD3;
    static final int XOR = 0xD4;
    static final int NOT = 0xD5;
    static final int EQ = 0xD6;
    static final int NEQ = 0xD7;
    static final int CP = 0xB1;
    static final int OP = 0xB2;
    static final int DP = 0xB3;
    static final int RP = 0xB4;
    static final int SP = 0xB5;

    static final int LOP = 0xB6;
    static final int LDP = 0xB7;
    static final int LRP = 0xB8;
    static final int LSP = 0xB9;

    static final int DEBUG = 0xEEEEEEEE;
    static final int LOAD = 0xA1;
    static final int STORE = 0xA2;
    static final int LSH = 0xD0;
    static final int RSH = 0xD1;
    static final int GR = 0xD8;
    static final int GRE = 0xD9;
    static final int LS = 0xDA;
    static final int LSE = 0xDB;
    static final int SETPIXEL = 0xAA;
    static final int DUP = 0xA8;

    /**
     * Convert data from string to bytecodes.
     * <p/>
     * Return bytecode of reserved CPU`s comand, integer value of parsed string
     * or -1 for uncorrect values
     */

    public static int convert(String code) {
        if (code.equals("")) {
            return 0;
        } else if (mnemonics.get(code) != null) {
            return mnemonics.get(code);
        } else {
            try {
                return Integer.parseInt(code);
            } catch (Exception e) {
                try {
                    if ((code.charAt(0) == '0') && (code.charAt(1) == 'X'))
                        code = code.substring(4).toLowerCase();
                    int i = Integer.parseInt(code, 16);
                    return i;
                } catch (Exception ee) {
                    return -1;
                }
            }
        }
    }

    /**
     * Return opcode of reserved bytecodes
     */

    public static String convertBack(int opcode) {
        if (opcodes.get(opcode) != null) {
            return opcodes.get(opcode);
        }
        return String.valueOf(opcode);
    }
}
