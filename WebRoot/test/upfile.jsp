<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<%@page import="java.util.*,com.oreilly.servlet.*"%>

<%
	//ָ���ϴ��ļ��Ĵ洢Ŀ¼
	String webTempPath = "D:/";

	//�����ļ���С����Ϊ5M Bytes
	int maxPostSize = 50 * 1024 * 1024;

	// �����ϴ��ļ�����ʹ�õı��룬Ĭ��ֵΪ ISO-8859-1��
	// ����ΪGBK��֧��������	
	String enCoding = "GBK";

	// ����һ���µ�MultipartRequest ����
	MultipartRequest mpr = new MultipartRequest(request, webTempPath,
			maxPostSize, enCoding);

	// ʹ�� MultipartRequest �����ȡ���������ע�⣺����ֱ��ʹ��request��ȡ��
	String fileName = mpr.getFilesystemName("fileName");
	String description = mpr.getParameter("description");
	System.out.println("�ϴ��ɹ�");
	out.println(fileName);
	out.println(description);
%>
