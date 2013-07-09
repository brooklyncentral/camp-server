package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.AssemblyTemplateDto;
import io.brooklyn.camp.spi.AssemblyTemplate;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import brooklyn.rest.apidoc.Apidoc;
import brooklyn.util.exceptions.Exceptions;

import com.wordnik.swagger.core.ApiOperation;
import com.wordnik.swagger.core.ApiParam;

@Path(AssemblyTemplateRestResource.URI_PATH)
@Apidoc("Platform Component Template resources")
@Produces("application/json")
public class AssemblyTemplateRestResource extends AbstractCampRestResource {

    private static final Logger log = LoggerFactory.getLogger(AssemblyTemplateRestResource.class);
    
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

    @Path("/{id}")
    @ApiOperation(value = "Instantiate a specific assembly template"
    // TODO AssemblyDto, or location thereto?
//            , responseClass = AssemblyTemplateDto.CLASS_NAME
            )
    @POST
    public void post(
            @ApiParam(value = "ID of item being retrieved", required = true)
            @PathParam("id") String id) {
        try {
            log.info("CAMP REST instantiating AT "+id);
            AssemblyTemplate at = lookup(camp().assemblyTemplates(), id);
            at.getInstantiator().newInstance().instantiate(at, camp());
            
            // TODO 201, with assembly info
        } catch (Exception e) {
            log.error("Unable to create AT "+id+": "+e);
            throw Exceptions.propagate(e);
        }
    }
    
}
