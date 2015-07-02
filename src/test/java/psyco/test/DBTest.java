package psyco.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import psyco.entity.*;
import psyco.repository.*;
import psyco.util.CommonUtil;
import psyco.util.FastJsonSimplePropertyExcludeFilter;
import psyco.web.SearchFormData;
import script.SubjectParser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
public class DBTest {


    @Autowired
    CityRepository cityR;
    @Autowired
    UserRepository userR;
    @Autowired
    AntiqueRepository antiqR;
    @Autowired
    AgeRepository ageR;
    @Autowired
    TypeTagRepository typeR;
    @Autowired
    FavorRepository colR;
    @Autowired
    ExchangeRepository exR;
    @Autowired
    DistinguishRepository disR;
    @Autowired
    AntiqueService anS;
    @Autowired
    NewsDao newsR;
    @Autowired
    FavorRepository favorR;
    @Autowired
    SubjectRepository subR;
    @Autowired
    SubjectGroupRepository sgR;
    @Autowired
    StoreRepository storeR;

    @PersistenceContext
    private EntityManager em;

    public void saveUser() {
        User u = new User();
        u.setName("root");
        u.setPassword("$2a$11$9Z5VN8mgPq/CD/KiE9Ep2e9uSY1LpGS1ghUbkEJVkroUWZA34YNuW");
        userR.save(u);
    }

    public void chineseWords() throws Exception {
        List<String> ls = IOUtils.readLines(new FileReader(new File("/Users/penli3/workspace/project/data/chineseWords.txt")));
        List<String> re = new ArrayList<String>();
        int len = 12;
        for (String s : ls) {
            for (int i = 0; i < s.length() - len; i += len) {
                String line = s.substring(i, i + len).trim().replaceAll("\\s+", "");
                re.add(line);
            }
        }
        for (Antique an : antiqR.findAll()) {
            an.setTitle(re.get((int) (Math.random() * re.size())));
            antiqR.saveAndFlush(an);
        }
        print(antiqR.findAll());
    }

    public void user() {
        User u = userR.findByName("root");
        u.setCity(cityR.findOne(110));
        userR.saveAndFlush(u);
    }


    public void subjectGroup() {
//        sgR.findAll().forEach(e->{
//            System.out.println("-----------"+e.getName());
//            List<Subject> group =subR.findByGroup(e.getNum());
//            group.forEach(s->s.setChildren(subR.findByParent(s.getId())));
//            System.out.println(group.stream().map(ss -> ss.getName()).collect(Collectors.toList()));
//        });
        toMap().forEach((k, v) -> {
            System.out.println("---------" + k);
            System.out.println(v.stream().map(Subject::getName).collect(Collectors.toList()));
        });
    }

    @Test
    public void skdjfklsjdflksdf(){
        System.out.println(antiqR.findOne(2051L));;
    }
    public void store3(){
        Store s = storeR.findAll().get(0);
        print(antiqR.findByUserAndType(s.getUser().getId(),1));
    }
    public void store2() {
        List<TypeTag> ts = typeR.findAll();
        antiqR.findAll().forEach(a -> {
            a.setType(ts.get((int) (Math.random() * ts.size())));
            antiqR.save(a);
        });
    }

    public void store() {
        Store store = new Store();
        store.setDescription("sdjfklsjdklfsldfs");
        store.setUser(userR.findOne(15L));
        storeR.saveAndFlush(store);
        print(storeR.findAll());
    }

    Map<SubjectGroup, List<Subject>> toMap() {
        return new TreeMap<>(sgR.findAll().stream().collect(Collectors.toMap(sg -> sg, e -> {
            List<Subject> group = subR.findByGroup(e.getNum());
            group.forEach(s -> s.setChildren(subR.findByParent(s.getId())));
            return group;
        })));
    }

    public void testSubjects() {
        List<Subject> re = loadAllSubjects();
        System.out.println(JSON.toJSONString(re, new FastJsonSimplePropertyExcludeFilter(Subject.class, "parent")));
//		System.out.println(JSON.toJSONString(re ));
    }

    List<Subject> loadAllSubjects() {
        List<Subject> re = subR.findByLevelOrderById(1);
        re.stream().forEach(e -> e.setChildren(subR.findByParent(e.getId())));
        return re;
    }

    public void initSubjects() throws IOException {
        String s = "/Users/psyco/workspace/project29-pro/src/test/java/script/xueke.txt";
        SubjectParser.fromFile(s).forEach(e -> subR.saveAndFlush(e));
        System.out.println("---------------------");
        print(subR.findAll());
    }

