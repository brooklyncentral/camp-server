package io.brooklyn.camp.spi.pdp;

import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.spi.ApplicationComponentTemplate;
import io.brooklyn.camp.spi.AssemblyTemplate;
import io.brooklyn.camp.spi.AssemblyTemplate.Builder;
import io.brooklyn.camp.spi.PlatformTransaction;

public class AssemblyTemplateConstructor {

    private final Builder<? extends AssemblyTemplate> builder;
    private final CampPlatform campPlatform;
    protected PlatformTransaction transaction;

    public AssemblyTemplateConstructor(CampPlatform campPlatform) {
        this.campPlatform = campPlatform;
        this.builder = AssemblyTemplate.builder();
        this.transaction = this.campPlatform.transaction();
    }
    
    /** records all the templates to the underlying platform */
    public AssemblyTemplate commit() {
        AssemblyTemplate at = builder.build();
        transaction.add(at).commit();
        transaction = null;
        return at;
    }

    public void name(String name) {
        builder.name(name);
    }

    public void description(String description) {
        builder.description(description);
    }

    public void add(ApplicationComponentTemplate act) {
        transaction.add(act);
    }
    
}
