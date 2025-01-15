package kodlamaio.northwind.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//bu sınıfın core katmanında olması sadece nortwind projesinde değil kullanıcı girişleri her zaman kullanırız ilerde başka projedede kullanabiliriz ama illa böyle olcak diye kaide yok belki bir projeye özgü bir  kullanıcı sistemi yaratırsın ozaman core'da olmayabilir


@Entity
@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor

public class User {

	@Id // private int id nin birincil anahtar olacağını söyle her users için bir id olacak demek
	@GeneratedValue(strategy = GenerationType.IDENTITY) //ile, id değeri otomatik olarak veritabanı tarafından oluşturulacak ve her yeni kayıt için artırılacak (otomatik artan bir değer olacak). Bu, genellikle veritabanı tablolarında kullanılan yaygın bir yapılandırmadır.
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	@Email // önce pomxml e validation depency ekledik sonra bunu yazdık import edebildik
	@NotBlank // alttaki ve bu  emaili falan yazarken belli kurallara uyması demek
	@NotNull
	private String email;
	
	@Column(name="password")
	@NotBlank
	@NotNull
	private String password;
	
	
}
