package com.fhi.fjl.lang.enums;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import com.fhi.fjl.lang.enums.excp.EnumNotFoundException;


/**
 *
 * Note on name of this class:
 * We choose to give the name PropertiesUtil rather than just Properties, along the pattern we have
 * adopted for other utility classes, because here the name would in effect clash with java.util.Properties,
 * which is generally not the case for the other utility classes.
 *
 * @author francois hill
 * @since 2012.08.24
 */
public class EnumUtils
{
	/**
	 * Return the enum value for which the passed field equals the passed value.                                   <br />
	 * If several matches are possible will return one of the matches (no garantee as to which).                   <br />
	 * The enum class should make the searched field public or provide a getter method, of type                    <br />
	 * {@code getField()}, i.e. {@code "get" + capitalize(field)}.                                                 <br />
	 * Note : the lookup mechanism uses reflection.                                                                <br />
	 * Note : the equality check between the passed value and the values of the passed field of all the
	 *        possible enum values is is done through equal(). If using "exotic" types, make sure equal() is
	 *        overridden.                                                                                          <br />
	 *                                                                                                             <br />
	 * Example:                                                                                                    <br />
	 * Given the following enum:                                                                                   <br />
	 * <pre>{@code
	 * 	public enum BookGenre
	 * 	{
	 * 		FICTION       (1),
	 * 		NON_FICTION   (2),
	 * 		TRAVEL        (3),
	 * 		CHILDREN      (4),
	 * 		REFERENCE     (5),
	 * 		COMIC         (6);
	 *
	 * 		private int    valueDb     ; // value in DataBase
	 *
	 * 		// Internal constructor (used by Java to build these types)
	 * 		BookGenre(int valueDb)
	 * 		{
	 * 			this.valueDb = valueDb;
	 * 		}
	 *
	 * 		public int getValueDb()
	 * 		{	return valueDb;
	 * 		}
	 *
	 * }</pre>
	 *
	 * the following calls:                                                                                        <br />
	 *
	 * <pre>{@code
	 *		BookGenre genre = lookup(BookGenre.class, "valueDb", 1);
	 * 	System.out.println("genre is : " + genre);
	 *
	 * 	genre = lookup(BookGenre.class, "valueDb", 2);
	 * 	System.out.println("genre is : " + genre);
	 *
	 * 	genre = lookup(BookGenre.class, "valueDb", 5);
	 * 	System.out.println("genre is : " + genre);
	 *
	 * 	genre = lookup(BookGenre.class, "valueDb", "yo");
	 * 	System.out.println("genre is : " + genre);
	 * }</pre>
	 *
	 * will yield:
	 *
	 * <pre>{@code
	 * genre is : FICTION
	 * genre is : NON_FICTION
	 * genre is : REFERENCE
	 * Exception in thread "main" java.lang.IllegalArgumentException: Could not find the requested enum of type {common.Runnable_Test$BookGenre} with the field {valueDb} equal to the value {yo}. Reason : looped through all enum values and could not find a match.
	 * 	at common.Runnable_Test.lookup(Runnable_Test.java:252)
	 * 	at common.Runnable_Test.test_5(Runnable_Test.java:86)
	 * 	at common.Runnable_Test.main(Runnable_Test.java:26)
	 * }</pre>
	 *
	 * If the field is not accessible (e.g. private) and no accessor is accessible either, the following exception will occur: <br />
	 *
	 * <pre>{@code
	 * Exception in thread "main" java.lang.IllegalArgumentException: Could not find the requested enum of type {common.Runnable_Test$BookGenre} with the field {valueDb} equal to the value {1}. Reason : failed to access field directly, so tried to access it through getter method {getValueDb}, but this getter seems not to be defined, please add it to the enum class.
	 * 	at common.Runnable_Test.lookup(Runnable_Test.java:247)
	 * 	at common.Runnable_Test.test_5(Runnable_Test.java:77)
	 * 	at common.Runnable_Test.main(Runnable_Test.java:26)
	 * Caused by: java.lang.NoSuchMethodException: common.Runnable_Test$BookGenre.getValueDb()
	 * 	at java.lang.Class.getDeclaredMethod(Class.java:1909)
	 * 	at common.Runnable_Test.lookup(Runnable_Test.java:234)
	 * 	... 2 more
	 * }</pre>
	 *
	 *
	 * @param <E>
	 * @param etype
	 * @param field
	 * @param value
	 * @return the enum value
	 * @throws EnumNotFoundException field is either not defined, not accessible (directly or through getter method), or no enum value
	 *         with this field equal to the passed value exists.
	 * @author francois hill
	 * @since 2012.08.24
	 *
	 */
	public static <E extends Enum<E>> E lookup(Class<E> etype, String field, Object value) throws EnumNotFoundException
	{
		String err_msg = "Could not find the requested enum of type {" +  etype.getName() +
		                  "} with the field {" + field + "} equal to the value {" +
		                  ((null == value ) ? "null" : value.toString()) + "}. ";
		Object val     = null;
		Method m       = null;

		for(E s : EnumSet.allOf(etype))
		{	Field f;

		/*        */		try
		/*        */		{

			f   = etype.getDeclaredField(field);
			val = f.get(s);

		/*        */		}
		/*        */		catch (Exception e4)
		/*        */		{
		/*        */			String getter_name = "get" + StringUtils.capitalize(field);
		/*        */			String err_msg_2   = "Reason : failed to access field directly, so tried to access it through getter method {" + getter_name + "}";
		/*        */			try
		/*        */			{

			// The field is not public. Try with a getter method instead :
			m   = etype.getDeclaredMethod(getter_name);
			val = m.invoke(s);

		/*        */			}
		/*        */			// This time, there's nothing much else we can do other than fail
		/*        */			catch (SecurityException e5)
		/*        */			{
		/*        */				err_msg = err_msg + err_msg_2 + ", but access was denied too. Please make either field or getter public.";
		/*        */				throw new IllegalArgumentException(err_msg, e5);
		/*        */			}
		/*        */			catch (NoSuchMethodException e6)
		/*        */			{
		/*        */				err_msg = err_msg + err_msg_2 +  ", but this getter seems not to be defined, please add it to the enum class.";
		/*        */				throw new IllegalArgumentException(err_msg, e6);
		/*        */			}
		/*        */			catch (IllegalAccessException e7)
		/*        */			{
		/*        */				err_msg = err_msg + err_msg_2 +  ", but access was denied too. Please make either field or getter public.";
		/*        */				throw new IllegalArgumentException(err_msg, e7);
		/*        */			}
		/*        */			catch (Exception e8)
		/*        */			{
		/*        */				err_msg += ", but an exception was encountered.";
		/*        */				throw new IllegalArgumentException(err_msg, e8);
		/*        */			}
		/*        */		}

			if (val.equals(value))
			{	return s;
			}
		}


		err_msg += "Reason : looped through all enum values and could not find a match. Is the passed value of the right type ?";
		throw new EnumNotFoundException(err_msg);
	}





