package psyco.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

public class CommonUtil {

	public static double format(double b) {
		return Math.round(b * 100) / 100.0;
	}

	public static String toJSON(Object a) {
		try {
			return new ObjectMapper().writeValueAsString(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "null";
	}

	public static <T> T fromJSON(String json, Class<T> t) {
		try {
			return new ObjectMapper().readValue(json, t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String hashFileName(File file) {
		return DigestUtils.md5Hex(System.currentTimeMillis() + "" + file.hashCode()) + getSufix(file.getName());
	}

	static String getSufix(String name) {
		return name.lastIndexOf(".") > 0 ? name.substring(name.lastIndexOf(".")) : "";
	}

	public static String hashFileName(String extra) {
		return DigestUtils.md5Hex(System.currentTimeMillis() + extra);
	}

	public static File saveMultipartFile(MultipartFile f, File dir) {
		File re = new File(dir, hashFileName(f.getOriginalFilename()) + getSufix(f.getOriginalFilename()));
		try {
			IOUtils.write(f.getBytes(), new FileOutputStream(re));
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
