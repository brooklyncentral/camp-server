package io.brooklyn.camp.spi;

import com.google.common.base.Preconditions;

import io.brooklyn.camp.spi.collection.BasicResourceLookup;
import io.brooklyn.camp.spi.collection.ResourceLookup;
import io.brooklyn.camp.spi.collection.ResourceLookup.EmptyResourceLookup;
import io.brooklyn.camp.spi.instantiate.AssemblyTemplateInstantiator;


/** Holds the metadata (name, description, etc) for an AssemblyTemplate
 * as well as fields pointing to behaviour (eg list of ACT's).
 * <p>
 * See {@link AbstractResource} for more general information.
 */
public class AssemblyTemplate extends AbstractResource {

    public static final String CAMP_TYPE = "AssemblyTemplate";
    static { assert CAMP_TYPE.equals(AssemblyTemplate.class.getSimpleName()); }
    
    Class<? extends AssemblyTemplateInstantiator> instantiator;
    ResourceLookup<ApplicationComponentTemplate> applicationComponentTemplates;
    ResourceLookup<PlatformComponentTemplate> platformComponentTemplates;
    
    // TODO
//    "parameterDefinitionUri": URI,
//    "pdpUri" : URI ?
                    
    /** Use {@link #builder()} to create */
    protected AssemblyTemplate() {}

    public Class<? extends AssemblyTemplateInstantiator> getInstantiator() {
        return instantiator;
    }
    public ResourceLookup<ApplicationComponentTemplate> getApplicationComponentTemplates() {
        return applicationComponentTemplates != null ? applicationComponentTemplates : new EmptyResourceLookup<ApplicationComponentTemplate>();
    }
    public ResourceLookup<PlatformComponentTemplate> getPlatformComponentTemplates() {
        return platformComponentTemplates != null ? platformComponentTemplates : new EmptyResourceLookup<PlatformComponentTemplate>();
    }
    
    private void setInstantiator(Class<? extends AssemblyTemplateInstantiator> instantiator) {
        this.instantiator = instantiator;
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
        
        public Builder<T> instantiator(Class<? extends AssemblyTemplateInstantiator> x) { instance().setInstantiator(x); return thisBuilder(); }
        public Builder<T> applicationComponentTemplates(ResourceLookup<ApplicationComponentTemplate> x) { instance().setApplicationComponentTemplates(x); return thisBuilder(); }
        public Builder<T> platformComponentTemplates(ResourceLookup<PlatformComponentTemplate> x) { instance().setPlatformComponentTemplates(x); return thisBuilder(); }
        
        public synchronized Builder<T> add(ApplicationComponentTemplate x) {
            if (instance().applicationComponentTemplates==null) {
                instance().applicationComponentTemplates = new BasicResourceLookup<ApplicationComponentTemplate>();
            }
            if (!(instance().applicationComponentTemplates instanceof BasicResourceLookup)) {
                throw new IllegalStateException("Cannot add to resource lookup "+instance().applicationComponentTemplates);
            }
            ((BasicResourceLookup<ApplicationComponentTemplate>)instance().applicationComponentTemplates).add(x);
            return thisBuilder();
        }
        
        public synchronized Builder<T> add(PlatformComponentTemplate x) {
            if (instance().platformComponentTemplates==null) {
                instance().platformComponentTemplates = new BasicResourceLookup<PlatformComponentTemplate>();
            }
            if (!(instance().platformComponentTemplates instanceof BasicResourceLookup)) {
                throw new IllegalStateException("Cannot add to resource lookup "+instance().platformComponentTemplates);
            }
            ((BasicResourceLookup<PlatformComponentTemplate>)instance().platformComponentTemplates).add(x);
            return thisBuilder();
        }
        
        @Override
        public synchronized T build() {
            Preconditions.checkNotNull(instance().instantiator);
            return super.build();
        }
    }

}
