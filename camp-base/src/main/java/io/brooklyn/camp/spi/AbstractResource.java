package io.brooklyn.camp.spi;

import io.brooklyn.camp.commontypes.RepresentationSkew;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import brooklyn.util.text.Identifiers;
import brooklyn.util.time.Time;

import com.google.common.collect.ImmutableList;

/** Superclass of CAMP resource implementation objects.
 * Typically used to hold common state of implementation objects
 * and to populate the DTO's used by the REST API.
 * <p>
 * These class instances are typically created using the 
 * static {@link #builder()} methods they contain. 
 * The resulting instances are typically immutable,
 * so where fields can change callers should use a new builder
 * (or update an underlying data store).
 */
public class AbstractResource {

    public static final String CAMP_TYPE = "Resource";
    
    private String id = Identifiers.makeRandomId(8);
    private String name;
    private String type;
    private String description;
    private Date created = Time.dropMilliseconds(new Date());
    private List<String> tags = Collections.emptyList();
    private RepresentationSkew representationSkew;
    
    /** Use {@link #builder()} to create */
    protected AbstractResource() {}
    
    // getters

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public Date getCreated() {
        return created;
    }
    public List<String> getTags() {
        return tags;
    }
    public RepresentationSkew getRepresentationSkew() {
        return representationSkew;
    }
    
    // setters

    private void setId(String id) {
        this.id = id;
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setDescription(String description) {
        this.description = description;
    }
    private void setCreated(Date created) {
        // precision beyond seconds breaks equals check
        this.created = Time.dropMilliseconds(created);
    }
    private void setTags(List<String> tags) {
        this.tags = ImmutableList.copyOf(tags);
    }
    private void setType(String type) {
        this.type = type;
    }
    private void setRepresentationSkew(RepresentationSkew representationSkew) {
        this.representationSkew = representationSkew;
    }
            
    // builder
    
    public static Builder<? extends AbstractResource> builder() {
        return new Builder<AbstractResource>(CAMP_TYPE);
    }
    
    /** Builder creates the instance up front to avoid repetition of fields in the builder;
     * but prevents object leakage until build and prevents changes after build,
     * so effectively immutable.
     * <p>
     * Similarly setters in the class are private so those objects are also typically effectively immutable. */
    public static class Builder<T extends AbstractResource> {
        
        private boolean built = false;
        private String type = null;
        private T instance = null;
        
        protected Builder(String type) {
            this.type = type;
        }
        
        @SuppressWarnings("unchecked")
        protected T createResource() {
            return (T) new AbstractResource();
        }
        
        protected synchronized T instance() {
            if (built) 
                throw new IllegalStateException("Builder instance from "+this+" cannot be access after build");
            if (instance==null) {
                instance = createResource();
                initialize();
            }
            return instance;
        }

        protected void initialize() {
            if (type!=null) type(type);
        }
        
        public synchronized T build() {
            T result = instance();
            built = true;
            return result;
        }
        
        public Builder<T> type(String x) { instance().setType(x); return this; }
        public Builder<T> id(String x) { instance().setId(x); return this; }
        public Builder<T> name(String x) { instance().setName(x); return this; }
        public Builder<T> description(String x) { instance().setDescription(x); return this; }
        public Builder<T> created(Date x) { instance().setCreated(x); return this; }
        public Builder<T> tags(List<String> x) { instance().setTags(x); return this; }
        public Builder<T> representationSkew(RepresentationSkew x) { instance().setRepresentationSkew(x); return this; }
    }
    
}
