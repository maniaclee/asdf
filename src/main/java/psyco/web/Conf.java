package psyco.web;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import psyco.util.CommonUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

public class Conf {
//	 public static File DIR_Base = new File("D:\\project-data\\data-upload");
//	 public static File DIR_Base = new File("H:\\project-data\\data-upload");
	public static File DIR_Base = new File("/Users/psyco/psyco/project-pro/project-data/data-upload/");
	public static File DIR_ItemImage = new File(DIR_Base, "itemImage");
	public static File DIR_UserPhoto = new File(DIR_Base, "userPhoto");
	public static File DIR_File = new File(DIR_Base, "file");
	public static String DIR_UserPhoto_prefix = DIR_UserPhoto.getParentFile().getName() + "/" + DIR_UserPhoto.getName() + "/";
	public static String DIR_ItemImage_prefix = DIR_ItemImage.getParentFile().getName() + "/" + DIR_ItemImage.getName() + "/";

	public static File getResourceFile(String url) {
		return new File(DIR_Base.getParentFile(), url);
	}

	/* header: data:image/png;base64 */
	public static String saveImageBase64(String base64, File dir) {
		String sep = ";base64";
		String sufix = base64.substring(base64.indexOf('/') + 1, base64.indexOf(';'));
		File re = new File(dir, CommonUtil.hashFileName(base64.hashCode() + "") + "." + sufix);
		byte dearr[] = Base64.decodeBase64(base64.substring(base64.indexOf(sep) + sep.length()));
		try {
			OutputStream stream = new FileOutputStream(re);
			stream.write(dearr);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re.getName();
	}

	@Test
	public void test() throws MalformedURLException {
		System.out.println(Conf.DIR_Base.toURI().toURL().toString());
	}
}
