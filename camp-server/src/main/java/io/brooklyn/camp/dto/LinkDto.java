package io.brooklyn.camp.dto;

import io.brooklyn.camp.impl.BasicResource;
import io.brooklyn.camp.impl.Link;
import io.brooklyn.camp.rest.util.DtoFactory;

import java.util.Map;

public class LinkDto extends DtoCustomAttributes {

    // defined as a constant so can be used in Swagger REST API annotations
    public static final String CLASS_NAME = "io.brooklyn.camp.dto.LinkDto";
    static { assert CLASS_NAME.equals(LinkDto.class.getCanonicalName()); }

    private String href;
    private String targetName;

    protected LinkDto() {}
    
    public String getHref() {
        return href;
    }
    
    public String getTargetName() {
        return targetName;
    }
    
    // --- building ---

    public static LinkDto newInstance(DtoFactory dtoFactory, Class<? extends BasicResource> targetType, Link<?> x) {
        return new LinkDto().newInstanceInitialization(dtoFactory, targetType, x);
    }
    
    protected LinkDto newInstanceInitialization(DtoFactory dtoFactory, Class<? extends BasicResource> targetType, Link<?> x) {
        targetName = x.getName();
        
        href = dtoFactory.uri(targetType, x.getId());
        return this;
    }

    public static LinkDto newInstance(String href, String targetName) {
        LinkDto x = new LinkDto();
        x.href = href;
        x.targetName = targetName;
        return x;
    }
    
    public static LinkDto newInstance(String href, String targetName, Map<String,? extends Object> customAttributes) {
        LinkDto x = newInstance(href, targetName);
        x.newInstanceCustomAttributes(customAttributes);
        return x;
    }
    
}
