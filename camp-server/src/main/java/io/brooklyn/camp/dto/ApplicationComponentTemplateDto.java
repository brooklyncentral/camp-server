package io.brooklyn.camp.dto;

import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.camp.spi.ApplicationComponentTemplate;

public class ApplicationComponentTemplateDto extends ResourceDto {

    // defined as a constant so can be used in Swagger REST API annotations
    public static final String CLASS_NAME = "io.brooklyn.camp.dto.ApplicationComponentTemplateDto";
    static { assert CLASS_NAME.equals(ApplicationComponentTemplateDto.class.getCanonicalName()); }

    // TODO add custom fields

    protected ApplicationComponentTemplateDto() {}
    protected ApplicationComponentTemplateDto(DtoFactory dtoFactory, ApplicationComponentTemplate template) {
        super(dtoFactory, template);
        // TODO set custom fields
    }

    // --- building ---

    public static ApplicationComponentTemplateDto newInstance(DtoFactory dtoFactory, ApplicationComponentTemplate template) {
        return new ApplicationComponentTemplateDto(dtoFactory, template);
    }
    
}
