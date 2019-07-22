package sbs.siris.data.base;

import sbs.siris.domain.entity.base.BaseParam;

public interface BaseMapper<E> {

	void deleteByPrimaryKey(BaseParam<E> param);

	void insert(BaseParam<E> param);

	void selectByPrimaryKey(BaseParam<E> param) throws RuntimeException;

	void updateByPrimaryKey(BaseParam<E> param);

	void selectList(BaseParam<E> param);

	void selectListPage(BaseParam<E> param);

	int totalCount(BaseParam<E> param);

	long getSequenceLong();

	int getSequenceInt();

	void buscar(BaseParam<E> param);

	long getIdAuditoria(E entity);

}