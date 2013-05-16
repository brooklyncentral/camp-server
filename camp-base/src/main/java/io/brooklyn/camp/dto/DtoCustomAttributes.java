package io.brooklyn.camp.dto;

import io.brooklyn.camp.util.TemporaryUtils;

import java.util.Map;

import brooklyn.util.collections.MutableMap;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DtoCustomAttributes extends DtoBase {

    private final Map<String,Object> customAttributes;

    protected DtoCustomAttributes() {
        this.customAttributes = new MutableMap<String, Object>();
    }
    
    @JsonCreator
    public DtoCustomAttributes(@JsonProperty("_") Map<String, ? extends Object> customAttributes) {
        // 'null' value treated as mutable map, set using methods below, as workaround for
        // https://github.com/FasterXML/jackson-databind/issues/171
        this.customAttributes = customAttributes==null ? new MutableMap<String, Object>() :
            TemporaryUtils.immutable(customAttributes);
    }

    @JsonIgnore
    public Map<String, Object> getCustomAttributes() {
        return customAttributes;
    }
    
    @JsonAnyGetter
    private Map<String,Object> any() {
        return customAttributes;
    }
    @JsonAnySetter
    private void set(String name, Object value) {
        customAttributes.put(name, value);
    }
    
}
