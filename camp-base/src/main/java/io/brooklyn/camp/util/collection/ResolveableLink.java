package io.brooklyn.camp.util.collection;

import io.brooklyn.camp.impl.BasicResource;
import io.brooklyn.camp.impl.Link;

public class ResolveableLink<T extends BasicResource> extends Link<T> {
    
    protected final AbstractResourceListProvider<T> provider;
    
    public ResolveableLink(String id, String name, AbstractResourceListProvider<T> provider) {
        super(id, name);
        this.provider = provider;
    }

    public T resolve() {
        return provider.get(getId());
    }

}
