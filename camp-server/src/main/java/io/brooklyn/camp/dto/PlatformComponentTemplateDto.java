package io.brooklyn.camp.dto;

import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.camp.spi.PlatformComponentTemplate;

public class PlatformComponentTemplateDto extends ResourceDto {

    // defined as a constant so can be used in Swagger REST API annotations
    public static final String CLASS_NAME = "io.brooklyn.camp.dto.PlatformComponentTemplateDto";
    static { assert CLASS_NAME.equals(PlatformComponentTemplateDto.class.getCanonicalName()); }
 
    // TODO add custom fields

    protected PlatformComponentTemplateDto() {}
    protected PlatformComponentTemplateDto(DtoFactory dtoFactory, PlatformComponentTemplate template) {
        super(dtoFactory, template);
        // TODO set custom fields
    }
 
    // --- building ---

    public static PlatformComponentTemplateDto newInstance(DtoFactory dtoFactory, PlatformComponentTemplate template) {
        return new PlatformComponentTemplateDto(dtoFactory, template);
    }
    
}
