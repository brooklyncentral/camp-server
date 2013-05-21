package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.ApplicationComponentTemplateDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/camp/v11/application-component-templates")
@Produces("application/json")
public class ApplicationComponentTemplateRestResource extends AbstractCampRestResource {

    @Path("/{id}")
    @GET
    public ApplicationComponentTemplateDto get(
//            @ApiParam(value = "Application ID or name", required = true)
            @PathParam("id") String id) {
        return dto().adapt(lookup(camp().applicationComponentTemplates(), id));
    }

}
