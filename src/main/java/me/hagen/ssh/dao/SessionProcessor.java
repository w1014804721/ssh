package me.hagen.ssh.dao;

import org.hibernate.Session;

public interface SessionProcessor {
	public void process(Session session);
}
