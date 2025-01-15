package kodlamaio.northwind.business.abstracts;

import java.util.List;





import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Category;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductService {
	DataResult<List<Product>> getAll(); 
	DataResult<List<Product>> getall(int pageNo, int pageSize);
	DataResult<List<Product>> getAllSorted(); 
	
		
	Result add(Product product);  // türü Result olan  add adında bir metoddur.
	
	DataResult<Product> getByProductName(String productName);
	
    //mesela alttaki ve üsttekinde List yok öğrenmek için yaptık  Prductname ve categoryıd ikisinede uyan ürünü getircek liste deseydik
	// uyanların hepsini getirirdi
	DataResult<Product> getByProductNameAndCategory(String productName, int categoryId); //duruma göre categoryId olanları sadece category yap
	
	DataResult<List<Product>> getByProductNameOrCategory(String productName, Category category);
	
	DataResult<List<Product>> getByCategoryIn(List<Category> categories); // şu şu kategori ıdye sahip olanlar
	
	DataResult<List<Product>> getByProductNameContains(String productName);
	
	DataResult<List<Product>> getByProductNameStartsWith(String productName); //bu isimle başlayanlar
	
	
	DataResult<List<Product>> getByNameAndCategory(String productName,int categoryId);
	 DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails();
	
	
	

}


/*List<Product> getAll();: Bu metodun amacı, veritabanındaki tüm ürünleri almak ve bir liste olarak döndürmektir. 
  ProductService arayüzünü uygulayan sınıflar, bu metodu sağlayarak ürünleri almak için gerekli iş mantığını içermelidir.
 */
/*Genel Amaç
İş Mantığı Tanımlaması: ProductService arayüzü, ürünlerle ilgili işlemleri tanımlar. Bu, servis sınıflarının ürünlerle 
ilgili iş mantığını uygulamalarını sağlar.
Bağımsızlık ve Soyutlama: Bu arayüz, ProductManager gibi servis sınıflarının implementasyon detaylarından bağımsız olarak 
kullanılabilmesini sağlar. Bu, uygulamanın farklı bölümleri arasında gevşek bir bağ sağlar ve değişikliklerin daha kolay yapılmasını sağlar.
Yeniden Kullanılabilirlik: ProductService arayüzü, farklı implementasyonlar (örneğin, ProductManager) tarafından paylaşılabilir, 
bu da kodun yeniden kullanılabilirliğini artırır ve farklı uygulama senaryolarına uyum sağlar.
Özet
ProductService arayüzü, ürünlerle ilgili iş mantığını tanımlar ve ürünleri almak için gerekli metotları içerir. Servis sınıfları
 bu arayüzü uygulayarak iş mantığını sağlar ve veri erişim işlemleri ile iş kurallarını ayırır. Bu yapı, uygulamanın modüler ve yönetilebilir olmasını sağlar.
*/