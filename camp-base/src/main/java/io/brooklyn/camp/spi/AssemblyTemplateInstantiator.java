package io.brooklyn.camp.spi;

import io.brooklyn.camp.CampPlatform;

/** instances of this class should have a public no-arg constructor so the class names can be serialized */
public interface AssemblyTemplateInstantiator {

    public Assembly instantiate(AssemblyTemplate template, CampPlatform platform);
    
}
