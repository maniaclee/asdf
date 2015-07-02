package psyco.web.config;

import java.awt.image.BufferedImage;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

public class KaptchaConfig {
	public static Producer kaptcha() {
		DefaultKaptcha re = new DefaultKaptcha();
		Properties p = new Properties();
		p.setProperty("kaptcha.border", "no");
		p.setProperty("kaptcha.border.color", "105,179,90");
		p.setProperty("kaptcha.textproducer.font.color", "red");
		p.setProperty("kaptcha.textproducer.font.size", "90");
		p.setProperty("kaptcha.image.width", "300");
		p.setProperty("kaptcha.image.height", "100");
		p.setProperty("kaptcha.session.key", "code");
		p.setProperty("kaptcha.textproducer.char.string", "0123456789");
		p.setProperty("kaptcha.textproducer.char.length", "4");
		re.setConfig(new Config(p));
		return re;
	}

	public static ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, Producer captchaProducer) throws Exception {
		// Set to expire far in the past.
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		// return a jpeg
		response.setContentType("image/jpeg");

		// create the text for the image
		String capText = captchaProducer.createText();

		// store the text in the session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		// create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);

		ServletOutputStream out = response.getOutputStream();

		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}

	public static boolean validate(HttpServletRequest request, String code) {
		String captchaId = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		return code != null && captchaId != null && code.trim().equalsIgnoreCase(captchaId);
	}
}
