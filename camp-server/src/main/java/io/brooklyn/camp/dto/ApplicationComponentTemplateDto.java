package io.brooklyn.camp.dto;

import io.brooklyn.camp.impl.ApplicationComponentTemplate;
import io.brooklyn.camp.rest.util.DtoFactory;

public class ApplicationComponentTemplateDto extends ResourceDto {

    // defined as a constant so can be used in Swagger REST API annotations
    public static final String CLASS_NAME = "io.brooklyn.camp.dto.ApplicationComponentTemplateDto";
    static { assert CLASS_NAME.equals(ApplicationComponentTemplateDto.class.getCanonicalName()); }

    // TODO add custom fields
    
    protected ApplicationComponentTemplateDto() {}
    
    // --- building ---

    public static ApplicationComponentTemplateDto newInstance(DtoFactory dtoFactory, ApplicationComponentTemplate x) {
        return new ApplicationComponentTemplateDto().newInstanceInitialization(dtoFactory, x);
    }

    protected ApplicationComponentTemplateDto newInstanceInitialization(DtoFactory dtoFactory, ApplicationComponentTemplate x) {
        super.newInstanceInitialization(dtoFactory, x);
        // TODO add custom fields
        return this;
    }
    
}
