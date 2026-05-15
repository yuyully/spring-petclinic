// from global agent
// from global claude.md
package org.springframework.samples.petclinic.system;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Maps domain lookup failures to a user-friendly 404 page. Only
 * {@link ResourceNotFoundException} is treated as 404 so genuine programmer errors
 * surfacing as {@link IllegalArgumentException} remain visible (500) rather than being
 * masked as "not found".
 */
@ControllerAdvice(annotations = Controller.class)
class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	ModelAndView handleResourceNotFound(ResourceNotFoundException ex) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.setStatus(HttpStatus.NOT_FOUND);
		modelAndView.addObject("status", HttpStatus.NOT_FOUND.value());
		modelAndView.addObject("message", ex.getMessage());
		return modelAndView;
	}

}
