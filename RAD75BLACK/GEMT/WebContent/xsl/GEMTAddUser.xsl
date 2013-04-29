<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" version = "1.0" >
<xsl:output method = "html" />

		
<xsl:template match = "/" >
	
	<xsl:variable name="numberoftabs"><xsl:value-of select="count(//Region)"/></xsl:variable>
	<xsl:variable name="starttab">1</xsl:variable>
	
	<TABLE CELLPADDING="0" CELLSPACING="0" WIDTH="100%">
		<TR>
			<TD style="background-color:#d47d18;">
				<TABLE CELPADDING="0" CELLSPACING="0" WIDTH="100%">
					<TR>
						<TD style="background-color:#d47d18;color:#FFFFFF;font-size:8pt;font-weight:bold;vertical-align:middle;text-align:left;padding-top:5px;padding-bottom:5px;padding-left:10px;">
							LDAP Users
						</TD>
						<TD valign="middle"  style="background-color:#d47d18;color:#FFFFFF;font-size:8pt;font-weight:bold;vertical-align:middle;text-align:right;padding-top:5px;padding-bottom:5px;padding-left:10px;">
							<xsl:call-template name="addUserButton">
								<xsl:with-param name="namespace" select="//Portletnamespace"/>
								<xsl:with-param name="action" select="//GemtDirectReportUIBean/Gemt_sum_ui_action_adduser"/>
							</xsl:call-template>
						</TD>
					</TR>
				</TABLE>
			</TD>
			<TD style="background-color:#d47d18;">
			
			</TD>
		</TR>
		<TR>
			<TD valign="top">	
				<TABLE CELLPADDING="0" CELLSPACING="0" WIDTH="100%">
					<TBODY>					
					<TR>
						<TD class="GemtBlueHeader">
							User (Region)
						</TD>
						<TD class="GemtBlueHeader">
							E-mail
						</TD>
						<TD class="GemtBlueHeader" style="text-align:center;">
							Select
						</TD>
					
					</TR>
					
					<xsl:if test="count(//GemtDirectReportUIBean)=0">
						<TR>
							<TD colspan="3" style="color:red;text-align:center;vertical-align:middle;height:100%">
								No users in selected locality.
							</TD>
						</TR>
					</xsl:if>
					<xsl:for-each select="//GemtDirectReportUIBean">
						<xsl:sort select="Gemt_sum_empname"/>
						<xsl:call-template name="generateRows">
							<xsl:with-param name="evenodd" select = "position() mod 2"/>
							<xsl:with-param name="id" select="Id"/>
							<xsl:with-param name="empname" select="Gemt_sum_empname"/>
							<xsl:with-param name="email" select="Gemt_sum_empemail"/>
							<xsl:with-param name="region" select="Gemt_sum_region"/>
							<xsl:with-param name="removeaction" select = "Gemt_sum_ui_action_remove"/>
							<xsl:with-param name="namespace" select="//Portletnamespace"/>
						</xsl:call-template>
					</xsl:for-each>
					</TBODY>
				</TABLE>
			</TD>
			<TD valign="top" width="25%" class="GemtBlueHeader">

				<TABLE cellspacing="1" width="100%">
					<TR>
						<TD rowspan="2" valign="middle" class="GemtBlueHeader">
							<xsl:element name="INPUT">
								<xsl:attribute name="TYPE">BUTTON</xsl:attribute>
								<xsl:attribute name="VALUE">&lt;&lt; Filter</xsl:attribute>
								<xsl:attribute name="CLASS">GemtTab3</xsl:attribute>
								<xsl:attribute name="onclick">
									<xsl:value-of select="//Portletnamespace"/>addFilter()
								</xsl:attribute>
							</xsl:element>
						</TD>
						<TD style="padding-left:10px;vertical-align:middle;" class="GemtBlueHeader">
						Filter by region: 
						</TD>
						<TD width="10%" rowspan="2" valign="middle" class="GemtBlueHeader">
							Hold down the CTRL key for multiple selections.
						</TD>
					</TR>
					<TR>
						<TD style="padding-left:10px;vertical-align:middle;" class="GemtBlueHeader"> 
						<SELECT MULTIPLE="TRUE" SIZE="10" width="100%" id="filterbyregion">
							<xsl:for-each select="//Region">
								<xsl:call-template name="createSelector">
									<xsl:with-param name="region" select="Region_name"/>
									<xsl:with-param name="selected" select="Region_selected"/>
									<xsl:with-param name="tabnumber" select="position()"/>
								</xsl:call-template>
							</xsl:for-each>
						</SELECT>	
						</TD>
					</TR>
				</TABLE>
		
			</TD>
		</TR>
	</TABLE>
	
	
</xsl:template>

<xsl:template name="createSelector">
	<xsl:param name="region"/>
	<xsl:param name="selected"/>
	<xsl:param name="tabnumber"/>
	<xsl:element name="OPTION">
		<xsl:attribute name="value">
			<xsl:value-of select="$region"/>
		</xsl:attribute>
		<xsl:if test="$selected='true'">
			<xsl:attribute name="SELECTED"/>
		</xsl:if>
		<xsl:value-of select="$region"/>
	</xsl:element>
