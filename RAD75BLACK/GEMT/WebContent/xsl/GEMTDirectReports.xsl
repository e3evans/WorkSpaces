<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" version = "1.0" >
<xsl:output method = "html" />
			
<xsl:template match = "/" >
	
	<TABLE CELLPADDING="0" CELLSPACING="0" WIDTH="100%">
		<TBODY>
		<TR>
			<TD colspan="2" valign="middle" style="background-color:#d47d18;color:#FFFFFF;font-size:8pt;font-weight:bold;vertical-align:middle;text-align:left;padding-top:5px;padding-bottom:5px;padding-left:10px;">
				<xsl:value-of select="//GemtManagersUIBean/Gemt_sum_managername"/> (Direct Reports)
			</TD>
			<TD align="center" valign="middle" style="background-color:#d47d18;padding-right:10px;padding-bottom:5px;">
				<xsl:call-template name="addUserButton">
					<xsl:with-param name="namespace" select="//GemtManagersUIBean/Portletnamespace"/>
					<xsl:with-param name="action" select="//GemtManagersUIBean/Gemt_sum_ui_action_pickuser"/>
				</xsl:call-template>
				
			</TD> 
		</TR>
		<TR>
			<TD class="GemtBlueHeader">
				User (Region)
			</TD>
			<TD class="GemtBlueHeader">
				E-mail
			</TD>
			<TD class="GemtBlueHeader" style="text-align:center;">
				Action
			</TD>
		
		</TR>
		<xsl:for-each select="//GemtManagersUIBean/GemtDirectReportUIBean">
			<xsl:call-template name="generateRows">
				<xsl:with-param name="evenodd" select = "position() mod 2"/>
				<xsl:with-param name="id" select="Id"/>
				<xsl:with-param name="empname" select="Gemt_sum_empname"/>
				<xsl:with-param name="email" select="Gemt_sum_empemail"/>
				<xsl:with-param name="region" select="Gemt_sum_region"/>
				<xsl:with-param name="removeaction" select = "Gemt_sum_ui_action_remove"/>
				<xsl:with-param name="namespace" select="//GemtManagersUIBean/Portletnamespace"/>
			</xsl:call-template>
		</xsl:for-each>
		</TBODY>
	</TABLE>
	
	
</xsl:template>

<xsl:template name="addUserButton">
	<xsl:param name="namespace"/>
	<xsl:param name="action"/>
	<xsl:element name="INPUT">
		<xsl:attribute name="class">GEMTCommandButtonOrange</xsl:attribute>
		<xsl:attribute name="onmouseover"><xsl:value-of select="$namespace"/>mouseover(this)</xsl:attribute>
		<xsl:attribute name="onmouseout"><xsl:value-of select="$namespace"/>mouseout(this)</xsl:attribute>
		<!-- <xsl:attribute name="onclick"><xsl:value-of select="$namespace"/>phantomButton('addUsers')</xsl:attribute>-->
		<xsl:attribute name="onclick"><xsl:value-of select="$namespace"/>_loadHtml('0','<xsl:value-of select="$action"/>')</xsl:attribute>
		<xsl:attribute name="type">BUTTON</xsl:attribute>
		<xsl:attribute name="value">Add User(s)</xsl:attribute>
		<xsl:attribute name="id">com.manpower.portal.portlet.gemt.gemtmanagerbutton.adduser</xsl:attribute>
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
				<xsl:element name="A">
					<xsl:attribute name="HREF">#</xsl:attribute>
					<xsl:attribute name="class">mpThemeCommandLink</xsl:attribute>
					<xsl:attribute name="onclick"><xsl:value-of select="$namespace"/>_loadHtml('<xsl:value-of select="$id"/>','<xsl:value-of select="$removeaction"/>');</xsl:attribute>
					Remove
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
