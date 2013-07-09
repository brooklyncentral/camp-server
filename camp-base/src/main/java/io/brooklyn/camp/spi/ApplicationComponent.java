package io.brooklyn.camp.spi;


/** Holds the metadata (name, description, etc) for a PCT
 * as well as fields pointing to behaviour (eg creation of PlatformComponent).
 * <p>
 * See {@link AbstractResource} for more general information.
 */
public class ApplicationComponent extends AbstractResource {

    public static final String CAMP_TYPE = "ApplicationComponent";
    static { assert CAMP_TYPE.equals(ApplicationComponent.class.getSimpleName()); }
    
    /** Use {@link #builder()} to create */
    protected ApplicationComponent() {}

    
    // no fields beyond basic resource
    // TODO in time might refer to add'l platform components
    
    
    // builder
    
    public static Builder<? extends ApplicationComponent> builder() {
        return new Builder<ApplicationComponent>(CAMP_TYPE);
    }
    
    public static class Builder<T extends ApplicationComponent> extends AbstractResource.Builder<T,Builder<T>> {
        
        protected Builder(String type) { super(type); }
        
        @SuppressWarnings("unchecked")
        protected T createResource() { return (T) new ApplicationComponent(); }
    }

}
