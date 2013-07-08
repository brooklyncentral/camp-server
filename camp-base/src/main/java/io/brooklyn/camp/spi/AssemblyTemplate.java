package io.brooklyn.camp.spi;

import io.brooklyn.camp.spi.collection.ResourceLookup;


/** Holds the metadata (name, description, etc) for an AssemblyTemplate
 * as well as fields pointing to behaviour (eg list of ACT's).
 * <p>
 * See {@link AbstractResource} for more general information.
 */
public class AssemblyTemplate extends AbstractResource {

    public static final String CAMP_TYPE = "AssemblyTemplate";
    static { assert CAMP_TYPE.equals(AssemblyTemplate.class.getSimpleName()); }
    
    ResourceLookup<ApplicationComponentTemplate> applicationComponentTemplates;
    ResourceLookup<PlatformComponentTemplate> platformComponentTemplates;
    
    // TODO
//    "parameterDefinitionUri": URI,
//    "pdpUri" : URI ?
                    
    /** Use {@link #builder()} to create */
    protected AssemblyTemplate() {}

    public ResourceLookup<ApplicationComponentTemplate> getApplicationComponentTemplates() {
        return applicationComponentTemplates;
    }
    public ResourceLookup<PlatformComponentTemplate> getPlatformComponentTemplates() {
        return platformComponentTemplates;
    }
    
    private void setApplicationComponentTemplates(ResourceLookup<ApplicationComponentTemplate> applicationComponentTemplates) {
        this.applicationComponentTemplates = applicationComponentTemplates;
    }
    private void setPlatformComponentTemplates(ResourceLookup<PlatformComponentTemplate> platformComponentTemplates) {
        this.platformComponentTemplates = platformComponentTemplates;
    }
    
    // builder
    
    public static Builder<? extends AssemblyTemplate> builder() {
        return new Builder<AssemblyTemplate>(CAMP_TYPE);
    }
    
    public static class Builder<T extends AssemblyTemplate> extends AbstractResource.Builder<T,Builder<T>> {
        
        protected Builder(String type) { super(type); }
        
        @SuppressWarnings("unchecked")
        protected T createResource() { return (T) new AssemblyTemplate(); }
        
        public Builder<T> applicationComponentTemplates(ResourceLookup<ApplicationComponentTemplate> x) { instance().setApplicationComponentTemplates(x); return thisBuilder(); }
        public Builder<T> platformComponentTemplates(ResourceLookup<PlatformComponentTemplate> x) { instance().setPlatformComponentTemplates(x); return thisBuilder(); }
    }

}
