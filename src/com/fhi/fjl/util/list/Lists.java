package com.fhi.fjl.util.list;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author fhill
 * @since 12 août 2011
 *
 */
public class Lists
{
	/**
	 * Useful in some cases in struts actions
	 * We have a list of type ArrayList<HashMap<String, String>> (usually the result of an ibatis query)
	 * and want to know if a given element is in that list
	 *
	 * Example of such a list :
	 *
	 * List ( Map [ "id"    -> "1",
	 *              "name"  -> "first value (displayed in select box)"
	 *             ],
	 *        Map [ "id"    -> "2",
	 *              "name"  -> "second value (displayed in select box)"
	 *             ],
	 *        Map [ "id"    -> "3",
	 *              "name"  -> "third value (displayed in select box)"
	 *             ],
	 *
	 * Example of use case :
	 * In our struts action we receive as result of a form submission the id="3"
	 * and want to validate it by making sure it is valid i.e. there is a map in the list with the "id"=3
	 *
	 * The HashMap is parametrised : HashMap<?,?>. However struts'action will receive a String value.
	 * The comparison will therefore rely on calling toString() on the keys of the HashMap.
	 *
	 *
	 * @param list
	 * @param key
	 * @return
	 */
	public static boolean ContainsMapWithKey(ArrayList<HashMap<?, ?>> list, String keyName, String keyValue)
	{	//#System.out.println("ContainsMapWithKey" );
		//#System.out.println("-list = " + list );
		//#System.out.println("-keyName = " + keyName );
		//#System.out.println("-keyValue = " + keyValue );

		for (HashMap<?, ?> map : list)
		{	//#System.out.println("Current hashmap = " + map );

			if (map.containsKey(keyName))
			{	//#System.out.println("map contains " + keyName);
				//#System.out.println("map.get(id)=" + map.get("id"));
				//#System.out.println("map.get(name)=" + map.get("name"));
				//#System.out.println("map.get(keyName)=" );
				//#String key_val = map.get(keyName).toString();
				//#System.out.println("" + key_val);
				if (map.get(keyName) != null && keyValue!=null && keyValue.equals( map.get(keyName).toString()) )
				{	return true;
				}
			}
		}
		return false;
	}


	private Lists() {}  // prevent instanciation
}
