package io.brooklyn.camp.dto;

import io.brooklyn.camp.util.TemporaryUtils;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.common.base.Preconditions;

public class LinkDto {

    public final String href;
    public final String targetName;
    
//    @JsonSerialize(include=Inclusion.NON_NULL)
    @JsonUnwrapped
//    @JsonInclude(Include.NON_EMPTY)
    public final Map<String,? extends Object> customAttributes;
    
    @JsonCreator
    public LinkDto(
            @JsonProperty("href") String href, 
            @JsonProperty("targetName") String targetName, 
            @JsonProperty("customAttributes") Map<String, ? extends Object> customAttributes) {
        Preconditions.checkNotNull(href, "href");
        Preconditions.checkNotNull(targetName, "targetName");
        this.href = href;
        this.targetName = targetName;
        this.customAttributes = TemporaryUtils.immutable(customAttributes);
    }

    @Override
    public boolean equals(Object obj) {
        // FIXME
//        return HashCodeBuilder.XXX();
        return ((LinkDto)obj).href.equals(href);
    }
}
