package br.furb.tcc.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.furb.tcc.model.Usuario;
import br.furb.tcc.repository.UsuarioRepository;
import br.furb.tcc.util.Utils;
import br.furb.tcc.validator.UserFormValidator;

@Controller
public class UserController {

	@Autowired
	UserFormValidator userFormValidator;
	
	@InitBinder("userForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}

	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showAllUsers(Model model) {

		model.addAttribute("users", usuarioRepository.findAll());
		return "users/list";

	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated Usuario user,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "users/userform";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			if(user.isNew()){
				redirectAttributes.addFlashAttribute("msg", "Usuário adicionado com sucesso!");
			}else{
				redirectAttributes.addFlashAttribute("msg", "Usuário atualziado com sucesso!");
			}
			
			if (user.isNew())
				user.setPassword(Utils.toSha256(user.getPassword()));
			
			usuarioRepository.save(user);
			
			return "redirect:/users/" + user.getId();
		}

	}

	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		Usuario user = new Usuario();
		model.addAttribute("userForm", user);
		populateDefaultModel(model);
		return "users/userform";
	}

	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") long id, Model model) {
		Usuario user = usuarioRepository.findOne(id);
		model.addAttribute("userForm", user);
		populateDefaultModel(model);
		return "users/userform";
	}

	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") long id, final RedirectAttributes redirectAttributes) {
		usuarioRepository.delete(id);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Usuário deletado!");
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") long id, Model model) {
		Usuario user = usuarioRepository.findOne(id);
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Usuário não encontrado!");
		}
		model.addAttribute("user", user);

		return "users/show";

	}

	private void populateDefaultModel(Model model) {
		Map<String, String> estados = new LinkedHashMap<String, String>();
		estados.put("A", "Ativo");
		estados.put("I", "Inativo");
		model.addAttribute("estadoList", estados);

	}

}