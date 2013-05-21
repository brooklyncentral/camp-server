package io.brooklyn.camp;

import io.brooklyn.camp.impl.ApplicationComponentTemplate;
import io.brooklyn.camp.impl.PlatformComponentTemplate;
import io.brooklyn.camp.impl.PlatformRootSummary;
import io.brooklyn.camp.util.collection.AbstractResourceListProvider;

public abstract class CampPlatform {

    private PlatformRootSummary root;

    public CampPlatform() {
        root = initializeRoot();
    }

    // --- root
    
    /** default root creation; intended to be overridden */
    protected PlatformRootSummary initializeRoot() {
        return PlatformRootSummary.builder().
                name("CAMP Platform").
                build();
    }
    
    public PlatformRootSummary root() {
        return root;
    }

    // --- extension hooks
    
    public abstract AbstractResourceListProvider<PlatformComponentTemplate> platformComponentTemplates();
    public abstract AbstractResourceListProvider<ApplicationComponentTemplate> applicationComponentTemplates();
    

}
