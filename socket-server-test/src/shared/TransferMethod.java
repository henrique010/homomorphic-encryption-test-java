package shared;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;

public class TransferMethod implements Serializable {
	private static final long serialVersionUID = -4545754341010570580L;
	
	private String methodName;
	private ArrayList<Param> params;
	
	public TransferMethod(String methodName) {
		this.methodName = methodName;
		this.params = new ArrayList<Param>();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Param getParam(int index) {
		return params.get(index);
	}

	public void setParam(Param param) {
		this.params.add(param);
	}
}
