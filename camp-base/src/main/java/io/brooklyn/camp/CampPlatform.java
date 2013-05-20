package io.brooklyn.camp;

import io.brooklyn.camp.impl.PlatformComponentTemplate;
import io.brooklyn.camp.impl.PlatformRootSummary;
import io.brooklyn.camp.util.collection.AbstractResourceListProvider;

public abstract class CampPlatform {

    public abstract AbstractResourceListProvider<PlatformComponentTemplate> platformComponentTemplates();

    public PlatformRootSummary root() {
        return PlatformRootSummary.builder().
                // TODO
                name("CAMP Platform").
                build();
    }
    
}
