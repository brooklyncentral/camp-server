package io.brooklyn.camp.dto;

import io.brooklyn.camp.impl.Link;
import io.brooklyn.camp.impl.PlatformComponentTemplate;
import io.brooklyn.camp.impl.PlatformRootSummary;
import io.brooklyn.camp.rest.util.DtoFactory;

import java.util.ArrayList;
import java.util.List;

public class PlatformDto extends ResourceDto {

    // TODO add custom fields
    private List<LinkDto> platformComponentTemplates;
    
    protected PlatformDto() {}

    public List<LinkDto> getPlatformComponentTemplates() {
        return platformComponentTemplates;
    }
    
    // --- building ---

    public static PlatformDto newInstance(DtoFactory dtoFactory, PlatformRootSummary x) {
        return new PlatformDto().newInstanceInitialization(dtoFactory, x);
    }

    protected PlatformDto newInstanceInitialization(DtoFactory dtoFactory, PlatformRootSummary x) {
        super.newInstanceInitialization(dtoFactory, x);

        platformComponentTemplates = new ArrayList<LinkDto>();
        for (Link<PlatformComponentTemplate> t: dtoFactory.getPlatform().platformComponentTemplates().links()) {
            platformComponentTemplates.add(LinkDto.newInstance(dtoFactory, PlatformComponentTemplate.class, t));
        }
                //dtoFactory.list(dtoFactory.getPlatform().platformComponentTemplates());
        // TODO add custom fields
        
        return this;
    }

}
