package org.cafelabs.svm;

import java.util.Scanner;
import java.util.StringTokenizer;

public class CPU {

    static int cp = 0;
    static int op = 0;
    static int dp = Memory.ram.length / 4;
    static int rp = Memory.ram.length;
    static int sp = Memory.ram.length * 3 / 4;

    /**
     * Read commands from console and save its in ROM
     */

    public static void readConsole() {
        System.out.println("Enter command:");
        String input = "";
        Scanner scan = new Scanner(System.in);
        input = scan.nextLine().toUpperCase();
        StringTokenizer token = new StringTokenizer(input, " ", false);
        int index = cp;
        while (token.hasMoreTokens()) {
            String str = token.nextToken();
            Memory.setRam(index, Opcode.convert(str));
            index++;
        }
    }

    /**
     * Goes on RAM, increasing cp and execute commands
     */
    public static void execution() {
        while (true) {
            switch (Memory.getRam(cp)) {
                case Opcode.NOP: {
                    break;
                }
                case Opcode.LOAD: {
                    int link = pop();
                    int val = Memory.getRam(link);
                    push(val);
                    break;
                }
                case Opcode.DUP: {
                    int value = pop();
                    push(value);
                    push(value);
                    break;
                }
                case Opcode.SWAP: {
                    int one = pop();
                    int two = pop();
                    push(one);
                    push(two);
                    break;
                }
                case Opcode.READI: {
                    System.out.println("put number:");
                    Scanner scan = new Scanner(System.in);
                    String input = scan.nextLine();
                    StringTokenizer token = new StringTokenizer(input, " ", false);
                    input = token.nextToken();
                    int val = Integer.parseInt(input);
                    push(val);
                    break;
                }
                case Opcode.DIV: {
                    int one = pop();
                    int two = pop();
                    push(two / one);
                    break;
                }
                case Opcode.MOD: {
                    int one = pop();
                    int two = pop();
                    push(two % one);
                    break;
                }
                case Opcode.STORE: {
                    int link = pop();
                    int val = pop();
                    Memory.setRam(link, val);
                    break;
                }
                case Opcode.PUSH: {
                    int value = Memory.getRam(++cp);
                    if (sp > Memory.ram.length) {
                        VideoMemory.videoMem[sp - Memory.ram.length] = value;
                        VideoMemory.videoMemory.repaint();
                    } else {
                        push(value);
                    }
                    break;
                }
                case Opcode.HALT: {
                    CPU.dump();
                    Memory.dump();
                    System.exit(0);
                    break;
                }
                case Opcode.DUMP: {
                    CPU.dump();
                    Memory.dump();
                    break;
                }
                case Opcode.POP: {
                    int i = pop();
                    break;
                }
                case Opcode.ADD: {
                    int k = add();
                    Memory.setRam(--sp, k);
                    break;
                }
                case Opcode.SUB: {
                    int k = sub();
                    Memory.setRam(--sp, k);
                    break;
                }
                case Opcode.MUL: {
                    int k = mul();
                    Memory.setRam(--sp, k);
                    break;
                }
                case Opcode.READ: {
                    System.out.println("put string:");
                    Scanner scan = new Scanner(System.in);
                    String str = scan.nextLine();
                    int strSize = str.length();
                    Memory.setRam(dp, strSize);
                    for (int i = 0; i < strSize; i++) {
                        Memory.setRam(++dp, (int) str.charAt(i));
                    }
                    break;
                }
                case Opcode.CP: {
                    Memory.setRam(--sp, cp);
                    break;
                }
                case Opcode.OP: {
                    Memory.setRam(--sp, op);
                    break;
                }
                case Opcode.DP: {
                    Memory.setRam(--sp, dp);
                    break;
                }
                case Opcode.RP: {
                    Memory.setRam(--sp, rp);
                    break;
                }
                case Opcode.SP: {
                    int val = sp;
                    Memory.setRam(--sp, val);
                    break;
                }
                case Opcode.LOP: {
                    int val = pop();
                    op = val;
                    break;
                }
                case Opcode.LDP: {
                    int val = pop();
                    dp = val;
                    break;
                }
                case Opcode.LRP: {
                    int val = pop();
                    rp = val;
                    break;
                }
                case Opcode.LSP: {
                    int val = pop();
                    sp = val;
                    break;
                }
                case Opcode.WRITE: {
                    int link = Memory.getRam(sp++);
                    int size = Memory.getRam(link);
                    String output = "";
                    for (int i = 1; i <= size; i++) {
                        output += (char) Memory.getRam(link + i);
                    }
                    System.out.println(output);
                    break;
                }
                case Opcode.WRITEI: {
                    System.out.println(pop());
                    break;
                }
                case Opcode.DEBUG: {
                    if (Debugger.isDebugModeEnable == false) {
                        Debugger.startDebug();
                    } else {
                        execution();
                    }
                    cp++;
                    break;
                }
                case Opcode.NEG: {
                    int val = pop();
                    push(-val);
                    break;
                }
                case Opcode.ABS: {
                    int val = pop();
                    push(Math.abs(val));
                    break;
                }
                case Opcode.RND: {
                    int val = pop();
                    push((int) (Math.random() * val));
                    break;
                }
                case Opcode.LSH: {
                    int times = pop();
                    int val = pop();
                    val = val << times;
                    push(val);
                    break;
                }
                case Opcode.RSH: {
                    int times = pop();
                    int val = pop();
                    val = val >> times;
                    push(val);
                    break;
                }
                case Opcode.EQ: {
                    int one = pop();
                    int two = pop();
                    int val = 0;
                    if (one == two)
                        val = 0xFFFFFFFF;
                    else
                        val = 0x00;
                    push(val);
                    break;
                }
                case Opcode.NEQ: {
                    int one = pop();
                    int two = pop();
                    int val = 0;
                    if (one == two)
                        val = 0x00;
                    else
                        val = 0xFFFFFFFF;
                    push(val);
                    break;
                }
                case Opcode.GR: {
                    int one = pop();
                    int two = pop();
                    int val = 0;
                    if (one < two)
                        val = 0x00;
                    else
                        val = 0xFFFFFFFF;
                    push(val);
                    break;
                }
                case Opcode.GRE: {
                    int one = pop();
                    int two = pop();
                    int val = 0;
                    if (one > two)
                        val = 0x00;
                    else
                        val = 0xFFFFFFFF;
                    push(val);
                    break;
                }
                case Opcode.LS: {
                    int one = pop();
                    int two = pop();
                    int val = 0;
                    if (one > two)
                        val = 0x00;
                    else
                        val = 0xFFFFFFFF;
                    push(val);
                    break;
                }
                case Opcode.LSE: {
                    int one = pop();
                    int two = pop();
                    int val = 0;
                    if (one < two)
                        val = 0x00;
                    else
                        val = 0xFFFFFFFF;
                    push(val);
                    break;
                }
                case Opcode.AND: {

                    int k = and();
                    Memory.setRam(--sp, k);
                    break;
                }

                case Opcode.OR: {

                    int k = or();
                    Memory.setRam(--sp, k);
                    break;
                }

                case Opcode.XOR: {

                    int k = xor();
                    Memory.setRam(--sp, k);
                    break;
                }

                case Opcode.NOT: {

                    int k = ~pop();
                    Memory.setRam(--sp, k);
                    break;
                }

                case Opcode.JMP: {
                    jmp();
                    break;
                }
                case Opcode.JMZ: {
                    jmz();
                    break;
                }
                case Opcode.JMN: {
                    jmn();
                    break;
                }
                case Opcode.CALL: {
                    call();
                    break;
                }
                case Opcode.RET: {
                    ret();
                    break;
                }
                case Opcode.SETPIXEL: {
                    VideoMemory.setPixel(Memory.getRam(++cp), Memory.getRam(++cp),
                            Memory.getRam(++cp));

                    break;
                }

            }
            cp++;
            if (Debugger.isDebugModeEnable == true)
                break;
        }
    }

