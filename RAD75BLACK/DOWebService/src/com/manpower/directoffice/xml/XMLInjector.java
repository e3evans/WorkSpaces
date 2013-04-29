package com.manpower.directoffice.xml;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.manpower.directoffice.pojos.CandidateEntity;
import com.manpower.directoffice.pojos.CandidateGeneralInformation;
import com.manpower.directoffice.pojos.CandidateProfile;

// TODO: Do we need to inject MIME-type of CV?
// TODO: Handle multiple contact records types.
// TODO: Decide how to handle dateOfBirth, nationalId.
// TODO: If XPath doesn't map to an element, throw exception - all are required in template.

/**
 * XMLInjector loads all the processed data from an
 * {@link AdvertisementResponse} and injects it into an XML template that is in
 * a DO expected format.
 */
public class XMLInjector 
{
	
	/**
	 * Injects appropriate data from {@link AdvertisementResponse} into an XML
	 * document template that will be delivered to DO.
	 * 
	 * @param advertResponse The response object to draw the data from.
	 * @return Document
	 * 			An XML document fully loaded with data from the response.
	 */
	public static Document performChangePasswordXMLInjection(String entityID,String newSSSPinNumber)
	{
	
		Document targetXMLDoc = loadTargetXMLTemplate("templates/SSSPassword.xml");
		
		// Inject Password Change values
		injectElementValue(targetXMLDoc, entityID, XPathExpressions.getString("XMLInjector.candidateID"));		
		injectElementValue(targetXMLDoc, newSSSPinNumber, XPathExpressions.getString("XMLInjector.newPassword"));
		return targetXMLDoc;
	}
	
	public static Document performUpdateGeneralInformationXMLInjection(CandidateEntity candidate)
	{

		Document targetXMLDoc = loadTargetXMLTemplate("templates/UpdateGeneralInfo.xml");
		// Inject general information
		injectElementValue(targetXMLDoc, candidate.getCandidateGeneralInformation().getDOEntityID(), XPathExpressions.getString("XMLInjector.candidateCommonReference"));
		injectElementValue(targetXMLDoc, candidate.getCandidateGeneralInformation().getEmergencyContactName(), XPathExpressions.getString("XMLInjector.emergencyContactName"));
		injectElementValue(targetXMLDoc, candidate.getCandidateGeneralInformation().getEmergencyContactPhone(), XPathExpressions.getString("XMLInjector.emergencyContactPhone"));

		return targetXMLDoc;
	}
	
