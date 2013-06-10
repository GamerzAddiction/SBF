package net.gamerzaddiction.gamermanh.arenas;

public enum ArenaStatus {
	/*
	 * @WAITING
	 * Arena is waiting for more players
	 * When enough players (see config), go to Lobby status
	 */
	WAITING,
	
	/*
	 * @LOBBY
	 * Arena has enough players and has begun countdown
	 * Players can join if the arena is not full
	 * After countdown, tele players to the actual stage and go to Preparing status
	 */
	LOBBY,
	
	/*
	 * @PREPARING
	 * 10 seconds for the players to get ready
	 * Afterwards, go to Started status
	 */
	PREPARING,
	
	/*
	 * @STARTED
	 * Arena has begun and is in motion
	 * Once game is won or timer is up, go to closing status
	 */
	STARTED,
	
	/*
	 * @CLOSING
	 * 5 second period for the arena to kick all the players (in it) and re-normalize everything
	 * Switch back to Waiting status
	 */
	CLOSING,
	
	/*
	 * @DISABLED
	 * Arena is disabled or there is a problem with it
	 * Once enabled, revert back to Waiting status
	 */
	DISABLED;
}
