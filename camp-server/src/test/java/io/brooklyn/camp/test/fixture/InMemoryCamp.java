package io.brooklyn.camp.test.fixture;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import io.brooklyn.camp.BasicCampPlatform;
import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.CampServer;
import io.brooklyn.camp.CampServletModule;
import io.brooklyn.camp.test.mock.web.MockWebPlatform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryCamp {
    
    private static final Logger log = LoggerFactory.getLogger(InMemoryCamp.class);

    public static void main(String[] args) {
        
        // new platform with some mock types and some data structures
        
            // interface CampComponent
            // getComponentTemplate() -> operations, links, etc
        
            // platformView.getComponent(id) -> returns instance of domain-specific component type
        Injector injector = Guice.createInjector(new CampServletModule());
        CampPlatform platform = injector.getInstance(CampPlatform.class);
        MockWebPlatform.populate((BasicCampPlatform) platform);

        // new server
        final CampServer server = injector.getInstance(CampServer.class);
        server.startWithFilter(injector.getInstance(GuiceFilter.class));

    }
    
    
}
