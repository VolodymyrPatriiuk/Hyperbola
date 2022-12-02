package org.eclipsercp.hyperbola.model;

public class Session implements ISession {
	
	private ContactsGroup rootGroup;
	private String name;
	private String server;

	public Session() {
	}

	public void setSessionDescription(String name, String server) {
		this.name = name;
		this.server = server;
	}

	public ContactsGroup getRoot() {
		if (rootGroup == null) {
			rootGroup = new ContactsGroup(null, "RootGroup"); //$NON-NLS-1$
		}
		return rootGroup;
	}

	public String getName() {
		return name;
	}

	public String getServer() {
		return server;
	}
	
}