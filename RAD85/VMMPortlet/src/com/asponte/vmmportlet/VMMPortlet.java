package com.asponte.vmmportlet;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import javax.portlet.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;




/**
 * A sample portlet based on GenericPortlet
 */
public class VMMPortlet extends GenericPortlet {



	public static final String JSP_FOLDER    = "/_VMMPortlet/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "VMMPortletView";         // JSP file name to be rendered on the view mode
	public static final String EDIT_JSP      = "VMMPortletEdit";         // JSP file name to be rendered on the edit mode
	public static final String LIST_FRAG_JSP = "VMMListFrag";
	
	public static final String CONFIG_JSP    = "VMMPortletConfig";       // JSP file name to be rendered on the configure mode
	public static final String SESSION_BEAN  = "VMMPortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "VMMPortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "VMMPortletFormText";     // Parameter name for the text input
   
	public static final String EDIT_SUBMIT   = "VMMPortletEditSubmit";   // Action name for submit form
	public static final String EDIT_TEXT     = "VMMPortletEditText";     // Parameter name for the text input
	public static final String EDIT_KEY      = ".VMMPortletEditKey";     // Key for the portlet preferences
	public static final String CONFIG_SUBMIT = "VMMPortletConfigSubmit"; // Action name for submit form
	public static final String CONFIG_TEXT   = "VMMPortletConfigText";   // Parameter name for the text input
	public static final String CONFIG_KEY    = ".VMMPortletConfigKey";   // Key for the portlet preferences

	public static final String PREF_USERNAME = "userName";
	public static final String PREF_PASSWORD = "password";
	public static final String PREF_SEARCHBASE = "searchBase";
	public static final String PREF_VMMSERVICE = "vmmService";
	
	private static final PortletMode CUSTOM_CONFIG_MODE = new PortletMode("config");



	 
	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException{
		super.init();
	}

