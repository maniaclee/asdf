var p4c = {};
p4c.VM = function () {
    this.typeTags = ko.observableArray();
    this.news = ko.observableArray();
    this.articles = ko.observableArray();
    this.itemsHot = ko.observableArray();
    this.itemsRecommand = ko.observable();
    psyco.installFormats(this);
};
p4c.vm = new p4c.VM();
p4c.init = function () {
    psyco.ui.carouselMulti($('#myCarousel'));
    // psyco.ui.carouselMultis($('#carousel-items-recommand'), 4);
    psyco.ui.carouselMultis($('#carousel-items-new-arrivals'), 2);
    psyco.ui.carouselMultis($('#carousel-items-recommand'), 4);
    //psyco.loadJsonKO('types', null, p4c.vm.typeTags);
    //psyco.loadJsonKO('recommond', null, p4c.vm.itemsRecommand);
    psyco.loadJsonKO('news/hot', null, p4c.vm.news);
    psyco.loadJsonKO('article/hot', null, p4c.vm.articles);
    //psyco.loadJsonKO('hot', null, p4c.vm.itemsHot, function (data) {
    //    psyco.ui.carouselMultis($('#carousel-items-recommand'), 4);
    //});
    // p4c.toSql(p4c.vm.types);
    var m = function (url) {
        // $.getJSON(url, function(data) {
        // console.log(url, data);
        // })
        $.ajax({
            url: url,
            type: 'GET',
            contentType: "application/hal+json",
            success: function (data) {
                console.log(data);
            }
        });
    };
};
$(document).ready(function () {
    ko.applyBindings(p4c.vm);
    p4c.init();
    var a = '';
    $('.vertical-menu-multi-level >li >a').each(function () {
        a += ('{name:"' + $(this).text() + '",children:[]},\n');
    });
});
var subjects = [{
    "children": [{"code": 11, "id": 2, "level": 2, "name": "数学史"}, {
        "code": 14,
        "id": 3,
        "level": 2,
        "name": "数理逻辑与数学基础"
    }, {"code": 17, "id": 11, "level": 2, "name": "数论"}, {"code": 21, "id": 21, "level": 2, "name": "代数学"}, {
        "code": 24,
        "id": 38,
        "level": 2,
        "name": "代数几何学"
    }, {"code": 27, "id": 39, "level": 2, "name": "几何学"}, {"code": 31, "id": 51, "level": 2, "name": "拓扑学"}, {
        "code": 34,
        "id": 64,
        "level": 2,
        "name": "数学分析"
    }, {"code": 37, "id": 69, "level": 2, "name": "非标准分析"}, {"code": 41, "id": 70, "level": 2, "name": "函数论"}, {
        "code": 44,
        "id": 79,
        "level": 2,
        "name": "常微分方程"
    }, {"code": 47, "id": 84, "level": 2, "name": "偏微分方程"}, {"code": 51, "id": 90, "level": 2, "name": "动力系统"}, {
        "code": 54,
        "id": 95,
        "level": 2,
        "name": "积分方程"
    }, {"code": 57, "id": 96, "level": 2, "name": "泛函分析"}, {"code": 61, "id": 108, "level": 2, "name": "计算数学"}, {
        "code": 64,
        "id": 118,
        "level": 2,
        "name": "概率论"
    }, {"code": 67, "id": 128, "level": 2, "name": "数理统计学"}, {
        "code": 71,
        "id": 141,
        "level": 2,
        "name": "应用统计数学"
    }, {"code": 74, "id": 147, "level": 2, "name": "运筹学"}, {"code": 77, "id": 164, "level": 2, "name": "组合数学"}, {
        "code": 81,
        "id": 165,
        "level": 2,
        "name": "离散数学"
    }, {"code": 84, "id": 166, "level": 2, "name": "模糊数学"}, {
        "code": 87,
        "id": 167,
        "level": 2,
        "name": "应用数学"
    }, {"code": 99, "id": 168, "level": 2, "name": "数学其他学科"}], "code": 110, "id": 1, "level": 1, "name": "数学"
}, {
    "children": [{"code": 10, "id": 170, "level": 2, "name": "信息科学与系统科学基础学科"}, {
        "code": 20,
        "id": 175,
        "level": 2,
        "name": "系统学"
    }, {"code": 30, "id": 183, "level": 2, "name": "控制理论"}, {
        "code": 40,
        "id": 189,
        "level": 2,
        "name": "系统评估与可行性分析"
    }, {"code": 50, "id": 190, "level": 2, "name": "系统工程方法论"}, {
        "code": 60,
        "id": 193,
        "level": 2,
        "name": "系统工程"
    }, {"code": 99, "id": 194, "level": 2, "name": "信息科学与系统科学其他学科"}],
    "code": 120,
    "id": 169,
    "level": 1,
    "name": "信息科学与系统科学"
}, {
    "children": [{"code": 10, "id": 196, "level": 2, "name": "基础力学"}, {
        "code": 15,
        "id": 206,
        "level": 2,
        "name": "固体力学"
    }, {"code": 20, "id": 222, "level": 2, "name": "振动与波"}, {
        "code": 25,
        "id": 231,
        "level": 2,
        "name": "流体力学"
    }, {"code": 30, "id": 252, "level": 2, "name": "流变学"}, {"code": 35, "id": 253, "level": 2, "name": "爆炸力学"}, {
        "code": 40,
        "id": 258,
        "level": 2,
        "name": "物理力学"
    }, {"code": 45, "id": 266, "level": 2, "name": "统计力学"}, {
        "code": 50,
        "id": 267,
        "level": 2,
        "name": "应用力学"
    }, {"code": 99, "id": 268, "level": 2, "name": "力学其他学科"}], "code": 130, "id": 195, "level": 1, "name": "力学"
}, {
    "children": [{"code": 10, "id": 270, "level": 2, "name": "物理学史"}, {
        "code": 15,
        "id": 271,
        "level": 2,
        "name": "理论物理学"
    }, {"code": 20, "id": 279, "level": 2, "name": "声学"}, {"code": 25, "id": 287, "level": 2, "name": "热学"}, {
        "code": 30,
        "id": 292,
        "level": 2,
        "name": "光学"
    }, {"code": 35, "id": 305, "level": 2, "name": "电磁学"}, {
        "code": 40,
        "id": 311,
        "level": 2,
        "name": "无线电物理"
    }, {"code": 45, "id": 318, "level": 2, "name": "电子物理学"}, {
        "code": 50,
        "id": 323,
        "level": 2,
        "name": "凝聚态物理学"
    }, {"code": 55, "id": 340, "level": 2, "name": "等离子体物理学"}, {
        "code": 60,
        "id": 347,
        "level": 2,
        "name": "原子分子物理学"
    }, {"code": 65, "id": 354, "level": 2, "name": "原子核物理学"}, {
        "code": 70,
        "id": 365,
        "level": 2,
        "name": "高能物理学"
    }, {"code": 75, "id": 371, "level": 2, "name": "计算物理学"}, {
        "code": 80,
        "id": 372,
        "level": 2,
        "name": "应用物理学"
    }, {"code": 99, "id": 373, "level": 2, "name": "物理学其他学科"}], "code": 140, "id": 269, "level": 1, "name": "物理学"
}, {
    "children": [{"code": 10, "id": 375, "level": 2, "name": "化学史"}, {
        "code": 15,
        "id": 376,
        "level": 2,
        "name": "无机化学"
    }, {"code": 20, "id": 386, "level": 2, "name": "有机化学"}, {
        "code": 25,
        "id": 395,
        "level": 2,
        "name": "分析化学"
    }, {"code": 30, "id": 408, "level": 2, "name": "物理化学"}, {
        "code": 35,
        "id": 422,
        "level": 2,
        "name": "化学物理学"
    }, {"code": 40, "id": 423, "level": 2, "name": "高分子物理"}, {
        "code": 45,
        "id": 424,
        "level": 2,
        "name": "高分子化学"
    }, {"code": 50, "id": 432, "level": 2, "name": "核化学"}, {"code": 55, "id": 441, "level": 2, "name": "应用化学"}, {
        "code": 99,
        "id": 442,
        "level": 2,
        "name": "化学其他学科"
    }], "code": 150, "id": 374, "level": 1, "name": "化学"
}, {
    "children": [{"code": 10, "id": 444, "level": 2, "name": "天文学史"}, {
        "code": 15,
        "id": 445,
        "level": 2,
        "name": "天体力学"
    }, {"code": 20, "id": 453, "level": 2, "name": "天体物理学"}, {
        "code": 25,
        "id": 460,
        "level": 2,
        "name": "天体化学"
    }, {"code": 30, "id": 461, "level": 2, "name": "天体测量学"}, {
        "code": 35,
        "id": 470,
        "level": 2,
        "name": "射电天文学"
    }, {"code": 40, "id": 474, "level": 2, "name": "空间天文学"}, {
        "code": 45,
        "id": 481,
        "level": 2,
        "name": "天体演化学(各层次天体形成与演化入各学科)"
    }, {"code": 50, "id": 482, "level": 2, "name": "星系与宇宙学"}, {
        "code": 55,
        "id": 491,
        "level": 2,
        "name": "恒星与银河系"
    }, {"code": 60, "id": 498, "level": 2, "name": "太阳与太阳系"}, {
        "code": 65,
        "id": 506,
        "level": 2,
        "name": "天体生物学"
    }, {"code": 99, "id": 507, "level": 2, "name": "天文学其他学科"}], "code": 160, "id": 443, "level": 1, "name": "天文学"
}, {
    "children": [{"code": 10, "id": 509, "level": 2, "name": "地球科学史"}, {
        "code": 15,
        "id": 510,
        "level": 2,
        "name": "大气科学"
    }, {"code": 20, "id": 520, "level": 2, "name": "固体地球物理学"}, {
        "code": 25,
        "id": 536,
        "level": 2,
        "name": "空间物理学"
    }, {"code": 30, "id": 543, "level": 2, "name": "地球化学"}, {
        "code": 35,
        "id": 555,
        "level": 2,
        "name": "大地测量学"
    }, {"code": 40, "id": 563, "level": 2, "name": "地图学"}, {"code": 45, "id": 564, "level": 2, "name": "地理学"}, {
        "code": 50,
        "id": 568,
        "level": 2,
        "name": "地质学"
    }, {"code": 55, "id": 593, "level": 2, "name": "水文学"}, {"code": 60, "id": 603, "level": 2, "name": "海洋科学"}, {
        "code": 99,
        "id": 614,
        "level": 2,
        "name": "地球科学其他学科"
    }], "code": 170, "id": 508, "level": 1, "name": "地球科学"
}, {
    "children": [{"code": 11, "id": 616, "level": 2, "name": "生物数学(包括生物统计学等)"}, {
        "code": 14,
        "id": 617,
        "level": 2,
        "name": "生物物理学"
    }, {"code": 17, "id": 631, "level": 2, "name": "生物化学"}, {
        "code": 21,
        "id": 645,
        "level": 2,
        "name": "细胞生物学"
    }, {"code": 24, "id": 653, "level": 2, "name": "生理学"}, {
        "code": 27,
        "id": 673,
        "level": 2,
        "name": "发育生物学"
    }, {"code": 31, "id": 674, "level": 2, "name": "遗传学"}, {
        "code": 34,
        "id": 689,
        "level": 2,
        "name": "放射生物学"
    }, {"code": 37, "id": 697, "level": 2, "name": "分子生物学"}, {
        "code": 41,
        "id": 698,
        "level": 2,
        "name": "生物进化论"
    }, {"code": 44, "id": 699, "level": 2, "name": "生态学"}, {
        "code": 47,
        "id": 710,
        "level": 2,
        "name": "神经生物学"
    }, {"code": 51, "id": 721, "level": 2, "name": "植物学"}, {"code": 54, "id": 739, "level": 2, "name": "昆虫学"}, {
        "code": 57,
        "id": 752,
        "level": 2,
        "name": "动物学"
    }, {"code": 61, "id": 772, "level": 2, "name": "微生物学"}, {"code": 64, "id": 783, "level": 2, "name": "病毒学"}, {
        "code": 67,
        "id": 789,
        "level": 2,
        "name": "人类学"
    }, {"code": 71, "id": 800, "level": 2, "name": "生物工程(亦称生物技术)"}, {
        "code": 74,
        "id": 807,
        "level": 2,
        "name": "心理学"
    }, {"code": 99, "id": 819, "level": 2, "name": "生物学其他学科"}], "code": 180, "id": 615, "level": 1, "name": "生物学"
}, {
    "children": [{"code": 10, "id": 821, "level": 2, "name": "农业史"}, {
        "code": 20,
        "id": 822,
        "level": 2,
        "name": "农业基础学科"
    }, {"code": 30, "id": 832, "level": 2, "name": "农艺学"}, {"code": 40, "id": 844, "level": 2, "name": "园艺学"}, {
        "code": 50,
        "id": 852,
        "level": 2,
        "name": "土壤学"
    }, {"code": 60, "id": 864, "level": 2, "name": "植物保护学"}, {
        "code": 70,
        "id": 880,
        "level": 2,
        "name": "农业工程"
    }, {"code": 99, "id": 891, "level": 2, "name": "农学其他学科"}], "code": 210, "id": 820, "level": 1, "name": "农学"
}, {
    "children": [{"code": 10, "id": 893, "level": 2, "name": "林业基础学科"}, {
        "code": 15,
        "id": 902,
        "level": 2,
        "name": "林木遗传育种学"
    }, {"code": 20, "id": 906, "level": 2, "name": "森林培育学(亦称造林学)"}, {
        "code": 25,
        "id": 907,
        "level": 2,
        "name": "森林经理学"
    }, {"code": 30, "id": 914, "level": 2, "name": "森林保护学"}, {
        "code": 35,
        "id": 919,
        "level": 2,
        "name": "野生动物保护与管理"
    }, {"code": 40, "id": 920, "level": 2, "name": "防护林学"}, {
        "code": 45,
        "id": 921,
        "level": 2,
        "name": "经济林学"
    }, {"code": 50, "id": 922, "level": 2, "name": "园林学"}, {"code": 55, "id": 927, "level": 2, "name": "林业工程"}, {
        "code": 60,
        "id": 936,
        "level": 2,
        "name": "森林统计学"
    }, {"code": 65, "id": 937, "level": 2, "name": "林业经济学"}, {"code": 99, "id": 938, "level": 2, "name": "林学其他学科"}],
    "code": 220,
    "id": 892,
    "level": 1,
    "name": "林学"
}, {
    "children": [{"code": 10, "id": 940, "level": 2, "name": "畜牧、兽医科学基础学科"}, {
        "code": 20,
        "id": 947,
        "level": 2,
        "name": "畜牧学"
    }, {"code": 30, "id": 964, "level": 2, "name": "兽医学"}, {"code": 99, "id": 978, "level": 2, "name": "畜牧、兽医科学其他学科"}],
    "code": 230,
    "id": 939,
    "level": 1,
    "name": "畜牧、兽医科学"
}, {
    "children": [{"code": 10, "id": 980, "level": 2, "name": "水产学基础学科"}, {
        "code": 15,
        "id": 986,
        "level": 2,
        "name": "水产增殖学"
    }, {"code": 20, "id": 987, "level": 2, "name": "水产养殖学"}, {
        "code": 25,
        "id": 988,
        "level": 2,
        "name": "水产饲料学"
    }, {"code": 30, "id": 989, "level": 2, "name": "水产保护学"}, {
        "code": 35,
        "id": 990,
        "level": 2,
        "name": "捕捞学"
    }, {"code": 40, "id": 991, "level": 2, "name": "水产品贮藏与加工"}, {
        "code": 45,
        "id": 992,
        "level": 2,
        "name": "水产工程学"
    }, {"code": 50, "id": 993, "level": 2, "name": "水产资源学"}, {
        "code": 55,
        "id": 994,
        "level": 2,
        "name": "水产经济学"
    }, {"code": 99, "id": 995, "level": 2, "name": "水产学其他学科"}], "code": 240, "id": 979, "level": 1, "name": "水产学"
}, {
    "children": [{"code": 11, "id": 997, "level": 2, "name": "医学生物化学"}, {
        "code": 14,
        "id": 998,
        "level": 2,
        "name": "人体解剖学"
    }, {"code": 17, "id": 1002, "level": 2, "name": "医学细胞生物学"}, {
        "code": 21,
        "id": 1003,
        "level": 2,
        "name": "人体生理学"
    }, {"code": 24, "id": 1004, "level": 2, "name": "人体组织胚胎学"}, {
        "code": 27,
        "id": 1005,
        "level": 2,
        "name": "医学遗传学"
    }, {"code": 31, "id": 1006, "level": 2, "name": "放射医学"}, {
        "code": 34,
        "id": 1007,
        "level": 2,
        "name": "人体免疫学"
    }, {"code": 37, "id": 1008, "level": 2, "name": "医学寄生虫学"}, {
        "code": 41,
        "id": 1014,
        "level": 2,
        "name": "医学微生物学(包括医学病毒学等)"
    }, {"code": 44, "id": 1015, "level": 2, "name": "病理学"}, {
        "code": 47,
        "id": 1025,
        "level": 2,
        "name": "药理学"
    }, {"code": 51, "id": 1032, "level": 2, "name": "医学实验动物学"}, {
        "code": 54,
        "id": 1033,
        "level": 2,
        "name": "医学心理学"
    }, {"code": 57, "id": 1034, "level": 2, "name": "医学统计学"}, {
        "code": 61,
        "id": 1035,
        "level": 2,
        "name": "生物医学工程学"
    }, {"code": 99, "id": 1042, "level": 2, "name": "基础医学其他学科"}], "code": 310, "id": 996, "level": 1, "name": "基础医学"
}, {
    "children": [{"code": 11, "id": 1044, "level": 2, "name": "临床诊断学"}, {
        "code": 14,
        "id": 1052,
        "level": 2,
        "name": "保健医学"
    }, {"code": 17, "id": 1057, "level": 2, "name": "理疗学"}, {
        "code": 21,
        "id": 1058,
        "level": 2,
        "name": "麻醉学"
    }, {"code": 24, "id": 1063, "level": 2, "name": "内科学"}, {
        "code": 27,
        "id": 1075,
        "level": 2,
        "name": "外科学"
    }, {"code": 31, "id": 1089, "level": 2, "name": "妇产科学"}, {
        "code": 34,
        "id": 1097,
        "level": 2,
        "name": "儿科学"
    }, {"code": 37, "id": 1098, "level": 2, "name": "眼科学"}, {
        "code": 41,
        "id": 1099,
        "level": 2,
        "name": "耳鼻咽喉科学"
    }, {"code": 44, "id": 1100, "level": 2, "name": "口腔医学"}, {
        "code": 47,
        "id": 1111,
        "level": 2,
        "name": "皮肤病学"
    }, {"code": 51, "id": 1112, "level": 2, "name": "性医学"}, {
        "code": 54,
        "id": 1113,
        "level": 2,
        "name": "神经病学"
    }, {"code": 57, "id": 1114, "level": 2, "name": "精神病学(包括精神卫生及行为医学等)"}, {
        "code": 61,
        "id": 1115,
        "level": 2,
        "name": "急诊医学"
    }, {"code": 64, "id": 1116, "level": 2, "name": "核医学"}, {
        "code": 67,
        "id": 1117,
        "level": 2,
        "name": "肿瘤学"
    }, {"code": 71, "id": 1126, "level": 2, "name": "护理学"}, {"code": 99, "id": 1134, "level": 2, "name": "临床医学其他学科"}],
    "code": 320,
    "id": 1043,
    "level": 1,
    "name": "临床医学"
}, {
    "children": [{"code": 11, "id": 1136, "level": 2, "name": "营养学"}, {
        "code": 14,
        "id": 1137,
        "level": 2,
        "name": "毒理学"
    }, {"code": 17, "id": 1138, "level": 2, "name": "消毒学"}, {
        "code": 21,
        "id": 1139,
        "level": 2,
        "name": "流行病学"
    }, {"code": 24, "id": 1140, "level": 2, "name": "传染病学"}, {
        "code": 27,
        "id": 1141,
        "level": 2,
        "name": "媒介生物控制学"
    }, {"code": 31, "id": 1142, "level": 2, "name": "环境医学"}, {
        "code": 34,
        "id": 1143,
        "level": 2,
        "name": "职业病学"
    }, {"code": 37, "id": 1144, "level": 2, "name": "地方病学"}, {
        "code": 41,
        "id": 1145,
        "level": 2,
        "name": "社会医学"
    }, {"code": 44, "id": 1146, "level": 2, "name": "卫生检验学"}, {
        "code": 47,
        "id": 1147,
        "level": 2,
        "name": "食品卫生学"
    }, {"code": 51, "id": 1148, "level": 2, "name": "儿少卫生学"}, {
        "code": 54,
        "id": 1149,
        "level": 2,
        "name": "妇幼卫生学"
    }, {"code": 57, "id": 1150, "level": 2, "name": "环境卫生学"}, {
        "code": 61,
        "id": 1151,
        "level": 2,
        "name": "劳动卫生学"
    }, {"code": 64, "id": 1152, "level": 2, "name": "放射卫生学"}, {
        "code": 67,
        "id": 1153,
        "level": 2,
        "name": "卫生工程学"
    }, {"code": 71, "id": 1154, "level": 2, "name": "卫生经济学"}, {
        "code": 74,
        "id": 1155,
        "level": 2,
        "name": "优生学"
    }, {"code": 77, "id": 1156, "level": 2, "name": "健康教育学"}, {
        "code": 81,
        "id": 1157,
        "level": 2,
        "name": "卫生管理学"
    }, {"code": 99, "id": 1158, "level": 2, "name": "预防医学与卫生学其他学科"}],
    "code": 330,
    "id": 1135,
    "level": 1,
    "name": "预防医学与卫生学"
}, {
    "children": [{"code": 10, "id": 1160, "level": 2, "name": "军事医学"}, {
        "code": 20,
        "id": 1172,
        "level": 2,
        "name": "特种医学"
    }, {"code": 99, "id": 1178, "level": 2, "name": "军事医学与特种医学其他学科"}],
    "code": 340,
    "id": 1159,
    "level": 1,
    "name": "军事医学与特种医学"
}, {
    "children": [{"code": 10, "id": 1180, "level": 2, "name": "药物化学(包括天然药物化学等)"}, {
        "code": 20,
        "id": 1181,
        "level": 2,
        "name": "生物药物学"
    }, {"code": 25, "id": 1182, "level": 2, "name": "微生物药物学"}, {
        "code": 30,
        "id": 1183,
        "level": 2,
        "name": "放射性药物学"
    }, {"code": 35, "id": 1184, "level": 2, "name": "药剂学"}, {
        "code": 40,
        "id": 1185,
        "level": 2,
        "name": "药效学"
    }, {"code": 45, "id": 1186, "level": 2, "name": "药物管理学"}, {
        "code": 50,
        "id": 1187,
        "level": 2,
        "name": "药物统计学"
    }, {"code": 99, "id": 1188, "level": 2, "name": "药学其他学科"}], "code": 350, "id": 1179, "level": 1, "name": "药学"
}, {
    "children": [{"code": 10, "id": 1190, "level": 2, "name": "中医学"}, {
        "code": 20,
        "id": 1210,
        "level": 2,
        "name": "民族医学"
    }, {"code": 30, "id": 1211, "level": 2, "name": "中西医结合医学"}, {
        "code": 40,
        "id": 1212,
        "level": 2,
        "name": "中药学"
    }, {"code": 99, "id": 1223, "level": 2, "name": "中医学与中药学其他学科"}],
    "code": 360,
    "id": 1189,
    "level": 1,
    "name": "中医学与中药学"
}, {
    "children": [{"code": 10, "id": 1225, "level": 2, "name": "工程数学"}, {
        "code": 15,
        "id": 1226,
        "level": 2,
        "name": "工程控制论"
    }, {"code": 20, "id": 1227, "level": 2, "name": "工程力学"}, {
        "code": 25,
        "id": 1228,
        "level": 2,
        "name": "工程物理学"
    }, {"code": 30, "id": 1229, "level": 2, "name": "工程地质学"}, {
        "code": 35,
        "id": 1230,
        "level": 2,
        "name": "工程水文学"
    }, {"code": 40, "id": 1231, "level": 2, "name": "工程仿生学"}, {
        "code": 45,
        "id": 1232,
        "level": 2,
        "name": "工程心理学"
    }, {"code": 50, "id": 1233, "level": 2, "name": "标准化科学技术(亦称标准化学)"}, {
        "code": 55,
        "id": 1234,
        "level": 2,
        "name": "计量学"
    }, {"code": 60, "id": 1235, "level": 2, "name": "工程图学"}, {
        "code": 65,
        "id": 1236,
        "level": 2,
        "name": "勘查技术"
    }, {"code": 70, "id": 1237, "level": 2, "name": "工程通用技术"}, {
        "code": 75,
        "id": 1247,
        "level": 2,
        "name": "工业工程学(亦称工程系统工程)"
    }, {"code": 99, "id": 1248, "level": 2, "name": "工程与技术科学基础学科其他学科"}],
    "code": 410,
    "id": 1224,
    "level": 1,
    "name": "工程与技术科学基础学科"
}, {
    "children": [{"code": 10, "id": 1250, "level": 2, "name": "大地测量技术"}, {
        "code": 20,
        "id": 1255,
        "level": 2,
        "name": "摄影测量与遥感技术"
    }, {"code": 30, "id": 1261, "level": 2, "name": "地图制图技术"}, {
        "code": 40,
        "id": 1267,
        "level": 2,
        "name": "工程测量技术"
    }, {"code": 50, "id": 1271, "level": 2, "name": "海洋测绘"}, {
        "code": 60,
        "id": 1282,
        "level": 2,
        "name": "测绘仪器"
    }, {"code": 99, "id": 1283, "level": 2, "name": "测绘科学技术其他学科"}],
    "code": 420,
    "id": 1249,
    "level": 1,
    "name": "测绘科学技术"
}, {
    "children": [{"code": 10, "id": 1285, "level": 2, "name": "材料科学基础学科"}, {
        "code": 15,
        "id": 1293,
        "level": 2,
        "name": "材料表面与界面(包括表面优化技术)"
    }, {"code": 20, "id": 1294, "level": 2, "name": "材料失效与保护(包括材料腐蚀、磨损、老化、断裂及其控制等)"}, {
        "code": 25,
        "id": 1295,
        "level": 2,
        "name": "材料检测与分析技术"
    }, {"code": 30, "id": 1296, "level": 2, "name": "材料实验"}, {
        "code": 35,
        "id": 1297,
        "level": 2,
        "name": "材料合成与加工工艺"
    }, {"code": 40, "id": 1298, "level": 2, "name": "金属材料"}, {
        "code": 45,
        "id": 1305,
        "level": 2,
        "name": "无机非金属材料"
    }, {"code": 50, "id": 1312, "level": 2, "name": "有机高分子材料"}, {
        "code": 55,
        "id": 1318,
        "level": 2,
        "name": "复合材料"
    }, {"code": 99, "id": 1323, "level": 2, "name": "材料科学其他学科"}], "code": 430, "id": 1284, "level": 1, "name": "材料科学"
}, {
    "children": [{"code": 10, "id": 1325, "level": 2, "name": "矿山地质学"}, {
        "code": 15,
        "id": 1326,
        "level": 2,
        "name": "矿山测量"
    }, {"code": 20, "id": 1327, "level": 2, "name": "矿山设计"}, {
        "code": 25,
        "id": 1331,
        "level": 2,
        "name": "矿山地面工程"
    }, {"code": 30, "id": 1332, "level": 2, "name": "井巷工程"}, {
        "code": 35,
        "id": 1336,
        "level": 2,
        "name": "采矿工程"
    }, {"code": 40, "id": 1342, "level": 2, "name": "选矿工程"}, {
        "code": 45,
        "id": 1347,
        "level": 2,
        "name": "钻井工程"
    }, {"code": 50, "id": 1348, "level": 2, "name": "油气田井开发工程"}, {
        "code": 55,
        "id": 1349,
        "level": 2,
        "name": "石油、天然气储存与运输工程"
    }, {"code": 60, "id": 1350, "level": 2, "name": "矿山机械工程"}, {
        "code": 65,
        "id": 1355,
        "level": 2,
        "name": "矿山电气工程"
    }, {"code": 70, "id": 1356, "level": 2, "name": "采矿环境工程"}, {
        "code": 75,
        "id": 1357,
        "level": 2,
        "name": "矿山安全"
    }, {"code": 80, "id": 1358, "level": 2, "name": "矿山综合利用工程"}, {
        "code": 99,
        "id": 1359,
        "level": 2,
        "name": "矿山工程技术其他学科"
    }], "code": 440, "id": 1324, "level": 1, "name": "矿山工程技术"
}, {
    "children": [{"code": 10, "id": 1361, "level": 2, "name": "冶金物理化学"}, {
        "code": 15,
        "id": 1362,
        "level": 2,
        "name": "冶金反应工程"
    }, {"code": 20, "id": 1363, "level": 2, "name": "冶金原料与预处理"}, {
        "code": 25,
        "id": 1364,
        "level": 2,
        "name": "冶金热能工程"
    }, {"code": 30, "id": 1370, "level": 2, "name": "冶金技术"}, {
        "code": 35,
        "id": 1381,
        "level": 2,
        "name": "钢铁冶金"
    }, {"code": 40, "id": 1386, "level": 2, "name": "有色金属冶金"}, {
        "code": 45,
        "id": 1387,
        "level": 2,
        "name": "轧制"
    }, {"code": 50, "id": 1388, "level": 2, "name": "冶金机械及自动化"}, {
        "code": 99,
        "id": 1389,
        "level": 2,
        "name": "冶金工程技术其他学科"
    }], "code": 450, "id": 1360, "level": 1, "name": "冶金工程技术"
}, {
    "children": [{"code": 10, "id": 1391, "level": 2, "name": "机械史"}, {
        "code": 15,
        "id": 1392,
        "level": 2,
        "name": "机械学"
    }, {"code": 20, "id": 1398, "level": 2, "name": "机械设计"}, {
        "code": 25,
        "id": 1404,
        "level": 2,
        "name": "机械制造工艺与设备"
    }, {"code": 30, "id": 1414, "level": 2, "name": "刀具技术"}, {
        "code": 35,
        "id": 1419,
        "level": 2,
        "name": "机床技术"
    }, {"code": 40, "id": 1425, "level": 2, "name": "仪器仪表技术"}, {
        "code": 45,
        "id": 1436,
        "level": 2,
        "name": "流体传动与控制(包括气动液压控制技术等)"
    }, {"code": 50, "id": 1437, "level": 2, "name": "机械制造自动化"}, {
        "code": 55,
        "id": 1443,
        "level": 2,
        "name": "专用机械工程"
    }, {"code": 99, "id": 1446, "level": 2, "name": "机械工程其他学科"}], "code": 460, "id": 1390, "level": 1, "name": "机械工程"
}, {
    "children": [{"code": 10, "id": 1448, "level": 2, "name": "工程热物理"}, {
        "code": 20,
        "id": 1454,
        "level": 2,
        "name": "热工学"
    }, {"code": 30, "id": 1460, "level": 2, "name": "动力机械工程"}, {
        "code": 40,
        "id": 1466,
        "level": 2,
        "name": "电气工程"
    }, {"code": 99, "id": 1485, "level": 2, "name": "动力与电气工程其他学科"}],
    "code": 470,
    "id": 1447,
    "level": 1,
    "name": "动力与电气工程"
}, {
    "children": [{"code": 10, "id": 1487, "level": 2, "name": "能源化学"}, {
        "code": 20,
        "id": 1488,
        "level": 2,
        "name": "能源地理学"
    }, {"code": 30, "id": 1489, "level": 2, "name": "能源计算与测量"}, {
        "code": 40,
        "id": 1490,
        "level": 2,
        "name": "储能技术"
    }, {"code": 50, "id": 1491, "level": 2, "name": "节能技术"}, {
        "code": 60,
        "id": 1492,
        "level": 2,
        "name": "一次能源"
    }, {"code": 70, "id": 1502, "level": 2, "name": "二次能源"}, {
        "code": 80,
        "id": 1509,
        "level": 2,
        "name": "能源系统工程"
    }, {"code": 99, "id": 1510, "level": 2, "name": "能源科学技术其他学科"}],
    "code": 480,
    "id": 1486,
    "level": 1,
    "name": "能源科学技术"
}, {
    "children": [{"code": 10, "id": 1512, "level": 2, "name": "辐射物理与技术"}, {
        "code": 15,
        "id": 1513,
        "level": 2,
        "name": "核探测技术与核电子学"
    }, {"code": 20, "id": 1514, "level": 2, "name": "放射性计量学"}, {
        "code": 25,
        "id": 1515,
        "level": 2,
        "name": "核仪器、仪表"
    }, {"code": 30, "id": 1516, "level": 2, "name": "核材料与工艺技术"}, {
        "code": 35,
        "id": 1519,
        "level": 2,
        "name": "粒子加速器"
    }, {"code": 40, "id": 1523, "level": 2, "name": "裂变堆工程技术"}, {
        "code": 45,
        "id": 1531,
        "level": 2,
        "name": "核聚变工程技术"
    }, {"code": 50, "id": 1537, "level": 2, "name": "核动力工程技术"}, {
        "code": 55,
        "id": 1543,
        "level": 2,
        "name": "同位素技术"
    }, {"code": 60, "id": 1548, "level": 2, "name": "核爆炸工程"}, {
        "code": 65,
        "id": 1549,
        "level": 2,
        "name": "核安全(包括核电站安全)"
    }, {"code": 70, "id": 1550, "level": 2, "name": "乏燃料后处理技术"}, {
        "code": 75,
        "id": 1551,
        "level": 2,
        "name": "辐射防护技术"
    }, {"code": 80, "id": 1552, "level": 2, "name": "核设施退役技术"}, {
        "code": 85,
        "id": 1553,
        "level": 2,
        "name": "放射性三废处理、处置技术"
    }, {"code": 99, "id": 1554, "level": 2, "name": "核科学技术其他学科"}], "code": 490, "id": 1511, "level": 1, "name": "核科学技术"
}, {
    "children": [{"code": 10, "id": 1556, "level": 2, "name": "电子技术"}, {
        "code": 20,
        "id": 1569,
        "level": 2,
        "name": "光电子学与激光技术"
    }, {"code": 30, "id": 1570, "level": 2, "name": "半导体技术"}, {
        "code": 40,
        "id": 1577,
        "level": 2,
        "name": "信息处理技术"
    }, {"code": 50, "id": 1584, "level": 2, "name": "通信技术"}, {
        "code": 60,
        "id": 1595,
        "level": 2,
        "name": "广播与电视工程技术"
    }, {"code": 70, "id": 1596, "level": 2, "name": "雷达工程"}, {
        "code": 80,
        "id": 1597,
        "level": 2,
        "name": "自动控制技术"
    }, {"code": 99, "id": 1605, "level": 2, "name": "电子、通信与自动控制技术其他学科"}],
    "code": 510,
    "id": 1555,
    "level": 1,
    "name": "电子、通信与自动控制技术"
}, {
    "children": [{"code": 10, "id": 1607, "level": 2, "name": "计算机科学技术基础学科"}, {
        "code": 20,
        "id": 1615,
        "level": 2,
        "name": "人工智能"
    }, {"code": 30, "id": 1624, "level": 2, "name": "计算机系统结构"}, {
        "code": 40,
        "id": 1631,
        "level": 2,
        "name": "计算机软件"
    }, {"code": 50, "id": 1640, "level": 2, "name": "计算机工程"}, {
        "code": 60,
        "id": 1648,
        "level": 2,
        "name": "计算机应用"
    }, {"code": 99, "id": 1658, "level": 2, "name": "计算机科学技术其他学科"}],
    "code": 520,
    "id": 1606,
    "level": 1,
    "name": "计算机科学技术"
}, {
    "children": [{"code": 11, "id": 1660, "level": 2, "name": "化学工程基础学科"}, {
        "code": 14,
        "id": 1666,
        "level": 2,
        "name": "化工测量技术与仪器仪表"
    }, {"code": 17, "id": 1667, "level": 2, "name": "化工传递过程"}, {
        "code": 21,
        "id": 1668,
        "level": 2,
        "name": "化学分离工程"
    }, {"code": 24, "id": 1677, "level": 2, "name": "化学反应工程"}, {
        "code": 27,
        "id": 1686,
        "level": 2,
        "name": "化工系统工程"
    }, {"code": 31, "id": 1691, "level": 2, "name": "化工机械与设备"}, {
        "code": 34,
        "id": 1692,
        "level": 2,
        "name": "无机化学工程"
    }, {"code": 37, "id": 1699, "level": 2, "name": "有机化学工程"}, {
        "code": 41,
        "id": 1700,
        "level": 2,
        "name": "电化学工程"
    }, {"code": 44, "id": 1706, "level": 2, "name": "高聚物工程"}, {
        "code": 47,
        "id": 1707,
        "level": 2,
        "name": "煤化学工程"
    }, {"code": 51, "id": 1708, "level": 2, "name": "石油化学工程"}, {
        "code": 54,
        "id": 1709,
        "level": 2,
        "name": "精细化学工程"
    }, {"code": 57, "id": 1717, "level": 2, "name": "造纸技术"}, {
        "code": 61,
        "id": 1718,
        "level": 2,
        "name": "毛皮与制革工程"
    }, {"code": 64, "id": 1719, "level": 2, "name": "制药工程"}, {
        "code": 67,
        "id": 1724,
        "level": 2,
        "name": "生物化学工程"
    }, {"code": 99, "id": 1725, "level": 2, "name": "化学工程其他学科"}], "code": 530, "id": 1659, "level": 1, "name": "化学工程"
}, {
    "children": [{"code": 10, "id": 1727, "level": 2, "name": "纺织科学技术基础学科"}, {
        "code": 20,
        "id": 1731,
        "level": 2,
        "name": "纺织材料"
    }, {"code": 30, "id": 1732, "level": 2, "name": "纤维制造技术"}, {
        "code": 40,
        "id": 1733,
        "level": 2,
        "name": "纺织技术"
    }, {"code": 50, "id": 1744, "level": 2, "name": "染整技术"}, {
        "code": 60,
        "id": 1750,
        "level": 2,
        "name": "服装技术"
    }, {"code": 70, "id": 1754, "level": 2, "name": "纺织机械与设备"}, {
        "code": 99,
        "id": 1758,
        "level": 2,
        "name": "纺织科学技术其他学科"
    }],
    "code": 540,
    "id": 1726,
    "level": 1,
    "name": "纺织科学技术"
}, {
    "children": [{"code": 10, "id": 1760, "level": 2, "name": "食品科学技术基础学科"}, {
        "code": 20,
        "id": 1765,
        "level": 2,
        "name": "食品加工技术"
    }, {"code": 30, "id": 1779, "level": 2, "name": "食品包装与储藏"}, {
        "code": 40,
        "id": 1780,
        "level": 2,
        "name": "食品机械"
    }, {"code": 50, "id": 1781, "level": 2, "name": "食品加工的副产品加工与利用"}, {
        "code": 60,
        "id": 1782,
        "level": 2,
        "name": "食品工业企业管理学"
    }, {"code": 99, "id": 1783, "level": 2, "name": "食品科学技术其他学科"}],
    "code": 550,
    "id": 1759,
    "level": 1,
    "name": "食品科学技术"
}, {
    "children": [{"code": 10, "id": 1785, "level": 2, "name": "建筑史"}, {
        "code": 15,
        "id": 1786,
        "level": 2,
        "name": "土木建筑工程基础学科"
    }, {"code": 20, "id": 1791, "level": 2, "name": "土木建筑工程测量"}, {
        "code": 25,
        "id": 1792,
        "level": 2,
        "name": "建筑材料"
    }, {"code": 30, "id": 1798, "level": 2, "name": "工程结构"}, {
        "code": 35,
        "id": 1805,
        "level": 2,
        "name": "土木建筑结构"
    }, {"code": 40, "id": 1814, "level": 2, "name": "土木建筑工程设计"}, {
        "code": 45,
        "id": 1822,
        "level": 2,
        "name": "土木建筑工程施工"
    }, {"code": 50, "id": 1830, "level": 2, "name": "土木工程机械与设备"}, {
        "code": 55,
        "id": 1839,
        "level": 2,
        "name": "市政工程"
    }, {"code": 60, "id": 1846, "level": 2, "name": "建筑经济学"}, {
        "code": 99,
        "id": 1847,
        "level": 2,
        "name": "土木建筑工程其他学科"
    }],
    "code": 560,
    "id": 1784,
    "level": 1,
    "name": "土木建筑工程"
}, {
    "children": [{"code": 10, "id": 1849, "level": 2, "name": "水利工程基础学科"}, {
        "code": 15,
        "id": 1853,
        "level": 2,
        "name": "水利工程测量"
    }, {"code": 20, "id": 1854, "level": 2, "name": "水工材料"}, {
        "code": 25,
        "id": 1855,
        "level": 2,
        "name": "水工结构(亦称水工建筑物)"
    }, {"code": 30, "id": 1859, "level": 2, "name": "水力机械"}, {
        "code": 35,
        "id": 1860,
        "level": 2,
        "name": "水利工程施工"
    }, {"code": 40, "id": 1864, "level": 2, "name": "水处理(不包括废水处理)"}, {
        "code": 45,
        "id": 1867,
        "level": 2,
        "name": "河流泥沙工程学"
    }, {"code": 50, "id": 1871, "level": 2, "name": "海洋工程"}, {
        "code": 55,
        "id": 1877,
        "level": 2,
        "name": "环境水利"
    }, {"code": 60, "id": 1882, "level": 2, "name": "水利管理"}, {
        "code": 65,
        "id": 1887,
        "level": 2,
        "name": "防洪工程"
    }, {"code": 70, "id": 1892, "level": 2, "name": "水利经济学"}, {"code": 99, "id": 1893, "level": 2, "name": "水利工程其他学科"}],
    "code": 570,
    "id": 1848,
    "level": 1,
    "name": "水利工程"
}, {
    "children": [{"code": 10, "id": 1895, "level": 2, "name": "道路工程"}, {
        "code": 20,
        "id": 1900,
        "level": 2,
        "name": "公路运输"
    }, {"code": 30, "id": 1905, "level": 2, "name": "铁路运输"}, {
        "code": 40,
        "id": 1911,
        "level": 2,
        "name": "水路运输"
    }, {"code": 50, "id": 1919, "level": 2, "name": "船舶、舰船工程"}, {
        "code": 60,
        "id": 1920,
        "level": 2,
        "name": "航空运输"
    }, {"code": 70, "id": 1924, "level": 2, "name": "交通运输系统工程"}, {
        "code": 80,
        "id": 1925,
        "level": 2,
        "name": "交通运输安全工程"
    }, {"code": 99, "id": 1926, "level": 2, "name": "交通运输工程其他学科"}],
    "code": 580,
    "id": 1894,
    "level": 1,
    "name": "交通运输工程"
}, {
    "children": [{"code": 10, "id": 1928, "level": 2, "name": "航空、航天科学技术基础学科"}, {
        "code": 15,
        "id": 1934,
        "level": 2,
        "name": "航空器结构与设计"
    }, {"code": 20, "id": 1939, "level": 2, "name": "航天器结构与设计"}, {
        "code": 25,
        "id": 1947,
        "level": 2,
        "name": "航空、航天推进系统"
    }, {"code": 30, "id": 1948, "level": 2, "name": "飞行器仪表、设备"}, {
        "code": 35,
        "id": 1949,
        "level": 2,
        "name": "飞行器控制、导航技术"
    }, {"code": 40, "id": 1950, "level": 2, "name": "航空、航天材料"}, {
        "code": 45,
        "id": 1956,
        "level": 2,
        "name": "飞行器制造技术"
    }, {"code": 50, "id": 1960, "level": 2, "name": "飞行器试验技术"}, {
        "code": 55,
        "id": 1966,
        "level": 2,
        "name": "飞行器发射、飞行技术"
    }, {"code": 60, "id": 1970, "level": 2, "name": "航天地面设施、技术保障"}, {
        "code": 65,
        "id": 1974,
        "level": 2,
        "name": "航空、航天系统工程"
    }, {"code": 99, "id": 1979, "level": 2, "name": "航空、航天科学技术其他学科"}],
    "code": 590,
    "id": 1927,
    "level": 1,
    "name": "航空、航天科学技术"
}, {
    "children": [{"code": 10, "id": 1981, "level": 2, "name": "环境科学技术基础学科"}, {
        "code": 20,
        "id": 1992,
        "level": 2,
        "name": "环境学"
    }, {"code": 30, "id": 1998, "level": 2, "name": "环境工程学"}, {
        "code": 99,
        "id": 2009,
        "level": 2,
        "name": "环境科学技术其他学科"
    }],
    "code": 610,
    "id": 1980,
    "level": 1,
    "name": "环境科学技术"
}, {
    "children": [{"code": 10, "id": 2011, "level": 2, "name": "安全科学技术基础学科"}, {
        "code": 20,
        "id": 2017,
        "level": 2,
        "name": "安全学"
    }, {"code": 30, "id": 2026, "level": 2, "name": "安全工程"}, {
        "code": 40,
        "id": 2033,
        "level": 2,
        "name": "职业卫生工程"
    }, {"code": 50, "id": 2039, "level": 2, "name": "安全管理工程"}, {
        "code": 99,
        "id": 2045,
        "level": 2,
        "name": "安全科学技术其他学科"
    }],
    "code": 620,
    "id": 2010,
    "level": 1,
    "name": "安全科学技术"
}, {
    "children": [{"code": 10, "id": 2047, "level": 2, "name": "管理思想史"}, {
        "code": 15,
        "id": 2048,
        "level": 2,
        "name": "管理理论"
    }, {"code": 20, "id": 2055, "level": 2, "name": "管理心理学"}, {
        "code": 25,
        "id": 2056,
        "level": 2,
        "name": "管理计量学"
    }, {"code": 30, "id": 2057, "level": 2, "name": "部门经济管理"}, {
        "code": 35,
        "id": 2058,
        "level": 2,
        "name": "科学学与科技管理"
    }, {"code": 40, "id": 2065, "level": 2, "name": "企业管理"}, {
        "code": 45,
        "id": 2077,
        "level": 2,
        "name": "行政管理"
    }, {"code": 50, "id": 2078, "level": 2, "name": "管理工程"}, {
        "code": 55,
        "id": 2091,
        "level": 2,
        "name": "人力资源开发与管理"
    }, {"code": 60, "id": 2095, "level": 2, "name": "未来学"}, {"code": 99, "id": 2101, "level": 2, "name": "管理学其他学科"}],
    "code": 630,
    "id": 2046,
    "level": 1,
    "name": "管理学"
}, {
    "children": [{"code": 10, "id": 2103, "level": 2, "name": "马、恩、列、斯思想研究"}, {
        "code": 20,
        "id": 2104,
        "level": 2,
        "name": "毛泽东思想研究"
    }, {"code": 30, "id": 2105, "level": 2, "name": "马克思主义思想史"}, {
        "code": 40,
        "id": 2106,
        "level": 2,
        "name": "科学社会主义"
    }, {"code": 50, "id": 2107, "level": 2, "name": "社会主义运动史(包括国际共产主义运动)"}, {
        "code": 60,
        "id": 2108,
        "level": 2,
        "name": "国外马克思主义研究"
    }, {"code": 99, "id": 2109, "level": 2, "name": "马克思主义其他学科"}], "code": 710, "id": 2102, "level": 1, "name": "马克思主义"
}, {
    "children": [{"code": 10, "id": 2111, "level": 2, "name": "马克思主义哲学"}, {
        "code": 15,
        "id": 2116,
        "level": 2,
        "name": "自然辩证法(亦称科学技术哲学)"
    }, {"code": 20, "id": 2122, "level": 2, "name": "中国哲学史"}, {
        "code": 25,
        "id": 2132,
        "level": 2,
        "name": "东方哲学史"
    }, {"code": 30, "id": 2137, "level": 2, "name": "西方哲学史"}, {
        "code": 35,
        "id": 2145,
        "level": 2,
        "name": "现代外国哲学"
    }, {"code": 40, "id": 2153, "level": 2, "name": "逻辑学"}, {
        "code": 45,
        "id": 2161,
        "level": 2,
        "name": "伦理学"
    }, {"code": 50, "id": 2175, "level": 2, "name": "美学"}, {"code": 99, "id": 2185, "level": 2, "name": "哲学其他学科"}],
    "code": 720,
    "id": 2110,
    "level": 1,
    "name": "哲学"
}, {
    "children": [{"code": 11, "id": 2187, "level": 2, "name": "宗教学理论"}, {
        "code": 14,
        "id": 2199,
        "level": 2,
        "name": "无神论"
    }, {"code": 17, "id": 2204, "level": 2, "name": "原始宗教"}, {
        "code": 21,
        "id": 2205,
        "level": 2,
        "name": "古代宗教"
    }, {"code": 24, "id": 2209, "level": 2, "name": "佛教"}, {"code": 27, "id": 2216, "level": 2, "name": "基督教"}, {
        "code": 31,
        "id": 2223,
        "level": 2,
        "name": "伊斯兰教"
    }, {"code": 34, "id": 2232, "level": 2, "name": "道教"}, {"code": 37, "id": 2238, "level": 2, "name": "印度教"}, {
        "code": 41,
        "id": 2239,
        "level": 2,
        "name": "犹太教"
    }, {"code": 44, "id": 2240, "level": 2, "name": "袄教"}, {"code": 47, "id": 2241, "level": 2, "name": "摩尼教"}, {
        "code": 51,
        "id": 2242,
        "level": 2,
        "name": "锡克教"
    }, {"code": 54, "id": 2243, "level": 2, "name": "耆那教"}, {
        "code": 57,
        "id": 2244,
        "level": 2,
        "name": "神道教"
    }, {"code": 61, "id": 2245, "level": 2, "name": "中国民间宗教与民间信仰"}, {
        "code": 64,
        "id": 2246,
        "level": 2,
        "name": "中国少数民族宗教"
    }, {"code": 67, "id": 2247, "level": 2, "name": "当代宗教"}, {"code": 99, "id": 2252, "level": 2, "name": "宗教学其他学科"}],
    "code": 730,
    "id": 2186,
    "level": 1,
    "name": "宗教学"
}, {
    "children": [{"code": 10, "id": 2254, "level": 2, "name": "普通语言学"}, {
        "code": 15,
        "id": 2265,
        "level": 2,
        "name": "比较语言学"
    }, {"code": 20, "id": 2270, "level": 2, "name": "语言地理学"}, {
        "code": 25,
        "id": 2271,
        "level": 2,
        "name": "社会语言学"
    }, {"code": 30, "id": 2272, "level": 2, "name": "心理语言学"}, {
        "code": 35,
        "id": 2273,
        "level": 2,
        "name": "应用语言学"
    }, {"code": 40, "id": 2281, "level": 2, "name": "汉语研究"}, {
        "code": 45,
        "id": 2293,
        "level": 2,
        "name": "中国少数民族语言文字"
    }, {"code": 50, "id": 2308, "level": 2, "name": "外国语言"}, {"code": 99, "id": 2336, "level": 2, "name": "语言学其他学科"}],
    "code": 740,
    "id": 2253,
    "level": 1,
    "name": "语言学"
}, {
    "children": [{"code": 11, "id": 2338, "level": 2, "name": "文学理论"}, {
        "code": 14,
        "id": 2339,
        "level": 2,
        "name": "文艺美学"
    }, {"code": 17, "id": 2340, "level": 2, "name": "文学批评"}, {
        "code": 21,
        "id": 2341,
        "level": 2,
        "name": "比较文学"
    }, {"code": 24, "id": 2342, "level": 2, "name": "中国古代文学史"}, {
        "code": 27,
        "id": 2353,
        "level": 2,
        "name": "中国近代文学史"
    }, {"code": 31, "id": 2354, "level": 2, "name": "中国现代文学史(包括当代文学史)"}, {
        "code": 34,
        "id": 2355,
        "level": 2,
        "name": "中国各体文学"
    }, {"code": 37, "id": 2361, "level": 2, "name": "中国民间文学"}, {
        "code": 41,
        "id": 2362,
        "level": 2,
        "name": "中国儿童文学"
    }, {"code": 44, "id": 2363, "level": 2, "name": "中国少数民族文学"}, {
        "code": 47,
        "id": 2370,
        "level": 2,
        "name": "世界文学史"
    }, {"code": 51, "id": 2376, "level": 2, "name": "东方文学"}, {
        "code": 54,
        "id": 2380,
        "level": 2,
        "name": "俄国文学(包括原苏联文学)"
    }, {"code": 57, "id": 2381, "level": 2, "name": "英国文学"}, {
        "code": 61,
        "id": 2382,
        "level": 2,
        "name": "法国文学"
    }, {"code": 64, "id": 2383, "level": 2, "name": "德国文学"}, {
        "code": 67,
        "id": 2384,
        "level": 2,
        "name": "意大利文学"
    }, {"code": 71, "id": 2385, "level": 2, "name": "美国文学"}, {
        "code": 74,
        "id": 2386,
        "level": 2,
        "name": "北欧文学"
    }, {"code": 77, "id": 2387, "level": 2, "name": "东欧文学"}, {
        "code": 81,
        "id": 2388,
        "level": 2,
        "name": "拉美文学"
    }, {"code": 84, "id": 2389, "level": 2, "name": "非洲文学"}, {
        "code": 87,
        "id": 2390,
        "level": 2,
        "name": "大洋洲文学"
    }, {"code": 99, "id": 2391, "level": 2, "name": "文学其他学科"}], "code": 750, "id": 2337, "level": 1, "name": "文学"
}, {
    "children": [{"code": 10, "id": 2393, "level": 2, "name": "艺术心理学"}, {
        "code": 15,
        "id": 2394,
        "level": 2,
        "name": "音乐"
    }, {"code": 20, "id": 2399, "level": 2, "name": "戏剧"}, {"code": 25, "id": 2403, "level": 2, "name": "戏曲"}, {
        "code": 30,
        "id": 2408,
        "level": 2,
        "name": "舞蹈"
    }, {"code": 35, "id": 2414, "level": 2, "name": "电影"}, {
        "code": 40,
        "id": 2419,
        "level": 2,
        "name": "广播电视文艺"
    }, {"code": 45, "id": 2420, "level": 2, "name": "美术"}, {
        "code": 50,
        "id": 2426,
        "level": 2,
        "name": "工艺美术"
    }, {"code": 55, "id": 2431, "level": 2, "name": "书法"}, {"code": 60, "id": 2435, "level": 2, "name": "摄影"}, {
        "code": 99,
        "id": 2439,
        "level": 2,
        "name": "艺术学其他学科"
    }], "code": 760, "id": 2392, "level": 1, "name": "艺术学"
}, {
    "children": [{"code": 10, "id": 2441, "level": 2, "name": "史学史"}, {
        "code": 15,
        "id": 2442,
        "level": 2,
        "name": "史学理论"
    }, {"code": 20, "id": 2443, "level": 2, "name": "历史文献学"}, {
        "code": 25,
        "id": 2444,
        "level": 2,
        "name": "中国通史"
    }, {"code": 30, "id": 2445, "level": 2, "name": "中国古代史"}, {
        "code": 35,
        "id": 2458,
        "level": 2,
        "name": "中国近代史、现代史"
    }, {"code": 40, "id": 2473, "level": 2, "name": "世界通史"}, {
        "code": 45,
        "id": 2480,
        "level": 2,
        "name": "亚洲史"
    }, {"code": 50, "id": 2488, "level": 2, "name": "非洲史"}, {
        "code": 55,
        "id": 2494,
        "level": 2,
        "name": "美洲史"
    }, {"code": 60, "id": 2500, "level": 2, "name": "欧洲史"}, {
        "code": 65,
        "id": 2510,
        "level": 2,
        "name": "澳洲、大洋洲史"
    }, {"code": 70, "id": 2511, "level": 2, "name": "专门史"}, {"code": 99, "id": 2524, "level": 2, "name": "历史学其他学科"}],
    "code": 770,
    "id": 2440,
    "level": 1,
    "name": "历史学"
}, {
    "children": [{"code": 10, "id": 2526, "level": 2, "name": "考古理论"}, {
        "code": 20,
        "id": 2527,
        "level": 2,
        "name": "考古学史"
    }, {"code": 30, "id": 2528, "level": 2, "name": "考古技术"}, {
        "code": 40,
        "id": 2533,
        "level": 2,
        "name": "中国考古"
    }, {"code": 50, "id": 2541, "level": 2, "name": "外国考古"}, {
        "code": 60,
        "id": 2548,
        "level": 2,
        "name": "专门考古"
    }, {"code": 99, "id": 2557, "level": 2, "name": "考古学其他学科"}], "code": 780, "id": 2525, "level": 1, "name": "考古学"
}, {
    "children": [{"code": 11, "id": 2559, "level": 2, "name": "政治经济学"}, {
        "code": 13,
        "id": 2563,
        "level": 2,
        "name": "宏观经济学"
    }, {"code": 15, "id": 2564, "level": 2, "name": "微观经济学"}, {
        "code": 17,
        "id": 2565,
        "level": 2,
        "name": "比较经济学"
    }, {"code": 19, "id": 2566, "level": 2, "name": "经济地理学(包括工业地理学、农业地理学等)"}, {
        "code": 21,
        "id": 2567,
        "level": 2,
        "name": "发展经济学"
    }, {"code": 23, "id": 2568, "level": 2, "name": "生产力经济学"}, {
        "code": 25,
        "id": 2569,
        "level": 2,
        "name": "经济思想史"
    }, {"code": 27, "id": 2574, "level": 2, "name": "经济史"}, {
        "code": 29,
        "id": 2578,
        "level": 2,
        "name": "世界经济学(亦称国际经济学)"
    }, {"code": 31, "id": 2599, "level": 2, "name": "国民经济学"}, {
        "code": 33,
        "id": 2605,
        "level": 2,
        "name": "管理经济学"
    }, {"code": 35, "id": 2606, "level": 2, "name": "数量经济学"}, {
        "code": 37,
        "id": 2610,
        "level": 2,
        "name": "会计学"
    }, {"code": 39, "id": 2617, "level": 2, "name": "审计学"}, {
        "code": 41,
        "id": 2618,
        "level": 2,
        "name": "技术经济学"
    }, {"code": 43, "id": 2632, "level": 2, "name": "生态经济学"}, {
        "code": 45,
        "id": 2639,
        "level": 2,
        "name": "劳动经济学"
    }, {"code": 47, "id": 2647, "level": 2, "name": "城市经济学"}, {
        "code": 49,
        "id": 2654,
        "level": 2,
        "name": "资源经济学"
    }, {"code": 51, "id": 2661, "level": 2, "name": "环境经济学"}, {
        "code": 53,
        "id": 2662,
        "level": 2,
        "name": "物资经济学"
    }, {"code": 55, "id": 2666, "level": 2, "name": "工业经济学"}, {
        "code": 57,
        "id": 2673,
        "level": 2,
        "name": "农村经济学"
    }, {"code": 59, "id": 2678, "level": 2, "name": "农业经济学"}, {
        "code": 61,
        "id": 2688,
        "level": 2,
        "name": "交通运输经济学"
    }, {"code": 63, "id": 2696, "level": 2, "name": "商业经济学"}, {
        "code": 65,
        "id": 2709,
        "level": 2,
        "name": "价格学"
    }, {"code": 67, "id": 2717, "level": 2, "name": "旅游经济学"}, {
        "code": 69,
        "id": 2723,
        "level": 2,
        "name": "信息经济学"
    }, {"code": 71, "id": 2724, "level": 2, "name": "财政学"}, {
        "code": 73,
        "id": 2734,
        "level": 2,
        "name": "货币银行学"
    }, {"code": 75, "id": 2745, "level": 2, "name": "保险学"}, {
        "code": 77,
        "id": 2748,
        "level": 2,
        "name": "国防经济学"
    }, {"code": 99, "id": 2749, "level": 2, "name": "经济学其他学科"}], "code": 790, "id": 2558, "level": 1, "name": "经济学"
}, {
    "children": [{"code": 10, "id": 2751, "level": 2, "name": "政治学理论"}, {
        "code": 20,
        "id": 2758,
        "level": 2,
        "name": "政治制度"
    }, {"code": 30, "id": 2771, "level": 2, "name": "行政学"}, {
        "code": 40,
        "id": 2778,
        "level": 2,
        "name": "国际政治学"
    }, {"code": 99, "id": 2798, "level": 2, "name": "政治学其他学科"}], "code": 810, "id": 2750, "level": 1, "name": "政治学"
}, {
    "children": [{"code": 10, "id": 2800, "level": 2, "name": "理论法学"}, {
        "code": 20,
        "id": 2810,
        "level": 2,
        "name": "法律史学"
    }, {"code": 30, "id": 2815, "level": 2, "name": "部门法学"}, {
        "code": 40,
        "id": 2833,
        "level": 2,
        "name": "国际法学"
    }, {"code": 99, "id": 2841, "level": 2, "name": "法学其他学科"}], "code": 820, "id": 2799, "level": 1, "name": "法学"
}, {
    "children": [{"code": 10, "id": 2843, "level": 2, "name": "军事理论"}, {
        "code": 15,
        "id": 2847,
        "level": 2,
        "name": "军事史"
    }, {"code": 20, "id": 2855, "level": 2, "name": "军事心理学"}, {
        "code": 25,
        "id": 2856,
        "level": 2,
        "name": "战略学"
    }, {"code": 30, "id": 2860, "level": 2, "name": "战役学"}, {
        "code": 35,
        "id": 2867,
        "level": 2,
        "name": "战术学"
    }, {"code": 40, "id": 2874, "level": 2, "name": "军队指挥学"}, {
        "code": 45,
        "id": 2881,
        "level": 2,
        "name": "军制学"
    }, {"code": 50, "id": 2886, "level": 2, "name": "军队政治工作学"}, {
        "code": 55,
        "id": 2890,
        "level": 2,
        "name": "军事后勤学"
    }, {"code": 60, "id": 2894, "level": 2, "name": "军事地学"}, {
        "code": 65,
        "id": 2902,
        "level": 2,
        "name": "军事技术"
    }, {"code": 99, "id": 2903, "level": 2, "name": "军事学其他学科"}], "code": 830, "id": 2842, "level": 1, "name": "军事学"
}, {
    "children": [{"code": 11, "id": 2905, "level": 2, "name": "社会学史"}, {
        "code": 14,
        "id": 2909,
        "level": 2,
        "name": "社会学理论"
    }, {"code": 17, "id": 2913, "level": 2, "name": "社会学方法"}, {
        "code": 21,
        "id": 2916,
        "level": 2,
        "name": "实验社会学"
    }, {"code": 24, "id": 2917, "level": 2, "name": "数理社会学"}, {
        "code": 27,
        "id": 2918,
        "level": 2,
        "name": "应用社会学"
    }, {"code": 31, "id": 2938, "level": 2, "name": "比较社会学"}, {
        "code": 34,
        "id": 2939,
        "level": 2,
        "name": "社会地理学"
    }, {"code": 37, "id": 2940, "level": 2, "name": "文化社会学"}, {
        "code": 41,
        "id": 2945,
        "level": 2,
        "name": "历史社会学"
    }, {"code": 44, "id": 2946, "level": 2, "name": "经济社会学"}, {
        "code": 47,
        "id": 2947,
        "level": 2,
        "name": "军事社会学"
    }, {"code": 51, "id": 2948, "level": 2, "name": "社会心理学"}, {
        "code": 54,
        "id": 2953,
        "level": 2,
        "name": "公共关系学"
    }, {"code": 57, "id": 2954, "level": 2, "name": "社会人类学"}, {
        "code": 61,
        "id": 2955,
        "level": 2,
        "name": "组织社会学"
    }, {"code": 64, "id": 2956, "level": 2, "name": "发展社会学"}, {
        "code": 67,
        "id": 2957,
        "level": 2,
        "name": "福利社会学"
    }, {"code": 71, "id": 2958, "level": 2, "name": "人口学"}, {"code": 99, "id": 2973, "level": 2, "name": "社会学其他学科"}],
    "code": 840,
    "id": 2904,
    "level": 1,
    "name": "社会学"
}, {
    "children": [{"code": 10, "id": 2975, "level": 2, "name": "民族问题理论"}, {
        "code": 20,
        "id": 2984,
        "level": 2,
        "name": "民族史学"
    }, {"code": 30, "id": 2988, "level": 2, "name": "蒙古学"}, {"code": 40, "id": 2989, "level": 2, "name": "藏学"}, {
        "code": 50,
        "id": 2990,
        "level": 2,
        "name": "文化人类学与民俗学"
    }, {"code": 60, "id": 2991, "level": 2, "name": "世界民族研究"}, {"code": 99, "id": 2992, "level": 2, "name": "民族学其他学科"}],
    "code": 850,
    "id": 2974,
    "level": 1,
    "name": "民族学"
}, {
    "children": [{"code": 10, "id": 2994, "level": 2, "name": "新闻理论"}, {
        "code": 20,
        "id": 3005,
        "level": 2,
        "name": "新闻史"
    }, {"code": 30, "id": 3010, "level": 2, "name": "新闻业务"}, {
        "code": 40,
        "id": 3017,
        "level": 2,
        "name": "新闻事业经营管理"
    }, {"code": 50, "id": 3018, "level": 2, "name": "广播与电视"}, {
        "code": 60,
        "id": 3024,
        "level": 2,
        "name": "传播学"
    }, {"code": 99, "id": 3030, "level": 2, "name": "新闻学与传播学其他学科"}],
    "code": 860,
    "id": 2993,
    "level": 1,
    "name": "新闻学与传播学"
}, {
    "children": [{"code": 10, "id": 3032, "level": 2, "name": "图书馆学"}, {
        "code": 20,
        "id": 3044,
        "level": 2,
        "name": "文献学"
    }, {"code": 30, "id": 3052, "level": 2, "name": "情报学"}, {
        "code": 40,
        "id": 3067,
        "level": 2,
        "name": "档案学"
    }, {"code": 50, "id": 3073, "level": 2, "name": "博物馆学"}, {
        "code": 99,
        "id": 3074,
        "level": 2,
        "name": "图书馆、情报与文献学其他"
    }],
    "code": 870,
    "id": 3031,
    "level": 1,
    "name": "图书馆、情报与文献学"
}, {
    "children": [{"code": 11, "id": 3076, "level": 2, "name": "教育史(包括中国教育史、外国教育史等)"}, {
        "code": 14,
        "id": 3077,
        "level": 2,
        "name": "教育学原理"
    }, {"code": 17, "id": 3078, "level": 2, "name": "教学论"}, {
        "code": 21,
        "id": 3079,
        "level": 2,
        "name": "德育原理"
    }, {"code": 24, "id": 3080, "level": 2, "name": "教育社会学"}, {
        "code": 27,
        "id": 3081,
        "level": 2,
        "name": "教育心理学"
    }, {"code": 31, "id": 3082, "level": 2, "name": "教育经济学"}, {
        "code": 34,
        "id": 3083,
        "level": 2,
        "name": "教育管理学"
    }, {"code": 37, "id": 3084, "level": 2, "name": "比较教育学"}, {
        "code": 41,
        "id": 3085,
        "level": 2,
        "name": "教育技术学"
    }, {"code": 44, "id": 3086, "level": 2, "name": "军事教育学"}, {
        "code": 47,
        "id": 3087,
        "level": 2,
        "name": "学前教育学"
    }, {"code": 51, "id": 3088, "level": 2, "name": "普通教育学(包括初等教育学、中等教育学等)"}, {
        "code": 54,
        "id": 3089,
        "level": 2,
        "name": "高等教育学"
    }, {"code": 57, "id": 3090, "level": 2, "name": "成人教育学"}, {
        "code": 61,
        "id": 3091,
        "level": 2,
        "name": "职业技术教育学"
    }, {"code": 64, "id": 3092, "level": 2, "name": "特殊教育学"}, {"code": 99, "id": 3093, "level": 2, "name": "教育学其他学科"}],
    "code": 880,
    "id": 3075,
    "level": 1,
    "name": "教育学"
}, {
    "children": [{"code": 10, "id": 3095, "level": 2, "name": "体育史"}, {
        "code": 15,
        "id": 3096,
        "level": 2,
        "name": "体育理论"
    }, {"code": 20, "id": 3097, "level": 2, "name": "运动生物力学(包括运动解剖学等)"}, {
        "code": 25,
        "id": 3098,
        "level": 2,
        "name": "运动生理学"
    }, {"code": 30, "id": 3099, "level": 2, "name": "运动心理学"}, {
        "code": 35,
        "id": 3100,
        "level": 2,
        "name": "运动生物化学"
    }, {"code": 40, "id": 3101, "level": 2, "name": "体育保健学"}, {
        "code": 45,
        "id": 3102,
        "level": 2,
        "name": "运动训练学"
    }, {"code": 50, "id": 3103, "level": 2, "name": "体育教育学"}, {
        "code": 55,
        "id": 3104,
        "level": 2,
        "name": "武术理论与方法"
    }, {"code": 60, "id": 3105, "level": 2, "name": "体育管理学"}, {
        "code": 65,
        "id": 3106,
        "level": 2,
        "name": "体育经济学"
    }, {"code": 99, "id": 3107, "level": 2, "name": "体育科学其他学科"}], "code": 890, "id": 3094, "level": 1, "name": "体育科学"
}, {
    "children": [{"code": 10, "id": 3109, "level": 2, "name": "统计学史"}, {
        "code": 15,
        "id": 3110,
        "level": 2,
        "name": "理论统计学"
    }, {"code": 20, "id": 3117, "level": 2, "name": "统计法学"}, {
        "code": 25,
        "id": 3118,
        "level": 2,
        "name": "描述统计学"
    }, {"code": 30, "id": 3119, "level": 2, "name": "经济统计学"}, {
        "code": 35,
        "id": 3123,
        "level": 2,
        "name": "科学技术统计学"
    }, {"code": 40, "id": 3124, "level": 2, "name": "社会统计学"}, {
        "code": 45,
        "id": 3132,
        "level": 2,
        "name": "人口统计学"
    }, {"code": 50, "id": 3133, "level": 2, "name": "环境与生态统计学"}, {
        "code": 55,
        "id": 3138,
        "level": 2,
        "name": "国际统计学"
    }, {"code": 99, "id": 3143, "level": 2, "name": "统计学其他学科"}], "code": 910, "id": 3108, "level": 1, "name": "统计学"
}];