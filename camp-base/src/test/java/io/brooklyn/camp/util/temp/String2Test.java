package io.brooklyn.camp.util.temp;

import io.brooklyn.util.temp.Strings2;
import io.brooklyn.util.temp.Strings2.FormattedString;

import org.testng.Assert;
import org.testng.annotations.Test;

public class String2Test {

    @Test
    public void testDeferredFormat() {
        ToStringCounter c = new ToStringCounter();
        FormattedString x = Strings2.format("hello %s", c);
        Assert.assertEquals(c.count, 0);
        Assert.assertEquals(x.toString(), "hello world");
        Assert.assertEquals(c.count, 1);
    }

    private static class ToStringCounter {
        private int count = 0;
        @Override
        public String toString() {
            count++;
            return "world";
        }
    }
    
}
