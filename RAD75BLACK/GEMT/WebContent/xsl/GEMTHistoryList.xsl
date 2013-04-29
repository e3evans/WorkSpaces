<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" 
	version = "1.0">
<xsl:output method = "html" />
			
<xsl:template match = "/" >

	<TABLE CELLPADDING="0" CELLSPACING="0" WIDTH="100%">
		<TBODY>
		<TR>
			<TD colspan="4">
			</TD>
			<TD align="center" style="padding-right:20px;">
				<xsl:element name="A">
					<xsl:attribute name="HREF">javascript:getCheckedReports('reports')</xsl:attribute>
					<xsl:attribute name="class">mpThemeCommandLink</xsl:attribute>
					Quick View Selected
				</xsl:element>
			</TD>
		</TR>
		<xsl:element name="TR">
			<xsl:element name="TD">
				<xsl:attribute name="class">
					GemtBlueHeader
				</xsl:attribute>
				Name
			</xsl:element>
			<xsl:element name="TD">
				<xsl:attribute name="class">
					GemtBlueHeader
				</xsl:attribute>
				Title
			</xsl:element>
			<xsl:element name="TD">
				<xsl:attribute name="class">
					GemtBlueHeader
				</xsl:attribute>
				Date
			</xsl:element>
			<xsl:element name="TD">
				<xsl:attribute name="class">
					GemtBlueHeader
				</xsl:attribute>
				<xsl:attribute name="style">
					text-align:center;
				</xsl:attribute>
				Action
			</xsl:element>
			<xsl:element name="TD">
				<xsl:attribute name="class">
					GemtBlueHeader
				</xsl:attribute>
				<xsl:attribute name="style">
					text-align:center;
				</xsl:attribute>
				 
			</xsl:element>
		</xsl:element>
		<xsl:for-each select="//GemtSummaryReportUIBean">
			<xsl:call-template name="generateRows">
				<xsl:with-param name="evenodd" select = "position() mod 2"/>
				<xsl:with-param name="reportid" select="Id"/>
				<xsl:with-param name="empname" select="Gemt_sum_empname"/>
				<xsl:with-param name="title" select="Gemt_sum_emptitle"/>
				<xsl:with-param name="reportdate" select="Gemt_sum_repdate"/>
				<xsl:with-param name="realpath" select="Xml_real_path"/>
			</xsl:call-template>
		</xsl:for-each>
		</TBODY>
	</TABLE>
	
	
</xsl:template>
<xsl:template name="generateRows">
		<xsl:param name="evenodd"/>
		<xsl:param name="reportid"/>
		<xsl:param name="empname"/>
		<xsl:param name="title"/>
		<xsl:param name="reportdate"/>
		<xsl:param name="realpath"/>
		<xsl:variable name="rowclass">
			<xsl:choose>
				<xsl:when test="$evenodd=0">
					rowClass1
				</xsl:when>
				<xsl:otherwise>
					rowClass2
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:element name="TR">
			<xsl:element name="TD">
				<xsl:attribute name="class"><xsl:value-of select="$rowclass"/></xsl:attribute>
				<xsl:attribute name="valign">top</xsl:attribute>
				<xsl:attribute name="style">padding-top:5px;padding-bottom:5px;padding-left:10px;padding-right:10px;</xsl:attribute>
				<xsl:element name="A">
				<xsl:attribute name="HREF">javascript:submitJSFForm('viewReports','<xsl:value-of select="$reportid"/>');</xsl:attribute>
				<xsl:attribute name="CLASS">mpThemeCommandLink</xsl:attribute>
				<xsl:value-of select="$empname"/>
				</xsl:element>
			</xsl:element>
			<xsl:element name="TD">
				<xsl:attribute name="class"><xsl:value-of select="$rowclass"/></xsl:attribute>
				<xsl:attribute name="valign">top</xsl:attribute>
				<xsl:value-of select="$title"/>
			</xsl:element>
			<xsl:element name="TD">
				<xsl:attribute name="class"><xsl:value-of select="$rowclass"/></xsl:attribute>
				<xsl:attribute name="valign">top</xsl:attribute>
				<xsl:call-template name="FormatDate"><xsl:with-param name="DateTime" select="$reportdate"/></xsl:call-template>
			</xsl:element>
			<xsl:element name="TD">
				<xsl:attribute name="class"><xsl:value-of select="$rowclass"/></xsl:attribute>
				<xsl:attribute name="valign">top</xsl:attribute>
				<xsl:attribute name="align">center</xsl:attribute>
				
				<xsl:element name="input">
					<xsl:attribute name="type">checkbox</xsl:attribute>
					<xsl:attribute name="value"><xsl:value-of select="$reportid"/></xsl:attribute>
					<xsl:attribute name="name">reports</xsl:attribute>
				</xsl:element>
			</xsl:element>
			<xsl:element name="TD">
				<xsl:attribute name="class"><xsl:value-of select="$rowclass"/></xsl:attribute>
				<xsl:attribute name="valign">top</xsl:attribute>
				<xsl:attribute name="align">center</xsl:attribute>
				
				<xsl:element name="A">
				<xsl:attribute name="HREF"><xsl:value-of select="$realpath"/>/GEMTFOPServlet?reportid=<xsl:value-of select="$reportid"/></xsl:attribute>
				<xsl:attribute name="CLASS">mpThemeCommandLink</xsl:attribute>
				Download as PDF file
				</xsl:element>
			</xsl:element>
		</xsl:element>
		<xsl:element name="TR">
			<xsl:element name="TD">
				<xsl:attribute name="class"><xsl:value-of select="$rowclass"/></xsl:attribute>
				<xsl:attribute name="style">padding-left:15px;padding-right:15px;padding-bottom:15px;</xsl:attribute>
				<xsl:attribute name="colspan">5</xsl:attribute>
				<xsl:attribute name="id"><xsl:value-of select="$reportid"/>_summaryAreaDiv</xsl:attribute>
			</xsl:element>
		</xsl:element>
		
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