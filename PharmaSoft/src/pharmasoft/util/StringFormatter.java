/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmasoft.util;

import java.awt.Dimension;
import java.math.BigDecimal;

/**
 *
 * @author gayan
 */
public class StringFormatter {

//    public static Dimension getFormLocation(Dimension JInnerPanel, Dimension FormPannel) {
//        Dimension dimension = new Dimension();
//
//        int height = (int) ((JInnerPanel.getHeight() / 2) - (FormPannel.getHeight() / 2));
//        int width = (int) ((JInnerPanel.getWidth() / 2) - (FormPannel.getWidth() / 2));
//
//        dimension.setSize(width, height);
//        return dimension;
//    }

//     public static String getNextTrnSerial(int prevTrnSerial) {
//        prevTrnSerial++;
//        if (prevTrnSerial == 999999999) {
//            prevTrnSerial = 0;
//        }
//        return padLeft(Integer.toString(prevTrnSerial), 9, "0");
//    }
    public static String padLeft(String strToPad, int len, String strFiller) {
        int fillLen = len - strToPad.length();
        if (fillLen <= 0) {
            return strToPad;
        } else {
            String temp = "";
            for (int i = 0; i < fillLen; i++) {
                temp = temp + strFiller;
            }
            return temp + strToPad;
        }
    }

    public static String padRight(String strToPad, int len, String strFiller) {
        int fillLen = len - strToPad.length();
        if (fillLen <= 0) {
            return strToPad;
        } else {
            String temp = "";
            for (int i = 0; i < fillLen; i++) {
                temp = temp + strFiller;
            }
            return strToPad + temp;
        }
    }

    public static String getNextSerial(String prevTrn, int serialLength, int maxNumber) {

        int prevTrnSerial = Integer.parseInt(prevTrn);
        prevTrnSerial++;
        if (prevTrnSerial == maxNumber) {
            prevTrnSerial = 0;
        }
        return padLeft(Integer.toString(prevTrnSerial), serialLength, "0");
    }

    public static String formatToRupees(long amount) {
        long intgerPart = amount / 100;
        long fractionPart = amount % 100;
        return Long.toString(intgerPart) + "." + padRight(padLeft(Long.toString(Math.abs(fractionPart)), 2, "0"), 2, "0");
    }

    public static long rupeesToLong(String strAmount) {
        BigDecimal bigDecimal = (new BigDecimal(strAmount)).multiply(new BigDecimal(100));
        return bigDecimal.longValue();
    }

    public static String getJobStatus(String status) {
        String complete = null;
        if (status.equalsIgnoreCase("Complete")) {
            complete = "1";

        } else if (status.equalsIgnoreCase("Incomplete")) {
            complete = "0";
        }
        return complete;
    }

    public static String roundingToImmediatePlusPoint(long amount) {
        long intgerPart = amount / 100;
        long fractionPart = amount % 100;
        if (fractionPart < 50) {
            return Long.toString(intgerPart) + ".00";
        } else {
            intgerPart = intgerPart + 1;
            return Long.toString(intgerPart) + ".00";
        }
    }

    public static String firstLetterUpperCase(String word) {
        String st = Character.toString(word.charAt(0));
        String newWord = st.toUpperCase() + word.substring(1);

        return newWord;
    }
    
}
