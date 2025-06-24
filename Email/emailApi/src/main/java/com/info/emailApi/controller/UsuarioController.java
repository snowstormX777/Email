package com.info.emailApi.controller;

import com.info.emailApi.model.Usuario;
import com.info.emailApi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> localizar(@PathVariable int id) {
        return ResponseEntity.ok(usuarioService.localizar(id));
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<Usuario> localizarLogin(@PathVariable String login){
        return ResponseEntity.ok(usuarioService.localizarLogin(login));
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> pesquisa() {
        return ResponseEntity.ok(usuarioService.pesquisar());
    }

    @PostMapping()
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvar(usuario));
    }

    @GetMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@PathVariable int id) {
        try {
            usuarioService.excluir(id);
            return ResponseEntity.ok("Usuario " + id + " excluído!");
        } catch (Exception e) {
            return ResponseEntity.ok("Erro ao excluir o Usuario " + id +
                    ": " + e.getMessage());
        }
    }
}
