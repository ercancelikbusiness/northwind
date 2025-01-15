package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Category;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@RestController //yani antdroid ve ios'da sana iletişimde bulunabilir herkes beni tanısına yarıyan birşey sanırım
//Spring Boot, @RestController anotasyonu sayesinde, kontrolcü metodlarının dönüş değerlerini otomatik olarak HTTP yanıtlarına çevirir. 
//Eğer dönüş değeri bir nesne ise, bu nesneyi JSON formatına dönüştürür ve 
//HTTP yanıtı olarak gönderir. Bu işlem Jackson kütüphanesi tarafından gerçekleştirilir.
//mesela /getall kısmında biz sysout yapmıyoruz bunun zincirleme devamındaki hiçbir sınıftada sysout yok ama postmanda vb çıktılar oluşuyor
//bunlar bu anatasyon sayesindedir
@RequestMapping("api/products") // bura domainin sonunu temsil ediyormuş  aşağıdakiler bunun devamına eklenicek
@CrossOrigin // farklı istemcilere erişimi açmak veya sınırlandırmak için kullanılan anatosyonlar mesela react projesine erişim izni vb

public class ProductsController {
	
	//bu api görevi görcek dış dünyayla sistemimizin iletişim kurduğu yer
	//kodlama.io/api/products böyle bir adres üzerinden dış dünyadan bir istek olursa ProductsController onu karşılıcak
	
	private ProductService productService; //bunun üstüne @Autowired yazsak constructor yazmasakda olur  ama birden fazla servis kullanacağın zaman hepsine yazmak gerek
	
	//@Autowired //bu şuanda alttaki metodun  paremetresini hallediyor yani bu paremetreyi(productService) yi  imp. eden sınıfı
	//yani ProductManager'e gidiyor  newliyor onu newlerken onun consundaki daoyuda newliyor nesneyi alıp productSerice yerleştiriyor.
	public ProductsController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping("/getall")
	//asagıdaki link gibi birşey
	public DataResult<List<Product>> getAll() 
	//DataResult<List<Product>>: Bu, metodun döndüreceği (return) değerin türüdür. Bu durumda, metod DataResult türünde bir değer döndürecektir 
	//ve bu DataResult, List<Product> türünde bir veri içerecektir.
	{
		 return this.productService.getAll(); //burdaki metod productService ye götürüyor bizi  ama Pr.service interface
		 //yani o sınıfa götürse bile onuda setleyen ProductManagerdır yani ordan oraya ordanda managera yönlendircek asıl olak managerda
	}
	
	/*İşlem Adımları
getAll metodu çağrıldığında , this.productDao.findAll() kullanılarak tüm Product kayıtları veritabanından çekilir.
Çekilen bu veriler, SuccessDataResult nesnesi içinde data olarak saklanır.
SuccessDataResult nesnesi, success değerini true yapar ve mesaj olarak "Data Listelendi" ayarlar.
getAll metodu, bu SuccessDataResult nesnesini döndürür.
Postman'deki Çıktının Anlamı
Postman'de http://localhost:8080/api/products/getall endpoint'ine istek gönderdiğinizde, getAll metodu çalışır ve JSON formatında
 bir SuccessDataResult nesnesi döner. Bu nesne, veriyi (data), başarılı olduğunu (success: true), ve mesajı (message: "Data Listelendi")
  içerir.
*/
	
	@PostMapping("/add") // biz birşey gönderirken bu anotasyon kullanılır
	public Result add(@RequestBody Product product) { // burdaki RequestBody bizim hazır body'i doldurup ekleme yapcağımızı gösterir
		//mesela postman'daki body kısmını doldurunca ekleme yapıyordu bu  body ise product sınıfındaki  id unitprice falan
		//o şablonu oluşturcaksın sonra değerleri rastgele verebilirsin 
        return this.productService.add(product);
    }
	
	//http://localhost:8080/api/products/getall şuanda bu adresle ulaşabiliyoruz mesela
	
	
	@GetMapping("/getByProductName")
	public DataResult<Product> getByProductName(@RequestParam String productName) 	//@reuestparam kullanıcı  alınan istekde  paremetre olarak productName göndericek demek aynı requestbody'deki gibi

