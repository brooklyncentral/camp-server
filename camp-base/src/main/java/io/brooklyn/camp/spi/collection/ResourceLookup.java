package io.brooklyn.camp.spi.collection;

import io.brooklyn.camp.spi.AbstractResource;

import java.util.List;

public interface ResourceLookup<T extends AbstractResource> {

    public abstract T get(String id);
    
    public abstract List<ResolveableLink<T>> links();

}
