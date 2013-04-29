package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CreateCaptchaImg {

	private int width;
	private int height;
	
	public CreateCaptchaImg(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public Image createImage()
	{
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = image.createGraphics();
		
		Random r = new Random();
		String token = Long.toString(Math.abs(r.nextLong()), 36);
		String ch = token.substring(0,6);
		
		Color c = new Color(0.6662f, 0.4569f, 0.3232f);
		GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.white, true);
		graphics2D.setPaint(gp);
	    Font font=new Font("Verdana", Font.CENTER_BASELINE , 26);
	    graphics2D.setFont(font);
	    graphics2D.drawString(ch,2,20);
	    graphics2D.dispose();
		
	    return image;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	
	
	
}
