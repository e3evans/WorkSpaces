package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.ApplicantSearchGeoCode;

public interface ApplicantGeoCodeDAO extends DAO {
	public List<ApplicantSearchGeoCode> getApplicantGeoLocations(long siteId, int fromIndex, int toIndex);
	public List<ApplicantSearchGeoCode> getApplicantGeoLocationsByIds(List<Long> idList);
/*	public GeoCodeLocation saveGeoCode(GeoCodeLocation location);
	public boolean saveGeoCodeList(List<GeoCodeLocation> list);
	public boolean saveApplicantGeoCodeList(List<ApplicantGeoCode> list);
	public ApplicantGeoCode saveApplicantGeoCode(ApplicantGeoCode applicantGeoCode);
	public boolean saveNotFound(List<NotFoundGeoCode> notFound);
*/}
