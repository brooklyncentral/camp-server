package io.brooklyn.camp;

import com.google.common.base.Preconditions;

import io.brooklyn.camp.spi.ApplicationComponentTemplate;
import io.brooklyn.camp.spi.PlatformComponentTemplate;
import io.brooklyn.camp.spi.PlatformRootSummary;
import io.brooklyn.camp.spi.collection.ResourceLookup;

public abstract class CampPlatform {

    private final PlatformRootSummary root;

    public CampPlatform(PlatformRootSummary root) {
        this.root = Preconditions.checkNotNull(root, "root");
    }

    // --- root
    
    public PlatformRootSummary root() {
        return root;
    }

    // --- extension hooks
    
    public abstract ResourceLookup<PlatformComponentTemplate> platformComponentTemplates();
    public abstract ResourceLookup<ApplicationComponentTemplate> applicationComponentTemplates();
    

}
