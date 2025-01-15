package kodlamaio.northwind.business.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.dataAccess.UserDao;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;

@Service
public class UserManager implements UserService{

	
	private UserDao userDao;
	
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user); // burdaki userDao değişkenine UserDao nesnesi oluşturmadan nasıl kullanıyoruz dersen
		//spring Framework sayesinde 1 adet cons yazınca otomatik nesne oluşuyor ve biz userDao değişkeni ile UserDao sınıfına ulaşıyoruz
		//buna gevşek bağımlılık deniyor zaten
		return  new SuccessResult("kullanıcı eklendi");
				
	}

	@Override
	public DataResult<User> findByEmail(String email) {
		return new SuccessDataResult<User>(this.userDao.findByEmail(email), "kullanıcı bulundu");
	}

}  
