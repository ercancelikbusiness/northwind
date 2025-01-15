package kodlamaio.northwind.entities.concretes;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //bunlar böyle sınıfın üstüne yazılır
@NoArgsConstructor
@Table(name="categories")  // @Entity ile işaretlenen sınıfın hangi veritabanı tablosuna karşılık geldiğini belirtir.
@Entity //Bu anotasyon sınıfın bir veritabanı varlığı olduğunu belirtir. Bu sayede JPA, bu sınıfın örneklerini veritabanı işlemleri 
//için kullanabilir.
//@Column anotasyonu ile sınıfın alanlarını tablo sütunlarına eşleyebilirsiniz.
//Bu anotasyonlar sayesinde, Java sınıflarınızı veritabanı tablolarına 
//kolayca eşleyebilir ve JPA'nın sunduğu ORM (Object-Relational Mapping) özelliklerinden faydalanabilirsiniz.

@JsonIgnoreProperties({"hibernateLazyInitializer","handler","products"})
public class Category {
	
	@Id
	@Column(name="category_id")
	private int categoryId;
	@Column(name="category_name")
	private String categoryName;
	
	@OneToMany(mappedBy  = "category") //Category ve Product adında iki varlık sınıfımız var. Her Category birden fazla Product içerebilir.
	private List<Product> products;

}
	