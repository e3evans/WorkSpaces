package com.manpower.directtalentseo.utility;

import java.math.BigDecimal;
import java.util.ResourceBundle;

public abstract class HtmlUtility {
	

	public static String getPager(int numberofResults,int rowsperPage,int selectedPage,String pagenumurl){
		ResourceBundle rb = ResourceBundle.getBundle("manpower.translations.JobCategory");
		int numberofPages = 1;
		int numberofPagers = 5;
		if (numberofResults>0 && numberofResults>rowsperPage){
			BigDecimal results = new BigDecimal(numberofResults);
			numberofPages = results.divide(new BigDecimal(rowsperPage), BigDecimal.ROUND_CEILING).intValue();
		}
		int pagerStart = 1;
		BigDecimal pagerPage = new BigDecimal(selectedPage).divide(new BigDecimal(numberofPagers),BigDecimal.ROUND_CEILING);
		pagerStart = ((pagerPage.intValue()*numberofPagers)-numberofPagers)+1;
		StringBuffer sb=new StringBuffer();
		//jobtitlelink
		
		//jobtitlemorelink  
	
//		sb.append("<hr>"+pagerStart+"<hr>"+(pagerStart+numberofPagers)+"<hr>"+numberofPages);
		sb.append("<span class=\"pagerStyle\">"+numberofResults+" "+rb.getString("jobsfound")+"&nbsp;&nbsp;|&nbsp;&nbsp;");
		if (numberofPages >1 ){
			if (selectedPage!=1)sb.append("<a href=\""+pagenumurl+Integer.toString(selectedPage-1)+"\" class=\"jobtitlemorelink\">"+rb.getString("previous")+"</a>&nbsp;&nbsp;");
			int stopPoint = 0;
			for (int i=pagerStart;i<pagerStart+numberofPagers;i++){
				if (i!=selectedPage){
					sb.append("<a href=\""+pagenumurl+Integer.toString(i)+"\" class=\"jobtitlelink\">"+Integer.toString(i)+"</a>&nbsp;&nbsp;");
				}else{
					sb.append(Integer.toString(i)+"&nbsp;&nbsp;");
				}
				stopPoint=i;
				if (i==numberofPages)break;
			}
			
			if (numberofPages>numberofPagers){
				sb.append("...<a href=\""+pagenumurl+Integer.toString(stopPoint+1)+"\" class=\"jobtitlemorelink\">"+rb.getString("more")+"</a>&nbsp;&nbsp;");
			}
			
			if (stopPoint!=1 && selectedPage!=numberofPages)
				sb.append("|&nbsp;"+"<a href=\""+pagenumurl+Integer.toString(selectedPage+1)+"\" class=\"jobtitlemorelink\">"+rb.getString("next")+"</a>&nbsp;&nbsp;");
		}
		sb.append("</span>");
		
//		if (selectedPage>1){
//			sb.append("<a href=\"javascript:pageResults("+(selectedPage-1)+",\'XXXX\',"+numberofPages+")\" class=\"pagingLinkActive\">&lt;&lt;previous page</a>&nbsp;&nbsp;");
//			sb.append ("Page <input class=\"pagerInput\" type=\"text\" id=\"pageNum\" size=\"2\" value=\""+selectedPage+"\" style=\"text-align:center;\" onblur=\"pageResults(this.value,\'XXXX\',"+numberofPages+")\">");
//		}else{
//			sb.append ("Page <input class=\"pagerInput\" type=\"text\" id=\"pageNum\" size=\"2\" value=\"1\" style=\"text-align:center;\" onblur=\"pageResults(this.value,\'XXXX\',"+numberofPages+")\">");
//		}
//		sb.append(" of " +numberofPages);
//
//		if (selectedPage<numberofPages){
//			sb.append("&nbsp;&nbsp;<a href=\"javascript:pageResults("+(selectedPage+1)+",\'XXXX\',"+numberofPages+")\" class=\"jobtitlemorelink\">next&gt;</a>");
//		}
		return sb.toString();
	}
	

}
