package com.globallogic.dashboard.common;

import java.util.Date;

public class VacationException extends RuntimeException{
    public VacationException(String message, Date date, Date start){super(message);}
    public VacationException(String message,Throwable couse){super(message,couse);}
}
