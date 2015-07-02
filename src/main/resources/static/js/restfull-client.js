var RestClient = function(rootPath) {
    var format = function(arg) {
        arg[0] = rootPath + arg[0];
        return arg;
    };
    this.get = function() {
        return $.get.apply($, format(arguments));
    };
    this.post = function(url, data, callback, type) {
        if ($.isFunction(data)) {
            type = type || callback, callback = data, data = {};
        }
        return $.ajax({
            method : 'POST',
            contentType : 'application/json',
            url : rootPath + url,
            data : JSON.stringify(data),
            success : callback,
        });
    };
    this.put = function(url, data, callback, type) {
        if ($.isFunction(data)) {
            type = type || callback, callback = data, data = {};
        }
        return $.ajax({
            url : rootPath + url,
            type : 'PUT',
            success : callback,
            data : JSON.stringify(data),
            // dataType : "json",
            contentType : type || 'application/json'
        });
    };
    this.DELETE = function(url, data, callback, type) {

        if ($.isFunction(data)) {
            type = type || callback, callback = data, data = {};
        }
        return $.ajax({
            url : rootPath + url,
            type : 'DELETE',
            success : callback,
            data : data,
            contentType : type
        });
    };

};
