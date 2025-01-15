package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Category;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product,Integer>{
	
	//aşağıdaki  metodları biz yazmışız gibi gözükebilir ama  Spring Data JPA bu metod isimlerine göre SQL sorgularını otomatik olarak
	//türetir. mesela List<Product> getByProductNameOrCategoryId(String productName, int categoryId); yazdığımızda 
	/*
	 * getBy...Or...: Burada Or ifadesi, productName veya categoryId alanlarından herhangi biri sağlanmışsa 
	 * bu koşullara uyan sonuçları döndüreceği anlamına gelir.
	 * Bu metodun kullanılmasıyla Spring Data JPA, aşağıdaki gibi bir sorgu oluşturur:
	 * SELECT * FROM products WHERE product_name = ? OR category_id = ?
	 * Metod ismini belirli bir kurala göre oluşturduğunuzda, Spring Data JPA bu isme uygun bir sorgu oluşturur ve çalıştırır.

	 */
	
	//Products category ile category_id üzerinden ilişkilendiriliyor. Dao'dada category diyince categoryId yi bilecek program
	
	// Ürün ismine göre sorgulama
    Product getByProductName(String productName);

    // Ürün ismi ve kategoriye göre sorgulama
    Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);
    //alt tire olma nedeni  format kuralıyla alakalı burda  JPA burda Sql sorgusu oluşturuyor ve
    //metot türü zaten Product ordan productName'yi alıyor birde Category sınııfndaki categoryId yi almasını istiyoruz aslında.

    // Ürün ismi veya kategoriye göre sorgulama
    //üstteki gibi güncellemeyi  alttaki içinde yapmak zorunda kalabiliriz şuanlık düzeltmedim
    List<Product> getByProductNameOrCategory(String productName, Category category);

    // Belirtilen kategorilerdeki ürünleri getirme
    List<Product> getByCategoryIn(List<Category> categories);

    // Ürün ismi belirli bir stringi içeriyorsa sorgulama
    List<Product> getByProductNameContains(String productName);

    // Ürün ismi belirli bir string ile başlıyorsa sorgulama
    List<Product> getByProductNameStartsWith(String productName);

    // Ürün ismi ve kategoriye göre özel bir JPQL sorgusu
    @Query("From Product where productName=:productName and category.id=:categoryId")
    List<Product> getByNameAndCategory(String productName, int categoryId);
    
    //alttakini yapay zekaya sorarak her defasında detaylı öğren kısaca ProductwithcatDto sınıfının constructorunu kullanıyor
    // c Category yani veritabanındaki categories ' i temsil ederek c.products Category sınıfındaki products' ı p ise  her bir product ' ı temsil eder yani sınıfı
    
    @Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id,p.productName,c.categoryName) From  Category c Inner Join c.products p")
    List<ProductWithCategoryDto> getProductWithCategoryDetails();
	
	
	
	
	//burayi spring ile calıstıgımız için yaptık Jpa nın hipernate implemantasyonuyla
	//Bu ProductDao arayüzü, Product entity'si için veri erişim işlemlerini gerçekleştirmek üzere Spring Data JPA'nın 
	//sağladığı altyapıyı kullanır.
	//extends JpaRepository<Product, Integer>: JpaRepository arayüzünü genişleterek, Product entity'si için 
	//temel CRUD (Create, Read, Update, Delete) işlemlerini otomatik olarak sağlar. 
	//Burada, Product entity'si için birincil anahtarın tipi Integer olarak belirtilmiştir.
	
	/*
	Bu arayüz, JpaRepository'den kalıtım alarak Product varlığı için CRUD (Create, Read, Update, Delete) işlemlerini ve daha fazlasını sağlayan 
	metotları otomatik olarak sağlar. Bu sayede, temel veri erişim işlemleri için özel kod yazmanız gerekmez.
	 */
	
	/*
	 Genel Amaç
Veri Erişim Katmanı: ProductDao arayüzü, Product entity'si için veri erişim işlemlerini soyutlar. Böylece, veri erişim işlemleri 
(veritabanı işlemleri) için herhangi bir SQL sorgusu yazmak zorunda kalmazsınız.
Spring Data JPA Entegrasyonu: Bu arayüz, Spring Data JPA'nın sağladığı hazır metotlar sayesinde, 
veri erişim işlemlerini hızlandırır ve kod tekrarını azaltır.
Basit ve Temiz Kod: Bu yapı sayesinde, veri erişim katmanında daha temiz ve anlaşılır bir kod yazılır. 
Geliştiriciler sadece ProductDao arayüzünü kullanarak veri erişim işlemlerini gerçekleştirebilirler.
Özet
Bu ProductDao arayüzü, Product entity'si için veri erişim işlemlerini kolaylaştırmak üzere Spring Data JPA'nın sağladığı 
hazır metotları kullanır. Bu sayede, uygulamanızda veri erişim işlemleri için daha az kod yazmanız ve veri erişim katmanını 
daha temiz tutmanız sağlanır.
	 */
	
	
	

}
