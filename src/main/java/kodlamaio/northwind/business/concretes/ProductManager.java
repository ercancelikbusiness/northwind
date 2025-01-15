package kodlamaio.northwind.business.concretes;



import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Category;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@Service  // Bu anotasyon, sınıfın bir Spring hizmet sınıfı olduğunu belirtir. Spring, 
//bu sınıfı bir servis olarak işaretler ve otomatik olarak Spring konteynerine dahil eder.

public class ProductManager implements ProductService {

	private ProductDao productDao;
	//@Autowired  //consun karşılığı olan sınıfı bulup enjekte ediyormuş yani biz newlemiyormuşuz
	
	
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}
	@Override
	public DataResult<List<Product>> getAll() { //tür :  DataResult<List<Product>> komple bir tür gibi düşün
		
		return  new SuccessDataResult<List<Product>>(this.productDao.findAll(),"Data Listelendi");
		//yukardaki kod nihai olarak Result sınıfına gidiyor ve yolu üzerindeki sınıflardaki get metodlarına bakıyor
		//get metodlarındaki veriler en güncel halinde neyse postman' gibi çıktıların olduğu api programları vb  en son onu çıktı yapıyor
		//mesela en son message ve succes verisi Result'da get edilmiş bunu baz alıcak ve data en son DataResult sınıfında get edilmiş
		//resulttaki get kısmını silersek message ve succes bilgisi çıkmaz DataResult'daki  data get'i silersekde data çıkmaz
		//aslında olay şöyle data, message, succes fln bunların hepsi private olduğu için dışarıdan erişim get set ile oluyordu
		//sen get veya setleri silersen verilere erişimde olmaz  şunuda unutma ben Result sınıfındaki  message get metondaki
		//return kısmına + "benCalistim" yazdım kısaca private alanların nihai halinide belirliyor get. yani get ve setler özellikle
		//get metodları kodlar çalıştığında  yapılmak istenenin yapılmasını sağlar sonuçları gösteren budur yoksa burda
		//success  data ve message'ye bir veri yüklenmesi bir anlam ifade etmiyor bunların çıktı olması  ve dışarıya aktarılması
		//get metodları sayesindedir.. kısaca postmanda biz gettall urlsine gittiğimizde çıkan sonuclar fieldler ve içindeki verilerle
		//ilgili ama bu verilere erişmek için get metodlarının mevcut olması gerekiyor
		//yukarda yaptığı şu hani bizim genericsTanım projemizda generics leri öğrendikya
		//burda bir sınıfı newliyor ama nesne belirtmiyor nesne soyut oluşuyor  demekki amaç sadece o sınıftaki cons'u aktiv etmek
		//eğer bu sınıfın  metodlarını tek tek setleyecek olsaydı nesne yaratırdı ama demekki amaç tek satırda
		//cons'u aktive etmek ve succsdataresult sınıfına veri göndermek o yüzden nesne belirtmeden newlemiş
		//succesdataresult generic bir sınıf olduğundan türünü  Product türünde bir listedir demiş
		//this.productDao.findAll() bu ise productDao Jpayı taşıyordu bu sayede findAll  anahtarını çalıştırabildik
		//bu anahtar jpa Product sınıfı için  hazır kodlar oluşturduğu için findAll ile Product sınıfındaki tüm veriyi  bulacak
		// FindAll tüm Product varlıklarını (ürünleri) veritabanından çeker ve bir List<Product> döndürür.
        //tabi generic yapılarda öğrendiğimiz üzere SuccesDataResult newlenirken  Listproduct türünde olacak demişisiz
		//bu findall verileri   producttüründe bir liste olarak gidiyor.
				
		//yani olayı asıl yapan sınıf yine ProductManager oldu..diğerleri yardımcı görevli
		
		
	
	}
	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		
		return new SuccessResult("ürün eklendi"); 
		//newliyoruz çünkü consun çalışmasını istiyoruz çalışan cons tek paremetreli olan SuccesResult sınıfı consu olcak
		//oda supere yani Result'a true ve ürün eklendi  gönderecek yani Resultun 2 paremetreli olan consu calıscak
		//ve Resultun getleri sayesinde true ve ürün eklendi çıkacak
		//zaten metod türü Result diyerek Result döndüreceğğimizi söylüyoruz çünkü return new SuccessResult("ürün eklendi"); 
		//kodu nihai olarak Result'a gidicek ve ordaki Get'leri gösterecek sonu add'le biten url ye gidince yani postman'da
		//sonu add le biteni  gönderince ekrana Sucess: true message: ürün eklendi çıkıcak
	}
	@Override
	public DataResult<Product> getByProductName(String productName) {
	    return new SuccessDataResult<Product>(this.productDao.getByProductName(productName), "Data Listelendi");
	    //return new SuccessDataResult<Product> şu kısım return new SuccessDataResult<>şeklindede olabilir direk
	}

	@Override //servici implemente ettiğimiz için bu metod adı çok önemli değil içindeki return önemli
	//yani dao'dakilerle metod isimleri aynı olacak diye ezberleme servistekiyle bunu abc yapsanda olurdu ama daodaki metod isminde
	//Category sonunda Id olunca hata veriyordu ona gerek yok
	public DataResult<Product> getByProductNameAndCategory(String productName, int categoryId) {
		
	    return new SuccessDataResult<Product>(this.productDao.getByProductNameAndCategory_CategoryId(productName, categoryId), "Data Listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategory(String productName, Category category) {
	    return new SuccessDataResult<>(this.productDao.getByProductNameOrCategory(productName, category), "Data Listelendi");
	    
	}

	@Override
	public DataResult<List<Product>> getByCategoryIn(List<Category> categories) {
	    return new SuccessDataResult<List<Product>>(this.productDao.getByCategoryIn(categories), "Data Listelendi");
	}
	//return new SuccessDataResult<List<Product>>... bu kısım return new SuccessDataResult<>... bu şekilde olabilir gereksiz kod
    //oluyor sanırım genericsTanim sınıfındaki eğitimdede böyle yapılıyordu zaten

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
	    return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameContains(productName), "Data Listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
	    return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameStartsWith(productName), "Data Listelendi");
	}

	@Override
	public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
	    return new SuccessDataResult<List<Product>>(this.productDao.getByNameAndCategory(productName, categoryId), "Data Listelendi");
	}
	@Override
	public DataResult<List<Product>> getall(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, Sort.by("id")); //  sağ blok hazırdı biz oraya metod paremetrelerini koyduk
		//ekstra sort by id ekledim çünkü product sınıfımızda productId id olarak kayıtlanmış ve bu metodu postmande calıstırdıgımızda
		//pageno 1 yazınca Idsi 2 olanlardan başlıyordu onu düzeltmek için idye göre sırala dedik
		//pagono-1 yapma nedenimiz sayfalar indexi 0 dan başladıgı için biz postmana 1 dediğimizde 2. sayfayı gösteriyordu
		//dolayısıyla postmana 1 dediğimizde programa 0 olarak geçecek sıkıntı olmucak
		//bu arada ilk yazdıgımız getall metoduda id 2 den başlıyor  postmanda muhtemelen onada sortby id yapmalıyız
		return  new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent());
	}
	@Override
	public DataResult<List<Product>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.DESC,"productName");
		return  new SuccessDataResult<List<Product>>(this.productDao.findAll(sort),"başarılı");
	}
	@Override
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		return new SuccessDataResult<List<ProductWithCategoryDto>>(productDao.getProductWithCategoryDetails(),"Productwithcategorydto calıştı");
	}


}



