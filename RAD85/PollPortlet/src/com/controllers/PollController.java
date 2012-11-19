/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.AbstractController;
import com.service.DataBaseService;
import com.domain.Poll;
import com.domain.VoteCount;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

/**
 *
 * @author praveen.ka
 */
public class PollController extends AbstractController {

    private DataBaseService dataBaseService;

    public DataBaseService getDataBaseService() {
        return dataBaseService;
    }

    public void setDataBaseService(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    @Override
    public void handleActionRequest(ActionRequest request, ActionResponse response) {
    }

    /**
     *Display voted questions and not voted questions
     */
    
    
    @Override
    public ModelAndView handleRenderRequest(RenderRequest request,
            RenderResponse response) throws Exception {

        Map<String, Object> model = new HashMap<String, Object>();

        Map<String, Object> userAttributeMap = (Map<String, Object>) request.getAttribute(PortletRequest.USER_INFO);

       
        String currentLoginUser = "";

        if (userAttributeMap != null) {

            currentLoginUser = (String) userAttributeMap.get("user.name.family");
            request.setAttribute("currentUser", currentLoginUser);
        }
        
        PortletSession session = request.getPortletSession();
        session.setAttribute("currentUser", currentLoginUser);

        List<Map<String, String>> questioncount = dataBaseService.getQuestionsCount();

        List<Poll> polls = dataBaseService.getPolls(currentLoginUser);

        model.put("pollData", polls);

        Map<Integer, Integer> pollCounts = new HashMap<Integer, Integer>();

        List<VoteCount> voteData = dataBaseService.displayVotes(pollCounts, currentLoginUser);

        model.put("votesData", voteData);
        model.put("pollCounts", pollCounts);
        model.put("questioncount", questioncount);


        return new ModelAndView("polls", model);
    }
}
