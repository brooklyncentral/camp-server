package io.brooklyn.camp.spi.pdp;

import io.brooklyn.camp.BasicCampPlatform;
import io.brooklyn.camp.spi.AssemblyTemplate;
import io.brooklyn.camp.test.mock.web.MockWebPlatform;

import java.io.IOException;
import java.io.InputStream;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import com.google.common.base.Preconditions;

public class PdpYamlTest {

    @Test
    public void testSimpleYaml() throws IOException {
        BasicCampPlatform platform = MockWebPlatform.populate(new BasicCampPlatform());
        InputStream input = getClass().getResourceAsStream("pdp1.yaml");
        AssemblyTemplate at = platform.pdp().registerPdpFromYaml(
                Files.readFile(Preconditions.checkNotNull(input, "file not found")));
        Assert.assertEquals(at.getName(), "sample");
    }
    
}
