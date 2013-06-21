package io.brooklyn.camp.rest.util;

import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.dto.ApplicationComponentTemplateDto;
import io.brooklyn.camp.dto.PlatformComponentTemplateDto;
import io.brooklyn.camp.dto.PlatformDto;
import io.brooklyn.camp.rest.resource.AbstractCampResource;
import io.brooklyn.camp.rest.resource.ApplicationComponentTemplateResource;
import io.brooklyn.camp.rest.resource.PlatformComponentTemplateResource;
import io.brooklyn.camp.rest.resource.PlatformResource;
import io.brooklyn.camp.spi.AbstractResource;
import io.brooklyn.camp.spi.ApplicationComponentTemplate;
import io.brooklyn.camp.spi.PlatformComponentTemplate;
import io.brooklyn.camp.spi.PlatformRootSummary;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import brooklyn.util.collections.MutableMap;
import brooklyn.util.net.Urls;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

@Singleton
public class DtoFactory {

    private CampPlatform platform;
    private String uriBase;
    
    private UriFactory uriFactory;

    @Inject
    public DtoFactory(CampPlatform campPlatform, @Named("uriBase") String uriBase) {
        this.platform = campPlatform;
        this.uriBase = uriBase;
        
        uriFactory = new UriFactory();
        uriFactory.registerIdentifiableRestResource(PlatformRootSummary.class, PlatformResource.class);
        uriFactory.registerIdentifiableRestResource(PlatformComponentTemplate.class, PlatformComponentTemplateResource.class);
        uriFactory.registerIdentifiableRestResource(ApplicationComponentTemplate.class, ApplicationComponentTemplateResource.class);
    }

    public CampPlatform getPlatform() {
        return platform;
    }

    public UriFactory getUriFactory() {
        return uriFactory;
    }

    public String uri(AbstractResource x) {
        return getUriFactory().uri(x);
    }
        
    public String uri(Class<? extends AbstractResource> targetType, String id) {
        return getUriFactory().uri(targetType, id);
    }

    public PlatformComponentTemplateDto adapt(PlatformComponentTemplate platformComponentTemplate) {
        return PlatformComponentTemplateDto.newInstance(this, platformComponentTemplate);
    }

    public ApplicationComponentTemplateDto adapt(ApplicationComponentTemplate applicationComponentTemplate) {
        return ApplicationComponentTemplateDto.newInstance(this, applicationComponentTemplate);
    }

    public PlatformDto adapt(PlatformRootSummary root) {
        return PlatformDto.newInstance(this, root);
    }

    public class UriFactory {
        /** registry of generating a URI given an object */
        Map<Class<?>,Function<Object,String>> registryResource = new MutableMap<Class<?>, Function<Object,String>>();
        /** registry of generating a URI given an ID */
        Map<Class<?>,Function<String,String>> registryId = new MutableMap<Class<?>, Function<String,String>>();

        /** registers a function which generates a URI given a type; note that this method cannot be used for links */
        @SuppressWarnings("unchecked")
        public synchronized <T> void registerResourceUriFunction(Class<T> type, Function<T,String> fnUri) {
            registryResource.put(type, (Function<Object, String>) fnUri);
        }

        /** registers a type to generate a URI which concatenates the given base with the
         * result of the given function to generate an ID against an object of the given type */
        public synchronized <T> void registerIdentityFunction(Class<T> type, final String resourceTypeUriBase, final Function<T,String> fnIdentity) {
            final Function<String,String> fnUriFromId = new Function<String,String>() {
                public String apply(String id) {
                    return Urls.mergePaths(resourceTypeUriBase, id);
                }
            };
            registryId.put(type, (Function<String, String>) fnUriFromId);
            registerResourceUriFunction(type, new Function<T,String>() {
                public String apply(T input) {
                    return fnUriFromId.apply(fnIdentity.apply(input));
                }
            });
        }

        /** registers a CAMP Resource type against a RestResource, generating the URI
         * by concatenating the @Path annotation on the RestResource with the ID of the CAMP resource */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public synchronized <T extends AbstractResource> void registerIdentifiableRestResource(Class<T> type, Class<? extends AbstractCampResource> restResource) {
            registerIdentityFunction(type, 
                    uriOfRestResource(restResource),
                    (Function) CampRestGuavas.IDENTITY_OF_REST_RESOURCE);
        }
        
        public String uri(Class<? extends AbstractResource> targetType, String id) {
            return Preconditions.checkNotNull(registryId.get(targetType), 
                    "No REST ID converter registered for %s (id %s)", targetType, id)
                    .apply(id);
        }

        public String uri(AbstractResource x) {
            return Preconditions.checkNotNull(registryResource.get(x.getClass()), 
                    "No REST converter registered for %s (%s)", x.getClass(), x)
                    .apply(x);
        }
        
        public String uriOfRestResource(Class<?> restResourceClass) {
            return Urls.mergePaths(uriBase, 
                    Preconditions.checkNotNull(restResourceClass.getAnnotation(Path.class),
                            "No @Path on type %s", restResourceClass)
                    .value());
        }
            

    }

}
