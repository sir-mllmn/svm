package org.cafelabs.svm;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class provides an emulation of debugger's work. The SVM goes into the debug mode after executing the
 * DEBUG opcode or after the start-up if -debug option was specified in the command line.
 * Debug allows us to make step-by-step execution of the program.
 * Methods that allows us to change the registers, dump the memory,
 * load or store memory, to restore the SVM execution cycle by issuing the "start" command
 * are implemented in this class.
 *
 * @author Zhugastrov A.
 * @author Iatsko E.
 * @author Bovkun E.
 * @author Stashko Y.
 * @author Meleshko S.
 */

public class Debugger {
    private static String line;
    private static String parameters[];
    public static boolean isDebugModeEnable = false;
    private static final HashMap<String, Integer> helpKeys = new HashMap<String, Integer>();

    static {
        helpKeys.put("start", 0);
        helpKeys.put("restart", 1);
        helpKeys.put("step", 2);
        helpKeys.put("dump", 3);
        helpKeys.put("loadRam", 4);
        helpKeys.put("storeRam", 5);
        helpKeys.put("read", 6);
        helpKeys.put("code", 7);
        helpKeys.put("uncode", 8);
        helpKeys.put("erase", 9);
        helpKeys.put("cp", 10);
        helpKeys.put("op", 11);
        helpKeys.put("dp", 12);
        helpKeys.put("rp", 13);
        helpKeys.put("sp", 14);
        helpKeys.put("help", 15);
        helpKeys.put("exit", 16);
    }

    static final int START = 0;
    static final int RESTART = 1;
    static final int STEP = 2;
    static final int DUMP = 3;
    static final int LOAD_RAM = 4;
    static final int STORE_RAM = 5;
    static final int READ = 6;
    static final int CODE = 7;
    static final int UNCODE = 8;
    static final int ERASE = 9;
    static final int CP = 10;
    static final int OP = 11;
    static final int DP = 12;
    static final int RP = 13;
    static final int SP = 14;
    static final int HELP = 15;
    static final int EXIT = 16;

    /**
     * Turn SVN to the debug mode
     */

