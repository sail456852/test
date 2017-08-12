package com.yuzhen.g_code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CodeServlet
 */
@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int charNum = 4;
		int width = 20 * 4;
		int height = 28;

		// this shit image still in the RAM yo
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// Get drawable target?
		Graphics graphics = bufferedImage.getGraphics();

		// paint the background
		graphics.setColor(Color.BLUE);
		graphics.fillRect(0, 0, width, height);

		// 4. draw the border
		graphics.setColor(Color.GRAY);
		graphics.drawRect(0, 0, width - 1, height - 1);

		// 5. output validation code content
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Courier New", Font.BOLD, 22));

		// 6. randomly pick out 4 characters
		String s = "ABCDEFGHGKLMNPQRSTUVWXYZ23456789";
		Random random = new Random();

		String msg = "";
		int x = 5; // what does 5 means here?
		// x means the x coordinate used to draw image
		for (int i = 0; i < charNum; i++) {
			int index = random.nextInt(32);

			// random alphabet in String format
			String content = String.valueOf(s.charAt(index));

			// concatenate the random 4 chars
			msg += content;
			graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			graphics.drawString(content, x, 22);
			x += 20;
		}

		// drawer the disturbing lines
		graphics.setColor(Color.gray);
		for (int i = 0; i < 5; i++) {
			int x1 = random.nextInt(width);
			int x2 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int y2 = random.nextInt(height);
			graphics.drawLine(x1, y1, x2, y2);
		}

		graphics.dispose();

		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
