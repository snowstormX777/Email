package com.info.emailConsumer.service;

import com.info.emailConsumer.model.Mensagem;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MensagemService {
    private WebClient webClient;

    public MensagemService() {
        this.webClient =
                WebClient.builder().baseUrl("http://localhost:8080/mensagem").build();
    }

    private static String uri = "";

    public Mensagem localizar(int id) {
        Mono<Mensagem> monoMensagem = webClient.method(HttpMethod.GET)
                .uri(uri + "/" + id)
                .retrieve()
                .bodyToMono(Mensagem.class);
        return monoMensagem.block();
    }


    public List<Mensagem> pesquisar() {
        Mono<List<Mensagem>> monoListMensagem = webClient.method(HttpMethod.GET)
                .uri(uri)
                .retrieve()
                .bodyToFlux(Mensagem.class)
                .collectList();
        return monoListMensagem.block();
    }

    public List<Mensagem> pesquisarDestinatario(String destinatario){
        Mono<List<Mensagem>> monoListMensagem = webClient.method(HttpMethod.GET)
                .uri(uri + "/destinatario/" + destinatario)
                .retrieve()
                .bodyToFlux(Mensagem.class)
                .collectList();
        return monoListMensagem.block();
    }

    public List<Mensagem> pesquisarRemetente(String remetente){
        Mono<List<Mensagem>> monoListMensagem = webClient.method(HttpMethod.GET)
                .uri(uri + "/remetente/" + remetente)
                .retrieve()
                .bodyToFlux(Mensagem.class)
                .collectList();
        return monoListMensagem.block();
    }

    public Mensagem salvar(Mensagem Mensagem) {
        Mono<Mensagem> monoMensagem = webClient.method(HttpMethod.POST)
                .uri(uri)
                .body(BodyInserters.fromValue(Mensagem))
                .retrieve()
                .bodyToMono(Mensagem.class);
        return monoMensagem.block();
    }

    public void excluir(int id) {
        webClient.get()
                .uri(uri + "/excluir/" + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
