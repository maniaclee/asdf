package psyco.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.WebApplicationInitializer;
import psyco.entity.Subject;
import psyco.repository.SubjectRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.List;

public class WebAppInitializer implements WebApplicationInitializer {
    public static String Const_Subjects = "subjects";
    @Autowired
    SubjectRepository subR;

    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setAttribute(Const_Subjects, loadAllSubjects());

    }

    List<Subject> loadAllSubjects() {
        List<Subject> re = subR.findByLevelOrderById(1);
        re.stream().forEach(e -> e.setChildren(subR.findByParent(e.getId())));
        return re;
    }
} 