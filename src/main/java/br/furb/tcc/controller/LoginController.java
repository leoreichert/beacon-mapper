package br.furb.tcc.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.furb.tcc.model.Usuario;
import br.furb.tcc.repository.UsuarioRepository;
import br.furb.tcc.util.Utils;

@Controller
public class LoginController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping("login")
	public String loginForm(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuarioForm", usuario);
		return "login";
	}

	@RequestMapping("logout")
	public String logout(Model model,final RedirectAttributes redirectAttributes, HttpSession session) {
		session.removeAttribute("usuarioLogado");
		redirectAttributes.addFlashAttribute("logout", "Saiu!");
		return "redirect:login";
	}

	@RequestMapping("/")
	public String home(Model model) {
		return "redirect:login";
	}

	@RequestMapping(value = "/efetuaLogin", method = RequestMethod.POST)
	public String efetuaLogin(@ModelAttribute Usuario usuario, Model model, final RedirectAttributes redirectAttributes, HttpSession session) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(usuario.getUsername());
		if (usuarioOptional.isPresent()) {
			if ((usuario.getPassword() != null) && (!usuario.getPassword().isEmpty())) {
				if (!usuarioOptional.get().getPassword().equals(Utils.toSha256(usuario.getPassword()))) {
					redirectAttributes.addFlashAttribute("error", "Senha inválida!");
					return "redirect:login";
				}
				
				session.setAttribute("usuarioLogado", usuario);
				return "redirect:/beacons";
			} else {
				redirectAttributes.addFlashAttribute("error", "Senha inválida!");
				return "redirect:login";
			}
		}
		
		redirectAttributes.addFlashAttribute("error", "Usuário inválido!");
		return "redirect:login";
	}
}
