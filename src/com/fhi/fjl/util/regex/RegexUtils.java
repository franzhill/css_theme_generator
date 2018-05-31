package com.fhi.fjl.util.regex;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils
{
	/**
	 * Does the passed list contain the string, allowing for a regex match (and not just a strict string equality) ?
	 *
	 * @param listOfRegexes list of regexes to be tested against <tt>search</tt>
	 * @param search string for which we are looking for a match in the list of regexes
	 * @return
	 */
	public static boolean contains(List<String> listOfRegexes, String search)
	{
		for (String regex: listOfRegexes)
		{
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(search);
			if (m.matches()) return true;
		}
		return false;
	}


	private RegexUtils() {}  // prevent instanciation

}
