package br.com.citrinstar.searchFIPE.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChamaServico {

    public String obterDados(String endereco){
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = null;

        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

        return response.body();
    }
}