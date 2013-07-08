package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.PlatformDto;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import brooklyn.rest.apidoc.Apidoc;
import brooklyn.util.collections.MutableMap;

import com.wordnik.swagger.core.ApiOperation;

//import io.brooklyn.camp.rest.apidoc.Apidoc;

@Path(PlatformRestResource.CAMP_URI_PATH)
@Apidoc("Platform (root)")
@Produces("application/json")
public class PlatformRestResource extends AbstractCampRestResource {

    private static final Logger log = LoggerFactory.getLogger(PlatformRestResource.class);
    
    public static final String CAMP_URI_PATH = "/camp/v11";
    
    @ApiOperation(value = "Return the Platform (root) resource",
            responseClass = PlatformDto.CLASS_NAME)
    @GET
    public PlatformDto get() {
        return dto().adapt(camp().root());
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Map<String,String> postJson(String json) {
        log.info("JSON pdp:\n"+json);
        return MutableMap.of("Location", "http://todo/json");
    }

    @POST
    @Consumes({"application/yaml"})
    public Map<String,String> postYaml(String yaml) {
        log.info("YAML pdp:\n"+yaml);
        return MutableMap.of("Location", "http://todo/yaml");
    }

}
