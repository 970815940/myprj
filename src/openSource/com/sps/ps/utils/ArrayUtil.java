package com.sps.ps.utils;

public class ArrayUtil {
	  public static String toString(Object[] array) {
		    if (array==null) {
		      return "null";
		    } else {
		      StringBuffer buffer = new StringBuffer();
		      buffer.append("<[");
		      for (int i=0; i<array.length; i++) {
		        Object o = array[i];
		        if (o!=null) {
		          buffer.append(o);
		        } else {
		          buffer.append("null");
		        }
		        if (i!=array.length-1) {
		          buffer.append("|");
		        }
		      }
		      buffer.append("]>");
		      return buffer.toString();
		    }
		  }
}
