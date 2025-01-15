package kodlamaio.northwind.core.utilities.results;

public class DataResult<T> extends Result{

	
	
	private T data;
	public DataResult(T data,boolean success, String message) { // basedeki 2 paremetreli consu hedef aldık bu metod ile
		super(success, message);
		this.data=data;
	}
	
	public DataResult(T data,boolean success) { 
		super(success);
		this.data=data;
	}

	
	 public T getData()
	 {
		 return this.data;
	 }
	 

	
	
}
//result'ın datalı hali yani işlem için başarılı ve mesaj bilgisi dısında birde şu datayı göndermek istiyorum dediğimiz result  türü var
//o yüzden bu sınıfı açtık
//mesaj ve succes bilgisi burdada kullanmak ve baseye veri göndermek  için extends edecez Result'da 2 adet cons vardı
//dateResult'u newlesek  Result'daki cons'lara paremetreleri nasıl göndercez bunun için lambaya tıklayıp ( extends lersek lamba cıkıyor)
//Resultdaki consları buraya uyarlıyor ( seçtiklerini uyarlar)
//public DataResult  şeklinde metod otomatik lambaya basınca kendi oluşuyor peki diceksinki 
//impelents de lambaya tıklayınca override yazısı cıkıp basedeki  metodla aynı isimde metod oluşuyordu burda neden Result değilde
//DataResult diye bir metod(cons) oluştu dersen   base'deki hedef metod yani lambayla seçtiğimiz hedef metod cons idi
//otomatik bu sınafa ait bir cons oluştu ve conslar sınıf ismiyle aynı olur...implements'de base'deki metodlar vb herşey bodysiz içi boş idi 
//basedeki metodu base'yi implemente eden sınıfta dolduruyorduk extends de ise  base'de içi dolu bir metod mevcut gördüğün üzere
//biz bu sınıfda yaptığımız metod ile basedeki metoda verileri göndereceğiz yada ekstra birşeyde ypblrz veri göndermeyi super yapıyor
//super demek base sınıf demek mesela DataResult ( 2 paremetreli olan ) metodun içindeki super Result sınıfına 2 paremetre gönderiyor
//yani basedeki 2 paremetreli metoda veri göndermiş oluyor... 
//bu arada Result sınıfında dümende bir metod yazdım bu sınafa tekrar gelip extends ettiğimde lambaya tıklayınca
//sadece 2 constructoru ekle dedi dümenden metod gözükmedi fakat eklemede çıkmasa bile mainde ben sadece bu sınıfı newlersem bile
//nesnesi ile base'deki metoduda çağırabiliyorum. extends oldugu için
//bu arada sınıflardaki metodlarda eğer birşey return etmiceksen ve cons değilsen metod başına void yazcaksın birşey return edeceksen 
//ve cons degılsek tipini yazcaksın void yazmıcaksın

	