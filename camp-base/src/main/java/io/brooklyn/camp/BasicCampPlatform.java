package io.brooklyn.camp;

import io.brooklyn.camp.impl.ApplicationComponentTemplate;
import io.brooklyn.camp.impl.PlatformComponentTemplate;
import io.brooklyn.camp.util.collection.BasicResourceListProvider;

public class BasicCampPlatform extends CampPlatform {

    BasicResourceListProvider<PlatformComponentTemplate> platformComponentTemplates = new BasicResourceListProvider<PlatformComponentTemplate>();
    BasicResourceListProvider<ApplicationComponentTemplate> applicationComponentTemplates = new BasicResourceListProvider<ApplicationComponentTemplate>();
    
    public BasicResourceListProvider<PlatformComponentTemplate> platformComponentTemplates() {
        return platformComponentTemplates;
    }

    @Override
    public BasicResourceListProvider<ApplicationComponentTemplate> applicationComponentTemplates() {
        return applicationComponentTemplates;
    }

}
