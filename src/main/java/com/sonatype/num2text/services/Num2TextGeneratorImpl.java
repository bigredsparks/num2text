package com.sonatype.num2text.services;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Num2TextGeneratorImpl implements Num2TextGenerator {

    // pattern for matching the input number
    Pattern pattern = Pattern.compile("^-?\\d{1,10}$");

    public String generateText( String numStr ) {

        // validate input number
        if (!validateInput(numStr)) {
            return "Error: Invalid input.";
        }

        // validate range of input number
        if (!validateRange(numStr)) {
            return "Error: Number out of range.";
        }

        StringBuilder textBuilder = new StringBuilder();

        if (numStr.startsWith("-")) {
            textBuilder.append("Negative ");
            numStr = numStr.substring(1);
        }

        StructuredNumber strNum = StructuredNumber.fromInput(numStr);

        // convert billions

        // get 3 billions digits
        int billions = strNum.getBillionsAsInt();
        if (billions != 0) {
            // convert the billions digits to text
            textBuilder.append(convertThreeDigits(billions));
            textBuilder.append(" billion ");
        }

        // get millons

        // get 3 millions digits
        int millions = strNum.getMillionsAsInt();
        if (millions != 0) {
            // convert the millions digits to text
            textBuilder.append(convertThreeDigits(millions));
            textBuilder.append(" million ");
        }

        // get thousands

        // get 3 thousands digits
        int thousands = strNum.getThousandsAsInt();
        if (thousands != 0) {
            // convert the thousands digits to text
            textBuilder.append(convertThreeDigits(thousands));
            textBuilder.append(" thousand ");
        }

        // get hundreds

        // get 3 hundreds digits
        int hundreds = strNum.getHundredsAsInt();
        if (hundreds != 0) {
            // convert the hundreds digits to text
            textBuilder.append(convertThreeDigits(hundreds));
        }

        // get the text from the builder and trim
        String text = textBuilder.toString().trim();

        // was any text generated?
        if (text.length() == 0 || text.equals("Negative")) {
            // no, return zero
            return "Zero";
        }

        // capitalize first letter
        text = String.format("%c%s", Character.toUpperCase(text.charAt(0)), text.substring(1));

        // return generated text
        return text;
    }

    /**
     * validate that the input is a positive or negative number
     * @param number - input number
     * @return true if valid number
     */
    private boolean validateInput( String number ) {
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * validate that number is in the signed, 32-bit integer range
     * @param number - input number
     * @return - true if number is in the range
     */
    private boolean validateRange( String number ) {
        // convert to a long number
        Long longNum = Long.parseLong(number);
        return (longNum >= Integer.MIN_VALUE && longNum <= Integer.MAX_VALUE);
    }

    /**
     * converts 3 digits to text.  for example - an input of 123 returns "one hundred twenty three"
     * @param threeDigits - three input digits
     * @return - text equivalent
     */
    private String convertThreeDigits( int threeDigits ) {
        StringBuilder textBuilder = new StringBuilder();

        // isolate the hundreds digit
        int hundreds = threeDigits / 100;

        // add hundreds digit if not zero
        if (hundreds != 0) {
            textBuilder.append(String.format("%s hundred ", convertSingleDigit(hundreds)));
        }

        // isolate tens digit
        int tens = threeDigits % 100;

        // is tens digit 1?
        if (tens / 10 == 1) {
            // yes, this convert teens
            textBuilder.append(String.format("%s ", convertTeens(tens)));

        } else if (tens != 0) {
            // convert tens digit to text
            textBuilder.append(String.format("%s ", convertTensDigit(tens / 10)));

            // convert the ones digit
            int ones = tens % 10;
            if (ones != 0) {
                textBuilder.append(convertSingleDigit(ones));
            }
        }

        // return generated three digits
        return textBuilder.toString().trim();

    }

    /**
     * convert tens digit to text - for example, an input of 3 returns "thirty"
     * @param tensDigit - input
     * @return - tens digit converted to text
     */
    private String convertTensDigit(int tensDigit) {
        switch (tensDigit) {
            case 2: return "twenty";
            case 3: return "thirty";
            case 4: return "forty";
            case 5: return "fifty";
            case 6: return "sixty";
            case 7: return "seventy";
            case 8: return "eighty";
            case 9: return "ninety";
        }
        return "";
    }

    /**
     * converts single digit to text - for example, an input of 4 returns "four".
     * @param singleDigit - input ones digit
     * @return - input converted text
     */
    private String convertSingleDigit( int singleDigit ) {

        switch ( singleDigit ) {
            case 0: return "zero";
            case 1: return "one";
            case 2: return "two";
            case 3: return "three";
            case 4: return "four";
            case 5: return "five";
            case 6: return "six";
            case 7: return "seven";
            case 8: return "eight";
            case 9: return "nine";
        }

        return "";
    }

    /**
     * convert two digit number between 10 and 19 to text - for example an input of 13 returns "thirteen"
     * @param teens - input
     * @return - input converted to text
     */
    private String convertTeens( int teens ) {
        switch ( teens ) {
            case 10: return "ten";
            case 11: return "eleven";
            case 12: return "twelve";
            case 13: return "thirteen";
            case 14: return "fourteen";
            case 15: return "fifteen";
            case 16: return "sixteen";
            case 17: return "seventeen";
            case 18: return "eighteen";
            case 19: return "nineteen";
        }

        return "";
    }

    /**
     * extract the digits from a large number from a starting position (exponent)
     * to an ending position (exponent)
     * @param num - the number from which the digits are extracted
     * @param startExp - position where to start extracting digits. ignored if zero
     * @param endExp - position where to end extracting digits
     * @return - the extracted numbers requested
     */
    private int getDigits(int num, int startExp, int endExp) {

        int val = num;

        if (startExp > 0) {
            // get the starting point - 10 to the power of start
            int start = (int) Math.pow(10, startExp);

            // trim leading digits - modulo remainder of start
            val = num % start;
        }

        // get the ending point - 10 to the power of end
        int end = (int) Math.pow(10, endExp);

        // trim trailing digits - integer division of end
        val = val / end;

        return val;
    }
}
