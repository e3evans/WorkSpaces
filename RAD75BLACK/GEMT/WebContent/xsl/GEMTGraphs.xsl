<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" version = "1.0" >

          <xsl:output method = "html" />
		
	
	<xsl:template match = "/" >
	<style>
	
</style>
	<!---
		<xsl:variable name="theZip" select="zip"/>
          <xsl:variable name="theTemp" select="java:getTemp($theZip)"/>
	-->
	
	<table class="tableSummaryGraph" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td colspan="5" class="tableTitle">Leadership Roles</td>
		</tr>
		<tr>
			<td class="GEMTlrSumBlue">Client</td>
			<xsl:call-template name="create-bar">
				<xsl:with-param name="colNum">1</xsl:with-param>
				<xsl:with-param name="score" select="//GemtSummaryReportUIBean/Gemt_sum_roleclient"/>
				<xsl:with-param name="barGif">blueBar.gif</xsl:with-param>		
				<xsl:with-param name="colFmt">colReport</xsl:with-param>
			</xsl:call-template>
		</tr>
		<tr>
			<td class="GEMTlrSumGreen" >People Leadership</td>
			<xsl:call-template name="create-bar">
				<xsl:with-param name="colNum">1</xsl:with-param>
				<xsl:with-param name="score" select="//GemtSummaryReportUIBean/Gemt_sum_rolepeople"/>
				<xsl:with-param name="barGif">greenBar.gif</xsl:with-param>		
				<xsl:with-param name="colFmt">colReport</xsl:with-param>
			</xsl:call-template>			
		</tr>
		<tr>
			<td class="GEMTlrSumOrange">Thought Leadership</td>
			<xsl:call-template name="create-bar">
				<xsl:with-param name="colNum">1</xsl:with-param>
				<xsl:with-param name="score" select="//GemtSummaryReportUIBean/Gemt_sum_rolethought"/>
				<xsl:with-param name="barGif">orangeBar.gif</xsl:with-param>		
				<xsl:with-param name="colFmt">colReport</xsl:with-param>
			</xsl:call-template>
		</tr>
		<tr>
			<td class="GEMTlrSumRed" >Day to Day</td>
			<xsl:call-template name="create-bar">
				<xsl:with-param name="colNum">1</xsl:with-param>
				<xsl:with-param name="score" select="//GemtSummaryReportUIBean/Gemt_sum_roledaytoday"/>
				<xsl:with-param name="barGif">redBar.gif</xsl:with-param>		
				<xsl:with-param name="colFmt">colReport</xsl:with-param>
			</xsl:call-template>
		</tr>
		<tr>
			<td class="colOneOverall" NOWRAP="true">Overall (Leadership):  </td>
			<xsl:call-template name="average-bar">
			<xsl:with-param name="param1" select="//GemtSummaryReportUIBean/Gemt_sum_roleclient"/>
			<xsl:with-param name="param2" select="//GemtSummaryReportUIBean/Gemt_sum_rolepeople"/>
			<xsl:with-param name="param3" select="//GemtSummaryReportUIBean/Gemt_sum_rolethought"/>
			<xsl:with-param name="param4" select="//GemtSummaryReportUIBean/Gemt_sum_roledaytoday"/>
			</xsl:call-template>
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
	<table class="tableSummaryGraph" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td colspan="5" class="tableTitle">Results Overview</td>
			</tr>
			<tr>
				<td class="GEMTlrSumBlue" NOWRAP="true">Scorecard - Financial</td>
				<xsl:call-template name="create-bar">
					<xsl:with-param name="colNum">1</xsl:with-param>
					<xsl:with-param name="score" select="//GemtSummaryReportUIBean/Gemt_sum_overviewscorefinan"/>
					<xsl:with-param name="barGif">blueBar.gif</xsl:with-param>		
					<xsl:with-param name="colFmt">colReport</xsl:with-param>
				</xsl:call-template>
			</tr>
			<tr>
				<td class="GEMTlrSumGreen" >Scorecard - KPI's</td>
				<xsl:call-template name="create-bar">
					<xsl:with-param name="colNum">1</xsl:with-param>
					<xsl:with-param name="score" select="//GemtSummaryReportUIBean/Gemt_sum_overviewscorekpi"/>
					<xsl:with-param name="barGif">greenBar.gif</xsl:with-param>		
					<xsl:with-param name="colFmt">colReport</xsl:with-param>
				</xsl:call-template>	
			</tr>
			
			<tr>
				<td class="colOneOverall" NOWRAP="true">Overall (Results):  </td>
				<xsl:call-template name="average-bar">
					<xsl:with-param name="param1" select="//GemtSummaryReportUIBean/Gemt_sum_overviewscorefinan"/>
					<xsl:with-param name="param2" select="//GemtSummaryReportUIBean/Gemt_sum_overviewscorekpi"/>
					<xsl:with-param name="param3">99</xsl:with-param>
					<xsl:with-param name="param4">0</xsl:with-param>
				</xsl:call-template>
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
				<xsl:with-param name="score" select="$averageScore"/>
				<xsl:with-param name="barGif">darkBlueBar.gif</xsl:with-param>		
				<xsl:with-param name="colFmt">colReport</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
	
	<xsl:template name="create-bar">
			<xsl:param name="colNum"/>
			<xsl:param name="score"/>
			<xsl:param name="barGif"/>
			<xsl:param name="colFmt"/>
			<xsl:variable name="maxCols" >4</xsl:variable>
			<xsl:variable name="lightFmt">colReport</xsl:variable>
			<xsl:variable name="darkFmt">colDarkReport</xsl:variable>
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
							<xsl:attribute name="width">20%</xsl:attribute>
							<xsl:element name="img">
								<xsl:attribute name="src"><xsl:value-of select="//GemtSummaryReportUIBean/Xml_real_path"/>/images/<xsl:value-of select="$barGif"/></xsl:attribute>
								<xsl:attribute name="alt"><xsl:value-of select="$score"/></xsl:attribute>
								<xsl:attribute name="title"><xsl:value-of select="$score"/></xsl:attribute>
								<xsl:attribute name="width">100%</xsl:attribute>
								<xsl:attribute name="height">15</xsl:attribute>
							</xsl:element>
						</xsl:element>
				     </xsl:when>
					<xsl:otherwise>
						<xsl:element name="td">
							<xsl:attribute name="class" ><xsl:value-of select="$colFmt"/></xsl:attribute>
							<xsl:attribute name="width">20%</xsl:attribute>
							<xsl:variable name="imgWidth" select="$colNum - $score"/>
							<xsl:if test="($imgWidth &lt; 1) and ($imgWidth &gt; 0)">
								<xsl:element name="img">
									<xsl:attribute name="src"><xsl:value-of select="//GemtSummaryReportUIBean/Xml_real_path"/>/images/<xsl:value-of select="$barGif"/></xsl:attribute>
									<xsl:attribute name="width"><xsl:value-of select="$imgWidth * 100"/>%</xsl:attribute>
									 <xsl:attribute name="alt"><xsl:value-of select="$score"/></xsl:attribute>
									 <xsl:attribute name="title"><xsl:value-of select="$score"/></xsl:attribute>
									<xsl:attribute name="height">15</xsl:attribute>
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
	

</xsl:stylesheet>
