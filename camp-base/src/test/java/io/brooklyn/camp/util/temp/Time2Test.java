package io.brooklyn.camp.util.temp;

import io.brooklyn.util.temp.Time2;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Time2Test {

    @Test
    public void testDateRounding() {
        long x = System.currentTimeMillis();
        Date d1 = Time2.dropMilliseconds(new Date(x));
        Date d2 = new Date(x - (x%1000));
        Date d3 = new Date( (x/1000)*1000 );
        Assert.assertEquals(d1.getTime() % 1000, 0);
        Assert.assertEquals(d1, d2);
        Assert.assertEquals(d1, d3);
    }

    @Test
    public void testDateRoundingNull() {
        Assert.assertNull(Time2.dropMilliseconds(null));
    }
    
}
