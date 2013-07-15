package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.dto.PlatformDto;
import io.brooklyn.camp.rest.util.WebResourceUtils;
import io.brooklyn.camp.spi.AssemblyTemplate;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import brooklyn.rest.apidoc.Apidoc;

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
    public Response postJson(@Context UriInfo info, String json) {
        return postYaml(info, json);
    }

    @POST
    @Consumes({"application/x-yaml"})
    public Response postYaml(@Context UriInfo info, String yaml) {
        log.debug("YAML pdp:\n"+yaml);
        AssemblyTemplate template = camp().pdp().registerPdpFromYaml(yaml);
        return created(info, template);
    }

    @POST
    @Consumes({"application/x-tar", "application/x-tgz", "application/x-zip"})
    public Response postArchive(@Context UriInfo info, InputStream archiveInput) {
        log.debug("ARCHIVE pdp");
        AssemblyTemplate template = camp().pdp().registerPdpFromArchive(archiveInput);
        return created(info, template);
    }

    protected Response created(UriInfo info, AssemblyTemplate template) {
        return WebResourceUtils.created(info, dto().adapt(template).getUri());
    }

}
