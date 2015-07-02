var p4cModify = {
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
            browseLabel : "添加图片(可多选)",
            showUpload : false,
        });
    }

};

p4cModify.VM = function() {
    var THIS = this;
    itemPsyco.detailPicUrls = JSON.parse(itemPsyco.detailPicUrls);
    this.itemToModify = ko.observable(JSON.parse(JSON.stringify(itemPsyco)));
    user.sex += '';
    this.userOriginal = JSON.stringify(user);
    this.user = ko.observable(user);
    this.ages = ko.observableArray(agesPsyco);
    this.types = ko.observableArray();
    psyco.installFormats(this);
    this.resetForm = function() {
        THIS.itemToModify(JSON.parse(JSON.stringify(itemPsyco)));
        p4cModify.initPicUpload($("#itemModityAddDetailPic"));
    };

    this.UI_modifyItem = function(model, e) {
        var item = JSON.parse(JSON.stringify(model));
        item.detailPicUrls = JSON.parse(item.detailPicUrls);
        THIS.itemToModify(item);
        console.log(THIS.itemToModify());
        p4cModify.initPicUpload($("#itemModityAddDetailPic"));
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
        btn.siblings().first().prop('disabled', true);
        btn.parent().hide();

    };
    this.modifyItemSubmit = function(form) {
        // console.log(item);
        form = $(form);
        console.log(form.serialize());
        psyco.submitMultiPort($(form), function(data) {
            BootstrapDialog.show({
                type : BootstrapDialog.TYPE_SUCCESS,
                title : '提示',
                message : '保存成功',
                onhidden : function() {
                    window.location.href = window.location.href;
                }
            });
        });
    };
    // $.get('/api/items/'+model.id,function(data){console.log(data)});
    psyco.loadJsonKO('types', null, this.types);
};
p4cModify.vm = new p4cModify.VM();

p4cModify.init = function() {
    $('.cropme').simpleCropper(function(url, el) {
        var id = el.attr('id');
        if (id == 'cropme-profile-image-item-modify') {
            var input = $('#imageTitle');
            input.val(url);
        }
    }, 200, 200);

    p4cModify.initPicUpload($("#itemModityAddDetailPic"));
};
$(document).ready(function() {
    ko.applyBindings(p4cModify.vm,$('html')[0]);
    p4cModify.init();
    bootbox.setDefaults({
        locale:'zh_CN',
    });
});
