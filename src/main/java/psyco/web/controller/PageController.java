package psyco.web.controller;

import com.google.common.base.Strings;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import psyco.entity.Antique;
import psyco.entity.Store;
import psyco.repository.*;
import psyco.util.CommonUtil;
import psyco.web.SearchFormData;
import psyco.web.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lipeng on 15/6/30.
 */
@Controller
public class PageController {
    static Logger logger = Logger.getLogger(PageController.class);
    @Autowired
    UserService userService;
    @Autowired
    UserRepository repUser;
    @Autowired
    AntiqueRepository repAntique;
    @Autowired
    AntiqueService antiqueService;
    @Autowired
    CityRepository cityR;
    @Autowired
    TypeTagRepository typeR;
    @Autowired
    AgeRepository ageR;
    @Autowired
    FavorRepository favorR;
    @Autowired
    DistinguishRepository disR;
    @Autowired
    ExchangeRepository exR;
    @Autowired
    StoreRepository storeR;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView defaultURL(HttpServletRequest request) {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login**", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView re = new ModelAndView("login");
        return re;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error(HttpServletRequest request) {
        ModelAndView re = new ModelAndView("error");
        return re;
    }

    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    public String regist(HttpServletRequest request) {
        return "regist";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(Model model, HttpServletRequest request) {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
    public String aboutUs(Model model, HttpServletRequest request) {
        return "aboutUs";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String news(Model model, HttpServletRequest request) {
        return "news";
    }

    @RequestMapping(value = "/items/all", method = RequestMethod.GET)
    public ModelAndView itemsAll(Model model, HttpServletRequest request) {
        ModelAndView re = new ModelAndView("login");
        re.addObject("fucker", repAntique.findAll());
        return re;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ModelAndView search(@ModelAttribute SearchFormData fd, HttpServletRequest request) {
        if (fd.key == null)
            fd.key = "";
        fd.key = fd.key.trim();
        ModelAndView re = new ModelAndView("search");
        Page<Antique> page = antiqueService.search(fd, UserService.getUser(request), fd.createPageRequest());
        re.addObject("result", page);
        re.addObject("conditions", fd);
        return re;
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ModelAndView my(@RequestParam(value = "op", required = false) String op, HttpServletRequest request) {
        /* admin page */
        if (UserService.getUser(request).getRole().equals("ROLE_ADMIN"))
            return new ModelAndView("admin");
        ModelAndView re = new ModelAndView("user");
        re.addObject("store", storeR.findByUser(UserService.getUser(request).getId()));
        if (Strings.isNullOrEmpty(op))
            return re;
        if ("myFavor".equals(op))
            return re.addObject(op, favorR.findByUserID(UserService.getUser(request).getId()));
        if ("myUpload".equals(op))
            return re.addObject(op, repAntique.findByUser(UserService.getUser(request).getId()));
        if ("upload".equals(op))
            return re;
        return re;
    }

    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public ModelAndView myStore(@RequestParam("id") long id, HttpServletRequest request) {
        ModelAndView re = new ModelAndView("store");
        Store store = storeR.findOne(id);
        if (store == null)
            return error("该商店不存在");
        typeR.findAll().forEach(t -> re.addObject(t.getId()+"", repAntique.findByUserAndType(store.getUser().getId(), t.getId(), new PageRequest(0,8))));
        return re.addObject("store", store);
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model, HttpServletRequest request) {
        return "admin";
    }

    @RequestMapping(value = "/contactUs", method = RequestMethod.GET)
    public String contactUs(Model model, HttpServletRequest request) {
        return "contactUs";
    }

    @RequestMapping(value = "/businessKnowledge", method = RequestMethod.GET)
    public String businessKnowledge(Model model, HttpServletRequest request) {
        return "businessKnowledge";
    }

    @RequestMapping(value = "/gdocs", method = RequestMethod.GET)
    public String gdocs(Model model, HttpServletRequest request) {
        return "gdocs";
    }

    @RequestMapping(value = "/law", method = RequestMethod.GET)
    public String law(Model model, HttpServletRequest request) {
        return "law";
    }

    @RequestMapping(value = "/partner", method = RequestMethod.GET)
    public String partner(Model model, HttpServletRequest request) {
        return "partner";
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public ModelAndView item(HttpServletRequest request, @Param("id") long id) {
        if (!repAntique.exists(id))
            return error("对不起，你访问的物品不存在或已被移除");
        ModelAndView re = new ModelAndView("item");
        re.addObject("item", repAntique.findOne(id));
        return re;
    }

    @RequestMapping(value = "/antiqueModify", method = RequestMethod.GET)
    public ModelAndView itemModify(HttpServletRequest request, @Param("id") long id) {
        if (!repAntique.exists(id))
            return error("对不起，你访问的物品不存在或已被移除");
        ModelAndView re = new ModelAndView("item-modify");
        re.addObject("item", CommonUtil.toJSON(repAntique.findOne(id)));
        return re;
    }

    ModelAndView error(String message) {
        ModelAndView re = new ModelAndView("error");
        re.addObject("error", message);
        return re;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleCustomException(Exception ex) {
        ex.printStackTrace();
        return error("非法请求");

    }

}
