package sample.ui;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.File;

/**
 * Created by lipeng on 15/6/14.
 */
public class Testsdfsdf {


    @Test
    public void formatJSPs() throws Exception {
        File dir = new File("/Users/psyco/workspace/project29-pro/src/main/resources/templates/");
//        dir.listFiles().stream().forEach(e-> e.getName());
        Lists.newArrayList(dir.listFiles(e -> e.getName().endsWith(".jsp"))).forEach(e -> {
            System.out.println(e.getName());
            e.renameTo(new File(e.getParentFile() , e.getName().replace(".jsp",".html")));
//            try {
//                Stream<String> re = Files.lines(Paths.get(e.getAbsolutePath())).filter(l -> l.trim().matches("^(<link)|(<script)"));
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }

//            try {
//                StringBuilder sb = new StringBuilder(1024);
//                Files.readAllLines(Paths.get(e.getAbsolutePath()), StandardCharsets.UTF_8).forEach(line -> {
//                    if(line.trim().matches("^(<link|<script).*")){
//                        System.out.println(line);
//                    }
//                });
//                String s = sb.toString();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }


        });
    }
}
