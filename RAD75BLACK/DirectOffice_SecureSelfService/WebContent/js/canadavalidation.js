var portletNamespace = 'view<portlet:namespace/>';
var selectedOldDate = false;

function getCanadaMonthString(month)
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

function formatCanadaDate(dateValue, format) {
	var fmt = format.toUpperCase();
    var re = /^(M|MM|MMM|D|DD|YYYY)([\-\/]{1})(M|MM|MMM|D|DD|YYYY)(\2)(M|MM|MMM|D|DD|YYYY)$/;
    if (!re.test(fmt)) { fmt = "MM/DD/YYYY"; }
    if (fmt.indexOf("M") == -1) { fmt = "MM/DD/YYYY"; }
    if (fmt.indexOf("D") == -1) { fmt = "MM/DD/YYYY"; }
    if (fmt.indexOf("YYYY") == -1) { fmt = "MM/DD/YYYY"; }
    
    var M = "" + (dateValue.getMonth()+1);
    var MM = "0" + M;
    MM = MM.substring(MM.length-2, MM.length);
    var MMM = getCanadaMonthString(MM);
    var D = "" + (dateValue.getDate());
    var DD = "0" + D;
    DD = DD.substring(DD.length-2, DD.length);
    var YYYY = "" + (dateValue.getFullYear());
    
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



function showCanadaErrorElement(formId,subviewOne, subviewTwo, elementId)
{
	document.getElementById(portletNamespace+":"+formId+":"+subviewOne+":"+subviewTwo+":"+elementId).style.display = "";	
}
function hideCanadaErrorElement(formId,subviewOne, subviewTwo, elementId)
{
	document.getElementById(portletNamespace+":"+formId+":"+subviewOne+":"+subviewTwo+":"+elementId).style.display = "none";	
}
function showCanadaConsentError(formId, subviewOne, subviewTwo, subviewThree, elementId)
{
	document.getElementById(portletNamespace+":"+formId+":"+subviewOne+":"+subviewTwo+":" + subviewThree+":"+elementId).style.display = "";
}
function hideCanadaConsentError(formId, subviewOne, subviewTwo, subviewThree, elementId)
{
	document.getElementById(portletNamespace+":"+formId+":"+subviewOne+":"+subviewTwo+":" + subviewThree+":"+elementId).style.display = "none";
}

function isFirstDateBiggerCanada(firstDate, secondDate)
{
	var firstDateCanada = firstDate.substring(0, 2);
	var firstMonthCanada = firstDate.substring(3, 5);
	var firstYearCanada = firstDate.substring(6, 10);
	
	var secondDateCanada = secondDate.substring(0, 2);
	var secondMonthCanada = secondDate.substring(3, 5);
	var secondYearCanada = secondDate.substring(6, 10);
	
	
	var fDateCanada = new Date();
	fDateCanada.setFullYear(firstYearCanada, firstMonthCanada, firstDateCanada);
	
	
	var sDateCanada = new Date();
	sDateCanada.setFullYear(secondYearCanada, secondMonthCanada, secondDateCanada);
	
	if(fDateCanada > sDateCanada)
	{
		return true;
	}
	
	return false;	
}

function checkCanadaForm()
{
	
	var addressLineOne =document.getElementById(portletNamespace+":candidate_form:canadaSubview:canadaCandidateData_subview:addressLineOne").value;
	var city = document.getElementById(portletNamespace+":candidate_form:canadaSubview:canadaCandidateData_subview:main_city_in").value;
	var state = document.getElementById(portletNamespace+":candidate_form:canadaSubview:canadaCandidateData_subview:main_state_select").value;
	var country = document.getElementById(portletNamespace+":candidate_form:canadaSubview:canadaCandidateData_subview:main_country_in").value;
	var payrollDiff = document.getElementById(portletNamespace+":candidate_form:canadaSubview:canadaCandidateData_subview:payrollDiffFromMain").value;
	var haveWorkVisa = document.getElementById(portletNamespace+":candidate_form:canadaSubview:canadaCandidateData_subview:visaMenu").value;
	
	//var visaExpDate = document.getElementById("datum3").value;
	var visaExpDate = document.getElementById(portletNamespace+":candidate_form:canadaSubview:canadaCandidateData_subview:datum3").value;
	
	var checkbox = portletNamespace+":candidate_form:canadaSubview:canadaCandidateData_subview:consentSubview:confirmTrue";
	
	var currentDateObject = new Date();
	var transformedCurDate = formatCanadaDate(currentDateObject, "DD-MM-YYYY");
	
	var isValid = false;
	var isAddressOneValid = false;
	var isCityValid = false;
	var isStateValid = false;

	var isCountryValid = false;
	var isConsentValid = false;
	var isVisaExpDateValid = false;
	var isPossessVisa = false;
	var isPayrollDiffValid = false;
	
	if(payrollDiff == 'true')
	{
		if(addressLineOne == null || addressLineOne == "")
		{	
			showCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','addressLineOneError');
			isAddressOneValid = false;
		}else{
			hideCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','addressLineOneError');
			isAddressOneValid = true;
		}
		if(city == null || city == "")
		{
			showCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','cityError');
			isCityValid = false;
		}else{
			hideCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','cityError');
			isCityValid = true;
		}
		if(state == null || state == "")
		{
			showCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','stateError');
			isStateValid = false;
		}else{
			hideCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','stateError');
			isStateValid = true;
		}
		if(country == null || country == "")
		{
			showCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','countryError');
			isCountryValid = false;
		}else{
			hideCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','countryError');
			isCountryValid = true;
		}
		
		hideCanadaErrorElement('candidate_form','canadaSubview','canadaCandidateData_subview','payrollDiffError');
		isPayrollDiffValid = true;
		
	}else{
		hideCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','addressLineOneError');
		hideCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','cityError');
		hideCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','stateError');
		hideCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','countryError');
		
		isAddressOneValid = true;
		isCityValid = true;
		isStateValid = true;
		isCountryValid = true;
		
		if(!(addressLineOne == null || addressLineOne == "")  || 
			!(city == null || city == "") 					  ||
			!(state == null || state == "") 				  ||
			!(country == null || country == ""))
		{
			showCanadaErrorElement('candidate_form','canadaSubview','canadaCandidateData_subview','payrollDiffError');
			isPayrollDiffValid = false;
			
		}else{
			hideCanadaErrorElement('candidate_form','canadaSubview','canadaCandidateData_subview','payrollDiffError');
			isPayrollDiffValid = true;
		}
	}
	if(haveWorkVisa == 'true')
	{
		
		if(visaExpDate == null || visaExpDate == "")
		{
			showCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','visaExpError');
			isVisaExpDateValid = false;
		}else{
			hideCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','visaExpError');
			isVisaExpDateValid = true;	
		}
		var expCanada = isFirstDateBiggerCanada(transformedCurDate, visaExpDate);
		if(visaExpDate != null && visaExpDate != "" && expCanada)
		{
			showCanadaErrorElement('candidate_form', 'canadaSubview', 'canadaCandidateData_subview', 'visaDateError');
			
			if(selectedOldDate == true)
			{
				isVisaExpDateValid = true;
			}
			else
			{
				isVisaExpDateValid = false;
			}
			
			if(selectedOldDate == false)
			{
				selectedOldDate = true;
			}
			
		}
		else if(visaExpDate != null && visaExpDate != "")
		{
			hideCanadaErrorElement('candidate_form', 'canadaSubview', 'canadaCandidateData_subview', 'visaDateError');
			isVisaExpDateValid = true;
		}
	}else{
		hideCanadaErrorElement('candidate_form', 'canadaSubview', 'canadaCandidateData_subview', 'visaDateError');
		hideCanadaErrorElement('candidate_form','canadaSubview', 'canadaCandidateData_subview','visaExpError');
		isVisaExpDateValid = true;
		
	}
	
	if(haveWorkVisa == "false" || haveWorkVisa == "blank")
	{
		if(!(visaExpDate == null || visaExpDate == ""))
		{
			showCanadaErrorElement('candidate_form', 'canadaSubview', 'canadaCandidateData_subview', 'visaPossessError');
			isPossessVisa = false;
		}
		else
		{
			hideCanadaErrorElement('candidate_form', 'canadaSubview', 'canadaCandidateData_subview', 'visaPossessError');
			isPossessVisa = true;
		}
	}
	else {
		hideCanadaErrorElement('candidate_form', 'canadaSubview', 'canadaCandidateData_subview', 'visaPossessError');
		isPossessVisa = true;
	}
	
	if(document.getElementById(checkbox).checked == false)
	{
		showCanadaConsentError('candidate_form','canadaSubview', 'canadaCandidateData_subview', 'consentSubview', 'consentErrorTxt');
		isConsentValid = false;
	}else{
		hideCanadaConsentError('candidate_form','canadaSubview', 'canadaCandidateData_subview', 'consentSubview', 'consentErrorTxt');
		isConsentValid = true;
	}
	
	if(isAddressOneValid == true && isCityValid == true 
		&& isStateValid == true && isCountryValid == true
		&& isConsentValid == true && isVisaExpDateValid == true && isPossessVisa == true
		&& isPayrollDiffValid == true)
	{
		return true;
	}else{
		return false;
	}
	
}