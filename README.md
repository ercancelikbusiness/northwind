```
## Detaylı Rehber 
Projenin tanıtımı ve kullanım talimatları için [Northwind Rehber PDF’sini indirin](https://github.com/ercancelikbusiness/northwind/raw/main/Northwind_Rehber.pdf).

1. Paket Yapısı
Proje şu şekilde organize edilmiş:

KopyalaDüzenle
src/main/java/kodlamaio/northwind/
├── api/controllers
├── business/abstracts
├── business/concretes
├── core/utilities/results
├── dataAccess/abstracts
├── entities/concretes
Her bir klasör veya paket belirli bir katmanı ifade eder ve projedeki sorumlulukları ayrıştırır.
________________________________________
2. Mimari Katmanlar
a. api/controllers (Controller Katmanı)
•	Amaç: Kullanıcıların (veya API istemcilerinin) uygulama ile etkileşim kurduğu uç noktaları (endpoints) barındırır.
•	İçerik:
o	ProductController: Ürünlerle ilgili işlemleri (ekleme, listeleme, güncelleme, silme) yönetir.
o	UserController: Kullanıcılarla ilgili işlemleri (kayıt, giriş) yönetir.
•	Örnek İşleyiş:
o	/api/products/add: Yeni bir ürün eklemek için kullanılır.
o	/api/users/register: Yeni bir kullanıcı kaydı oluşturur.
Controller katmanı iş mantığı içermez, yalnızca gelen istekleri alır ve yanıt verir.
b. business/abstracts (Service Interface Katmanı)
•	Amaç: İş mantığını tanımlayan arayüzler barındırır.
•	İçerik:
o	ProductService: Ürün işlemleri için metodların tanımlandığı arayüz.
o	UserService: Kullanıcı işlemleri için metodların tanımlandığı arayüz.
•	Örnek Metodlar:

List<Product> getAll();
void add(Product product);
•	Bu katman, farklı iş kurallarıyla çalışabilen esnek bir yapı sağlar. Somut sınıflar burada tanımlanan kuralları uygular.
c. business/concretes (Service Implementation Katmanı)
•	Amaç: İş mantığını içeren somut sınıflar barındırır.
•	İçerik:
o	ProductManager: ProductService arayüzünü uygulayarak ürün iş mantığını yönetir.
o	UserManager: UserService arayüzünü uygulayarak kullanıcı iş mantığını yönetir.
•	İşleyiş:
o	Veritabanı işlemleri için dataAccess katmanını kullanır.
o	İş kurallarını (örneğin, "Bir ürünün adı boş olamaz") burada uygular.
d. dataAccess/abstracts (Repository Katmanı)
•	Amaç: Veritabanı işlemleri için kullanılan arayüzler barındırır.
•	İçerik:
o	ProductDao: Spring Data JPA'nın sağladığı özelliklerle ürün verilerine erişim sağlar.
o	UserDao: Kullanıcı verilerine erişim sağlar.
•	Teknoloji: Bu proje Spring Data JPA kullanıyor, dolayısıyla SQL sorgularını manuel yazmak yerine JPA yöntemleriyle çalışıyor. Örneğin:
List<Product> findByCategory(String category);
e. entities/concretes (Entity Katmanı)
•	Amaç: Veritabanı tablolarını temsil eden sınıflar barındırır.
•	İçerik:
o	Product: Ürün tablosunu temsil eden sınıf.
o	User: Kullanıcı tablosunu temsil eden sınıf.
•	Notlar:
o	Bu sınıflar genellikle Spring JPA ile uyumlu olarak, @Entity, @Table, @Id gibi anotasyonlarla donatılır.
o	Örnek:
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
}
f. core/utilities/results (Yardımcı Yapılar)
•	Amaç: Projeye özel sonuç yapılarını (örneğin, başarılı/başarısız sonuçlar) içerir.
•	İçerik:
o	Result: İşlemlerin başarı veya başarısızlık durumunu belirtir.
o	DataResult<T>: İşlem sonucu dönen veriyi içerir.
o	SuccessResult, ErrorResult: İşlemin başarılı veya başarısız olduğunu belirtir.
•	Örnek Kullanım:
return new SuccessDataResult<>(products, "Products listed successfully");
________________________________________
3. Genel İş Akışı
Bir ürün ekleme işlemini örnek alalım:
1.	Controller (API):
o	ProductController üzerinden /api/products/add endpoint'ine bir istek gelir.
2.	Service:
o	ProductManager çağrılır ve iş kuralları burada uygulanır.
o	Örneğin: "Ürün adı boş olamaz" kuralı kontrol edilir.
3.	Repository:
o	ProductDao kullanılarak ürün verisi veritabanına kaydedilir.
4.	Entity:
o	Product sınıfı, veritabanındaki tabloyu temsil eder ve kaydedilen veri bu tabloya eklenir.
5.	Sonuç:
o	İşlem başarılıysa SuccessResult veya SuccessDataResult ile geri bildirim yapılır.
________________________________________
4. Kullanılan Teknolojiler
•	Spring Boot: Uygulama geliştirme ve yönetim altyapısı.
•	Spring Data JPA: Veritabanı işlemleri için ORM çözümü.
•	H2 Database (veya başka bir RDBMS): Uygulamanın veritabanı.
•	Lombok: Kod tekrarı azaltmak için getter/setter oluşturur.
________________________________________
5. Avantajlar
•	Katmanlı Mimari: Kodun düzenli ve sürdürülebilir olmasını sağlar.
•	Bağımlılık Ayrımı: İş mantığı, veritabanı işlemleri ve kullanıcı etkileşimleri birbirinden ayrılmıştır.
•	Test Edilebilirlik: Her katman bağımsız test edilebilir.
•	Genişletilebilirlik: Yeni özellikler kolayca eklenebilir.
________________________________________
Bu proje, Spring Boot'un standart bir katmanlı mimari düzenine göre yapılandırılmıştır ve ölçeklenebilir bir yapıya sahiptir. Bu yapı, hem yeni geliştiricilerin projeye hızlıca adapte olmasını sağlar hem de uzun vadede bakım maliyetini düşürür.



2.	
Projeyi bir IDE'de (örneğin Eclipse) açın.
3.	PostgreSQL veya uygun bir veritabanı ayarlayın ve application.properties dosyasını güncelleyin.
4.	Projeyi çalıştırmak için NorthwindApplication.java dosyasını çalıştırın.
🛠 Gereksinimler
•	Java 17
•	Spring Boot 3.x
•	PostgreSQL
📖 Kullanım
Ürün Eklemek
•	POST /api/products/add:
o	JSON örneği:
json
KopyalaDüzenle
{
  "name": "Ürün Adı",
  "price": 100.0,
  "category": "Kategori Adı"
}
Ürün Listelemek
•	GET /api/products/getall
📜 Lisans
Bu proje açık kaynaklıdır ve MIT Lisansı altında yayınlanmıştır.