    public static void push(int value) {
        Memory.setRam(--sp, value);
    }

    public static int pop() {
        int result = Memory.getRam(sp);
        sp++;
        return result;
    }

    public static int add() {
        int a = pop();
        int b = pop();
        return a + b;
    }

    public static int sub() {
        int a = pop();
        int b = pop();
        return b - a;
    }

    public static int mul() {
        int a = pop();
        int b = pop();
        return a * b;
    }

    public static int and() {
        int a = pop();
        int b = pop();
        return a & b;
    }

    public static int or() {
        int a = pop();
        int b = pop();
        return a | b;
    }

    public static int xor() {
        int a = pop();
        int b = pop();
        return a ^ b;
    }

    public static void jmp() {
        int k = pop();
        cp += k;
    }

    public static void jmz() {
        int k = pop();
        int s = pop();
        if (s == 0)
            cp += k;
    }

    public static void jmn() {
        int k = pop();
        int s = pop();
        if (s < 0)
            cp += k;
        //System.out.println("cp: " + cp);
    }

    public static void call() {
        int k = pop();
        Memory.setRam(--rp, cp + 1);
        cp = k - 1;
    }

    public static void ret() {
        cp = Memory.getRam(rp);
        cp--;
        rp++;
    }

    /**
     * Dump CPU`s registers
     */
    public static void dump() {
        StringBuffer format = new StringBuffer("\nCP: ");
        format.append(cp);
        format.append("\nOP: ");
        format.append(op);
        format.append("\nSP: ");
        format.append(sp);
        format.append("\nDP: ");
        format.append(dp);
        format.append("\nRP: ");
        format.append(rp);
        format.append("\n");
        System.out.println(format);
    }

    public static void main(String[] args) {
        while (true) {
            CPU.readConsole();
            CPU.execution();
            // CPU.dump();
            // Memory.dump();
        }
    }
}