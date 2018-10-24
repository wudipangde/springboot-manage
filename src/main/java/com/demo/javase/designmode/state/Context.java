package com.demo.javase.designmode.state;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
@Getter@Setter
public class Context {
	private State state;
	public Context(State state){
		this.state=state;
	}
	public void method(){
		if(state.getValue().equals("state1")){
			state.method1();
		}else if(state.getValue().equals("state2")){
			state.mehtod2();
		}
	}

	public static void main(String[] args) {
		State state=new State();
		Context context=new Context(state);
		state.setValue("state1");
		context.method();
		state.setValue("state2");
		context.method();
	}
}
