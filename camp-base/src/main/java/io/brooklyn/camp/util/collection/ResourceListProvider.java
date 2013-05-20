package io.brooklyn.camp.util.collection;

import io.brooklyn.camp.impl.BasicResource;

import java.util.List;

public interface ResourceListProvider<T extends BasicResource> {

    public abstract T get(String id);
    
    public abstract List<ResolveableLink<T>> links();

}
