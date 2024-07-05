package br.com.citrinstar.searchFIPE.service;

public interface ConverteDadosInterface {

    <T> T converterDados(String json, Class<T> classe);
}
