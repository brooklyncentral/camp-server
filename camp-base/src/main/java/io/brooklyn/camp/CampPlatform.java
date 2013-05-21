package io.brooklyn.camp;

import io.brooklyn.camp.impl.PlatformComponentTemplate;
import io.brooklyn.camp.impl.PlatformRootSummary;
import io.brooklyn.camp.util.collection.AbstractResourceListProvider;

public abstract class CampPlatform {

    public abstract AbstractResourceListProvider<PlatformComponentTemplate> platformComponentTemplates();
    
    private PlatformRootSummary root;

    public CampPlatform() {
        root = initializeRoot();
    }
    
    /** default root creation; intended to be overridden */
    protected PlatformRootSummary initializeRoot() {
        return PlatformRootSummary.builder().
                name("CAMP Platform").
                build();
    }
    
    public PlatformRootSummary root() {
        return root;
    }

}
