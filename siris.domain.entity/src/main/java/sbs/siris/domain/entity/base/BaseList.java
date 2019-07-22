package sbs.siris.domain.entity.base;

import java.util.List;

public class BaseList<T> {

	List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
