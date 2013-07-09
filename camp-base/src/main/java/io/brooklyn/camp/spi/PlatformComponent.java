package io.brooklyn.camp.spi;


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

    String externalManagementUri;
    
    // TODO in time might refer to add'l platform components
    
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
        
        @SuppressWarnings("unchecked")
        protected T createResource() { return (T) new PlatformComponent(); }
    }

}
