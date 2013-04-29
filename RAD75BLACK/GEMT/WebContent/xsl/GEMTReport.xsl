<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	version = "1.0" >
			
<xsl:import href="xhtml2fo.xsl"/>			

<xsl:template match = "/" >

<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

  <fo:layout-master-set>
    <fo:simple-page-master master-name="simple"
                  page-height="29.7cm" 
                  page-width="21cm"
                  margin-top="1cm" 
                  margin-bottom="2cm" 
                  margin-left="2.5cm" 
                  margin-right="2.5cm">
      <fo:region-body margin-top="1.75cm"/>
      <fo:region-before extent="2cm"/>
      <fo:region-after extent="2cm"/>
    </fo:simple-page-master>
  </fo:layout-master-set>

  <fo:page-sequence master-reference="simple">
  <fo:static-content flow-name="xsl-region-before">
  	<fo:block border-after-color="#C07200" border-after-width="2mm" border-after-style="solid">
  		<fo:external-graphic scaling="uniform" content-width="16cm" vertical-align="middle">
  			<xsl:attribute name="src">url('<xsl:value-of select="//GemtSummaryReportUIBean/Xml_real_path" />/images/CISHeader.gif')</xsl:attribute>
  		</fo:external-graphic>
  	</fo:block>

  	</fo:static-content>
  <fo:flow flow-name="xsl-region-body">

  	<fo:table>
  		<fo:table-column column-width="80mm" />
  		<fo:table-column column-width="80mm" />
  		<fo:table-body>
  			<fo:table-row>
  				<fo:table-cell>
  					<fo:block></fo:block>
  				</fo:table-cell>
  				<fo:table-cell>
  					<fo:block></fo:block>
  				</fo:table-cell>
  			</fo:table-row>
  			<fo:table-row>
  				<fo:table-cell>
  					<fo:block font-size="9.5pt" font-family="arial"
  						font-weight="bold">
  						Name :
  						<xsl:value-of
  							select="//GemtSummaryReportUIBean/Gemt_sum_empname" />
  					</fo:block>
  				</fo:table-cell>
  				<fo:table-cell>
  					<fo:block font-size="9.5pt" font-family="arial"
  						font-weight="bold">
  						Title :
  						<xsl:value-of
  							select="//GemtSummaryReportUIBean/Gemt_sum_emptitle" />
  					</fo:block>
  				</fo:table-cell>
  			</fo:table-row>
  			<fo:table-row>
  				<fo:table-cell>
  					<fo:block font-size="9.5pt" font-family="arial"
  						font-weight="bold">
  						Date :
  						<xsl:call-template name="FormatDate">
  							<xsl:with-param name="DateTime"
  								select="//GemtSummaryReportUIBean/Gemt_sum_repdate" />
  						</xsl:call-template>
  					</fo:block>
  				</fo:table-cell>
  				<fo:table-cell>
  					<fo:block font-size="9.5pt" font-family="arial"
  						font-weight="bold">
  						Manager :
  						<xsl:value-of
  							select="//GemtSummaryReportUIBean/Gemt_sum_managername" />
  					</fo:block>
  				</fo:table-cell>
  			</fo:table-row>
  			<fo:table-row>
  				<fo:table-cell>
  					<fo:block font-size="9.5pt" font-family="arial"
  						font-weight="bold">
  						Selected Period :
  						<xsl:call-template name="FormatPeriod">
  							<xsl:with-param name="period"
  								select="//GemtSummaryReportUIBean/Gemt_sum_period" />
  						</xsl:call-template>
  					</fo:block>  					
  				</fo:table-cell>
  			</fo:table-row>
  		</fo:table-body>
  	</fo:table>
  	<fo:block font-size="12pt" 
	                   font-family="sans-serif" 
	                   background-color="#C07200"
	                   color="#B2B2B2"
	                   text-align="center" space-after.optimum="20pt">  
     		 </fo:block>
	               <fo:table>
	               <fo:table-column column-width="45mm"/>
	               <fo:table-column column-width="30mm"/>
	               <fo:table-column column-width="30mm"/>
	               <fo:table-column column-width="30mm"/>
	
	               <fo:table-body >
	                    <fo:table-row>
                                            <fo:table-cell>
                                      			<fo:block font-size="12pt" font-family="arial" font-weight="bold" line-height="24pt">Leadership Role</fo:block>
                                           </fo:table-cell>
                                           <fo:table-cell>
                                      			<fo:block font-size="10pt" font-family="arial" font-weight="bold" line-height="24pt">Unsatisfactory</fo:block>
                                           </fo:table-cell>
                                            <fo:table-cell>
                                      			<fo:block font-size="10pt" font-family="arial" font-weight="bold" line-height="24pt">Developing</fo:block>
                                           </fo:table-cell>
                                            <fo:table-cell>
                                      			<fo:block font-size="10pt" font-family="arial" font-weight="bold" line-height="24pt">Proficient</fo:block>
                                           </fo:table-cell>
                                            <fo:table-cell>
                                      			<fo:block font-size="10pt" font-family="arial" font-weight="bold" line-height="24pt">Outstanding</fo:block>
                                           </fo:table-cell>
                       </fo:table-row>  
				   <xsl:variable name = "clientRowtitle" >Client</xsl:variable>  
				   <xsl:variable name = "clientRowcolor">#5F81AA</xsl:variable>
				   <xsl:call-template name="generateBoxRow">
						<xsl:with-param name="selected" select="//GemtSummaryReportUIBean/Gemt_sum_roleclient"/>
						<xsl:with-param name="boxnum" select="1"/>
						<xsl:with-param name="rowtitle" select="$clientRowtitle"/>
						<xsl:with-param name="textcolor" select="$clientRowcolor"/>
				   </xsl:call-template>           
				  <xsl:variable name = "peopleRowtitle" >People</xsl:variable>  
				    <xsl:variable name = "peopleRowcolor">#7EA190</xsl:variable>
				   <xsl:call-template name="generateBoxRow">
						<xsl:with-param name="selected" select="//GemtSummaryReportUIBean/Gemt_sum_rolepeople"/>
						<xsl:with-param name="boxnum" select="1"/>
						<xsl:with-param name="rowtitle" select="$peopleRowtitle"/>
						<xsl:with-param name="textcolor" select="$peopleRowcolor"/>
				   </xsl:call-template>   
				   <xsl:variable name = "thoughtRowtitle" >Thought Leadership</xsl:variable>  
				     <xsl:variable name = "thoughtRowcolor">#D47C18</xsl:variable>
				   <xsl:call-template name="generateBoxRow">
						<xsl:with-param name="selected" select="//GemtSummaryReportUIBean/Gemt_sum_rolethought"/>
						<xsl:with-param name="boxnum" select="1"/>
						<xsl:with-param name="rowtitle" select="$thoughtRowtitle"/>
						<xsl:with-param name="textcolor" select="$thoughtRowcolor"/>
				   </xsl:call-template>
				   <xsl:variable name = "day2dayRowtitle" >Day to Day</xsl:variable>
				     <xsl:variable name = "day2dayRowcolor">#C8504F</xsl:variable>  
				   <xsl:call-template name="generateBoxRow">
						<xsl:with-param name="selected" select="//GemtSummaryReportUIBean/Gemt_sum_roledaytoday"/>
						<xsl:with-param name="boxnum" select="1"/>
						<xsl:with-param name="rowtitle" select="$day2dayRowtitle"/>
						<xsl:with-param name="textcolor" select="$day2dayRowcolor"/>
				   </xsl:call-template>
	         </fo:table-body>
	         </fo:table>
	                                             <fo:block font-size="12pt" 
				       	                   font-family="sans-serif" 
				       	                   background-color="#C07200"
				       	                   color="#B2B2B2"
				       	                   text-align="center" space-after.optimum="20pt"
				       	                   >
				       	               
      </fo:block>
