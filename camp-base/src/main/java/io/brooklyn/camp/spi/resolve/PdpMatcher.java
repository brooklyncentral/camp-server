package io.brooklyn.camp.spi.resolve;

import io.brooklyn.camp.spi.pdp.Artifact;
import io.brooklyn.camp.spi.pdp.AssemblyTemplateConstructor;


public interface PdpMatcher {

    boolean accepts(Object art);
    boolean apply(Object art, AssemblyTemplateConstructor atc);

    public abstract class ArtifactMatcher implements PdpMatcher {
        private String artifactType;
        public ArtifactMatcher(String artifactType) {
            this.artifactType = artifactType;
        }
        public boolean accepts(Object art) {
            return (art instanceof Artifact) && this.artifactType.equals( ((Artifact)art).getArtifactType() );
        }
    }
}
