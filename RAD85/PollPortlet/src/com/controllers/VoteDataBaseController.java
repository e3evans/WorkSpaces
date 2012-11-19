/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.service.CustomException;
import com.service.DataBaseService;
import java.util.HashMap;
import java.util.Map;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.AbstractController;

public class VoteDataBaseController extends AbstractController {

    private DataBaseService dataBaseService;

    public DataBaseService getDataBaseService() {
        return dataBaseService;
    }

    public void setDataBaseService(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    /**
     * We get QuestionId, OptionId and User when vote button clicked
     */
    @Override
    public void handleActionRequest(ActionRequest request, ActionResponse response) {
        try {
            String optionId = request.getParameter(request.getParameter("qName"));

            String questionId = request.getParameter("qid");

            PortletSession session = request.getPortletSession();

            String currentUser = (String) session.getAttribute("currentUser");

            dataBaseService.insertVoteData(questionId, currentUser, optionId);

            response.setRenderParameter("myaction", "");

            response.setRenderParameter("pollid", questionId);
        } catch (Exception e) {
           new CustomException("unable to connect to database");
        }

    }

    @Override
    public ModelAndView handleRenderRequest(RenderRequest request, RenderResponse response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();

        return new ModelAndView("polls", model);
    }
}
