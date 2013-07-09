package io.brooklyn.camp.dto;

import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.camp.spi.ApplicationComponent;

public class ApplicationComponentDto extends ResourceDto {

    // defined as a constant so can be used in Swagger REST API annotations
    public static final String CLASS_NAME = "io.brooklyn.camp.dto.ApplicationComponentDto";
    static { assert CLASS_NAME.equals(ApplicationComponentDto.class.getCanonicalName()); }

    protected ApplicationComponentDto() {}
    protected ApplicationComponentDto(DtoFactory dtoFactory, ApplicationComponent x) {
        super(dtoFactory, x);
        // TODO set addl ACT fields
    }
 
    // TODO add addl ACT fields
    
    // --- building ---

    public static ApplicationComponentDto newInstance(DtoFactory dtoFactory, ApplicationComponent x) {
        return new ApplicationComponentDto(dtoFactory, x);
    }
    
}
