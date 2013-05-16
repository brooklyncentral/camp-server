package io.brooklyn.camp.dto;

import io.brooklyn.camp.util.TemporaryUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

public class ResourceDto extends DtoCustomAttributes {

    private final String uri;
    private final String name;
    private final String description;
    private final Date created;
    private final List<String> tags;
    private final String type;
    private final String representationSkew;

    @JsonCreator
    public ResourceDto(
            @JsonProperty("uri") String uri, 
            @JsonProperty("name") String name, 
            @JsonProperty("description") String description, 
            @JsonProperty("created") Date created, 
            @JsonProperty("tags") List<String> tags, 
            @JsonProperty("type") String type, 
            @JsonProperty("representationSkew") String representationSkew, 
            @JsonProperty("_") Map<String, ? extends Object> customAttributes) {
        super(customAttributes);

        this.uri = uri;
        this.name = name;
        this.description = description;
        // precision beyond seconds breaks equals check
        this.created = created==null ? null : created.getTime()%1000!=0 ? new Date(1000*(created.getTime()/1000)) : created;
        this.tags = TemporaryUtils.immutable(tags);
        this.type = type;
        this.representationSkew = representationSkew;
    }

    public String getUri() {
        return uri;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    @JsonProperty("created")
    public String getCreatedAsString() {
        return created==null ? null : ISO8601Utils.format(created);
    }
    
    @JsonIgnore
    public Date getCreated() {
        return created;
    }
    
    public List<String> getTags() {
        return tags;
    }
    
    public String getType() {
        return type;
    }
    
    public String getRepresentationSkew() {
        return representationSkew;
    }
    
}
