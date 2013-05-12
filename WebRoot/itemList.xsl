<?xml version="1.0" encoding="UTF-8"?>
<!-- Example:
<item>
  <id>1</id>   
  <name>apple</name>
  <price>2.1</price>
  <stock>20</stock> 
</item>
-->
<!--
    Document   : ItemList.xsl
    Created on : 2013年5月11日, 下午4:37
    Author     : wnt
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="itemlist">
        <table border="0">
    <tr bgcolor="silver" cellspacing="0">
    <td>
        <strong>Id</strong>
    </td>
    <td>
        <strong>Name</strong>
    </td>
    <td>
        <strong>Price</strong>
    </td>
    <td>
        <strong>Stock</strong>
    </td>
    </tr>
        <xsl:apply-templates/>
   </table>
    </xsl:template>
 
 <xsl:template match="itemlist">   
    <tr bgcolor="#FFDC75" >
        <td>
            <xsl:value-of select="id"/>
        </td>
        <td>
            <xsl:value-of select="name"/>
        </td>
        <td>
            <xsl:value-of select="price"/>
        </td>
         <td>
            <xsl:value-of select="stock"/>
        </td>
    </tr>
</xsl:template>

</xsl:stylesheet>
