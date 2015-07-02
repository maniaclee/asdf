package psyco.web.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import psyco.entity.City;
import psyco.entity.Subject;
import psyco.entity.SubjectGroup;
import psyco.repository.CityRepository;
import psyco.repository.SubjectGroupRepository;
import psyco.repository.SubjectRepository;
import psyco.repository.TypeTagRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PsycoServletContextInitializer implements ServletContextInitializer {
    public static String Const_Subjects_All = "subjects_all";
    public static String Const_Subjects_Group = "subjects_group";
    public static String Const_Cities = "cities";
    public static String Const_Types = "types";
    @Autowired
    SubjectRepository subR;
    @Autowired
    SubjectGroupRepository sgR;
    @Autowired
    CityRepository cityR;
    @Autowired
    TypeTagRepository typeR;

    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("--------------init context----------------");
        Map<SubjectGroup, List<Subject>> subjectGroup = loadAllSubjectsByGroup();
        servletContext.setAttribute(Const_Cities, getCityAll());
        servletContext.setAttribute(Const_Types, typeR.getRoots());
        servletContext.setAttribute(Const_Subjects_Group, subjectGroup);
        servletContext.setAttribute(Const_Subjects_All, subjectGroup.values().stream().reduce(Lists.newLinkedList(), (resultList, l) -> {
            resultList.addAll(l);
            return resultList;
        }));
    }

    Map<SubjectGroup, List<Subject>> loadAllSubjectsByGroup() {
        return new TreeMap<>(sgR.findAll().stream().collect(Collectors.toMap(sg -> sg, e -> {
            List<Subject> group = subR.findByGroup(e.getNum());
            group.forEach(s -> s.setChildren(subR.findByParent(s.getId())));
            return group;
        })));
    }

    List<City> getCityAll() {
        List<City> re = cityR.getProvinces();
        Lists.reverse(Lists.newArrayList("北京", "上海", "天津", "重庆", "广东")).forEach(name -> {
            City city = re.stream().filter(e -> e.getName().equals(name)).findFirst().get();
            re.remove(city);
            re.add(0, city);
        });
        re.forEach(p -> {
            p.setChildren(cityR.getCities(p.getId()));
            p.getChildren().forEach(c -> c.setChildren(cityR.getDistricts(c.getId())));
        });
        return re;
    }
}