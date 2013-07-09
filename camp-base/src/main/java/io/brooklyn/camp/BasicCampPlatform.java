package io.brooklyn.camp;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.brooklyn.camp.spi.ApplicationComponentTemplate;
import io.brooklyn.camp.spi.AssemblyTemplate;
import io.brooklyn.camp.spi.PlatformComponentTemplate;
import io.brooklyn.camp.spi.PlatformRootSummary;
import io.brooklyn.camp.spi.PlatformTransaction;
import io.brooklyn.camp.spi.collection.BasicResourceLookup;

public class BasicCampPlatform extends CampPlatform {

    private static final Logger log = LoggerFactory.getLogger(BasicCampPlatform.class);
    
    public BasicCampPlatform() {
        this(PlatformRootSummary.builder().name("CAMP Platform").build());
    }
    
    public BasicCampPlatform(PlatformRootSummary root) {
        super(root);
    }

    BasicResourceLookup<PlatformComponentTemplate> platformComponentTemplates = new BasicResourceLookup<PlatformComponentTemplate>();
    BasicResourceLookup<ApplicationComponentTemplate> applicationComponentTemplates = new BasicResourceLookup<ApplicationComponentTemplate>();
    BasicResourceLookup<AssemblyTemplate> assemblyTemplates = new BasicResourceLookup<AssemblyTemplate>();
    
    public BasicResourceLookup<PlatformComponentTemplate> platformComponentTemplates() {
        return platformComponentTemplates;
    }

    @Override
    public BasicResourceLookup<ApplicationComponentTemplate> applicationComponentTemplates() {
        return applicationComponentTemplates;
    }

    public BasicResourceLookup<AssemblyTemplate> assemblyTemplates() {
        return assemblyTemplates;
    }
    
    @Override
    public PlatformTransaction transaction() {
        return new BasicPlatformTransaction(this);
    }
    
    public static class BasicPlatformTransaction extends PlatformTransaction {
        private final BasicCampPlatform platform;
        private final AtomicBoolean committed = new AtomicBoolean(false);
        
        public BasicPlatformTransaction(BasicCampPlatform platform) {
            this.platform = platform;
        }
        
        @Override
        public void commit() {
            if (committed.getAndSet(true)) 
                throw new IllegalStateException("transaction being committed multiple times");
            
            for (Object o: additions) {
                if (o instanceof PlatformComponentTemplate) {
                    platform.platformComponentTemplates.add((PlatformComponentTemplate) o);
                    continue;
                }
                if (o instanceof ApplicationComponentTemplate) {
                    platform.applicationComponentTemplates.add((ApplicationComponentTemplate) o);
                    continue;
                }
                if (o instanceof PlatformComponentTemplate) {
                    platform.assemblyTemplates.add((AssemblyTemplate) o);
                    continue;
                }
                throw new UnsupportedOperationException("Object "+o+" of type "+o.getClass()+" cannot be added to "+platform);
            }
        }
        
        @Override
        protected void finalize() throws Throwable {
            if (!committed.get())
                log.warn("transaction was never applied");
            super.finalize();
        }
    }
    
}
