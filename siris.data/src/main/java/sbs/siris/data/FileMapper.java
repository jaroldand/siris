package sbs.siris.data;

import sbs.siris.data.base.BaseMapper;
import sbs.siris.domain.entity.File;
import sbs.siris.domain.entity.base.BaseParam;

public interface FileMapper extends BaseMapper<File>  {
	
	void inactivateFileByEntidad(BaseParam<File> param);
}