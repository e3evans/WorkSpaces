package com.manpower.portal.mpnet.dao.interfaces;

import com.manpower.portal.mpnet.hbn.beans.MobileDevice;

public interface MobileDeviceDAO extends DAO {

	public MobileDevice findByDeviceID(String deviceID);
}
