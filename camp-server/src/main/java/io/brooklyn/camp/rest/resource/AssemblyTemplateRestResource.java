package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.AssemblyTemplateDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import brooklyn.rest.apidoc.Apidoc;

import com.wordnik.swagger.core.ApiOperation;
import com.wordnik.swagger.core.ApiParam;

@Path(AssemblyTemplateRestResource.URI_PATH)
@Apidoc("Platform Component Template resources")
@Produces("application/json")
public class AssemblyTemplateRestResource extends AbstractCampRestResource {

    public static final String URI_PATH = PlatformRestResource.CAMP_URI_PATH + "/assembly-templates";

    @Path("/{id}")
    @ApiOperation(value = "Get a specific assembly template",
            responseClass = AssemblyTemplateDto.CLASS_NAME)
    @GET
    public AssemblyTemplateDto get(
            @ApiParam(value = "ID of item being retrieved", required = true)
            @PathParam("id") String id) {
        return dto().adapt(lookup(camp().assemblyTemplates(), id));
    }

}