	public static Document performUpdateCandidateXMLInjection(CandidateEntity candidate, String countryCode) {
		Document targetXMLDoc = loadTargetXMLTemplate("templates/UpdateDOCandidate.xml");
		CandidateGeneralInformation canGen = candidate.getCandidateGeneralInformation();
		
		CandidateProfile boProfile = candidate.getCandidateDetailsForCountry(countryCode);

		
		injectElementValue(targetXMLDoc, canGen.getDOEntityID(), XPathExpressions.getString("XMLInjector.candidateID"));
		injectElementValue(targetXMLDoc, boProfile.getCountryCode(), XPathExpressions.getString("XMLInjector.bocountry"));
		injectElementValue(targetXMLDoc, boProfile.isEighteenOrOlder(), XPathExpressions.getString("XMLInjector.over18"));
		injectElementValue(targetXMLDoc, boProfile.isLegallyEntiltedToWork(), XPathExpressions.getString("XMLInjector.rightToWork"));
		injectElementAttribute(targetXMLDoc,"different", boProfile.isPayAddressDifferentThanMain(), XPathExpressions.getString("XMLInjector.payAddressDifferent"));
		injectElementValue(targetXMLDoc, boProfile.isCitizen(), XPathExpressions.getString("XMLInjector.isCitizen"));
		injectElementValue(targetXMLDoc, boProfile.hasGovernmentID(), XPathExpressions.getString("XMLInjector.hasNationalID"));
		injectElementValue(targetXMLDoc, boProfile.hasWorkVisa(), XPathExpressions.getString("XMLInjector.hasWorkVisa"));
		injectElementValue(targetXMLDoc, boProfile.hasCriminalConvictions(), XPathExpressions.getString("XMLInjector.hasCriminalConviction"));
		injectElementValue(targetXMLDoc, boProfile.isWillingToTakeDrugTest(), XPathExpressions.getString("XMLInjector.willingDrugTest"));
		injectElementValue(targetXMLDoc, boProfile.acceptDirectDepositByEmail(), XPathExpressions.getString("XMLInjector.electronicStatement"));
		injectElementValue(targetXMLDoc, boProfile.getWorkVisaExpirationDateXMLValue(),XPathExpressions.getString("XMLInjector.workVisaExpiry"));
		
		injectElementValue(targetXMLDoc, boProfile.getPayrollAddress().getPostal_code(), XPathExpressions.getString("XMLInjector.payPostalCode"));
		injectElementValue(targetXMLDoc, boProfile.getPayrollAddress().getMunicipality(), XPathExpressions.getString("XMLInjector.payMunicipality"));
		injectElementValue(targetXMLDoc, boProfile.getPayrollAddress().getAddressLine1(), XPathExpressions.getString("XMLInjector.payAddressLine1"));
		injectElementValue(targetXMLDoc, boProfile.getPayrollAddress().getAddressLine2(), XPathExpressions.getString("XMLInjector.payAddressLine2"));
		
//		System.out.println("INJECTOR:  "+boProfile.getPayrollAddress().getState_province());
		injectElementValue(targetXMLDoc, boProfile.getPayrollAddress().getState_province(), XPathExpressions.getString("XMLInjector.payRegion"));
		injectElementValue(targetXMLDoc, boProfile.getPayrollAddress().getCountry(), XPathExpressions.getString("XMLInjector.payCountryCode"));
		

		//System.out.println("UPDATE CANDIDATE");
		//System.out.println(targetXMLDoc.asXML());
		
		injectElementValue(targetXMLDoc, canGen.getDateOfBirthString(), XPathExpressions.getString("XMLInjector.dob"));		
		
		return targetXMLDoc;
	}
	
	/**
	 * Loads the DOTarget.xml from resources for injection prior to delivery.
	 * 
	 * @return Document An XML document to inject {@link AdvertisementResponse}
	 *         values into.
	 */
	public static Document loadTargetXMLTemplate(String targetXMLTemplateName)
	{
		Document targetXMLDoc = null;
		
		try
		{
			InputStream targetInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(targetXMLTemplateName);
			SAXReader targetISReader = new SAXReader();
			targetXMLDoc = targetISReader.read(targetInputStream);
		}
		catch (DocumentException de)
		{
			//System.out.println("Error processing the target XML");
		}
		
		return targetXMLDoc;
	}
	
	/**
	 * Injects a value for the element specified by the XPath expression in the
	 * document specified. A null injection value is passed as an empty string.
	 * 
	 * @param targetXMLDoc
	 *            The document to inject the element value into.
	 * @param elementInjectionValue
	 *            The value that should be injected.
	 * @param xPathSelection
	 *            The XPath expression pinpointing the element for injection.
	 */
	public static void injectElementValue(Document targetXMLDoc, String elementInjectionValue, String xPathSelection)
	{
		targetXMLDoc.setXMLEncoding("UTF-8");
		String injectionValue = elementInjectionValue;
		if (elementInjectionValue == null) injectionValue = "";
		Element element = (Element) targetXMLDoc.selectSingleNode(xPathSelection);
		element.setText(injectionValue);
	}
	

	
	
	/**
	 * Injects an attribute on the element specified by the XPath expression in
	 * the document specified. A null injection value is passed as an empty
	 * string.
	 * 
	 * @param targetXMLDoc
	 *            The document to inject the attribute into.
	 * @param attributeName
	 *            The name of the attribute to add.
	 * @param attributeInjectionValue
	 *            The value the attribute will hold.
	 * @param xPathString
	 *            The XPath expression pinpointing the element for injection.
	 */
	public static void injectElementAttribute(Document targetXMLDoc, String attributeName, String attributeInjectionValue, String xPathString)
	{
		String injectionValue = attributeInjectionValue;
		if (attributeInjectionValue == null) injectionValue = "";
		
		Element element = (Element) targetXMLDoc.selectSingleNode(xPathString);
		element.attribute(attributeName).setValue(injectionValue);
	}
}