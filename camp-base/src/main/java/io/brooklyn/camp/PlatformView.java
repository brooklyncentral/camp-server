package io.brooklyn.camp;

import io.brooklyn.camp.dto.PlatformDto;
import io.brooklyn.camp.impl.PlatformComponentTemplate;
import io.brooklyn.camp.util.collection.ResourceListProvider;

public abstract class PlatformView {

    public abstract ResourceListProvider<PlatformComponentTemplate> platformComponentTemplates();

    public PlatformDtoGenerator platform() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public class PlatformDtoGenerator {
        public PlatformDto dto() {
            return new PlatformDto(null, null, null, null, null, null, null, null);
        }
    }
}
