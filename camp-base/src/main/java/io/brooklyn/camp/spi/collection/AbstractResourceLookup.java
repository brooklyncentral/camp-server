package io.brooklyn.camp.spi.collection;

import io.brooklyn.camp.spi.AbstractResource;

public abstract class AbstractResourceLookup<T extends AbstractResource> implements ResourceLookup<T> {

    /** convenience for concrete subclasses */
    protected ResolveableLink<T> newLink(String id, String name) {
        return new ResolveableLink<T>(id, name, this);
    }
    
}
