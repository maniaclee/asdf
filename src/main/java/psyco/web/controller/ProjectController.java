package psyco.web.controller;

import com.google.code.kaptcha.Producer;
import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import psyco.entity.*;
import psyco.repository.*;
import psyco.util.CommonUtil;
import psyco.web.Conf;
import psyco.web.Message;
import psyco.web.UserService;
import psyco.web.config.KaptchaConfig;
import psyco.web.config.RememberConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller(value = "/")
public class ProjectController {
    static Logger logger = Logger.getLogger(ProjectController.class);
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
    RememberConfig rememberMeConfig;
    Producer kaptcha = KaptchaConfig.kaptcha();
    static ObjectMapper om = new ObjectMapper();

    static String toJson(Object o) {
        try {
            return om.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleCustomException(Exception ex) {
        ex.printStackTrace();
        return error("非法请求");

    }



    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public ModelAndView loginSubmit(HttpServletRequest request, @RequestParam("password") String password, @RequestParam("email") String email) {
        User u = new User();
        u.setEmail(email);
        u.setPassword(password);
        ModelAndView re = new ModelAndView();
        try {
            userService.performLogin(u, request);
            return new ModelAndView("redirect:/index");
        } catch (Exception e) {
            e.printStackTrace();
            u.setPassword(e.getMessage());
            re.addObject("error", CommonUtil.toJSON(u));
            // re.addObject("error", CommonUtil.toJSON(new String[] { e.getMessage() }));
        }
        re.setViewName("/login");
        return re;
    }



    @RequestMapping(value = "/registSubmit", method = RequestMethod.POST)
    public ModelAndView registSubmit(User user, HttpServletRequest request) {
        if (user == null)
            return error("无效用户注册");
        ModelAndView re = new ModelAndView("regist");
        // if (bindingResult.hasFieldErrors()) {
        // System.out.println(bindingResult.getFieldErrors());
        // return re;
        // }
        user.setEmail(user.getEmail().trim());
        // if (!KaptchaConfig.validate(request, code)) {
        // logger.debug("registration failed :" + "invalid kaptcha code.");
        // return "redirect:/regist";
        // }
        try {
            userService.registAndLogin(user, request);
            return new ModelAndView("index");
        } catch (Exception e) {
            logger.debug(e.getMessage());
            user.setPassword(e.getMessage());
            re.addObject("error", CommonUtil.toJSON(user));
            return re;
        }
    }



    @RequestMapping(value = "/userUpgrade", method = RequestMethod.GET)
    public
    @ResponseBody
    Message userUpgrade(HttpServletRequest request) {
        return new Message(true, null, "留给后台    会员升级+支付      的接口");
    }

    ModelAndView error(String message) {
        ModelAndView re = new ModelAndView("error");
        re.addObject("error", message);
        return re;
    }

    // @RequestMapping(value = "/antique/{id}", method = RequestMethod.GET)
    // public ModelAndView item(HttpServletRequest request, @PathVariable long id) {
    // ModelAndView re = new ModelAndView("item");
    // re.addObject("item", CommonUtil.toJSON(repAntique.findOne(id)));
    // return re;
    // }


    /**
     * {favor}: 'favor':do favor;'unfavor': do unfavor
     */
    @RequestMapping(value = "/favor/{favor}/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Message favor(HttpServletRequest request, @PathVariable("favor") String favor, @PathVariable("id") long id) {
        User user = UserService.getUser(request);
        if (user == null)
            return new Message(false, "session", null);
        if ("favor".equals(favor)) {
            Favor item = new Favor();
            item.setAntique(repAntique.findOne(id));
            item.setUser(user);
            favorR.saveAndFlush(item);
        } else if ("unfavor".equals(favor)) {
            Favor re = favorR.findByAntiqueAndUser(id, user.getId());
            if (re == null)
                return new Message(false, "error", null);
            favorR.delete(re);
        }
        return new Message(true, null, null);
    }

    @RequestMapping(value = "/upload/profile", method = RequestMethod.POST)
    public String uploadProfile(HttpServletRequest request, @Param("name") String name, @Param("phone") String tel, @Param("district") Integer district, @Param("sex") int sex) {
        User user = UserService.getUser(request);
        user.setName(name);
        user.setSex(sex);
        if (district != null)
            user.setCity(cityR.findOne(district));
        user = repUser.saveAndFlush(user);
        request.getSession().setAttribute(UserService.__SessionUser_Identifier, user);
        return "redirect:/my";
    }

    // @RequestMapping(value = "/upload/profile", method = RequestMethod.POST)
    // public ModelAndView uploadProfileOld(@RequestBody User user, HttpServletRequest request) {
    // // if (!user.getImageUrl().startsWith(Conf.DIR_UserPhoto_prefix)) {
    // // String imgUrl = Conf.DIR_UserPhoto_prefix + Conf.saveImageBase64(user.getImageUrl(), Conf.DIR_UserPhoto);
    // // user.setImageUrl(imgUrl);
    // // }
    // repUser.saveAndFlush(user);
    // request.getSession().setAttribute(UserService.__SessionUser_Identifier, user);
    // return new ModelAndView("user");
    // }

    @RequestMapping(value = "/upload/profile/image", method = RequestMethod.POST)
    public ModelAndView uploadProfileImage(@Param("imageUrl") String imageUrl, HttpServletRequest request) {
        User user = UserService.getUser(request);
        String imgUrl = Conf.DIR_UserPhoto_prefix + Conf.saveImageBase64(imageUrl, Conf.DIR_UserPhoto);
        user.setImageUrl(imgUrl);
        System.err.println(user.getImageUrl());
        repUser.saveAndFlush(user);
        request.getSession().setAttribute(UserService.__SessionUser_Identifier, user);
        return new ModelAndView("user");
    }

    @RequestMapping(value = "/upload/item", method = RequestMethod.POST)
    public Object uploadItem(HttpServletRequest request, @Param("title") String title, @Param("price") double price, @Param("age") Integer age, @Param("type") Integer type, @Param("imageUrl") String imageUrl, @Param("breifInfo") String breifInfo, @Param("district") Integer district) {
        Antique item = new Antique();
        item.setBreifInfo(breifInfo);
        item.setPrice(price);
        item.setTitle(title);
        if (age != null)
            item.setAge(ageR.findOne(age));
        if (district != null)
            item.setLocation(cityR.findOne(district));
        if (type != null)
            item.setType(typeR.findOne(type));
        if (imageUrl != null && imageUrl.length() > 0)
            item.setImageUrl(Conf.DIR_ItemImage_prefix + Conf.saveImageBase64(imageUrl, Conf.DIR_ItemImage));
        item.setUser(UserService.getUser(request));
        DefaultMultipartHttpServletRequest req = (DefaultMultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> map = req.getMultiFileMap();
        LinkedList<String> re = new LinkedList<String>();
        for (String key : map.keySet()) {
            for (MultipartFile f : map.get(key)) {
                System.err.println(f.getOriginalFilename());
                re.add(Conf.DIR_ItemImage_prefix + CommonUtil.saveMultipartFile(f, Conf.DIR_ItemImage).getName());
            }
        }
        item.setDetailPicUrls(CommonUtil.toJSON(re));
        repAntique.save(item);
        return "redirect:/my";
    }

    @RequestMapping(value = "/modify/item", method = RequestMethod.POST)
    public Object modifyItem(HttpServletRequest request, @Param("id") Long id, @Param("title") String title, @Param("price") double price, @Param("age") Integer age, @Param("type") Integer type, @Param("imageUrl") String imageUrl, @Param("breifInfo") String breifInfo,
                             @Param("district") Integer district, @Param("detailPicUrls") String[] detailPicUrls) {
        if (id == null || !repAntique.exists(id))
            return error("该物品不存在！");
        Antique item = repAntique.findOne(id);
        // System.out.println(Arrays.toString(detailPicUrls));
        // System.out.println(Arrays.toString(CommonUtil.fromJSON(item.getDetailPicUrls(), String[].class)));
        item.setBreifInfo(breifInfo);
        item.setPrice(price);
        item.setTitle(title);
        if (age != null)
            item.setAge(ageR.findOne(age));
        if (district != null)
            item.setLocation(cityR.findOne(district));
        if (type != null)
            item.setType(typeR.findOne(type));
        if (imageUrl != null && imageUrl.length() > 200) {
            Conf.getResourceFile(item.getImageUrl()).delete();
            item.setImageUrl(Conf.DIR_ItemImage_prefix + Conf.saveImageBase64(imageUrl, Conf.DIR_ItemImage));
        }

        // item.setUser(UserService.getUser(request));
        DefaultMultipartHttpServletRequest req = (DefaultMultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> map = req.getMultiFileMap();
        LinkedList<String> re = new LinkedList<String>();
        if (detailPicUrls != null)
            for (String s : detailPicUrls)
                re.add(s);
        for (String key : map.keySet()) {
            for (MultipartFile f : map.get(key)) {
                System.out.println(f.getOriginalFilename() + "---------" + f.getSize());
                if (f.getSize() > 0)
                    re.add(Conf.DIR_ItemImage_prefix + CommonUtil.saveMultipartFile(f, Conf.DIR_ItemImage).getName());
            }
        }
        for (String s : CommonUtil.fromJSON(item.getDetailPicUrls(), String[].class)) {
            if (!re.contains(s))
                Conf.getResourceFile(s).delete();
        }
        System.out.println(CommonUtil.toJSON(re));
        item.setDetailPicUrls(CommonUtil.toJSON(re));
        repAntique.saveAndFlush(item);
        return "redirect:/my";
    }

    @RequestMapping(value = "/delete/item/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Message deleteItem(@PathVariable("id") long id, HttpServletRequest request) {
        if (!repAntique.exists(id))
            return new Message(false, null, "该物品不存在！");
        Antique re = repAntique.findOne(id);
        User u = UserService.getUser(request);
        if (u == null || re.getUser().getId() != u.getId())
            return new Message(false, null, "您没有权限！");
        if (re.getStatus() != Antique.STATUS_New)
            return new Message(false, null, "该物品正在操作中，暂时无法删除");
        repAntique.delete(id);
        return new Message();
    }

    @RequestMapping(value = "/exchange/new", method = RequestMethod.POST)
    public
    @ResponseBody
    Message exchange(@Param("userID") long userID, @Param("srcID") long srcID, @Param("desID") long desID, HttpServletRequest request) {
        Exchange re = new Exchange();
        Antique src = repAntique.findOne(srcID);
        Antique des = repAntique.findOne(desID);
        src.setStatus(Antique.STATUS_Proccessing);
        src = repAntique.saveAndFlush(src);
        des.setStatus(Antique.STATUS_Proccessing);
        des = repAntique.saveAndFlush(des);
        re.setUser(repUser.findOne(userID));
        re.setSrc(src);
        re.setTarget(des);
        // re.setType(type);
        // re.setLevel(level);
        exR.saveAndFlush(re);
        return new Message(true, null, "这里是留给后台   交换   的接口");
    }

    @RequestMapping(value = "/distinguish", method = RequestMethod.POST)
    public
    @ResponseBody
    Message distinguish(@Param("userID") long userID, @Param("itemID") long itemID, @Param("type") int type, @Param("level") int level, HttpServletRequest request) {
        Distinguish re = new Distinguish();
        re.setItem(repAntique.findOne(itemID));
        re.setUser(repUser.findOne(userID));
        re.setLevel(level);
        re.setType(type);
        System.out.println(re);
        disR.saveAndFlush(re);
        return new Message(true, null, "这里是留给后台   鉴定付款    的接口");
    }

    @RequestMapping(value = "/distinguish/modify", method = RequestMethod.POST)
    public
    @ResponseBody
    Message distinguishModify(@Param("id") long id, @Param("status") int status, HttpServletRequest request) {
        Distinguish re = disR.findOne(id);
        if (re == null)
            return new Message(false, null, "不存在");
        re.setStatus(status);
        re = disR.saveAndFlush(re);
        return new Message(true, null, re);
    }

    @RequestMapping(value = "/exchange/modify", method = RequestMethod.POST)
    public
    @ResponseBody
    Message exchangeModify(@Param("id") long id, @Param("status") int status, HttpServletRequest request) {
        Exchange re = exR.findOne(id);
        if (re == null)
            return new Message(false, null, "不存在");
        re.setStatus(status);
        re = exR.saveAndFlush(re);
        return new Message(true, null, re);
    }

    @RequestMapping("/captcha-image")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return KaptchaConfig.handleRequest(request, response, kaptcha);
    }

    @RequestMapping(value = "/site/file-upload", method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> imageUpload(HttpServletRequest request, MultipartFile file) {
        DefaultMultipartHttpServletRequest req = (DefaultMultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> map = req.getMultiFileMap();
        LinkedList<String> re = new LinkedList<String>();
        for (String key : map.keySet()) {
            for (MultipartFile f : map.get(key)) {
                re.add(Conf.DIR_ItemImage_prefix + CommonUtil.saveMultipartFile(f, Conf.DIR_ItemImage).getName());
            }
        }
        return re;
    }

    @RequestMapping("/pdf/{pdf:.+}")
    public void pdf(HttpServletRequest request, HttpServletResponse response, @PathVariable String pdf) throws Exception {
        byte[] bs = Files.toByteArray(new File(Conf.DIR_File, pdf));
        response.reset();
        response.setBufferSize(1024);
        response.setContentType("application/pdf"); //or whatever file type you want to send.
        try {
            response.getOutputStream().write(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<Age> AGES;

    List<Age> __ages() {
        if (AGES == null)
            AGES = ageR.findAll();
        return AGES;
    }

    List<City> getCityALL() {
        List<City> re = cityR.getProvinces();
        String[] hots = new String[]{"北京", "上海", "天津", "重庆", "广东"};
        for (int i = hots.length - 1; i >= 0; i--)
            for (City c : re)
                if (c.getName().equals(hots[i])) {
                    re.remove(c);
                    re.add(0, c);
                    break;
                }
        for (City p : re) {
            p.setChildren(cityR.getCities(p.getId()));
            for (City c : p.getChildren())
                c.setChildren(cityR.getDistricts(c.getId()));
        }
        return re;
    }

    Map<String, Object> _loadApplicationData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("AGE_ALL", ageR.findOrderByAgeAsc());
        map.put("TYPETAG_ALL", typeR.getRoots());
        map.put("CITY_ALL", getCityALL());
        return map;
    }
}
