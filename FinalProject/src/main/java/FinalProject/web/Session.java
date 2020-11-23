package FinalProject.web;

import FinalProject.services.ClientServices;

public class Session {
	private ClientServices facade;
	private long lastAccessed;
	
	public Session(ClientServices facade, long lastAccessed) {
		super();
		this.facade = facade;
		this.lastAccessed = lastAccessed;
	}

	public ClientServices getFacade() {
		return facade;
	}

	public long getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(long currentTimeMillis) {
		this.lastAccessed=currentTimeMillis;
		
	}
	
	
}
