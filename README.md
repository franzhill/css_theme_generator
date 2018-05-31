# css_theme_generator

## Generating themed css stylesheets with Java

### Abstract

CSS is too low level when you want to think the look and feel of your website in terms of "theme".
A **theme** would be a coherent set of CSS settings that "go well together". For example, mixing all sorts of fonts or using non-complementary colours are examples of things that don't "go well together". Problem is, there's nothing to prevent you doing that in CSS. 

The aim of this project is to show how it is possible to build upon CSS and offer a higher level, more coherent approach to theming a webpage.

By defining the concept of "*Painters*", then stacking them up and combining them to form a the idea of "*themes*", 
we are presented with a powerful tool that achieves just that, and relegates CSS to generated machine code.

The code presented here is written in Java, but it could have been written in any other (Turing complete ;o)) language.

![fig1](./DOC/images/01.png)

### Intro

CSS has come a long way over the years but if you're trying to build anything more than a static page you'll still find it suffers many shortcomings.

For example, there are no variables. If you're using one same colour in multiple rules, and wish to change it, you will have to edit all the rules, you can't use a variable and just change its value.

Many tools have arisen to bridge these shortcomings. A simple web search will yield many references (look for "css framework"). A few examples among many: less, sass.

These tools are often a superset of CSS, providing the basic programmatic mechanisms that CSS lacks: variables, functions, inheritance etc.

However these tools come short when we start to think of styling in terms of "theming". A theme would be a set of visually coherent styling properties applied to content (either by the website programmer, or by the browsing user if the site is very dynamic). For example, a "liquid" theme could display rounded box edges, fancy fonts, and lively colours, whereas a "square" theme could exhibit square edges, straight fonts and darker shades. To achieve that we need to have some mechanism to abstract display properties and bundle together sets of coherent, intertwined and inter-dependant CSS properties.

In this article, we'll describe such a mechanism. The language chosen is Java, and the code is meant to be executed server-side, but the concept can easily be ported to PHP, Python or Javascript, and be executed client-side.


### Approach

What we're trying to achieve follows: 
-generate css stylesheet on the fly
-stylesheet reflects a chosen "theme"
-easily develop new themes based on existing


The concept of themes is not something that would let us think we're exploring uncharted territory. Themes are for example quite widespread in desktop software (they're usually called skins). However desktop application programming and web development are quite different.

So it seems we'll have to build our solution from the bottom up. Or rather, use another analogy. The power in analogies lies in the leverage they bring from solutions that have already been thought trough and tested in other domains - provided that transposing is possible.

In our case we'll get our inspiration from image editing tools. Image editing tools illustrate the concept of working "by layers". Here we mean not the specific editing software lingo, but layers in the broad sense, i.e. changes that are applied successively to a picture and pile up (or down when you hit "undo"). You start with a blank canvas, then apply a background. Then apply a border. Then some content etc. Then choose a colour tone, that might affect the background colour, the border colour and possibly other layers already laid.

![fig2](./DOC/images/02.jpg)

We'll try and replicate this concept. This will help us build the tools to work in semantical terms of higher level than the too finely-grained CSS properties. 

So following of our analogy, our canvas will be a css rule (for example div > a.special), on which we will apply changes (layers) one after the other. We'll name the tool to apply these layers "painters".

The following diagram gives an idea of the core mechanism:

![fig3](./DOC/images/03.jpg)

### Modelising the CSS rule and stylesheet

First let's start by modelising a CSS rule: <code>CssRule</code> is a class listing of properties with their values, just like "real-life" CSS. The class lets us add a property with its value or remove it. It is a list of properties with their values, just like in "real-life" CSS.
The class lets us add a property with its value or remove it.
The class also manages CSS pseudo classes and their priority (LoVeHAte).
This is the class the Painters will operate on (they will change/add properties and values)
One the painters have been applied, the rule can be printed (for output in a css file).

(notice: in this article code snippets have been stripped of material not directly relevant to the  subject. You can download the full classes at the end of the article) 

```java
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

	private String            selectorName   = null;

	private List<CssProperty> cssProperties  = new ArrayList<CssProperty>();

	/**
	 * Here we're using a LinkedHashMap to keep the order of insertion of the pseudoclass. This order is important in css.
	 */
	private Map<String, List<CssProperty>> cssPropertiesPseudo = new LinkedHashMap<String, List<CssProperty>>();


	...


	/**
	 * Add a css property (with its value) to this rule, overwriting, or not,
	 * any previous value set for that property.                                                    <br />
	 * If the value is <tt>null</tt>, it won't be added.                                            <br />
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
	 * any previous value set for that property.                                                    <br />
	 * Alias for <tt>set(pseudoClass, prop, val, true);</tt>                                        <br />
	 * If the value is <tt>null</tt>, it won't be added.                                            <br />
	 * @see {@link #set(String, boolean, String, String)}
	 * @param prop
	 * @param val
	 */
	public void set(String pseudoClass, String prop, String val)
	{	set(pseudoClass, true, prop, val);
	}


	/**
	 * Add a css property (with its value) to this rule or to a pseudo class of this rule, overwriting, or not,
	 * any previous value set for that property.                                                    <br />
	 * If the value is <tt>null</tt>, it won't be added.                                            <br />
	 *                                                                                              <br />
	 * DESIGN NOTE: although optional, pseudoClass is placed as the first parameter, this is
	 * done to uniformise the look of the many calls to this function with varying <tt>prop</tt> and <tt>val</tt>
	 * but always the same pseudoClass.                                                             <br />
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
		...
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
```

## Painters

WIP, soon to come!
