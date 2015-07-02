var p4cuAdmin = {};
p4cuAdmin.VM = function() {
    var THIS = this;
    this.ANTIQUE_EMPTY = '{"id":0,"title":null,"imageUrl":null,"imageThumbUrl":null,"breifInfo":null,"user":null,"type":null,"price":0.0,"priceReference":0.0,"detailPicUrls":null,"location":null,"status":0,"age":null,"integrity":null,"dateUpload":null,"dateLastModify":null,"hight":0.0,"width":0.0,"length":0.0}';
    this.antiqueEdit = ko.observable(JSON.parse(this.ANTIQUE_EMPTY));
    user.sex += '';
    this.userOriginal = JSON.stringify(user);
    this.user = ko.observable(user);
    this.ages = ko.observableArray();
    this.types = ko.observableArray();
    this.distinguishType = psyco.distinguishType;
    this.distinguishTypes = ko.observableArray(psyco.distinguishStatus);
    this.exchangeStatus = ko.observableArray(psyco.exchangeStatus);
    this.reset = function() {
        THIS.user(JSON.parse(THIS.userOriginal));
    };
    this.myPublish = ko.observableArray();
    this.myFavor = ko.observableArray();
    this.myDistinguish = ko.observableArray();
    this.myExchange = ko.observableArray();
    psyco.installFormats(this);
    psyco.loadJsonKO('types', null, this.types);
    psyco.loadJsonKO('favors/' + user.id, null, this.myFavor);
    psyco.loadJsonKO('ages', null, this.ages);
    psyco.loadJsonKO('distinguish/status/3', null, this.myDistinguish);
    psyco.loadJsonKO('exchange/status/3', null, this.myExchange);
    this.itemToModify = ko.observable();
    this.itemToModify_exchange = ko.observable();
    this.UI_clickModifyStatus = function(model, a) {
        console.log(model);
        THIS.itemToModify(model);
        $('#modifyStatusModal').modal('show');
    };
    this.UI_modifyStatus = function(f) {
        f = $(f);
        console.log(f.serialize());
        psyco.submit(f, function(data) {
            console.log(data);
            if (!data.success) {
                psyco.alert(data.data, '错误');
                $('#modifyStatusModal').modal('hide');
            }
            window.location.href = window.location.href;
        });
    };
    this.UI_clickModifyStatus_exchange = function(model, a) {
        console.log(model);
        THIS.itemToModify_exchange(model);
        $('#modifyStatusExchangeModal').modal('show');
    };
    this.UI_modifyStatus_exchange = function(f) {
        f = $(f);
        console.log(f.serialize());
        psyco.submit(f, function(data) {
            console.log(data);
            if (!data.success) {
                psyco.alert(data.data, '错误');
                $('#modifyStatusExchangeModal').modal('hide');
                return;
            }
            window.location.href = window.location.href;
        });
    };
};
p4cuAdmin.vm = new p4cuAdmin.VM();

p4cuAdmin.init = function() {
    $('#settings-content >.row').hide();
    $('#my-nav a').each(function(i, a) {
        a = $(a);
        a.click(function(e) {
            e.preventDefault();
            $('#my-nav a').removeClass('active');
            a.addClass('active');
            $('#settings-content >.row').hide();
            $(a.attr('href')).show();
        });
    });
    $('.cropme').simpleCropper(function(url, el) {
        var id = el.attr('id');
        if (id == 'cropme-profile-image-upload') {
            $.post('/upload/profile/image', {
                imageUrl : url
            }, function() {
                window.location.href = "my";
            });
        } else if (id == 'itemImageTitle') {
            var input = el.siblings('input').first();
            input.val(url);
        }
    }, 230, 230);
    $('#my-nav a:first-child').trigger('click');
};
$(document).ready(function() {
    ko.applyBindings(p4cuAdmin.vm);
    p4cuAdmin.init();
});
