package testng.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DataProviderUtils
{

   	  protected static String resolveDataProviderArguments2(Method
	  testMethod) throws Exception { if (testMethod == null) throw new
	  IllegalArgumentException("Test Method context cannot be null.");
	  
	  DataProviderArguments args =testMethod.getAnnotation(DataProviderArguments.class); 
	  
	  if (args == null)
	  throw new IllegalArgumentException("Test Method context has no DataProviderArguments annotation."
	  ); 
	  if (args.value() == null || args.value().length() == 0) throw 
	  new IllegalArgumentException("Test Method context has a malformed DataProviderArguments annotation."
	  ); 
	  
	 // Map<String, String> arguments = new HashMap<String, String>(); 
	  
	
	  return  args.value(); 
	  
	  }
	 

}