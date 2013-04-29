<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" version = "1.0" >

          <xsl:output method = "html" />
		
	
	<xsl:template match = "/" >


	<div style="padding:10px;">
	<div style="text-align:right;width:96%;padding-right:10px;">
	<xsl:element name="A">
		<xsl:attribute name="HREF">javascript:resetHistory()</xsl:attribute>
		<xsl:attribute name="class">mpThemeCommandLink</xsl:attribute>
		<xsl:attribute name="style">padding-bottom:3px;</xsl:attribute>
		Close Quick View
	</xsl:element>
	</div>
	<table class="tableSummaryGraph" cellpadding="0" cellspacing="0" style="width:96%;" >
		<tr>
			<td colspan="5" class="tableTitle">Leadership Roles</td>
		</tr>
		<tr>
			<td class="GEMTlrSumBlue">Client</td>
			<td colspan="4">
		<xsl:for-each select="//GemtSummaryReportUIBean">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<xsl:call-template name="create-bar">
						<xsl:with-param name="colNum">1</xsl:with-param>
						<xsl:with-param name="score" select="Gemt_sum_roleclient"/>
						<xsl:with-param name="barGif">blueBar.gif</xsl:with-param>		
						<xsl:with-param name="colFmt">colReport</xsl:with-param>
						<xsl:with-param name="repDate"><xsl:call-template name="FormatDate"><xsl:with-param name="DateTime" select="Gemt_sum_repdate"/></xsl:call-template></xsl:with-param>
					</xsl:call-template>
				</tr>
			</table>
		</xsl:for-each>
			</td>
		</tr>
		<tr>
			<td class="GEMTlrSumGreen" >People Leadership</td>
			<td colspan="4">
			<xsl:for-each select="//GemtSummaryReportUIBean">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
			<xsl:call-template name="create-bar">
				<xsl:with-param name="colNum">1</xsl:with-param>
				<xsl:with-param name="score" select="Gemt_sum_rolepeople"/>
				<xsl:with-param name="barGif">greenBar.gif</xsl:with-param>		
				<xsl:with-param name="colFmt">colReport</xsl:with-param>
				<xsl:with-param name="repDate"><xsl:call-template name="FormatDate"><xsl:with-param name="DateTime" select="Gemt_sum_repdate"/></xsl:call-template></xsl:with-param>
			</xsl:call-template>	
				</tr>
			</table>
		</xsl:for-each>
			</td>		
		</tr>
		<tr>
			<td class="GEMTlrSumOrange">Thought Leadership</td>
			<td colspan="4">
			<xsl:for-each select="//GemtSummaryReportUIBean">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
			<xsl:call-template name="create-bar">
				<xsl:with-param name="colNum">1</xsl:with-param>
				<xsl:with-param name="score" select="Gemt_sum_rolethought"/>
				<xsl:with-param name="barGif">orangeBar.gif</xsl:with-param>		
				<xsl:with-param name="colFmt">colReport</xsl:with-param>
				<xsl:with-param name="repDate"><xsl:call-template name="FormatDate"><xsl:with-param name="DateTime" select="Gemt_sum_repdate"/></xsl:call-template></xsl:with-param>
			</xsl:call-template>
				</tr>
			</table>
			</xsl:for-each>
			</td>		
		</tr>
		<tr>
			<td class="GEMTlrSumRed" >Day to Day</td>
			<td colspan="4">
			<xsl:for-each select="//GemtSummaryReportUIBean">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
			<xsl:call-template name="create-bar">
				<xsl:with-param name="colNum">1</xsl:with-param>
				<xsl:with-param name="score" select="Gemt_sum_roledaytoday"/>
				<xsl:with-param name="barGif">redBar.gif</xsl:with-param>		
				<xsl:with-param name="colFmt">colReport</xsl:with-param>
				<xsl:with-param name="repDate"><xsl:call-template name="FormatDate"><xsl:with-param name="DateTime" select="Gemt_sum_repdate"/></xsl:call-template></xsl:with-param>
			</xsl:call-template>
				</tr>
			</table>
			</xsl:for-each>
			</td>		
		</tr>
		<tr>
			<td class="colOneOverall" NOWRAP="true">Overall (Leadership): </td>
			<td colspan="4">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
			<xsl:call-template name="average-bar">
			<xsl:with-param name="param1" select="sum(//GemtSummaryReportUIBean/Gemt_sum_roleclient) div count(//GemtSummaryReportUIBean/Gemt_sum_roleclient)"/>
			<xsl:with-param name="param2" select="sum(//GemtSummaryReportUIBean/Gemt_sum_rolepeople) div count(//GemtSummaryReportUIBean/Gemt_sum_rolepeople)"/>
			<xsl:with-param name="param3" select="sum(//GemtSummaryReportUIBean/Gemt_sum_rolethought) div count(//GemtSummaryReportUIBean/Gemt_sum_rolethought)"/>
			<xsl:with-param name="param4" select="sum(//GemtSummaryReportUIBean/Gemt_sum_roledaytoday) div count(//GemtSummaryReportUIBean/Gemt_sum_roledaytoday)"/>
			</xsl:call-template>
				</tr>
			</table>
			</td>
		</tr>
		<tr>

			<td class="colOneOverall" ><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
			<td class="colReport" width="20%" align="right">1</td>
			<td class="colDarkReport" width="20%" align="right">2</td>
			<td class="colReport" width="20%" align="right">3</td>
			<td class="colDarkReport" width="20%" align="right">4</td>
		</tr>
		
		<tr>
			<td colspan="5" class="tableLegend">
				1-Unsatisfactory | 2-Developing | 3-Proficient | 4-Outstanding
			</td>
		</tr>
	</table>
	</div>
	<div style="padding:10px;">
	<table class="tableSummaryGraph" cellpadding="0" cellspacing="0" style="width:96%;" >
			<tr>
				<td colspan="5" class="tableTitle">Results Overview</td>
			</tr>
			<tr>
				<td class="GEMTlrSumBlue" NOWRAP="true">Scorecard - Financial</td>
				<td colspan="4">
				<xsl:for-each select="//GemtSummaryReportUIBean">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
				<xsl:call-template name="create-bar">
					<xsl:with-param name="colNum">1</xsl:with-param>
					<xsl:with-param name="score" select="Gemt_sum_overviewscorefinan"/>
					<xsl:with-param name="barGif">blueBar.gif</xsl:with-param>		
					<xsl:with-param name="colFmt">colReport</xsl:with-param>
					<xsl:with-param name="repDate"><xsl:call-template name="FormatDate"><xsl:with-param name="DateTime" select="Gemt_sum_repdate"/></xsl:call-template></xsl:with-param>
				</xsl:call-template>
					</tr>
				</table>
				</xsl:for-each>
				</td>
			</tr>
			<tr>
				<td class="GEMTlrSumGreen" >Scorecard - KPI's</td>
				<td colspan="4">
				<xsl:for-each select="//GemtSummaryReportUIBean">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
				<xsl:call-template name="create-bar">
					<xsl:with-param name="colNum">1</xsl:with-param>
					<xsl:with-param name="score" select="Gemt_sum_overviewscorekpi"/>
					<xsl:with-param name="barGif">greenBar.gif</xsl:with-param>		
					<xsl:with-param name="colFmt">colReport</xsl:with-param>
					<xsl:with-param name="repDate"><xsl:call-template name="FormatDate"><xsl:with-param name="DateTime" select="Gemt_sum_repdate"/></xsl:call-template></xsl:with-param>
				</xsl:call-template>
					</tr>
				</table>
				</xsl:for-each>	
				</td>
			</tr>
			
			<tr>
				<td class="colOneOverall" NOWRAP="true">Overall (Results):  </td>
				<td colspan="4">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
				<xsl:call-template name="average-bar">
					<xsl:with-param name="param1" select="sum(//GemtSummaryReportUIBean/Gemt_sum_overviewscorefinan) div count(//GemtSummaryReportUIBean/Gemt_sum_overviewscorefinan)"/>
					<xsl:with-param name="param2" select="sum(//GemtSummaryReportUIBean/Gemt_sum_overviewscorekpi) div count(//GemtSummaryReportUIBean/Gemt_sum_overviewscorekpi)"/>
					<xsl:with-param name="param3">99</xsl:with-param>
					<xsl:with-param name="param4">0</xsl:with-param>
					<xsl:with-param name="repDate"><xsl:call-template name="FormatDate"><xsl:with-param name="DateTime" select="Gemt_sum_repdate"/></xsl:call-template></xsl:with-param>
				</xsl:call-template>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="colOneOverall" ><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
				<td class="colReport" width="20%" align="right">1</td>
				<td class="colDarkReport" width="20%" align="right">2</td>
				<td class="colReport" width="20%" align="right">3</td>
				<td class="colDarkReport" width="20%" align="right">4</td>
			</tr>
			<tr>
			
				<td colspan="5" class="tableLegend">
					1-Unsatisfactory | 2-Developing | 3-Proficient | 4-Outstanding
				</td>
			</tr>
		</table>
	</div>	
	

		
	</xsl:template>	
	
	<xsl:template name="average-bar">
		<xsl:param name="param1"/>
		<xsl:param name="param2"/>
		<xsl:param name="param3"/>
		<xsl:param name="param4"/>
		
		<xsl:variable name="averageScore" >
			<xsl:choose>
				<xsl:when test="$param3=99">
					<xsl:value-of select="($param1+$param2) div 2"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="($param1+$param2+$param3+$param4) div 4"/>
				</xsl:otherwise>
			</xsl:choose>
			
		</xsl:variable>
		
		<xsl:call-template name="create-bar">
				<xsl:with-param name="colNum">1</xsl:with-param>
				<xsl:with-param name="score"><xsl:value-of select="format-number($averageScore,'#.##')"/></xsl:with-param>
				<xsl:with-param name="barGif">darkBlueBar.gif</xsl:with-param>		
				<xsl:with-param name="colFmt">colReport</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
	

	
	<xsl:template name="create-bar">
			<xsl:param name="colNum"/>
			<xsl:param name="score"/>
			<xsl:param name="barGif"/>
			<xsl:param name="colFmt"/>
			<xsl:param name="repDate"/>
			<xsl:variable name="maxCols" >4</xsl:variable>
			<xsl:variable name="lightFmt">colReport</xsl:variable>
			<xsl:variable name="darkFmt">colDarkReport</xsl:variable>
			<xsl:variable name="imgSrc"><xsl:value-of select="//GemtSummaryReportUIBean/Xml_real_path"/>/images/<xsl:value-of select="$barGif"/></xsl:variable>
			<xsl:variable name="nextFmt">
				<xsl:choose>
					<xsl:when test="$colFmt=$lightFmt"><xsl:value-of select="$darkFmt"/></xsl:when>
					<xsl:otherwise><xsl:value-of select="$lightFmt"/></xsl:otherwise>
				</xsl:choose>
			</xsl:variable>
			
			<xsl:if test="$colNum &lt; $maxCols+1">
				<xsl:choose>
					<xsl:when test="$colNum &lt;= $score">
						<xsl:element name="td">
							<xsl:attribute name="class" ><xsl:value-of select="$colFmt"/></xsl:attribute>
							<xsl:attribute name="style">padding-bottom:3px;padding-top:3px;</xsl:attribute>
							<xsl:attribute name="width">25%</xsl:attribute>
							
								<xsl:element name="div">
									<xsl:attribute name="width">100%</xsl:attribute>
									<xsl:attribute name="height">20</xsl:attribute>
									<xsl:attribute name="style">padding-left:5px;color:#FFFFFF;font-family:Verdana;font-size:8pt;background-image:url(<xsl:value-of select="$imgSrc"/>);</xsl:attribute>
								<xsl:choose>
									<xsl:when test="$repDate='' and $colNum=1">
										Average Score: <xsl:value-of select="$score"/>
									</xsl:when>
									<xsl:when test="$colNum=1">
										<xsl:value-of select="$repDate"/>
									</xsl:when>
								</xsl:choose>
								<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>
								</xsl:element>
						</xsl:element>
				     </xsl:when>
					<xsl:otherwise>
						<xsl:element name="td">
							<xsl:attribute name="class" ><xsl:value-of select="$colFmt"/></xsl:attribute>
							<xsl:attribute name="style">padding-bottom:3px;padding-top:3px;</xsl:attribute>
							<xsl:attribute name="width">25%</xsl:attribute>
							
							<xsl:variable name="imgWidth" select="$colNum - $score"/>
							<xsl:if test="($imgWidth &lt; 1) and ($imgWidth &gt; 0)">
								<xsl:element name="div">
									<xsl:attribute name="height">20</xsl:attribute>
									<xsl:attribute name="style">width:<xsl:value-of select="$imgWidth * 100"/>%;color:#FFFFFF;font-family:Verdana;font-size:8pt;background-image:url(<xsl:value-of select="$imgSrc"/>);</xsl:attribute>
									<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>
								</xsl:element>
							</xsl:if>
						</xsl:element>
					</xsl:otherwise>
				</xsl:choose>
			     
		        <xsl:call-template name="create-bar">
		        <xsl:with-param name="colNum" select="$colNum +1"/>
				<xsl:with-param name="score" select="$score"/>
				<xsl:with-param name="barGif" select="$barGif"/>
				<xsl:with-param name="colFmt" select="$nextFmt"/>
		        </xsl:call-template>
		     </xsl:if>
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

</xsl:stylesheet>