	/**
	 * Serve up the <code>view</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		PortletPreferences prefs = request.getPreferences();
		
		
		
		
		// Check if portlet session exists
		VMMPortletSessionBean sessionBean = getSessionBean(request);
		
		
		if( sessionBean==null ) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}

		
		sessionBean.setUserName(prefs.getValue(PREF_USERNAME, ""));
		sessionBean.setPassword(prefs.getValue(PREF_PASSWORD, ""));
		sessionBean.setSearchBase(prefs.getValue(PREF_SEARCHBASE, ""));
		sessionBean.setVmmService(prefs.getValue(PREF_VMMSERVICE,"NOT SET"));
		sessionBean.setRawtext(sessionBean.getFormText());
		sessionBean.setFormText(StringEscapeUtils.escapeXml(sessionBean.getFormText()));

		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		rd.include(request,response);
	}

	/**
	 * Serve up the <code>edit</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doEdit(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		VMMPortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
		    response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}


		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, EDIT_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Serve up the custom <code>config</code> mode.
	 */
	protected void doCustomConfigure(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, CONFIG_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Override doDispatch method for handling custom portlet modes.
	 * 
	 * @see javax.portlet.GenericPortlet#doDispatch(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	protected void doDispatch(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		if (!WindowState.MINIMIZED.equals(request.getWindowState())){
			PortletMode mode = request.getPortletMode();			
			if (CUSTOM_CONFIG_MODE.equals(mode)) {
				doCustomConfigure(request, response);
				return;
			}
		}
		super.doDispatch(request, response);
	}

	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	@SuppressWarnings("unchecked")
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
		if( request.getParameter("reqType") != null ) {
			// Set form text in the session bean
			VMMPortletSessionBean sessionBean = getSessionBean(request);
			String jsonXml = "";
			if (request.getParameter("reqType").equals("json"))jsonXml="/json";
			if( sessionBean != null ){
				String requestURL = sessionBean.getVmmService()+jsonXml;
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(requestURL);
				method.addParameter(PREF_USERNAME,sessionBean.getUserName());
				method.addParameter(PREF_PASSWORD,sessionBean.getPassword());
				method.addParameter(PREF_SEARCHBASE,sessionBean.getSearchBase());
				method.addParameter("searchTerm",request.getParameter(FORM_TEXT));
				int statusCode = client.executeMethod(method);
				System.out.println("WEB SERVICE STATUS CODE:  "+statusCode);
				
				InputStream rstream = null;
		        rstream = method.getResponseBodyAsStream();
		        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
		        String line;
		        StringBuffer sb = new StringBuffer();
//		        sb.append("<textarea style='width:100%;height:auto;'>");
		        while ((line = br.readLine()) != null) {
		        	sb.append(line);
		        }
		        br.close();
		        
		        sessionBean.setFormText(sb.toString());
		        System.out.println(sessionBean.getFormText());
		        if (jsonXml.equals("/json")){
					sessionBean.setEntities(JSONHelper(sessionBean.getFormText()));
				}else{
					sessionBean.setEntities(XMLHelper(sessionBean.getFormText()));
					
//					sessionBean.setEntities(new ArrayList());	
				}
			}
		}
		if( request.getParameter(EDIT_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				Enumeration<String> e = request.getParameterNames();
				while (e.hasMoreElements()){
					String tempName = (String)e.nextElement();
//					System.out.println(tempName.indexOf("PREF_"));
					if (tempName.indexOf("PREF_")>-1){
						System.out.println(tempName + "--->"+tempName.substring(5, tempName.length()));
						prefs.setValue(tempName.substring(5, tempName.length()), request.getParameter(tempName));
					}				
				}
//				System.out.println("STORING PREFS!!");
				
//				prefs.setValue(EDIT_KEY,request.getParameter(EDIT_TEXT));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
			}
			catch( ValidatorException ve ) {
			}
		}
		if( request.getParameter(CONFIG_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(CONFIG_KEY,request.getParameter(CONFIG_TEXT));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
			}
			catch( ValidatorException ve ) {
			}
		}
	}

	private List XMLHelper (String xmlString){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			NodeList nodeList = doc.getElementsByTagName("wim:entities");
			List<HashMap>tempList=new ArrayList<HashMap>();
			for (int i = 0 ; i < nodeList.getLength();i++){
				Node person = nodeList.item(i);
				HashMap<String, String> entityMap = new HashMap<String, String>();
				if (person.hasChildNodes()){
					NodeList personAttribs = person.getChildNodes();
					for (int x =0; x<personAttribs.getLength();x++){
						Node attrib = personAttribs.item(x);
						if (attrib.hasAttributes()){
							NamedNodeMap nnm = attrib.getAttributes();
							String temp = "";
							for (int y=0;y<nnm.getLength();y++){
								Node tempA = nnm.item(y);
								temp+=tempA.getNodeName() + "=" + tempA.getNodeValue()+" ";
							}
							entityMap.put(attrib.getNodeName(), temp);
						}else{
							entityMap.put(attrib.getNodeName(), attrib.getTextContent());
						}
					}
				}
				tempList.add(entityMap);
			}
			return tempList;

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
		
	}
	
	private List JSONHelper(String jsonString){
		
		try {
			JSONObject jObj = new JSONObject(jsonString);
			JSONObject dGraph = jObj.getJSONObject("sdo:datagraph");
			if (!dGraph.optBoolean("wim:Root")){
				JSONObject wimRoot = dGraph.getJSONObject("wim:Root");
				JSONArray jArray = wimRoot.getJSONArray("wim:entities");
				List<HashMap>tempList=new ArrayList<HashMap>();
				for (int i = 0 ;i<jArray.length();i++){
					JSONObject entity = jArray.getJSONObject(i);
					HashMap<String, String> entityMap = new HashMap<String, String>();
					Iterator<String> it = entity.keys();
					while (it.hasNext()){
						String temp = (String)it.next();
						entityMap.put(temp, entity.getString(temp));
					}
					tempList.add(entityMap);
	//				System.out.println(jArray.get(i));
				}
				return tempList;
			}
//			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Get SessionBean.
	 * 
	 * @param request PortletRequest
	 * @return VMMPortletSessionBean
	 */
	private static VMMPortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		VMMPortletSessionBean sessionBean = (VMMPortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new VMMPortletSessionBean();
			session.setAttribute(SESSION_BEAN,sessionBean);
		}
		return sessionBean;
	}

	/**
	 * Returns JSP file path.
	 * 
	 * @param request Render request
	 * @param jspFile JSP file name
	 * @return JSP file path
	 */
	private static String getJspFilePath(RenderRequest request, String jspFile) {
		String markup = request.getProperty("wps.markup");
		if( markup == null )
			markup = getMarkup(request.getResponseContentType());
		return JSP_FOLDER + markup + "/" + jspFile + "." + getJspExtension(markup);
	}

	/**
	 * Convert MIME type to markup name.
	 * 
	 * @param contentType MIME type
	 * @return Markup name
	 */
	private static String getMarkup(String contentType) {
		if( "text/vnd.wap.wml".equals(contentType) )
			return "wml";
        else
            return "html";
	}

	/**
	 * Returns the file extension for the JSP file
	 * 
	 * @param markupName Markup name
	 * @return JSP extension
	 */
	private static String getJspExtension(String markupName) {
		return "jsp";
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void serveResource(ResourceRequest request, ResourceResponse response)
			throws PortletException, IOException {
		VMMPortletSessionBean sessionBean = getSessionBean(request);
		
		/**
		 * 	<%for (int i = 0; i<entities.size();i++){
				HashMap temp = (HashMap)entities.get(i);
				Iterator it = temp.keySet().iterator();
				while (it.hasNext()){
					String tempS = (String)it.next();
					tempS = tempS+"="+temp.get(tempS);
					%><%=tempS%><br><%
				}
				%><HR/><%
			%><%} %>
		 * 
		 * 
		 */
		List entities = sessionBean.getEntities();
		StringBuffer sb = new StringBuffer();
		for (int i =0;i<entities.size();i++){
			HashMap temp = (HashMap)entities.get(i);
			Iterator it = temp.keySet().iterator();
			while (it.hasNext()){
				String tempS = (String)it.next();
				sb.append(tempS+"="+temp.get(tempS)+"<br/>");
			}
			sb.append("<hr/>");
		}
		
		response.setContentType("text/html");
	    PrintWriter writer = response.getWriter();
		writer.write(sb.toString());

	}

}
