<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>JasperReports - Web Application Sample</title>
<style type="text/css">
<!--
.title
{
	font-decoration:none;
	font-family:'DejaVu Sans', Arial, Helvetica, sans-serif;
	font-size:10pt;
	font-weight:bold;
	color:#000000;
}
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.js"></script>
<script type="text/javascript">
function doSetSession() {
	$.ajaxSetup({async:false, type:'POST'});
	var rtValue = false;
	return $.getJSON("toPrint.jsp?isSetSession=1", 
		{
			toPrintStr:$("#toPrintStr").val()
		}, 
		function(data){
		});
	return rtValue;
}
</script>
</head>
<body BGCOLOR="#ffffff" LINK="#000099">

<span class="title">Web打印测试</span>
<br>
<br>

<!--"CONVERTED_APPLET"-->
<!-- HTML CONVERTER -->
<SCRIPT LANGUAGE="JavaScript"><!--
    var _info = navigator.userAgent; 
    var _ns = false; 
    var _ns6 = false;
    var _ie = (_info.indexOf("MSIE") > 0 && _info.indexOf("Win") > 0 && _info.indexOf("Windows 3.1") < 0);
//--></SCRIPT>
    <COMMENT>
        <SCRIPT LANGUAGE="JavaScript1.1"><!--
        var _ns = (navigator.appName.indexOf("Netscape") >= 0 && ((_info.indexOf("Win") > 0 && _info.indexOf("Win16") < 0 && java.lang.System.getProperty("os.version").indexOf("3.5") < 0) || (_info.indexOf("Sun") > 0) || (_info.indexOf("Linux") > 0) || (_info.indexOf("AIX") > 0) || (_info.indexOf("OS/2") > 0)));
        var _ns6 = ((_ns == true) && (_info.indexOf("Mozilla/5") >= 0));
//--></SCRIPT>
    </COMMENT>

<SCRIPT LANGUAGE="JavaScript">
<!--
/*
    if (_ie == true) 
        document.writeln('<OBJECT classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" WIDTH="300" HEIGHT="40"  codebase="http://java.sun.com/products/plugin/1.1.2/jinstall-112-win32.cab#Version=1,1,2,0"><NOEMBED><XMP>');
    else if (_ns == true && _ns6 == false) 
        document.writeln('<EMBED type="application/x-java-applet;version=1.1.2" CODE="PrinterApplet.class" CODEBASE="applets" ARCHIVE="jasperreports-applet-3.7.3.jar,commons-logging-1.0.4.jar,commons-collections-2.1.1.jar" WIDTH="300" HEIGHT="40" REPORT_URL="../toPrint.jsp" scriptable=false pluginspage="http://java.sun.com/products/plugin/1.1.2/plugin-install.html"><NOEMBED><XMP>');
*/
//-->
</SCRIPT>
<APPLET CODE="org.quickbundle.third.jasperreport.applet.PrinterApplet.class" CODEBASE="applets" ARCHIVE="PrinterApplet.jar,jasperreports-3.7.3.jar,jasperreports-applet-3.7.3.jar,commons-logging-1.0.4.jar,commons-collections-2.1.1.jar,commons-digester-1.7.jar" WIDTH="300" HEIGHT="40"></XMP>
	<PARAM NAME=CODE VALUE="org.quickbundle.third.jasperreport.applet.PrinterApplet.class" >
	<PARAM NAME=CODEBASE VALUE="applets" >
	<PARAM NAME=ARCHIVE VALUE="PrinterApplet.jar,jasperreports-3.7.3.jar,jasperreports-applet-3.7.3.jar,commons-logging-1.0.4.jar,commons-collections-2.1.1.jar,commons-digester-1.7.jar" >
    <PARAM NAME="type" VALUE="application/x-java-applet;version=1.2.2">
    <PARAM NAME="scriptable" VALUE="false">
    <PARAM id="print_url_id" NAME="REPORT_URL" VALUE ="../toPrint.jsp">
</APPLET>
</NOEMBED>
</EMBED>
</OBJECT>

<!--
<APPLET CODE="PrinterApplet.class" CODEBASE="applets" ARCHIVE="jasperreports-applet-3.7.3.jar,commons-logging-1.0.4.jar,commons-collections-2.1.1.jar" WIDTH="300" HEIGHT="40">
<PARAM NAME="REPORT_URL" VALUE="../toPrint.jsp">


</APPLET>
-->
<!--"END_CONVERTED_APPLET"-->

<hr>
<textarea id="toPrintStr" name="toPrintStr" rows="20" cols="100">
拉丁美洲文学爆炸（西班牙文：Boom Latinoamericano）是一场发生在1960年代至1970年代之间的文学运动，在那期间一大批相关拉丁美洲作家的作品流行于欧洲并最终流行于全世界。说起这场文学爆炸人们会很自然地联想到四位主将：阿根廷的胡利奥·科塔萨尔、墨西哥的卡洛斯·富恩特斯、秘鲁的马里奥·巴尔加斯·略萨以及哥伦比亚的加西亚·马尔克斯。这些作家受到欧洲和北美现代主义的影响，同时也秉承了拉美先锋运动的衣钵，向拉美文学的传统套路发起挑战。他们的作品带有实验性质，并且十分政治化。“毫不夸张的说”，评论家杰拉尔德·马丁写道，“在1960年代南方大陆上有两件事比其他所有事情都更有影响，首先是古巴革命对拉丁美洲和第三世界的广泛冲击，第二件便是拉丁美洲文学爆炸，它的起伏与1959年至1971年古巴自由观念的兴衰息息相关。”[1]
这些新晋作家的迅速成名很大程度上归功于他们的作品是最早一批在欧洲出版的拉美小说中（主要由像西班牙巴塞罗那的先锋文学出版社希克斯·巴拉尔这样的出版社出版）的一部分这个事实。[2]当然，弗雷德里克·M·纳恩也写道“拉美小说家变得闻名世界是通过他们作品中对政治和社会行为的鼓吹，同时也因为他们中的很多人很幸运的在拉丁美洲之外获得受众——通过翻译和传播，有时也由于作家们流亡他乡。”[3]
</textarea>
<input type="button" value="提交" onclick="doSetSession();">
<hr>

</body>
</html>