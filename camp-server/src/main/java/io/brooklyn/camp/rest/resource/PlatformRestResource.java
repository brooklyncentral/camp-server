package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.PlatformDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

//import io.brooklyn.camp.rest.apidoc.Apidoc;

@Path("/camp/v11")
//@Apidoc("Application entities")
@Produces("application/json")
public class PlatformRestResource extends AbstractCampRestResource {

    @GET
    public PlatformDto get() {
        return camp().root().dto();
    }
    
}
