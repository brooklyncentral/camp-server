package io.brooklyn.camp.test.mock.web;

import io.brooklyn.camp.BasicCampPlatform;

public class MockWebPlatform {

    public static <T extends BasicCampPlatform> T populate(T platform) {
        platform.platformComponentTemplates().add(MockAppServerComponent.TEMPLATE);
        platform.applicationComponentTemplates().add(MockWarComponent.TEMPLATE);
        return platform;
    }

    public static BasicCampPlatform newPlatform() {
        return MockWebPlatform.populate(new BasicCampPlatform());
    }
    
}