<fo:table>
               <fo:table-column column-width="42mm"/>
               <fo:table-column column-width="34mm"/>
               <fo:table-column column-width="30mm"/>
               <fo:table-column column-width="30mm"/>

               <fo:table-body >
                    <fo:table-row>
	       			                                           <fo:table-cell>
	       			                                                <fo:block font-size="12pt" 
            font-family="arial" font-weight="bold" line-height="24pt">Results Overview</fo:block>
	       			                                           </fo:table-cell>
	       			                                           <fo:table-cell>
	       			                                                <fo:block font-size="9.5pt" 
            font-family="arial" font-weight="bold" line-height="24pt">Below Threshold</fo:block>
	       			                                           </fo:table-cell>
	       			                                           <fo:table-cell>
	       			                                                <fo:block font-size="9.5pt" 
            font-family="arial" font-weight="bold" line-height="24pt">Threshold</fo:block>
	       			                                           </fo:table-cell>
	       			                                           <fo:table-cell>
	       			       				         <fo:block font-size="9.5pt" 
            font-family="arial" font-weight="bold" line-height="24pt">Target</fo:block>
	       			                                           </fo:table-cell>
	       			                                           <fo:table-cell>
	       			       				         <fo:block font-size="9.5pt" 
            font-family="arial" font-weight="bold" line-height="24pt">Outstanding</fo:block>
	       			                                           </fo:table-cell>
                               </fo:table-row>
				   <xsl:variable name = "overviewscorefinanRowtitle" >Scorecard - Financial</xsl:variable>  
				     <xsl:variable name = "overviewscorefinanRowcolor">#D47C18</xsl:variable>
				   <xsl:call-template name="generateBoxRow">
						<xsl:with-param name="selected" select="//GemtSummaryReportUIBean/Gemt_sum_overviewscorefinan"/>
						<xsl:with-param name="boxnum" select="1"/>
						<xsl:with-param name="rowtitle" select="$overviewscorefinanRowtitle"/>
						<xsl:with-param name="textcolor" select="$overviewscorefinanRowcolor"/>
				   </xsl:call-template>	
				   <xsl:variable name = "overviewscorekpiRowtitle" >Scorecard - KPIs</xsl:variable>  
				     <xsl:variable name = "overviewscorekpiRowcolor">#D47C18</xsl:variable>
				   <xsl:call-template name="generateBoxRow">
						<xsl:with-param name="selected" select="//GemtSummaryReportUIBean/Gemt_sum_overviewscorekpi"/>
						<xsl:with-param name="boxnum" select="1"/>
						<xsl:with-param name="rowtitle" select="$overviewscorekpiRowtitle"/>
						<xsl:with-param name="textcolor" select="$overviewscorekpiRowcolor"/>
				   </xsl:call-template>					   
                               
                               </fo:table-body>
                               </fo:table>
                               
                               <fo:block font-size="12pt" 
			       				       	                   font-family="sans-serif" 
			       				       	                   background-color="#C07200"
			       				       	                   color="#B2B2B2"
			       				       	                   text-align="center" space-after.optimum="20pt"
			       				       	                   >
			       				       	               
      </fo:block>
      
      <fo:table>
                     <fo:table-column column-width="80mm"/>
                     <fo:table-column column-width="80mm"/>
                  
      
                     <fo:table-body >
                          <fo:table-row>
      	       			<fo:table-cell>
				      <fo:block font-size="12pt" 
				            font-family="arial" font-weight="bold" line-height="24pt">Performance Highlights
				      </fo:block>
                  		</fo:table-cell>  
      	      			<fo:table-cell>
      	       			      <fo:block font-size="12pt" 
                  			font-family="arial" font-weight="bold" line-height="24pt">Missed Opportunities                  
                  		      </fo:block>
                  		</fo:table-cell>      	       			                                        
                          </fo:table-row>     
                          <fo:table-row>
			        	       			<fo:table-cell>
			  				      <fo:block font-size="12pt" 
			  				            font-family="arial">  
												<xsl:apply-templates select="//GemtSummaryReportUIBean/Gemt_sum_perfhighlights"/>
			  				      </fo:block>
			                    		</fo:table-cell>  
			        	      			<fo:table-cell>
			        	       			      <fo:block font-size="12pt" 
			                    			font-family="arial">             
									<xsl:apply-templates select="//GemtSummaryReportUIBean/Gemt_sum_missedopps"/>
			                    		      </fo:block>
			                    		</fo:table-cell>      	       			                                        
                          </fo:table-row>  
                        
                    </fo:table-body>
       </fo:table>
	<fo:block font-size="12pt" 
		                   font-family="sans-serif" 
		                   background-color="#C07200"
		                   color="#B2B2B2"
		                   text-align="center" space-after.optimum="20pt"
		                   >
		               
	      </fo:block>
	               
		               <fo:table>
		               <fo:table-column column-width="45mm"/>
		               <fo:table-column column-width="30mm"/>
		               <fo:table-column column-width="30mm"/>
		               <fo:table-column column-width="30mm"/>
		
		               <fo:table-body >
		                    <fo:table-row>
			       			                                           <fo:table-cell>
			       			                                                <fo:block font-size="10pt" 
		            font-family="arial" font-weight="bold" background-color="#D47C18" color ="#FFFFFF" line-height="24pt">Overall Performance</fo:block>
			       			                                           </fo:table-cell>
			       			                                           <fo:table-cell>
			       			                                                <fo:block font-size="10pt" 
		            font-family="arial" font-weight="bold" background-color="#D47C18" color ="#FFFFFF" line-height="24pt">Unsatisfactory</fo:block>
			       			                                           </fo:table-cell>
			       			                                           <fo:table-cell>
			       			                                                <fo:block font-size="10pt" 
		            font-family="arial" font-weight="bold" background-color="#D47C18" color ="#FFFFFF" line-height="24pt">Developing</fo:block>
			       			                                           </fo:table-cell>
			       			                                           <fo:table-cell>
			       			       				         <fo:block font-size="10pt" 
		            font-family="arial" font-weight="bold" background-color="#D47C18" color ="#FFFFFF" line-height="24pt">Proficient</fo:block>
			       			                                           </fo:table-cell>
			       			                                           <fo:table-cell>
			       			       				         <fo:block font-size="10pt" 
		            font-family="arial" font-weight="bold" background-color="#D47C18"  color ="#FFFFFF" line-height="24pt">Outstanding</fo:block>
			       			                                           </fo:table-cell>
		                               </fo:table-row>
				   <xsl:variable name = "overallperfRowtitle" >50% Leadership Role / 50% Results Overview</xsl:variable>  
				     <xsl:variable name = "overallperfRowcolor">#D47C18</xsl:variable>
				   <xsl:call-template name="generateBoxRow">
						<xsl:with-param name="selected" select="//GemtSummaryReportUIBean/Gemt_sum_overallperf"/>
						<xsl:with-param name="boxnum" select="1"/>
						<xsl:with-param name="rowtitle" select="$overallperfRowtitle"/>
						<xsl:with-param name="textcolor" select="$overallperfRowcolor"/>
				   </xsl:call-template>
		                               </fo:table-body>
	                               </fo:table>
	                               <fo:block font-size="12pt" 
				       			       				       	                   font-family="sans-serif" 
				       			       				       	                   background-color="#C07200"
				       			       				       	                   color="#B2B2B2"
				       			       				       	                   text-align="center" space-after.optimum="20pt"
				       			       				       	                   >
				       			       				       	               
				             </fo:block>
				             
				             <fo:table>
				                            <fo:table-column column-width="160mm"/>
				                         
				             
				                            <fo:table-body >
				                                 <fo:table-row>
				             	       			<fo:table-cell>
				       				      <fo:block font-size="9.5pt" 
				       				            font-family="arial" font-weight="bold" line-height="24pt">Comments                 
				       				      </fo:block>
				                         		</fo:table-cell>     	       			                                        
				                                 </fo:table-row>     
				                                 <fo:table-row>
				       			        	       			<fo:table-cell>
				       			  				      <fo:block font-size="12pt" 
				       			  				            font-family="arial">
													<xsl:apply-templates select="//GemtSummaryReportUIBean/Gemt_sum_comments"/>												    
				       			  				      </fo:block>
				       			                    		</fo:table-cell>       	       			                                        
				                                 </fo:table-row>  
				                           </fo:table-body>
       </fo:table>

