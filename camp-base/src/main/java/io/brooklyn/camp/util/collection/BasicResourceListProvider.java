package io.brooklyn.camp.util.collection;

import io.brooklyn.camp.impl.BasicResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import brooklyn.util.collections.MutableMap;

public class BasicResourceListProvider<T extends BasicResource> extends AbstractResourceListProvider<T> {

    Map<String,T> items = new MutableMap<String,T>();
    Map<String,ResolveableLink<T>> links = new MutableMap<String,ResolveableLink<T>>();
    
    public T get(String id) {
        return items.get(id);
    }

    public synchronized List<ResolveableLink<T>> links() {
        return new ArrayList<ResolveableLink<T>>(links.values());
    }

    public synchronized void add(T item) {
        T old = items.put(item.getId(), item);
        if (old!=null) {
            items.put(old.getId(), old);
            throw new IllegalStateException("Already contains item for "+item.getId()+": "+old+" (adding "+item+")");
        }
        links.put(item.getId(), newLink(item.getId(), item.getName()));
    }
    
    public synchronized T update(T item) {
        T old = items.put(item.getId(), item);
        links.put(item.getId(), newLink(item.getId(), item.getName()));
        return old;
    }
    
    public synchronized boolean remove(String id) {
        items.remove(id);
        return links.remove(id)!=null;
    }
    
}
