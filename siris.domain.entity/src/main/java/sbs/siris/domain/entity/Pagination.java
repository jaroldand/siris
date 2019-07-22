package sbs.siris.domain.entity;

import sbs.siris.domain.entity.base.BaseList;

public class Pagination<T> extends BaseList<T> {

	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
