package io.brooklyn.camp.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;

public class LinkDto extends DtoCustomAttributes {

    private final String href;
    private final String targetName;

    @JsonCreator
    public LinkDto(
            @JsonProperty("href") String href, 
            @JsonProperty("targetName") String targetName,
            @JsonProperty("_") Map<String, ? extends Object> customAttributes) {
        super(customAttributes);
        
        Preconditions.checkNotNull(href, "href");
        Preconditions.checkNotNull(targetName, "targetName");
        this.href = href;
        this.targetName = targetName;
    }

    public String getHref() {
        return href;
    }
    
    public String getTargetName() {
        return targetName;
    }

}
