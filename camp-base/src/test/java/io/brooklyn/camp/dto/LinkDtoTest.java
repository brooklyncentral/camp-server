package io.brooklyn.camp.dto;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import brooklyn.util.collections.MutableMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Test
public class LinkDtoTest {

    private static final Logger log = LoggerFactory.getLogger(LinkDtoTest.class);
    
    @Test
    public void testSimple() throws IOException {
        LinkDto l = new LinkDto("http://foo", "Foo", null);
        
        JsonNode t = tree(l);
        Assert.assertEquals(t.size(), 2);
        Assert.assertEquals(t.get("href").asText(), l.href);
        Assert.assertEquals(t.get("targetName").asText(), l.targetName);
        Assert.assertNull(l.customAttributes);
        
        Assert.assertEquals(l, new ObjectMapper().readValue(t.toString(), LinkDto.class));
    }

    @Test
    public void testCustomAttrs() throws IOException {
        LinkDto l = new LinkDto("http://foo", "Foo", MutableMap.of("bar", "bee"));
        
        JsonNode t = tree(l);
        Assert.assertEquals(t.size(), 3);
        Assert.assertEquals(t.get("href").asText(), l.href);
        Assert.assertEquals(t.get("targetName").asText(), l.targetName);
        Assert.assertEquals(t.get("bar"), l.customAttributes.get("bar"));
        
        Assert.assertEquals(l, new ObjectMapper().readValue(t.toString(), LinkDto.class));
    }

    public static JsonNode tree(Object l) throws JsonProcessingException, IOException {
        ObjectMapper m = new ObjectMapper();
        String s = m.writeValueAsString(l);
        log.debug(s);
        JsonNode t = m.readTree(s);
        return t;
    }

//    @Test
//    public void testSimple() {
//        LinkDto l = new LinkDto("http://foo", "Foo", null);
//    }

}
