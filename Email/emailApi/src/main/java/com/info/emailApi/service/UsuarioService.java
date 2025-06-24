package com.info.emailApi.service;

import com.info.emailApi.model.Usuario;
import com.info.emailApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario localizar(int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario localizarLogin(String login){
        return usuarioRepository.findByLogin(login);
    }

    public List<Usuario> pesquisar(){
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void excluir(int id){
        usuarioRepository.deleteById(id);
    }
}