    public void sjdflsldkfsdf() {
//		print(antiqR.itemsOrderByDateupload(new PageRequest(0, 8)));
//		System.out.println(CommonUtil.toJSON(userR.findAll().get(0)));
        print(cityR.findAll());
        print(antiqR.findAll());
        print(favorR.findAll());
    }

    public void findByObject() {
        print(antiqR.searchByType(60, null));
    }


    public void recursiveTable() {
        // print(typeR.findAll());
        // System.out.println(typeR.findAll().get(0).getChildren());
        // System.out.println(cityR.getChina());
        // print(cityR.getProvinces());
        // print(cityR.getCities(10));
        // print(cityR.getDistricts(139));
        print(typeR.getRoots().get(0).getChildren());
        // print(typeR.findAll());
    }

    public void pagination() throws JsonGenerationException, JsonMappingException, IOException {
        // System.out.println(new ObjectMapper().writeValueAsString(antiqR.findAll().get(0)));
        // print(antiqR.search("耳朵"));
        // Page<Antique> re = antiqR.search("耳朵", 1);
        // Page<Antique> re = antiqR.findByTitleContaining("耳朵", new PageRequest(1, 30));
        // print(antiqR.findByTitleContaining("耳朵", new PageRequest(0, 30, new Sort(Sort.Direction.DESC, "id"))).getContent());
        print(antiqR.search("耳朵", 0, 9000, new PageRequest(0, 30, new Sort(Sort.Direction.DESC, "price"))).getContent());
        // print(antiqR.findByTitleContaining("耳朵"));
    }

    public void alterItems() {
        List<Age> ages = ageR.findAll();
        List<Antique> ans = antiqR.findAll();
        for (Antique an : ans) {
            an.setAge(ages.get((int) (Math.random() * ages.size())));
            antiqR.saveAndFlush(an);
            System.out.println(an);
        }
    }

    public void age() {
        int conut = 5;
        String[] items = new String[]{"夏", "商", "西周", "春秋战国", "秦", "西汉", "东汉", "三国", "晋", "南北朝", "隋", "唐", "五代十国", "宋", "辽", "西夏", "金", "元", "明", "清", "民国", "近现代"};
        for (String i : items) {
            Age a = new Age();
            a.setName(i);
            a.setAge(conut++);
            ageR.save(a);
            System.out.println(a);
        }
    }

    public void exchange() {
        // Exchange re = new Exchange();
        // List<Antique> as = antiqR.findAll();
        // re.setSrc(as.get(0));
        // re.setTarget(as.get(1));
        // re.setUser(userR.findAll().get(0));
        // re = exR.saveAndFlush(re);
        Exchange re = exR.findAll().get(0);
        re.setStatus(3);
        re = exR.saveAndFlush(re);
        System.out.println(re);
    }

    public void columnSQL() {
        for (Object[] l : antiqR.test(5)) {
            System.out.println(Arrays.toString(l));
        }
    }

    public void entityManager() {
        TypedQuery<Antique> query = em.createQuery("select a from Antique a where a.id > ?1", Antique.class);
        query.setParameter(1, 120l);
        List<Antique> re = query.getResultList();
        print(re);
    }

    public void news() {
        print(newsR.findAll(new PageRequest(0, 4)));
    }

    public void searchAdvance() {
        SearchFormData fd = new SearchFormData();
        fd.setKey("fff");
        fd.setOnlyShowExchange(true);
        // print(anS.search(fd, null, fd.createPageRequest()));
        // print(anS.search(fd, userR.findAll().get(3), fd.createPageRequest()));
        print(antiqR.getMyItemsExchangable(15));
    }

    public void distinguish() {
        print(disR.findByStatusLessThan(2));
    }

    public void level() {
        print(antiqR.findByUserLevel(3));
    }

