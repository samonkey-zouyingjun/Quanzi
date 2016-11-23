package com.fz.afinal.http;

import java.util.List;

/**    
 * @author cate
 * 2014-12-31 下午4:05:43   
 */
public class FzHttpResponse<T>
{
	private String responseString;
	
	private String msg;
	
	private int erroNo;
	
	private T data;
	private List<T> datalist;
	private String flag = "-10";
	
	public String getResponseString()
	{
		return responseString;
	}
	public void setResponseString(String responseString)
	{
		this.responseString = responseString;
	}
	public T getData()
	{
		return data;
	}
	public void setData(T data)
	{
		this.data = data;
	}
	public List<T> getDatalist()
	{
		
		return datalist;
	}
	public void setDatalist(List<T> datalist)
	{
		this.datalist = datalist;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public int getErroNo()
	{
		return erroNo;
	}
	public void setErroNo(int erroNo)
	{
		this.erroNo = erroNo;
	}
	public String getFlag()
	{
		return flag;
	}
	public void setFlag(String flag)
	{
		this.flag = flag;
	}
	
	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		System.out.println(FzHttpResponse.class.getSimpleName()+"-->被回收");
	}
}
