package com.service;

import com.domain.Poll;
import com.domain.Questions;
import com.domain.VoteCount;
import java.util.List;
import java.util.Map;

/**
 *
 * @author praveen.ka
 */
public interface DataBaseService {
    // public void DataBaseService() throws InstantiationException, IllegalAccessException;

    public int addPollQuestionRow(String pollQ, String creator, String Edate, String dateNow, String status);

    public int addPollOptionsRow(int pollid, String option);

    public void insertDummyVote(int pollId, int optionId);

    public void insertVoteData(String questionid, String user, String optionid);

    public List<Poll> getPolls(String user);

    public List<VoteCount> displayVotes(Map<Integer, Integer> pollCounts, String user);

    public List<Questions> getPollQuestions();

    public List<Map<String, String>> getQuestionsCount();

    public void insertStatus(String status, int pollid);
}
