package com.globallogic.dashboard.email;

import java.util.Date;

public final class CheckDateUtil {
    public CheckDateUtil() {
    }
    public static boolean checkDateValidation(Date expDate){
        Date current = new Date();
        return current.compareTo(expDate) < 0;
    }
}
