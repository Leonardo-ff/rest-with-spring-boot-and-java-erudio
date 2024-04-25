package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.erudio.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//aqui no JPSQL o User é um objeto, e não a tabela user
	@Query("SELECT u FROM User u WHERE u.userName =:userName") 
	User findByUserName(@Param("userName") String userName);
}
