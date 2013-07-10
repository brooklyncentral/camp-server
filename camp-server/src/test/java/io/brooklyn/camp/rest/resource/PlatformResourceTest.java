package io.brooklyn.camp.rest.resource;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.brooklyn.camp.dto.PlatformComponentTemplateDto;
import io.brooklyn.camp.dto.PlatformDto;
import io.brooklyn.camp.test.fixture.AbstractResourceTest;

public class PlatformResourceTest extends AbstractResourceTest {

    private static final Logger LOG = LoggerFactory.getLogger(PlatformResourceTest.class);
    
    @Test
    public void testLoadPlatform() {
        PlatformDto p = loadPlatform();
        assertFalse(p.getPlatformComponentTemplates().isEmpty());
        assertFalse(p.getApplicationComponentTemplates().isEmpty());
        String linkedTemplate = p.getPlatformComponentTemplates().get(0).getHref();
        LOG.debug("Linked template should be found at: " + linkedTemplate);

        PlatformComponentTemplateDto pct = resource(linkedTemplate)
                .get(PlatformComponentTemplateDto.class);

        LOG.debug("Loaded platform component template: " + pct);
        assertNotNull(pct.getName());
    }
        
}
