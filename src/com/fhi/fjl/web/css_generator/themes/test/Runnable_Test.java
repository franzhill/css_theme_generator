package com.fhi.fjl.web.css_generator.themes.test;

import java.util.Calendar;

import com.fhi.fjl.web.color.palette.Palette;
import com.fhi.fjl.web.color.palette.excp.PaletteLoadException;
import com.fhi.fjl.web.css_generator.skin.Skin;
import com.fhi.fjl.web.css_generator.themes.ITheme;
import com.fhi.fjl.web.css_generator.themes.ThemeFactory;




/**
 * General purpose test class
 * Just write the piece of code to test inside the main() function, and run this class.
 *
 *
 * @author fhill
 * @since 13 déc. 2011
 *
 */
public class Runnable_Test
{
	public static void main(String[] args) throws PaletteLoadException
	{
		test_1();
	}


	public static void test_1() throws PaletteLoadException
	{	long start_time = Calendar.getInstance().getTimeInMillis();
		String xmlPalette = ""
									+ "<palette>                                                                      "
									+ "<url>http://colorschemedesigner.com/#5A329hWs0g0g0</url>                       "
									+ "<colorspace>RGB;</colorspace>                                                  "
									+ "<colorset id='primary' title='Primary Color'>                                  "
									+ "<color id='primary-1' nr='1' rgb='D96069' r='217' g='96' b='105'/>             "
									+ "<color id='primary-2' nr='2' rgb='BE6168' r='190' g='97' b='104'/>             "
									+ "<color id='primary-3' nr='3' rgb='B33B44' r='179' g='59' b='68'/>              "
									+ "<color id='primary-4' nr='4' rgb='E3747C' r='227' g='116' b='124'/>            "
									+ "<color id='primary-5' nr='5' rgb='E38088' r='227' g='128' b='136'/>            "
									+ "</colorset>                                                                    "
									+ "<colorset id='secondary-a' title='Secondary Color A'>                          "
									+ "<color id='secondary-a-1' nr='1' rgb='44708F' r='68' g='112' b='143'/>         "
									+ "<color id='secondary-a-2' nr='2' rgb='44657D' r='68' g='101' b='125'/>         "
									+ "<color id='secondary-a-3' nr='3' rgb='2A5776' r='42' g='87' b='118'/>          "
									+ "<color id='secondary-a-4' nr='4' rgb='5C8BAB' r='92' g='139' b='171'/>         "
									+ "<color id='secondary-a-5' nr='5' rgb='658EAB' r='101' g='142' b='171'/>        "
									+ "</colorset>                                                                    "
									+ "<colorset id='secondary-b' title='Secondary Color B'>                          "
									+ "<color id='secondary-b-1' nr='1' rgb='CBDA60' r='203' g='218' b='96'/>         "
									+ "<color id='secondary-b-2' nr='2' rgb='B4BF61' r='180' g='191' b='97'/>         "
									+ "<color id='secondary-b-3' nr='3' rgb='A6B43B' r='166' g='180' b='59'/>         "
									+ "<color id='secondary-b-4' nr='4' rgb='D6E374' r='214' g='227' b='116'/>        "
									+ "<color id='secondary-b-5' nr='5' rgb='D7E381' r='215' g='227' b='129'/>        "
									+ "</colorset>                                                                    "
									+ "</palette>                                                                     ";
		Palette palette = new Palette(xmlPalette);

		ThemeFactory theme_factory = new ThemeFactory();
		ITheme       theme         = theme_factory.getTheme(ThemeSquare.class);
		Skin         skin          = new Skin(theme, palette, 1);

		// To test efficiency, we might want to run the stylesheet generation a certain number of times:
		int i=100;
		 while (i-- != 0)
		{
			 test_1_sub(skin);
		}
		long stop_time = Calendar.getInstance().getTimeInMillis();
		System.out.println("EXECUTED IN: " + (stop_time - start_time) + " ms");
	}

	public static void test_1_sub(Skin skin) throws PaletteLoadException
	{
		System.out.println("Skin with theme: " + skin.getTheme().getClass().getName() + ":");
		String print = skin.print();
		System.out.println(print);
		System.out.println("\n\n");
	}



}


