var portletNamespace = 'view<portlet:namespace/>';
var selectedOldDateUSA = false;

function getMonthString(month)
{
	var months = new Array('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec');
	var resultMonth = "";
	if(month == "01")
	{
		resultMonth += months[0];
	}else if(month == "02")
	{
		resultMonth += months[1];
	}else if(month == "03")
	{
		resultMonth += months[2];
	}else if(month == "04")
	{
		resultMonth += months[3];
	}else if(month == "05")
	{
		resultMonth += months[4];
	}else if(month == "06")
	{
		resultMonth += months[5];
	}else if(month == "07")
	{
		resultMonth += months[6];
	}else if(month == "08")
	{
		resultMonth += months[7];
	}else if(month == "09")
	{
		resultMonth += months[8];
	}else if(month == "10")
	{
		resultMonth += months[9];
	}else if(month == "11")
	{
		resultMonth += months[10];
	}else if(month == "12")
	{
		resultMonth += months[11];
	}
	
	return resultMonth;
}





function showErrorElement(formId,subviewOne, subviewTwo, elementId)
{
	document.getElementById(portletNamespace+":"+formId+":"+subviewOne+":"+subviewTwo+":"+elementId).style.display = "";
	
}
function hideErrorElement(formId,subviewOne, subviewTwo, elementId)
{
	document.getElementById(portletNamespace+":"+formId+":"+subviewOne+":"+subviewTwo+":"+elementId).style.display = "none";	
	
}
function showConsentError(formId, subviewOne, subviewTwo, subviewThree, elementId)
{
	document.getElementById(portletNamespace+":"+formId+":"+subviewOne+":"+subviewTwo+":" + subviewThree+":"+elementId).style.display = "";
	
}
function hideConsentError(formId, subviewOne, subviewTwo, subviewThree, elementId)
{
	document.getElementById(portletNamespace+":"+formId+":"+subviewOne+":"+subviewTwo+":" + subviewThree+":"+elementId).style.display = "none";
	
}

function formatDate(format) {
	var dateObj = new Date();
	var fmt = format.toUpperCase();
    var re = /^(M|MM|MMM|D|DD|YYYY)([\-\/]{1})(M|MM|MMM|D|DD|YYYY)(\2)(M|MM|MMM|D|DD|YYYY)$/;
    if (!re.test(fmt)) { fmt = "MM/DD/YYYY"; }
    if (fmt.indexOf("M") == -1) { fmt = "MM/DD/YYYY"; }
    if (fmt.indexOf("D") == -1) { fmt = "MM/DD/YYYY"; }
    if (fmt.indexOf("YYYY") == -1) { fmt = "MM/DD/YYYY"; }
    
    var M = "" + (dateObj.getMonth()+1);
    var MM = "0" + M;
    MM = MM.substring(MM.length-2, MM.length);
    var MMM = getMonthString(MM);
    var D = "" + (dateObj.getDate());
    var DD = "0" + D;
    DD = DD.substring(DD.length-2, DD.length);
    var YYYY = "" + (dateObj.getFullYear());
    
    var sep = "/";
    if (fmt.indexOf("-") != -1) { sep = "-"; }
    var pieces = fmt.split(sep);
    var result = "";
    
    switch (pieces[0]) {
         case "M" : result += M + sep; break;
         case "MM" : result += MM + sep; break;
         case "MMM" : result += MMM + sep; break;
         case "D" : result += D + sep; break;
         case "DD" : result += DD + sep; break;
         case "YYYY" : result += YYYY + sep; break;
    } 
    switch (pieces[1]) {
         case "M" : result += M + sep; break;
         case "MM" : result += MM + sep; break;
         case "MMM" : result += MMM + sep; break;
         case "D" : result += D + sep; break;
         case "DD" : result += DD + sep; break;
         case "YYYY" : result += YYYY + sep; break;
    }
    
    switch (pieces[2]) {
         case "M" : result += M; break;
         case "MM" : result += MM; break;
         case "MMM" : result += MMM + sep; break;
         case "D" : result += D; break;
         case "DD" : result += DD; break;
         case "YYYY" : result += YYYY; break;
    }
    
    return result;
}

