package io.brooklyn.camp.impl;


/** Holds the metadata (name, description, etc) for a PCT
 * as well as fields pointing to behaviour (eg creation of PlatformComponent).
 * <p>
 * See {@link BasicResource} for more general information.
 */
public class PlatformComponentTemplate extends BasicResource {

    public static final String CAMP_TYPE = "PlatformComponentTemplate";
    
    /** Use {@link #builder()} to create */
    protected PlatformComponentTemplate() {}

    
    // no fields beyond basic resource
    
    
    // builder
    
    public static Builder<? extends PlatformComponentTemplate> builder() {
        return new Builder<PlatformComponentTemplate>(CAMP_TYPE);
    }
    
    public static class Builder<T extends PlatformComponentTemplate> extends BasicResource.Builder<T> {
        
        protected Builder(String type) { super(type); }
        
        @SuppressWarnings("unchecked")
        protected T createResource() { return (T) new PlatformComponentTemplate(); }
        
//        public Builder<T> foo(String x) { instance().setFoo(x); return this; }
    }

}
