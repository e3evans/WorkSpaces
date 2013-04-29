/*
 * Created on Jan 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.dom.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;

import org.hibernate.collection.PersistentSet;

public class XMLWriter {
	/**
	 * Constructor is, for the most part, superfluous, since all of the object's
	 * methods are <B>static </B>.
	 */
	public XMLWriter() {
		super();
	}

	/**
	 * Build a DOM DocumentFragment representing the class and properties of a
	 * JavaBean.
	 * 
	 * @return org.w3c.dom.DocumentFragment - the document representing the
	 *         JavaBean.
	 * @param doc
	 *            Creates nodes using this document as a factory.
	 * @param bean
	 *            The JavaBean for which we want the DOM tree
	 * @exception java.beans.IntrospectionException
	 *                An error occurred during introspection of the JavaBean.
	 */
	public static String getAsXMLMessage(Object bean)
			throws IntrospectionException, InstantiationException,
			IllegalAccessException {

		// Create the fragment we'll return
		StringBuffer dfResult = null;

		// Analyze the bean
		Class classOfBean = bean.getClass();

		// If the bean doesn't know how to encode itself in XML,
		// then create a string document by introspecting it.
		if (dfResult == null) {
			String strClassName = getClassName(classOfBean.getName());
			dfResult = new StringBuffer("<" + strClassName + ">");
			BeanInfo bi = Introspector.getBeanInfo(classOfBean);
			PropertyDescriptor[] pds = bi.getPropertyDescriptors();		

			// For each property of the bean, get an xml string that
			// represents the individual property. Append that string
			// to the Properties element of the document
			for (int i = 0; i < pds.length; i++) {
				PropertyDescriptor pd = pds[i];
				String df = getAsXMLMessage(bean, pd);
				if (df != null){
					dfResult.append(df);
				}
			}
			dfResult.append("</" + strClassName + ">");
		}
		return dfResult.toString();
	}

	/**
	 * @param name
	 * @return
	 */
	private static String getClassName(String name) {
		String strReturnValue = "";
		
		int index = name.lastIndexOf('.');
		strReturnValue = name.substring(index + 1);
		return strReturnValue;
	}

	/**
	 * Return a DOM DocumentFragment representing a property of a JavaBean
	 * 
	 * @return org.w3c.dom.DocumentFragment
	 * @param doc
	 *            The document to use as a factory for subelements of the tree.
	 * @param bean
	 *            The object that is the value of the property. This object must
	 *            be a JavaBean.
	 * @param pd
	 *            A property descriptor describing the property of which the
	 *            object is a value.
	 * @exception IllegalAccessException
	 * @exception InstantiationException
	 * @exception IntrospectionException
	 */
	public static String getAsXMLMessage(Object bean,
			PropertyDescriptor pd) throws IntrospectionException,
			InstantiationException, IllegalAccessException {
		Class classOfBean = bean.getClass();
		Class classOfProperty = pd.getPropertyType();
		StringBuffer dfResult = new StringBuffer();
//		String sValueAsText = null;
//		Class[] paramsNone = {};
		Object[] argsNone = {};

		// If the property is "class", and the type is java.lang.class, then
		// this is the class of the bean, which we've already encoded.
		// So, in this special case, return null.
		if (pd.getName().equals("class")
				&& classOfProperty.equals(java.lang.Class.class)) {
			return null;
		}

		// [1a] Does the bean have a method called get<Propname>AsXML()?
		// Capitalize property name
		StringBuffer sPropname = new StringBuffer(pd.getName());
		char c = sPropname.charAt(0);
		if (c >= 'a' && c <= 'z') {
			c += 'A' - 'a';
		}
		sPropname.setCharAt(0, c);
		String sGetterName = "get" + sPropname;
		//System.out.print("property " + sPropname + " = ");
		

		// If both of these methods succeed, then dfResult will be set
		// to non-null; that is, the method existed and returned a
		// DocumentFragment
		try {
			Class[] params = {};
			Method mGetter = classOfBean.getMethod(sGetterName, params);
			Object oResult = mGetter.invoke(bean, argsNone);
			if (oResult != null) {
				Class objSet = Class.forName("org.hibernate.collection.PersistentSet");
				Class objSet2 = oResult.getClass();
				if (objSet.equals(objSet2)) {
//					String strClassName = "com.manpower.portal.mpnet.hbn.beans." + sPropname;
					Object[] objItems = ((PersistentSet)oResult).toArray();
					for (int i = 0; i < objItems.length; i++) {
						
						System.out.println(objItems[i].getClass().getName());
						dfResult.append(getAsXMLMessage(objItems[i]));
					}
					//System.out.println(oResult.getClass().getName());
				} else {
					dfResult.append("<" + sPropname + ">");
					dfResult.append(oResult.toString());
					dfResult.append("</" + sPropname + ">");
				}
			}
			else {
				dfResult.append("<" + sPropname + "/>");
			}
		} catch (Exception ee) {
			System.out.println("could not invoke method " + sGetterName);
		}
		finally {
		}
		return dfResult.toString();
	}

	/**
	 * Write a JavaBean as XML to a Writer.
	 * 
	 * @param bean
	 *            The JavaBean to write.
	 * @exception java.beans.IntrospectionException
	 * @exception IOException
	 * @exception InstantiationException
	 * @exception IllegalAccessException
	 */
	public static String getBeanAsXMLString(Object bean)
			throws IOException, java.beans.IntrospectionException,
			InstantiationException, IllegalAccessException {

		String df = getAsXMLMessage(bean);
		System.out.println(df);
		return df;
	}
}