    public static void startDebug() {
        isDebugModeEnable = true;
        System.out.println("\nWelcome to SVM");
        System.out.println("SVM is in the debug Mode");
        Scanner input = new Scanner(System.in);

        while (isDebugModeEnable) {
            System.out.print(">>");
            line = input.nextLine();
            StringTokenizer strTok = new StringTokenizer(line, " ");
            String userChoise = strTok.nextToken();
            if (helpKeys.get(userChoise) != null)
                switch (helpKeys.get(userChoise)) {
                    case Debugger.START:
                        start();
                        continue;
                    case Debugger.RESTART:
                        restart();
                        continue;
                    case Debugger.STEP:
                        step();
                        continue;
                    case Debugger.DUMP:
                        dump();
                        continue;
                    case Debugger.LOAD_RAM:
                        if (strTok.countTokens() != 1) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        parameters = new String[strTok.countTokens()];
                        parameters[0] = strTok.nextToken();
                        loadRam(parameters);
                        continue;
                    case Debugger.STORE_RAM:
                        if (strTok.countTokens() != 1) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        parameters = new String[strTok.countTokens()];
                        parameters[0] = strTok.nextToken();
                        storeRam(parameters);
                        continue;
                    case Debugger.READ:
                        if (!(strTok.hasMoreTokens())) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        parameters = new String[strTok.countTokens()];
                        int countTokens = strTok.countTokens();
                        for (int i = 0; i < countTokens; i++) {
                            parameters[i] = strTok.nextToken();
                        }
                        read(parameters);
                        continue;
                    case Debugger.CODE:
                        if (strTok.countTokens() != 1) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        parameters = new String[strTok.countTokens()];
                        parameters[0] = strTok.nextToken();
                        code(parameters);
                        continue;
                    case Debugger.UNCODE:
                        if (strTok.countTokens() != 2) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        parameters = new String[strTok.countTokens()];
                        parameters[0] = strTok.nextToken();
                        parameters[1] = strTok.nextToken();
                        uncode(parameters);
                        continue;
                    case Debugger.ERASE:
                        if (strTok.countTokens() != 2) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        parameters = new String[strTok.countTokens()];
                        parameters[0] = strTok.nextToken();
                        parameters[1] = strTok.nextToken();
                        erase(parameters);
                        continue;
                    case Debugger.CP:
                        if (strTok.countTokens() != 1) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        parameters = new String[strTok.countTokens()];
                        parameters[0] = strTok.nextToken();
                        changeCP(parameters);
                        continue;
                    case Debugger.OP:
                        if (strTok.countTokens() != 1) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        parameters = new String[strTok.countTokens()];
                        parameters[0] = strTok.nextToken();
                        changeOP(parameters);
                        continue;
                    case Debugger.DP:
                        if (strTok.countTokens() != 1) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        parameters = new String[strTok.countTokens()];
                        parameters[0] = strTok.nextToken();
                        changeDP(parameters);
                        continue;
                    case Debugger.RP:
                        if (strTok.countTokens() != 1) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        parameters = new String[strTok.countTokens()];
                        parameters[0] = strTok.nextToken();
                        changeRP(parameters);
                        continue;
                    case Debugger.SP:
                        if (strTok.countTokens() != 1) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        parameters = new String[strTok.countTokens()];
                        parameters[0] = strTok.nextToken();
                        changeSP(parameters);
                        continue;
                    case Debugger.HELP:
                        if (strTok.countTokens() != 0) {
                            System.out
                                    .println("Wrong command parameters. Type 'help' to show help");
                            continue;
                        }
                        help();
                        continue;
                    case Debugger.EXIT:
                        exit();

                }
            System.out.println("Unknow command '" + userChoise
                    + "'. Type 'help' to show help");
        }
    }

    /**
     * Display the message with help
     */

    private static void help() {
        System.out.println("Possible commands are:");
        System.out
                .println("start           	   	  - restart execution cycle from current CP position");
        System.out
                .println("restart        	          - restart execution cycle from the 0x00 position");
        System.out
                .println("step    			   	  - execute single instruction execution cycle");
        System.out
                .println("dump    			   	  - CPU and RAM dump (optional parameters - address and length)");
        System.out
                .println("load [filename] 	      - loads ROM image from file");
        System.out.println("store [filename]          - store RAM on the file");
        System.out
                .println("read .. 				  - read values/mnemonics from console to memory starting from DP");
        System.out
                .println("code [filename] 		  - read and translate values from file starting from DP");
        System.out
                .println("uncode [address], length] - dissasembler memory region");
        System.out
                .println("erase [shift]][length]    - fill area of memory with 0x00");
        System.out
                .println("cp [xx]			    	  - assigns new value to CP register");
        System.out
                .println("op [xx]				   	  - assigns new value to OP register");
        System.out
                .println("dp [xx]				   	  - assigns new value to DP register");
        System.out
                .println("rp [xx]				   	  - assigns new value to RP register");
        System.out
                .println("sp [xx]				   	  - assigns new value to SP register");
        System.out.println("help				   	  - display this message");
        System.out.println("exit				   	  - stop SVM and exit");
    }

    /**
     * Restart execution cycle from current CP position
     */
    private static void start() {
        isDebugModeEnable = false;
        CPU.execution();
    }

    /**
     * Restart execution cycle from the 0x00 position
     */

    private static void restart() {

        changeCP(0x00);
        isDebugModeEnable = false;
        CPU.execution();

    }

    /**
     * Execute single instruction execution cycle
     */
    private static void step() {
        CPU.execution();
    }

    /**
     * CPU and RAM dump.
     */

    private static void dump() {
        CPU.dump();
        Memory.dump();
    }

    /**
     * Store RAM on the file.
     *
     * @param String[] params
     * @throws IOException {@inheritDoc}
     */