</xsl:template>
<xsl:template name="createTabs">
	<xsl:param name="region"/>
	<xsl:param name="tabnumber"/>
	<xsl:variable name="stylenumber">
		<xsl:call-template name="incrementTabNumber">
			<xsl:with-param name="tabPosition" select="$tabnumber"/>
		</xsl:call-template>
	</xsl:variable>
	<xsl:variable name="class">GemtTab<xsl:value-of select="$stylenumber"/></xsl:variable>
	<xsl:element name="TD">
		<xsl:attribute name="class">
			<xsl:value-of select="$class"/>
		</xsl:attribute>
		<xsl:attribute name="id"><xsl:value-of select="$region"/></xsl:attribute>
		<xsl:attribute name="width">10%</xsl:attribute>
		<xsl:attribute name="onmouseover">changeClass(this, &apos;<xsl:value-of select="$class"/>High&apos;)</xsl:attribute>
		<xsl:attribute name="onmouseout">changeClass(this,&apos;<xsl:value-of select="$class"/>&apos;)</xsl:attribute>
		<xsl:value-of select="$region"/>
	</xsl:element>
</xsl:template>

<xsl:template name="incrementTabNumber">
	<xsl:param name="tabPosition"/>
	<xsl:if test="$tabPosition &lt;= 4">
		<xsl:value-of select="$tabPosition"/>
	</xsl:if>
	<xsl:if test="$tabPosition &gt; 4">
		<xsl:call-template name="incrementTabNumber">
			<xsl:with-param name="tabPosition" select="$tabPosition - 4"/>
		</xsl:call-template>
	</xsl:if>
</xsl:template>

<xsl:template name="addUserButton">
	<xsl:param name="namespace"/>
	<xsl:param name="action"/>
	<xsl:element name="INPUT">
		<xsl:attribute name="class">GEMTCommandButtonOrange</xsl:attribute>
		<xsl:attribute name="onmouseover"><xsl:value-of select="$namespace"/>mouseover(this)</xsl:attribute>
		<xsl:attribute name="onmouseout"><xsl:value-of select="$namespace"/>mouseout(this)</xsl:attribute>
		<!-- <xsl:attribute name="onclick"><xsl:value-of select="$namespace"/>phantomButton('addUsers')</xsl:attribute>-->
		<xsl:attribute name="onclick"><xsl:value-of select="$namespace"/>addUser();</xsl:attribute>
		<xsl:attribute name="type">BUTTON</xsl:attribute>
		<xsl:attribute name="value">Add to Direct Reports</xsl:attribute>
		<xsl:attribute name="id">com.manpower.portal.portlet.gemt.gemtmanagerbutton.adduser</xsl:attribute>
		<xsl:attribute name="style">text-align:right;</xsl:attribute>
	</xsl:element>

</xsl:template>

<xsl:template name="generateRows">
		<xsl:param name="evenodd"/>
		<xsl:param name="id"/>
		<xsl:param name="empname"/>
		<xsl:param name="email"/>
		<xsl:param name="region"/>
		<xsl:param name="removeaction"/>
		<xsl:param name="namespace"/>
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
				<xsl:value-of select="$empname"/> (<xsl:value-of select="$region"/>)
			</xsl:element>
			<xsl:element name="TD">
				<xsl:attribute name="class"><xsl:value-of select="$rowclass"/></xsl:attribute>
				<xsl:attribute name="valign">top</xsl:attribute>
				<xsl:attribute name="style">padding-top:5px;padding-bottom:5px;padding-left:10px;padding-right:10px;</xsl:attribute>
				<xsl:value-of select="$email"/>
			</xsl:element>
			<xsl:element name="TD">
				<xsl:attribute name="class"><xsl:value-of select="$rowclass"/></xsl:attribute>
				<xsl:attribute name="style">text-align:center;</xsl:attribute>
				<xsl:attribute name="valign">top</xsl:attribute>
				<xsl:element name="INPUT">
					<xsl:attribute name="type">checkbox</xsl:attribute>
					<xsl:attribute name="id">checkbox_addusers</xsl:attribute>
					<xsl:attribute name="name">checkbox_addusers</xsl:attribute>
					<xsl:attribute name="value"><xsl:value-of select="$email"/></xsl:attribute>
				</xsl:element>
			</xsl:element>
		</xsl:element>
		<xsl:element name="TR">
			<xsl:element name="TD">
				<xsl:attribute name="class"><xsl:value-of select="$rowclass"/></xsl:attribute>
				<xsl:attribute name="style">padding-left:15px;padding-right:15px;padding-bottom:15px;</xsl:attribute>
				<xsl:attribute name="colspan">3</xsl:attribute>
				<xsl:attribute name="id"><xsl:value-of select="$id"/>_summaryAreaDiv</xsl:attribute>
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
