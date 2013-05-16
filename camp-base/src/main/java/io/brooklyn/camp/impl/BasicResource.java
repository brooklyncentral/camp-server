package io.brooklyn.camp.impl;

import io.brooklyn.camp.dto.ResourceDto;

public class BasicResource<T extends ResourceDto> {

    T dto;
    
    public BasicResource(T dto) {
        this.dto = dto;
    }

    public T dto() { return dto; }
    
}
