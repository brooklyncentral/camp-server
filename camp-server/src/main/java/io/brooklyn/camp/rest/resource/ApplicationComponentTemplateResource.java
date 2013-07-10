package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.ApplicationComponentTemplateDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import brooklyn.rest.apidoc.Apidoc;

import com.wordnik.swagger.core.ApiOperation;
import com.wordnik.swagger.core.ApiParam;

@Path("/v11/application-component-templates")
@Apidoc("Application Component Template resources")
@Produces("application/json")
public class ApplicationComponentTemplateResource extends AbstractCampResource {

    @Path("/{id}")
    @ApiOperation(value = "Get a specific platform component template",
        responseClass = ApplicationComponentTemplateDto.CLASS_NAME)
    @GET
    public ApplicationComponentTemplateDto get(
            @ApiParam(value = "ID of item being retrieved", required = true)
            @PathParam("id") String id) {
        return dtoFactory.adapt(lookup(campPlatform.applicationComponentTemplates(), id));
    }

}
