<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" version = "1.0" >
          <xsl:output method = "html" />

          <xsl:template match = "/" >
		
			<table border="0" cellpadding="0" cellspacing="0" >
				<xsl:call-template name="generateHeader">
					<xsl:with-param name="className" select="//WcmSiteAreaBean/Classname"/>
				</xsl:call-template>
	               <xsl:for-each select = "//WcmSiteAreaBean" >
					<xsl:call-template name="generateRows">
						<xsl:with-param name="reportid" select="Id"/>
						<xsl:with-param name="styleClass">greenTableRow</xsl:with-param>
						<xsl:with-param name="rowId" select="SiteAreaId"/>
						<xsl:with-param name="displayName" select="SiteName"/>
						<xsl:with-param name="childCount" select="ChildrenCount"/>
						<xsl:with-param name="className" select="Classname"/>
						<xsl:with-param name="siteName" select="SiteName"/>
						<xsl:with-param name="siteType" select="Type"/>
					</xsl:call-template>
				
				</xsl:for-each>
				
			</table>
			<xsl:value-of select="//NORESULT"></xsl:value-of>
          </xsl:template>

		<xsl:template name="generateHeader">
			<xsl:param name="className"/>
			<xsl:element name="TR">
				<xsl:element name="TD">
					<xsl:attribute name="colspan">2</xsl:attribute>
					<xsl:choose>
						<xsl:when test="$className = 'WCM_Site'">
							<xsl:attribute name="class">greenTableHeader</xsl:attribute>
							Site
						</xsl:when>
						<xsl:when test="$className = 'WCM_SiteArea'">
							<xsl:attribute name="class">blueTableHeader</xsl:attribute>
							Site Area
						</xsl:when>
					</xsl:choose>
				</xsl:element>
			</xsl:element>
		</xsl:template>

		<xsl:template name="generateRows">
			<xsl:param name="styleClass"/>
			<xsl:param name="rowId"/>
			<xsl:param name="click"/>
			<xsl:param name="displayName"/>
			<xsl:param name="childCount"/>
			<xsl:param name="className"/>
			<xsl:param name="siteName"/>
			<xsl:param name="siteType"/>
			
			<xsl:element name="TR">
				<xsl:element name="TD">
					<xsl:attribute name="class">
						<xsl:choose>
							<xsl:when test="$className = 'WCM_Site'">greenTableRow</xsl:when>
							<xsl:when test="$className = 'WCM_SiteArea'">blueTableRow</xsl:when>
							<xsl:when test="$className ='WCM_Content'">redTableRow</xsl:when>
						</xsl:choose>
					</xsl:attribute>
					<xsl:attribute name="id"><xsl:value-of select="$rowId"/></xsl:attribute>
					<xsl:if test="$childCount &gt; 0 or $className='WCM_Content'">
						<xsl:attribute name="onmouseover">
							<xsl:choose>
								<xsl:when test="$className = 'WCM_Site'">changeClass(this,'greenTableRowHighlighted')</xsl:when>
								<xsl:when test="$className = 'WCM_SiteArea'">changeClass(this,'blueTableRowHighlighted')</xsl:when>
								<xsl:when test="$className ='WCM_Content'">changeContentClass(this,'redTableRowHighlighted')</xsl:when>
							</xsl:choose>
						</xsl:attribute>
						<xsl:attribute name="onmouseout">
							<xsl:choose>
								<xsl:when test="$className = 'WCM_Site'">changeClass(this,'greenTableRow')</xsl:when>
								<xsl:when test="$className = 'WCM_SiteArea'">changeClass(this,'blueTableRow')</xsl:when>
								<xsl:when test="$className = 'WCM_Content'">changeContentClass(this,'redTableRow')</xsl:when>
							</xsl:choose>
						</xsl:attribute>
						<xsl:attribute name="onclick">
							<xsl:choose>
								<xsl:when test="$className = 'WCM_Site'">wcmViewerCallAjax('<xsl:value-of select="$rowId"/>','<xsl:value-of select="$siteType"/>')</xsl:when>
								<xsl:when test="$className = 'WCM_SiteArea'">wcmViewerCallAjax('<xsl:value-of select="$rowId"/>','<xsl:value-of select="$siteType"/>')</xsl:when>
								<xsl:when test="$className = 'WCM_Content'">wcmViewerSelectContent(this,'<xsl:value-of select="$rowId"/>','<xsl:value-of select="$siteType"/>')</xsl:when>
							</xsl:choose>
							
						</xsl:attribute>
					</xsl:if>
					<xsl:value-of select="$displayName"/>
				</xsl:element>
				<xsl:element name="TD">
					<xsl:attribute name="valign">top</xsl:attribute>
					<xsl:attribute name="id"><xsl:value-of select="$rowId"/>_summaryAreaDiv</xsl:attribute>
				</xsl:element>	
			</xsl:element>
			
			
		
		</xsl:template>
</xsl:stylesheet>