package xyz.itwill05.di;

import java.util.List;
import java.util.Set;

public class CollectionBean {
	private Set<String> nameSet;
	private List<String> nameList;
	
	public CollectionBean() {
		System.out.println("### CollectionBean Ŭ������ �⺻ ������ ȣ�� ###");
	}

	public Set<String> getNameSet() {
		return nameSet;
	}

	public void setNameSet(Set<String> nameSet) {
		this.nameSet = nameSet;
	}

	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
}
