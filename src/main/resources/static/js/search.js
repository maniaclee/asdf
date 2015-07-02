var p4cs = {};
p4cs.VM = function() {
    var THIS = this;
    this.conditions = ko.observable(conditions);
    this.itemsResult = ko.observable(result);
    this.user = ko.observable(user);
    this.types = ko.observableArray([]);
    this.setPagination = function() {
        var page = THIS.itemsResult();
        if (page.totalPages > 0)
            $('#pagination').twbsPagination({
                totalPages : page.totalPages,
                visiblePages : 7,
                startPage : page.number + 1 > page.totalPages ? 1 : (page.number + 1),
                first : '<<',
                prev : '<',
                next : '>',
                last : '>>',
                onPageClick : function(event, page) {
                    var pm = THIS.getSearchCondition();
                    pm.page = page - 1;
                    window.location.href = 'search?' + $.param(pm);
                }
            });
    };
    this.search = function() {
        console.log($.param(THIS.getSearchCondition()));
        window.location.href = 'search?' + $.param(THIS.getSearchCondition());
    };
    this.searchByType = function(type) {
        $.get('json/search/type/' + type, null, THIS.setPagination);
    };

    this.getSearchCondition = function() {
        var re = {
            'key' : THIS.conditions().key,
            'sort' : $('.sortGroup .active >a').first().attr('name'),
            'asc' : $('.sortGroup .active >a').first().attr('asc') || 'true',
            'onlyShowExchange' : $('#onlyShowExchange').is(":checked")
        };
        _.each($('#searchSelectForm').serializeArray(), function(data) {
            if (data.value.trim().length > 0)
                re[data.name] = data.value;
        });
        return re;
    };
};
p4cs.vm = new p4cs.VM();
p4cs.init = function() {
    p4cs.vm.setPagination();
    $('.sortGroup li a[name="@"]'.replace('@', p4cs.vm.conditions().sort)).parent().addClass('active');
};

$(document).ready(function() {
    ko.applyBindings(p4cs.vm, $('html')[0]);
    p4cs.init();
    $('.sortGroup >li').click(function(e) {
        $('.sortGroup >li').removeClass('active');
        console.log(e);
        e = $(e.currentTarget);
        e.addClass('active');
        p4cs.vm.search();
    });
});
