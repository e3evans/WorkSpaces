dojo.provide("asponte.ui.wcm.EventCalendar");

dojo.require('dijit._Calendar');
dojo.require('dojox.widget.Standby');

dojo.declare('asponte.ui.wcm.EventCalendar',[dijit._Calendar],
{
   value: new Date(),
   storeUrl: '',
   siteArea: '',
   _allEventDates:null,
   _standby:null,
   _evtCache:{},
   _NO_RESULTS:{events:[]},
   _currentNode:null,
   postCreate: function(){
      var _t=this;
      this.inherited(arguments);
      this._standby=new dojox.widget.Standby({target: this.id,imageText:'Loading events'});
	  dojo.query(".dijitCalendarDateTemplate", this.domNode).forEach(
        function(template, i){
			dojo.connect(template,'onmouseover',_t,_t._onDayMouseOver);
			dojo.connect(template,'onmouseout',_t,_t._onDayMouseOut);
        }
      );
      // Set the event details href stored in the content item with the one
      // builded dynamically with the required QueryString parameters needed
      // for the event details page. 
      // Same logic is also required for _adjustDisplay().
      // FIX ME: Manipulating DOM within the DOJO context is not a best practice      // Consider moving the EventDetailsLink to THIS widget instead 
/*      var eventDetailsLink = document.getElementById('eventDetailsLink');
      if (eventDetailsLink != null) {
        eventDetailsLink.href = this._buildEventDetailsUrl(this.displayMonth);
      }*/
      var f=dojo.byId(this.siteArea+'AwwCal');
      if(f){
        var e=dojo.query('.awwCalDetailsLink');
        if(e&&e.length>0){
			dojo.connect(e[0],'onclick',this,this._onDetailsClick);
        }
	  }
   },
   startup: function(){
      document.body.appendChild(this._standby.domNode);
      this._standby.startup();
      this._loadEventInfo(this.displayMonth,dojo.hitch(this,function(){this._populateGrid();}));
   },
   _onDetailsClick: function(/*Event*/ evt){
     var a=evt.target;
     var url=this._buildEventDetailsUrl(a.href,this.displayMonth);
     dojo.stopEvent(evt);
     document.location=url;
   },
   _buildEventDetailsUrl: function(base,startDate){
      var currentMonth=startDate.getMonth()+1;//current month
      var currentYear=startDate.getFullYear();//current Year
      var endDate = new Date(currentYear, currentMonth, 0);
      var endDay=endDate.getDate();
	  if(currentMonth < 10){currentMonth='0' + currentMonth};
      var sDate = currentYear + '-' + currentMonth + '-01';
      var eDate = currentYear + '-' + currentMonth + '-' + endDay;
      var url = base;
      url += '?SiteArea=uuid:';
      url += this.siteArea;
      url += '&MonthStartDate=';
      url += sDate;
      url += '&MonthEndDate=';
      url += eDate;
      return url;
   },
   _loadEventInfo: function(startDate,loadfn){
      var currentMonth=startDate.getMonth()+1;//current month
      var currentYear=startDate.getFullYear();//current Year
      var endDate = new Date(currentYear, currentMonth, 0);
      var endDay=endDate.getDate();//End Day
      if(currentMonth < 10){currentMonth='0' + currentMonth};
      var sDate = currentYear + '-' + currentMonth + '-01';
      var eDate = currentYear + '-' + currentMonth + '-' + endDay;
      var cacheKey=sDate.replace(/[\-]/g,'_');
      if(this._evtCache[cacheKey]){
         this._allEventDates=this._evtCache[cacheKey];
         loadfn();
      }else{
         this._standby.show();
          var url=this.storeUrl+'?SiteArea=uuid:'+this.siteArea+'&MonthStartDate=' + sDate + '&MonthEndDate=' + eDate;
         dojo.xhrGet({ 
            url: url,
            load: dojo.hitch(this,function(response,ioargs) {
            	response=dojo.trim(response);
            	if(response){
            	    this._allEventDates = dojo.fromJson(response);
            	}else{
            		this._allEventDates=this._NO_RESULTS;
            	}
            	this._evtCache[cacheKey]=this._allEventDates;
            	loadfn();
            	this._standby.hide();
            })
         });
      }
   },
   _adjustDisplay: function(part, amount, isLoadDone){
      if(isLoadDone){
         this.inherited(arguments);
      }else{
         var startDate = dojo.date.add(this.displayMonth, part, amount);
         startDate.setDate(1);//current month start date
         this._loadEventInfo(startDate,dojo.hitch(this,function(){this._adjustDisplay(part,amount,true);}));
         var eventDetailsLink = document.getElementById('eventDetailsLink');
         if (eventDetailsLink != null) {
           eventDetailsLink.href = this._buildEventDetailsUrl(startDate);
         }
      }
   },
   isDisabledDate: function(d) {           
      //this will disable any dates that don't have events
      var newDay = '0' + d.getDate();
      newDay = newDay.substring(newDay.length-2, newDay.length); 
      var newMonth = '0' + (d.getMonth() + 1);
      newMonth = newMonth. substring(newMonth.length-2, newMonth.length); 
      var newFullDate = d.getFullYear() + '_' + newMonth + '_' + newDay;
      if(this._allEventDates){
         for(var ii=0;ii<this._allEventDates.events.length;ii++){
            var evt=this._allEventDates.events[ii];
            if(evt.date==newFullDate){return false;}
         }
      }
      return true;
   },
   // Override to return CSS classes to associate event categories with its corresponding color code
   getClassForDate: function(d) {
      var newDay = '0' + d.getDate();
      newDay = newDay.substring(newDay.length-2, newDay.length); 
      var newMonth = '0' + (d.getMonth() + 1);
      newMonth = newMonth. substring(newMonth.length-2, newMonth.length); 
      var newFullDate = d.getFullYear() + '_' + newMonth + '_' + newDay;
      if(this._allEventDates){
         for(var ii=0;ii<this._allEventDates.events.length;ii++){
            var evt=this._allEventDates.events[ii];
            if(evt.date==newFullDate){
               var eventCategory = evt.category;
			   if(evt.category&&evt.category.className){
			      return evt.category.className;
			   }
            }
         }
      }
   },
   _onDayMouseOver: function(/*Event*/ evt){
      var node=evt.target;
      if(node && node.dijitDateValue && !dojo.hasClass(node, "dijitCalendarDisabledDate") && !dojo.hasClass(node, "dijitCalendarPreviousMonth") && !dojo.hasClass(node,"dijitCalendarNextMonth")){
         if(node==this._currentNode){return;}
		 this._currentNode=node;
         this._showEvents(node.dijitDateValue,node);
      }
   },
   _onDayMouseOut: function(/*Event*/ evt){
      if(!this._currentNode){return;}
      else{
         /* Following taken from Calendar.js from dojo 1.4 */
         for(var node = evt.relatedTarget; node;){
           if(node == this._currentNode){ return; }
           try{
             node = node.parentNode;
           }catch(x){
             node = null;
           }
         }
         this._hideEvents(this._currentNode);
         this._currentNode=null;
      }
   },
   _showEvents: function(dateValue, theNode){
      if(dateValue&&theNode){
         var d=new Date(dateValue);
         var newDay = '0' + d.getDate();
         newDay = newDay.substring(newDay.length-2, newDay.length); 
         var newMonth = '0' + (d.getMonth() + 1);
         newMonth = newMonth. substring(newMonth.length-2, newMonth.length); 
         var newFullDate = d.getFullYear() + '_' + newMonth + '_' + newDay;
         var data='<div class="awwCalEvents">';
         var jj=0;
         for(var ii=0;ii<this._allEventDates.events.length;ii++){
            var evt=this._allEventDates.events[ii];
            if(evt.date==newFullDate){
               if(jj==0){data+='<h2>'+evt.displayDate+'</h2>';}
               if(evt.start&&evt.start.length>0){
	               data+='<h3>'+evt.start;
	               if(evt.end&&evt.end.length>0){
	            	   data+=' - '+evt.end;
	               }
	               data+='</h3>';
               }
               data+='<h5>'+evt.title+'</h5>';
               data+='<p>'+evt.location+'</p>';
               jj++;
            }
         }
         data+='</div>';
         if(ii>0){
            dijit.hideTooltip(theNode);
            dijit.showTooltip(data, theNode, ['above','below']);
         }
      }
   },
   _hideEvents: function(node){
         dijit.hideTooltip(node);
   }
});
