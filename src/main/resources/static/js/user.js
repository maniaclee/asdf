var p4cu = {
    initPicUpload : function(e) {
        e.fileinput({
            // uploadUrl : 'uploadUrl',
            dropZoneTitle : '预览图片',
            allowedFileTypes : [ 'image' ],
            dropZoneEnabled : false,
            previewFileType : "image",
            maxFileCount : 10,
            overwriteInitial : false,
            browseClass : "btn btn-success ",
            showCaption : false,
            showRemove : false,
            showPreview : true,
            browseLabel : "选择图片(如需多选请一次性选择多幅图片)",
            showUpload : false,
        });
    }

};
p4cu.VM = function() {
    var THIS = this;
    user={"id":3,"name":"root","sex":0,"password":"$2a$11$9Z5VN8mgPq/CD/KiE9Ep2e9uSY1LpGS1ghUbkEJVkroUWZA34YNuW","email":"someone@126.com","phone":null,"level":4,"imageUrl":"","imageThumbUrl":null,"city":{"id":762,"name":"龙门县","type":3,"parent":{"id":82,"name":"惠州","type":2,"parent":{"id":6,"name":"广东","type":1,"parent":{"id":1,"name":"中国","type":0,"parent":null,"children":null},"children":null},"children":null},"children":null},"location":null,"role":"ROLE_USER","enabled":true};
    this.ANTIQUE_EMPTY = '{"id":0,"title":null,"imageUrl":null,"imageThumbUrl":null,"breifInfo":null,"user":null,"type":null,"price":0.0,"priceReference":0.0,"detailPicUrls":null,"location":null,"status":0,"age":null,"integrity":null,"dateUpload":null,"dateLastModify":null,"hight":0.0,"width":0.0,"length":0.0}';
    this.antiqueEdit = ko.observable(JSON.parse(this.ANTIQUE_EMPTY));
    user.sex += '';
    this.userOriginal = JSON.stringify(user);
    this.user = ko.observable(user);
    this.ages = ko.observableArray();
    this.types = ko.observableArray();
    /*application data*/
    this.AGE_ALL=ko.observableArray(null);
    this.TYPETAG_ALL=ko.observableArray(null);
    this.CITY_ALL=ko.observableArray(null);
    // this.save = function(fd) {
    // var u = THIS.user();
    // console.log(u);
    // psyco.post({
    // url : 'upload/profile',
    // data : u,
    // success : function(d) {
    // window.location.href = "/my";
    // }
    // });
    // };
    /* install formats from psyco */
    psyco.installFormats(this);
    this.reset = function() {
        THIS.user(JSON.parse(THIS.userOriginal));
    };
    this.myPublish = ko.observableArray();
    this.myFavor = ko.observableArray();
    this.myDistinguish = ko.observableArray();
    this.myExchange = ko.observableArray();
    this.uploadItem = function(form) {
        psyco.submitMultiPort($(form), function(data) {
            BootstrapDialog.show({
                type : BootstrapDialog.TYPE_SUCCESS,
                title : '提示',
                message : '上传成功',
                onhidden : function() {
                    window.location.href = window.location.href;
                }
            });
        });
    };
    this.openUserUpgradeModal = function() {
        $('#userUpgradeModal').modal({
            backdrop : 'static'
        });
    };
    this.userUpgrade = function() {
        $.get('/userUpgrade', function(data) {
            alert(data.data);
            $('#userUpgradeModal').modal('hide');
        });
    };
    this.itemToModify = ko.observable();
    this.UI_modifyItem = function(model, e) {
        var item = JSON.parse(JSON.stringify(model));
        item.detailPicUrls = JSON.parse(item.detailPicUrls);
        THIS.itemToModify(item);
        console.log(THIS.itemToModify());
        p4cu.initPicUpload($("#itemModityAddDetailPic"));
        $('.cropme').simpleCropper(function(url, el) {

        });
        $('#itemEditModal').modal({
            backdrop : 'static'
        });
    };
    this.itemModify_removeDetailPic = function(model, e) {
        var btn = $(e.currentTarget);
        console.log($(e.currentTarget));
        console.log(model);
        btn.parent().hide();

    };
    this.modifyItemSubmit = function(form) {
        var item = THIS.itemToModify();
        // console.log(item);
        form = $(form);
        console.log(form.serialize());
        // psyco.submitMultiPort($(form), function(data) {
        // BootstrapDialog.show({
        // type : BootstrapDialog.TYPE_SUCCESS,
        // title : '提示',
        // message : '上传成功',
        // onhidden : function() {
        // window.location.href = window.location.href;
        // }
        // });
        // });
    };
    this.removeItem = function(model, e) {
        bootbox.confirm("确定要删除吗?" ,function(result) {
            if(!result)
                return;
            $.get('/delete/item/' + model.id, function(data) {
                if (data.success) {
                    BootstrapDialog.show({
                        type : BootstrapDialog.TYPE_SUCCESS,
                        title : '提示',
                        message : '删除成功',
                        onhidden : function() {
                            window.location.href = window.location.href;
                        }
                    });
                } else {
                    BootstrapDialog.show({
                        type : BootstrapDialog.TYPE_DANGER,
                        title : '提示',
                        message : '删除失败:' + data.data,
                        onhidden : function() {
                        }
                    });
                }
            });
        }); 
    };
    // $.get('/api/items/'+model.id,function(data){console.log(data)});
//    psyco.loadJsonKO('types', null, this.types);
    psyco.loadJsonKO('favors/' + user.id, null, this.myFavor);
    psyco.loadJsonKO('myPublishedItems/' + user.id, null, this.myPublish);
    psyco.loadJsonKO('exchange/user/' + user.id, null, this.myExchange);
//    psyco.loadJsonKO('ages', null, this.ages);
    psyco.loadJsonKO('distinguish/user/' + THIS.user().id, null, this.myDistinguish);
};
p4cu.vm = new p4cu.VM();

p4cu.init = function() {
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
    // $('#cropme-profile-image-upload').simpleCropper(function(url,el) {
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
        } else if (id == 'cropme-profile-image-item-modify') {

        }
    }, 200, 200);

    p4cu.initPicUpload($("#input-702"));

    // $('#my-nav a:first-child').trigger('click');
    $('#my-nav a:first-child').trigger('click');
};
$(document).ready(function() {
    ko.applyBindings(p4cu.vm,$('html')[0]);
    p4cu.init();
    bootbox.setDefaults({
        locale:'zh_CN',
    });
});
