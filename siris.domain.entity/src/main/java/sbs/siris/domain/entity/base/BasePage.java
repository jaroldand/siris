package sbs.siris.domain.entity.base;

public class BasePage<T> {

	private T entity;
	private int numberPage;
	private int sizePage;
	private String sortColumn;
	private String sortDirection;
	private boolean carryAll;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public int getNumberPage() {
		return numberPage;
	}

	public void setNumberPage(int numberPage) {
		this.numberPage = numberPage;
	}

	public int getSizePage() {
		return sizePage;
	}

	public void setSizePage(int sizePage) {
		this.sizePage = sizePage;
	}

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

	public boolean isCarryAll() {
		return carryAll;
	}

	public void setCarryAll(boolean carryAll) {
		this.carryAll = carryAll;
	}

}
