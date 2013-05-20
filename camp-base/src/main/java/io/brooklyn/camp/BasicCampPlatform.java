package io.brooklyn.camp;

import io.brooklyn.camp.impl.PlatformComponentTemplate;
import io.brooklyn.camp.util.collection.BasicResourceListProvider;

public class BasicCampPlatform extends CampPlatform {

    BasicResourceListProvider<PlatformComponentTemplate> platformComponentTemplates = new BasicResourceListProvider<PlatformComponentTemplate>();
    
    public BasicResourceListProvider<PlatformComponentTemplate> platformComponentTemplates() {
        return platformComponentTemplates;
    }

}
