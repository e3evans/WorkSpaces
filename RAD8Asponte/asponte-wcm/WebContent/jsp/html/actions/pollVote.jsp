<%@ page session="false" isELIgnored="false" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.ibm.workplace.wcm.api.exceptions.*,com.asponte.commons.*,com.asponte.commons.portal.*,com.asponte.wcm.widgets.*"
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm"
	%><%!
	private static com.ibm.portal.um.PumaHome PUMA_HOME;
	private static com.ibm.portal.identification.Identification IDENTIFICATION;
	public void jspInit(){
	      try{
	    	  javax.naming.Context ctx = new javax.naming.InitialContext();
	    	  javax.naming.Name myjndiname = new javax.naming.CompositeName(com.ibm.portal.um.PumaHome.JNDI_NAME);
	    	  PUMA_HOME = (com.ibm.portal.um.PumaHome) ctx.lookup(myjndiname);
			  IDENTIFICATION = (com.ibm.portal.identification.Identification) ctx.lookup( "portal:service/Identification" );
	      }catch(javax.naming.NamingException e){
	    	  throw new RuntimeException("Missing PumaHome!",e);
	      }
	}
	public String getCurrentUserObjectId() throws com.ibm.portal.um.exceptions.PumaException, com.ibm.portal.serialize.SerializationException{
		com.ibm.portal.um.User currUser=PUMA_HOME.getProfile().getCurrentUser();
		return serialize(currUser.getObjectID());	
	}
    protected String serialize( com.ibm.portal.ObjectID oid ) throws com.ibm.portal.serialize.SerializationException {
        return IDENTIFICATION.serialize( oid );
    }
	%><%final String METHOD_NAME="pollVote.jsp";
	boolean isTraceEnabled=Jsp.LOGGER.isLoggable(Level.FINER);
	boolean isDebugEnabled=Jsp.LOGGER.isLoggable(Level.FINEST);
	boolean isErrorEnabled=Jsp.LOGGER.isLoggable(Level.SEVERE);
	if(isTraceEnabled){Jsp.LOGGER.entering(Jsp.CLASS_NAME,METHOD_NAME,new Object[]{request.getParameterMap()});}
	response.setContentType("application/json; charset=ISO-8859-1");
	String answer=request.getParameter("answer");
	String docid=request.getParameter("docid");
	boolean success=false;
	List _results=new ArrayList();
	if(!com.asponte.commons.portal.Utils.empty(answer)&&!com.asponte.commons.portal.Utils.empty(docid)){
		/* Step 1: We have to grab the system workspace here b/c the user likely doesn't have edit access to the content - i think... */
		Workspace wkspc = WCM_API.getRepository().getSystemWorkspace();
		DocumentLibrary currdoclib = null;
		try {
			/* Step 2: login */
			wkspc.login();
			/* Step 3: Save the current library, if any, and load our libraries */
			try {currdoclib = wkspc.getCurrentDocumentLibrary();} catch (NullPointerException e) {}
			DocumentId did=wkspc.createDocumentId(docid);
			/* Step 4: Grab the current content */
			Content content = (Content)wkspc.getById(did);
			/* Step 5: Now, let's look for and grab the components we need */
			if (content.hasComponent(Constants.POLL_ANSWERS_TEXT)&&content.hasComponent(Constants.POLL_USERS_TEXT)&&content.hasComponent(Constants.POLL_RESULTS_TEXT)) {
				TextComponent anstc = (TextComponent)content.getComponent(Constants.POLL_ANSWERS_TEXT);
				TextComponent usertc = (TextComponent)content.getComponent(Constants.POLL_USERS_TEXT);
				TextComponent resulttc=(TextComponent)content.getComponent(Constants.POLL_RESULTS_TEXT);
				String ans=anstc.getText();
				if(ans==null){ans="";}
				String users=usertc.getText();
				if(users==null){users="";}
				String results=resulttc.getText();
				if(results==null){results="";}
				String objectId=getCurrentUserObjectId();
				String []answersAry=ans.split(",");
				String []resultsAry;
				if((results=results.trim()).length()==0){
					resultsAry=new String[answersAry.length];
					for(int iii=0;iii<answersAry.length;iii++){
						resultsAry[iii]="0";
					}
				}else{
					resultsAry=results.split(",");
				}
				int ansi=Integer.parseInt(answer);
				if(ansi<resultsAry.length){
					int ansj=Integer.parseInt(resultsAry[ansi]);
					resultsAry[ansi]=Integer.toString(++ansj);
					users+=":"+objectId+":";
					results="";
					for(int iii=0;iii<resultsAry.length;iii++){
						if(iii>0){results+=",";}
						results+=resultsAry[iii];
					}
					usertc.setText(users);
					resulttc.setText(results);
					content.setComponent(Constants.POLL_USERS_TEXT,usertc);
					content.setComponent(Constants.POLL_RESULTS_TEXT,resulttc);
					String []errors=wkspc.save(content);
					// TODO: handle errors
					if(errors.length==0){
						success=true;
					}else{
						for(int ii=0;ii<errors.length;ii++){			
							if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The poll with the id "+docid+" could not be updated due to the following WCM error: " +errors[ii]);}
							_results.add(new ActionResult(Result.ERROR,Results.CAST_VOTE_SAVE_FAILED,new Object[] {docid,errors[ii]}));
						}
					}
				}
			}
		}catch(Throwable t){
			if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The poll with the id "+docid+" could not be updated due to the following error: " +t.getMessage(),t);}
			_results.add(new ActionResult(Result.ERROR,Results.CAST_VOTE_FAILED,new Object[] {docid,t.getMessage()},t));		
		}finally {
			if (currdoclib != null) {wkspc.setCurrentDocumentLibrary(currdoclib);}
			wkspc.logout();
			// Non-JSP components must call endWorkspace()
			WCM_API.getRepository().endWorkspace();	
			if(isTraceEnabled){Jsp.LOGGER.exiting(Jsp.CLASS_NAME,METHOD_NAME,Boolean.valueOf(success));}
		}
	}else{
		if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": Missing required parameters (enable trace to see parameter dump)!");}
		_results.add(new ActionResult(Result.ERROR,Results.CAST_VOTE_INVALID_INPUT));
	}
	boolean jsonResult=success;
	List jsonResults=_results;%><%@ include file="../includes/jsonResponse.jspf" %>