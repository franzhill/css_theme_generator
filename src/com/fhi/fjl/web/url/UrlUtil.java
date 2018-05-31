package com.fhi.fjl.web.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 *
 * @author fhill
 * @since 18 oct. 2011
 *
 */
public class UrlUtil
{
	static Logger logger_l4j = Logger.getLogger("com.fhi.fjl");

	/**
	 * This class is intended as static -> prevent it from being instantiated with private constructor
	 */
	private UrlUtil()
	{
	}


	/**
	 * Splits a string of http query params into a map of keys and values
	 * Ex :
	 * The passed value :
	 *   "param1=val1&param2=val2"
	 * will yield the following map :
	 *   param1 => val1
	 *   param2 => val2
	 *
	 * @param query
	 * @return
	 * @author fhi
	 */
	public static Map<String, String> getQueryMap(String query)
	{
		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params)
		{
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}
		return map;
	}



	/**
	 * Will render the string : action_name?param1=val1&param2=val2&...
	 *
	 *
	 * TODO see if not already provided by Java
	 *
	 * @param action_name   without the ".action" extension. This will be added by this function
	 * @param params        map of param names and their respective values
	 *
	 * @author fhi
	 * @since 2 août 2011
	 */
	public static String buildUrl(String action_name, Map<String, Object> params) 
	{	/*        */	String lp = "Interceptor : AuthorizedInterceptor, buildUrl() - ";

		String url="";
		       url += action_name + ".action";

		if (params == null) { return url;}

		       url += "?";

		// There can be several entries for a same key - this is why we are using this special construct
		for (Map.Entry<String, Object> entry : params.entrySet())
		{
			String value = entry.getValue().toString();
			// Replace newline characters by spaces:
			       value = value.replaceAll("\\r\\n|\\r|\\n", " ");
			// Encode special html url characters:
			/*        */	try {
			       value = URLEncoder.encode(value, "UTF-8");
			/*        */	} catch (UnsupportedEncodingException e)
			/*        */	{	throw new RuntimeException("", e);
			/*        */	}

			url += entry.getKey() + "=" + value + "&";
		}

		// Drop off trailing '&' :
		int len = url.length();
		if (len > 0 && '&' == (url.charAt(len-1)))
		{	url = url.substring(0, len-1);
		}
		return url;
	}


	/**
	 * Read the contents located at a given url.
	 * @param url
	 * @return the contents as a String. In case of error returns the error message.
	 *
	 */
	public static String readFromUrlNoException(URL url)
	{
		try
		{	return readFromUrl(url);
		}
		catch (IOException e)
		{	return e.getMessage();
		}
	}


	/**
	 * Read the contents located at a given url.
	 * Doc :
	 *
	 * @param url
	 * @return the contents as a String
	 * @throws IOException
	 *
	 */
	public static String readFromUrl(URL url) throws IOException
	{
		StringBuffer   fBuf = new StringBuffer  () ;
		InputStream    in   = url.openStream();
		BufferedReader dis  = new BufferedReader (new InputStreamReader (in));
		String         line;
		while ( (line = dis.readLine ()) != null)
		{	fBuf.append (line + "\n");
		}
		in.close ();
		return fBuf.toString();
	}
}
