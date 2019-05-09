package com.sonatype.num2text;

import com.sonatype.num2text.services.Num2TextGeneratorImpl;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.not;

public class Num2TextGenerateTests {

    @Test
    public void testSingleDigitGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("0"), equalTo("Zero"));
        assertThat(generator.generateText("1"), equalTo("One"));
        assertThat(generator.generateText("2"), equalTo("Two"));
        assertThat(generator.generateText("3"), equalTo("Three"));
        assertThat(generator.generateText("4"), equalTo("Four"));
        assertThat(generator.generateText("5"), equalTo("Five"));
        assertThat(generator.generateText("6"), equalTo("Six"));
        assertThat(generator.generateText("7"), equalTo("Seven"));
        assertThat(generator.generateText("8"), equalTo("Eight"));
        assertThat(generator.generateText("9"), equalTo("Nine"));
    }

    @Test
    public void testDoubleDigitGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("00"), equalTo("Zero"));
        assertThat(generator.generateText("01"), equalTo("One"));

        assertThat(generator.generateText("20"), equalTo("Twenty"));
        assertThat(generator.generateText("21"), equalTo("Twenty one"));
        assertThat(generator.generateText("22"), equalTo("Twenty two"));
        assertThat(generator.generateText("23"), equalTo("Twenty three"));
        assertThat(generator.generateText("24"), equalTo("Twenty four"));
        assertThat(generator.generateText("25"), equalTo("Twenty five"));
        assertThat(generator.generateText("26"), equalTo("Twenty six"));
        assertThat(generator.generateText("27"), equalTo("Twenty seven"));
        assertThat(generator.generateText("28"), equalTo("Twenty eight"));
        assertThat(generator.generateText("29"), equalTo("Twenty nine"));

        assertThat(generator.generateText("30"), equalTo("Thirty"));
        assertThat(generator.generateText("40"), equalTo("Forty"));
        assertThat(generator.generateText("50"), equalTo("Fifty"));
        assertThat(generator.generateText("60"), equalTo("Sixty"));
        assertThat(generator.generateText("70"), equalTo("Seventy"));
        assertThat(generator.generateText("80"), equalTo("Eighty"));
        assertThat(generator.generateText("90"), equalTo("Ninety"));
    }

    @Test
    public void testThreeDigitGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("100"), equalTo("One hundred"));
    }

    @Test
    public void testTeenGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("10"), equalTo("Ten"));
        assertThat(generator.generateText("11"), equalTo("Eleven"));
        assertThat(generator.generateText("12"), equalTo("Twelve"));
        assertThat(generator.generateText("13"), equalTo("Thirteen"));
        assertThat(generator.generateText("14"), equalTo("Fourteen"));
        assertThat(generator.generateText("15"), equalTo("Fifteen"));
        assertThat(generator.generateText("16"), equalTo("Sixteen"));
        assertThat(generator.generateText("17"), equalTo("Seventeen"));
        assertThat(generator.generateText("18"), equalTo("Eighteen"));
        assertThat(generator.generateText("19"), equalTo("Nineteen"));
    }

    @Test
    public void testThousandsGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("1000"), equalTo("One thousand"));
        assertThat(generator.generateText("10000"), equalTo("Ten thousand"));
        assertThat(generator.generateText("100000"), equalTo("One hundred thousand"));
    }

    @Test
    public void testMillionsGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("1000000"), equalTo("One million"));
        assertThat(generator.generateText("10000000"), equalTo("Ten million"));
        assertThat(generator.generateText("100000000"), equalTo("One hundred million"));
    }

    @Test
    public void testBillionsGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("1000000000"), equalTo("One billion"));
    }

    @Test
    public void testNegativeNumberGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("-0"), equalTo("Zero"));
        assertThat(generator.generateText("-1"), equalTo("Negative one"));
        assertThat(generator.generateText("-10"), equalTo("Negative ten"));
        assertThat(generator.generateText("-100"), equalTo("Negative one hundred"));
        assertThat(generator.generateText("-1000"), equalTo("Negative one thousand"));
        assertThat(generator.generateText("-10000"), equalTo("Negative ten thousand"));
        assertThat(generator.generateText("-100000"), equalTo("Negative one hundred thousand"));
        assertThat(generator.generateText("-1000000"), equalTo("Negative one million"));
        assertThat(generator.generateText("-10000000"), equalTo("Negative ten million"));
    }

    @Test
    public void testInputValidation() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("-"), startsWith("Error"));
        assertThat(generator.generateText("abc"), startsWith("Error"));
        assertThat(generator.generateText("123abc"), startsWith("Error"));

        // length validation
        assertThat(generator.generateText("10000000000"), startsWith("Error"));
        assertThat(generator.generateText("-10000000000"), startsWith("Error"));
    }

    @Test
    public void testRangeValidation() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("2147483648"), startsWith("Error"));
        assertThat(generator.generateText("2147483647"), not(startsWith("Error")));

        assertThat(generator.generateText("-2147483649"), startsWith("Error"));
        assertThat(generator.generateText("-2147483648"), not(startsWith("Error")));
    }

}
