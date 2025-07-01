package com.info.emailConsumer.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Mensagem {
    private Integer id;
    private String destinatario;
    private String remetente;
    private String assunto;
    private String mensagem;
    private LocalDate data;

    public String getDataFormatada(){
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(data);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
