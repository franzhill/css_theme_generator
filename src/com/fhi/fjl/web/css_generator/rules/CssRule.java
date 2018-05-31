package com.fhi.fjl.web.css_generator.rules;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;




/**
 * Represents a css rule, i.e.:                 <br />
 * - a rule name (aka selector name)            <br />
 * - a list of css properties and their values  <br />
 * Example                                      <br />
 * <pre>
 * .class
 * {	border : 1px solid red;
 *  	padding: 2px;
 * }
 * </pre>
 *
 *
 * @author francois hill
 * @since 28 nov. 2012
 */
public class CssRule
{
	/**
	 * Defines a couple {property, value}
	 *
	 */
	private class CssProperty
	{	public String property;
		public String value   ;
		public CssProperty(String prop, String val)
		{	property = prop;
			value    = val;
		}
	}

	private String selectorName = null;


	//private Map<ETypeCssProperties, String> cssProperties = new HashMap<ETypeCssProperties_DEPR, String>();
	// DESIGN NOTE: We'll just use a String instead of the ETypeCssProperties_DEPR which is probably too much overhead for what it's really worth.
	private List<CssProperty>              cssProperties       = new ArrayList<CssProperty>();

	/**
	 * Here we're using a LinkedHashMap to keep the order of insertion of the pseudoclass. This order is important in css.
	 */
	private Map<String, List<CssProperty>> cssPropertiesPseudo = new LinkedHashMap<String, List<CssProperty>>();


	/**
	 * Add a css property (with its value) to this rule, overwriting
	 * any previous value set for that property.                                                       <br />
	 * Alias for <tt>set(true, prop, val);</tt>                                                        <br />
	 * If the value is <tt>null</tt>, it won't be added.                                               <br />
	 * @see {@link #set(boolean, String, String)}
	 * @param prop
	 * @param val
	 */
	public void set(String prop, String val)
	{	set(true, prop, val);
	}


	/**
	 * Add a css property (with its value) to this rule, overwriting, or not,
	 * any previous value set for that property.                                                       <br />
	 * If the value is <tt>null</tt>, it won't be added.                                               <br />
	 * @param prop
	 * @param val
	 * @param remove   remove any property of same name before inserting (i.e. overwrite).
	 *                 If <tt>false</tt>, the property will just be added, resulting in a rule
	 *                 that may have several values defined for the same property (i.e. several
	 *                 couples <tt><prop, val></tt> with <tt>prop</tt> being identical)
	 */
	public void set(boolean remove, String prop, String val)
	{
		if(val != null)
		{	// Remove before add:
			if (remove) {remove(null, prop);}
			cssProperties.add(new CssProperty(prop, val));
		}
	}


	/**
	 * Add a css property (with its value) to this rule or to a pseudo class of this rule, overwriting
	 * any previous value set for that property.                                                       <br />
	 * Alias for <tt>set(pseudoClass, prop, val, true);</tt>                                           <br />
	 * If the value is <tt>null</tt>, it won't be added.                                               <br />
	 * @see {@link #set(String, boolean, String, String)}
	 * @param prop
	 * @param val
	 */
	public void set(String pseudoClass, String prop, String val)
	{	set(pseudoClass, true, prop, val);
	}


	/**
	 * Add a css property (with its value) to this rule or to a pseudo class of this rule, overwriting, or not,
	 * any previous value set for that property.                                                                          <br />
	 * If the value is <tt>null</tt>, it won't be added.                                                                  <br />                                                                                                                   <br />
	 * DESIGN NOTE: although optional, pseudoClass is placed as the first parameter, this is
	 * done to uniformise the look of the many calls to this function with varying <tt>prop</tt> and <tt>val</tt>
	 * but always the same pseudoClass.                                                                                   <br />
	 * Same for <tt>remove</tt>.
	 *
	 * @param pseudoClass optional (pass null if non applicable).
	 *                    Ex: "active", "hover", "focus", "link"...
	 *                    If passed null, this is equivalent to calling
	 *                    {@link #set(String, String)}
	 * @param prop
	 * @param val
	 * @param remove      remove any property of same name before inserting ?
	 */
	public void set(String pseudoClass, boolean remove, String prop, String val)
	{
		if(val != null)
		{
			if (pseudoClass == null)
			{	set(remove, prop, val);
			}
			else
			{
				if (! cssPropertiesPseudo.containsKey(pseudoClass) || cssPropertiesPseudo.get(pseudoClass) == null)
				{	 cssPropertiesPseudo.put(pseudoClass, new ArrayList<CssProperty>());
				}

				// Remove before add:
				if (remove) {remove(pseudoClass, prop);}
				cssPropertiesPseudo.get(pseudoClass).add(new CssProperty(prop, val));
			}
		}

	}


