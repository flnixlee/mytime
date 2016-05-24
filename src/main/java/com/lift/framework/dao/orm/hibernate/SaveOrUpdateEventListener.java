package com.lift.framework.dao.orm.hibernate;

import java.util.Date;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

import com.lift.framework.entity.EntityBean;

/**
 * 保存和修改的监听器
 */
// @Component(value="listener")
public class SaveOrUpdateEventListener implements PreInsertEventListener, PreUpdateEventListener {

	private static final long serialVersionUID = -2297408806609187435L;

	public boolean onPreInsert(PreInsertEvent event) {
		if (event.getEntity() instanceof EntityBean) {
			// ServletRequestAttributes attr = (ServletRequestAttributes)
			// RequestContextHolder.currentRequestAttributes();
			// HttpSession session = attr.getRequest().getSession(true);

			// int user = session.getAttribute(Constant.CURRENT_USER) == null ?
			// EntityBean.DEFAULT_USER :
			// (Integer)session.getAttribute(Constant.CURRENT_USER);

			EntityBean bean = (EntityBean) event.getEntity();

			// bean.setCreationUser(user);
			// bean.setCreationTime(new Date());
			//
			// bean.setLastUpdateUser(user);
			bean.setLastUpdateTm(new Date());
		}
		return true;
	}

	public boolean onPreUpdate(PreUpdateEvent event) {
		if (event.getEntity() instanceof EntityBean) {
			// ServletRequestAttributes attr = (ServletRequestAttributes)
			// RequestContextHolder.currentRequestAttributes();
			// HttpSession session = attr.getRequest().getSession(true);

			// int user = session.getAttribute(Constant.CURRENT_USER) == null ?
			// EntityBean.DEFAULT_USER :
			// (Integer)session.getAttribute(Constant.CURRENT_USER);

			EntityBean bean = (EntityBean) event.getEntity();
			// bean.setLastUpdateUser(user);
			bean.setLastUpdateTm(new Date());
		}
		return true;
	}
}
