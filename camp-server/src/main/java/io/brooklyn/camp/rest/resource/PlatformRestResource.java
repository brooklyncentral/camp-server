package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.PlatformDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

//import io.brooklyn.camp.rest.apidoc.Apidoc;

@Path(PlatformRestResource.CAMP_URI_PATH)
//@Apidoc("Application entities")
@Produces("application/json")
public class PlatformRestResource extends AbstractCampRestResource {

    public static final String CAMP_URI_PATH = "/camp/v11";
    
    @GET
    public PlatformDto get() {
        return dto().adapt(camp().root());
    }
    
}
