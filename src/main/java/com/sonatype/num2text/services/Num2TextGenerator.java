package com.sonatype.num2text.services;

public interface Num2TextGenerator {
    /**
     * converts an input number to text representation
     * @param numStr - input number
     * @return
     *  - The input number converted to text
     *  - "Error:..."
     *      - invalid input - input does not contain numeric digits
     *      - input out of range - input number is outside the range of a signed 32-bit integer
     */
    String generateText( String numStr );
}
