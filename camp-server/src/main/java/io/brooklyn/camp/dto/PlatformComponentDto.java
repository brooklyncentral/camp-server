package io.brooklyn.camp.dto;

import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.camp.spi.PlatformComponent;

public class PlatformComponentDto extends ResourceDto {

    // defined as a constant so can be used in Swagger REST API annotations
    public static final String CLASS_NAME = "io.brooklyn.camp.dto.PlatformComponentDto";
    static { assert CLASS_NAME.equals(PlatformComponentDto.class.getCanonicalName()); }
 
    protected PlatformComponentDto() {}
    protected PlatformComponentDto(DtoFactory dtoFactory, PlatformComponent x) {
        super(dtoFactory, x);
        setExternalManagementUri(x.getExternalManagementUri());
        // TODO set addl PCT fields
    }
 
    private String externalManagementUri;
    
    // TODO in time might refer to add'l platform components
    
    public String getExternalManagementUri() {
        return externalManagementUri;
    }
    private void setExternalManagementUri(String externalManagementUri) {
        this.externalManagementUri = externalManagementUri;
    }
    
    // --- building ---

    public static PlatformComponentDto newInstance(DtoFactory dtoFactory, PlatformComponent x) {
        return new PlatformComponentDto(dtoFactory, x);
    }
    
}
