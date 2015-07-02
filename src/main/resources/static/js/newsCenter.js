var p4cu = {};
p4cu.VM = function() {
    var temp = [ {
        title : '乾隆青花宝相花八吉祥纹兽耳尊赏析',
        date : ''
    }, {
        title : '回顾中国瓷器文明传入欧洲',
        date : ''
    }, {
        title : '什么样的国画作品更值得收藏  ',
        date : ''
    }, {
        title : '被市场关注的当代水墨     ',
        date : ''
    } ];
    temp = temp.concat(temp).concat(temp).concat(temp).concat(temp).concat(temp);
    this.articles = ko.observableArray(temp);
};

p4cu.init = function() {
};
$(document).ready(function() {
    ko.applyBindings(new p4cu.VM());
    p4cu.init();
});