	/**
	 * Remove a given property from the rule
	 * @see {@link #set(String, String, String)}
	 * @param pseudoClass same as in {@link #set(String, String, String)}
	 * @param prop property to be removed
	 */
	public void remove(String pseudoClass, String prop)
	{
		if (pseudoClass == null)
		{	removeProp(cssProperties, prop);
		}
		else
		{
			if (cssPropertiesPseudo.containsKey(pseudoClass))
			{	removeProp(cssPropertiesPseudo.get(pseudoClass), prop);
			}
		}
	}


	private void removeProp( List<CssProperty> list, String prop)
	{
		List<CssProperty> to_be_removed = new ArrayList<CssProperty>();
		for (CssProperty css_prop : list)
		{	if (css_prop.property.equals(prop))
			{	to_be_removed.add(css_prop);
			}
		}
		list.removeAll(to_be_removed);
	}

	/**
	 *
	 * @param sel_name selector name
	 */
	public CssRule(String sel_name)
	{	selectorName = sel_name;
	}

	/**
	 * Print out the css rule.
	 * @return
	 */
	public String print()
	{	StringBuffer sb = new StringBuffer();

		// 1.Print regular rule
		// ---------------------
		// 1.1 Print regular rule name

		if(selectorName != null)
		{	sb.append(selectorName + "\n{");
		}

		// 1.2 Print regular rule properties
		for (CssProperty cssProp : cssProperties)
		{	sb.append("\t");
			sb.append(cssProp.property);sb.append(" : "); sb.append(cssProp.value);sb.append(" ;");
			sb.append("\n");
		}

		if(selectorName != null)
		{	sb.append("\n}\n");
		}

		// 2.Print pseudoclasses rules
		// ----------------------------
		// 2.1 Re-order pseudoclasses

		// The key set is ordered in the insertion order so it is up to the theme to insert the pseudoclasses in the right order.
		// However we'll still operate a reorder to catch any possible order mistakes:
		Set<String> pseudoClasses = cssPropertiesPseudo.keySet();
		Set<String> pseudoClassesSorted = new LinkedHashSet<String>();
		if (pseudoClasses.contains("link"   )) { pseudoClassesSorted.add("link"   );}
		if (pseudoClasses.contains("visited")) { pseudoClassesSorted.add("visited");}
		if (pseudoClasses.contains("hover"  )) { pseudoClassesSorted.add("hover"  );}
		if (pseudoClasses.contains("focus"  )) { pseudoClassesSorted.add("focus"  );}
		if (pseudoClasses.contains("active" )) { pseudoClassesSorted.add("active" );}
		for (String pseudo : pseudoClasses)
		{	if (   (! "link"   .equals(pseudo))
			    && (! "visited".equals(pseudo))
			    && (! "hover"  .equals(pseudo))
			    && (! "focus"  .equals(pseudo))
			    && (! "active" .equals(pseudo))
			   )
			{	pseudoClassesSorted.add(pseudo);
			}
		}

		// 2.2 Print pseudoclasses rules
		for (String pseudo : pseudoClassesSorted)
		{
			if(selectorName != null)
			{
				String[] selectors = selectorName.split(",");                                                 // ex of a selector name : ".myClass, #myId>.myClass"
				for (int i = 0; i < selectors.length; i++)                                                    // trim the array
				{	selectors[i] = selectors[i].trim();
				}
				String new_selectorName = StringUtils.join(selectors, ":" + pseudo +", ") + ":" + pseudo ;    // add the pseudo selector to each separated by ","
				sb.append(new_selectorName + "\n{");
			}
			for (CssProperty cssProp : cssPropertiesPseudo.get(pseudo))
			{	sb.append("\t");

				sb.append(cssProp.property);sb.append(" : "); sb.append(cssProp.value);sb.append(" ;");
				sb.append("\n");
			}
			if(selectorName != null)
			{	sb.append("\n}\n");
			}
		}

		return sb.toString();
	}

}
