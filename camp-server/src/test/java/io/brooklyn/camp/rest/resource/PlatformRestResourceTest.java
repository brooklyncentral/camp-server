package io.brooklyn.camp.rest.resource;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.brooklyn.camp.dto.PlatformComponentTemplateDto;
import io.brooklyn.camp.dto.PlatformDto;
import io.brooklyn.camp.test.fixture.AbstractRestResourceTest;

public class PlatformRestResourceTest extends AbstractRestResourceTest {

    private static final Logger log = LoggerFactory.getLogger(PlatformRestResourceTest.class);
    
    @Test
    public void testPlatformIncludesList() {

        PlatformDto p = resource(PlatformRestResource.CAMP_URI_PATH)
                                .get(PlatformDto.class);
        assertFalse(p.getPlatformComponentTemplates().isEmpty());

        String linkedTemplate = p.getPlatformComponentTemplates().get(0).getHref();
        PlatformComponentTemplateDto pct = resource(linkedTemplate)
                .get(PlatformComponentTemplateDto.class);

        log.debug("Loaded PCT via REST: "+pct);
        assertNotNull(pct.getName());
    }
        
}
