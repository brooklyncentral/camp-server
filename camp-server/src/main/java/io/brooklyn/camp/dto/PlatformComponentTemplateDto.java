package io.brooklyn.camp.dto;

import io.brooklyn.camp.impl.PlatformComponentTemplate;
import io.brooklyn.camp.rest.util.DtoFactory;

public class PlatformComponentTemplateDto extends ResourceDto {

    // TODO add custom fields
    
    protected PlatformComponentTemplateDto() {}
    
    // --- building ---

    public static PlatformComponentTemplateDto newInstance(DtoFactory dtoFactory, PlatformComponentTemplate x) {
        return new PlatformComponentTemplateDto().newInstanceInitialization(dtoFactory, x);
    }

    protected PlatformComponentTemplateDto newInstanceInitialization(DtoFactory dtoFactory, PlatformComponentTemplate x) {
        super.newInstanceInitialization(dtoFactory, x);
        // TODO add custom fields
        return this;
    }
    
}
