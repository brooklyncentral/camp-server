package io.brooklyn.camp.spi;

import io.brooklyn.camp.spi.collection.BasicResourceLookup;
import io.brooklyn.camp.spi.collection.ResourceLookup;
import io.brooklyn.camp.spi.collection.ResourceLookup.EmptyResourceLookup;


/** Holds the metadata (name, description, etc) for a PCT
 * as well as fields pointing to behaviour (eg creation of PlatformComponent).
 * <p>
 * See {@link AbstractResource} for more general information.
 */
public class PlatformComponent extends AbstractResource {

    public static final String CAMP_TYPE = "PlatformComponent";
    static { assert CAMP_TYPE.equals(PlatformComponent.class.getSimpleName()); }
    
    /** Use {@link #builder()} to create */
    protected PlatformComponent() {}

    ResourceLookup<ApplicationComponent> applicationComponents;
    ResourceLookup<PlatformComponent> platformComponents;
    String externalManagementUri;
    
    public ResourceLookup<ApplicationComponent> getApplicationComponents() {
        return applicationComponents != null ? applicationComponents : new EmptyResourceLookup<ApplicationComponent>();
    }
    public ResourceLookup<PlatformComponent> getPlatformComponents() {
        return platformComponents != null ? platformComponents : new EmptyResourceLookup<PlatformComponent>();
    }

    private void setApplicationComponents(ResourceLookup<ApplicationComponent> applicationComponents) {
        this.applicationComponents = applicationComponents;
    }
    private void setPlatformComponents(ResourceLookup<PlatformComponent> platformComponents) {
        this.platformComponents = platformComponents;
    }
    
    public String getExternalManagementUri() {
        return externalManagementUri;
    }
    private void setExternalManagementUri(String externalManagementUri) {
        this.externalManagementUri = externalManagementUri;
    }
    
    // builder
    
    public static Builder<? extends PlatformComponent> builder() {
        return new Builder<PlatformComponent>(CAMP_TYPE);
    }
    
    public static class Builder<T extends PlatformComponent> extends AbstractResource.Builder<T,Builder<T>> {
        
        protected Builder(String type) { super(type); }
        
        public Builder<T> externalManagementUri(String x) { instance().setExternalManagementUri(x); return thisBuilder(); }
        public Builder<T> applicationComponentTemplates(ResourceLookup<ApplicationComponent> x) { instance().setApplicationComponents(x); return thisBuilder(); }
        public Builder<T> platformComponentTemplates(ResourceLookup<PlatformComponent> x) { instance().setPlatformComponents(x); return thisBuilder(); }
        
        public synchronized Builder<T> add(ApplicationComponent x) {
            if (instance().applicationComponents==null) {
                instance().applicationComponents = new BasicResourceLookup<ApplicationComponent>();
            }
            if (!(instance().applicationComponents instanceof BasicResourceLookup)) {
                throw new IllegalStateException("Cannot add to resource lookup "+instance().applicationComponents);
            }
            ((BasicResourceLookup<ApplicationComponent>)instance().applicationComponents).add(x);
            return thisBuilder();
        }
        
        public synchronized Builder<T> add(PlatformComponent x) {
            if (instance().platformComponents==null) {
                instance().platformComponents = new BasicResourceLookup<PlatformComponent>();
            }
            if (!(instance().platformComponents instanceof BasicResourceLookup)) {
                throw new IllegalStateException("Cannot add to resource lookup "+instance().platformComponents);
            }
            ((BasicResourceLookup<PlatformComponent>)instance().platformComponents).add(x);
            return thisBuilder();
        }

        @SuppressWarnings("unchecked")
        protected T createResource() { return (T) new PlatformComponent(); }
    }

}
