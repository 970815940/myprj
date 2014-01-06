package com.test.exception;
/** *//**
 * 自定义异常类
 * 第二种定义方法，继承Throwable类
 * @author joe
 *
 */

public class myException extends Throwable{

    /** *//**
     * 
     */
    private static final long serialVersionUID = 1L;

    public myException() {
        super();
    }
    
    public myException(String msg){
        super(msg);
    }
    
    public myException(String msg, Throwable cause){
        super(msg, cause);
    }
    
    public myException(Throwable cause){
        super(cause);
    }
}
