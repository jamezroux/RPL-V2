import java.util.Arrays;

public class Cube {

// This is the array for the face, a good reference is a 2x2 rubiks cube
//
//    [000][001]
//    [010][011]
//
public static int[][][] cube = new int[6][4][64];
public static int[] face = new int[4];
public static int[] cell = new int[64];

// 0 = Top Left, 1 = Top Right, 2 = Bottom Left, 3 = Bottom Right
public static int pointer = 0;

// cubie identifyer, times increased, side
public static int[] TOP_0001 = new int[] {1,0,1};
public static int[] TOP_0010 = new int[] {2,0,1};
public static int[] TOP_0011 = new int[] {3,0,1};
public static int[] TOP_0100 = new int[] {4,0,1};

public static int[] BOTTOM_0001 = new int[] {5,0,2};
public static int[] BOTTOM_0010 = new int[] {6,0,2};
public static int[] BOTTOM_0011 = new int[] {7,0,2};
public static int[] BOTTOM_0100 = new int[] {8,0,2};

public static int[] FRONT_0001 = new int[] {9,0,3};
public static int[] FRONT_0010 = new int[] {10,0,3};
public static int[] FRONT_0011 = new int[] {11,0,3};
public static int[] FRONT_0100 = new int[] {12,0,3};

public static int[] BACK_0001 = new int[] {13,0,4};
public static int[] BACK_0010 = new int[] {14,0,4};
public static int[] BACK_0011 = new int[] {15,0,4};
public static int[] BACK_0100 = new int[] {16,0,4};

public static int[] LEFT_0001 = new int[] {17,0,5};
public static int[] LEFT_0010 = new int[] {18,0,5};
public static int[] LEFT_0011 = new int[] {19,0,5};
public static int[] LEFT_0100 = new int[] {20,0,5};

public static int[] RIGHT_0001 = new int[] {21,0,6};
public static int[] RIGHT_0010 = new int[] {22,0,6};
public static int[] RIGHT_0011 = new int[] {23,0,6};
public static int[] RIGHT_0100 = new int[] {24,0,6};

public static void main(String[] args) {
        System.exit(0);
}

// General functions
public static String[] loadCube() {
        face[0] = TOP_0001[0];
        face[1] = TOP_0010[0];
        face[2] = TOP_0011[0];
        face[3] = TOP_0100[0];
        int side = 0;
        if(face[pointer] < 5) { side = 0; }
        else if(face[pointer] < 9) { side = 1; }
        else if(face[pointer] < 13) { side = 2; }
        else if(face[pointer] < 17) { side = 3; }
        else if(face[pointer] < 21) { side = 4; }
        else if(face[pointer] < 25) { side = 5; }
        else { System.exit(0); }
        return new String[] {Integer.toString(side), Integer.toString(pointer), Integer.toString(face[pointer])};
}

public static boolean cellFull() {
        for(int i = 0; i < 64; i++) {
                if(cell[i] == 0) {
                        return false;
                } else {
                        continue;
                }
        } return true;
}

public static String getCell(String cellCall) {
        if(cellCall.matches("0001")) {
                return Cube.POP(face[0]);
        } else if(cellCall.matches("0010")) {
                return Cube.POP(face[1]);
        } else if(cellCall.matches("0011")) {
                return Cube.POP(face[2]);
        } else if(cellCall.matches("0100")) {
                return Cube.POP(face[3]);
        } else { return "Nothing"; }
}

// Rubiks Functions
public static void MOV(int direction) {
        switch(direction) {
        case 1: // MOV T
                face[0] = face[3];
                face[1] = face[0];
                face[2] = face[1];
                face[3] = face[2];
                break;
        case 2: // MOV B
                break;
        case 3: // MOV L
                break;
        case 4: // MOV R
                break;
        case 5: // MOV F
                break;
        case 6: // MOV B
                break;
        case 7: // MOV T'
                face[0] = face[1];
                face[1] = face[2];
                face[2] = face[3];
                face[3] = face[0];
                break;
        case 8: // MOV B'
                break;
        case 9: // MOV L'
                break;
        case 10: // MOV R'
                break;
        case 11: // MOV F'
                break;
        case 12: // MOV B'
                break;
        }
}

// Stack Functions
public static void PUS(int cellValue) {
        if(!cellFull()) {
                Cube self = new Cube();
                String[] cubeLoc = Cube.loadCube();
                cube[Integer.parseInt(cubeLoc[0])][Integer.parseInt(cubeLoc[1])][Integer.parseInt(cubeLoc[2])] = cellValue;
        }
}
public static String POP(int cellPointer) {
        Cube self = new Cube();
        String[] cubeLoc = Cube.loadCube();
        return Integer.toString(cube[Integer.parseInt(cubeLoc[0])][Integer.parseInt(cubeLoc[1])][cellPointer]);
}
}
