package io.brooklyn.camp.dto;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import brooklyn.util.collections.MutableMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Test
public class LinkDtoTest {

    @Test
    public void testSimple() throws IOException {
        LinkDto l = LinkDto.newInstance("http://foo", "Foo");
        
        JsonNode t = BasicDtoTest.tree(l);
        Assert.assertEquals(t.size(), 2);
        Assert.assertEquals(t.get("href").asText(), l.getHref());
        Assert.assertEquals(t.get("targetName").asText(), l.getTargetName());
        Assert.assertTrue(l.getCustomAttributes()==null || l.getCustomAttributes().isEmpty());
        
        Assert.assertEquals(l, new ObjectMapper().readValue(t.toString(), LinkDto.class));
    }

    @Test
    public void testCustomAttrs() throws IOException {
        LinkDto l = LinkDto.newInstance("http://foo", "Foo", MutableMap.of("bar", "bee"));
        
        JsonNode t = BasicDtoTest.tree(l);
        Assert.assertEquals(t.size(), 3);
        Assert.assertEquals(t.get("href").asText(), l.getHref());
        Assert.assertEquals(t.get("targetName").asText(), l.getTargetName());
        Assert.assertEquals(t.get("bar").asText(), l.getCustomAttributes().get("bar"));
        
        Assert.assertEquals(l, new ObjectMapper().readValue(t.toString(), LinkDto.class));
    }

}
