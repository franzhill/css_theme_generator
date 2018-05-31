package com.fhi.fjl.web.color.palette;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;

import com.fhi.fjl.core.BaseSupport;
import com.fhi.fjl.dom.DOMDocument;
import com.fhi.fjl.dom.DOMXPath;
import com.fhi.fjl.dom.ElementList;
import com.fhi.fjl.dom.excp.DOMDocumentBuildException;
import com.fhi.fjl.dom.excp.DOMDocumentLoadIOException;
import com.fhi.fjl.dom.excp.DOMDocumentLoadMalFormedException;
import com.fhi.fjl.web.color.Color2;
import com.fhi.fjl.web.color.palette.excp.PaletteLoadException;
import com.fhi.fjl.web.color.palette.types.ETypeColorSet;
import com.fhi.fjl.web.color.palette.types.ETypeColorShade;

/**
 * Represents a 'palette', as created by http://colorschemedesigner.com/
 *
 * Example of a 'palette' config file:
 * <?xml version="1.0" encoding="UTF-8"?>
 * <!--
 *    Color Palette by Color Scheme Designer
 * -->
 * <palette>
 *   <url>http://colorschemedesigner.com/#1e42yw0w0w0w0</url>
 *   <colorspace>RGB;</colorspace>
 *   <colorset id="primary" title="Primary Color">
 *     <color id="primary-1" nr="1" rgb="FFC300" r="255" g="195" b="0" />
 *     <color id="primary-2" nr="2" rgb="BF9E30" r="191" g="158" b="48" />
 *     <color id="primary-3" nr="3" rgb="A67F00" r="166" g="127" b="0" />
 *     <color id="primary-4" nr="4" rgb="FFD240" r="255" g="210" b="64" />
 *     <color id="primary-5" nr="5" rgb="FFDE73" r="255" g="222" b="115" />
 *   </colorset>
 *   <colorset id="secondary-a" title="Secondary Color A">
 *     <color id="secondary-a-1" nr="1" rgb="9FEE00" r="159" g="238" b="0" />
 *     <color id="secondary-a-2" nr="2" rgb="86B32D" r="134" g="179" b="45" />
 *     <color id="secondary-a-3" nr="3" rgb="679B00" r="103" g="155" b="0" />
 *     <color id="secondary-a-4" nr="4" rgb="B9F73E" r="185" g="247" b="62" />
 *     <color id="secondary-a-5" nr="5" rgb="C9F76F" r="201" g="247" b="111" />
 *   </colorset>
 *   <colorset id="secondary-b" title="Secondary Color B">
 *     <color id="secondary-b-1" nr="1" rgb="CD0074" r="205" g="0" b="116" />
 *     <color id="secondary-b-2" nr="2" rgb="992667" r="153" g="38" b="103" />
 *     <color id="secondary-b-3" nr="3" rgb="85004B" r="133" g="0" b="75" />
 *     <color id="secondary-b-4" nr="4" rgb="E6399B" r="230" g="57" b="155" />
 *     <color id="secondary-b-5" nr="5" rgb="E667AF" r="230" g="103" b="175" />
 *   </colorset>
 *   <colorset id="complement" title="Complementary Color">
 *     <color id="complement-1" nr="1" rgb="2219B2" r="34" g="25" b="178" />
 *     <color id="complement-2" nr="2" rgb="342F85" r="52" g="47" b="133" />
 *     <color id="complement-3" nr="3" rgb="0E0874" r="14" g="8" b="116" />
 *     <color id="complement-4" nr="4" rgb="554DD8" r="85" g="77" b="216" />
 *     <color id="complement-5" nr="5" rgb="7872D8" r="120" g="114" b="216" />
 *   </colorset>
 * </palette>
 * <!-- Generated by Color Scheme Designer � Petr Stanicek 2002-2010 -->
 *
 *
 * @author u103860
 * @since 21 nov. 2012
 *
 */
public class Palette extends BaseSupport
{
	private String         url;
	private String         colorspace;
	private List<ColorSet> colorsets = new ArrayList<ColorSet>();
	private boolean        isEmpty;

	// ==========================================================================
	// LOGGING
	// ==========================================================================

	// Redefine preset fields :
	// See base parent class
	{
		logger  = LogFactory.getLog("com.palette");
		debugOn = true
		                       && logger.isDebugEnabled();
	}


	/**
	 * PRIVATE !
	 */
	private Palette()
	{
	}


	/**
	 * Build a palette from an XML string. See class doc for format.
	 *
	 * @throws PaletteLoadException
	 * @throws XPathExpressionException
	 */
	public Palette(String xml) throws PaletteLoadException
	{	PaletteBuilder(null, xml);
		this.isEmpty    = false;
	}


	/**
	 * Build a palette from an XML file. See class doc for format.
	 *
	 * @param file
	 * @throws PaletteLoadException
	 * @throws XPathExpressionException
	 */
	public Palette(File file) throws PaletteLoadException
	{	PaletteBuilder(file, null);
		this.isEmpty    = false;
	}




