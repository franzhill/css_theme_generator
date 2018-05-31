package com.fhi.fjl.web.html;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author francois hill
 * @since 26 nov. 2012
 *
 */
public class HtmlUtils
{
	/**
	 * Example: for sizeFactor=2.0
	 *  Input : 15.2px 20.4px 22px 30px
	 *  Output: 30.4px 40.8px 44.0px 60.0px   (Tested)
	 *
	 * Nota: OGNL seems to convert the string '2' into the double '50.0', why ? ...
	 * So if working from OGNL it's best to use the version {@link  #applySizeFactor(String, String) } from within the JSP.
	 *
	 * @param originalSetting string to be modified. If empty ("") returns empty, if null, returns null.
	 * @param sizeFactor
	 * @return
	 */
	public static String applySizeFactorDouble(String originalSetting, double sizeFactor)
	{
		if ("".equals(originalSetting) || (originalSetting == null)) return originalSetting;

		Pattern p = Pattern.compile("[0-9]*\\.?[0-9]+(?=px|em)");  //Look for ints or doubles
		Matcher m = p.matcher(originalSetting);
		StringBuffer res = new StringBuffer();
		while(m.find())
		{	Double new_val = Math.round(  Double.valueOf(m.group()) * sizeFactor *10 ) / 10.0d ;  // Rounding to 1 decimal
			m.appendReplacement(res, "" + new_val);
		}
		m.appendTail(res);
		return res.toString();
	}

	/**
	 * Example: for sizeFactor=2
	 *  Input : 15.2px 20.4px 22px 30px
	 *  Output: 30.4px 40.8px 44.0px 60.0px   (Tested)
	 */
	public static String applySizeFactor(String originalSetting, String sizeFactor)
	{	return applySizeFactorDouble(originalSetting, Double.parseDouble(sizeFactor));
	}


	private HtmlUtils() {}  // prevent instanciation

}
