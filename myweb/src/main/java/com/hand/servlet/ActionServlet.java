package com.hand.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hand.dao.Impl.UserDaoImpl;

public class ActionServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		if ("login".equals(action)) {
			UserDaoImpl ui = new UserDaoImpl();
			String name = request.getParameter("userName");
			String password = request.getParameter("password");
//			System.out.println(name+":"+password);
			
			if (ui.findUser(name, password)) {
				System.out.println(ui.findUser(name, password));
				request.getRequestDispatcher("main.jsp").forward(request, response);
			} else {
			
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		if ("download".equals(action)) {
			URL url = null;
			HttpURLConnection con = null;
			InputStream in = null;
			FileOutputStream out = null;
			String result = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000"
					+ "&sec=1489138138891&di=60ab14eb7a88efa1f888e95ebc7991a8&imgtype=0&"
					+ "src=http%3A%2F%2Fdata.bbs.18183.com%2Fattachment%2Fforum%2F201411"
					+ "%2F12%2F173640rt8rqsrhqjicemts.jpg";
			url = new URL(result);
			// 建立http连接，得到连接对象
			con = (HttpURLConnection) url.openConnection();
			in = con.getInputStream();

			BufferedInputStream bis = new BufferedInputStream(in);

			FileOutputStream fos = new FileOutputStream("yuzhibo1.jpg");
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			byte[] b = new byte[1000];
			int line = 0;
			while ((line = bis.read(b)) != -1) {
				bos.write(b, 0, line);
			}
			bos.close();
			fos.close();
			bis.close();
			in.close();
			if (bos != null) {
				request.setAttribute("success", "下载成功");
				request.getRequestDispatcher("success.jsp").forward(request, response);
			}
		}
	}
}
