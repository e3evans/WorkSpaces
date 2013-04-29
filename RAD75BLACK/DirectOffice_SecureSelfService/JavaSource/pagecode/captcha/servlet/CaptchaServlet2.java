package pagecode.captcha.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.SSSConstants;

public class CaptchaServlet2 extends HttpServlet{

	private int height=0;
	private int width=0;
	
	
	  
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		System.setProperty("java.awt.headless", "true");
		height=30;
		width=120;
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse response)
	throws IOException, ServletException
	{
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("Do you see this message?");
		writer.println("</body>");
		writer.println("</html>");
	}
	
	protected void doGet1(HttpServletRequest req, HttpServletResponse response)
		throws IOException, ServletException
	{
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
	    Graphics2D graphics2D = image.createGraphics();
	    
	    String ch = (String)req.getParameter("captchakey");
	    System.out.println("---------------------- CAPTCHA SERVLET ---------------------");
	    System.out.println("key = " + ch);
	    System.out.println("------------------------------------------------------------");
	    Color c = new Color(0.6662f, 0.4569f, 0.3232f);
	    GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.white, true);
	    graphics2D.setPaint(gp);
	    Font font=new Font("Verdana", Font.CENTER_BASELINE , 26);
	    graphics2D.setFont(font);
	    graphics2D.drawString(ch,2,20);
	    graphics2D.dispose();
	    
	    HttpSession session = req.getSession(true);
	    session.setAttribute(SSSConstants.CAPTCHA_KEY,ch);
	    
	    OutputStream outputStream = response.getOutputStream();
	    ImageIO.write(image, "jpeg", outputStream);
	    outputStream.close();
	    
	}
}
