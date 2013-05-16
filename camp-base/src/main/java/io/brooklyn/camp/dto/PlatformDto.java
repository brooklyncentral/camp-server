package io.brooklyn.camp.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PlatformDto extends ResourceDto {

    public PlatformDto(String uri, String name, String description, Date created, List<String> tags, String type,
            String representationSkew, Map<String, ? extends Object> customAttributes) {
        super(uri, name, description, created, tags, type, representationSkew, customAttributes);
        // TODO Auto-generated constructor stub
    }

    // TODO...
    
}
