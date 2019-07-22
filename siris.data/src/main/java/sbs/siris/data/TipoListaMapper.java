package sbs.siris.data;

import sbs.siris.data.base.BaseMapper;
import sbs.siris.domain.entity.ClaveValor;
import sbs.siris.domain.entity.TipoLista;
import sbs.siris.domain.entity.base.BaseParam;

public interface TipoListaMapper extends BaseMapper<TipoLista> {

	void selectListClaveValor(BaseParam<ClaveValor> param);
}