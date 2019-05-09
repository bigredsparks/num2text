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
        assertThat(generator.generateText("0"), equalTo("zero"));
        assertThat(generator.generateText("1"), equalTo("one"));
        assertThat(generator.generateText("2"), equalTo("two"));
        assertThat(generator.generateText("3"), equalTo("three"));
        assertThat(generator.generateText("4"), equalTo("four"));
        assertThat(generator.generateText("5"), equalTo("five"));
        assertThat(generator.generateText("6"), equalTo("six"));
        assertThat(generator.generateText("7"), equalTo("seven"));
        assertThat(generator.generateText("8"), equalTo("eight"));
        assertThat(generator.generateText("9"), equalTo("nine"));
    }

    @Test
    public void testDoubleDigitGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("00"), equalTo("zero"));
        assertThat(generator.generateText("01"), equalTo("one"));

        assertThat(generator.generateText("20"), equalTo("twenty"));
        assertThat(generator.generateText("21"), equalTo("twenty one"));
        assertThat(generator.generateText("22"), equalTo("twenty two"));
        assertThat(generator.generateText("23"), equalTo("twenty three"));
        assertThat(generator.generateText("24"), equalTo("twenty four"));
        assertThat(generator.generateText("25"), equalTo("twenty five"));
        assertThat(generator.generateText("26"), equalTo("twenty six"));
        assertThat(generator.generateText("27"), equalTo("twenty seven"));
        assertThat(generator.generateText("28"), equalTo("twenty eight"));
        assertThat(generator.generateText("29"), equalTo("twenty nine"));

        assertThat(generator.generateText("30"), equalTo("thirty"));
        assertThat(generator.generateText("40"), equalTo("forty"));
        assertThat(generator.generateText("50"), equalTo("fifty"));
        assertThat(generator.generateText("60"), equalTo("sixty"));
        assertThat(generator.generateText("70"), equalTo("seventy"));
        assertThat(generator.generateText("80"), equalTo("eighty"));
        assertThat(generator.generateText("90"), equalTo("ninety"));
    }

    @Test
    public void testThreeDigitGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("100"), equalTo("one hundred"));
    }

    @Test
    public void testTeenGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("10"), equalTo("ten"));
        assertThat(generator.generateText("11"), equalTo("eleven"));
        assertThat(generator.generateText("12"), equalTo("twelve"));
        assertThat(generator.generateText("13"), equalTo("thirteen"));
        assertThat(generator.generateText("14"), equalTo("fourteen"));
        assertThat(generator.generateText("15"), equalTo("fifteen"));
        assertThat(generator.generateText("16"), equalTo("sixteen"));
        assertThat(generator.generateText("17"), equalTo("seventeen"));
        assertThat(generator.generateText("18"), equalTo("eighteen"));
        assertThat(generator.generateText("19"), equalTo("nineteen"));
    }

    @Test
    public void testThousandsGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("1000"), equalTo("one thousand"));
        assertThat(generator.generateText("10000"), equalTo("ten thousand"));
        assertThat(generator.generateText("100000"), equalTo("one hundred thousand"));
    }

    @Test
    public void testMillionsGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("1000000"), equalTo("one million"));
        assertThat(generator.generateText("10000000"), equalTo("ten million"));
        assertThat(generator.generateText("100000000"), equalTo("one hundred million"));
    }

    @Test
    public void testBillionsGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("1000000000"), equalTo("one billion"));
    }

    @Test
    public void testNegativeNumberGeneration() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("-0"), equalTo("zero"));
        assertThat(generator.generateText("-1"), equalTo("negative one"));
        assertThat(generator.generateText("-10"), equalTo("negative ten"));
        assertThat(generator.generateText("-100"), equalTo("negative one hundred"));
        assertThat(generator.generateText("-1000"), equalTo("negative one thousand"));
        assertThat(generator.generateText("-10000"), equalTo("negative ten thousand"));
        assertThat(generator.generateText("-100000"), equalTo("negative one hundred thousand"));
        assertThat(generator.generateText("-1000000"), equalTo("negative one million"));
        assertThat(generator.generateText("-10000000"), equalTo("negative ten million"));
    }

    @Test
    public void testInputValidation() {
        Num2TextGeneratorImpl generator = new Num2TextGeneratorImpl();
        assertThat(generator.generateText("-"), startsWith("Error"));
        assertThat(generator.generateText("abc"), startsWith("Error"));
        assertThat(generator.generateText("123abc"), startsWith("Error"));
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
