package sbs.siris.domain.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sbs.cross.util.exception.BusinessAsyncException;
import sbs.cross.util.exception.LevelType;
import sbs.cross.util.exception.StatusCode;
import sbs.siris.data.base.BaseMapper;
import sbs.siris.domain.entity.Pagination;
import sbs.siris.domain.entity.base.BaseEntity;
import sbs.siris.domain.entity.base.BasePage;
import sbs.siris.domain.entity.base.BaseParam;

public class BaseDomain<T extends BaseEntity> {

	/*@Autowired
	private AuditoriaMapper<T> auditoriaMapper;*/

	protected BaseMapper<T> mapper;

	public void setMapper(BaseMapper<T> mapper) {
		this.mapper = mapper;
	}

	public List<T> getListEntity(Object key, String codUser, boolean valid) {
		BaseParam<T> param = new BaseParam<>();
		param.setKey(key);
		mapper.selectList(param);
		if (valid)
			if (param.getList() == null || param.getList().size() <= 0)
				throw new BusinessAsyncException(DomainProperties.NO_EXISTE, LevelType.WARNING, StatusCode.NO_FOUND);
		return param.getList();
	}

	public List<T> getListEntity(String codUser, boolean valid) {
		BaseParam<T> param = new BaseParam<>();
		mapper.selectList(param);
		if (valid)
			if (param.getList() == null || param.getList().size() <= 0)
				throw new BusinessAsyncException(DomainProperties.NO_EXISTE, LevelType.WARNING, StatusCode.NO_FOUND);
		return param.getList();
	}

	public List<T> getListEntity(String codUser) {
		return getListEntity(codUser, false);
	}

	public List<T> buscar(T entity, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		param.setEntity(entity);
		mapper.selectList(param);
		return param.getList();
	}

	public int getSequenceInt() {
		return mapper.getSequenceInt();
	}

	public long getSequenceLong() {
		return mapper.getSequenceLong();
	}

	public Pagination<T> getListPageEntity(BasePage<T> page, String codUser) {
		return getListPageEntity(page, codUser, false);
	}

	public Pagination<T> getListPageEntity(BasePage<T> page, String codUser, boolean valid) {
		BaseParam<T> param = new BaseParam<>();
		param.setEntity(page.getEntity());
		param.setSortColumn(page.getSortColumn());
		param.setSortDirection(page.getSortColumn());
		param.setStartRecord(page.isCarryAll() ? null : (((page.getNumberPage() - 1) * page.getSizePage()) + 1));
		mapper.selectListPage(param);
		if (valid)
			if (param.getList() == null || param.getList().size() <= 0)
				throw new BusinessAsyncException(DomainProperties.NO_DATOS, LevelType.WARNING, StatusCode.NO_FOUND);

		Pagination<T> response = new Pagination<>();
		response.setList(param.getList());
		response.setSize(mapper.totalCount(param));
		return response;
	}

	public T getEntityByKey(Object key, String codUser) {
		return getEntityByKey(key, codUser, false);
	}

