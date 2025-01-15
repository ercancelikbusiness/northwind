package kodlamaio.northwind.entities.concretes;

import jakarta.persistence.Column;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Bu Lombok anotasyonu, bu sınıf için getter ve setter yöntemlerini, toString(), equals(), ve hashCode() 
//gibi yaygın yöntemleri otomatik olarak oluşturur. yani lombok kurduysak get set vb yi sınıf içine yazmaya gerek yok
@AllArgsConstructor // full paremetreli consu lombok ile auto yaratmak için
@NoArgsConstructor// parametresiz cons için lombok anatasyonu sağdaki outlinenin product şeklindeki consları lombok'u temsil eder
@Entity //Bu sınıfın bir JPA entity (varlık) olduğunu belirtir. Yani, bu sınıfın örnekleri bir veritabanı tablosunda saklanabilir.
@Table(name="products")
// table postgredeki karışılık gelen halidir    javadaki hali ile postgredeki halini farklı yaptık java Product pg de products
public class Product {
	@Id  //Bu alanın bu entity'nin birincil anahtarı olduğunu belirtir.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Birincil anahtarın otomatik olarak oluşturulacağını belirtir.
	//veri eklendikce otomatik birincil anahtarı arttırıcak gibi birşey
	@Column(name="product_id") // @columnlar  veritabanında ( şuan postgre kullanıyoruz ) hangi alana karşılık geldiğini belirtir
	private int id;
	//postgredeki products tablosundaki karşılık gelen kısmını @ li olarak yazdık
	//@Column(name="category_id") Category sınıfı açıldı  bu sınıfla onu ilişkilendircez onda buna benzer kod var dublucate hatası alırız
	//private int categoryId;
	@Column(name="product_name")
	private String productName;
	@Column(name="unit_price")
	private double unitPrice;
	@Column(name="units_in_stock")
	private short unitsInStock;
	@Column(name="quantity_per_unit")
	private String quantityPerUnit;
	
	// şuan Product sınıfındayız çok olan üründür ona Many deriz az olan kategori ona one deriz öyle düşün yani bir kategoride
	//birden fazla ürün olur Products category ile category_id üzerinden ilişkilendiriliyor. Dao'dada category diyince 
	//categoryId yi bilecek program
	@ManyToOne() 
	@JoinColumn(name="category_id")
	private Category category;
	
	// aşağıdaki paremetresiz ve paremetreli 2 consu yorum satırı yapcam @allargsconstructor ve noargscons.
	//anotasyonu lomboktan yazacak bunlarıda
	// aynı @data anotasyonunun get setleri yapması gibi yani lombok sayesinde get set ve conslar aradan cıkıyor..
	/*
	public Product() //Bu, varsayılan yapıcıdır ve bir Product nesnesi oluşturmak için kullanılır.
	{
		
	}
	//paremetreli cons  Bu, tüm alanları başlatmak için kullanılır. Tüm alanları belirtilen değerlerle başlatır.
	
	public Product(int id, int categoryId, String productName, double unitPrice, short unitsInStock,
			String quantityPerUnit) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		ProductName = productName;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.quantityPerUnit = quantityPerUnit;
	}
	//Bu Product sınıfı, bir ürünün veritabanı tablosunda nasıl temsil edildiğini tanımlar. Spring Data JPA gibi araçlarla birlikte 
	//kullanıldığında, veritabanı işlemlerini (CRUD işlemleri) kolaylaştırır. Bu sınıf, products tablosundaki her bir satırı 
	//temsil eder ve bu tabloya veri ekleme, güncelleme, silme ve sorgulama işlemlerini yapmanızı sağlar.
	
*/
}
