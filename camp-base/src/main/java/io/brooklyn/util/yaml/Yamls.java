package io.brooklyn.util.yaml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Iterables;

public class Yamls {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T getAs(Object x, Class<T> type) {
        if (x==null) return null;
        if (x instanceof Iterable || x instanceof Iterator) {
            List result = new ArrayList();
            Iterator xi;
            if (Iterator.class.isAssignableFrom(x.getClass())) {
                xi = (Iterator)x;
            } else {
                xi = ((Iterable)x).iterator();
            }
            while (xi.hasNext()) {
                    result.add( xi.next() );
            }
            if (type.isAssignableFrom(Iterable.class)) return (T)result;
            if (type.isAssignableFrom(Iterator.class)) return (T)result.iterator();
            if (type.isAssignableFrom(List.class)) return (T)result;
            x = Iterables.getOnlyElement(result);
        }
        // TODO more coercion?
        return (T)x;
    }

    @SuppressWarnings("rawtypes")
    public static void dump(int depth, Object r) {
        if (r instanceof Iterable) {
            for (Object ri : ((Iterable)r))
                dump(depth+1, ri);
        } else if (r instanceof Map) {
            for (Object re: ((Map)r).entrySet()) {
                for (int i=0; i<depth; i++) System.out.print(" ");
                System.out.println(((Entry)re).getKey()+":");
                dump(depth+1, ((Entry)re).getValue());
            }
        } else {
            for (int i=0; i<depth; i++) System.out.print(" ");
            if (r==null) System.out.println("<null>");
            else System.out.println("<"+r.getClass().getSimpleName()+">"+" "+r);
        }
    }

    /** simplifies new Yaml().loadAll, and converts to list to prevent single-use iterable bug in yaml */
    @SuppressWarnings("unchecked")
    public static Iterable<Object> parseAll(String yaml) {
        Iterable<Object> result = new org.yaml.snakeyaml.Yaml().loadAll(yaml);
        return (List<Object>) getAs(result, List.class);
    }

}
