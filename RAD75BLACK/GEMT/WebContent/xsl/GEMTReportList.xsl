<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" version = "1.0" >
<xsl:output method = "html" />
			
<xsl:template match = "/" >
	
	<TABLE CELLPADDING="0" CELLSPACING="0" WIDTH="100%">
		<TBODY>
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
				Action
			</xsl:element>
		</xsl:element>
		<xsl:for-each select="//GemtSummaryReportUIBean">
			<xsl:call-template name="generateRows">
				<xsl:with-param name="evenodd" select = "position() mod 2"/>
				<xsl:with-param name="reportid" select="Id"/>
				<xsl:with-param name="empname" select="Gemt_sum_empname"/>
				<xsl:with-param name="title" select="Gemt_sum_emptitle"/>
				<xsl:with-param name="reportdate" select="Gemt_sum_repdate"/>
				<xsl:with-param name="status" select="Gemt_sum_locked"/>
				<xsl:with-param name="realpath" select="Xml_real_path"/>
				<xsl:with-param name="empl_email" select="Gemt_sum_empemail"></xsl:with-param>
				<xsl:with-param name="showDirectReportsButton" select="ShowDirectReportsButton"></xsl:with-param>
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
		<xsl:param name="status"/>
		<xsl:param name="realpath"/>	
		<xsl:param name="empl_email"/>
		<xsl:param name="showDirectReportsButton"/>
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
						<xsl:choose>
							<xsl:when test="$status=''">
								(DRAFT)
							</xsl:when>
							<xsl:otherwise>
								(<xsl:value-of select="$status"/>)
							</xsl:otherwise>
						</xsl:choose>
				</xsl:element>
				<xsl:choose>
					<xsl:when test="$showDirectReportsButton='true'">
						<xsl:element name="A">
							<xsl:attribute name="HREF">javascript:getDirectReports('<xsl:value-of select="$reportid"/>','<xsl:value-of select="$empl_email"/>','directReports');</xsl:attribute>
							<xsl:element name="IMG">
								<xsl:attribute name="src"><xsl:value-of select="$realpath"/>/images/viewReport1.gif</xsl:attribute>
								<xsl:attribute name="width">12</xsl:attribute>
								<xsl:attribute name="height">12</xsl:attribute>
								<xsl:attribute name="hspace">4</xsl:attribute>
								<xsl:attribute name="border">0</xsl:attribute>
							</xsl:element>
						</xsl:element>
					</xsl:when>
				</xsl:choose>
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
				<xsl:element name="A">
					<xsl:attribute name="HREF">javascript:getAjaxData('<xsl:value-of select="$reportid"/>','quickView');</xsl:attribute>
					<xsl:attribute name="class">mpThemeCommandLink</xsl:attribute>
					Quick View
				</xsl:element>
				/
				<xsl:element name="A">
					<xsl:attribute name="HREF">javascript:openCenteredWindow('GEMTFOPServlet?reportid=<xsl:value-of select="$reportid"/>');</xsl:attribute>
					<xsl:attribute name="CLASS">mpThemeCommandLink</xsl:attribute>
					Download as PDF file
				</xsl:element>	
				<xsl:choose>
					<xsl:when test="$status=''">
						/
						<xsl:element name="A">
							<xsl:attribute name="HREF">javascript:deleteReport('deleteReport','<xsl:value-of select="$reportid"/>');</xsl:attribute>
							<xsl:attribute name="CLASS">mpThemeCommandLink</xsl:attribute>
							Delete
						</xsl:element>	
					</xsl:when>
				</xsl:choose>											
			</xsl:element>			
		</xsl:element>
		<xsl:element name="TR">
			<xsl:element name="TD">
				<xsl:attribute name="class"><xsl:value-of select="$rowclass"/></xsl:attribute>
				<xsl:attribute name="style">padding-left:15px;padding-right:15px;padding-bottom:15px;</xsl:attribute>
				<xsl:attribute name="colspan">4</xsl:attribute>
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