package br.com.erudio.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erudio.repositories.UserRepository;

@Service
public class UserServices implements UserDetailsService{

	private Logger logger = Logger.getLogger(UserServices.class.getName());
	
	// aqui usamos duas estrategias, construção de dependendias por field/ propriedade (autowired abaixo)
	@Autowired
	UserRepository repository;

	// e tambem por construtor
	//https://spring.io/blog/2007/07/11/setter-injection-versus-constructor-injection-and-the-use-of-required explica um pouco sobre
	// aqui tbm https://reflectoring.io/constructor-injection/
	public UserServices(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Finding one user by name "+ username +"!");
		
		var user = repository.findByUserName(username);
		
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username "+username+" not found!");
		}
	}
	
	
	
}
