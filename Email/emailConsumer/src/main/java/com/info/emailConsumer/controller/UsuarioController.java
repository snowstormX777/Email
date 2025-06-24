package com.info.emailConsumer.controller;

import com.info.emailConsumer.model.Mensagem;
import com.info.emailConsumer.model.Usuario;
import com.info.emailConsumer.service.MensagemService;
import com.info.emailConsumer.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    MensagemService mensagemService;

    @GetMapping("/admin")
    public String listarUsuario(Model model, HttpSession session) {
        session.removeAttribute("idUsuario");
        model.addAttribute("listusuario",  usuarioService.pesquisar());
        return "admin";
    }

    @GetMapping("/novo")
    public String novoUsuario(Model model){
        model.addAttribute("usuario", new Usuario());
        return "newuser";
    }

    @GetMapping("")
    public String Login(Model model, HttpSession session) {
        model.addAttribute("usuario", new Usuario());
        if (session.getAttribute("erro") != null) {
            model.addAttribute("erro", (String) session.getAttribute("erro"));
            session.removeAttribute("erro");
        }
        return "login";
    }

    @PostMapping("/login")
    public String logarUsuario(@ModelAttribute("usuario") Usuario usuario, HttpSession session, Model model) {
        if (usuario.getLogin().equals("admin") && usuario.getSenha().equals("senha")) {
            return "redirect:/usuario/admin";
        }
        Usuario usuarioEncontrado = usuarioService.localizarLogin(usuario.getLogin());
        if (usuarioEncontrado != null && usuario.getSenha().equals(usuarioEncontrado.getSenha())) {
            session.setAttribute("idUsuarioLogado", usuarioEncontrado.getId());
            return "redirect:/mensagem";
        } else {
            session.setAttribute("erro", "Usuario ou senha incorretos");
            return "redirect:/usuario";
        }
    }

    @GetMapping("/visualizar/{id}")
    public String visualizaUsuario(@PathVariable int id, Model model, HttpSession session) {
        model.addAttribute("usuario", usuarioService.localizar(id));
        return "";
    }

    @PostMapping("/salvar")
    public String salvaUsuario(@ModelAttribute("usuario") Usuario usuario, HttpSession session) {
        //usuario.setId(session.getAttribute("idUsuario") == null ? 0 : (int) session.getAttribute("idUsuario"));
        usuarioService.salvar(usuario);
        return "redirect:/usuario/admin";
    }

    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable int id, HttpSession session) {
        if(mensagemService.pesquisarDestinatario(usuarioService.localizar(id).getLogin()).isEmpty()&&mensagemService.pesquisarRemetente(usuarioService.localizar(id).getLogin()).isEmpty()){
            usuarioService.excluir(id);
            return "redirect:/usuario/admin";
        }
        else{
            session.setAttribute("erro", "O usuário já está ativo");
            return "redirect:/usuario/admin";
        }
    }
}
