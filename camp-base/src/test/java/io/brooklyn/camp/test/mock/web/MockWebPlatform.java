package io.brooklyn.camp.test.mock.web;

import io.brooklyn.camp.BasicCampPlatform;
import io.brooklyn.camp.spi.AssemblyTemplate;
import io.brooklyn.camp.spi.collection.BasicResourceLookup;

public class MockWebPlatform {

    public static <T extends BasicCampPlatform> T populate(T platform) {
        platform.platformComponentTemplates().add(MockAppServerComponent.TEMPLATE);
        platform.applicationComponentTemplates().add(MockWarComponent.TEMPLATE);
        
        AssemblyTemplate assembly = AssemblyTemplate.builder()
                .name("WebAppAssembly1")
                .description("Mock Web App Assembly Template")
                .applicationComponentTemplates(BasicResourceLookup.of(MockWarComponent.TEMPLATE))
                .build();
        platform.assemblyTemplates().add(assembly);
        
        return platform;
    }

    public static BasicCampPlatform newPlatform() {
        return MockWebPlatform.populate(new BasicCampPlatform());
    }
    
}
