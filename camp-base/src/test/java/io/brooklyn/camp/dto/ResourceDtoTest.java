package io.brooklyn.camp.dto;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Test
public class ResourceDtoTest {

//    private static final Logger log = LoggerFactory.getLogger(ResourceDtoTest.class);
    
    @Test
    public void testSimple() throws IOException {
        ResourceDto r = new ResourceDto("http://foo", "Name", "a description",
                new Date(), Arrays.asList("tag1", "tag 2"), "RESOURCE", "NONE", null);
        
        JsonNode t = BasicDtoTest.tree(r);
        
        Assert.assertEquals(t.get("uri").asText(), r.getUri());
        Assert.assertEquals(r, new ObjectMapper().readValue(t.toString(), ResourceDto.class));
    }


}
