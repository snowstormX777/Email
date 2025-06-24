package com.info.emailApi.controller;

import com.info.emailApi.model.Mensagem;
import com.info.emailApi.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mensagem")
public class MensagemController {
    @Autowired
    private MensagemService mensagemService;

    @GetMapping("/{id}")
    public ResponseEntity<Mensagem> localizar(@PathVariable int id) {
        return ResponseEntity.ok(mensagemService.localizar(id));
    }

    @GetMapping()
    public ResponseEntity<List<Mensagem>> pesquisa() {
        return ResponseEntity.ok(mensagemService.pesquisar());
    }

    @GetMapping("/destinatario/{destinatario}")
    public ResponseEntity<List<Mensagem>> pesquisaDestinatario(@PathVariable String destinatario) {
        return ResponseEntity.ok(mensagemService.pesquisarDestinatario(destinatario));
    }

    @GetMapping("/remetente/{remetente}")
    public ResponseEntity<List<Mensagem>> pesquisaRemetente(@PathVariable String remetente) {
        return ResponseEntity.ok(mensagemService.pesquisarDestinatario(remetente));
    }

    @PostMapping()
    public ResponseEntity<Mensagem> salvar(@RequestBody Mensagem mensagem) {
        return ResponseEntity.ok(mensagemService.salvar(mensagem));
    }

    @GetMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@PathVariable int id) {
        try {
            mensagemService.excluir(id);
            return ResponseEntity.ok("Mensagem " + id + " excluída!");
        } catch (Exception e) {
            return ResponseEntity.ok("Erro ao excluir a Mensagem " + id +
                    ": " + e.getMessage());
        }
    }
}