    private static void storeRam(String[] params) {
        String fileName = params[0];
        DataOutputStream out;
        try {
            out = new DataOutputStream(new FileOutputStream(fileName));
            for (int i = 0; i < Memory.ram.length; i++) {
                out.writeInt(Memory.getRam(i));
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load RaM-image from file.
     *
     * @param String[] params
     * @throws IOException {@inheritDoc}
     */

    private static void loadRam(String[] params) {
        String fileName = params[0];
        try {
            RandomAccessFile in = new RandomAccessFile(fileName, "r");
            int magic = in.readInt();
            if (magic != 0xCAFEEEEE) return;//throw new Exception("Wrong ROM file");
            int length = in.readInt();
            if (length > Memory.ram.length) return; // throw new Exception("Not enough RAM to read the file");

            //int n=(int)(in.length() / (Integer.SIZE/8) );
            for (int i = 0; i < length; i++) {
                Memory.setRam(i, in.readInt());
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read values/mnemonics from console to memory starting from OP.
     *
     * @param String[] param
     */

    private static void read(String[] param) {
        for (int i = 0; i < param.length; i++) {
            Memory.setRam(CPU.op, Opcode.convert(param[i].toUpperCase()));
            CPU.op++;
        }
    }

    /**
     * Read and translate values from file starting from OP.
     *
     * @param String[] param
     * @throws IOBoundsException, FileNotFoundException  {@inheritDoc}
     */

    private static void code(String[] param) {

        File file = new File(param[0].trim());
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);
        String inputSt;

        try {
            while ((inputSt = reader.readLine()) != null) {

                Memory.setRam(CPU.op, Opcode.convert(inputSt.toUpperCase()));
                CPU.op++;
            }
            ((Closeable) file).close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * Disassemble memory region.
     *
     * @param String[] param
     * @throws NumberFormatException {@inheritDoc}
     */

    private static void uncode(String[] param) {
        int address;
        int length;
        try {
            address = Integer.parseInt(param[0]);
            length = Integer.parseInt(param[1]);
        } catch (NumberFormatException e) {
            System.out
                    .println("Wrong command parameters. Type 'help' to show help");
            return;
        }
        if ((address > Memory.ram.length)
                || (address + length > Memory.ram.length)) {
            System.out.println("Out of memory");
            return;
        }

        for (int i = 0; i < length; i++) {
            System.out.print(Opcode.convertBack(Memory.ram[address + i]) + " ");
        }
        System.out.println();
    }

    /**
     * Fill area of memory with 0x00
     *
     * @param String[] param
     */

    private static void erase(String[] param) {
        int shift;
        int length;
        shift = Integer.parseInt(param[0]);
        length = Integer.parseInt(param[1]);
        // Erases area of memory from shift to shift+length
        for (int i = shift; i < shift + length; i++)
            Memory.setRam(i, 0x00);
    }

    /**
     * Assigns new value to CP register.
     *
     * @param String[] param
     */

    private static void changeCP(String[] param) {
        CPU.cp = Integer.parseInt(param[0]);
    }

    /**
     * Assigns new value to CP register.
     *
     * @param int param
     */

    private static void changeCP(int param) {
        CPU.cp = param;
    }

    /**
     * Assigns new value to OP register.
     *
     * @param int param
     */


    private static void changeOP(String[] param) {
        CPU.op = Integer.parseInt(param[0]);
    }

    /**
     * Assigns new value to DP register.
     *
     * @param int param
     */

    private static void changeDP(String[] param) {
        CPU.dp = Integer.parseInt(param[0]);
    }

    /**
     * Assigns new value to RP register.
     *
     * @param int param
     */

    private static void changeRP(String[] param) {
        CPU.rp = Integer.parseInt(param[0]);
    }

    /**
     * Assigns new value to SP register.
     *
     * @param int param
     */

    private static void changeSP(String[] param) {
        CPU.sp = Integer.parseInt(param[0]);
    }

    private static void exit() {
        System.exit(0);
    }
}
