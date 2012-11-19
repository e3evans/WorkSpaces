/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.domain.Poll;
import com.domain.Questions;
import com.domain.VoteCount;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseServiceImpl implements DataBaseService {

    public Connection conn;

    /**  Create DataBase Connection   */
    
    public DataBaseServiceImpl() {

        try {

            // Class.forName("org.apache.derby.jdbc.ClientDriver");
             //  conn = DriverManager.getConnection("jdbc:derby://localhost:1527/PollsDb", "admin", "admin");
            
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:derby:/home/virtuser/APP;create=true");
            
        } catch (Exception e) {
            new CustomException("unable to connect to database");
        }
    }
    /**Insert values into POLLS Table   
     *@param String pollQuestion
     *@param String creator 
     * @param String Edate
     * @param String dateNow
     * @param String status
     * @return int
     */
   

    public int addPollQuestionRow(String pollQ, String creator, String Edate, String dateNow, String status) {

        String addQry = "INSERT INTO APP.POLLS (POLLQUETION,CREATOR,CREATEDDATE,EXPIREDATE,STATUS) values(?,?,?,?,?)";
             int maxPollId = 0;
        try {

            PreparedStatement ps = conn.prepareStatement(addQry);

            ps.setString(1, pollQ);

            ps.setString(2, creator);

            ps.setString(3, dateNow);

            ps.setString(4, Edate);

            ps.setString(5, status);

            int succes = ps.executeUpdate();

            if (succes != 0) {
                System.out.println("successfully updated=" + succes);
            } else {
                System.out.println("not updated");
            }

        } catch (Exception e) {
            new CustomException("unable to connect to database");
        }
        String query = "select MAX(POLLID) from APP.POLLS";
        try {
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                maxPollId = rs.getInt(1);
            }

        } catch (Exception e) {
            new CustomException("unable to connect to database");
        }
        return maxPollId;
    }

    /**   Insert values into POLLOPTIONS Table 
     * @param pollid which contains question id  
     * @param option 
     * @return int 
     */
    public int addPollOptionsRow(int pollid, String option) {
        int maxOptionId = 0;
        try {
            String optionqury = "INSERT INTO APP.POLLOPTION(POLLID,POLLOPTIONS) values(?,?)";

            PreparedStatement ps1 = conn.prepareStatement(optionqury);

            ps1.setInt(1, pollid);
            ps1.setString(2, option);

            int succes = ps1.executeUpdate();
            if (succes != 0) {
                
                String query = "select MAX(ID) from APP.POLLOPTION";
                try {
                    java.sql.Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    while (rs.next()) {
                        maxOptionId = rs.getInt(1);
                    }
                    System.out.println("maxoptionId" + maxOptionId);
                } catch (Exception e) {
                    new CustomException("unable to connect to database");
                }

            } else {
                System.out.println("not updated in polls option");
            }
        } catch (SQLException ex) {
            new CustomException("unable to connect to database");
        }
        return maxOptionId;
    }
    /**  Create Current Date */
    
    Calendar currentDate = Calendar.getInstance();
    SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd");
    String dateNow = formatter.format(currentDate.getTime());
    

    /**Get Poll Questions and Options those questions  are not voted
     * @param String user who has logged in
     * @return List of  poll bean which contains questions and options
     */
    public List<Poll> getPolls(String user) {

        List<Poll> li = new ArrayList<Poll>();

        String pollQuery = "select a.pollid,a.pollquetion,b.ID,b.POLLOPTIONS from "
                + "app.polls a,app.polloption b where a.POLLID = b.POLLID and a.status = 'Active'  and  "
                + " a.POLLID not in (select POLLID from APP.POLLDATA where upper(USERID)=upper('" + user + "'))"
                + "and '" + dateNow + "'<a.EXPIREDATE"
                + " order by a.pollid";

        try {
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(pollQuery);

            Poll poll = null;

            while (rs.next()) {
                poll = new Poll();
                poll.setPollId(rs.getString(1));
                poll.setQname(rs.getString(2));
                poll.setId(rs.getString(3));
                poll.setOption(rs.getString(4));
                li.add(poll);

            }
        } catch (Exception e) {
            new CustomException("unable to connect to database");

        }
        return li;
    }

    /** Insert Votes into POLLDATA Table 
     *@param String questionId
     * @param String user
     * @param String optionId
     */
    public void insertVoteData(String questionid, String user, String optionid) {
        try {
            String insertquery = "insert into app.polldata values(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(insertquery);
            pst.setInt(1, Integer.parseInt(questionid));
            pst.setString(2, user);
            pst.setInt(3, Integer.parseInt(optionid));
            int success = pst.executeUpdate();
            if (success != 0) {
                System.out.println("Success fully row inserted");
            } else {
                System.out.println("row not inserted");
            }

        } catch (SQLException ex) {

            new CustomException("unable to connect to database");
        }
    }

    /** get questions ,options and each option count  
     * @param Map pollCounts
     * @param String user
     * @return List
     */
    public List<VoteCount> displayVotes(Map<Integer, Integer> pollCounts, String user) {
        List<VoteCount> list = new ArrayList<VoteCount>();

        try {
            String displayQry = "select a.pollid,a.pollquetion,b.ID,b.POLLOPTIONS,COUNT(b.ID) count "
                    + "from app.polls a,app.polloption b,APP.POLLDATA c where a.POLLID = b.POLLID and  "
                    + " b.ID=c.OPTIONID  and '" + dateNow + "'<a.EXPIREDATE and a.status = 'Active' and   a.POLLID  in (select POLLID from APP.POLLDATA where "
                    + "upper(USERID)=upper('" + user + "')) group by a.pollid,a.pollquetion,b.ID,b.POLLOPTIONS "
                    + "order by a.pollid";
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(displayQry);
            VoteCount voteData = null;
            while (rs.next()) {
                voteData = new VoteCount();
                voteData.setPollId(rs.getInt(1));
                voteData.setQuestionName(rs.getString(2));
                voteData.setOptionId(rs.getInt(3));
                voteData.setOptionName(rs.getString(4));
                voteData.setCount(rs.getInt(5));
                list.add(voteData);
                if (pollCounts.get(voteData.getPollId()) == null) {
                    pollCounts.put(voteData.getPollId(), voteData.getCount());
                } else {
                    pollCounts.put(voteData.getPollId(), voteData.getCount()
                            + pollCounts.get(voteData.getPollId()));
                }
            }

        } catch (Exception e) {
            new CustomException("unable to connect to database");
        }
        return list;
    }

    /**  Insert dummy vote for every option  when question created
     *@param int pollId
     * @param int optionId
     */
    public void insertDummyVote(int pollId, int optionId) {
        try {
            String optionqury = "INSERT INTO APP.POLLDATA(POLLID,USERID,OPTIONID) values(?,?,?)";

            PreparedStatement ps1 = conn.prepareStatement(optionqury);

            ps1.setInt(1, pollId);
            ps1.setString(2, "Dummy");
            ps1.setInt(3, optionId);

            int succes = ps1.executeUpdate();
            if (succes != 0) {
                System.out.println("successfully updated in POLLDATA Dummy Row=" + succes);
            } else {
                System.out.println("not updated in polls option");
            }
        } catch (SQLException ex) {
            new CustomException("unable to connect to database");
        }

    }

    /** Get all poll questions for config page with current status
     *@return List
     */
    public List<Questions> getPollQuestions() {
        List<Questions> li = new ArrayList<Questions>();

        try {
            String displayQry = "select a.pollid,a.pollquetion,a.status from app.polls a "
                    + "where '" + dateNow + "'<a.EXPIREDATE";
            System.out.println("in get poll");
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(displayQry);
            Questions question = null;
            System.out.println("after get poll");
            while (rs.next()) {
                question = new Questions();

                question.setQuestionID(rs.getInt(1));
                question.setQuestion(rs.getString(2));
                question.setStatus(rs.getString(3));
                li.add(question);

            }

        } catch (SQLException ex) {
            new CustomException("unable to connect to database");
        }

        return li;

    }

    /** Get all poll questions count to identify options for every question
     *@return List
     */
    public List<Map<String, String>> getQuestionsCount() {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String countQuery = "select pollid ,count(1) pollsCount from APP.POLLOPTION group by pollid";
        try {
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(countQuery);
            Map<String, String> pollMap;
            while (rs.next()) {
                pollMap = new HashMap<String, String>();
                pollMap.put(rs.getString("pollid"), rs.getString("pollsCount"));

                list.add(pollMap);
            }
        } catch (Exception e) {
            new CustomException("unable to connect to database");
        }
        return list;
    }

    /** Insert status when click ststus(Active/InActive)  button  
     *@param String status
     * @param int pollId
     */
    public void insertStatus(String status, int pollid) {

        try {
            String statusQuery = "update app.POLLS set status=? where pollid=?";

            PreparedStatement ps1 = conn.prepareStatement(statusQuery);

            ps1.setString(1, status);

            ps1.setInt(2, pollid);
            // ps1.setString(2, pollid+"");

            int succes = ps1.executeUpdate();
            if (succes != 0) {
                System.out.println("successfully updated in POLLS status column=" + succes);
            } else {
                System.out.println("not updated in polls status column");
            }
        } catch (SQLException ex) {
            new CustomException("unable to connect to database");
        }
    }
}
