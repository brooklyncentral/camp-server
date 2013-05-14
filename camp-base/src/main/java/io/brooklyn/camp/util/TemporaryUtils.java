package io.brooklyn.camp.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/** utility routines, to move to brooklyn-utils */
public class TemporaryUtils {

    public static <K,V> ImmutableMap<K,V> immutable(Map<K,V> map) {
        if (map==null) return null;
        if (map instanceof ImmutableMap) return (ImmutableMap<K,V>) map;
        return ImmutableMap.copyOf(map);
    }

    public static <T> ImmutableList<T> immutable(List<T> list) {
        if (list==null) return null;
        if (list instanceof ImmutableList) return (ImmutableList<T>) list;
        return ImmutableList.copyOf(list);
    }

    public static <T> ImmutableSet<T> immutable(Set<T> set) {
        if (set==null) return null;
        if (set instanceof ImmutableSet) return (ImmutableSet<T>) set;
        return ImmutableSet.copyOf(set);
    }

}
