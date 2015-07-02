var viewModel = {
    error : ko.observable(error),
    regist:function(f){
        f=$(f);
        console.log(f);
    }
};
$(document).ready(function() {
    ko.applyBindings(viewModel);
    $('#registForm input[type=email]').focus();
});