	{
		return this.productService.getByProductName(productName);
	}
	
	@GetMapping("/getByProductNameAndCategory")
	public DataResult<Product> getByProductNameAndCategory(@RequestParam String productName, @RequestParam int categoryId)
	{
		return this.productService.getByProductNameAndCategory(productName, categoryId);
	}
	
	//alttaki  mesela postman params productName ' e "La" yazarsak içinde La  geçen ürümleri bulacak
	@GetMapping("/getByProductNameContains")
	public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName)
	{
		return this.productService.getByProductNameContains(productName);
	}
	

	@GetMapping("/getallByPage")
	public DataResult<List<Product>> getall(int pageNo, int pageSize)
	{
		return this.productService.getall(pageNo, pageSize);
	}
	
	@GetMapping("/getAllDesc")
	public DataResult<List<Product>> getAllSorted()  //  DESC a den a ya ESC A dan z ye gider burda calışınca producNamesinin ilk harfı
	// z ile başlayandan başlıcak
	{
		return this.productService.getAllSorted();
	}
	
	@GetMapping("/getProductWithCategoryDetails")
	
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() 
	
	{
		 return this.productService.getProductWithCategoryDetails();
	}
}


/*
Bu ProductsController sınıfı, Spring Boot uygulamanızda REST API uç noktalarını tanımlar ve dış dünya ile sisteminiz arasındaki 
etkileşimi yönetir. İşte bu sınıfın bileşenleri ve işlevleri:

Anotasyonlar
@RestController: Bu anotasyon, sınıfın bir RESTful web servis denetleyicisi olduğunu belirtir. Bu, Spring'in bu sınıfı 

HTTP isteklerini işleyen bir denetleyici olarak tanımasını sağlar ve yanıtları JSON formatında döndürür.
@RequestMapping("api/products"): Bu anotasyon, bu denetleyicinin HTTP isteklerini hangi yolla karşılayacağını belirler. 
Burada, api/products yolu, denetleyiciye yapılacak tüm istekleri kapsar.
Alanlar
private ProductService productService;: Bu alan, ürünlerle ilgili iş mantığını yönetmek için kullanılan ProductService 
bağımlılığını tutar.
Constructor
public ProductsController(ProductService productService): Bu yapıcı metod, ProductService bağımlılığını alır ve denetleyiciye 
enjekte eder. Spring, bu yapıcıyı kullanarak bağımlılığı otomatik olarak sağlar. @Autowired anotasyonu kullanılmadığı için,
bağımlılık enjeksiyonu yapıcı metod ile yapılır.
Metot
@GetMapping("/getall"): Bu anotasyon, getAll metodunun HTTP GET istekleri için /getall yolunu işlediğini belirtir. Yani, 
kodlama.io/api/products/getall adresine yapılan GET istekleri bu metod tarafından karşılanır.
public List<Product> getAll(): Bu metod, ProductService'in getAll metodunu çağırır ve tüm ürünleri bir liste olarak döndürür. 
Bu sayede, dış dünya bu API uç noktasına istek gönderdiğinde tüm ürünler alınabilir.
Genel Amaç
REST API Sağlama: ProductsController sınıfı, ürünlerle ilgili HTTP isteklerini karşılayan bir REST API sağlar. Bu API uç noktası, 
uygulamanızın dış dünyadan gelen isteklerle etkileşimde bulunmasını sağlar.
İş Mantığına Erişim: ProductService bağımlılığını kullanarak, iş mantığına erişir ve gerekli verileri alır. Bu, denetleyicinin 
iş mantığından bağımsız olarak veri sağlar.
Yol Yönetimi: HTTP isteklerinin hangi yollarla karşılanacağını ve hangi metotların çağrılacağını belirler.
Özet
ProductsController sınıfı, Spring Boot uygulamanızda RESTful API uç noktalarını tanımlar. ProductService'i kullanarak iş 
mantığına erişir ve @GetMapping anotasyonu ile belirlenen yolla HTTP GET isteklerini karşılar. Bu yapı, dış dünya ile sisteminiz
 arasındaki etkileşimi yönetir ve API'lerinizi düzenler.
*/