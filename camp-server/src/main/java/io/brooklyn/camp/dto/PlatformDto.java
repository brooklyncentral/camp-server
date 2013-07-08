package io.brooklyn.camp.dto;

import io.brooklyn.camp.rest.resource.ApidocRestResource;
import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.camp.spi.ApplicationComponentTemplate;
import io.brooklyn.camp.spi.AssemblyTemplate;
import io.brooklyn.camp.spi.Link;
import io.brooklyn.camp.spi.PlatformComponentTemplate;
import io.brooklyn.camp.spi.PlatformRootSummary;

import java.util.ArrayList;
import java.util.List;

public class PlatformDto extends ResourceDto {

    // defined as a constant so can be used in Swagger REST API annotations
    public static final String CLASS_NAME = "io.brooklyn.camp.dto.PlatformDto";
    static { assert CLASS_NAME.equals(PlatformDto.class.getCanonicalName()); }

    protected PlatformDto() {}
    protected PlatformDto(DtoFactory dtoFactory, PlatformRootSummary x) {
        super(dtoFactory, x);
        platformComponentTemplates = new ArrayList<LinkDto>();
        for (Link<PlatformComponentTemplate> t: dtoFactory.getPlatform().platformComponentTemplates().links()) {
            platformComponentTemplates.add(LinkDto.newInstance(dtoFactory, PlatformComponentTemplate.class, t));
        }
        
        applicationComponentTemplates = new ArrayList<LinkDto>();
        for (Link<ApplicationComponentTemplate> t: dtoFactory.getPlatform().applicationComponentTemplates().links()) {
            applicationComponentTemplates.add(LinkDto.newInstance(dtoFactory, ApplicationComponentTemplate.class, t));
        }

        assemblyTemplates = new ArrayList<LinkDto>();
        for (Link<AssemblyTemplate> t: dtoFactory.getPlatform().assemblyTemplates().links()) {
            assemblyTemplates.add(LinkDto.newInstance(dtoFactory, AssemblyTemplate.class, t));
        }

        // TODO set custom fields

        apidoc = LinkDto.newInstance(
                dtoFactory.getUriFactory().uriOfRestResource(ApidocRestResource.class),
                "API documentation");
    }

    // TODO add custom fields
    private List<LinkDto> assemblyTemplates;
    private List<LinkDto> platformComponentTemplates;
    private List<LinkDto> applicationComponentTemplates;
    
    // non-CAMP, but useful
    private LinkDto apidoc;
    
    public List<LinkDto> getAssemblyTemplates() {
        return assemblyTemplates;
    }
    
    public List<LinkDto> getPlatformComponentTemplates() {
        return platformComponentTemplates;
    }
    
    public List<LinkDto> getApplicationComponentTemplates() {
        return applicationComponentTemplates;
    }
    
    public LinkDto getApidoc() {
        return apidoc;
    }
    
    // --- building ---

    public static PlatformDto newInstance(DtoFactory dtoFactory, PlatformRootSummary x) {
        return new PlatformDto(dtoFactory, x);
    }

}