function isFirstDateBiggerUSA(firstDate, secondDate)
{
	
	var firstDateUSA = firstDate.substring(0, 2);
	var firstMonthUSA = firstDate.substring(3, 5);
	var firstYearUSA = firstDate.substring(6, 10);
	var secondDateUSA = secondDate.substring(0, 2);
	var secondMonthUSA = secondDate.substring(3, 5);
	var secondYearUSA = secondDate.substring(6, 10);
	var fDate = new Date();
	fDate.setFullYear(firstYearUSA, firstMonthUSA, firstDateUSA);
	
	var sDate = new Date();
	
	sDate.setFullYear(secondYearUSA, secondMonthUSA, secondDateUSA);
	
	
	if(fDate > sDate)
	{
		return true;
	}

	return false;
		
}

function checkForm()
{
	
	var addressLineOne =document.getElementById(portletNamespace+":candidate_form:usaSubview:usaCandidateData_subview:addressLineOne").value;
	
	var usaCity = document.getElementById(portletNamespace+":candidate_form:usaSubview:usaCandidateData_subview:main_city_in").value;
	var usausaState = document.getElementById(portletNamespace+":candidate_form:usaSubview:usaCandidateData_subview:main_state_select").value;
	var usaCountry = document.getElementById(portletNamespace+":candidate_form:usaSubview:usaCandidateData_subview:main_country_in").value;
	
	var usaPayrollDiff = document.getElementById(portletNamespace+":candidate_form:usaSubview:usaCandidateData_subview:payrollDiffFromMain").value;
	var haveWorkVisa = document.getElementById(portletNamespace+":candidate_form:usaSubview:usaCandidateData_subview:visaMenu").value;
	//var visaExpDate = document.getElementById(portletNamespace + ":candidate_form:usaSubview:usaCandidateData_subview:datum11").value;
	var usaVisaExpDate = document.getElementById(portletNamespace+":candidate_form:usaSubview:usaCandidateData_subview:datum1").value;
	
	var isVisaExpErrorShown = document.getElementById(portletNamespace+":candidate_form:usaSubview:usaCandidateData_subview:visaExpError").value;
	
	var usaCheckBox = portletNamespace+":candidate_form:usaSubview:usaCandidateData_subview:consentSubview:confirmTrue";
	
	var transformedCurDate = formatDate("DD-MM-YYYY");
	
	var isUSAAddressOneValid = false;

	var isUSAusaCityValid = false;
	var isUSAusaStateValid = false;
	var isUSAusaCountryValid = false;
	var isUSAConsentValid = false;
	var isUSAusaVisaExpDateValid = false;
	var isUSAPossessVisa = false;
	var isUSAusaPayrollDiffValid = false;
	
	if(usaPayrollDiff == 'true')
	{
		if(addressLineOne == null || addressLineOne == "")
		{	
			showErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','addressLineOneError');
			isUSAAddressOneValid = false;
		}else{
			hideErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','addressLineOneError');
			isUSAAddressOneValid = true;
		}
		if(usaCity == null || usaCity == "")
		{
			showErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','cityError');
			isUSAusaCityValid = false;
		}else{
			hideErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','cityError');
			isUSAusaCityValid = true;
		}
		if(usausaState == null || usausaState == "")
		{
			showErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','stateError');
			isUSAusaStateValid = false;
		}else{
			hideErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','stateError');
			isUSAusaStateValid = true;
		}
		if(usaCountry == null || usaCountry == "")
		{
			showErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','countryError');
			isUSAusaCountryValid = false;
		}else{
			hideErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','countryError');
			isUSAusaCountryValid = true;
		}
		
		hideErrorElement('candidate_form','usaSubview','usaCandidateData_subview','payrollDiffError');
		isUSAusaPayrollDiffValid = true;
		
	}else{
		hideErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','addressLineOneError');
		hideErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','cityError');
		hideErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','stateError');
		hideErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','postalCodeError');
		hideErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','countryError');
		
		isUSAAddressOneValid = true;
		isUSAusaCityValid = true;
		isUSAusaStateValid = true;
		isUSAusaCountryValid = true;
		
		if(!(addressLineOne == null || addressLineOne == "")  || 
			!(usaCity == null || usaCity == "") 					  ||
			!(usaState == null || usaState == "") 				  ||
			!(usaCountry == null || usaCountry == ""))
		{
			showErrorElement('candidate_form','usaSubview','usaCandidateData_subview','payrollDiffError');
			isUSAusaPayrollDiffValid = false;
			
		}else{
			hideErrorElement('candidate_form','usaSubview','usaCandidateData_subview','payrollDiffError');
			isUSAusaPayrollDiffValid = true;
		}
	}
	alert("haveWorkVisa = " + haveWorkVisa);
	if(haveWorkVisa == 'true')
	{
		alert("haveWorkVisa = " + haveWorkVisa);
		if(usaVisaExpDate == null || usaVisaExpDate == "")
		{
			showErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','visaExpError');
			isUSAusaVisaExpDateValid = false;
		}else{
			hideErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','visaExpError');
			isUSAusaVisaExpDateValid = true;	
		}

		alert("transform date");
		var expired = isFirstDateBiggerUSA(transformedCurDate, usaVisaExpDate);
		if(usaVisaExpDate != null && usaVisaExpDate != "" && expired)
		{
			showErrorElement('candidate_form', 'usaSubview', 'usaCandidateData_subview', 'visaDateError');
			
			if(selectedOldDateUSA == true)
			{
				isUSAusaVisaExpDateValid = true;
			}
			else
			{
				isUSAusaVisaExpDateValid = false;
			}
			
			if(selectedOldDateUSA == false)
			{
				selectedOldDateUSA = true;
			}
			
		} else if(usaVisaExpDate != null && usaVisaExpDate != "")
		{
			hideErrorElement('candidate_form', 'usaSubview', 'usaCandidateData_subview', 'visaDateError');
			isUSAusaVisaExpDateValid = true;
		}
	}else{
		hideErrorElement('candidate_form', 'usaSubview', 'usaCandidateData_subview', 'visaDateError');
		hideErrorElement('candidate_form','usaSubview', 'usaCandidateData_subview','visaExpError');
		isUSAusaVisaExpDateValid = true;
		
	}
	
	
	if(haveWorkVisa == "false" || haveWorkVisa == "blank")
	{
		if(usaVisaExpDate != null && usaVisaExpDate != "")
		{
			showErrorElement('candidate_form', 'usaSubview', 'usaCandidateData_subview', 'visaPossessError');
			isUSAPossessVisa = false;
		}
		else
		{
			hideErrorElement('candidate_form', 'usaSubview', 'usaCandidateData_subview', 'visaPossessError');
			isUSAPossessVisa = true;
		}
	}
	else {
		hideErrorElement('candidate_form', 'usaSubview', 'usaCandidateData_subview', 'visaPossessError');
		isUSAPossessVisa = true;
	}
	
	
	if(document.getElementById(usaCheckBox).checked == false)
	{
		showConsentError('candidate_form','usaSubview', 'usaCandidateData_subview', 'consentSubview', 'consentErrorTxt');
		isUSAConsentValid = false;
	}else{
		hideConsentError('candidate_form','usaSubview', 'usaCandidateData_subview', 'consentSubview', 'consentErrorTxt');
		isUSAConsentValid = true;
	}
	
	
	if(isUSAAddressOneValid == true && isUSAusaCityValid == true 
			&& isUSAusaStateValid == true && isUSAusaCountryValid == true
			&& isUSAConsentValid == true && isUSAusaVisaExpDateValid == true && isUSAPossessVisa == true
			&& isUSAusaPayrollDiffValid == true)
		{
			
			return true;
		}else{
			
			return false;
		}
	
	
}

