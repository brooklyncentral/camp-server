package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.PlatformDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import brooklyn.rest.apidoc.Apidoc;

import com.wordnik.swagger.core.ApiOperation;

@Path(PlatformResource.CAMP_URI_PATH)
@Apidoc("Platform (root)")
@Produces(MediaType.APPLICATION_JSON)
public class PlatformResource extends AbstractCampResource {

    public static final String CAMP_URI_PATH = "/camp/v11";
    
    @ApiOperation(value = "Return the Platform (root) resource",
            responseClass = PlatformDto.CLASS_NAME)
    @GET
    public PlatformDto get() {
        return dtoFactory.adapt(campPlatform.root());
    }
    
}
