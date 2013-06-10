package net.gamerzaddiction.gamermanh.arenas;

public abstract class IArena {
	private String name;
	
	public IArena(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract ArenaStatus getStatus();
	
	public abstract ArenaType getType();
	
	public abstract void setType(ArenaType type);
	
	public abstract void setStatus(ArenaStatus status);
	
}
