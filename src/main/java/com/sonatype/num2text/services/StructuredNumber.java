package com.sonatype.num2text.services;

public class StructuredNumber {

    private String billions = "";
    private String millions = "";
    private String thousands = "";
    private String hundreds = "";

    private StructuredNumber() {}

    public static StructuredNumber fromInput(String input) {
        StructuredNumber strInput = new StructuredNumber();

        StringBuilder builder = new StringBuilder(input);
        builder.reverse();
        String revInput = builder.toString();

        for (int i = 0; i < revInput.length(); i++) {
            char x = revInput.charAt(i);
            if (i > 8) {
                strInput.billions = revInput.charAt(i) + strInput.billions;
            }
            else if (i > 5) {
                strInput.millions = revInput.charAt(i) + strInput.millions;
            }
            else if (i > 2) {
                strInput.thousands = revInput.charAt(i) + strInput.thousands;
            }
            else {
                strInput.hundreds = revInput.charAt(i) + strInput.hundreds;
            }
        }

        return strInput;
    }

    public String getBillions() {
        return billions;
    }

    public void setBillions(String billions) {
        this.billions = billions;
    }

    public String getMillions() {
        return millions;
    }

    public void setMillions(String millions) {
        this.millions = millions;
    }

    public String getThousands() {
        return thousands;
    }

    public void setThousands(String thousands) {
        this.thousands = thousands;
    }

    public String getHundreds() {
        return hundreds;
    }

    public void setHundreds(String hundreds) {
        this.hundreds = hundreds;
    }

    public int getBillionsAsInt() {
        if (billions.length() == 0) {
            return 0;
        }
        return Integer.parseInt(billions);
    }

    public int getMillionsAsInt() {
        if (millions.length() == 0) {
            return 0;
        }
        return Integer.parseInt(millions);
    }

    public int getThousandsAsInt() {
        if (thousands.length() == 0) {
            return 0;
        }
        return Integer.parseInt(thousands);
    }

    public int getHundredsAsInt() {
        if (hundreds.length() == 0) {
            return 0;
        }
        return Integer.parseInt(hundreds);
    }
}
