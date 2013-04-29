package com.manpower.portal.portlet.directtalent_recruitersearch.utility;

import java.math.BigDecimal;

public class HtmlUtility {
	

	public static String getPager(String keywords,String namespace,int numberofResults,int rowsperPage,int selectedPage){
		int numberofPages = 1;
		if (numberofResults>0 && numberofResults>rowsperPage){
			BigDecimal results = new BigDecimal(numberofResults);
			numberofPages = results.divide(new BigDecimal(rowsperPage), BigDecimal.ROUND_CEILING).intValue();
		}
		StringBuffer sb=new StringBuffer();
		sb.append("<i>Your search returned:  "+numberofResults+" results.</i>&nbsp;&nbsp;|&nbsp;&nbsp;");
		if (selectedPage>1){
			sb.append("<a href=\"javascript:"+namespace+"pageResults("+(selectedPage-1)+",\'"+keywords+"\',"+numberofPages+")\" class=\"pagingLinkActive\">&lt;&lt;previous page</a>&nbsp;&nbsp;");
			sb.append ("Page <input class=\"pagerInput\" type=\"text\" id=\"pageNum\" size=\"2\" value=\""+selectedPage+"\" style=\"text-align:center;\" onblur=\""+namespace+"pageResults(this.value,\'"+keywords+"\',"+numberofPages+")\">");
		}else{
			sb.append ("Page <input class=\"pagerInput\" type=\"text\" id=\"pageNum\" size=\"2\" value=\"1\" style=\"text-align:center;\" onblur=\""+namespace+"pageResults(this.value,\'"+keywords+"\',"+numberofPages+")\">");
		}
		sb.append(" of " +numberofPages);

		if (selectedPage<numberofPages){
			sb.append("&nbsp;&nbsp;<a href=\"javascript:"+namespace+"pageResults("+(selectedPage+1)+",\'"+keywords+"\',"+numberofPages+")\" class=\"pagingLinkActive\">&gt;&gt;next</a>");
		}
		return sb.toString();
	}
	

}
