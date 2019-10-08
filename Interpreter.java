/*

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
   10/8/2019
   }

 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Interpreter {

// Math opcodes
final static String OP_ADD = "ADD";
final static String OP_SUB = "SUB";
final static String OP_MUL = "MUL";
final static String OP_DIV = "DIV";
final static String OP_NEG = "NEG";

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
final static String op_MOVBN = "BN";
final static String OP_MOVTN = "TN";
final static String OP_MOVDN = "DN";

// Pointer opcodes
final static String OP_PNT = "PNT";

final static String OP_PNTF = "F";
final static String OP_PNTB = "B";
final static String OP_PNTL = "L";
final static String OP_PNTR = "R";

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

// Generaal Functions
final static String OP_EXT = "EXT";
final static String OP_CID = "CID";
final static String OP_JUT = "JUT";
final static String OP_RET = "RET";

// Regex patterns
final static String CODELINE = "[0-9][0-9][0-9]";

public static String[][] program = new String[999][256];

public static void main(String[] args) {

        int lineNumber;

        loadCode();

        // This chunk here takes the program variable we just loaded up with our
        // code and iterates through each line, for each line it takes the opcodes
        // and does the correct actions
        for(lineNumber = 0; lineNumber < program.length; lineNumber++) {

                parseLine(program[lineNumber]);

        }

        System.exit(0);

}

// This method loads the code into the program variable, this makes it so that
// we done need to keep checking the file itself. Increases the speed of code
// execution
//
// Todo: Make the file it reads by given via user input rather than hardcoded
// Todo: Filter out the comments, no need for unneccesary data

public static void loadCode() {

        try {
                int lineNumber = 0;
                String[] line = new String[256];

                Scanner scanner = new Scanner(new File("./test.rpl"));

                while (scanner.hasNextLine()) {

                        line = scanner.nextLine().split(" ");
                        //System.out.println(line[0]);

                        if(line[0].toString().matches(CODELINE)) {
                                for(int code = 0; code < line.length; code++) {
                                        program[lineNumber][code] = line[code];
                                        System.out.print("\t" + program[lineNumber][code] + "\t");
                                } System.out.print("\n");
                        }

                        lineNumber++;
                }
                scanner.close();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
}

public static void parseLine(String[] program) {
        int segment;

        for(segment = 0; segment < program.length; segment++) {
                if(program[segment] != null) {
                        // This is where we build the catches for all the opcodes
                        // This will be a lot of if statements for now.
                        if(program[segment] == OP_ADD) {
                                break;
                        }
                }
        }
}

}
