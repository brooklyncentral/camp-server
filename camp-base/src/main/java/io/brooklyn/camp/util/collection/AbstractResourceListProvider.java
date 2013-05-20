package io.brooklyn.camp.util.collection;

import io.brooklyn.camp.impl.BasicResource;

public abstract class AbstractResourceListProvider<T extends BasicResource> implements ResourceListProvider<T> {

    /** convenience for concrete subclasses */
    protected ResolveableLink<T> newLink(String id, String name) {
        return new ResolveableLink<T>(id, name, this);
    }
    
}
