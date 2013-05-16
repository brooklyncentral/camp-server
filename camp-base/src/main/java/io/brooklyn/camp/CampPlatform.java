package io.brooklyn.camp;

import io.brooklyn.camp.impl.PlatformComponentTemplate;
import io.brooklyn.camp.impl.PlatformRootResource;
import io.brooklyn.camp.util.collection.ResourceListProvider;

public abstract class CampPlatform {

    public abstract ResourceListProvider<PlatformComponentTemplate> platformComponentTemplates();

    public PlatformRootResource root() {
        return new PlatformRootResource(null);
    }
    
}
