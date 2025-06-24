package com.info.emailApi.repository;

import com.info.emailApi.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
    List<Mensagem> findAllByDestinatario(String destinatario);
    List<Mensagem> findAllByRemetente(String remetente);
}
