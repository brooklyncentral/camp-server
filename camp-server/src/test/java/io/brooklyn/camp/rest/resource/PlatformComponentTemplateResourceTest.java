package io.brooklyn.camp.rest.resource;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import io.brooklyn.camp.dto.LinkDto;
import io.brooklyn.camp.dto.PlatformComponentTemplateDto;
import io.brooklyn.camp.dto.PlatformDto;
import io.brooklyn.camp.test.fixture.AbstractResourceTest;

public class PlatformComponentTemplateResourceTest extends AbstractResourceTest {

    @Test
    public void testLoadPlatformComponentTemplates() {
        PlatformDto platform = loadPlatform();
        assertFalse(platform.getPlatformComponentTemplates().isEmpty());

        for (LinkDto templateLink : platform.getPlatformComponentTemplates()) {
            PlatformComponentTemplateDto pct = resource(templateLink.getHref())
                    .get(PlatformComponentTemplateDto.class);

            assertNotNull(pct.getName());
        }
    }

}
