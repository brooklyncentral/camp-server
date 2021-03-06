package io.brooklyn.camp.spi.pdp;

import io.brooklyn.camp.BasicCampPlatform;
import io.brooklyn.camp.spi.AssemblyTemplate;
import io.brooklyn.camp.test.mock.web.MockWebPlatform;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import brooklyn.util.stream.Streams;

public class PdpYamlTest {

    private static final Logger log = LoggerFactory.getLogger(PdpYamlTest.class);
    
    @Test
    public void testSimpleYamlArtifactParse() throws IOException {
        BasicCampPlatform platform = MockWebPlatform.populate(new BasicCampPlatform());
        Reader input = Streams.reader(getClass().getResourceAsStream("pdp-single-artifact.yaml"));
        DeploymentPlan plan = platform.pdp().parseDeploymentPlan(input);
        log.info("DP is:\n"+plan.toString());
        Assert.assertEquals(plan.getName(), "sample");
        Assert.assertEquals(plan.getArtifacts().size(), 1);
        Assert.assertEquals(plan.getServices().size(), 0);
        
        Artifact artifact1 = plan.getArtifacts().iterator().next();
        Assert.assertEquals(artifact1.getName(), "sample WAR");
    }
    
    @Test
    public void testSimpleYamlServiceParse() throws IOException {
        BasicCampPlatform platform = MockWebPlatform.populate(new BasicCampPlatform());
        Reader input = Streams.reader(getClass().getResourceAsStream("pdp-single-service.yaml"));
        DeploymentPlan plan = platform.pdp().parseDeploymentPlan(input);
        log.info("DP is:\n"+plan.toString());
        Assert.assertEquals(plan.getName(), "sample");
        Assert.assertEquals(plan.getArtifacts().size(), 0);
        Assert.assertEquals(plan.getServices().size(), 1);
        
        Service service1 = plan.getServices().iterator().next();
        Assert.assertEquals(service1.getName(), "Hello WAR");
    }
    
    @Test
    public void testSimpleYamlMatch() throws IOException {
        BasicCampPlatform platform = MockWebPlatform.populate(new BasicCampPlatform());
        Reader input = new InputStreamReader(getClass().getResourceAsStream("pdp-single-artifact.yaml"));
        AssemblyTemplate at = platform.pdp().registerDeploymentPlan(input);
        log.info("AT is:\n"+at.toString());
        Assert.assertEquals(at.getApplicationComponentTemplates().links().size(), 1);
        Assert.assertEquals(at.getName(), "sample");
    }
    
}
