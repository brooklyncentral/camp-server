package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.PlatformComponentTemplateDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/camp/v11/platform-component-templates/{id}")
public class PlatformComponentTemplateRestResource extends AbstractCampRestResource {

    @GET
    public PlatformComponentTemplateDto get(String id) {
        return camp().platformComponentTemplates().get(id).dto();
    }
    
}
