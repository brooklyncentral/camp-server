package io.brooklyn.camp.dto;

import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.camp.spi.AbstractResource;
import io.brooklyn.camp.spi.Link;

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

    public static LinkDto newInstance(DtoFactory dtoFactory, Class<? extends AbstractResource> targetType, Link<?> link) {
        return new LinkDto().newInstanceInitialization(dtoFactory, targetType, link);
    }
    
    protected LinkDto newInstanceInitialization(DtoFactory dtoFactory, Class<? extends AbstractResource> targetType, Link<?> link) {
        targetName = link.getName();
        
        href = dtoFactory.uri(targetType, link.getId());
        return this;
    }

    public static LinkDto newInstance(String href, String targetName) {
        LinkDto link = new LinkDto();
        link.href = href;
        link.targetName = targetName;
        return link;
    }
    
    public static LinkDto newInstance(String href, String targetName, Map<String, ?> customAttributes) {
        LinkDto link = newInstance(href, targetName);
        link.newInstanceCustomAttributes(customAttributes);
        return link;
    }
    
}
