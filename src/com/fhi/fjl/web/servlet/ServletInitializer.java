package com.fhi.fjl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fhi.fjl.log.Log4jConfigurer;
import com.fhi.fwl.core.system.SystemUtil;

/**
 * Contains code to be executed on webapp initialization/startup
 *
 * Place call inside web.xml as follows :
 *
 *	<web-app>
 *		...
 *		<servlet>
 * 			<servlet-name>ServletInitializer</servlet-name>
 * 			<servlet-class>com.fhi.fwl.core.init.ServletInitializer</servlet-class>
 * 			<load-on-startup>1</load-on-startup>
 * 		</servlet>
 * 		...
 *
 * @author fhi
 * @since 2012.06.21
 */
@SuppressWarnings("serial")
public class ServletInitializer extends HttpServlet
{

	@Override
	public void init() throws ServletException
	{
		// Configure Log4j with environment specific settings (if not already done)
		// (It is assumed the underlying implementation of the 'org.apache.commons.logging.Log' returned
		//  is of type Log4j.)
		Log4jConfigurer.configureLog4j(SystemUtil.getSystemEnv());


		System.out.println("**************************************");
		System.out.println("*                                     ");
		System.out.println("* Servlet Initialized successfully !  ");
		System.out.println("*                                     ");
		System.out.println("**************************************");
	}


	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
	}



}