	public T getEntityByKey(Object key, String codUser, boolean valid) {
		BaseParam<T> param = new BaseParam<>();
		param.setKey(key);
		mapper.selectByPrimaryKey(param);
		if (valid)
			if (param.getList() == null || param.getList().size() <= 0)
				throw new BusinessAsyncException(DomainProperties.NO_EXISTE, LevelType.WARNING, StatusCode.NO_FOUND);
		return param.getList().get(0);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean insertWithout(T entity, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		param.setEntity(entity);
		mapper.insert(param);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean insert(T entity, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		//
		//entity.setIdAuditoria(auditoriaMapper.getSequenceLong());
		/*entity.setCodUsuarioAudit(codUser);
		entity.setTipEventoAudit(TipoValor.SGC_AUD_DETALLE_TIP_EVENTO_CREACION);
		entity.setTipUsuarioAudit(TipoValor.SGC_USUARIO_TIP_USUARIO_COOPAC);*/
		
		entity.setAuditUserCrea(codUser);
		entity.setAuditFecCrea(new Date());
		
		param.setEntity(entity);
		//
		mapper.insert(param);
		// Inserta auditoría
		//auditoriaMapper.insertAuditoriaNueva(param);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean updateByKeyWithout(Object key, T entity, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		param.setKey(key);
		param.setEntity(entity);
		mapper.updateByPrimaryKey(param);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean updateByKey(Object key, T entity, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		param.setKey(key);
		//
		/*entity.setIdAuditoria(mapper.getIdAuditoria(entity));
		entity.setCodUsuarioAudit(codUser);
		entity.setTipEventoAudit(TipoValor.SGC_AUD_DETALLE_TIP_EVENTO_MODIFICACION);
		entity.setTipUsuarioAudit(TipoValor.SGC_USUARIO_TIP_USUARIO_COOPAC);*/
		
		entity.setAuditUserMod(codUser);
		entity.setAuditFecMod(new Date());
		
		param.setEntity(entity);
		//auditoriaMapper.insertAuditoriaAbrir(param);
		//
		//entity.setIdAudDetalle((Long) param.getResult());
		//
		mapper.updateByPrimaryKey(param);
		//
		//auditoriaMapper.insertAuditoriaCerrar(param);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean deleteByKeyWithout(Object key, T entity, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		param.setKey(key);
		param.setEntity(entity);
		//auditoriaMapper.insertAuditoriaBorrar(param);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean deleteByKey(Object key, T entity, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		param.setKey(key);
		//
		/*entity.setIdAuditoria(mapper.getIdAuditoria(entity));
		entity.setCodUsuarioAudit(codUser);
		entity.setTipEventoAudit(TipoValor.SGC_AUD_DETALLE_TIP_EVENTO_ELIMINACION);
		entity.setTipUsuarioAudit(TipoValor.SGC_USUARIO_TIP_USUARIO_COOPAC);*/
		param.setEntity(entity);
		//auditoriaMapper.insertAuditoriaBorrar(param);
		//
		mapper.deleteByPrimaryKey(param);
		return true;
	}

	// i: multiples keys

	public T getEntityByKey(Map<String, Object> key, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		param.setKeyMap(key);
		mapper.selectByPrimaryKey(param);
		if (param.getList().size() > 0)
			return param.getList().get(0);
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean updateByKeyWithout(Map<String, Object> key, T entity, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		param.setKeyMap(key);
		param.setEntity(entity);
		mapper.updateByPrimaryKey(param);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean updateByKey(Map<String, Object> key, T entity, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		param.setKeyMap(key);
		//
		/*entity.setIdAuditoria(mapper.getIdAuditoria(entity));
		entity.setCodUsuarioAudit(codUser);
		entity.setTipEventoAudit(TipoValor.SGC_AUD_DETALLE_TIP_EVENTO_MODIFICACION);
		entity.setTipUsuarioAudit(TipoValor.SGC_USUARIO_TIP_USUARIO_COOPAC);*/
		param.setEntity(entity);
		//auditoriaMapper.insertAuditoriaAbrir(param);
		//
		//entity.setIdAudDetalle((Long) param.getResult());
		//
		mapper.updateByPrimaryKey(param);
		//
		//auditoriaMapper.insertAuditoriaCerrar(param);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean deleteByKeyWithout(Map<String, Object> key, T entity, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		param.setKeyMap(key);
		param.setEntity(entity);
		mapper.deleteByPrimaryKey(param);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean deleteByKey(Map<String, Object> key, T entity, String codUser) {
		BaseParam<T> param = new BaseParam<>();
		param.setKeyMap(key);
		//
		/*entity.setIdAuditoria(mapper.getIdAuditoria(entity));
		entity.setCodUsuarioAudit(codUser);
		entity.setTipEventoAudit(TipoValor.SGC_AUD_DETALLE_TIP_EVENTO_ELIMINACION);
		entity.setTipUsuarioAudit(TipoValor.SGC_USUARIO_TIP_USUARIO_COOPAC);*/
		param.setEntity(entity);
		//auditoriaMapper.insertAuditoriaBorrar(param);
		//
		mapper.deleteByPrimaryKey(param);
		return true;
	}

	// :i multiples keys

}
