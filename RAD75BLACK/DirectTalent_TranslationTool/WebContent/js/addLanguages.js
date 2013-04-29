$(document).ready(function() {


	var remDialog = $('#remLang').dialog({
		autoOpen: false,
		title: 'Choose a language to filter',
		closeOnEscape: true,
		draggable: true,
		modal: true,
		height: 150
	});

	$(".remCancelLangBtn").click(function(event) {
		remDialog.dialog('close');
	});
	$(".remLangButton").click(function(event) {
		event.preventDefault();
		remDialog.dialog('open');
	});




	$(".remLangBtn").click(function(event) {
		  event.preventDefault();
		  var showLang = $('input[class="filterLangCheck"]:checked');
		  var remLang = $('input[class="filterLangCheck"]:not(:checked)');
		  if (!showLang || showLang.size() == 0) {
			  alert('You must at least show one language');
			  return;
		  }
		  $('.languagesShow').val("");
		  $('.allLanguages').val("");
		  var firstTime = true;
		  remLang.each(function() {
			  var langElegido =$(this).attr('name');


			  $('.propLine').each(function(index){
				  var key = $('.propKey', $(this)).html();
				  var escapedKey = key.replace('.','-');
				  var langsKeySel = "." + escapedKey + "_" + langElegido;
				  var langsKey = $(langsKeySel, $(this));
				  if (langsKey && langsKey.size() > 0) {
					  langsKey.hide();
				  }
			  });
		  });
		  showLang.each(function() {
			  var langElegido =$(this).attr('name');
			  if (firstTime) {
				  $('.languagesShow').val(langElegido);
				  $('.allLanguages').val(langElegido);
				  firstTime = false;
			  } else {
				  $('.languagesShow').val($('.languagesShow').val() + ',' + langElegido);
				  $('.allLanguages').val($('.allLanguages').val() + ',' + langElegido);
			  }

			  var langTemplate = $('.langTemplate');
			  $('.propLine').each(function(index){
				  var key = $('.propKey', $(this)).html();
				  var escapedKey = key.replace('.','-');
				  var langsKeySel = "." + escapedKey + "_" + langElegido;
				  var langsKey = $(langsKeySel, $(this));
				  if (!langsKey || langsKey.size() == 0) {
					  var langNew = langTemplate.clone();
					  $('.langName', langNew).html(langElegido + ":");
					  $('.langValue', langNew).attr('name',key + '_' + langElegido);
					  $('dd', langNew).addClass(escapedKey +  "_" + langElegido);
					  $('dt', langNew).addClass(escapedKey +  "_" + langElegido);
					  var propLine = $(this);
					  langNew.children().each(function(){
						  $('.dlPropLine' , propLine).append($(this));
					  }
					  );
				  } else {
					  langsKey.show();
				  }

			  });


		  });

		  remDialog.dialog('close');
	});

});