	/**
	 * Factorization function.
	 * Pass only one parameter, the other should be null.
	 *
	 * @throws PaletteLoadException
	 * @throws XPathExpressionException
	 */
	@SuppressWarnings("unchecked")
	private void PaletteBuilder(File file, String xml) throws PaletteLoadException
	{
		/*        */	boolean debug_on = debugOn && true;
		/*        */	if (debug_on) logger.debug("<<");
		/*        */	String err_msg = "";
		/*        */	String rt_excp_msg = "One of file or xml should be non null. Review code.";
		/*        */	     if (file != null) {err_msg = "during construction from a xml config file";}
		/*        */	else if (xml  != null) {err_msg = "during construction from a xml string"     ;}
		/*        */	else throw new RuntimeException(rt_excp_msg);

		DOMDocument configDomDoc;

		/*        */	try
		/*        */	{

		// 2. Load the config file :
		     if (file != null) {	configDomDoc = new DOMDocument(file); }
		else if (xml  != null) {	configDomDoc = new DOMDocument(xml, ""); }
		else throw new RuntimeException(rt_excp_msg);

		/*        */	}
		/*        */	catch (DOMDocumentLoadMalFormedException e)
		/*        */	{	throw new PaletteLoadException(err_msg + "(config file load error)", null);
		/*        */	}
		/*        */	catch (DOMDocumentLoadIOException e)
		/*        */	{	throw new PaletteLoadException(err_msg + "(config file load error)", null);
		/*        */	}
		/*        */	catch (DOMDocumentBuildException e)
		/*        */	{	throw new PaletteLoadException(err_msg + "(config file load error)", null);
		/*        */	}

		DOMXPath    xpath        = new DOMXPath(configDomDoc);

		/*        */	try
		/*        */	{

		ElementList        el   = new ElementList(xpath.query("/palette/url"));
		this.url        =  el.item(0).getTextContent();

		                   el   = new ElementList(xpath.query("/palette/colorspace"));
		this.colorspace =  el.item(0).getTextContent();

		                   el   = new ElementList(xpath.query("/palette/colorset"));
		for (Element e_colorset : el)
		{
			String            colorset_id     = e_colorset.getAttribute("id");
			String            colorset_title  = e_colorset.getAttribute("title");
			ETypeColorSet     colorset_type   = ETypeColorSet.lookup(e_colorset.getAttribute("id"));
			ArrayList<Color2> colorset_colors = new ArrayList<Color2>();

			ElementList     el2   = new ElementList(xpath.query("color", e_colorset));
			for (Element e_color : el2)
			{	Color2 color = new Color2(e_color.getAttribute("id"), Integer.valueOf(e_color.getAttribute("nr")), e_color.getAttribute("rgb"));
				colorset_colors.add(color);
			}
			// Make sure the list is ordered in such a way that  colorset[idx] returns the color of order=idx (give or take index correction)
			Collections.sort(colorset_colors);

			ColorSet      colorset = new ColorSet(colorset_id, colorset_title, colorset_type, colorset_colors);
			this.colorsets.add(colorset);

		}



		/*        */	}
		/*        */	catch (XPathExpressionException e)
		/*        */	{	throw new PaletteLoadException(err_msg + ". Check XML config file structure", null);
		/*        */	}
	}


	/**
	 * Return an empty palette
	 * Convenience, for these cases where it's technically convenient to have an empty palette.
	 */
	public static Palette empty()
	{
		Palette ret = new Palette();
		        ret . colorspace = "";
		        ret . url        = "";
		        ret . colorsets  . add(ColorSet.empty());
		        ret . isEmpty    = true;
		return ret;
	}








	public String getUrl()
	{	return url;
	}

	public void setUrl(String url)
	{	this.url = url;
	}

	public String getColorSpace()
	{	return colorspace;
	}

	public void setColorSpace(String colorspace)
	{	this.colorspace = colorspace;
	}

	public List<ColorSet> getColorSets()
	{	return colorsets;
	}

	public void setColorSets(List<ColorSet> colorsets)
	{	this.colorsets = colorsets;
	}

	/**
	 * If the specified colorset does not exist, the EMPTY colorset is returned.
	 * @param type
	 * @return
	 */
	public ColorSet getColorSetByType(ETypeColorSet type)
	{
		for (ColorSet colorset : this.colorsets)
		{	if (colorset.getType() == type)
			{	return colorset;
			}
		}
		return ColorSet.empty();
	}

	/**
	 * alias for {@link #getColorsetByType(ETypeColorset)}
	 * @param type
	 * @return
	 */
	public ColorSet getColorSet(ETypeColorSet type)
	{	return getColorSetByType(type);
	}

	/**
	 * Alias for getColorsetByType()
	 * @param type
	 * @return
	 */
	public ColorSet getType(ETypeColorSet type)
	{	return getColorSetByType(type);
	}


	/**
	 * Alias for getColorsetByType(type).getColorByShade(shade).printHex()
	 */
	public String getColor(ETypeColorSet type, ETypeColorShade shade)
	{	return getColorSetByType(type).getColorByShade(shade).printHex();
	}



	@Override
	public String toString()
	{
		return
		                                         "\n" +
		"Palette = [url="        + url +        ",\n" +
		"           colorspace=" + colorspace + ",\n" +
		"           colorsets="  + colorsets  +  "\n"  +
		"          ]";
	}



}