	/**
	 * Convenience.
	 * Same as {@code lookup()}, only return null instead of throwing {@code EnumNotFoundException}
	 */
	public static <E extends Enum<E>> E lookupNoException(Class<E> etype, String field, Object value)
	{
		/*        */	try
		/*        */	{
		return EnumUtils.lookup(etype, field, value);
		/*        */	}
		/*        */	catch (EnumNotFoundException e)
		/*        */	{
		/*        */		return null;
		/*        */	}

	}


	/**
	 * Return a localized text value associated to the passed enum value of the passed enum type.                  <br />
	 *                                                                                                             <br />
	 * The associated text values are classically looked up in resource bundles associated to the enum type.       <br />
	 *                                                                                                             <br />
	 * Example : if the enum type is com.my.BookGenre.java, then the bundle is com.my.BookGenre and the following  <br />
	 * properties files are looked up:                                                                             <br />
	 *    com/my/BookGenre_en.properties,                                                                          <br />
	 *    com/my/BookGenre_fr.properties,                                                                          <br />
	 *    com/my/BookGenre_de.properties,                                                                          <br />
	 *    etc.                                                                                                     <br />
	 *                                                                                                             <br />
	 * Given the following enum:                                                                                   <br />
	 * <pre>{@code
	 * 	public enum BookGenre
	 * 	{
	 * 		FICTION       (1),
	 * 		NON_FICTION   (2),
	 * 		TRAVEL        (3),
	 * 		CHILDREN      (4),
	 * 		REFERENCE     (5),
	 * 		COMIC         (6);
	 *
	 * 		private int    valueDb     ; // value in DataBase
	 *
	 * 		// Internal constructor (used by Java to build these types)
	 * 		BookGenre(int valueDb)
	 * 		{
	 * 			this.valueDb = valueDb;
	 * 		}
	 *
	 * 		public int getValueDb()
	 * 		{	return valueDb;
	 * 		}
	 *
	 * }</pre>
	 *
	 * the resource bundle for the French language could be:
	 * <pre>{@code
	 * FICTION      = Fiction
	 * NON_FICTION  = Documentaire
	 * TRAVEL       = Voyages
	 * CHILDREN     = Enfants
	 * REFERENCE    = Référence
	 * COMIC        = Bande Dessinée
	 *
	 * }</pre>
	 *
	 * If no resource bundle can be located then the string value of the enum value will be returned.
	 *
	 * @param etype class of enum type
	 * @param value
	 * @return
	 */
	public static <E extends Enum<E>> String getText(Class<E> etype, E value, Locale locale)
	{
		String enum_pckg_name = etype.getClass().getCanonicalName();
		String enum_value_str = value.toString();
		String result         = enum_value_str;

		/*        */	try
		/*        */	{
		ResourceBundle res_bundle     = java.util.ResourceBundle.getBundle(enum_pckg_name, locale);
		               result         = res_bundle.getString(enum_value_str);

		/*        */	}
		/*        */	catch (MissingResourceException e)
		/*        */	{	// TODO : log warning
		/*        */
		/*        */	}

		return result;
	}





}
