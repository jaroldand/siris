package sbs.siris.service.controller.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import sbs.siris.domain.base.BaseDomain;
import sbs.siris.domain.entity.Pagination;
import sbs.siris.domain.entity.base.BaseEntity;
import sbs.siris.domain.entity.base.BasePage;

public class BaseController<T extends BaseEntity, I> {

	protected BaseDomain<T> domain;

	public void setDomain(BaseDomain<T> domain) {
		this.domain = domain;
	}

	@GetMapping(value = "/{key}")
	public DeferredResult<ResponseEntity<T>> getEntityByKey(@PathVariable I key) {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<T>(domain.getEntityByKey(key, user), HttpStatus.OK);
		}, user);
	}

	@RequestMapping(value = "/_page", method = { RequestMethod.POST, RequestMethod.GET })
	public DeferredResult<ResponseEntity<Pagination<T>>> getListPageEntity(@RequestBody BasePage<T> page) {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<Pagination<T>>(domain.getListPageEntity(page, user), HttpStatus.OK);
		}, user);
	}

	@PostMapping
	public DeferredResult<ResponseEntity<Boolean>> insert(@RequestBody T entity) {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<Boolean>(domain.insert(entity, user), HttpStatus.CREATED);
		}, user);
	}

	@PutMapping(value = "/{key}")
	public DeferredResult<ResponseEntity<Boolean>> updateByKey(@PathVariable I key, @RequestBody T entity) {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<Boolean>(domain.updateByKey(key, entity, user), HttpStatus.OK);
		}, user);
	}

	@DeleteMapping(value = "/{key}")
	public DeferredResult<ResponseEntity<Boolean>> deleteByKey(@PathVariable I key, @RequestBody T entity) {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<Boolean>(domain.deleteByKey(key, entity, user), HttpStatus.OK);
		}, user);
	}

}


// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// HTTP METHOD |CRUD |ENTIRE COLLECTION (E.G. /USERS) |SPECIFIC ITEM (E.G. /USERS/123)
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// POST |Create |201 (Created), ‘Location’ header with link to /users/{id} containing new ID. |Avoid using POST on single resource
// GET |Read |200 (OK), list of users. Use pagination, sorting and filtering to navigate big lists. |200 (OK), single user. 404 (Not Found), if ID not found or invalid.
// PUT |Update/Replace |404 (Not Found), unless you want to update every resource in the entire collection of resource. |200 (OK) or 204 (No Content). Use 404 (Not Found), if ID not found or invalid.
// PATCH |Partial Update/Modify |404 (Not Found), unless you want to modify the collection itself. |200 (OK) or 204 (No Content). Use 404 (Not Found), if ID not found or invalid.
// DELETE |Delete |404 (Not Found), unless you want to delete the whole collection — use with caution. |200 (OK). 404 (Not Found), if ID not found or invalid.
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// @RequestParam
// @RequestBody
// @PathVariable
// @MatrixVariable