package controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.Game;

/**
 * Application Lifecycle Listener implementation class TicTacToeSessionListener
 *
 */
@WebListener
public class TicTacToeSessionListener implements HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public TicTacToeSessionListener() {
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("game", new Game());
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	}

}
