package com.test.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.runqian.base4.util.upload.File;
/**
 *<p>Title: ChangeFile</p>
 *<p>Description:重命名文件 </p> 
 *<p>Company: Sysway</p> 
 * @author   <a href="">taoxs</a>
 * @date Nov 2, 2013
 * @version
 */
public class ChangeFile {
	public static void main(String[] args) throws FileNotFoundException{
		String path="D:\\study\\linux";
		getFile(path);
	}
	public static void printf(String str){
		System.out.println(str);
	}
	public static void getFile(String path){
		java.io.File file=new java.io.File(path);
		System.out.println(path+"是"+(file.isFile()==true?"文件":"文件夹"));
		java.io.File[] files=file.listFiles();
		for (int i = 0; i < files.length; i++) {
			//System.out.println(files[i].getName());
			java.io.File f_tmp=files[i];
			String filename=f_tmp.getName();
			//filename.replace("韩顺平 linux视频教程", "Linux");
			filename=filename.replaceAll("韩顺平 linux视频教程", "Linux");
			java.io.File f2=new java.io.File(path+"/"+filename);
			System.out.println(f2.getName());
			if(f_tmp.renameTo(f2)){
				System.out.println("重命名成功");
			}
		}
	}
}
