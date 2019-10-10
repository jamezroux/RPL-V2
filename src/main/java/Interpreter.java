/*

   Title: Rubiks Programming Language

   Authors {
   JamezRoux
   Plexeon
   }

   Current Methods {
   main: Control center, calls all the other methods to move data around
   loadCode: loads the code into an array instead of always going over the folder
   parseLine: Parses each line of the array created by loadCode to find the operators and execute their functions
   }

   Dates Edited {
   10/7/2019
   10/8/2019
   }

 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Interpreter {

// Generaal Functions
final static String OP_EXT = "EXT";
final static String OP_CID = "CID";
final static String OP_JUT = "JUT";
final static String OP_RET = "RET";

// Stack opcodes
final static String OP_PUS = "PUS";
final static String OP_POP = "POP";
final static String OP_POF = "POF";
final static String OP_POB = "POB";
final static String OP_POL = "POL";
final static String OP_POR = "POR";

// Print Opcodes
final static String OP_PRN = "PRN";
final static String OP_PRB = "PRB";
final static String OP_PRD = "PRD";

// Math opcodes
final static String OP_ADD = "ADD";
final static String OP_SUB = "SUB";
final static String OP_MUL = "MUL";
final static String OP_DIV = "DIV";
final static String OP_NEG = "NEG";

// Pointer opcodes
final static String OP_PNT = "PNT";

final static String OP_PNTF = "F";
final static String OP_PNTB = "B";
final static String OP_PNTL = "L";
final static String OP_PNTR = "R";

// Rubiks movement opcodes
final static String OP_MOV = "MOV";

final static String OP_MOVL = "L";
final static String OP_MOVR = "R";
final static String OP_MOVF = "F";
final static String OP_MOVB = "B";
final static String OP_MOVT = "T";
final static String OP_MOVD = "D";

final static String OP_MOVLN = "LN";
final static String OP_MOVRN = "RN";
final static String OP_MOVFN = "FN";
final static String OP_MOVBN = "BN";
final static String OP_MOVTN = "TN";
final static String OP_MOVDN = "DN";


// Regex patterns
final static String CODELINE = "[0-9][0-9][0-9]";
final static String BINARY_POSITIVE = "[0][0-1][0-1][0-1][0-1][0-1][0-1][0-1][0-1][0-1]";
final static String BINARY_NEGATIVE = "[1][0-1][0-1][0-1][0-1][0-1][0-1][0-1][0-1][0-1]";
final static String CELL_SELECTOR = "[0-1][0-1][0-1]";

public static int[][] face = new int[4][64];
public static int[] cell = new int[64];

public static File file = new File("../../test/rpl/test.rpl"); // A file chosen by the user
public static String[][] program = new String[999][256]; // The program to be translated
public static String[][] refinedProgram = new String[999][256]; // The refined program, with only executable code in an array

public static Cube cube = new Cube();

public static void main(String[] args) {
        /* This will be uncommented for release
           if(0 < args.length) {
                file = new File(args[0]);
           } else {
                System.err.println("The interpreter requires a filename.");
                System.exit(0);
           } */
        int lineNumber;
        loadCode();
        System.out.println("Loaded Code Successfully");
        System.out.println("Loaded " + program.length + " lines of code.");
        // This chunk here takes the program variable we just loaded up with our
        // code and iterates through each line, for each line it takes the opcodes
        // and does the correct actions
        for(lineNumber = 0; lineNumber < program.length; lineNumber++) {
                parseLine(program[lineNumber]);
                //System.out.println(Arrays.toString(program[lineNumber]));
        }
        System.exit(0);
}
// Todo: Make the file it reads by given via user input rather than hardcoded
// Todo: Delete all sections past '//' in the code
public static void loadCode() {
        Logger lCL = Logger.getLogger(Interpreter.class.getName());
        lCL.log(Level.INFO, "Started loading the code.");
        try {
                int lineNumber = 0;
                String[] line = new String[256];
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {
                        line = scan.nextLine().split(" ");
                        if(line[0].toString().matches(CODELINE)) {
                                for(int code = 0; code < line.length; code++) {
                                        program[lineNumber][code] = line[code];
                                        lCL.log(Level.FINEST, program[lineNumber][code]);
                                }
                                //lCL.log(Level.FINE, Arrays.toString(line));
                                lineNumber++;
                        }
                }
                scan.close();
                lCL.log(Level.INFO, "Cleaning up the program array.");
                int count = -1;
                for(String[] s : program) {
                        if(s != null) {
                                refinedProgram[++count] = s;
                        }
                }
                program = Arrays.copyOf(refinedProgram, lineNumber + 1);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
}

// Todo : The rest of the opcodes
public static void parseLine(String[] program) {
        Logger pLL = Logger.getLogger(Interpreter.class.getName());
        pLL.log(Level.FINEST, "Initialized the line parser.");
        String cellOne = "0001010010"; // 82
        String cellTwo = "0010011011"; // 155
        if(program[0] != null) {
                System.out.println("OP: " + program[1].toString());
                String programOpt = program[1].toString();
                if (programOpt.matches(OP_EXT)) {
                        pLL.log(Level.INFO, "Received exit OP.");
                        System.exit(0);
                }
                else if (programOpt.matches(OP_CID)) {}
                else if (programOpt.matches(OP_JUT)) {}
                else if (programOpt.matches(OP_RET)) {}
                else if (programOpt.matches(OP_PUS)) {}
                else if (programOpt.matches(OP_POP)) {}
                else if (programOpt.matches(OP_POF)) {}
                else if (programOpt.matches(OP_POB)) {}
                else if (programOpt.matches(OP_POL)) {}
                else if (programOpt.matches(OP_POR)) {}
                else if (programOpt.matches(OP_PRN)) {}
                else if (programOpt.matches(OP_PRB)) {}
                else if (programOpt.matches(OP_PRD)) {}
                else if (programOpt.matches(OP_ADD)) {
                        int cellValue = 0;
                        pLL.log(Level.INFO, "Matched OP_ADD");
                        if((program[2].toString().matches(CELL_SELECTOR) || program[2].toString().matches(BINARY_POSITIVE)) && (program[2].toString().matches(CELL_SELECTOR) || program[2].toString().matches(BINARY_POSITIVE))) {
                                pLL.log(Level.INFO, "Strings match up.");
                                // Typically we would grab the top value from these cells then send those values over
                                // But for right now just testing the function so throwing some binary over
                                if(program[2].toString().matches(CELL_SELECTOR)) {
                                        cellOne = "0b" + Cube.getCellByID(program[2].toString());
                                } else { cellOne = "0b" + program[2].toString(); }
                                if(program[3].toString().matches(CELL_SELECTOR)) {
                                        cellTwo = "0b" + Cube.getCellByID(program[2].toString());
                                } else { cellTwo = "0b" + program[2].toString(); }
                                int cellOneInt = Integer.parseInt(cellOne.substring(2), 2);
                                int cellTwoInt = Integer.parseInt(cellTwo.substring(2), 2);
                                pLL.log(Level.INFO, "cellOne: " + cellOneInt + "\tcellTwo" + cellTwoInt);
                                cellValue = cellOneInt + cellTwoInt;
                                pLL.log(Level.INFO, "Value is:\t" + cellValue);
                        } Cube.PUS(cellValue);
                }
                else if (programOpt.matches(OP_SUB)) {
                        int cellValue = 0;
                        pLL.log(Level.INFO, "Matched OP_SUB");
                        if((program[2].toString().matches(CELL_SELECTOR) || program[2].toString().matches(BINARY_POSITIVE)) && (program[2].toString().matches(CELL_SELECTOR) || program[2].toString().matches(BINARY_POSITIVE))) {
                                pLL.log(Level.INFO, "Strings match up");
                                if(program[2].toString().matches(CELL_SELECTOR)) {
                                        cellOne = "0b" + Cube.getCellByID(program[2].toString());
                                } else { cellOne = "0b" + program[2].toString(); }
                                if(program[3].toString().matches(CELL_SELECTOR)) {
                                        cellTwo = "0b" + Cube.getCellByID(program[3].toString());
                                } else { cellTwo = "0b" + program[3].toString(); }
                                int cellOneInt = Integer.parseInt(cellOne.substring(2), 2);
                                int cellTwoInt = Integer.parseInt(cellTwo.substring(2), 2);
                                pLL.log(Level.INFO, "cellOne: " + cellOneInt + "\tcellTwo: " + cellTwoInt);
                                cellValue = cellOneInt - cellTwoInt;
                                pLL.log(Level.INFO, "Value is:\t" + cellValue);
                        } Cube.PUS(cellValue);
                }
                else if (programOpt.matches(OP_MUL)) {
                        int cellValue = 0;
                        pLL.log(Level.INFO, "Matched OP_MUL");
                        if((program[2].toString().matches(CELL_SELECTOR) || program[2].toString().matches(BINARY_POSITIVE)) && (program[2].toString().matches(CELL_SELECTOR) || program[2].toString().matches(BINARY_POSITIVE))) {
                                pLL.log(Level.INFO, "Strings match up");
                                if(program[2].toString().matches(CELL_SELECTOR)) {
                                        cellOne = "0b" + Cube.getCellByID(program[2].toString());
                                } else { cellOne = "0b" + program[2].toString(); }
                                if(program[3].toString().matches(CELL_SELECTOR)) {
                                        cellTwo = "0b" + Cube.getCellByID(program[3].toString());
                                } else { cellTwo = "0b" + program[3].toString(); }
                                int cellOneInt = Integer.parseInt(cellOne.substring(2), 2);
                                int cellTwoInt = Integer.parseInt(cellTwo.substring(2), 2);
                                pLL.log(Level.INFO, "cellOne: " + cellOneInt + "\tcellTwo: " + cellTwoInt);
                                cellValue = cellOneInt * cellTwoInt;
                                pLL.log(Level.INFO, "Value is:\t" + cellValue);
                        } Cube.PUS(cellValue);
                }
                else if (programOpt.matches(OP_DIV)) {
                        int cellValue = 0;
                        pLL.log(Level.INFO, "Matched OP_SUB");
                        if((program[2].toString().matches(CELL_SELECTOR) || program[2].toString().matches(BINARY_POSITIVE)) && (program[2].toString().matches(CELL_SELECTOR) || program[2].toString().matches(BINARY_POSITIVE))) {
                                pLL.log(Level.INFO, "Strings match up");
                                if(program[2].toString().matches(CELL_SELECTOR)) {
                                        cellOne = "0b" + Cube.getCellByID(program[2].toString());
                                } else { cellOne = "0b" + program[2].toString(); }
                                if(program[3].toString().matches(CELL_SELECTOR)) {
                                        cellTwo = "0b" + Cube.getCellByID(program[3].toString());
                                } else { cellTwo = "0b" + program[3].toString(); }
                                int cellOneInt = Integer.parseInt(cellOne.substring(2), 2);
                                int cellTwoInt = Integer.parseInt(cellTwo.substring(2), 2);
                                pLL.log(Level.INFO, "cellOne: " + cellOneInt + "\tcellTwo: " + cellTwoInt);
                                cellValue = cellOneInt / cellTwoInt;
                                pLL.log(Level.INFO, "Value is:\t" + cellValue);
                        } Cube.PUS(cellValue);
                }
                else if (programOpt.matches(OP_NEG)) {}
                else if (programOpt.matches(OP_PNT)) {
                        if (program[2].toString().matches(OP_PNTF)) { }
                        else if (program[2].toString().matches(OP_PNTB)) { }
                        else if (program[2].toString().matches(OP_PNTL)) { }
                        else if (program[2].toString().matches(OP_PNTR)) { }
                        else { pLL.log(Level.WARNING, "The given pointer direction is not a command reference."); }
                } // FBLR
                else if (programOpt.matches(OP_MOV)) {
                        if (program[2].toString().matches(OP_MOVL)) { }
                        else if (program[2].toString().matches(OP_MOVR)) { }
                        else if (program[2].toString().matches(OP_MOVF)) { }
                        else if (program[2].toString().matches(OP_MOVB)) { }
                        else if (program[2].toString().matches(OP_MOVD)) { }
                        else if (program[2].toString().matches(OP_MOVT)) { }
                        else if (program[2].toString().matches(OP_MOVLN)) { }
                        else if (program[2].toString().matches(OP_MOVRN)) { }
                        else if (program[2].toString().matches(OP_MOVFN)) { }
                        else if (program[2].toString().matches(OP_MOVBN)) { }
                        else if (program[2].toString().matches(OP_MOVDN)) { }
                        else if (program[2].toString().matches(OP_MOVTN)) { }
                        else { pLL.log(Level.WARNING, "The given Rubik's movement is not a command reference."); }
                }
                else { pLL.log(Level.WARNING, "The given OPT is not a command reference."); }
        }

}
} // End of file
