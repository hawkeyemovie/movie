! function(e, t) {
	"object" == typeof exports && "object" == typeof module ? module.exports = t() : "function" == typeof define && define.amd ? define([], t) : "object" == typeof exports ? exports.Owl = t() : e.Owl = t()
}(this, function() {
	return function(e) {
		function t(r) {
			if(n[r]) return n[r].exports;
			var i = n[r] = {
				exports: {},
				id: r,
				loaded: !1
			};
			return e[r].call(i.exports, i, i.exports, t), i.loaded = !0, i.exports
		}
		var n = {};
		return t.m = e, t.c = n, t.p = "", t(0)
	}([function(e, t, n) {
		"use strict";

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}
		var i = function() {
				function e(e, t) {
					for(var n = 0; n < t.length; n++) {
						var r = t[n];
						r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
					}
				}
				return function(t, n, r) {
					return n && e(t.prototype, n), r && e(t, r), t
				}
			}(),
			o = n(1),
			a = n(9),
			s = n(11),
			c = n(14),
			u = n(19),
			f = n(21),
			g = n(24),
			h = n(25),
			l = n(17),
			p = n(26),
			d = n(27),
			v = n(28),
			y = n(29),
			m = n(7).version,
			w = n(13),
			b = n(6),
			k = n(16),
			M = n(31),
			E = function() {
				function e(t, n) {
					r(this, e);
					var i = new o(t);
					this.errManager = new c(i, this), this.pageManager = new f(i), this.resManager = new u(i, this.errManager), this.metricManager = new g(i), this.logManager = new h(i), this.cfgManager = i, this.init(), n && n.noFilter || a(this.cfgManager)
				}
				return i(e, [{
					key: "init",
					value: function() {
						this.cfgManager.setExtension(y.getExt())
					}
				}, {
					key: "config",
					value: function(e) {
						this.cfgManager.set(e)
					}
				}, {
					key: "addError",
					value: function(e, t) {
						t && t.combo === !1 ? this.errManager.report(e, t) : this.cfgManager.get("error").combo === !1 ? this.errManager.report(e, t) : this.errManager.push(e, t)
					}
				}, {
					key: "sendErrors",
					value: function() {
						this.errManager.report()
					}
				}, {
					key: "addPoint",
					value: function() {
						var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {},
							t = this.pageManager.getPerformaceTiming(),
							n = t.perf,
							r = void 0;
						if(void 0 !== e.duration) r = e;
						else if(void 0 !== n) {
							var i = e.timeStamp || +new Date;
							r = {
								position: e.position,
								duration: i - n.navigationStart
							}
						}
						r && (this.pageManager.setUserReady(), this.pageManager.push(r), this.pageManager.report())
					}
				}, {
					key: "sendPoints",
					value: function() {
						this.pageManager.report(!0)
					}
				}, {
					key: "addApi",
					value: function(e) {
						if(e) {
							if(void 0 !== e.networkCode && "number" != typeof e.networkCode) return b.ignore("网络状态码必须为Number类型", JSON.stringify(e));
							if(void 0 !== e.statusCode && "number" != typeof e.statusCode) return b.ignore("业务状态码必须为Number类型", JSON.stringify(e));
							var t = {
								type: "api",
								connectType: e.connectType || this.cfgManager.setConnectType(),
								resourceUrl: e.name,
								statusCode: (e.networkCode || "") + "|" + (e.statusCode || ""),
								responsetime: e.responseTime && e.responseTime.toString() ? e.responseTime.toString() : "0"
							};
							e.content && (t.firstCategory = w.AJAX, t.secondCategory = e.secondCategory || e.name, t.logContent = e.content), this.resManager.pushApi(t)
						}
					}
				}, {
					key: "reportApi",
					value: function() {
						this.addApi.apply(this, arguments)
					}
				}, {
					key: "sendApis",
					value: function() {
						this.resManager.report()
					}
				}, {
					key: "addLog",
					value: function(e) {
						this.logManager.addLog(e)
					}
				}, {
					key: "createLog",
					value: function() {
						var e = this.cfgManager,
							t = new h(e);
						return t
					}
				}, {
					key: "updateFilter",
					value: function(e, t) {
						void 0 === t ? this.cfgManager.removeFilter(e) : this.cfgManager.addFilter(e, t)
					}
				}, {
					key: "wrap",
					value: function(e, t, n) {
						if("function" != typeof e) return e;
						try {
							if(e.__owl_wrapped__) return e;
							if(e.__owl_wrapper__) return e.__owl_wrapper__
						} catch(t) {
							return e
						}
						var r = function() {
							try {
								return e.apply(t, arguments)
							} catch(e) {
								T.addError(e, n)
							}
						};
						for(var i in e) e.prototype.hasOwnProperty(i) && (r[i] = e[i]);
						return r.prototype = e.prototype, e.__owl_wrapper__ = r, r.__owl_wrapper__ = !0, r
					}
				}, {
					key: "setDimension",
					value: function(e) {
						this.cfgManager.setExtension(e)
					}
				}]), e
			}(),
			T = new E({}, {
				noFilter: !0
			});
		T.OWL = E, T.__version__ = m, T.errorModel = s, T.MetricManager = function() {
			var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : T.cfgManager,
				t = new g(e);
			return t
		}, T.start = function(e) {
			var t = k.getUserAgent(),
				r = !1;
			if(M.forEach(function(e) {
					t.indexOf(e) !== -1 && (r = !0, b.ignore("当前环境被判断为爬虫，监控功能关闭"))
				}), !r && !this.isStarted) {
				if(this.isStarted = !0, T.cfgManager.set({
						pageUrl: k.getHref()
					}), e && (e && e.resource && e.resource.sample && (e.resource.sample = void 0), T.cfgManager.set(e), T.cfgManager.checkUpdate()), this.cfgManager.get("logan").enable) {
					var i = this.cfgManager.get("logan").Logan,
						o = this.cfgManager.get("project"),
						s = this.cfgManager.get("pageUrl"),
						c = this.cfgManager.get("logan").config;
					l.ready({
						LoganAPI: i,
						project: o,
						pageUrl: s,
						loganConfig: c
					})
				}
				a(T.cfgManager), v(T.pageManager), p(T.errManager), d(T.resManager, T.errManager);
				var u = e.preLoadName || "_Owl_";
				if(window[u]) {
					window[u].isReady = !0;
					var f = window[u],
						g = f.config,
						h = f.preTasks,
						y = f.dataSet;
					T.cfgManager.set({
						autoCatch: g
					}), h && h.length && (h.forEach(function(e) {
						T[e.api].apply(T, e.data)
					}), window[u].preTasks = []), setTimeout(function() {
						y && y.length && y.forEach(function(e) {
							e && "jsError" === e.type ? T.errManager.parseWindowError.apply(T.errManager, e.data) : "resError" === e.type ? T.resManager.handleResourceLoadError.apply(T.resManager, e.data) : "resTime" === e.type ? T.resManager.handleResourceTiming.apply(T.resManager, e.data) : "pageTime" === e.type && T.pageManager.parsePageTime.apply(T.pageManager, e.data)
						}), window[u].dataSet = []
					}, 0)
				}
				var m = T.cfgManager.get("isSPA");
				if(m) {
					var w = window.onpopstate;
					window.onpopstate = function(e) {
						T.cfgManager.set({
							pageUrl: k.getHref()
						});
						var t = T.cfgManager.get("onpopstate");
						try {
							t(e)
						} catch(e) {
							b.ignore(e)
						}
						w && w.apply(window, e)
					}
				}
				T.errManager.checkCache();
				var E = n(32);
				E("owl_sdk", T)
			}
		}, e.exports = T
	}, function(e, t, n) {
		"use strict";

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}
		var i = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
				return typeof e
			} : function(e) {
				return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
			},
			o = function() {
				function e(e, t) {
					for(var n = 0; n < t.length; n++) {
						var r = t[n];
						r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
					}
				}
				return function(t, n, r) {
					return n && e(t.prototype, n), r && e(t, r), t
				}
			}(),
			a = n(2),
			s = n(3)(),
			c = n(4),
			u = n(6),
			f = n(7).version,
			g = n(8),
			h = "owl_config",
			l = 36e5,
			p = ["region", "operator", "network", "container", "os", "unionId"],
			d = window.location.protocol ? window.location.protocol : "http:";
		d = new RegExp("http").test(d) ? d : "http:";
		var v = d + "//catfront.dianping.com",
			y = d + "//catfront.51ping.com",
			m = function() {
				function e(t) {
					r(this, e), this.url = v, this.filters = [], this._config = {
						devMode: !1,
						project: "",
						pageUrl: "",
						logan: {
							enable: !1
						},
						disabledFilters: [],
						noScriptError: !0,
						ignoreList: {
							js: [],
							ajax: ["https?://report.meituan.com/"],
							resource: ["https?://hls.dianping.com/", "https?://frep.meituan.net/"],
							attr: "owl-ignore"
						},
						resourceReg: /(.51ping|.dianping|.dpfile|.meituan|.sankuai|.maoyan|.kuxun)/,
						disableCache: !1,
						invalid: {
							ajax: !0
						},
						autoCatch: {
							page: !0,
							ajax: !0,
							js: !0,
							resource: !0,
							console: !1
						},
						ajax: {
							flag: !1,
							duration: 2e3
						},
						image: {
							flag: !1,
							duration: 5e3,
							fileSize: 100,
							filter: function() {
								return !1
							},
							pattern: /(\.)(png|jpg|jpeg|gif|webp|ico|bmp|tiff|svg)/i
						},
						resource: {
							sample: .1,
							sampleApi: .1,
							combo: !0,
							delay: 1e3
						},
						page: {
							auto: !0,
							sample: .5,
							delay: 0,
							timeThreshold: 5,
							mainResourceNumber: 5,
							sensoryIndex: !0,
							disableSensoryImageIndex: !1
						},
						error: {
							sample: 1,
							delay: 1e3,
							combo: !1,
							maxSize: 10240,
							maxNum: 100,
							maxTime: 6e4
						},
						metric: {
							sample: .5,
							combo: !0,
							delay: 1500
						}
					}, this.baseQuery = {
						v: 1,
						sdk: f
					}, this.apiPaths = {
						log: "/api/log",
						error: "/api/log",
						page: "/api/speed",
						resource: "/batch",
						metric: "/api/metric"
					}, this.extensions = {}, this.userConfig = {}, this.config = {}, t ? this.set(t) : this.config = this._config
				}
				return o(e, [{
					key: "removeFilter",
					value: function(e) {
						for(var t = 0; t < this.filters.length; t++) {
							var n = this.filters[t];
							if(n.key === e) return void this.filters.splice(t, 1)
						}
					}
				}, {
					key: "addFilter",
					value: function(e, t) {
						if(e && t instanceof Function && this.config.disabledFilters && this.config.disabledFilters.indexOf(e) === -1) {
							for(var n = -1, r = 0; r < this.filters.length; r++) {
								var i = this.filters[r];
								i.key === e && (n = r, i.fn = t)
							}
							n === -1 && this.filters.push({
								key: e,
								fn: t
							})
						}
					}
				}, {
					key: "get",
					value: function(e) {
						return e ? this.config[e] : this.config
					}
				}, {
					key: "set",
					value: function(e) {
						for(var t in e)
							if(e.hasOwnProperty(t)) {
								if("devMode" === t && this.setApiDomain(e[t]), "ext" === t && this.setExtension(e[t]), "resourceReg" === t) try {
									this.userConfig[t] = new RegExp(e[t])
								} catch(e) {
									u.ignore(e)
								}
								"object" !== i(e[t]) || e[t] instanceof RegExp || e[t] instanceof Array ? this.userConfig[t] = e[t] : this.userConfig[t] = a(this.userConfig[t], e[t])
							}
						this.update()
					}
				}, {
					key: "setApiDomain",
					value: function(e) {
						e ? this.url = y : this.url = v
					}
				}, {
					key: "getApiDomain",
					value: function() {
						return this.url
					}
				}, {
					key: "getExtension",
					value: function(e) {
						return e ? this.extensions[e] : this.extensions
					}
				}, {
					key: "setExtension",
					value: function(e) {
						for(var t in e)
							if(e.hasOwnProperty(t)) {
								var n = e[t];
								"latlng" === t ? (this.extensions.region = n, this.baseQuery.useLatlng = !0) : p.indexOf(t) !== -1 && (this.extensions[t] = n)
							}
					}
				}, {
					key: "getApiPath",
					value: function(e) {
						var t = this.apiPaths[e];
						return g.stringify(this.url + t, this.baseQuery)
					}
				}, {
					key: "update",
					value: function() {
						this.config = this._config;
						for(var e in this.userConfig) {
							var t = this.userConfig[e];
							"object" != ("undefined" == typeof t ? "undefined" : i(t)) || t instanceof RegExp || t instanceof Array ? this.config[e] = t : this.config[e] = a(this.config[e], this.userConfig[e])
						}
					}
				}, {
					key: "reset",
					value: function() {
						this.config = this._config
					}
				}, {
					key: "_setDefault",
					value: function(e) {
						if(e) {
							e.resourceReg && (e.resourceReg = new RegExp(e.resourceReg));
							for(var t in e)
								if(e.hasOwnProperty(t)) {
									var n = e[t];
									"object" != ("undefined" == typeof n ? "undefined" : i(n)) || n instanceof RegExp || n instanceof Array ? this._config[t] = n : this._config[t] = a(this.config[t], n)
								}
							this.update()
						}
					}
				}, {
					key: "checkUpdate",
					value: function() {
						var e = this;
						try {
							var t = this.initFromCache();
							if(t) {
								var n = t.delay || l; + new Date - t.ts > n || 0 === Object.keys(t).length ? c({
									url: this.url + "/api/config",
									success: function(t) {
										t ? (e.updateCache(t.config, t.md5, t.delay), e._setDefault(t.config)) : e.clearCache()
									},
									fail: function() {
										e._setDefault(t.config)
									}
								}) : this._setDefault(t.config)
							}
						} catch(e) {
							u.ignore(e)
						}
					}
				}, {
					key: "updateCache",
					value: function(e, t, n) {
						if(e) {
							var r = {
								config: e,
								ts: +new Date,
								md5: t,
								delay: n || l
							};
							if(s) try {
								window.localStorage.setItem(h, JSON.stringify(r))
							} catch(e) {
								u.ignore(e)
							}
						}
					}
				}, {
					key: "clearCache",
					value: function() {
						if(s) try {
							window.localStorage.removeItem(h)
						} catch(e) {
							u.ignore(e)
						}
					}
				}, {
					key: "initFromCache",
					value: function() {
						var e = void 0;
						if(s) try {
							e = window.localStorage.getItem(h), e = e ? JSON.parse(e) : {}
						} catch(t) {
							e = {}, u.ignore(t)
						}
						return e
					}
				}, {
					key: "setConnectType",
					value: function() {
						return d.indexOf("https") !== -1 ? "https" : "http"
					}
				}]), e
			}();
		e.exports = m
	}, function(e, t) {
		"use strict";
		e.exports = function(e, t) {
			var n = {},
				r = void 0;
			for(r in e) n[r] = e[r];
			for(r in t) t.hasOwnProperty(r) && void 0 !== t[r] && (n[r] = t[r]);
			return n
		}
	}, function(e, t) {
		"use strict";
		e.exports = function() {
			var e = "local_test";
			try {
				localStorage.setItem(e, 1), localStorage.removeItem(e)
			} catch(e) {
				return !1
			}
			return !0
		}
	}, function(e, t, n) {
		"use strict";
		var r = window,
			i = r.XMLHttpRequest,
			o = n(5),
			a = n(6),
			s = function() {},
			c = function() {
				if(i && !("_owl" in i)) {
					i._owl = !0;
					var e = r.location.protocol;
					if("file:" !== e) {
						var t = i.prototype.open,
							n = i.prototype.send;
						i.prototype.open = function(e, n) {
							return this.url = n, this._startTime = +new Date, t.apply(this, arguments)
						}, i.prototype.send = function() {
							var e = this,
								t = "addEventListener",
								r = "onreadystatechange",
								i = function(t) {
									if(t) {
										var n = +new Date - e._startTime;
										if(t.duration = n, /catfront.(dianping|51ping).com/.test(e.url)) {
											var r = void 0;
											try {
												e.getAllResponseHeaders("content-type").indexOf("application/json") !== -1 && (r = t.currentTarget.response, r = JSON.parse(r))
											} catch(e) {
												a.ignore(e)
											}
											200 === t.currentTarget.status ? e.success && e.success(r) : e.fail && e.fail(r)
										} else o.trigger("ajaxCall", t)
									}
								};
							if(t in this) this[t]("load", i), this[t]("error", i), this[t]("abort", i);
							else {
								var s = this[r];
								this[r] = function(e) {
									4 === this.readyState && i(e), s && s.apply(this, arguments)
								}
							}
							return n.apply(this, arguments)
						}
					}
				}
			};
		c(), e.exports = function(e) {
			if(e) {
				var t = window.navigator.userAgent,
					n = window.navigator.appName,
					r = n.indexOf("Microsoft Internet Explorer") !== -1 && (t.indexOf("MSIE 8.0") !== -1 || t.indexOf("MSIE 9.0") !== -1),
					i = r && window.XDomainRequest,
					o = void 0;
				if(o = i ? new XDomainRequest : new XMLHttpRequest, o.open(e.type || "GET", e.url, !0), o.success = e.success || s, o.fail = e.fail || s, "POST" === e.type) {
					if(e.header && !i)
						for(var a in e.header) e.header.hasOwnProperty(a) && o.setRequestHeader(a, e.header[a]);
					o.send(e.data)
				} else o.send();
				return o
			}
		}
	}, function(e, t) {
		"use strict";
		e.exports = {
			on: function(e, t) {
				if(e) {
					this._events_ || (this._events_ = {});
					var n = this._events_;
					n[e] || (n[e] = []), n[e].push(t)
				}
			},
			trigger: function(e) {
				var t = this._events_;
				if(e && t && t[e])
					for(var n = t[e], r = n.length, i = Array.prototype.slice.call(arguments, 1), o = 0; o < r; o++) n[o].apply(this, i)
			}
		}
	}, function(e, t) {
		"use strict";
		e.exports = {
			ignore: function() {
				window.Owl && window.Owl.debugLog && (window.console.log("【OWL日志:】"), window.console.log && window.console.log.apply(window.console, arguments))
			}
		}
	}, function(e, t) {
		"use strict";
		t.version = "1.7.20"
	}, function(e, t) {
		"use strict";
		e.exports = {
			stringify: function(e, t) {
				if(!t) return e;
				var n = [];
				for(var r in t) t.hasOwnProperty(r) && n.push(r + "=" + t[r]);
				return ~e.indexOf("?") ? e + "&" + n.join("&") : e + "?" + n.join("&")
			}
		}
	}, function(e, t, n) {
		"use strict";
		var r = n(10),
			i = window.navigator.userAgent;
		e.exports = function(e) {
			e.get("devMode") || (e.addFilter("base", r.base), /MicroMessenger/.test(i) ? e.addFilter("weixin", r.weixin) : /dp\/com\.dianping/.test(i) && e.addFilter("dianping", r.dianping))
		}
	}, function(e, t) {
		"use strict";
		e.exports = {
			base: function(e) {
				return !e.resourceUrl || /\.(dpfile|dianping|51ping|meituan)\.(com|net)/.test(e.resourceUrl)
			},
			weixin: function(e) {
				return !/(WeixinJSBridge|_WXJS|WebViewJavascriptBridge)/.test(e.sec_category)
			},
			dianping: function(e) {
				return !/document.elementFromPoint/.test(e.sec_category)
			}
		}
	}, function(e, t, n) {
		"use strict";

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}
		var i = function() {
				function e(e, t) {
					for(var n = 0; n < t.length; n++) {
						var r = t[n];
						r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
					}
				}
				return function(t, n, r) {
					return n && e(t.prototype, n), r && e(t, r), t
				}
			}(),
			o = n(2),
			a = n(12),
			s = n(13),
			c = ["project", "pageUrl", "realUrl", "resourceUrl", "category", "sec_category", "level", "timestamp", "content"],
			u = ["rowNum", "colNum", "tags"].concat(c),
			f = function() {
				function e(t) {
					if(r(this, e), t) {
						var n = void 0;
						for(n in t) t.hasOwnProperty(n) && (this[n] = t[n]);
						this.parse(t)
					}
				}
				return i(e, [{
					key: "parse",
					value: function() {
						this.category || (this.category = s.SCRIPT), this.level || (this.level = a.ERROR), this.timestamp || (this.timestamp = +new Date), this.sec_category || (this.sec_category = "default")
					}
				}, {
					key: "isEqual",
					value: function(e) {
						return this.sec_category === e.sec_category && this.resourceUrl === e.resourceUrl && this.colNum === e.colNum && this.rowNum === e.rowNum && this.content === e.content
					}
				}, {
					key: "update",
					value: function(e) {
						for(var t in e) void 0 !== e[t] && u.indexOf(t) !== -1 && (this[t] = e[t]);
						return this
					}
				}, {
					key: "updateTags",
					value: function(e) {
						var t = o(this.tags || {}, e);
						return this.tags = t, this
					}
				}, {
					key: "toJson",
					value: function() {
						var e = this,
							t = this.rowNum,
							n = this.colNum,
							r = {};
						return c.map(function(t) {
							void 0 !== e[t] && (r[t] = e[t])
						}), r.category === s.SCRIPT && t && n && (r.dynamicMetric = {
							rowNum: t,
							colNum: n
						}), this.tags && (r.dynamicMetric = o(r.dynamicMetric || {}, this.tags)), r
					}
				}]), e
			}();
		f.LEVEL = a, f.CATEGORY = s, e.exports = f
	}, function(e, t) {
		"use strict";
		e.exports = {
			ERROR: "error",
			INFO: "info",
			WARN: "warn",
			DEBUG: "debug"
		}
	}, function(e, t) {
		"use strict";
		e.exports = {
			SCRIPT: "jsError",
			AJAX: "ajaxError",
			RESOURCE: "resourceError"
		}
	}, function(e, t, n) {
		"use strict";

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}
		var i = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
				return typeof e
			} : function(e) {
				return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
			},
			o = function() {
				function e(e, t) {
					for(var n = 0; n < t.length; n++) {
						var r = t[n];
						r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
					}
				}
				return function(t, n, r) {
					return n && e(t.prototype, n), r && e(t, r), t
				}
			}(),
			a = n(4),
			s = n(2),
			c = n(11),
			u = n(15),
			f = n(13),
			g = n(6),
			h = n(16),
			l = n(8),
			p = n(17),
			d = function(e) {
				return e && e.stack || ""
			},
			v = "error",
			y = function(e) {
				return "c=" + encodeURIComponent(JSON.stringify(e))
			},
			m = function() {
				var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {};
				if(e && e.data) return e.beforeSend && e.beforeSend(), a({
					type: "POST",
					url: e.url,
					header: {
						"Content-Type": "application/x-www-form-urlencoded"
					},
					data: y(e.data),
					success: e.success,
					fail: e.fail
				})
			},
			w = function() {
				function e(t, n) {
					r(this, e), this.parent = n, this.cfgManager = t, this.cache = [], this.cacheSending = {}, this.comboTimeout = 0, this.timeLimit = Date.now(), this.errorCount = 0, this.isTimeLimit = !1, this.detectLeave()
				}
				return o(e, [{
					key: "_handleError",
					value: function(e) {
						try {
							var t = this.cfgManager.get("onErrorPush");
							if(t instanceof Function && (e = t(e)), e instanceof c || void 0 === e) return e;
							g.ignore("onErrorPush方法返回结果有误")
						} catch(t) {
							return g.ignore("onErrorPush方法处理有误", t), e
						}
					}
				}, {
					key: "parseWindowError",
					value: function(e, t, n, r, i) {
						try {
							i && i.stack ? this.push(i) : "string" == typeof e && this._push({
								category: f.SCRIPT,
								sec_category: e,
								resourceUrl: t,
								rowNum: n,
								colNum: r
							})
						} catch(e) {
							this.reportSystemError(e)
						}
					}
				}, {
					key: "parsePromiseUnhandled",
					value: function(e) {
						if(e) try {
							var t = e.reason;
							if(t) {
								if(t instanceof Error && (t = t.toString()), this.cfgManager && "test" === this.cfgManager.__env__) return t;
								this._push({
									category: f.SCRIPT,
									sec_category: "unhandledrejection",
									content: t
								})
							}
						} catch(e) {
							g.ignore(e)
						}
					}
				}, {
					key: "parseConsoleError",
					value: function() {
						try {
							var e = [].slice.call(arguments);
							if(!e || !e.length) return;
							for(var t = [], n = 0; n < e.length; n++) {
								var r = e[n];
								"string" != typeof e[n] && (r = e[n] instanceof window.Error ? JSON.stringify(e[n].stack) : JSON.stringify(e[n])), t.push(r)
							}
							t && t.length && this._push({
								category: f.SCRIPT,
								sec_category: "consoleError",
								content: t.join(" ")
							})
						} catch(e) {
							this.reportSystemError(e)
						}
					}
				}, {
					key: "detectLeave",
					value: function() {
						var e = this,
							t = arguments;
						try {
							var n = window.onbeforeunload;
							window.onbeforeunload = function() {
								var r = e.addCacheExtension(),
									i = e.cacheSending,
									o = void 0;
								if(Object.keys(i))
									for(var a in i)
										if(i.hasOwnProperty(a) && void 0 !== i[a]) {
											var s = i[a],
												c = s.xhr,
												f = s.cache;
											try {
												c.abort()
											} catch(e) {
												g.ignore(e)
											}
											o = r.concat(f)
										}
								if(o && o.length)
									if(window.navigator.sendBeacon && e.cfgManager.get("useSendBeacon")) {
										var h = e.cfgManager.getApiPath(v),
											p = l.stringify(h, {
												beacon: 1
											});
										window.navigator.sendBeacon(p, y(o))
									} else e.cfgManager.get("disableCache") || u.add(o);
								n && n.apply(e, t)
							}
						} catch(e) {
							g.ignore(e)
						}
					}
				}, {
					key: "checkCache",
					value: function() {
						var e = this,
							t = u.get();
						t && t.length && setTimeout(function() {
							m({
								url: e.cfgManager.getApiPath(v),
								data: t,
								success: function() {
									u.clear()
								}
							})
						}, 1500)
					}
				}, {
					key: "reportSystemError",
					value: function(e) {
						e && e.stack && (this.cache.push(new c({
							project: "owl",
							pageUrl: "default",
							realUrl: h.getHref(),
							sec_category: e.msg || e.name || "parseError",
							content: JSON.stringify(e.stack)
						})), this.send(!0))
					}
				}, {
					key: "_processError",
					value: function(e) {
						var t = function(e) {
								var t = "object" === ("undefined" == typeof e ? "undefined" : i(e)) ? JSON.stringify(e) : e;
								return {
									category: f.SCRIPT,
									sec_category: "Invalid_Error",
									content: t
								}
							},
							n = function(e) {
								var n = e.line,
									r = e.column,
									i = e.message || e.name || "";
								if(e.stack) {
									var o = e.stack.match("https?://[^\n]+");
									o = o ? o[0] : "";
									var a = /https?:\/\/(\S)*\.js/,
										s = "";
									a.test(o) && (s = o.match(a)[0]);
									var c = o.match(":(\\d+):(\\d+)");
									c || (c = [0, 0, 0]);
									var u = d(e);
									return {
										category: f.SCRIPT,
										sec_category: i,
										content: u,
										rowNum: void 0 !== n ? n : c[1],
										colNum: void 0 !== r ? r : c[2],
										resourceUrl: s
									}
								}
								return t(e)
							};
						try {
							return n(e)
						} catch(n) {
							return this.reportSystemError(e), t(e)
						}
					}
				}, {
					key: "_push",
					value: function(e, t) {
						e = this.parse(e), this.push(new c(e), t)
					}
				}, {
					key: "_pushResource",
					value: function() {
						var e = this.cfgManager.get("resource").sample;
						Math.random() > e || this._push.apply(this, arguments)
					}
				}, {
					key: "parse",
					value: function(e) {
						return e.project || (e.project = this.cfgManager.get("project")), e.pageUrl || (e.pageUrl = this.cfgManager.get("pageUrl") || "default"), e.realUrl = h.getHref(), e
					}
				}, {
					key: "push",
					value: function(e) {
						var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {},
							n = this.cfgManager;
						if(e && !(Math.random() > n.get(v).sample)) {
							if(e instanceof c || (e instanceof Error ? e = this._processError(e) : "string" == typeof e ? e = {
									sec_category: e
								} : "object" === ("undefined" == typeof e ? "undefined" : i(e)) && (e = {
									sec_category: e.name,
									content: e.msg
								}), e = this.parse(e), e = new c(e)), e && e.content && e.content.length >= n.get(v).maxSize) return void p.log("[Error]: 错误长度大于" + n.get(v).maxSize);
							e.update(t), p.log("[Error]: 错误类别->" + e.sec_category + " 错误堆栈->" + e.content);
							var r = n.filters,
								o = !0;
							if(this.cfgManager.get("noScriptError") && 0 === e.sec_category.indexOf("Script error")) return o = !1;
							if(r && r.length)
								for(var a = 0; a < r.length; a++) {
									var s = r[a];
									if(!s.fn(e)) return o = !1
								}
							var u = n.get("ignoreList").js;
							if(u && u.length)
								for(var f = 0; f < u.length; f++) {
									var g = u[f];
									if(0 === e.sec_category.indexOf(g)) {
										o = !1;
										break
									}
								}
							o && (this.isExist(e) || (this.onPush && this.onPush(e), e = this._handleError(e), e && (this.cache.push(e), this.send())))
						}
					}
				}, {
					key: "isExist",
					value: function(e) {
						for(var t = 0; t < this.cache.length; t++) {
							var n = this.cache[t];
							if(n instanceof c || (n = new c(n)), n.isEqual(e)) return !0
						}
						return !1
					}
				}, {
					key: "report",
					value: function() {
						this.push.apply(this, arguments), this.send(!0)
					}
				}, {
					key: "send",
					value: function(e) {
						var t = this,
							n = this.cfgManager,
							r = this.comboTimeout,
							i = n.get("error").maxNum || 100,
							o = n.get("error").maxTime || 6e4,
							a = Date.now() - this.timeLimit;
						this.isTimeLimit || (this.timeLimit = Date.now()), this.isTimeLimit = !0;
						var s = function() {
								if(t.cache.length) {
									clearTimeout(r), r = 0, t.onSubmit && t.onSubmit(t.cache);
									var e = t.addCacheExtension(),
										n = +new Date;
									try {
										if(t.errorCount = t.errorCount + t.cache.length, a <= o) {
											if(t.errorCount >= i) return
										} else t.isTimeLimit = !1, t.errorCount = 0
									} catch(e) {
										g.ignore(e)
									}
									var s = m({
										url: t.cfgManager.getApiPath(v),
										data: e,
										success: function() {
											t.nextCache = [], t.cacheSending[n] = void 0
										},
										fail: function() {
											t.cfgManager.get("disableCache") || u.add(t.nextCache)
										}
									});
									t.cacheSending[n] = {
										xhr: s,
										cache: e
									}, t.nextCache = e, t.cache = []
								}
							},
							c = n.get(v).delay;
						e ? s() : r || c === -1 || (r = setTimeout(s, c))
					}
				}, {
					key: "addCacheExtension",
					value: function() {
						var e = this.cfgManager,
							t = this.cache,
							n = [],
							r = e.getExtension();
						if(!t || !t.length) return n;
						for(var o = 0; o < t.length; o++) {
							var a = t[o];
							r && "object" === ("undefined" == typeof r ? "undefined" : i(r)) && (a = s(a.toJson(), r)), n.push(a)
						}
						return n
					}
				}]), e
			}();
		e.exports = w
	}, function(e, t, n) {
		"use strict";
		var r = "owl_cache",
			i = n(3),
			o = n(6);
		e.exports = {
			isSupport: i(),
			get: function() {
				if(this.isSupport) {
					var e = [];
					try {
						var t = localStorage.getItem(r);
						t && (e = JSON.parse(t))
					} catch(e) {
						o.ignore(e)
					}
					return e
				}
			},
			add: function(e) {
				if(this.isSupport && e instanceof Array) {
					var t = this.get();
					e = e.concat(t);
					try {
						localStorage.setItem(r, JSON.stringify(e))
					} catch(e) {
						o.ignore(e)
					}
				}
			},
			clear: function() {
				if(this.isSupport) try {
					localStorage.removeItem(r)
				} catch(e) {
					o.ignore(e)
				}
			}
		}
	}, function(e, t) {
		"use strict";
		var n = {
			getInstance: function() {
				return window
			},
			getConstructor: function() {
				return Window
			},
			getOrigin: function() {
				var e = window.location.origin;
				return e || (e = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ":" + window.location.port : "")), e
			},
			getProtocol: function() {
				var e = window.location.protocol;
				return e
			},
			getHref: function() {
				var e = window.location.href;
				return e
			},
			getUserAgent: function() {
				return window.navigator.userAgent
			}
		};
		e.exports = n
	}, function(e, t, n) {
		"use strict";
		var r = n(18).loadScript,
			i = void 0,
			o = !1;
		e.exports = {
			queue: [],
			ready: function() {
				var e = this,
					t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {},
					n = t.LoganAPI,
					a = t.pageUrl,
					s = t.project,
					c = t.loganConfig;
				o = !0;
				var u = function() {
					for(c && (i = i.config(c)), i.log("[新项目]: 项目-> " + s + " 对应的页面-> " + a + " "); e.queue.length;) {
						var t = e.queue.shift();
						i.log(t)
					}
				};
				n ? (i = n, u()) : r("//www.dpfile.com/app/dp-logan-web/logan_1.11.0.js", function() {
					i = window.Logan, u()
				})
			},
			log: function(e) {
				var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
				if(o) {
					var n = "owl";
					t.noType && (n = void 0), i ? i.log(e, n) : this.queue.push(e)
				}
			}
		}
	}, function(e, t) {
		"use strict";
		var n = [];
		t.loadScript = function(e, t) {
			if(~n.indexOf(e)) return void t();
			var r = document.createElement("script");
			r.src = e, r.onload = function() {
				n.push(e), t()
			}, document.getElementsByTagName("head")[0].appendChild(r)
		}
	}, function(e, t, n) {
		"use strict";

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}
		var i = function() {
				function e(e, t) {
					for(var n = 0; n < t.length; n++) {
						var r = t[n];
						r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
					}
				}
				return function(t, n, r) {
					return n && e(t.prototype, n), r && e(t, r), t
				}
			}(),
			o = n(4),
			a = n(20),
			s = n(13),
			c = n(5),
			u = n(6),
			f = n(16),
			g = n(17),
			h = "resource",
			l = 10,
			p = window.performance && void 0 !== window.performance.getEntries,
			d = function(e) {
				var t = e.split("//");
				if(t && t.length > 1) return t[0] + "//" + t[1].split("/")[0] + "/images"
			},
			v = function() {
				function e(t, n) {
					r(this, e), this.cfgManager = t, this.errManager = n, this.cache = [], this.entryCache = [], this.comboTimeout = 0
				}
				return i(e, [{
					key: "parseAjax",
					value: function(e) {
						var t = void 0,
							n = void 0,
							r = void 0,
							i = void 0,
							o = void 0,
							a = void 0;
						try {
							t = e.type, n = e.currentTarget.responseURL || e.currentTarget.url, r = e.duration, i = e.total, o = e.currentTarget.status, a = this.cfgManager.setConnectType()
						} catch(e) {
							return e
						}
						if(void 0 !== r && !isNaN(r)) {
							var c = "load" === t || "readystatechange" === t && 200 === o;
							g.log("[加载资源]: [类型]->AJAX [链接]->" + n), 0 === n.indexOf("//") ? n = f.getProtocol() + n : 0 === n.indexOf("/") && (n = f.getOrigin() + n), o = (c ? 200 : 500) + "|";
							var h = this.cfgManager,
								l = !0;
							try {
								var p = h.get("ignoreList").ajax;
								if(p)
									for(var d = 0; d < p.length; d++) {
										var v = p[d],
											y = new RegExp(v);
										if(y.test(n)) return void(l = !1)
									}
							} catch(e) {
								u.ignore(e)
							}
							if(l) {
								var m = h.get("resourceReg"),
									w = h.get("devMode") || m instanceof RegExp && h.get("resourceReg").test(n);
								w ? !h.get("ajax").flag || r < h.get("ajax").duration ? this.pushApi({
									type: "ajax",
									connectType: a,
									resourceUrl: n,
									responsetime: r && r.toString() ? r.toString() : "0",
									responsebyte: i && i.toString() ? i.toString() : "0",
									statusCode: o,
									firstCategory: c ? "" : s.AJAX
								}) : (this.errManager.push({
									name: "TIMEOUT_AJAX",
									msg: "ajax请求时间超过设定" + n
								}, {
									category: s.AJAX
								}), g.log("[加载资源请求超时]: [类型]->AJAX [链接]->" + n)) : h.get("invalid").ajax && Math.random() < .1 && (this.errManager.push({
									name: "INVALID_AJAX",
									msg: n
								}, {
									category: s.AJAX
								}), g.log("[资源请求域名非法]: [类型]->AJAX [链接]->" + n))
							}
						}
					}
				}, {
					key: "filterResource",
					value: function(e) {
						var t = !0,
							n = this.cfgManager.get("ignoreList").resource;
						try {
							for(var r = 0; r < n.length; r++) {
								var i = n[r],
									o = new RegExp(i);
								if(o.test(e)) {
									t = !1;
									break
								}
							}
							return t
						} catch(e) {
							return u.ignore(e), t
						}
					}
				}, {
					key: "doHandlePerformanceEntry",
					value: function(e) {
						if(e = this.filterEntry(e), e && e.length) {
							for(var t = this.cfgManager, n = this.entryCache, r = 0; r < e.length; r++) {
								var i = e[r],
									o = i.name,
									a = this.filterResource(o);
								if(!a) return;
								if(t.get("devMode") || t.get("resourceReg").test(o)) {
									n.push(o);
									var c = "200|",
										u = t.get("image"),
										f = !1;
									if(("img" === i.initiatorType || "css" === i.initiatorType) && u.flag) {
										var g = i.name,
											h = u.filter;
										if("function" == typeof h && !h(g)) {
											var l = i.transferSize,
												p = i.duration,
												v = void 0;
											l && l > 1e3 * u.fileSize ? v = "IMAGE_SIZE_EXCEED" : p && p > u.duration && (v = "IMAGE_DURATION_EXCEED"), v && (this.errManager._pushResource({
												content: g,
												sec_category: v
											}, {
												category: s.RESOURCE
											}), f = !0)
										}
									}
									f || this.push({
										type: i.initiatorType,
										resourceUrl: "img" === i.initiatorType ? d(o) : o,
										responsebyte: i.transferSize && i.transferSize.toString() ? i.transferSize.toString() : "0",
										responsetime: i.duration && i.duration.toString() ? i.duration.toString() : "0",
										statusCode: c
									})
								}
							}
							this.report()
						}
					}
				}, {
					key: "filterEntry",
					value: function(e) {
						for(var t = [], n = 0; n < e.length; n++) {
							var r = e[n];
							["link", "script", "img", "css"].indexOf(r.initiatorType) !== -1 && t.push(r)
						}
						return t
					}
				}, {
					key: "observePerformanceChange",
					value: function() {
						var e = this;
						if(!this.observePerformance) {
							this.observePerformance = !0;
							var t = this.cfgManager.get(h).disablePerformanceObserver || !1;
							try {
								var n = /Chrome\/(\d+)/,
									r = window.navigator.userAgent;
								if(n.test(r)) {
									var i = r.match(n)[1];
									parseInt(i) <= 59 && (t = !0)
								}
							} catch(e) {
								console.log("解析chrome版本出错")
							}
							if(window.PerformanceObserver && t !== !0) {
								var o = new window.PerformanceObserver(function(t) {
									try {
										var n = t.getEntries();
										e.doHandlePerformanceEntry(n)
									} catch(e) {
										u.ignore(e)
									}
								});
								o.observe({
									entryTypes: ["resource"]
								})
							} else c.on("ajaxCall", function() {
								setTimeout(function() {
									var t = e.entryCache,
										n = window.performance.getEntries();
									if(n.length !== t.length && n.length > t.length) {
										for(var r = [], i = 0; i < n.length; i++) t.indexOf(n[i].name) === -1 && r.push(n[i]);
										t = n, e.doHandlePerformanceEntry(r)
									}
								}, 1500)
							})
						}
					}
				}, {
					key: "handleResourceTiming",
					value: function() {
						try {
							if(p) {
								var e = window.performance.getEntries();
								this.doHandlePerformanceEntry(e), this.observePerformanceChange()
							}
						} catch(e) {
							this.errManager.reportSystemError(e)
						}
					}
				}, {
					key: "handleResourceLoadError",
					value: function(e) {
						var t = this,
							n = e.target || e.srcElement,
							r = function() {
								var e = n.src || n.href,
									r = n.nodeName;
								r && (r = r.toLowerCase());
								var i = f.getHref();
								if(i && 0 !== i.indexOf(e)) {
									var o = e;
									if("img" === r && (o = d(e)), o) {
										var a = t.filterResource(o);
										a && (t.addLogan(r, e), p ? t.pushApi({
											type: r,
											resourceUrl: o,
											responsetime: "0",
											responsebyte: "0",
											statusCode: "500|",
											firstCategory: s.RESOURCE,
											secondCategory: r,
											logContent: e
										}) : t.errManager._pushResource({
											category: s.RESOURCE,
											sec_category: r,
											content: e
										}))
									}
								}
							};
						try {
							var i = n instanceof HTMLScriptElement || n instanceof HTMLLinkElement || n instanceof HTMLImageElement;
							if(!i) return;
							r()
						} catch(e) {
							this.errManager.reportSystemError(e)
						}
					}
				}, {
					key: "addLogan",
					value: function(e, t) {
						g.log("[加载资源出错]: [类型]->" + e + " [链接]->" + t)
					}
				}, {
					key: "_stringify",
					value: function() {
						var e = this.cache;
						if(e && e.length) {
							for(var t = {}, n = this.cfgManager.getExtension(), r = {}, i = [], o = ["region", "operator", "network", "container", "os"], a = 0; a < o.length; a++) r[o[a]] = "";
							for(var s = 0; s < e.length; s++) {
								var c = e[s];
								i.push(c)
							}
							for(var u in r) r.hasOwnProperty(u) && (r[u] = n[u] || "");
							return r.infos = i, t = r
						}
					}
				}, {
					key: "doBatchPush",
					value: function(e) {
						e = this.parse(e);
						var t = new a(e);
						this.onPush && this.onPush(t);
						var n = !0,
							r = this.cfgManager;
						if("function" == typeof r.get("onBatchPush")) {
							var i = r.get("onBatchPush");
							try {
								n = i(t)
							} catch(e) {
								u.ignore(e)
							}
						}
						n && this.cache.push(t)
					}
				}, {
					key: "push",
					value: function(e) {
						var t = this.cfgManager;
						Math.random() > t.get(h).sample || (this.doBatchPush(e), this.cache.length >= l ? this.report(!0) : this.triggerReport())
					}
				}, {
					key: "parse",
					value: function(e) {
						return e.pageUrl || (e.pageUrl = this.cfgManager.get("pageUrl")), e.project || (e.project = this.cfgManager.get("project")), e
					}
				}, {
					key: "pushApi",
					value: function(e) {
						var t = this.cfgManager;
						Math.random() > t.get(h).sampleApi || (this.doBatchPush(e), this.triggerReport())
					}
				}, {
					key: "report",
					value: function() {
						this.triggerReport(!0)
					}
				}, {
					key: "triggerReport",
					value: function(e) {
						var t = this,
							n = this.cfgManager;
						if(this.cache.length) {
							var r = function() {
									clearTimeout(t.comboTimeout), t.comboTimeout = 0, t.onSubmit && t.onSubmit(t.cache), t.send()
								},
								i = n.get(h).delay;
							e ? this.send() : this.comboTimeout || i === -1 || (this.comboTimeout = setTimeout(r, i))
						}
					}
				}, {
					key: "send",
					value: function() {
						this.onSubmit && this.onSubmit(this.cache);
						var e = this._stringify();
						if(e) {
							var t = this.cfgManager.getApiPath(h);
							o({
								type: "POST",
								url: t,
								data: JSON.stringify(e),
								header: {
									"content-type": "application/json"
								}
							}), this.cache = []
						}
					}
				}]), e
			}();
		e.exports = v
	}, function(e, t) {
		"use strict";

		function n(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}
		var r = function() {
				function e(e, t) {
					for(var n = 0; n < t.length; n++) {
						var r = t[n];
						r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
					}
				}
				return function(t, n, r) {
					return n && e(t.prototype, n), r && e(t, r), t
				}
			}(),
			i = ["resourceUrl", "connectType", "type", "timestamp", "requestbyte", "responsebyte", "responsetime", "project", "pageUrl", "statusCode", "firstCategory", "secondCategory", "logContent"],
			o = ["resourceUrl", "connectType", "type", "timestamp", "requestbyte", "responsebyte", "responsetime", "project", "pageUrl", "statusCode", "firstCategory", "secondCategory", "logContent"],
			a = function() {
				function e(t) {
					var r = this;
					n(this, e), i.forEach(function(e) {
						r[e] = t[e]
					}), this.parse()
				}
				return r(e, [{
					key: "update",
					value: function(e) {
						for(var t in e) e.hasOwnProperty(t) && i.indexOf(t) !== -1 && (this[t] = e[t])
					}
				}, {
					key: "parse",
					value: function() {
						if(!this.timestamp) {
							var e = Date.now();
							this.timestamp = e.toString()
						}
						this.requestbyte || (this.requestbyte = "0"), this.responsebyte || (this.responsebyte = "0")
					}
				}, {
					key: "stringify",
					value: function() {
						var e = this,
							t = o.map(function(t) {
								return e[t]
							});
						return t.join("\t")
					}
				}]), e
			}();
		e.exports = a
	}, function(e, t, n) {
		"use strict";

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}
		var i = function() {
				function e(e, t) {
					for(var n = 0; n < t.length; n++) {
						var r = t[n];
						r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
					}
				}
				return function(t, n, r) {
					return n && e(t.prototype, n), r && e(t, r), t
				}
			}(),
			o = n(4),
			a = n(8),
			s = n(2),
			c = n(7).version,
			u = n(6),
			f = n(22),
			g = "page",
			h = function() {
				function e(t) {
					r(this, e), this.noCache = !1, this.points = [], this.pointsCustom = [], this.cfgManager = t
				}
				return i(e, [{
					key: "setUserReady",
					value: function() {
						this.cfgManager.set({
							page: {
								auto: !0
							}
						})
					}
				}, {
					key: "getUserReady",
					value: function() {
						return this.cfgManager.get(g).auto
					}
				}, {
					key: "setReady",
					value: function() {
						this.isReady = !0
					}
				}, {
					key: "getReady",
					value: function() {
						return this.isReady
					}
				}, {
					key: "getPerformaceTiming",
					value: function() {
						return {
							perf: window.performance && window.performance.timing,
							paint: window.performance && window.performance.getEntriesByType && window.performance.getEntriesByType("paint")
						}
					}
				}, {
					key: "getMainResourceTiming",
					value: function() {
						var e = window.performance && window.performance.getEntriesByType && window.performance.getEntriesByType("resource"),
							t = this.cfgManager.get("page").isMainResource,
							n = [];
						try {
							if(!e) return;
							if("function" == typeof t) {
								for(var r = 0; r < e.length; r++) {
									var i = e[r];
									t(i) && n.push(i)
								}
								return n
							}
							for(var o = 0; o < e.length; o++) {
								var a = e[o];
								"link" !== a.initiatorType && "script" !== a.initiatorType || n.push(a)
							}
							var s = this.cfgManager.get("page").mainResourceNumber;
							return n.length <= s ? n : n.splice(0, s)
						} catch(e) {
							u.ignore(e)
						}
					}
				}, {
					key: "sensoryObserver",
					value: function() {
						this.firstScreenManager = new f(this.cfgManager), this.firstScreenManager.mutaObserver()
					}
				}, {
					key: "parsePageTimeWithDefer",
					value: function() {
						var e = this;
						setTimeout(function() {
							try {
								var t = e.cfgManager.get("page");
								t && t.sensoryIndex ? e.firstScreenManager.getSensoryIndex(function(t) {
									e.parsePageTime(t)
								}) : e.parsePageTime()
							} catch(t) {
								e.parsePageTime()
							}
						}, 0)
					}
				}, {
					key: "parsePageTime",
					value: function(e) {
						var t = this.getPerformaceTiming(),
							n = t.perf,
							r = t.paint;
						if(!n) return this.setReady();
						var i = n.navigationStart,
							o = {
								unloadEventStart: 1,
								unloadEventEnd: 2,
								redirectStart: 3,
								redirectEnd: 4,
								fetchStart: 5,
								domainLookupStart: 6,
								domainLookupEnd: 7,
								connectStart: 8,
								connectEnd: 9,
								requestStart: 10,
								responseStart: 11,
								responseEnd: 12,
								domLoading: 13,
								domInteractive: 14,
								domContentLoadedEventStart: 15,
								domContentLoadedEventEnd: 16,
								domComplete: 17,
								loadEventStart: 18,
								loadEventEnd: 19
							},
							a = void 0;
						for(a in n) 0 === n[a] ? this.points[o[a]] = n[a] : this.points[o[a]] = n[a] - i;
						if(this.points[20] = n.domainLookupEnd - n.domainLookupStart, this.points[21] = n.connectEnd - n.connectStart, this.points[22] = n.responseEnd - n.requestStart, r && r.length) try {
							for(var s = Number.parseInt || window.parseInt, c = void 0, f = void 0, g = 0; g < r.length; g++) {
								var h = r[g];
								"first-paint" === h.name ? c = s(h.startTime) : "first-contentful-paint" === h.name && (f = s(h.startTime))
							}
							c && f && (this.points[23] = c, this.points[24] = f, e && (e.FST && e.FST > f ? this.points[25] = e.FST : this.points[25] = f, this.points[26] = e.FCP || f))
						} catch(e) {
							u.ignore(e)
						} else e && (this.points[25] = e.FST || 0, this.points[26] = e.FCP || this.points[15]);
						for(var l = 0; l < this.points.length; l++) isNaN(this.points[l]) && (this.points[l] = 0);
						var p = this.getMainResourceTiming();
						if(p) {
							for(var d = this.noCache, v = 0; v < p.length; v++) {
								var y = p[v];
								if(void 0 !== y.transferSize) 0 !== y.transferSize && (d = !0);
								else if(void 0 !== y.duration) {
									var m = this.cfgManager.get("page").timeThreshold;
									y.duration > m && (d = !0)
								}
							}
							this.noCache = d
						}
						this.setReady(), this.report()
					}
				}, {
					key: "push",
					value: function(e) {
						this.onPush && this.onPush(e), e && "number" == typeof e.position && (e.position < 0 || e.position > 31 || (this.pointsCustom[e.position] = e.duration, this.report()))
					}
				}, {
					key: "clearTimeout",
					value: function(e) {
						function t() {
							return e.apply(this, arguments)
						}
						return t.toString = function() {
							return e.toString()
						}, t
					}(function() {
						clearTimeout(this.timeout), this.timeout = void 0
					})
				}, {
					key: "report",
					value: function(e) {
						var t = this,
							n = this.cfgManager;
						if(this.getReady() && this.getUserReady() && (this.points.length || this.pointsCustom.length) && !(Math.random() > n.get(g).sample)) {
							this.onSubmit && this.onSubmit(this.points, this.pointsCustom);
							try {
								this.timeout ? this.clearTimeout() : this.pendingAjax && this.pendingAjax.abort && this.pendingAjax.abort()
							} catch(e) {
								u.ignore(e)
							}
							var r = function() {
									if(t.clearTimeout(), t.points.length || t.pointsCustom.length) {
										var e = n.getApiPath(g),
											r = a.stringify(e),
											i = n.getExtension(),
											u = s({
												project: n.get("project"),
												pageurl: encodeURIComponent(n.get("pageUrl")),
												speed: encodeURIComponent(t.points.join("|")),
												customspeed: encodeURIComponent(t.pointsCustom.join("|")),
												timestamp: +new Date,
												sdk: c,
												noCache: t.noCache ? "true" : "false"
											}, i);
										delete u.unionId, r = a.stringify(r, u), t.pendingAjax = o({
											method: "GET",
											url: r,
											success: function() {
												t.points = [], t.pointsCustom = [], t.pendingAjax = void 0
											}
										})
									}
								},
								i = n.get(g).delay || 0;
							e ? r() : i >= 0 && (this.timeout = setTimeout(function() {
								r()
							}, i))
						}
					}
				}]), e
			}();
		e.exports = h
	}, function(e, t, n) {
		"use strict";

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function i(e) {
			A || (window.performance && window.performance.getEntriesByType && window.performance.getEntriesByType("paint") && document && document.body && "" != document.body.innerText ? (e && 3 === e.nodeType || e && "" !== e.innerText || document.querySelector && document.querySelector("img")) && (u("FCP"), A = !0) : (T = 0, A = !0))
		}

		function o(e, t) {
			clearTimeout(F), O && clearTimeout(O), O = setTimeout(function() {
				a(t)
			}, m);
			for(var n = e.length, r = 0; r < n; r++) {
				var o = e[r];
				if("head" === o.target || "html" === o.target) return;
				if("childList" === o.type && o.addedNodes.length && !_) {
					var s = o.addedNodes[0];
					i(s), x++, u(x), S[x] = {
						nodes: o.addedNodes
					}, s.offsetTop > 1.2 * w && _ && (C++, C > 15 && (_ = !0, a()));
					for(var f = 0, g = o.addedNodes.length; f < g; f++) {
						var h = o.addedNodes[f];
						h && 1 === h.nodeType && document.querySelectorAll && c(h)
					}
				}
			}
		}

		function a() {
			P && P.disconnect && (P.disconnect(), P = null, f())
		}

		function s() {
			R && R.disconnect && (R.disconnect(), R = null), N = !0
		}

		function c(e) {
			for(var t = e.querySelectorAll("[interactive]"), n = 0, r = t.length; n < r; n++) {
				var i = t[n],
					o = i.getAttribute("interactive") || "click";
				i.addEventListener(o, function() {
					a(P)
				})
			}
		}

		function u(e) {
			2 === b ? E[e] = Date.now() : 1 === b ? E[e] = performance.now() : performance.mark(e)
		}

		function f() {
			if(b) {
				for(var e = Object.keys(E), t = 0, n = e.length; t < n; t++) {
					var r = E[e[t]];
					"FCP" === r ? T = E[r] : S[r] && (S[r].startTime = E[r])
				}
				E = null
			} else {
				for(var i = performance.getEntriesByType("mark"), o = 0, a = i.length; o < a; o++) {
					var s = i[o];
					"FCP" === s.name ? T = parseInt(s.startTime) : S[s.name] && (S[s.name].startTime = s.startTime)
				}
				performance.clearMarks()
			}
			g()
		}

		function g() {
			for(var e = [], t = Object.keys(S), n = 0, r = t.length; n < r; n++) {
				var i = t[n],
					o = S[i],
					a = S[i].nodes;
				if(a && a.length) {
					for(var s = [], c = 0, u = a.length; c < u; c++) {
						var f = a[c];
						(!f.style || "hidden" !== f.style.visibility && "none" !== f.style.display) && s.push(f)
					}
					s.length && e.push({
						score: p(s),
						startTime: o.startTime
					})
				}
			}
			S = {};
			var g = l(e),
				h = 2 === b ? g - performance.timing.navigationStart : g;
			L = {
				FST: h,
				FCP: T
			}, I = !0
		}

		function h(e, t) {
			var n = y.getImageTimingsInFirstScreen(e, t);
			L && n.length && (L.FST = Math.max(L.FST, Math.round(n[0])))
		}

		function l(e) {
			var t = Object.keys(e).length,
				n = 0;
			if(1 === t) n = e[0].startTime;
			else if(t > 1) {
				var r = void 0;
				r = e.reduce(function(e, t) {
					if(!e.length) return e.push(t), e;
					var n = e.length - 1,
						r = e[n],
						i = t.startTime - r.startTime;
					return b < 2 && i < 1 || 2 === b && i < 2 ? e[n] = {
						score: t.score + r.score,
						startTime: t.startTime
					} : e.push(t), e
				}, []).reduce(function(e, t) {
					return e ? e.score < t.score || t.score >= 3 ? t : e : t
				}), n = r.startTime
			}
			return parseInt(n)
		}

		function p(e) {
			var t = 0;
			if(e && e.length) {
				t += e.length * k;
				for(var n = 0, r = e.length; n < r; n++) t += d(e[n], 0)
			}
			return t
		}

		function d(e, t) {
			if(3 === e.nodeType) return t;
			if(e.style && ("hidden" === e.style.visibility || "none" === e.style.display)) return 0;
			if(!e.clientWidth || !e.clientHeight) return 0;
			if(e.childNodes.length) {
				var n = Array.prototype.slice.call(e.childNodes, 0),
					r = n.reduce(function(e, n) {
						var r = d(n, t);
						return e + r
					}, 0);
				return r
			}
			return t + M
		}
		var v = function() {
				function e(e, t) {
					for(var n = 0; n < t.length; n++) {
						var r = t[n];
						r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
					}
				}
				return function(t, n, r) {
					return n && e(t.prototype, n), r && e(t, r), t
				}
			}(),
			y = n(23),
			m = 3e3,
			w = window.outerHeight || screen.height,
			b = function() {
				return window.performance ? window.performance.mark ? 0 : window.performance.now ? 1 : 2 : null
			}(),
			k = 1,
			M = 1,
			E = {},
			T = 0,
			S = {},
			x = 0,
			C = 0,
			_ = !1,
			O = !1,
			P = void 0,
			R = void 0,
			j = void 0,
			A = void 0,
			I = !1,
			N = !1,
			L = void 0,
			U = void 0,
			F = void 0,
			D = function() {
				function e(t) {
					r(this, e), this.cfgManager = t;
					var n = window.MutationObserver || window.WebKitMutationObserver || window.MozMutationObserver;
					n && null !== b && document.body || (I = !0, N = !0)
				}
				return v(e, [{
					key: "mutaObserver",
					value: function() {
						return I || (P = new MutationObserver(o), P.observe(document, {
							childList: !0,
							subtree: !0
						}), F = setTimeout(function() {
							f(), I = !0
						}, m)), (U = this.cfgManager.get("page").disableSensoryImageIndex) ? void(N = !0) : void this.registerPerformanceObserver()
					}
				}, {
					key: "registerPerformanceObserver",
					value: function() {
						var e = this.cfgManager.get("resource").disablePerformanceObserver || !1;
						try {
							var t = /Chrome\/(\d+)/,
								n = window.navigator.userAgent;
							if(t.test(n)) {
								var r = n.match(t)[1];
								parseInt(r) <= 59 && (e = !0)
							}
						} catch(e) {
							console.log("解析chrome版本出错")
						}
						window.PerformanceObserver && e !== !0 ? (R = new window.PerformanceObserver(function() {
							clearTimeout(j), j = setTimeout(function() {
								s()
							}, m)
						}), R.observe({
							entryTypes: ["resource"]
						})) : N = !0
					}
				}, {
					key: "getSensoryIndex",
					value: function(e) {
						var t = this,
							n = 0,
							r = function() {
								if(I && N)
									if(clearInterval(n), L) {
										if(!U) {
											var r = t.cfgManager.get();
											h(r.ignoreList.attr, r.image.pattern)
										}
										e(L)
									} else e()
							};
						n = setInterval(r, 500)
					}
				}]), e
			}();
		e.exports = D
	}, function(e, t) {
		"use strict";

		function n(e) {
			return e && "data:image" === e.slice(0, 10)
		}

		function r(e) {
			var t = document.createElement("a");
			return t.href = e, t.href
		}

		function i(e, t) {
			var r = void 0,
				i = e.match(/url\(.*?\)/g);
			if(i && i.length) {
				var o = i[i.length - 1],
					a = o.replace(/^url\([\'\"]?/, "").replace(/[\'\"]?\)$/, "");
				t.test(a) && !n(a) && (r = a)
			}
			return r
		}

		function o(e, t) {
			if("IMG" === e.nodeName.toUpperCase()) return e.getAttribute("data-srcset") || e.getAttribute("src");
			var n = window.getComputedStyle(e),
				r = n.getPropertyValue("background-image") || n.getPropertyValue("background");
			return i(r, t)
		}

		function a(e, t) {
			var i = [];
			try {
				for(var a = document.createNodeIterator(document.body, NodeFilter.SHOW_ELEMENT, function(t) {
						if(t && t.getAttribute && !t.hasAttribute("" + e)) return NodeFilter.FILTER_ACCEPT
					}, !1), c = a.nextNode(); c;) {
					var u = o(c, t);
					u ? (s(c) && !n(u) && i.push(r(u)), c = a.nextNode()) : c = a.nextNode()
				}
			} catch(e) {
				console.log("getDomImgs err")
			}
			return i
		}

		function s(e) {
			if(!e) return !1;
			var t = e.getBoundingClientRect(),
				n = t.top,
				r = t.left,
				i = t.right,
				o = t.bottom;
			return !(!n && !o) && e.scrollTop + n < window.innerHeight && i > 0 && r < window.innerWidth
		}

		function c(e, t) {
			if(!window.performance || !window.performance.getEntriesByType) return [];
			var n = [];
			try {
				var r = window.performance.getEntriesByType("resource").filter(function(e) {
						return "img" === e.initiatorType || "css" === e.initiatorType
					}).map(function(e) {
						return {
							name: e.name.split(":")[1] || e.name,
							responseEnd: e.responseEnd,
							connectEnd: e.connectEnd,
							duration: e.duration
						}
					}),
					i = a(e, t).join("###");
				n = r.filter(function(e) {
					return i.indexOf(e.name) !== -1
				}).map(function(e) {
					return e.responseEnd || e.connectEnd + e.duration
				}).sort(function(e, t) {
					return t - e
				})
			} catch(e) {
				n = []
			}
			return n
		}
		e.exports = {
			getImageTimingsInFirstScreen: c,
			isInFirstScreen: s
		}
	}, function(e, t, n) {
		"use strict";

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}
		var i = function() {
				function e(e, t) {
					for(var n = 0; n < t.length; n++) {
						var r = t[n];
						r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
					}
				}
				return function(t, n, r) {
					return n && e(t.prototype, n), r && e(t, r), t
				}
			}(),
			o = n(2),
			a = n(4),
			s = n(6),
			c = n(8),
			u = "metric",
			f = function() {
				function e(t) {
					r(this, e), this.cfgManager = t, this.tags = {}, this.kvs = {}, this.random = Math.random()
				}
				return i(e, [{
					key: "setTags",
					value: function(e) {
						this.tags = o(this.tags, e)
					}
				}, {
					key: "getTags",
					value: function(e) {
						return e ? this.tags[e] : this.tags
					}
				}, {
					key: "setMetric",
					value: function(e, t) {
						var n = this;
						if("string" != typeof e) return s.ignore("metric名称必须是string类型");
						if("number" != typeof t) return s.ignore("metric值必须是number类型,当前为" + e + "-" + t);
						this.kvs[e] || (this.kvs[e] = []), this.kvs[e].push(t);
						try {
							this.cfgManager.get(u).combo && (this.timeout && (clearTimeout(this.timeout), this.timeout = null), this.timeout = setTimeout(function() {
								n.report()
							}, this.cfgManager.get(u).delay || 1500))
						} catch(e) {
							throw e
						}
					}
				}, {
					key: "getMetric",
					value: function(e) {
						return e ? this.kvs[e] : this.kvs
					}
				}, {
					key: "clearMetric",
					value: function() {
						this.kvs = {}
					}
				}, {
					key: "_rollbackMetric",
					value: function(e) {
						for(var t in e) e.hasOwnProperty(t) && (this.kvs[t] = e[t].concat(this.kvs[t] || []))
					}
				}, {
					key: "report",
					value: function() {
						var e = this;
						if(!(this.random > this.cfgManager.get(u).sample)) {
							var t = this.cfgManager,
								n = t.get("project");
							if(this.kvs && 0 !== Object.keys(this.kvs).length) {
								var r = {
										kvs: this.kvs,
										tags: this.tags,
										ts: parseInt(+new Date / 1e3)
									},
									i = this.kvs;
								this.clearMetric();
								var o = t.getApiPath(u),
									s = c.stringify(o, {
										p: n
									});
								a({
									type: "POST",
									url: s,
									header: {
										"Content-Type": "application/x-www-form-urlencoded"
									},
									data: "data=" + encodeURIComponent(JSON.stringify(r)),
									fail: function() {
										e._rollbackMetric(i)
									}
								})
							}
						}
					}
				}]), e
			}();
		e.exports = f
	}, function(e, t, n) {
		"use strict";

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}
		var i = function() {
				function e(e, t) {
					for(var n = 0; n < t.length; n++) {
						var r = t[n];
						r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
					}
				}
				return function(t, n, r) {
					return n && e(t.prototype, n), r && e(t, r), t
				}
			}(),
			o = n(17),
			a = function() {
				function e(t) {
					r(this, e), this.cfgManager = t
				}
				return i(e, [{
					key: "addLog",
					value: function(e) {
						if("string" != typeof e) throw "addLog只接受string类型的日志";
						o.log("[Log]: " + e, {
							noType: !0
						})
					}
				}]), e
			}();
		e.exports = a
	}, function(e, t) {
		"use strict";
		e.exports = function(e) {
			var t = window.onerror;
			if(e) {
				var n = e.cfgManager;
				if(n.get("autoCatch").js !== !1) {
					window.onerror = function() {
						e.parseWindowError.apply(e, arguments), t && t.apply(window, arguments)
					};
					var r = window.addEventListener || window.attachEvent;
					r("unhandledrejection", function() {
						e.parsePromiseUnhandled.apply(e, arguments)
					})
				}
				if(n.get("autoCatch").console === !0) {
					if(!window.console || !window.console.error) return;
					var i = window.console.error;
					window.console.error = function() {
						e.parseConsoleError.apply(e, arguments), i && i.apply(window, arguments)
					}
				}
			}
		}
	}, function(e, t, n) {
		"use strict";
		var r = n(5);
		e.exports = function(e) {
			var t = e.cfgManager;
			t.get("autoCatch").ajax && r.on("ajaxCall", function() {
				e.parseAjax.apply(e, arguments)
			});
			var n = window.addEventListener || window.attachEvent;
			t.get("autoCatch").resource && (document && "complete" === document.readyState ? e.handleResourceTiming.apply(e, arguments) : n("load", function() {
				e.handleResourceTiming.apply(e, arguments)
			}), n("error", function(t) {
				t && e.handleResourceLoadError.apply(e, arguments)
			}, !0))
		}
	}, function(e, t) {
		"use strict";
		e.exports = function(e) {
			var t = e.cfgManager;
			if(t.get("page").sensoryIndex && e.sensoryObserver(), t.get("autoCatch").page && !e.getReady())
				if(document && "complete" === document.readyState) e.parsePageTimeWithDefer();
				else {
					var n = window.addEventListener || window.attachEvent;
					n("load", function() {
						e.parsePageTimeWithDefer()
					})
				}
		}
	}, function(e, t, n) {
		"use strict";
		var r = n(30);
		e.exports = {
			getExt: function() {
				var e = r("network");
				if(!e) {
					var t = navigator.userAgent,
						n = /NetType\/([a-zA-Z0-9]+)/;
					t && n.test(t) && (e = t.match(n)[1])
				}
				var i = r("unionid") || r("dpid") || r("_lxsdk_cuid"),
					o = {};
				return e && (o.network = e), i && (o.unionId = i), o
			}
		}
	}, function(e, t) {
		"use strict";
		e.exports = function(e) {
			for(var t = e + "=", n = document.cookie.split(";"), r = 0; r < n.length; r++) {
				for(var i = n[r];
					" " == i.charAt(0);) i = i.substring(1, i.length);
				if(0 == i.indexOf(t)) return i.substring(t.length, i.length)
			}
			return null
		}
	}, function(e, t) {
		"use strict";
		var n = ["Baiduspider", "googlebot", "360Spider", "haosouspider", "YoudaoBot", "Sogou News Spider", "Yisouspider", "Googlebot"];
		e.exports = n
	}, function(e, t, n) {
		"use strict";
		var r = n(4),
			i = n(8),
			o = n(7).version,
			a = n(6),
			s = .01;
		e.exports = function(e, t) {
			if(e && t && !(Math.random() > s)) {
				var n = function(e, t) {
					try {
						e = e.split(".").map(function(e) {
							return parseInt(e)
						}), t = t.split(".").map(function(e) {
							return parseInt(e)
						});
						var n = void 0;
						return n = e[0] !== t[0] ? e[0] - t[0] : e[1] !== t[1] ? e[1] - t[1] : e[2] - t[2], n >= 0
					} catch(e) {
						return a.ignore(e), !1
					}
				};
				if(!n(t.cfgManager.get("version"), o)) {
					var c = {
							v: 1,
							rate: s,
							project: e,
							version: "v_" + o,
							pageurl: encodeURIComponent(window.location.href),
							count: 1
						},
						u = t.cfgManager.url + "/api/version";
					u = i.stringify(u, c), setTimeout(function() {
						r({
							type: "GET",
							url: u
						})
					}, 1500)
				}
			}
		}
	}])
});