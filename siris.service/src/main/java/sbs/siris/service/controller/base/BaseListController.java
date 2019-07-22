package sbs.siris.service.controller.base;

//import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.context.request.async.DeferredResult;

import sbs.siris.domain.entity.base.BaseEntity;

public class BaseListController<T extends BaseEntity, I> extends BaseController<T, I> {

	// @PostAuthorize("isAnonymous()")
	// @PreAuthorize("hasRole('ROL_CLIENT')")
	// @PostAuthorize("hasRole('ROL_CLIENT')")
	// No debe utilizarce para lista mayores de 100, no debe figurar metodos en el mapper.xml
	@GetMapping
	public DeferredResult<ResponseEntity<List<T>>> getListEntity() {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<List<T>>(domain.getListEntity(user), HttpStatus.OK);
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