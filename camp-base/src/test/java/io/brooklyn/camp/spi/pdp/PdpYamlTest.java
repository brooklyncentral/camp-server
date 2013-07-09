package io.brooklyn.camp.spi.pdp;

import io.brooklyn.camp.spi.AssemblyTemplate;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

public class PdpYamlTest {

    @Test
    public void testSimpleYaml() throws IOException {
        PdpProcessor p = new PdpProcessor(null);
        AssemblyTemplate at = p.registerPdpFromYaml(Files.readFile(getClass().getResourceAsStream("pdp1.yaml")));
        Assert.assertEquals(at.getName(), "sample");
    }
    
}
