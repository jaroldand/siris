package sbs.siris.domain.entity.base;

import java.util.Map;

public class BaseParam<T> extends BaseList<T> {

	// i: Variables Normales
	private Object key;
	private Map<String, Object> keyMap;
	private T entity;
	private Object result;
	// :i Variables Normales

	// i: Variables de Paginación
	private String sortColumn;
	private String sortDirection;
	private int startRecord;
	private int endRecord;
	// :i Variables de Paginación

	// i: Métodos Normales

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public Map<String, Object> getKeyMap() {
		return keyMap;
	}

	public void setKeyMap(Map<String, Object> keyMap) {
		this.keyMap = keyMap;
	}

	// :i Métodos Normales

	// i: Métodos de Paginación

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	public int getStartRecord() {
		return startRecord;
	}

	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}

	public int getEndRecord() {
		return endRecord;
	}

	public void setEndRecord(int endRecord) {
		this.endRecord = endRecord;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	// :i Métodos de Paginación

}
