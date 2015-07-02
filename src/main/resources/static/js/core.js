var psyco = {
	// user : (user && user.length > 0) ? JSON.parse(user) : null,
	status : [ '待交换', '交换中', '已交换' ],
	userLevel : [ '菜鸟', '初级会员', '中级会员', '高级会员', '金牌会员' ],
	distinguishStatus : [ '提交鉴别', '鉴别处理中', '鉴别完成' ],
	distinguishType : [ '机器鉴定', '专家鉴定' ],
	exchangeStatus : [ '提交待确认', '交换中', '交换完成' ],
	userLevel : [ '菜鸟', '初级', '中级', '高级', 'VIP' ],
	formatDistinguishType : function(s) {
		return psyco.distinguishType[s];
	},
	formatExchangeStatus : function(s) {
		return psyco.exchangeStatus[s];
	},
	formatUserLevel : function(s) {
		return psyco.exchangeStatus[s];
	},
	formatStatus : function(s) {
		return psyco.status[s];
	},
	formatDistinguishStatus : function(s) {
		return psyco.distinguishStatus[s];
	},
	formatUserLevel : function(s) {
		return psyco.userLevel[s];
	},
	formatCity : function(city) {
		return city ? (city.parent.parent.name + ' ' + city.parent.name + ' ' + city.name) : '未知';
	},
	formatTime : function(millis) {
		return millis > 0 ? moment(millis).format("YYYY/MM/DD-HH:mm:ss") : '';
	},
	formatDate : function(millis) {
		return millis > 0 ? moment(millis).format("YYYY/MM/DD") : '';
	},
	installFormats : function(obj) {
		_.each(psyco, function(method, name) {
			// if (name.startsWith('format'))
			if (name.indexOf('format') == 0)
				obj[name] = method;
		});
	},
	UI_numberComparator : function(a, b) {
		return parseInt(a) - parseInt(b);
	},
	showLogin : function() {
		BootstrapDialog.show({
			// type : BootstrapDialog.TYPE_SUCCESS,
			type : BootstrapDialog.TYPE_DEFAULT,
			title : '提示',
			message : '请先登录到本站',
			buttons : [ {
				label : '登录',
				cssClass : 'btn-success',
				action : function(dialogRef) {
					window.location.href = 'login';
				}
			}, {
				label : '注册',
				cssClass : 'btn-success',
				action : function(dialogRef) {
					window.location.href = 'regist';
				}
			} ]
		});
	},
	alert : function(message, title) {
		BootstrapDialog.show({
			type : BootstrapDialog.TYPE_DEFAULT,
			title : title || '提示',
			message : message
		});
	},

	post : function(config) {
		$.ajax({
			method : 'POST',
			contentType : 'application/json',
			url : config.url,
			data : JSON.stringify(config.data),
			success : config.success
		});
	},
	submitMultiPort : function(form, callback) {
		$.ajax({
			url : form.attr("action"),
			type : form.attr("method"),
			data : new FormData(form[0]),
			mimeType : "multipart/form-data",
			contentType : false,
			cache : false,
			processData : false,
			success : callback
		});
	},
	submit : function(form, callback) {
		$.ajax({
			url : form.attr("action"),
			type : form.attr("method"),
			// data : new FormData(form[0]),
			data : form.serialize(),
			success : callback
		});
	},

	getJson : function(url, data, fn) {
		$.get('/json/' + url, data, fn);
	},
	loadJsonKO : function(url, data, v, callback) {
		this.getJson(url, data, function(d) {
			v(d);
			if (callback)
				callback(d);
		});
	},
	ui : {
		carouselMulti : function(target) {
			target.find('.item').each(function() {
				var next = $(this).next();
				if (!next.length) {
					next = $(this).siblings(':first');
				}
				next.children(':first-child').clone().appendTo($(this));

				for (var i = 0; i < 2; i++) {
					next = next.next();
					if (!next.length) {
						next = $(this).siblings(':first');
					}

					next.children(':first-child').clone().appendTo($(this));
				}
			});
		},
		carouselMultis : function(target, cols) {
			target.find('.item').each(function() {
				var next = $(this).next();
				if (!next.length) {
					next = $(this).siblings(':first');
				}
				next.children(':first-child').clone().appendTo($(this));

				if (cols - 2 > 0)
					for (var i = 0; i < cols - 2; i++) {
						next = next.next();
						if (!next.length) {
							next = $(this).siblings(':first');
						}
						next.children(':first-child').clone().appendTo($(this));
					}
			});
		},
		models : {

			'component-nav' : {
				viewModel : {
					createViewModel : function(params, componentInfo) {
						return (function(params) {
							this.user = ko.observable(user);
						})();
					}
				},
				template : '<div class="navbar-primary"><nav class="navbar navbar-default" style="padding: 0px 100px; margin: 0"><div class="container-fluid"><div class="navbar-header"><button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"><span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></button><a class="navbar-brand" href="index">29教授网店</a></div><div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"><ul class="nav navbar-nav navbar-right"><li data-bind="if:user()==null"><a href="login">登录</a></li><li data-bind="if:user()==null"><a href="regist">注册</a></li><li data-bind="if:user()!=null" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"aria-expanded="false"><span data-bind="text:user().email"></span> <span class="caret"></span></a><ul class="dropdown-menu" role="menu"><li><a href="logout">退出</a></li><li><a href="my" target="blank">我的</a></li></ul></li></ul></div></div></nav></div>'
			},
			'component-logo' : {
				template : '<a href="/index"><img src="../static/img/logo.png" style="width: 300px; height: 130px"></img></a>'
			},
			'component-footer' : {
				template : '<div style="position: relative; bottom: -60px;height:auto"> <div class="footer     " style="position: absolute; bottom: 0; width: 100%;" align="center"> <span><a href="/aboutUs" target="_blank"> 关于我们</a></span> <span><a href="/partner" target="_blank"> 合作伙伴</a></span> <span><a href="/law" target="_blank"> 法律声明</a></span> <span class="muted"> © 2015 29.com 版权所有</span> </div> </div>'
			},
			'component-header-middle' : {
				template : '<div class="row"><div class="navbar-primary"><div class="  navbar-sm col-sm-12" style="margin: 0px; padding: 0px"><nav class="navbar navbar-default " style="border: none; padding: 0px;"><div class="container-fluid"><div class="navbar-header"><a class="navbar-brand" href="/index">首页</a></div><div class="collapse navbar-collapse"><ul class="nav navbar-nav"><li><a  href="/search" target="_blank">产品展示 <span class="sr-only">(current)</span></a></li><li><a href="/news" target="_blank">新闻中心</a></li><li><a href="/businessKnowledge" target="_blank">行业知识</a></li><li><a href="/aboutUs" target="_blank">关于我们</a></li><li><a href="/contactUs" target="_blank">联系我们</a></li></ul></div></div></nav></div></div></div>'
			},
			'component-logo-search' : {
				viewModel : function(params) {
					this.key = ko.observable(params && params.key && params.key() || '');
					this.target = ko.observable(params && params.target || '');
				},
				template : '<div class="row" style="margin-top: 15px;" id="logo-area"> <div class="col-sm-3 no-padding" align="center"> <component-logo></component-logo> </div> <div class="col-sm-2"></div> <div class="col-sm-7 no-padding "> <form role="search" action="search" method="get" data-bind="attr:{target:target}" style="margin-top: 40px;"> <div class="input-group  search-component"> <input type="text" name="key" class="form-control" placeholder="请输入关键字" data-bind="value:key"> <span class="input-group-btn" style="border: none"> <input class="btn btn-default" type="submit" value="搜索"></span> </div> </form> </div> </div>'
			},
			'component-city-chooser-test' : {
				viewModel : {
					createViewModel : function(params, componentInfo) {
						return (function(params) {
							var THIS = this;
							this.provinces = ko.observableArray();
							this.cities = ko.observableArray();
							this.districts = ko.observableArray();
							this.provinceX = ko.observable();
							this.cityX = ko.observable();
							this.districtX = ko.observable();
							var sels = $(componentInfo.element).find('select');
							var proE = $(sels[0]);
							var cityE = $(sels[1]);
							var disE = $(sels[2]);
							console.log(sels);
							this.findProvince = function(provinceID) {
								return _.find(THIS.provinces(), function(p) {
									return p.id == provinceID;
								});
							};
							this.findCity = function(provinceID, cityID) {
								return _.find(THIS.findProvince(provinceID).children, function(c) {
									return c.id == cityID;
								});
							};
							proE.change(function(e) {
								THIS.cities([]);
								THIS.districts([]);
								if (proE.val().length > 0)
									THIS.cities(THIS.findProvince(proE.val()).children);
							});
							cityE.change(function(e) {
								THIS.districts([]);
								if (cityE.val().length > 0)
									THIS.districts(THIS.findCity(proE.val(), cityE.val()).children);
							});
							psyco.loadJsonKO('city/all', null, this.provinces, function(e) {
								THIS.provinces(e);
								if (params && params.select) {
									var s = params.select;
									THIS.cities(THIS.findProvince(s.parent.parent.id).children);
									THIS.districts(THIS.findCity(s.parent.parent.id, s.parent.id).children);
									proE.val(s.parent.parent.id);
									cityE.val(s.parent.id);
									disE.val(s.id);
								}
							});
						})(params);
					}
				},
				template : '<div class="row  "><div class="col-sm-4"><select class="form-control" data-bind="options:provinces, optionsText: \'name\', value:provinceX, optionsValue:\'id\',optionsCaption: \'省\'"></select></div><div class="col-sm-4"><select class="form-control" data-bind="options: cities, optionsText: \'name\',  value:cityX,optionsValue:\'id\',  optionsCaption: \'市\'"></select></div><div class="col-sm-4"><select   class="form-control" name="district" data-bind="options: districts,  optionsText: \'name\', value:districtX, optionsValue:\'id\',optionsCaption: \'区\'" data-error="请选择地区"></select><div class="help-block with-errors"></div></div></div>'
			},
			'component-city-chooser' : {
				viewModel : function(params) {
					var THIS = this;
					this.name = (params && params.name || 'district');
					this.provinces = ko.observableArray(_.isFunction(params.cities) ? params.cities() : params.cities);
					this.cities = ko.observableArray();
					this.districts = ko.observableArray();
					this.provinceX = ko.observable();
					this.cityX = ko.observable();
					this.districtX = ko.observable();
					this.findProvince = function(provinceID) {
						return _.find(THIS.provinces(), function(p) {
							return p.id == provinceID;
						});
					};
					this.findCity = function(provinceID, cityID) {
						return _.find(THIS.findProvince(provinceID).children, function(c) {
							return c.id == cityID;
						});
					};

					this.proChange = function(data, e) {
						var proE = $(e.currentTarget);
						THIS.cities([]);
						THIS.districts([]);
						if (proE.val().length > 0) {
							THIS.provinceX(proE.val());
							THIS.cities(THIS.findProvince(proE.val()).children);
						}
					};
					this.cityChange = function(data, e) {
						var cityE = $(e.currentTarget);
						THIS.districts([]);
						if (cityE.val().length > 0)
							THIS.districts(THIS.findCity(THIS.provinceX(), cityE.val()).children);
					};
					setTimeout(function() {
						if (params && params.select) {
							var s = params.select;
							console.log(THIS.findProvince(s.parent.parent.id).children);
							console.log(THIS.findCity(s.parent.parent.id, s.parent.id).children);
							THIS.cities(THIS.findProvince(s.parent.parent.id).children);
							THIS.districts(THIS.findCity(s.parent.parent.id, s.parent.id).children);
							THIS.provinceX(s.parent.parent.id);
							THIS.cityX(s.parent.id);
							THIS.districtX(s.id);
						}

					}, 500);
					// psyco.loadJsonKO('city/all', null, this.provinces,
					// function(e) {
					// THIS.provinces(e);
					// if (params && params.select) {
					// var s = params.select;
					// THIS.cities(THIS.findProvince(s.parent.parent.id).children);
					// THIS.districts(THIS.findCity(s.parent.parent.id,
					// s.parent.id).children);
					// THIS.provinceX(s.parent.parent.id);
					// THIS.cityX(s.parent.id);
					// THIS.districtX(s.id);
					// }
					// });
				},
				template : '<div class="row  "><div class="col-sm-4"><select class="form-control" data-bind="event: { change:proChange},options:provinces, optionsText: \'name\', value:provinceX, optionsValue:\'id\',optionsCaption: \'省\'"></select></div><div class="col-sm-4"><select class="form-control" data-bind="event: { change:cityChange},options: cities, optionsText: \'name\',  value:cityX,optionsValue:\'id\',  optionsCaption: \'市\'"></select></div><div class="col-sm-4"><select   class="form-control" name="district" data-bind="attr:{name:name},options: districts,  optionsText: \'name\', value:districtX, optionsValue:\'id\',optionsCaption: \'区\'" data-error="请选择地区"></select><div class="help-block with-errors"></div></div></div>'
			},
			'component-type-chooser' : {
				viewModel : function(params) {
					var THIS = this;
					this.types = ko.observable();
					this.typeValue = ko.observable();
					this.title = ko.observable('请选择类别');
					this.findParent = function(data) {
						return _.find(THIS.types(), function(d) {
							return d.id == data.parent;
						});
					};
					this.findByID = function(id) {
						var types = THIS.types();
						for ( var i in types) {
							var re = _.find(types[i].children, function(d) {
								return d.id == id;
							});
							if (re != null) {
								return re;
							}
						}
						return null;
					};
					this.selectTypeFn = function(data, e) {
						THIS.selectType(data, e);
						if (params.callback)
							params.callback(data);
					};
					this.selectType = function(data, e) {
						if (typeof data == 'number') {
							data = data > 0 ? THIS.findByID(data) : null;
						}
						if (!data)
							return;
						THIS.title(THIS.findParent(data).name + '/' + data.name);
						THIS.typeValue(data.id);
					};
					// initialize
					if (params.types) {
						this.types(_.isFunction(params.types) ? params.types() : params.types);
						var select = _.isFunction(params.select) ? params.select() : params.select;
						if (select) {
							console.log(select);
							THIS.selectType(select);
						}
					}
					// else
					// psyco.loadJsonKO('types', null, this.types,
					// function(data) {
					// if (params.select) {
					// THIS.selectType(_.isFunction(params.select) ?
					// params.select() : params.select);
					// }
					// });

				},
				template : '<div class="dropdown"><button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="true"><span data-bind="text:title">选择类别</span> <span class="caret"></span></button><ul class="dropdown-menu" role="menu"  data-bind="foreach:types"><li class="dropdown dropdownTopHover"><a><span data-bind="text:$data.name"></span><span class="caret-right "></span></a><ul class="dropdown-menu" data-bind="foreach:$data.children"><li data-bind="click:$component.selectTypeFn"><a data-bind="text:$data.name"></a></li></ul></li></ul><input type="text" style="display: none" name="type" data-bind="value:typeValue"></div>'
			}
		},
		registComponent : function() {
			_.each(this.models, function(component, name) {
				ko.components.register(name, component);
			});
		}
	}
};
psyco.ui.registComponent();
