package org.irods.jargon.core.connection;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.exception.JargonRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a transitional refactoring, such that the IRODSProtocolManager
 * interface will be re-worked, and perhaps discarded. For the moment, this is
 * not used in 'normal' jargon code.
 * 
 * @author Mike Conway - DICE (www.irods.org)
 * 
 */
public abstract class AbstractIRODSProtocolManager implements
		IRODSProtocolManager {

	private Logger log = LoggerFactory
			.getLogger(AbstractIRODSProtocolManager.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.core.connection.IRODSProtocolManager#getIRODSProtocol
	 * (org.irods.jargon.core.connection.IRODSAccount,
	 * org.irods.jargon.core.connection.PipelineConfiguration)
	 */
	@Override
	public abstract IRODSCommands getIRODSProtocol(
			final IRODSAccount irodsAccount,
			final PipelineConfiguration pipelineConfiguration)
			throws JargonException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.core.connection.IRODSProtocolManager#returnIRODSConnection
	 * (org.irods.jargon.core.connection.IRODSManagedConnection)
	 */
	@Override
	public abstract void returnIRODSConnection(
			final IRODSManagedConnection irodsConnection)
			throws JargonException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.irods.jargon.core.connection.IRODSProtocolManager#
	 * returnConnectionWithIoException
	 * (org.irods.jargon.core.connection.IRODSManagedConnection)
	 */
	@Override
	public void returnConnectionWithIoException(
			final IRODSManagedConnection irodsConnection) {
		forcefullyCloseConnectionAndClearFromSession(irodsConnection);
	}

	/**
	 * Abandon a connection to iRODS by shutting down the socket, and ensure
	 * that the session is cleared.
	 * 
	 * @param irodsConnection
	 */
	protected void forcefullyCloseConnectionAndClearFromSession(
			final IRODSManagedConnection irodsConnection) {
		log.warn("connection returned with IOException, will forcefully close and remove from session cache");
		if (irodsConnection != null) {
			irodsConnection.obliterateConnectionAndDiscardErrors();
			try {
				irodsConnection.getIrodsSession().discardSessionForErrors(
						irodsConnection.getIrodsAccount());
			} catch (JargonException e) {
				log.error("unable to obliterate connection");
				throw new JargonRuntimeException(
						"unable to obliterate connection", e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.irods.jargon.core.connection.IRODSProtocolManager#destroy()
	 */
	@Override
	public abstract void destroy() throws JargonException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.irods.jargon.core.connection.IRODSProtocolManager#initialize()
	 */
	@Override
	public abstract void initialize() throws JargonException;

}
