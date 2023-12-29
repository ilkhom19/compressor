public class Compressor {

    public static String compress(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c) || c == '±') {
                throw new IllegalArgumentException("Invalid character in input: " + c + " at position " + i + " in " + input);
            }
        }

        StringBuilder compressed = new StringBuilder();
        char currentChar = input.charAt(0);
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == currentChar) {
                count++;
            } else {
                compressed.append(currentChar).append("±").append(count);
                currentChar = input.charAt(i);
                count = 1;
            }
        }

        compressed.append(currentChar).append("±").append(count);

        return compressed.toString();
    }

    public static String decompress(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder decompressed = new StringBuilder();
        char currentChar = input.charAt(0);
        StringBuilder countBuffer = new StringBuilder();

        if (Character.isDigit(currentChar) || currentChar == '±') {
            throw new IllegalArgumentException("Invalid character in input: " + currentChar + " at position 0 in " + input);
        }

        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '±' ){
                if (!Character.isDigit(input.charAt(i+1))){
                    throw new IllegalArgumentException("Invalid character in input: " + input.charAt(i+1) + " at position " + (i+1) + " in " + input);
                }
                continue;
            }

            if (Character.isDigit(c)) {
                countBuffer.append(c);
            } else if (input.charAt(i+1) == '±') {

                int count = Integer.parseInt(countBuffer.toString());
                decompressed.append(String.valueOf(currentChar).repeat(Math.max(0, count)));

                currentChar = c;
                countBuffer.setLength(0);
            } else {
                throw new IllegalArgumentException("Invalid character in input: " + c + " at position " + i + " in " + input);
            }
        }

        // Check for the last character and count
        if (countBuffer.length() == 0) {
            throw new IllegalArgumentException("Invalid input format: no count before the end");
        }

        int count = Integer.parseInt(countBuffer.toString());
        decompressed.append(String.valueOf(currentChar).repeat(Math.max(0, count)));

        return decompressed.toString();
    }
}