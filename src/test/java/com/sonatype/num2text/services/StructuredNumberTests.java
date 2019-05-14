package com.sonatype.num2text.services;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.not;


public class StructuredNumberTests {
    @Test
    public void testStructuredNumber1() {
        String input = "2147483647";
        StructuredNumber strInput = StructuredNumber.fromInput(input);
        assertThat(strInput.getBillions(), equalTo("2"));
        assertThat(strInput.getMillions(), equalTo("147"));
        assertThat(strInput.getThousands(), equalTo("483"));
        assertThat(strInput.getHundreds(), equalTo("647"));
    }

    @Test
    public void testHundreds() {
        StructuredNumber strInput = StructuredNumber.fromInput("1");
        assertThat(strInput.getBillions(), equalTo(""));
        assertThat(strInput.getMillions(), equalTo(""));
        assertThat(strInput.getThousands(), equalTo(""));
        assertThat(strInput.getHundreds(), equalTo("1"));
    }

    @Test
    public void testThousands() {
        StructuredNumber strInput = StructuredNumber.fromInput("1000");
        assertThat(strInput.getBillions(), equalTo(""));
        assertThat(strInput.getMillions(), equalTo(""));
        assertThat(strInput.getThousands(), equalTo("1"));
        assertThat(strInput.getHundreds(), equalTo("000"));
    }

    @Test
    public void testMillions() {
        StructuredNumber strInput = StructuredNumber.fromInput("1000000");
        assertThat(strInput.getBillions(), equalTo(""));
        assertThat(strInput.getMillions(), equalTo("1"));
        assertThat(strInput.getThousands(), equalTo("000"));
        assertThat(strInput.getHundreds(), equalTo("000"));
    }

    @Test
    public void testBillions() {
        StructuredNumber strInput = StructuredNumber.fromInput("1000000000");
        assertThat(strInput.getBillions(), equalTo("1"));
        assertThat(strInput.getMillions(), equalTo("000"));
        assertThat(strInput.getThousands(), equalTo("000"));
        assertThat(strInput.getHundreds(), equalTo("000"));
    }
}
