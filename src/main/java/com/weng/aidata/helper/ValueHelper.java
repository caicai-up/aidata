package com.weng.aidata.helper;

public class ValueHelper {
    public static String formatDate(String input) {
        if (input == null) return null;
        String result = input;
        if (result.startsWith("+")) result = result.substring(1);
        if (result.contains("T")) result = result.substring(0, result.indexOf("T"));
        return result;
    }

    public static String escape(String input) {
        if (input == null) return null;
        String result = input.replace("\\", "\\\\").replace("\'", "\\\'");
        return "'" + result + "'";
    }
}
