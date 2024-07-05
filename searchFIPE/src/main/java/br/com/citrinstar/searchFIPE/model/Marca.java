package br.com.citrinstar.searchFIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Marca(
        @JsonAlias("codigo") String codigo,
        @JsonAlias("nome") String nome) {
}
