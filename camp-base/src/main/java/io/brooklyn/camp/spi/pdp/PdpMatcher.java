package io.brooklyn.camp.spi.pdp;


public interface PdpMatcher {

    boolean accepts(Object art);
    boolean apply(Object art, AssemblyTemplateConstructor atc);

    public abstract class ArtifactMatcher implements PdpMatcher {
        private String artifactType;
        public ArtifactMatcher(String artifactType) {
            this.artifactType = artifactType;
        }
        public boolean accepts(Object art) {
            return (art instanceof Artifact) && this.artifactType.equals( ((Artifact)art).artifactType );
        }
    }
}
