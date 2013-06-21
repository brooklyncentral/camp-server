package io.brooklyn.camp.rest.resource;

import javax.ws.rs.Path;

import brooklyn.rest.apidoc.Apidoc;

@Path(ApidocResource.API_URI_PATH)
@Apidoc("Web API Documentation")
public class ApidocResource extends brooklyn.rest.apidoc.ApidocResource {

    public static final String API_URI_PATH = PlatformResource.CAMP_URI_PATH + "/apidoc";

}
