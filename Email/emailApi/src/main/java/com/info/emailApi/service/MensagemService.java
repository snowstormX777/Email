package com.info.emailApi.service;

import com.info.emailApi.model.Mensagem;
import com.info.emailApi.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensagemService {
    @Autowired
    MensagemRepository mensagemRepository;

    public Mensagem localizar(int id){
        return mensagemRepository.findById(id).orElse(null);
    }

    public List<Mensagem> pesquisar(){
        return mensagemRepository.findAll();
    }

    public List<Mensagem> pesquisarDestinatario(String destinatario){
        return mensagemRepository.findAllByDestinatario(destinatario);
    }

    public List<Mensagem> pesquisarRemetente(String remetente){
        return mensagemRepository.findAllByDestinatario(remetente);
    }

    public Mensagem salvar(Mensagem mensagem){
        return mensagemRepository.save(mensagem);
    }

    public void excluir(int id){
        mensagemRepository.deleteById(id);
    }
}
