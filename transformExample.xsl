<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template  match="skript"><html>
   <head>
     <meta http-equiv="content-type" content="text/html; charset=utf-8" />
   </head>
   <body>
     <xsl:apply-templates select="*" />
   </body>
</html>
 </xsl:template>

 <xsl:template  match="titelseite">Die Titelseite
 </xsl:template>

 <xsl:template  match="kapitel">
   <h1><xsl:number format="1. "/>
     <xsl:apply-templates select="self::*/attribute::titel" />
   </h1>
     <xsl:apply-templates select="child::*" />
 </xsl:template>

 <xsl:template  match="section">
   <h2><xsl:number format="1. "  level="multiple" count="kapitel|section"/>
     <xsl:apply-templates select="self::*/attribute::titel" />
   </h2>
     <xsl:apply-templates select="child::*" />
 </xsl:template>

 <xsl:template  match="subsection">
   <h3><xsl:number format="1. " level="multiple" count="kapitel|section|subsection"/>
     <xsl:apply-templates select="self::*/attribute::titel" />
   </h3>
     <xsl:apply-templates select="child::*" />
 </xsl:template>

 <xsl:template  match="subsubsection">
   <h4><xsl:number format="1. "  level="multiple" count="kapitel|section|subsection|subsubsection"/>
     <xsl:apply-templates select="self::*/attribute::titel" />
   </h4>
     <xsl:apply-templates select="child::*" />
 </xsl:template>

 <xsl:template  match="code">
   <pre>
     <xsl:apply-templates select="descendant::text()" />
   </pre>
 </xsl:template>


</xsl:stylesheet>
