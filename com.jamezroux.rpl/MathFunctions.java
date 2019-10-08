import java.util.Arrays;

// Each time a math opcode is used, it will pass the cells and / or numbers to
// this class with the operand it is. This class will return the result.

public class MathFunctions {
public static void main(String[] args) {

}

// I spent a lot of time coding these useless functions
/*public static int old_addBinary(long valueOne, long valueTwo) {
        StringBuilder returnValue = new StringBuilder();

        int i = 0;
        int carry = 0;

        //int[] returnValue = new int[9];
        int[] sum = new int[10];

        while(valueOne != 0 || valueTwo != 0) {
                sum[i++] = (int)(valueOne % 10 + valueTwo % 10 + carry) % 2;
                carry = (int)(valueOne % 10 + valueTwo % 10 + carry) / 2;
                valueOne = valueOne / 10;
                valueTwo = valueTwo / 10;
        }

        if(carry != 0) {
                System.out.println("Carry is not 0: " + carry);
                sum[i++] = carry;
        }

        System.out.println("After Carry");

        i++;

        System.out.println("Before Reverse: " + Arrays.toString(sum));

        while(i >= 0) {
                returnValue.append(sum[i--]);
        }

        System.out.println("After Reverse: " + returnValue.toString());

        //int finalReturnValue = Integer.parseInt(returnValue.toString());
        int finalReturnValue = Integer.parseInt(Arrays.toString(sum));

        return finalReturnValue;
   }
 */
/*public static int addBinary(int valueOne, int valueTwo) {

        StringBuilder finalResult = new StringBuilder();
        boolean carry = false;
        int[] result = new int[] {0,0,0,0,0,0,0,0,0,0};
        int charOne = 0;
        int charTwo = 0;
        int character = 9;
        int slot;

        System.out.println("valueOne: " + valueOne + "\tvalueTwo: " + valueTwo);
        String[] valueOneStr = Integer.toString(valueOne).split("");
        String[] valueTwoStr = Integer.toString(valueTwo).split("");
        System.out.println("valueOneStr Length: " + valueOneStr.length + "\tvalueOneStr: " + Arrays.toString(valueOneStr));
        System.out.println("valueTwoStr Length: " + valueTwoStr.length + "\tvalueTwoStr: " + Arrays.toString(valueTwoStr));

        while(character >= 0) {
                charOne = Integer.parseInt(valueOneStr[character]);
                charTwo = Integer.parseInt(valueTwoStr[character]);;
                slot = result[character];

                if((charOne + charTwo + slot) == 0) {
                        result[character] = 0;
                } else if((charOne + charTwo + slot) == 1) {
                        result[character] = 1;
                } else if((charOne + charTwo + slot) == 2) {
                        result[character] = 1;
                        result[character-1] = 1;
                } else if((charOne + charTwo + slot) == 3) {
                        result[character] = 1;
                        result[character-1] = 1;
                } else {

                }
                character--;
        }

        for(int num : result) {
                finalResult.append(num);
        }

        return Integer.parseInt(finalResult.toString());
   }*/

}
