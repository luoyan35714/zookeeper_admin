package com.freud.zkadmin.framework.base.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.freud.zkadmin.framework.exception.ServiceRuntimeException;

public abstract class BaseController {

	/**
	 * The LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	/**
	 * The jsp file [error.jsp].
	 */
	private static final String JSP_FILE_ERROR = "error";

	/**
	 * The Error Key : exception.
	 */
	private static final String ERROR_KEY_EXCEPTION = "exception";
	/**
	 * The Error Key : message.
	 */
	private static final String ERROR_KEY_MESSAGE = "message";

	/**
	 * The Error Description String : ERROR_STRING_ERROR_OCCURED.
	 */
	private static final String ERROR_STRING_ERROR_OCCURED = "Exception occured : {0}";

	private static final String LEFT_TREE = "LEFT_TREE";
	@Autowired
	private HttpSession session;

	protected void clearLeftTree() {
		session.removeAttribute(LEFT_TREE);
	}

	protected void setLeftTree(String value) {
		session.setAttribute(LEFT_TREE, value);
	}

	@ExceptionHandler(Exception.class)
	protected ModelAndView exception(Exception e) {

		LOGGER.error(MessageFormat.format(ERROR_STRING_ERROR_OCCURED, e.getMessage()));

		e.printStackTrace();

		return packageExceptionModelAndView(JSP_FILE_ERROR, e, e.getMessage());
	}

	@ExceptionHandler(ServiceRuntimeException.class)
	protected ModelAndView serviceRuntimeException(ServiceRuntimeException e) {

		LOGGER.error(MessageFormat.format(ERROR_STRING_ERROR_OCCURED, e.getMessage()));

		e.printStackTrace();

		return packageExceptionModelAndView(JSP_FILE_ERROR, e, e.getMessage());
	}

	private ModelAndView packageExceptionModelAndView(String view, Exception exception, Object errorMessage) {

		ModelAndView mav = new ModelAndView(view);

		mav.addObject(ERROR_KEY_EXCEPTION, exception);
		mav.addObject(ERROR_KEY_MESSAGE, errorMessage);

		return mav;
	}

}