<fo:block font-size="12pt" 
		                   font-family="sans-serif" 
		                   background-color="#C07200"
		                   color="#B2B2B2"
		                   text-align="center" space-after.optimum="20pt"
		                   >
		               
	      </fo:block>
	               
		               <fo:table>
			                            <fo:table-column column-width="80mm"/>
			                            <fo:table-column column-width="80mm"/>
			                         
			             
			                            <fo:table-body >
			                                 <fo:table-row>
			             	       			<fo:table-cell>
			       				      <fo:block font-size="12pt" 
			       				            font-family="arial" background-color="#D47C18" color ="#FFFFFF" line-height="24pt">Individual Development                  
			       				      </fo:block>
			                         		</fo:table-cell>  
			             	      			<fo:table-cell>
			             	       			      <fo:block font-size="12pt" 
			                         			font-family="arial" font-weight="bold" background-color="#D47C18" line-height="24pt">.                  
			                         		      </fo:block>
			                         		</fo:table-cell>      	       			                                        
			                                 </fo:table-row>     
			                                 
			                           </fo:table-body>
       </fo:table>
		<fo:table>
                     <fo:table-column column-width="80mm"/>
                     <fo:table-column column-width="80mm"/>
                  
      
                     <fo:table-body >
                          <fo:table-row>
		      	       			<fo:table-cell>
								      <fo:block font-size="9.5pt" 
								            font-family="arial" font-weight="bold">Development Strengths
								      </fo:block>
		                  		</fo:table-cell>  
		      	      			<fo:table-cell>
		      	       			      <fo:block font-size="9.5pt" 
		                  					font-family="arial" font-weight="bold">Development Needs                  
		                  		      </fo:block>
		                  		</fo:table-cell>      	       			                                        
                          </fo:table-row>     
                          <fo:table-row>
	        	       			<fo:table-cell>
			  				      <fo:block font-size="12pt" 
			  				            font-family="arial">
									<xsl:apply-templates select="//GemtSummaryReportUIBean/Gemt_sum_devstrengths"/>
			  				      </fo:block>
	                    		</fo:table-cell>  
	        	      			<fo:table-cell>
        	       			      <fo:block font-size="12pt" 
                    						font-family="arial">
									<xsl:apply-templates select="//GemtSummaryReportUIBean/Gemt_sum_devneeds"/>
                    		      </fo:block>
	                    		</fo:table-cell>      	       			                                        
                          </fo:table-row>  
                          <fo:table-row>
			  			        <fo:table-cell>
				  					<fo:block font-size="9.5pt" 
				  			  					font-family="arial" font-weight="bold">Development Priorities                  
				  			  		</fo:block>
			  			  		</fo:table-cell>
			  			        <fo:table-cell>
				  					<fo:block font-size="9.5pt" 
				  			  					font-family="arial" font-weight="bold">Development Actions                  
				  			  		</fo:block>
			  			  		</fo:table-cell>			  			                    		      	       			                                        
                          </fo:table-row> 
                          <fo:table-row>
			  			       	<fo:table-cell>
			  			        	<fo:block font-size="12pt" 
			  			                    font-family="arial">
									    <xsl:apply-templates select="//GemtSummaryReportUIBean/Gemt_sum_devpriorities"/>
		  			                </fo:block>
	                    		</fo:table-cell>                            
			  			       	<fo:table-cell>
			  			        	<fo:block font-size="12pt" 
			  			                    font-family="arial">
									    <xsl:apply-templates select="//GemtSummaryReportUIBean/Gemt_sum_devactions"/>
		  			                </fo:block>
	                    		</fo:table-cell>    
                   		</fo:table-row>
                    </fo:table-body>
       </fo:table>
       <fo:block font-size="12pt" 
       		                   font-family="sans-serif" 
       		                   background-color="#C07200"
       		                   color="#B2B2B2"
       		                   text-align="center" space-after.optimum="20pt"
       		                   >
       		               
       	      </fo:block>
       	               
       		               <fo:table>
       			                            <fo:table-column column-width="80mm"/>
       			                            <fo:table-column column-width="80mm"/>
       			                         
       			             
       			                            <fo:table-body >
       			                                 <fo:table-row>
       			             	       			<fo:table-cell>
       			       				      <fo:block font-size="12pt" 
       			       				            font-family="arial"  background-color="#D47C18" color ="#FFFFFF" line-height="24pt">Employee Acknowledgment                  
       			       				      </fo:block>
       			                         		</fo:table-cell>  
       			             	      			<fo:table-cell>
       			             	       			      <fo:block font-size="12pt" 
       			                         			font-family="arial" font-weight="bold" background-color="#D47C18" line-height="24pt">.                  
       			                         		      </fo:block>
       			                         		</fo:table-cell>      	       			                                        
       			                                 </fo:table-row>     
       			                                 
       			                           </fo:table-body>
       </fo:table>
       <fo:table>
                            <fo:table-column column-width="130mm"/>
                            <fo:table-column column-width="30mm"/>
                         
             
                            <fo:table-body >
                                 <fo:table-row>
             	       			<fo:table-cell>
       				      <fo:block font-size="9.5pt" 
       				            font-family="arial" font-weight="bold">Signature:
       				      </fo:block>
                         		</fo:table-cell>  
             	      			<fo:table-cell>
             	       			      <fo:block font-size="9.5pt" 
                         			font-family="arial" font-weight="bold">Date:                 
                         		      </fo:block>
                         		</fo:table-cell>      	       			                                        
                          </fo:table-row>     
                          </fo:table-body>
       </fo:table>
       <fo:block font-size="12pt" 
       		                   font-family="sans-serif" 
       		                   background-color="#C07200"
       		                   color="#B2B2B2"
       		                   text-align="center" space-after.optimum="20pt"
       		                   >
       		               
       	      </fo:block>
       	               
       		               <fo:table>
       			                            <fo:table-column column-width="80mm"/>
       			                            <fo:table-column column-width="80mm"/>
       			                         
       			             
       			                            <fo:table-body >
       			                                 <fo:table-row>
       			             	       			<fo:table-cell>
       			       				      <fo:block font-size="12pt" 
       			       				            font-family="arial"  background-color="#D47C18" color ="#FFFFFF" line-height="24pt">Manager Acknowledgment                  
       			       				      </fo:block>
       			                         		</fo:table-cell>  
       			             	      			<fo:table-cell>
       			             	       			      <fo:block font-size="12pt" 
       			                         			font-family="arial" font-weight="bold" background-color="#D47C18" line-height="24pt">.                  
       			                         		      </fo:block>
       			                         		</fo:table-cell>      	       			                                        
       			                                 </fo:table-row>     
       			                                 
       			                           </fo:table-body>
       </fo:table>
       <fo:table>
                            <fo:table-column column-width="130mm"/>
                            <fo:table-column column-width="30mm"/>
                         
             
                            <fo:table-body >
                                 <fo:table-row>
             	       			<fo:table-cell>
       				      <fo:block font-size="9.5pt" 
       				            font-family="arial" font-weight="bold">Signature:
       				      </fo:block>
                         		</fo:table-cell>  
             	      			<fo:table-cell>
             	       			      <fo:block font-size="9.5pt" 
                         			font-family="arial" font-weight="bold">Date:                 
                         		      </fo:block>
                         		</fo:table-cell>      	       			                                        
                          </fo:table-row>     
                          </fo:table-body>
                          </fo:table>
                          
    </fo:flow>
  </fo:page-sequence>
