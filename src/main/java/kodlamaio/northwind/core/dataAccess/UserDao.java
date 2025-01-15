package kodlamaio.northwind.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.northwind.core.entities.User;

public interface UserDao extends JpaRepository<User,Integer>{
	//kullanıcı ekleme hazır kullanıcıya ulaşmak için mailiyle ulaşacağız
	
	User findByEmail(String email);
	
	

}
