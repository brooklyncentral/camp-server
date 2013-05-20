package io.brooklyn.camp.dto;

import io.brooklyn.camp.commontypes.RepresentationSkew;
import io.brooklyn.camp.impl.BasicResource;
import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.util.temp.Time2;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

public class ResourceDto extends DtoCustomAttributes {

    private String uri;
    private String type;
    
    private String name;
    private String description;
    private Date created;
    private List<String> tags;
    private RepresentationSkew representationSkew;

    public String getUri() {
        return uri;
    }
    
    public String getName() {
        return name;
    }
    
    @JsonInclude(Include.NON_NULL)
    public String getDescription() {
        return description;
    }
    
    @JsonGetter("created")
    public String getCreatedAsString() {
        return created==null ? null : ISO8601Utils.format(created);
    }
    
    @JsonSetter
    private void setCreated(Date created) {
        this.created = Time2.dropMilliseconds(created);
    }

    @JsonIgnore
    public Date getCreated() {
        return created;
    }
    
    @JsonInclude(Include.NON_EMPTY)
    public List<String> getTags() {
        return tags;
    }
    
    public String getType() {
        return type;
    }
    
    @JsonInclude(Include.NON_NULL)
    public RepresentationSkew getRepresentationSkew() {
        return representationSkew;
    }

    // --- building ---

    public static ResourceDto newInstance(DtoFactory dtoFactory, BasicResource x) {
        return new ResourceDto().newInstanceInitialization(dtoFactory, x);
    }
    
    protected ResourceDto newInstanceInitialization(DtoFactory dtoFactory, BasicResource x) {
        type = x.getType();
        name = x.getName();

        description = x.getDescription();
        setCreated(x.getCreated());
        tags = x.getTags();
        representationSkew = x.getRepresentationSkew();
        
        uri = dtoFactory.uri(x);
        return this;
    }

}
