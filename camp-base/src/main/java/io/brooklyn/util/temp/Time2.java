package io.brooklyn.util.temp;

import java.util.Date;

/** utility routines, to move to brooklyn-utils */
public class Time2 {

    /** removes milliseconds from the date object; needed if serializing to ISO-8601 format 
     * and want to serialize back and get the same data */
    public static Date dropMilliseconds(Date date) {
        return date==null ? null : date.getTime()%1000!=0 ? new Date(date.getTime() - (date.getTime()%1000)) : date;
    }
    
}
