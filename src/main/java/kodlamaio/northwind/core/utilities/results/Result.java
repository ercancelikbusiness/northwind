package kodlamaio.northwind.core.utilities.results;

public class Result {
	
	private boolean success;
	private String message;
	
	
	//mesaj döndürmek istemeyebiliriz sadece true false döndürelim yeterli diyebiliriz bunun için cons oluşturcaz
	public Result(boolean success)
	{
		this.success = success;
	}
	
	public Result(boolean success , String message)
	{
		this(success); // eğer 2 paremetreli cons cagırılırsa bu alana girecek ve gönderilen succes bu satırdaki
		//this'in içine gircek  this demek bu class demek yani bu classın nesnesi(örneği) görevi goruyorx
		//aslında this.success=success; de yazılabilirdi ama bu metod çağırılırsa aynı anda tek paremetreli cons'da calıstırılmış
		//oldu suanda
		//kısaca dataresult sınıfında super baseyi calıstırıyordu this ise bu sınıfı calıstıyor öyle düşün yani this bu sınıfın
		//tek paremetreli cons'una gitti çünkü this parentezinin içine tek paremetre yazdık bu yüzden tek paremetreli cons bulup içine
		//succes attı
		this.message=message;
	}

	
	
	public boolean isSuccess() // getter yapıyoruz aslında get yerine is de yazılabiliyormuş set işlemleri cons'da yapılıyo
	{
		return this.success;
	}
	public String getMessage()
	{
		return this.message +"- benCalistim_Result'daki get message";
	}
	

}
//bütün projelerimizde kullanacağımız ortak kodlar core katmanında olur sadece northwind için düşünme
//burada bu işlem başarılımı değilmi ve mesaj bilgisi
