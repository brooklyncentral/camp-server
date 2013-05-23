package io.brooklyn.camp.spi.collection;

import io.brooklyn.camp.spi.AbstractResource;
import io.brooklyn.camp.spi.Link;

public class ResolveableLink<T extends AbstractResource> extends Link<T> {
    
    protected final AbstractResourceLookup<T> provider;
    
    public ResolveableLink(String id, String name, AbstractResourceLookup<T> provider) {
        super(id, name);
        this.provider = provider;
    }

    public T resolve() {
        return provider.get(getId());
    }

}
