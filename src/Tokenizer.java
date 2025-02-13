public class Tokenizer {
    public static void main(String[] args) {
        String user_input = "Hello, my name is Kent Ancla. I am gay 3.0. this is my phone_number_0969-662-4210";
        // Remove underscore sa input
        String[] parts = user_input.split("_");

        // index counter for the array
        int indexCount = 0;
        for (String part : parts) {
            indexCount += countElements(part);
        }

        // set number of index sa array
        String[] array = new String[indexCount];
        int index = 0;

        // separate special characters na dikit
        for (String part : parts) { //shortcut sa for loop since di man gamiton ang index
            StringBuilder hold = new StringBuilder();
            for (int i = 0; i < part.length(); i++) {
                char c = part.charAt(i);

                // Check if letter or digit
                if (Character.isDigit(c) || // check kung ang '.' followed or before niya is digit
                        (c == '.' && !hold.isEmpty() && Character.isDigit(hold.charAt(hold.length() - 1)) && (i + 1 < part.length() && Character.isDigit(part.charAt(i + 1))))) {
                    hold.append(c); //add sa hold
                } else if (Character.isLetterOrDigit(c)) { //if letter or number
                    hold.append(c); //add
                } else {
                    if (!hold.isEmpty()) { //if naay sulod ang hold
                        array[index++] = hold.toString(); // Add the word/number
                        hold.setLength(0);//reset hold
                    }
                    array[index++] = String.valueOf(c); // Add the special character
                }
            }
            if (!hold.isEmpty()) {
                array[index++] = hold.toString(); // Add the last word/number
            }
        }

        System.out.println("---------\t Phase 1 \t---------");
        phase1(array);
        System.out.println("---------\t Phase 2 \t---------");
        phase2(array);
    }

    // counter
    private static int countElements(String part) {
        int count = 0;
        StringBuilder hold = new StringBuilder();
        for (int i = 0; i < part.length(); i++) {
            char c = part.charAt(i);

            // same conditional sa taas but different action[add sa counter]
            if (Character.isDigit(c) ||
                    (c == '.' && !hold.isEmpty() && Character.isDigit(hold.charAt(hold.length() - 1)) && (i + 1 < part.length() && Character.isDigit(part.charAt(i + 1))))) {
                hold.append(c);
            } else if (Character.isLetterOrDigit(c)) {
                hold.append(c);
            } else {
                if (!hold.isEmpty()) { //if naay sulod, add count para sa index, then reset
                    count++;
                    hold.setLength(0);
                }
                count++;
            }
        }
        if (!hold.isEmpty()) {
            count++;
        }
        return count;
    }


    public static void phase1(String[] array) {
        int end=0;
        for (String s : array) {
            boolean hasDigits = false; //pang determine if naa digit/letter/special character ang token
            boolean hasLetter = false;
            boolean hasSpecial = false;
            for (int y = 0; y < s.length(); y++) {
                char tokenChar = s.charAt(y); //separate each index into character
                if (Character.isDigit(tokenChar)) {
                    hasDigits = true; //set true
                } else if (Character.isAlphabetic(tokenChar)) {
                    hasLetter = true; //set true
                } else if (tokenChar == ',' | tokenChar == '!' | tokenChar == '@' | tokenChar == '-' | tokenChar == '#' | tokenChar == '$' |  //best coder
                        tokenChar == '%' | tokenChar == '^' | tokenChar == '(' | tokenChar == ')' | tokenChar == '.') {
                    hasSpecial = true; //set true
                }
            }
            if (hasLetter && hasDigits && !hasSpecial) { //if naa letter and number tas wala special char
                System.out.println("Token\t: \"" + s + "\" - Type: AlphaNumeric");
                end++;
            } else if (hasLetter && !hasSpecial) { //pag letter lang
                System.out.println("Token\t: \"" + s + "\" - Type: Word");
                end++;
            } else if (hasDigits && !hasSpecial) { //pag digit lang
                System.out.println("Token\t: \"" + s + "\" - Type: Number");
                end++;
            } else if (hasSpecial){ //special child
                System.out.println("Token\t: \"" + s + "\" - Type: Punctuation");
                end++;
            }
            if(end>= array.length){ //if end na sa array mag print
                System.out.println("Token\t: \"\\n\" - Type: End of Line");
            }

        }
    }

    public static void phase2(String[] array) {
        for (String token : array) {
            if (!token.matches("[a-zA-Z0-9]+")) { //remove special characters
                continue;
            }
            StringBuilder result = new StringBuilder("Token\t: \"" + token + "\"\t->\t"); //output na i print
            for (int i = 0; i < token.length(); i++) { //add
                result.append("'").append(token.charAt(i)).append("'");
                if (i < token.length() - 1) { //add na may comma
                    result.append(", ");
                }
            }
            System.out.println(result);
        }
    }
}

