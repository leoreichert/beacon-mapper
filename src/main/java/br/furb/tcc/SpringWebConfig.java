package br.furb.tcc;

import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.furb.tcc.controller.AutorizadorInterceptor;
import br.furb.tcc.model.Usuario;
import br.furb.tcc.repository.UsuarioRepository;
import br.furb.tcc.util.Utils;

@Configuration
@EnableWebMvc
public class SpringWebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsps/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		rb.setBasenames(new String[] { "messages/validation" });
		return rb;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/UpdateTemplate").allowedOrigins("*");
				registry.addMapping("/AccessBeacon").allowedOrigins("*");
				registry.addMapping("/GetTemplate").allowedOrigins("*");
			}
		};
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AutorizadorInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public InitializingBean sendDatabase() {
		return () -> {
			Optional<Usuario> user = usuarioRepository.findByUsername("admin");
			if (!user.isPresent()) {
				Usuario usuario = new Usuario();
				usuario.setUsername("admin");
				usuario.setPassword(Utils.toSha256("0071278457"));
				usuarioRepository.save(usuario);
			}
		};
	}
}