package com.softlond.modelconverter;

import org.reactivecommons.utils.ObjectMapper;

import java.lang.reflect.ParameterizedType;

public abstract class ModelMapper<M, D> {

    private final ObjectMapper mapper;
    protected final Class<M> modelClass;
    protected final Class<D> dtoClass;

    protected ModelMapper(ObjectMapper mapper) {
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.modelClass = (Class<M>) genericSuperclass.getActualTypeArguments()[0];
        this.dtoClass = (Class<D>) genericSuperclass.getActualTypeArguments()[1];
    }

    protected D modelToDTO(M modelEntity) {
        return this.mapper.map(modelEntity, this.dtoClass);
    }

    protected M dtoToModel(D dtoObject) {
        return this.mapper.map(dtoObject, this.modelClass);
    }
}
