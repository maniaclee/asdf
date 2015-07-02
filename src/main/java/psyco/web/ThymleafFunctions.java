package psyco.web;

import com.google.common.collect.Lists;
import psyco.entity.City;
import psyco.util.CommonUtil;

import javax.inject.Named;
import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lipeng on 15/6/19.
 */

/*if value ==null, default is lowercase first char:ThymleafFunctions-->thymleafFunctions */
@Named(value = "fn")
@Singleton
public class ThymleafFunctions {


    public static String randomID() {
        return System.currentTimeMillis() + "" + Math.random() * 1000;
    }

    public static String randomID(String msg) {
        return msg + System.currentTimeMillis() + "" + Math.random() * 1000;
    }

    public static String formatCity(City city) {
        return city == null ? "未知" : Lists.newArrayList(city.getParent().getParent().getName(), city.getParent().getName(), city.getName()).stream().collect(Collectors.joining(" "));
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static List<String> decodeJsonArray(String s) {
        return CommonUtil.fromJSON(s, List.class);
    }
}
