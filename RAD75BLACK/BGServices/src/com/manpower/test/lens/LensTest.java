package com.manpower.test.lens;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.bgt.lens.LensException;
import com.bgt.lens.LensMessage;
import com.bgt.lens.LensSession;
import com.bgt.lens.ServerBusyException;

public class LensTest{
	
	public static void main(String[] args) {
		LensSession session = null;
		try {
			session = LensConnection.getSession("GSD1W064S.CORP.ROOT.GLOBAL", 2015);
			StringBuffer input = new StringBuffer();
			input.append("<?xml version='1.0' encoding='utf-8'?><bgtcmd><search type='resume' vendor='TWCampus' count='500' min='0'><JobDoc>");
			input.append("\n" + "<posting>");		
			input.append("\n" + "<duties>");
			input.append("\n" + "<title>åœ‹å…§æ¥­å‹™</title>");
			input.append("\n" + "å…¬å�¸æ��ä¾›å®Œå°ˆæ¥­çš„å–„æ•™è‚²è¨“ç·´ï¼Œä¸�éœ€å°ˆæ¥­è­‰ç…§ï¼Œç„¡ç¶“é©—å�¯");
			input.append("\n" + "æ��ä¾›å¤šç¨®å…·ç«¶çˆ­æ€§æˆ¿è²¸ç�?¢å“�ï¼Œè¦ªè‡ªç‚ºå®¢æˆ¶é‡�èº«è¦�åŠƒï¼Œæ»¿è¶³ä¸�å�Œå®¢æˆ¶çš„éœ€æ±‚");
			input.append("\n" + "é«˜æ¥­ç¸¾ç�Žé‡‘åˆ¶åº¦");
			input.append("\n" + "æŠ•è³‡åž‹/æŠµåˆ©åž‹æˆ¿è²¸æ¥­å‹™æŽ¨å»£ã€‚");
			input.append("\n" + "å…¬å�¸æ��ä¾›å„ªè³ªå…·æ½›åŠ›çš„å®¢æˆ¶å��å–®ï¼Œä¸�éœ€å�šé™Œç�?Ÿé–‹ç™¼ã€‚");
			input.append("\n" + "é‡�å°�è‡ªå®¶æ—¢æœ‰å®¢æˆ¶é€�é�Žé›»è©±å�šæ¥­å‹™æŽ¨å»£èˆ‡ä»‹ç´¹ï¼Œé€²è€Œé‚€ç´„æ‹œè¨ªã€‚");
			input.append("\n" + "é€�é�Žé›»è…¦è©¦ç®—ï¼Œå¹«å®¢æˆ¶é€�æ˜Žçœ�æ�¯ï¼Œè�°æ˜Žç�†è²¡ã€‚");
			input.append("\n" + "æœ¬è�·å‹™æœ‰å®Œæ•´æ•™è‚²è¨“ç·´8å¤©èˆ‡åœ¨è�·è¨“ç·´å…©å€‹æœˆã€‚");
			input.append("\n" + "å¹³å�‡ä¸‰å€‹æœˆä»¥ä¸Šç¸½æ�?¶å…¥å�¯é ˜5è�¬å…ƒï¼›æ›´è³‡æ·±å�Œä»�ç´„é ˜7~8è�¬ã€‚");
			input.append("\n" + "</duties>");
			input.append("\n" + "<qualifications>");
			input.append("\n" + "<required>.æ“…æº�é€šï¼Œèƒ½æ‰¿å�—æ¥­ç¸¾å£“åŠ›ï¼Œé…�å�ˆåº¦é«˜");
			input.append("\n" + "<skill>.æ“…æº�é€šï¼Œèƒ½æ‰¿å�—æ¥­ç¸¾å£“åŠ›ï¼Œé…�å�ˆåº¦é«˜ </skill>");
			input.append("\n" + "</required>");
			input.append("\n" + "</qualifications>");
			input.append("\n" + "</posting>");
			input.append("\n" + "</JobDoc><keyword>applicantresponse[]</keyword><include var='id'/><include var='title'/><include var='lens'/><include var='name'/><include var='jobs'/></search></bgtcmd>");
			writeOutput(input.toString(), "c:/lensTest/ChinaInput.txt");
			LensMessage inMessage = LensMessage.create(input.toString(), LensMessage.XML_TYPE);
			writeOutput(inMessage.getMessageData(), "c:/lensTest/ChinaOutput.txt");
			session.close();
			
			session = LensConnection.getSession("usd1w070s.CORP.ROOT.GLOBAL", 2010);
			StringBuffer inputEnglish = new StringBuffer();
			inputEnglish.append("<?xml version='1.0' encoding='iso-8859-1'?><bgtcmd><search type='resume' vendor='USCampus' count='500' min='0'><JobDoc>");
			inputEnglish.append("\n" + "<posting>");		
			inputEnglish.append("\n" + "<duties>");
			inputEnglish.append("\n" + "<title>Stoffel Job Test</title>");
			inputEnglish.append("\n" + "This is a test job position to test the JLens.jar file");
			inputEnglish.append("\n" + "</duties>");
			inputEnglish.append("\n" + "<qualifications>");
			inputEnglish.append("\n" + "<required>Good Java Developer");
			inputEnglish.append("\n" + "<skill>J2EE, Oracle SQL</skill>");
			inputEnglish.append("\n" + "</required>");
			inputEnglish.append("\n" + "</qualifications>");
			inputEnglish.append("\n" + "</posting>");
			inputEnglish.append("\n" + "</JobDoc><keyword>applicantresponse[]</keyword><include var='id'/><include var='title'/><include var='lens'/><include var='name'/><include var='jobs'/></search></bgtcmd>");
			writeOutput(inputEnglish.toString(), "c:/lensTest/EnglishInput.txt");
			
			LensMessage englishMessage = LensMessage.create(inputEnglish.toString(), LensMessage.XML_TYPE);
			LensMessage returnMessage = session.sendMessage(englishMessage);
			writeOutput(returnMessage.toString(),"c:/lensTest/rmess.xml");
			//writeOutput(englishMessage.getMessageData(), "c:/lensTest/EnglishOutput.txt");
			session.close();
		} catch (LensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServerBusyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	}

	static void writeOutput(String str, String file) {

	    try {
		FileOutputStream fos = new FileOutputStream(file);
		Writer out = new OutputStreamWriter(fos, "UTF8");
		out.write(str);
		out.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
}
