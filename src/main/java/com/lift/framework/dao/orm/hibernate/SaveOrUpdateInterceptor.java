package com.lift.framework.dao.orm.hibernate;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lift.framework.entity.EntityBean;
import com.lift.framework.util.Constant;

//@Component(value="entityInterceptor")
@SuppressWarnings("serial")
public class SaveOrUpdateInterceptor extends EmptyInterceptor {

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof EntityBean) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);

			String user = session.getAttribute(Constant.CURRENT_USER) == null ? Constant.DEFAULT_USER : String.valueOf(session.getAttribute(Constant.CURRENT_USER));

			for (int index = 0; index < propertyNames.length; index++) {
				if ("creator".equals(propertyNames[index]) && (Integer) state[index] == 0) {
					state[index] = user;
				} else if ("lastUpdateUser".equals(propertyNames[index])) {
					state[index] = user;
				} else if ("createTm".equals(propertyNames[index]) && state[index] == null) {
					state[index] = new Date();
				} else if ("lastUpdateTm".equals(propertyNames[index])) {
					state[index] = new Date();
				}
			}
		}
		return true;
	}
}
