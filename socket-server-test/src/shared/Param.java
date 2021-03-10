package shared;

import java.io.Serializable;

public class Param implements Serializable{
	private static final long serialVersionUID = -7918042503281821326L;
	
	private String name;
	private Object value;
	
	public Param(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
