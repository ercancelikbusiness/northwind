package kodlamaio.northwind.core.utilities.results;

public class SuccessDataResult<T> extends DataResult<T> {
	
	public SuccessDataResult(T data, String message)
	{
	super(data,true,message); // bu sınıf success : true. yapıyor otomatikmen çünkü superdeki 3 paremetreli cons bunu ifade eder
	}
	
	//alternatif kodlar yazdık aşağıda alternatif öylesine bunlar
	
	public SuccessDataResult(T data)
	{
		super(data,true);
	}
	

	public SuccessDataResult(String message)
	{
	super(null,true,message);
	}
	
	public SuccessDataResult()
	{
		super(null,true);
	}

}