</fo:root>

</xsl:template>

<xsl:template name="generateBoxRow">
	<xsl:param name="selected"/>
	<xsl:param name="boxnum"/>
	<xsl:param name="rowtitle"/>
	<xsl:param name="textcolor"/>
	
	<fo:table-row>
		<fo:table-cell>
			<xsl:element name="fo:block">
				<xsl:attribute name="font-size">
				11pt
				</xsl:attribute>
				<xsl:attribute name="font-family">
				arial
				</xsl:attribute>
				<xsl:attribute name="font-weight">
				bold
				</xsl:attribute>
				<xsl:attribute name="color">
				<xsl:value-of select="$textcolor"/>
				</xsl:attribute>
				<xsl:attribute name="space-after.optimum">
				11pt
				</xsl:attribute>
				<xsl:value-of select="$rowtitle"/>
			</xsl:element>
		</fo:table-cell>
		<xsl:call-template name="generateBoxCells">
			<xsl:with-param name="selected" select="$selected"/>
			<xsl:with-param name="boxnum" select="$boxnum"/>
		</xsl:call-template>
	 </fo:table-row>
	
</xsl:template>

<xsl:template name="generateBoxCells">
	<xsl:param name="selected"/>
	<xsl:param name="boxnum"/>
		
		<xsl:if test="$boxnum &lt; 5">
			<fo:table-cell>				       	       			                                                
				<fo:block>
					<fo:instream-foreign-object width="11pt" height="11pt">
							<svg:svg width="22" height="22" xmlns:svg="http://www.w3.org/2000/svg" >
								<svg:g transform="scale(.5)" style="fill:none; stroke:black;stroke-width:2">
				   					<svg:rect x = "0" y = "2" width = "15" height = "15"  style = "fill: black; stroke-width: 1;"/>
									<svg:rect x = "2" y = "0" width = "15" height = "15"  style = "fill: white; stroke-width: 1;"/>
									<xsl:if test="$selected = $boxnum">
										<svg:line x1="4" y1="8" x2="7" y2="12"/>
										<svg:line x1="7" y1="12" x2="15" y2="2"/>
									</xsl:if>
			 					</svg:g>
							</svg:svg>
					</fo:instream-foreign-object>
				</fo:block>
	   	  </fo:table-cell>  	
		  <xsl:call-template name="generateBoxCells">
		  	<xsl:with-param name="selected" select="$selected"/>
			<xsl:with-param name="boxnum" select="$boxnum+1"/>
		  </xsl:call-template>
	  </xsl:if> 
