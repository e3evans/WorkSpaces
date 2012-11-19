/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.domain.Questions;
import com.service.DataBaseService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.AbstractController;

public class EditController extends AbstractController {

    private DataBaseService dataBaseService;

    public DataBaseService getDataBaseService() {
        return dataBaseService;
    }

    public void setDataBaseService(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

   /**
     *get form data(question,choices,expire date ) and insert into tables
     */
    
    @Override
    protected void handleActionRequestInternal(ActionRequest request,
            ActionResponse response) throws Exception {

        List<String> l = new ArrayList<String>();

        for (Enumeration<String> val = request.getParameterNames(); val.hasMoreElements();) {
            String str = val.nextElement();

            if (str.contains("choice")) {

                l.add(str);
            }

        }

        PortletSession session = request.getPortletSession();

        if (request.getParameter("formname").equals("statusform")) {

            String status = request.getParameter("status");
            int pollid = Integer.parseInt(request.getParameter("pollid"));
            dataBaseService.insertStatus(status, pollid);

        } else {

            String pollQ = request.getParameter("pollQ");

            String Edate = request.getParameter("Edate");

            Calendar currentDate = Calendar.getInstance();

            SimpleDateFormat formatter =
                    new SimpleDateFormat("yyyy-MM-dd");

            SimpleDateFormat expireformatter =
                    new SimpleDateFormat("dd/MM/yyyy");

            Date expireDate = (Date) expireformatter.parse(Edate);

            String dateNow = formatter.format(currentDate.getTime());



            String creator = (String) session.getAttribute("currentUser");


            int questintbsize = dataBaseService.addPollQuestionRow(pollQ, creator,
                    formatter.format(expireDate), dateNow, "Active");
            Iterator i = l.iterator();
            while (i.hasNext()) {

                String str = (String) i.next();


                int maxOptionID = dataBaseService.addPollOptionsRow(questintbsize, request.getParameter(str));
                dataBaseService.insertDummyVote(questintbsize, maxOptionID);
            }
        }

        // response.setPortletMode(PortletMode.VIEW);
        response.setRenderParameter("myaction", "");
    }

   /**
     * get poll questions from POllS table and display config page
     */
    @Override
    protected ModelAndView handleRenderRequestInternal(RenderRequest request,
            RenderResponse response) throws Exception {

        List<Questions> questions = dataBaseService.getPollQuestions();

        Map<String, Object> model = new HashMap<String, Object>();

        model.put("questionsdata", questions);

        return new ModelAndView("Edit", model);

    }
}