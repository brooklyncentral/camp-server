package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.PlatformComponentTemplateDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import brooklyn.rest.apidoc.Apidoc;

import com.wordnik.swagger.core.ApiOperation;
import com.wordnik.swagger.core.ApiParam;

@Path("/v11/platform-component-templates")
@Apidoc("Platform Component Template resources")
@Produces(MediaType.APPLICATION_JSON)
public class PlatformComponentTemplateResource extends AbstractCampResource {

    @Path("/{id}")
    @ApiOperation(value = "Get a specific platform component template",
            responseClass = PlatformComponentTemplateDto.CLASS_NAME)
    @GET
    public PlatformComponentTemplateDto get(
            @ApiParam(value = "ID of item being retrieved", required = true)
            @PathParam("id") String id) {
        return dtoFactory.adapt(lookup(campPlatform.platformComponentTemplates(), id));
    }

}
