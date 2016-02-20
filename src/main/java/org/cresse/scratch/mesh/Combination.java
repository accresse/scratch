package org.cresse.scratch.mesh;

import java.util.List;

public class Combination {
	
	private String value1;
	private String value2;
	private List<String> unused;
	
	public Combination(String value1, String value2, List<String> unused) {
		if(value1.compareTo(value2)<0) {
			this.value1 = value1;
			this.value2 = value2;
		} else {
			this.value1 = value2;
			this.value2 = value1;
		}
		this.unused = unused;
	}

	public String getValue1() {
		return value1;
	}

	public String getValue2() {
		return value2;
	}

	public List<String> getUnused() {
		return unused;
	}

	public String toString() {
		return String.format("(%s, %s) / %s", this.value1, this.value2, this.unused);
	}
}
