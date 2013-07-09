package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.PlatformDto;
import io.brooklyn.camp.spi.AssemblyTemplate;

import java.io.InputStream;
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
        return postYaml(json);
    }

    @POST
    @Consumes({"application/x-yaml"})
    public Map<String,String> postYaml(String yaml) {
        log.debug("YAML pdp:\n"+yaml);
        
        AssemblyTemplate template = camp().pdp().registerPdpFromYaml(yaml);
        return MutableMap.of("Location", dto().adapt(template).getUri());
    }

    @POST
    @Consumes({"application/x-tar", "application/x-tgz", "application/x-zip"})
    public Map<String,String> postArchive(InputStream archiveInput) {
        log.debug("ARCHIVE pdp");
        
        AssemblyTemplate template = camp().pdp().registerPdpFromArchive(archiveInput);
        return MutableMap.of("Location", dto().adapt(template).getUri());
    }

}
