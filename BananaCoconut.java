/**
 * This class prints numbers or specific words based on the numerical value.
 * For multiples of 3 it prints out "banana"
 * For multiples of 7 it prints out "coconut"
 * For multiples of 3 and 7 it prints out "banana-coconut"
 * For numbers smaller than 1 it'll print "puttputt"
 */

public class BananaCoconut {

    /**
     * Main method which will be used to process command lines.
     * @param args for command
     */

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("One or more numbers required as a command line argument.");
            System.err.println("Example Usage: java BananaCoconut [number] [number] [...]");
            //prints error message if there aren't enough arguments given
            return;
            //will exit the program if theres no valid arguments
        }

        StringBuilder resultString = new StringBuilder();
        //initalize the string builder 
        for (String arg : args) {
            //converts the numbers based on the equations below
            String result = convertNumbers(arg);
            //checks if result is empty or not
            if (!result.isEmpty()) {
                //if resultzString has something within it, it'll remove any extra spaces
                if (resultString.length() > 0) {
                    resultString.append(" ");
                }
                //appends the result to the resultString
                resultString.append(result);
            }
        }
        if (resultString.length() > 1) {
            //gives the final resultString
            System.out.println(resultString.toString());
        }
    }

    /**
     * Converts a string and returns the values depending on the values.
     * @param arg to proccess
     * @return will return the newly formatted string based on the values inputed
     */
    
    private static String convertNumbers(String arg) {
        try {
            int i = Integer.parseInt(arg);
            //converts string to integer

            if (i < 1) {
                return "puttputt";
                //if value is less than 1 it'll print puttputt
            }

            if (i % 3 == 0 && i % 7 == 0) {
                return "banana-coconut";
                //if the number is a multiple of 3 and 7 it'll print banana-coconut
            }

            if (i % 3 == 0) {
                return "banana";
                //if the number is a multiple of 3 it'll print banana
            }

            if (i % 7 == 0) {
                return "coconut";
                //if the number is a multiple of 7 it'll print coconut
            }

            return String.valueOf(i);
            //other numbers will be returned as a string

        } catch (NumberFormatException e) {
            System.err.println("One or more numbers required as a command line argument.");
            System.err.println("Example Usage: java BananaCoconut [number] [number] [...]");
            return "";
        }
    }
}