package io.brooklyn.camp.spi.resolve;

import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.spi.AssemblyTemplate;
import io.brooklyn.camp.spi.instantiate.BasicAssemblyTemplateInstantiator;
import io.brooklyn.camp.spi.pdp.Artifact;
import io.brooklyn.camp.spi.pdp.AssemblyTemplateConstructor;
import io.brooklyn.camp.spi.pdp.DeploymentPlan;
import io.brooklyn.camp.spi.pdp.Service;
import io.brooklyn.util.yaml.Yamls;

import java.io.InputStream;
import java.io.Reader;
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

    @SuppressWarnings("unchecked")
    public DeploymentPlan parseDeploymentPlan(Reader yaml) {
        Iterable<Object> template = Yamls.parseAll(yaml);
        
        return DeploymentPlan.of( Yamls.getAs(template, Map.class) );
    }
    
    /** create and return an AssemblyTemplate based on the given DP (yaml) */
    public AssemblyTemplate registerDeploymentPlan(Reader yaml) {
        DeploymentPlan plan = parseDeploymentPlan(yaml);
        return registerDeploymentPlan(plan);
    }

    public AssemblyTemplate registerDeploymentPlan(DeploymentPlan plan) {
        AssemblyTemplateConstructor atc = new AssemblyTemplateConstructor(campPlatform);
        
        // default instantiator is one which just invokes the component's instantiator
        atc.instantiator(BasicAssemblyTemplateInstantiator.class);
        
        if (plan.getName()!=null) atc.name(plan.getName());
        if (plan.getDescription()!=null) atc.description(plan.getDescription());
        // nothing done with origin just now...
        
        if (plan.getServices()!=null) {
            for (Service svc: plan.getServices()) {
                resolve(svc, atc);
            }
        }

        if (plan.getArtifacts()!=null) {
            for (Artifact art: plan.getArtifacts()) {
                resolve(art, atc);
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
    
    public void resolve(Object deploymentPlanItem, AssemblyTemplateConstructor atc) {
        for (PdpMatcher matcher: getMatchers()) {
            if (matcher.accepts(deploymentPlanItem)) {
                // TODO first accepting is a crude way to do matching ... but good enough to start
                if (matcher.apply(deploymentPlanItem, atc))
                    return;
            }
        }
        throw new UnsupportedOperationException("Deployment plan item "+deploymentPlanItem+" cannot be matched");
    }

}
