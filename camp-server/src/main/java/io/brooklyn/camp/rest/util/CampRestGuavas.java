package io.brooklyn.camp.rest.util;

import io.brooklyn.camp.impl.BasicResource;

import com.google.common.base.Function;

public class CampRestGuavas {

    public static final Function<BasicResource,String> IDENTITY_OF_REST_RESOURCE = 
            new Function<BasicResource,String>() {
                public String apply(BasicResource input) { return input.getId(); }
            };

}
