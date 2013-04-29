/*
 * Creado el 16-mar-2006
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

/**
 * @author MartinMuguiro
 */
public interface EmailDAO extends DAO {
	public void sendEmailsToCandidates(List emails);
}