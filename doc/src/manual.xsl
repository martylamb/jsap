<xsl:stylesheet version = '1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>

<xsl:template match="manual"> 
	<html>
		<head></head>
		<body>
    		<xsl:call-template name="contents"/>
    		<xsl:for-each select="chapter"> 
				<xsl:sort data-type="number" select="@number"/>
    			<xsl:apply-templates select="."/>
    		</xsl:for-each>
    	</body>
    </html>
</xsl:template>

<xsl:template match="chapter">
	<a>
		<xsl:attribute name="name">
			<xsl:text>chapter</xsl:text>
			<xsl:value-of select="@number"/>
		</xsl:attribute>
	</a>
			 
     <h2 style="color:blue"> 
          <xsl:text>Chapter </xsl:text> 
          <xsl:value-of select="@number"/> 
          <xsl:text>: </xsl:text>
          <xsl:value-of select="title"/>
     </h2><br/>
	 <xsl:apply-templates select="body"/>
</xsl:template>

<xsl:template match="//section/title">
	<h3 style="color:red">
		<xsl:value-of select="."/>
	</h3>
</xsl:template>

<xsl:template match="body">
	<xsl:apply-templates/>
</xsl:template>


<xsl:template match="p">
	<p>
	<xsl:apply-templates/>
	</p>
</xsl:template>

<xsl:template match="ol">
	<ol>
	<xsl:apply-templates/>
	</ol>
</xsl:template>

<xsl:template match="li">
	<li>
	<xsl:apply-templates/>
	</li>
</xsl:template>

<xsl:template match="console">
	<br/>
	<center>
		<table width="80%" cellpadding="0" cellspacing="0" border="1">
			<tr>
				<td bgcolor="#000000" align="left">
					<font color="#ffffff" size="2">
						<xsl:apply-templates/><br/>
					</font>
				</td>
			</tr>
		</table>
	</center>
</xsl:template>

<xsl:template match="console/input">
	<pre><br/>[mlamb@kang]$ <b><xsl:apply-templates/></b></pre>
</xsl:template>

<xsl:template match="console/output">
	<pre><br/><xsl:apply-templates/></pre>
</xsl:template>

<xsl:template match="example-src">
	<center>
		<table width="80%" cellpadding="10" cellspacing="0" border="1">
			<tr>
				<td bgcolor="#eeeeee">
					<pre>
						<xsl:value-of select="."/>
					</pre>
				</td>
			</tr>
			<tr>
				<td align="center">
					<b>
						<xsl:text>Figure </xsl:text>
						<xsl:value-of select="@id"/>
						<xsl:text>: </xsl:text>
						<xsl:value-of select="@caption"/>
					</b>
				</td>
			</tr>
		</table>
	</center>
</xsl:template>

<!-- Displays the table of contents. -->
<xsl:template name="contents"> 
	<xsl:for-each select="chapter"> 
		<xsl:sort data-type="number" select="@number"/>
		<a>
		<xsl:attribute name="href">
			<xsl:text>#chapter</xsl:text>
			<xsl:value-of select="@number"/>
		</xsl:attribute>
		<xsl:text>Chapter </xsl:text>
        <xsl:value-of select="@number"/>
        <xsl:text>:</xsl:text>
        <xsl:value-of select="/manual/chapter/title"/>
        </a>
		<br/>
	</xsl:for-each>
</xsl:template>

</xsl:stylesheet> 