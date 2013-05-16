package io.brooklyn.camp.util.collection;

import io.brooklyn.camp.impl.BasicResource;

import java.util.List;

public abstract class ResourceListProvider<T extends BasicResource<?>> {

    public abstract T get(String id);
    
    public abstract List<ResolveableLink<T>> links();

    /** convenience for concrete subclasses */
    protected ResolveableLink<T> newLink(String id, String name) {
        return new ResolveableLink<T>(id, name, this);
    }
    
}
