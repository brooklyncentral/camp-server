package io.brooklyn.camp.test.fixture;

import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.CampServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryCamp {
    
    private static final Logger log = LoggerFactory.getLogger(InMemoryCamp.class);

    
    public static void main(String[] args) {
        
        // new platform with some mock types and some data structures
        
            // interface CampComponent
            // getComponentTemplate() -> operations, links, etc
        
            // platformView.getComponent(id) -> returns instance of domain-specific component type
        CampPlatform p = new MockCampPlatform1();
        
        // new server
        CampServer s = new CampServer(p).start();
        
        // requests against server
        
    }
    
    
}