//Bu ProductManager sınıfı, ProductService arayüzünü uygulayan 
//bir servis sınıfıdır ve iş mantığını yönetir. Şimdi, bu sınıfın bileşenlerini ve işlevlerini açıklayalım:
//private ProductDao productDao;: Bu alan, veri erişim işlemleri için kullanılan ProductDao arayüzüne referans tutar. 
//ProductDao, Product entity'si ile ilgili CRUD işlemlerini sağlar.

/*
Constructor
public ProductManager(ProductDao productDao): Bu, ProductManager sınıfının bir yapıcısıdır. 
Spring, bu yapıcıyı kullanarak ProductDao bağımlılığını enjekte eder. @Autowired anotasyonu, Spring'in bu bağımlılığı otomatik 
olarak sağlamasını sağlar, ancak yapıcı tabanlı enjekte etme (constructor injection) yöntemi ile bağımlılığı doğrudan sağlayabilirsiniz.
*/
/*Metot
@Override public List<Product> getAll(): Bu metod, ProductService arayüzündeki getAll metodunu uygulayan bir işlevdir. 
Bu metot, tüm ürünleri getirmek için ProductDao'nun findAll metodunu çağırır ve sonuçları döner. ProductDao derken
//ProductDao sınıfında Jpa falan yapmıştık hazır bir sistem yaratıyor bize sanırım o yüzden  ProductDao sınıfının nesnesini kullanarak
 yapıyoruz findAll() metodu hazır bir anahtar gibi birşey olmalı muhtemelen onuda Productdao sını sağlıyor bize jpa sayesinde diye düşündm
 */

/*Genel Amaç
İş Mantığı Yönetimi: ProductManager sınıfı, iş mantığını yönetir ve iş kurallarını uygular. Veritabanı erişim işlemleri için 
ProductDao arayüzünü kullanır ve veri erişim işlemlerini ProductService arayüzünden çağrılarak sağlar.
Bağımlılık Enjeksiyonu: Spring, bu sınıfa ProductDao bağımlılığını otomatik olarak sağlar. Bu, sınıfın bağımlılıklarını
 yönetmeyi ve bağımlılıkların oluşturulmasını basit hale getirir.
Veri Erişimi: ProductManager sınıfı, ProductDao aracılığıyla veritabanından veri alır ve iş mantığına göre işlenmiş veriyi döner.
Özet
ProductManager sınıfı, iş mantığını ve veri erişim işlemlerini yöneten bir servis sınıfıdır. ProductService arayüzünü uygulayarak, 
veri erişim işlemleri için ProductDao arayüzünü kullanır ve gerekli iş kurallarını uygular. Bu yapı, uygulamanızın iş mantığını veri 
erişiminden ayırarak daha düzenli ve yönetilebilir bir kod tabanı sağlar.
*/
