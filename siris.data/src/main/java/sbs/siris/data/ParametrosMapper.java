package sbs.siris.data;

import sbs.siris.data.base.BaseMapper;
import sbs.siris.domain.entity.Parametros;
import sbs.siris.domain.entity.base.BaseParam;

public interface ParametrosMapper extends BaseMapper<Parametros>{

	void listarParametrosByDesc(BaseParam<Parametros> param);
}