    public void useThumb() {
        String[] thums = new String[]{"data-upload/itemImage/55bd9482aef92a0f2aabb231127d9233.png", "data-upload/itemImage/ea595808c0e39b1d785071b60d49bed6.png", "data-upload/itemImage/ac9a8948057127a0f4e5737ac8d85c10.png", "data-upload/itemImage/3fb7eeb9cb853b2946a026793dec2d43.png",
                "data-upload/itemImage/14cb5b2ce4a43d53f0a04eec8d5e93f4.png", "data-upload/itemImage/eadd19e6b3e12fa899568519e869200f.png", "data-upload/itemImage/d0ceeeff31655cd3a5db44e84cc42565.png", "data-upload/itemImage/a4a5d3b7a08568722154e75f812ed39b.png",
                "data-upload/itemImage/f50556d78db3250d6fdbf8e5cc99d066.png", "data-upload/itemImage/21214d55da2b0e208db4160badc68fb3.png"};
        String[] titles = new String[]{"sdfadsf", "sjldjflas", "sdjfklajslkdfj", "fffffffsssssss", "llllloddffdsss", "uoiwuiouo"};
        List<User> us = userR.findAll();
        int i = 0;
        for (Antique an : antiqR.findAll()) {
            // an.setImageUrl(thums[(int) (Math.random() * thums.length)]);
            // an.setTitle(titles[(int) (Math.random() * titles.length)]);
            // an.setType(ts.get(i++ % ts.size()));
            an.setUser(us.get(i++ % us.size()));
            antiqR.saveAndFlush(an);
        }
    }


    public void sdflksfd() {
        List<City> re = cityR.getProvinces();
        String[] hots = new String[]{"北京", "上海", "天津", "重庆", "广东"};
        for (int i = hots.length - 1; i >= 0; i--)
            for (City c : re)
                if (c.getName().equals(hots[i])) {
                    re.remove(c);
                    re.add(0, c);
                    break;
                }
        print(re);
//		print(ageR.findOrderByAgeAsc());
//		print(antiqR.itemsOrderByDateupload(new PageRequest(0, 8)));
//		print(antiqR.itemsOrderByDateupload(new PageRequest(1, 8)));
//		System.out.println(antiqR.findOne(2251l));
    }

    public void sdfljsldfjlsjdf() {
        for (Antique q : antiqR.findAll()) {
            if (q.getPrice() < 2000)
                q.setPrice((int) (q.getPrice() + 2000));
            antiqR.saveAndFlush(q);
        }
    }


    public void test() {
        String[] fs = new File("/Users/penli3/workspace/project/src/main/webapp/resources/img/collection").list();
        for (Antique a : antiqR.findAll()) {
            List<String> re = new LinkedList<String>();
            for (int i = 0; i < 4; i++) {
                re.add("img/collection/" + fs[(int) (Math.random() * fs.length)]);
            }
            a.setDetailPicUrls(CommonUtil.toJSON(re));
            antiqR.saveAndFlush(a);
        }
        print(antiqR.findAll());
    }

    public void favor() {
        print(colR.findByUserID(3));
    }

    public void collection() {
        User r = userR.findAll().get(0);
        List<Antique> ans = antiqR.findAll();
        for (int i = 0; i < 10; i++) {
            Favor f = new Favor();
            f.setUser(r);
            f.setAntique(ans.get((int) (Math.random() * ans.size())));
            colR.save(f);
        }

        print(colR.findAll());
    }

    public void tesXt() {
        List<Antique> is = antiqR.findByUser(12);
        for (Antique an : is) {
            an.setPrice(CommonUtil.format(Math.random() * 10000));
            an.setAge(ageR.findOne((int) (Math.random() * 23)));
            antiqR.saveAndFlush(an);
        }
    }

    public void userRegularPhoto() {
        List<Antique> is = antiqR.findByUser(12);
        List<String> images = new LinkedList<String>();
        for (Antique an : is) {
            images.add(an.getImageUrl());
            String d = an.getDetailPicUrls();
            d = d.substring(2, d.length() - 2);
            images.add(d);
        }
        File dir = new File("/Users/penli3/psyco/project/project-data/data-upload/itemImage");
        for (File f : dir.listFiles()) {
            boolean has = false;
            for (String s : images) {
                if (s.contains(f.getName())) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                // System.out.println(f.getName());
                f.delete();
            }
        }
    }

    public void city() throws JsonGenerationException, JsonMappingException, IOException {
        // print(cityR.getProvinces());
        // System.out.println(cityR.getProvinces().get(0).getParent());
        // System.out.println(cityR.findOne(1));
        // System.out.println(new ObjectMapper().writeValueAsString(cityR.findOne(143)));

        List<City> re = cityR.getProvinces();
        for (City p : re) {
            p.setChildren(cityR.getCities(p.getId()));
            for (City c : p.getChildren())
                c.setChildren(cityR.getDistricts(c.getId()));
        }
    }

    void print(Iterable<?> list) {
        for (Object o : list)
            System.out.println(o);
    }
}