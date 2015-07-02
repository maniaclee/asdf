var p4ci = {};
p4ci.VM = function() {
    var THIS = this;
    this.user = ko.observable(user);
    /* XXX item is a built-in variable in IE！！！！ */
    itemPsyco.status = psyco.status[itemPsyco.status];
    itemPsyco.detailPicUrls = JSON.parse(itemPsyco.detailPicUrls);
    this.item = ko.observable(itemPsyco);
    this.isItemInExchange = ko.observable(THIS.item().status == '交换中');
    this.isItemSelf = ko.observable(THIS.user() != null && THIS.item().user.id == THIS.user().id);
    this.isItemLevel = ko.observable(THIS.user() != null && THIS.item().user.level <= THIS.user().level);
    this.canExchange = ko.observable(!THIS.isItemInExchange() && !THIS.isItemSelf() && THIS.isItemLevel());
    this.formatCity = psyco.formatCity;
    this.isFavor = ko.observable(false);
    this.exchangeables = ko.observableArray();
    this.UI_favorAction = function(data, e) {
        console.log(data);
        console.log(e);
        $.get('/favor/' + (THIS.isFavor() ? 'unfavor' : 'favor') + '/' + THIS.item().id, function(data) {
            console.log(data);
            if (data.success) {
                THIS.isFavor(!THIS.isFavor());
                return;
            }
            if (data.type == 'session') {
                window.location.href = '/login';
            }
        });
    };
    var showModal = function(modalID) {
        if (!THIS.user()) {
            psyco.showLogin();
            return;
        }
        $(modalID).modal({
            backdrop : 'static'
        });
    };
    var closeModal = function(modal) {
        $(modal).modal('hide');
    };

    this.UI_distinguish = function() {
        showModal('#distinguishModal');
    };
    this.UI_exchange = function() {
        showModal('#exchangeModal');
    };
    this.UI_distinguish_submit = function(f) {
        psyco.submit($(f), function(data) {
            // if (!data.success)
            psyco.alert(data.data);
            closeModal('#distinguishModal');

        });
        // $(f).submit(function(data) {
        // psyco.alert(data.data);
        // });
    };
    window.UI_table_doExchange = {
        'click .btn' : function(e, value, row, index) {
            var id = $(e.currentTarget).attr('name');
            BootstrapDialog.show({
                type : BootstrapDialog.TYPE_DEFAULT,
                title : '提示',
                message : '确定要交换吗？',
                closable : false,
                buttons : [ {
                    label : '确定',
                    cssClass : 'btn-success',
                    action : function(dialogItself) {
                        $.post('exchange/new', {
                            userID : THIS.user().id,
                            srcID : id,
                            desID : THIS.item().id
                        }, function(data) {
                            if (data.success) {
                                $('#exchangeModal').modal('toggle');
                                dialogItself.close();
                                BootstrapDialog.alert({
                                    title : '提示',
                                    // message : '恭喜您提交成功！',
                                    message : '恭喜您提交成功！' + data.data,
                                    type : BootstrapDialog.TYPE_SUCCESS,
                                    buttonLabel : '确认',
                                    callback : function(result) {
                                        window.location.href = window.location.href;
                                    }
                                });
                            }

                        });
                    }
                }, {
                    label : '取消',
                    action : function(dialogItself) {
                        dialogItself.close();
                    }
                } ]
            });
        }
    };
    psyco.installFormats(this);
    if (user)
        psyco.loadJsonKO('exchange/usable/' + this.user().id, null, this.exchangeables, function() {
            $('.bootstrapTable').bootstrapTable({
                formatSearch : function() {
                    return '查找';
                },
                formatNoMatches : function() {
                    return '没有找到匹配的内容';
                }
            });
        });
};
p4ci.vm = new p4ci.VM();
p4ci.init = function() {

};
$(document).ready(function() {
    ko.applyBindings(p4ci.vm, $('html')[0]);
    p4ci.init();
});
