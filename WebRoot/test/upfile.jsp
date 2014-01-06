<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<%@page import="java.util.*,com.oreilly.servlet.*"%>

<%
	//指定上传文件的存储目录
	String webTempPath = "D:/";

	//单个文件大小限制为5M Bytes
	int maxPostSize = 50 * 1024 * 1024;

	// 声明上传文件名所使用的编码，默认值为 ISO-8859-1，
	// 若改为GBK则支持中文名	
	String enCoding = "GBK";

	// 产生一个新的MultipartRequest 对象
	MultipartRequest mpr = new MultipartRequest(request, webTempPath,
			maxPostSize, enCoding);

	// 使用 MultipartRequest 对象获取请求参数，注意：不能直接使用request获取了
	String fileName = mpr.getFilesystemName("fileName");
	String description = mpr.getParameter("description");
	System.out.println("上传成功");
	out.println(fileName);
	out.println(description);
%>
