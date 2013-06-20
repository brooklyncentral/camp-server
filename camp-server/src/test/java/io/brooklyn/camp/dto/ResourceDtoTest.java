package io.brooklyn.camp.dto;

import io.brooklyn.camp.BasicCampPlatform;
import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.CampServer;
import io.brooklyn.camp.commontypes.RepresentationSkew;
import io.brooklyn.camp.rest.util.CampRestGuavas;
import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.camp.spi.AbstractResource;

import java.io.IOException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Test
public class ResourceDtoTest {

//    private static final Logger log = LoggerFactory.getLogger(ResourceDtoTest.class);
    
    final CampPlatform platform = new BasicCampPlatform();
    final String uriBase = "http://atest/";

    protected ResourceDto initSimpleDto() {
        CampServer server = new CampServer(platform, uriBase);
        server.setDtoFactory(new DtoFactory(platform, uriBase));
        server.getDtoFactory().getUriFactory().registerIdentityFunction(AbstractResource.class, "basic", CampRestGuavas.IDENTITY_OF_REST_RESOURCE);
        AbstractResource resource = AbstractResource.builder()
                .name("Name")
                .description("a description")
                .tags(Arrays.asList("tag1", "tag 2"))
                .representationSkew(RepresentationSkew.NONE)
                .build();
        return ResourceDto.newInstance(server.getDtoFactory(), resource);
    }
    
    @Test
    public void testSimpleCreation() throws IOException {
        ResourceDto resourceDto = initSimpleDto();
        
        Assert.assertNotNull(resourceDto.getCreatedAsString());
        Assert.assertEquals(resourceDto.getName(), "Name");
        Assert.assertEquals(resourceDto.getDescription(), "a description");
        Assert.assertEquals(resourceDto.getTags(), Arrays.asList("tag1", "tag 2"));
        Assert.assertEquals(resourceDto.getRepresentationSkew(), RepresentationSkew.NONE);
    }
    
    public void testSimpleSerializationAndDeserialization() throws IOException {
        ResourceDto resourceDto = initSimpleDto();
        
        JsonNode t = BasicDtoTest.tree(resourceDto);
        
//        Assert.assertEquals(t.get("uri").asText(), r.getUri());
        ResourceDto r2 = new ObjectMapper().readValue(t.toString(), ResourceDto.class);
        Assert.assertNotNull(r2.getCreated());
        Assert.assertEquals(resourceDto, r2);
    }


}
