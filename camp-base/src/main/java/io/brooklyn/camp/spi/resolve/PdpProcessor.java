package io.brooklyn.camp.spi.resolve;

import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.spi.AssemblyTemplate;
import io.brooklyn.camp.spi.instantiate.BasicAssemblyTemplateInstantiator;
import io.brooklyn.camp.spi.pdp.Artifact;
import io.brooklyn.camp.spi.pdp.AssemblyTemplateConstructor;
import io.brooklyn.camp.spi.pdp.DeploymentPlan;
import io.brooklyn.util.yaml.Yamls;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

import brooklyn.util.exceptions.Exceptions;

public class PdpProcessor {

    final CampPlatform campPlatform;
    
    final List<PdpMatcher> matchers = new ArrayList<PdpMatcher>();
    
    public PdpProcessor(CampPlatform campPlatform) {
        this.campPlatform = campPlatform;
    }

    /** create and return an AssemblyTemplate based on the given PDP */
    public AssemblyTemplate registerPdpFromYaml(String yaml) {
        Iterable<Object> template;
        template = Yamls.parseAll(yaml);
//        Yamls.dump(0, template);
        
        @SuppressWarnings("unchecked")
        DeploymentPlan plan = DeploymentPlan.of( Yamls.getAs(template, Map.class) );
        
        AssemblyTemplateConstructor atc = new AssemblyTemplateConstructor(campPlatform);
        
        // default instantiator is one which just invokes the component's instantiator
        atc.instantiator(BasicAssemblyTemplateInstantiator.class);
        
        if (plan.getName()!=null) atc.name(plan.getName());
        if (plan.getDescription()!=null) atc.description(plan.getDescription());
        // nothing done with origin just now...
        
        if (plan.getArtifacts()!=null) {
            for (Artifact art: plan.getArtifacts()) {
                apply(art, atc);
            }
        }
        
        return atc.commit();
    }
    
    public AssemblyTemplate registerPdpFromArchive(InputStream archiveInput) {
        try {
            ArchiveInputStream input = new ArchiveStreamFactory()
                .createArchiveInputStream(archiveInput);
            
            while (true) {
                ArchiveEntry entry = input.getNextEntry();
                if (entry==null) break;
                // TODO unpack entry, create a space on disk holding the archive ?
            }

            // use yaml...
            throw new UnsupportedOperationException("in progress");
            
        } catch (Exception e) {
            throw Exceptions.propagate(e);
        }
    }

    public void addMatcher(PdpMatcher m) {
        // TODO a list is a crude way to do matching ... but good enough to start
        matchers.add(m);
    }

    public List<PdpMatcher> getMatchers() {
        return matchers;
    }
    
    public void apply(Artifact art, AssemblyTemplateConstructor atc) {
        for (PdpMatcher matcher: getMatchers()) {
            if (matcher.accepts(art)) {
                // TODO first accepting is a crude way to do matching ... but good enough to start
                if (matcher.apply(art, atc))
                    return;
            }
        }
        throw new UnsupportedOperationException("Artifact "+art+" cannot be matched");
    }

}
