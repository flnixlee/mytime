package com.lift.framework.dao.orm.hibernate;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 监听器
 * 
 * @author 吴南南
 * 
 */
// @Component
public class HibernateEventRegister {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private SaveOrUpdateEventListener listener;

    @PostConstruct
    public void registerListeners() {
	EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(EventListenerRegistry.class);
	// registry.getEventListenerGroup(EventType.SAVE_UPDATE).appendListener(new
	// DefaultSaveOrUpdateEventListener());
	registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(listener);
	registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(listener);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void setListener(SaveOrUpdateEventListener listener) {
	this.listener = listener;
    }
}
