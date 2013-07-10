package io.brooklyn.camp.spi.instantiate;

import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.spi.Assembly;
import io.brooklyn.camp.spi.AssemblyTemplate;

/** A simple instantiator which simply invokes the component's instantiators in parallel */
public class BasicAssemblyTemplateInstantiator implements AssemblyTemplateInstantiator {

    public Assembly instantiate(AssemblyTemplate template, CampPlatform platform) {
        // TODO this should build it based on the components
        throw new UnsupportedOperationException("TODO");
    }

}
