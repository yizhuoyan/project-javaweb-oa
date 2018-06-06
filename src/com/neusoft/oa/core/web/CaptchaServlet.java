package com.neusoft.oa.core.web;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.noise.NoiseProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.servlet.SimpleCaptchaServlet;
import nl.captcha.text.renderer.ColoredEdgesWordRenderer;
@WebServlet("/captcha.do")
public class CaptchaServlet extends SimpleCaptchaServlet {
	private static final long serialVersionUID = 1L;
	final private static int width = 200;
	final private static int height = 50;
	private static final Random RANDOM=new Random();
	private static final java.util.List<Color> COLORS=new ArrayList<>(8);
	private static final java.util.List<Font> FONTS=new ArrayList<>(7);
	private static final String CAPTCHA_SESSION_KEY = "CURRENT-CAPTCHA";
	static {
		COLORS.add(Color.BLACK);
		COLORS.add(Color.BLUE);
		COLORS.add(Color.YELLOW);
		COLORS.add(Color.CYAN);
		COLORS.add(Color.DARK_GRAY);
		COLORS.add(Color.GRAY);
		COLORS.add(Color.GREEN);
		COLORS.add(Color.ORANGE);
		FONTS.add(new Font("Geneva", 2, 48));
		FONTS.add(new Font("Courier", 1, 48));
		FONTS.add(new Font("Sans-serif", 1, 48));
		FONTS.add(new Font("SimSun", 1, 48));
		FONTS.add(new Font("Microsoft YaHei", 1, 48));
		FONTS.add(new Font("Times New Roman", 1, 48));
		FONTS.add(new Font("Arial", 1, 48));
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ColoredEdgesWordRenderer wordRenderer = new ColoredEdgesWordRenderer(COLORS, FONTS);
		int w=parseInt(req.getParameter("w"),width);
		int h=parseInt(req.getParameter("h"),height);
		
		Captcha captcha = (new nl.captcha.Captcha.Builder(w, h)).addText(wordRenderer)
				.gimp().addNoise().addNoise()
				.addBackground(new GradiatedBackgroundProducer(randomColor(),randomColor())).build();
		req.getSession().setAttribute(CAPTCHA_SESSION_KEY, captcha.getAnswer());
		//System.out.println(captcha.getAnswer());
		CaptchaServletUtil.writeImage(resp, captcha.getImage());
	}
	private static Color randomColor() {
		return COLORS.get(RANDOM.nextInt(COLORS.size()));
	}
	private static int parseInt(String s,int defaultInt) {
		if(s==null)return defaultInt;
		if((s=s.trim()).length()==0)return defaultInt;
		try {
			return Integer.parseInt(s,10);
		}catch(Exception e) {
			return defaultInt;
		}
	}

	
	
}
