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

import java.time.LocalDate;

@Controller
@RequestMapping("/mensagem")
public class MensagemController {
    @Autowired
    private MensagemService mensagemService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public String listarMensagem(Model model, HttpSession session) {
        session.removeAttribute("idMensagem");
        if (session.getAttribute("erro") != null) {
            model.addAttribute("erro", (String) session.getAttribute("erro"));
            session.removeAttribute("erro");
        }
        Usuario usuario = usuarioService.localizar((int)session.getAttribute("idUsuarioLogado"));
        model.addAttribute("listamensagem", mensagemService.pesquisarDestinatario(usuario.getLogin()));
        return "email";
    }

    @GetMapping("/novo")
    public String novaMensagem(Model model){
        model.addAttribute("mensagem", new Mensagem());
        return "enviar";
    }

    @GetMapping("/visualizar/{id}")
    public String visualizaMensagem(@PathVariable int id, Model model, HttpSession session){
        model.addAttribute("mensagem", mensagemService.localizar(id));
        return "visualiza-mensagem";
    }

    @PostMapping("/salvar")
    public String salvaMensagem(@ModelAttribute("mensagem") Mensagem mensagem, HttpSession session){
        if(usuarioService.localizarLogin(mensagem.getDestinatario())!=null){
            mensagem.setRemetente(usuarioService.localizar((int)session.getAttribute("idUsuarioLogado")).getLogin());
            mensagem.setData(LocalDate.now());
            mensagemService.salvar(mensagem);
            return "redirect:/mensagem";
        } else {
            session.setAttribute("erro", "Destinatario não existe");
            return "redirect:/mensagem";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluirMensagem(@PathVariable int id){
        mensagemService.excluir(id);
        return "redirect:/mensagem";
    }
}
