package io.brooklyn.camp.rest.resource;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import io.brooklyn.camp.dto.ApplicationComponentTemplateDto;
import io.brooklyn.camp.dto.LinkDto;
import io.brooklyn.camp.dto.PlatformDto;
import io.brooklyn.camp.test.fixture.AbstractResourceTest;

public class ApplicationComponentTemplateResourceTest extends AbstractResourceTest {

    @Test
    public void testLoadApplicationComponentTemplates() {
        PlatformDto platform = loadPlatform();
        assertFalse(platform.getApplicationComponentTemplates().isEmpty());

        for (LinkDto templateLink : platform.getApplicationComponentTemplates()) {
            ApplicationComponentTemplateDto pct = resource(templateLink.getHref())
                    .get(ApplicationComponentTemplateDto.class);

            assertNotNull(pct.getName());
        }
    }

}
