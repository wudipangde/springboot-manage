package com.demo.javase.designmode.prototype;

import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
@Getter@Setter
public class Prototype implements Cloneable,Serializable {

	private static final long serialVersionUID = 1486812423113579380L;
	private String stirng;
	private SerialzableObject Obj;

	//浅复制
	@Override
	public Object clone() throws CloneNotSupportedException {
		Prototype proto= (Prototype) super.clone();
		return proto;
	}
	//深复制
	public Object deepClone() throws IOException,ClassNotFoundException{
		//写入当前对象的二进制
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(bos);
		oos.writeObject(this);
		//读出二进制流产生的新对象
		ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois=new ObjectInputStream(bis);
		return ois.readObject();
	}
}