</xsl:template>

<xsl:template name="removetags">
	<xsl:param name="rawdata"/>
	<xsl:choose>
		<xsl:when test="string-length(substring-before($rawdata,'&gt;'))&gt;0">
			<xsl:variable name="str1" select="substring-before($rawdata,'&lt;')"/>
			<xsl:variable name="str2" select="substring-after($rawdata,'&gt;')"/>
			<xsl:variable name="res" select="concat($str1,$str2)"/>
			<xsl:call-template name="removetags">
				<xsl:with-param name="rawdata" select="$res"/>
			</xsl:call-template>
		</xsl:when>
		<xsl:otherwise>
			<xsl:value-of select="$rawdata"/>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

	<xsl:template name="FormatDate">
		<xsl:param name="DateTime"/>
		<xsl:variable name="year">
			<xsl:value-of select="substring($DateTime,1,4)"/>
		</xsl:variable>
		<xsl:variable name="mo">
			<xsl:value-of select="substring($DateTime,6,2)"/>
		</xsl:variable>
		<xsl:variable name="day">
			<xsl:value-of select="substring($DateTime,9,2)"/>
		</xsl:variable>
		 <xsl:choose>
      		<xsl:when test="$mo = '01'">Jan.</xsl:when>
		      <xsl:when test="$mo = '02'">Feb.</xsl:when>
		      <xsl:when test="$mo = '03'">Mar.</xsl:when>
		      <xsl:when test="$mo = '04'">Apr.</xsl:when>
		      <xsl:when test="$mo = '05'">May</xsl:when>
		      <xsl:when test="$mo = '06'">Jun.</xsl:when>
		      <xsl:when test="$mo = '07'">Jul.</xsl:when>
		      <xsl:when test="$mo = '08'">Aug.</xsl:when>
		      <xsl:when test="$mo = '09'">Sept.</xsl:when>
		      <xsl:when test="$mo = '10'">Oct.</xsl:when>
		      <xsl:when test="$mo = '11'">Nov.</xsl:when>
		      <xsl:when test="$mo = '12'">Dec.</xsl:when>
	    </xsl:choose>
		<xsl:text> </xsl:text>
		<xsl:value-of select="$day"/>,<xsl:text> </xsl:text><xsl:value-of select="$year"/>
	
	</xsl:template>
	
	<xsl:template name="FormatPeriod">
		<xsl:param name="period"></xsl:param>
		<xsl:choose>
			<xsl:when test="$period=1">Mid Year Review</xsl:when>
			<xsl:when test="$period=2">Annual Review</xsl:when>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
