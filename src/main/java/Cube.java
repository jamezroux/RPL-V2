import java.util.Arrays;

public class Cube {
/*

   This massive block of variables is the control center for the cube itself
   The [FACE]_[LOCATION] integers contain 3 variables, {[CUBIE_ID][INCREASES][FACE_ID]} where
                                                      CUBIE_ID  = number assigned to that stack
                                                      INCREASES = how many times the data variable has been increased
                                                      FACE_ID   = number representing which of the 4 corners the stack is on

   The [FACE]_[LOCATION]_[DATA] integers contain the stacks for their corresponding cubies

 */
public static int[] TOP_0001 = new int[] {1,0,0};    public static int[] TOP_0010_DATA  = new int[64];
public static int[] TOP_0010 = new int[] {2,0,0};    public static int[] TOP_0001_DATA  = new int[64];
public static int[] TOP_0011 = new int[] {3,0,0};    public static int[] TOP_0011_DATA  = new int[64];
public static int[] TOP_0100 = new int[] {4,0,0};    public static int[] TOP_0100_DATA  = new int[64];

public static int[] BOTTOM_0001 = new int[] {5,0,1}; public static int[] BOTTOM_0001_DATA = new int[64];
public static int[] BOTTOM_0010 = new int[] {6,0,1}; public static int[] BOTTOM_0010_DATA = new int[64];
public static int[] BOTTOM_0011 = new int[] {7,0,1}; public static int[] BOTTOM_0011_DATA = new int[64];
public static int[] BOTTOM_0100 = new int[] {8,0,1}; public static int[] BOTTOM_0100_DATA = new int[64];

public static int[] FRONT_0001 = new int[] {9,0,2};  public static int[] FRONT_0001_DATA = new int[64];
public static int[] FRONT_0010 = new int[] {10,0,2}; public static int[] FRONT_0010_DATA = new int[64];
public static int[] FRONT_0011 = new int[] {11,0,2}; public static int[] FRONT_0011_DATA = new int[64];
public static int[] FRONT_0100 = new int[] {12,0,2}; public static int[] FRONT_0100_DATA = new int[64];

public static int[] BACK_0001 = new int[] {13,0,3};  public static int[] BACK_0001_DATA = new int[64];
public static int[] BACK_0010 = new int[] {14,0,3};  public static int[] BACK_0010_DATA = new int[64];
public static int[] BACK_0011 = new int[] {15,0,3};  public static int[] BACK_0011_DATA = new int[64];
public static int[] BACK_0100 = new int[] {16,0,3};  public static int[] BACK_0100_DATA = new int[64];

public static int[] LEFT_0001 = new int[] {17,0,4};  public static int[] LEFT_0001_DATA = new int[64];
public static int[] LEFT_0100 = new int[] {20,0,4};  public static int[] LEFT_0100_DATA = new int[64];
public static int[] LEFT_0010 = new int[] {18,0,4};  public static int[] LEFT_0010_DATA = new int[64];
public static int[] LEFT_0011 = new int[] {19,0,4};  public static int[] LEFT_0011_DATA = new int[64];

public static int[] RIGHT_0001 = new int[] {21,0,5}; public static int[] RIGHT_0001_DATA = new int[64];
public static int[] RIGHT_0100 = new int[] {24,0,5}; public static int[] RIGHT_0100_DATA = new int[64];
public static int[] RIGHT_0010 = new int[] {22,0,5}; public static int[] RIGHT_0010_DATA = new int[64];
public static int[] RIGHT_0011 = new int[] {23,0,5}; public static int[] RIGHT_0011_DATA = new int[64];

/*

   This string[][] is how we will keep track of the cubies locations so that we can move the cube around
   Whenever we do a movement, say T (or turn the top clockwise) all we have to do is move the words around like so:

    String[][] cubeTemp = cube;
    // Move the top 4 cubie faces
    cube[0][0] = cubeTemp[0][3];
    cube[0][1] = cubeTemp[0][0];
    cube[0][2] = cubeTemp[0][1];
    cube[0][3] = cubeTemp[0][2];
    // Move the front top 2 cubie faces
    cube[2][0] = cubeTemp[4][0];
    cube[2][1] = cubeTemp[4][1];
    // Move the right top 2 cubie faces
    cube[5][0] = cubeTemp[2][0];
    cube[5][1] = cubeTemp[2][1];
    // Move the left top 2 cubie faces
    cube[4][0] = cubeTemp[3][0];
    cube[4][1] = cubeTemp[3][1];
    // Move the back top 2 cubie faces
    cube[3][0] = cubeTemp[4][0];
    cube[3][1] = cubeTemp[4][1];

   Each rubiks operation should be able to be coded that easily. Hopefully. Each face has a permenat assigned int:
      Top: 0
      Bottom: 1
      Front: 2
      Back: 3
      Left: 4
      Right: 5

 */
public static String[][] cube = new String[][] { {"TOP_0001", "TOP_0010", "TOP_0011", "TOP_0100"},
                                                 {"BOTTOM_0001", "BOTTOM_0010", "BOTTOM_0011", "BOTTOM_0100"},
                                                 {"FRONT_0001", "FRONT_0010", "FRONT_0011", "FRONT_0100"},
                                                 {"BACK_0001", "BACK_0010", "BACK_0011", "BACK_0100"},
                                                 {"LEFT_0001", "LEFT_0010", "LEFT_0011", "LEFT_0100"},
                                                 {"RIGHT_0001", "RIGHT_0010", "RIGHT_0011", "RIGHT_0100"} };

public static int[] side = new int[6];
public static String[] face = new String[4];
public static int[] cell = new int[64];

// 0 = Top Left, 1 = Top Right, 2 = Bottom Left, 3 = Bottom Right
public static int pointer = 0;

public static void main(String[] args) {
        System.exit(0);
}
// General functions, these aren't usually called outside this class
// Trying to rework the code to use getCubie() and getCubieData() rather than loadCube(), only two left to change are CID() and PUS()
public static String[] loadCube() {
        // Initialize the face wih the top side
        face[0] = cube[0][0];
        face[1] = cube[0][1];
        face[2] = cube[0][2];
        face[3] = cube[0][3];

        return face;
}
public static int[] getCubie(String reference) {
        int[] cubie = new int[2];
        if(reference.matches(null)) {
                cubie[0] = 64;
                cubie[1] = 64;
                return cubie;
        }
        else if (reference.matches("TOP_0001")) { return TOP_0001; }
        else if (reference.matches("TOP_0010")) { return TOP_0010; }
        else if (reference.matches("TOP_0011")) { return TOP_0011; }
        else if (reference.matches("TOP_0100")) { return TOP_0100; }
        else if (reference.matches("BOTTOM_0001")) { return BOTTOM_0001; }
        else if (reference.matches("BOTTOM_0010")) { return BOTTOM_0010; }
        else if (reference.matches("BOTTOM_0011")) { return BOTTOM_0011; }
        else if (reference.matches("BOTTOM_0100")) { return BOTTOM_0100; }
        else if (reference.matches("FRONT_0001")) { return FRONT_0001; }
        else if (reference.matches("FRONT_0010")) { return FRONT_0010; }
        else if (reference.matches("FRONT_0011")) { return FRONT_0011; }
        else if (reference.matches("FRONT_0100")) { return FRONT_0100; }
        else if (reference.matches("BACK_0001")) { return BACK_0001; }
        else if (reference.matches("BACK_0010")) { return BACK_0010; }
        else if (reference.matches("BACK_0011")) { return BACK_0011; }
        else if (reference.matches("BACK_0100")) { return BACK_0100; }
        else if (reference.matches("LEFT_0001")) { return LEFT_0001; }
        else if (reference.matches("LEFT_0010")) { return LEFT_0010; }
        else if (reference.matches("LEFT_0011")) { return LEFT_0011; }
        else if (reference.matches("LEFT_0100")) { return LEFT_0100; }
        else if (reference.matches("RIGHT_0001")) { return RIGHT_0001; }
        else if (reference.matches("RIGHT_0010")) { return RIGHT_0010; }
        else if (reference.matches("RIGHT_0011")) { return RIGHT_0011; }
        else if (reference.matches("RIGHT_0100")) { return RIGHT_0100; }
}
public static int[] getCubieData(String reference) {
        int[] cubie = new int[2];
        if(reference.matches(null)) {
                cubie[0] = 64;
                cubie[1] = 64;
                return cubie;
        }
        else if (reference.matches("TOP_0001")) { return TOP_0001_DATA; }
        else if (reference.matches("TOP_0010")) { return TOP_0010_DATA; }
        else if (reference.matches("TOP_0011")) { return TOP_0011_DATA; }
        else if (reference.matches("TOP_0100")) { return TOP_0100_DATA; }
        else if (reference.matches("BOTTOM_0001")) { return BOTTOM_0001_DATA; }
        else if (reference.matches("BOTTOM_0010")) { return BOTTOM_0010_DATA; }
        else if (reference.matches("BOTTOM_0011")) { return BOTTOM_0011_DATA; }
        else if (reference.matches("BOTTOM_0100")) { return BOTTOM_0100_DATA; }
        else if (reference.matches("FRONT_0001")) { return FRONT_0001_DATA; }
        else if (reference.matches("FRONT_0010")) { return FRONT_0010_DATA; }
        else if (reference.matches("FRONT_0011")) { return FRONT_0011_DATA; }
        else if (reference.matches("FRONT_0100")) { return FRONT_0100_DATA; }
        else if (reference.matches("BACK_0001")) { return BACK_0001_DATA; }
        else if (reference.matches("BACK_0010")) { return BACK_0010_DATA; }
        else if (reference.matches("BACK_0011")) { return BACK_0011_DATA; }
        else if (reference.matches("BACK_0100")) { return BACK_0100_DATA; }
        else if (reference.matches("LEFT_0001")) { return LEFT_0001_DATA; }
        else if (reference.matches("LEFT_0010")) { return LEFT_0010_DATA; }
        else if (reference.matches("LEFT_0011")) { return LEFT_0011_DATA; }
        else if (reference.matches("LEFT_0100")) { return LEFT_0100_DATA; }
        else if (reference.matches("RIGHT_0001")) { return RIGHT_0001_DATA; }
        else if (reference.matches("RIGHT_0010")) { return RIGHT_0010_DATA; }
        else if (reference.matches("RIGHT_0011")) { return RIGHT_0011_DATA; }
        else if (reference.matches("RIGHT_0100")) { return RIGHT_0100_DATA; }
}
public static boolean isCellFull() {           // Get
        int[] cubie = getCubieData(face[pointer]);

        for(int i = 0; i < 64; i++) {
                if(cubie[i] == 0) {
                        return false;
                } else {
                        continue;
                }
        } return true;
}
public static String getCellByID(String cellCall) {
        if(cellCall.matches("0001")) {
                return Cube.POP(face[0]);
        } else if(cellCall.matches("0010")) {
                return Cube.POP(face[1]);
        } else if(cellCall.matches("0011")) {
                return Cube.POP(face[2]);
        } else if(cellCall.matches("0100")) {
                return Cube.POP(face[3]);
        } else {
                System.out.println("Error, this is not a cell id.");
                System.exit(0);
        }
}
// Rubiks Functions, these move around the arrays to build the cube
public static void MOV(int direction) {
        switch(direction) {
        case 1:           // MOV T
                face[0] = face[3];
                face[1] = face[0];
                face[2] = face[1];
                face[3] = face[2];
                break;
        case 2:           // MOV B
                break;
        case 3:           // MOV L
                break;
        case 4:           // MOV R
                break;
        case 5:           // MOV F
                break;
        case 6:           // MOV B
                break;
        case 7:           // MOV T'
                face[0] = face[1];
                face[1] = face[2];
                face[2] = face[3];
                face[3] = face[0];
                break;
        case 8:           // MOV B'
                break;
        case 9:           // MOV L'
                break;
        case 10:           // MOV R'
                break;
        case 11:           // MOV F'
                break;
        case 12:           // MOV B'
                break;
        }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Stack Functions, these will usually be called directly from the interpreter
public static void PUS(int cellValue) {
        if(!isCellFull()) {
                Cube self = new Cube();
                String[] cubeLoc = Cube.loadCube();
                cube[Integer.parseInt(cubeLoc[0])][Integer.parseInt(cubeLoc[1])][Integer.parseInt(cubeLoc[2])] = cellValue;
        }
}
public static String POP(String pointer) {
        int[] cubieData = getCubieData(face[pointer]);
        int[] cubie = getCubie(face[pointer]);
        int value = cubieData[cubieData.length-1];


        return value;

        return Integer.toString(cube[Integer.parseInt(cubeLoc[0])][Integer.parseInt(cubeLoc[1])][pointer]);
}
public static boolean CID(int cellPointer) {
        Cube self = new Cube();
        String[] cubeLoc = Cube.loadCube();
        if(cube[Integer.parseInt(cubeLoc[0])][Integer.parseInt(cubeLoc[1])][Integer.parseInt(cubeLoc[2])].matches(null)) {
                return false;
        } else { return true; }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
