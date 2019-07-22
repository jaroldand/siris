package sbs.siris.data;

import sbs.siris.data.base.BaseMapper;
import sbs.siris.domain.entity.Validacion;
import sbs.siris.domain.entity.base.BaseParam;

public interface ValidacionMapper extends BaseMapper<Validacion> {
    
	void listarValidacionByEvent(BaseParam<Validacion> param);
}