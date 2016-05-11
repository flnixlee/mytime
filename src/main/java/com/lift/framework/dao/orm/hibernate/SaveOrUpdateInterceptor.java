package com.lift.framework.dao.orm.hibernate;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lift.framework.entity.EntityBean;

//@Component(value="entityInterceptor")
public class SaveOrUpdateInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = -5802239224389022481L;

    /** 当前用户 */
    public static final String CURRENT_USER = "CURRENT_USER";

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
	if (entity instanceof EntityBean) {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    HttpSession session = attr.getRequest().getSession(true);

	    int user = session.getAttribute(CURRENT_USER) == null ? EntityBean.DEFAULT_USER : (Integer) session.getAttribute(CURRENT_USER);

	    for (int index = 0; index < propertyNames.length; index++) {
		if ("creationUser".equals(propertyNames[index]) && (Integer) state[index] == 0) {
		    state[index] = user;
		} else if ("lastUpdateUser".equals(propertyNames[index])) {
		    state[index] = user;
		} else if ("creationTime".equals(propertyNames[index]) && state[index] == null) {
		    state[index] = new Date();
		} else if ("lastUpdateTime".equals(propertyNames[index])) {
		    state[index] = new Date();
		}
	    }
	}
	return true;
    }
}
