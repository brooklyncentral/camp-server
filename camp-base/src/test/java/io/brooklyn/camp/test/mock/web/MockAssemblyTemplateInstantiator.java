package io.brooklyn.camp.test.mock.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.spi.AssemblyTemplate;
import io.brooklyn.camp.spi.AssemblyTemplateInstantiator;

public class MockAssemblyTemplateInstantiator implements AssemblyTemplateInstantiator {

    private static final Logger log = LoggerFactory.getLogger(MockAssemblyTemplateInstantiator.class);
    
    public void instantiate(AssemblyTemplate template, CampPlatform platform) {
        log.debug("Ignoring request to instantiate "+template);
    }

}
