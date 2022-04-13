<?xml version="1.0" encoding="UTF-8"?>
<!-- XSL 문서 : XSLT 정보를 저장한 XML 문서 -->
<!-- XSLT(eXtensible Stylesheet Language Template) : XML 기반의 언어로 작성된 파서(Parser) -->
<!-- => XML 문서를 전달받아 HTML 또는 XML 문서로 변환하는 정보 저장 -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- output : XML 텍스트 데이타의 변환 결과를 설정하는 엘리먼트 -->
	<xsl:output method="html" encoding="utf-8"/>
	<!-- template : XML 텍스트 데이타를 변환하기 위한 정보를 설정하는 상위 엘리먼트 -->
	<!-- => match 속성 : XML 문서의 최상위 태그명을 속성값으로 설정 -->
	<xsl:template match="books">
		<!-- TODO: Auto-generated template -->
		<ol>
			<!-- for-each : XML 텍스트 데이타의 반복 처리를 위해 제공되는 엘리먼트 -->
			<!-- => select 속성 : 반복 처리할 태그명을 속성값으로 설정 -->
			<xsl:for-each select="book">
				<!-- value-of : XML 텍스트 데이타의 값을 제공하는 엘리먼트 -->
				<!-- =>  select 속성 : 값을 제공할 태그명을 속성값으로 설정 -->
				<li><b><xsl:value-of select="title"/></b>[<xsl:value-of select="author"/>]</li>
			</xsl:for-each>
		</ol>
	</xsl:template>
</xsl:stylesheet>