package br.com.citrinstar.searchFIPE.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConverteDados implements ConverteDadosInterface{

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T converterDados(String json, Class<T> classe) {
        try{
            return mapper.readValue(json,classe);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> converterDadosLista(String json, Class<T> classe) {
        /*
        *Obtém a fábrica de tipos do ObjectMapper
            TypeFactory typeFactory = mapper.getTypeFactory();

        *Cria um JavaType representando uma lista do tipo especificado
            JavaType javaType = typeFactory.constructCollectionType(List.class, clazz);
        *Usa o JavaType para desserializar o JSON na lista especificada
            return mapper.readValue(json, javaType);
        * */

        try{
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, classe) );
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

}
