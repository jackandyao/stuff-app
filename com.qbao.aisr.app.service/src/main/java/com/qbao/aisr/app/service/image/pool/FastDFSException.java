package com.qbao.aisr.app.service.image.pool;

public class FastDFSException extends Exception{
	private String name;
	/** 错误码 */
	private String code;

	/** 错误信息，用于日志输出，便于问题定位 */
	private String message;

	/** 错误提示，用于客户端提示 */
	private String descreption;
	private static final long serialVersionUID = 1165654943977322076L;
	//用来创建无参数对象
	public FastDFSException()  {
		
	}   
	//用来创建指定参数对象
    public FastDFSException(String name, String code, String message,String descreption) {        
        super(message);                             //调用超类构造器
        this.name=name;
        this.code=code;
        this.message=message;
        this.descreption=descreption;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescreption() {
		return descreption;
	}
	public void setDescreption(String descreption) {
		this.descreption = descreption;
	}

}
