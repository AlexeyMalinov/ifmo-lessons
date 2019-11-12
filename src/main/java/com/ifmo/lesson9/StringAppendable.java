package com.ifmo.lesson9;

public class StringAppendable implements Appendable<String, StringAppendable>{

    StringBuilder sb = new StringBuilder();

    String separator;
    public StringAppendable(String separator) {
        this.separator = separator;
    }

    @Override
    public StringAppendable append(String type) {
        if(sb.length() > 0) {
            sb.append(separator);
            sb.append(type);
        } else {
            sb.append(type);
        }
        return this;
    }

    @Override
    public String value() {
        return sb.toString();
    }
}
