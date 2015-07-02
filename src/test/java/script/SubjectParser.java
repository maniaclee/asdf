package script;

import com.google.common.collect.Lists;
import org.junit.Test;
import psyco.entity.Subject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lipeng on 15/6/24.
 */
public class SubjectParser {

    public static List<Subject> fromFile(String s) throws IOException {
        List<Subject> re = Lists.newLinkedList();
        Pattern pattern = Pattern.compile("(\\d+)\\.?(\\d+)?\\s+(\\S+)");
        Files.readAllLines(Paths.get(s)).stream().forEach(e -> {
            if (e.matches("\\s*"))
                return;
            Matcher m = pattern.matcher(e);
            if (!m.find())
                return;
            int code = Integer.parseInt(m.group(1));
            int code2 = m.group(2) == null ? -1 : Integer.parseInt(m.group(2));
            int code3 = -1;
            if (code2 > 100) {
                code3 = code2 % 100;
                code2 = code2 / 100;
            }
            int parent = code3 > 0 ? code2 : (code2 > 0 ? code : -1);
            Subject sub = new Subject(code3 > 0 ? code3 : (code2 > 0 ? code2 : code), code3 > 0 ? 3 : (code2 > 0 ? 2 : 1), m.group(3));
            if (parent > 0) {
                Optional<Subject> op = re.stream().filter(e1 -> e1.getCode() == parent && e1.getLevel() == sub.getLevel() - 1).findFirst();
                if (op.isPresent())
                    sub.setParent(op.get());
            }
            re.add(sub);
        });
       /* re.sort((e1, e2) -> {
            if(e1.getLevel()-e2.getLevel() !=0)
                return e1.getLevel()-e2.getLevel();
            return e1.getCode()-e2.getCode();
        });*/
//        re.stream().forEach(ll -> System.out.println(ll));
        return re;
    }


    @Test
    public void ssdfsf() throws IOException {
        String s = "/Users/psyco/workspace/project29-pro/src/test/java/script/xueke.txt";
        fromFile(s);
    }
}
