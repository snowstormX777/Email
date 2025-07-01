package com.info.emailConsumer.service;

import com.info.emailConsumer.model.Usuario;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UsuarioService {

    private WebClient webClient;

    public UsuarioService() {
        this.webClient =
                WebClient.builder().baseUrl("http://localhost:8080/usuario").build();
    }

    private static String uri = "";

    public Usuario localizar(int id) {
        Mono<Usuario> monoUsuario = webClient.method(HttpMethod.GET)
                .uri(uri + "/" + id)
                .retrieve()
                .bodyToMono(Usuario.class);
        return monoUsuario.block();
    }

    public Usuario localizarLogin(String login) {
        Mono<Usuario> monoUsuario = webClient.method(HttpMethod.GET)
                .uri(uri + "/login/" + login)
                .retrieve()
                .bodyToMono(Usuario.class);
        return monoUsuario.block();
    }


    public List<Usuario> pesquisar() {
        Mono<List<Usuario>> monoListUsuario = webClient.method(HttpMethod.GET)
                .uri(uri)
                .retrieve()
                .bodyToFlux(Usuario.class)
                .collectList();
        return monoListUsuario.block();
    }

    public Usuario salvar(Usuario Usuario) {
        Mono<Usuario> monoUsuario = webClient.method(HttpMethod.POST)
                .uri(uri)
                .body(BodyInserters.fromValue(Usuario))
                .retrieve()
                .bodyToMono(Usuario.class);
        return monoUsuario.block();
    }

    public void excluir(int id) {
        webClient.get()
                .uri(uri + "/excluir/" + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
