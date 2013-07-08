package io.brooklyn.camp;

import io.brooklyn.camp.spi.ApplicationComponentTemplate;
import io.brooklyn.camp.spi.AssemblyTemplate;
import io.brooklyn.camp.spi.PlatformComponentTemplate;
import io.brooklyn.camp.spi.PlatformRootSummary;
import io.brooklyn.camp.spi.collection.BasicResourceLookup;

public class BasicCampPlatform extends CampPlatform {

    public BasicCampPlatform() {
        this(PlatformRootSummary.builder().name("CAMP Platform").build());
    }
    
    public BasicCampPlatform(PlatformRootSummary root) {
        super(root);
    }

    BasicResourceLookup<PlatformComponentTemplate> platformComponentTemplates = new BasicResourceLookup<PlatformComponentTemplate>();
    BasicResourceLookup<ApplicationComponentTemplate> applicationComponentTemplates = new BasicResourceLookup<ApplicationComponentTemplate>();
    BasicResourceLookup<AssemblyTemplate> assemblyTemplates = new BasicResourceLookup<AssemblyTemplate>();
    
    public BasicResourceLookup<PlatformComponentTemplate> platformComponentTemplates() {
        return platformComponentTemplates;
    }

    @Override
    public BasicResourceLookup<ApplicationComponentTemplate> applicationComponentTemplates() {
        return applicationComponentTemplates;
    }

    public BasicResourceLookup<AssemblyTemplate> assemblyTemplates() {
        return assemblyTemplates;
    }
    
}
