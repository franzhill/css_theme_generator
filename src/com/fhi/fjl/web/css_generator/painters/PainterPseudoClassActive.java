package com.fhi.fjl.web.css_generator.painters;

public class PainterPseudoClassActive extends PainterPseudoClass
{

	public PainterPseudoClassActive(IPainter painterPseudoClass)
	{	super(painterPseudoClass);
	}

	@Override
	String getPseudoClassName()
	{	return "active";
	}


}
