Zepto(function($) {
    buryLink = function(e, indx, id,disabled) {
      var souce = $(e).attr("source"),
          url   = $(e).attr("href");
      // console.log($(e).attr("source"))
        // QBFK.EventLog.sendMsg({
        //     pageName: '113',
        //     model: indx == 1 ? 'coupon_tab_data_product' : 'coupon_tap_product',
        //     id: id
        // });
        // setTimeout("window.location.href='" + href + "'", 500);
        if(disabled!=1){
          setTimeout(function(){
            if(_defautBiz.client.isAndroid() && souce == 'jd' ){
               window.open(url.split("url=")[1],'blank');
            }
            else window.location.href=url;
            // var u = navigator.userAgent,
            //     app = navigator.appVersion;
            // var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android ն˻   uc
            // if(isAndroid&&souce=="jd") window.open(href.split("url=")[1]);
            // else window.location.href=href;
          }, 500);
        }
    }
    var _defautBiz =
        _defautBiz || {
            env: 'product',
            _baseUrl: function() {
                return this.env == 'debug' ? '/static/mock' : ''
            },
            api: function(name) {
                return this._api(this._baseUrl())[this.env][name];
            },
            _api: function(_base_url) {
                var _v = new Date().getTime();
                return {
                    debug: {
                        flashSaleDetail: 'mock2.json'
                    },
                    product: {
                        flashSaleDetail: '/stuff/ad/banner/flashSaleDetail.do',
                    }
                }
            },
            client:{
              isAndroid:function () {
                if(navigator.userAgent.match(/Android/i)) {
                        return true
                    }
                    return false;
              },
              isiOS:function () {
                if(navigator.userAgent.match(/iPhone|iPad|iPod/i)) {
                  return true
                }
                return false;
              }
            },
            login:function(){
                //android login
                if(navigator.userAgent.match(/Android/i)) {
                    if (typeof QBaoJSBridge != 'undefined') {
                        QBaoJSBridge.login("goodstuff.qbao.com", String(active_obj.initData));
                    }
                }
                //ios login
                else if(navigator.userAgent.match(/iPhone|iPad|iPod/i)) {
                    if (typeof ioswebview != 'undefined') {
                        ioswebview.showLoginViewAnd("goodstuff.qbao.com", "active_obj.initData();");
                    }
                }
            }
      }
    var active_obj = {
        heightList: [],
        flashSale: [],
        scroll_height: 0,
        first_date:'',
        initData: function() {
            var u = navigator.userAgent,
                app = navigator.appVersion;
            var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android 
            var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios 
            var obj = this;
            var url = window.location.href + "";

            function getUrlParam(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(r[2]);
                return null;
            }
            var device = (function() {
                if (isAndroid) return 2;
                else if (isiOS) return 1
            })()
            $.ajax({
                type: 'GET',
                url: _defautBiz.api("flashSaleDetail"),
                // data to be added to query string:
                data: {
                    bannerId: getUrlParam("bannerId"),
                    device: device
                },
                // type of data we are expecting in return:
                dataType: 'json',
                //  timeout: 300,
                //  context: $('body'),
                success: function(resp) {

                    if(resp && (resp.returnCode === 100001 || resp.returnCode === "100001")){
                        _defautBiz.login()
                        return;
                    }

                    $("#nav").html("");
                    $("#time").html("");
                    $(".body .comment .nav-goods").html("");
                    $("html").show();
                    $("#header").append('<img class="lazyload" src="' + resp.data.imgUrl + '" alt="">');
                    setTimeout(function(){
                      obj.scroll_height = $("#header").height();
                    },500)
                    $(window).on('scroll', function() {
                        var _winScrollTop = $(window).scrollTop();
                        obj.scroll_height = $("#header").height();
                        var navHeight = obj.scroll_height + 45;
                        if (obj.scroll_height <= _winScrollTop) $("#nav").parent(".header").addClass("fixed");
                        else $("#nav").parent(".header").removeClass("fixed");

                    });
                    for (var i in resp.data.flashSale.item) {
                        var itemList = [];
                        for (var j in resp.data.flashSale.item[i].stuffs) {
                            var good = resp.data.flashSale.item[i].stuffs[j];
                            itemList.push({
                                url: good.url,
                                id: good.id,
                                imgUrl: good.imgUrl,
                                source:good.source,
                                rebateValue: good.rebateValue,
                                name: good.name,
                                finalPrice: good.finalPrice,
                                orderNum: good.orderNum
                            });
                            if (i == 0) {
                                $(".body .comment .nav-goods").append('<div class="nav-item good"><a linkable=0 source='+good.source+' href="' +"newtab://goodstuff.qbao.com/goods?url="+ good.url + '" onclick="buryLink(this,1,' + good.id + ',0);return false;"><img class="lazyload" src="img/default.png" data-original="' + good.imgUrl + '" alt=""><div class=info_content style="margin-top:15px;line-height:20px;"><span style="color:#35353f">' + good.name + '</span><span style="font-size:10px;color:#fd472b;position:relative;text-align:left;padding-left:20px;height:20px"><img style="background-image:none;width:12px;top:4px;position:absolute;left:0px;" src="' + parseIcon(good.source) + '">' + good.rebateValue + '</span><span style="font-weight:200">￥' + good.finalPrice + '</span><span style="font-size:12px;color:#999">销量' + good.orderNum + '</span><span style="position:absolute;right:0px;bottom:4px;"><img src="img/right_now.png" style="width:auto;height:16px;"></span></div></a></div>');
                                var width=$(".comment .nav-goods img.lazyload").width();
                                $(".comment .nav-goods img.lazyload").height(width);
                            }
                        }
                        obj.flashSale.push({
                            date: resp.data.flashSale.item[i].date,
                            stuffs: itemList
                        });
                        obj.first_date=resp.data.flashSale.item[0].date;
                        $("#time").append('<div class="time-item"><a href="javascript:void(0)" order="'+(parseInt(i) + 1)+'" date="' + resp.data.flashSale.item[i].date + '">' + parseDate(resp.data.flashSale.item[i].date) + '</a></div>');
                    }

                    function parseDate(date) {
                        var month=(date.split("-")[1].split("0").length > 1 ? date.split("-")[1].split("0")[1] : date.split("-")[1].split("0")[0]);
                        month=month?month:date.split("-")[1];
                        var day=(date.split("-")[2].split("0").length > 1 ? date.split("-")[2].split("0")[1] : date.split("-")[2].split("0")[0]);
                        day=day?day:date.split("-")[2];
                        var time =  month+ "月" + day + "日";
                        return time;
                    }
                    if (resp.data.flashSale) $("#nav").append('<div class="nav-item"><a href="javascript:void(0)" order="1" scroll_to="' + 0 + '" ><span class="loc"><img src="img/mark.png"></img></span><span class="tit">' + resp.data.flashSale.title + '</span></a></div>');
                    for (var i in resp.data.details) {
                        $("#nav").append('<div class="nav-item"><a href="javascript:void(0)" order="'+(parseInt(i) + 2)+'" scroll_to="' + (parseInt(i) + 1) + '"><span class="loc"><img src="img/mark.png"></img></span><span class="tit">' + resp.data.details[i].title + '</span></a></div>');
                        $(".body").append('<div class="content" floor="' + (parseInt(i) + 2) + '"><div class="title">' + resp.data.details[i].title + '</div><div class="goods-list list' + parseInt(resp.data.details[i].level) + '" id="ctx_' + i + '"></div></div>');
                        for (var j in resp.data.details[i].stuffs) {
                            var good = resp.data.details[i].stuffs[j];
                            if (resp.data.details[i].level == 1)
                                $("#ctx_" + i).append('<div class="list-item good"><a href="' +"newtab://goodstuff.qbao.com/goods?url="+ good.url + '" source='+good.source+' onclick="buryLink(this,2,' + good.id + ',0);return false;"><img class="lazyload" src="img/default.png" data-original="' + good.imgUrl + '" alt=""><div class=info_content style="margin-top:5px;line-height:20px;"><span style="color:#35353f;">' + good.name + '</span><span style="position:relative;padding-left:20px;font-size:10px;color:#fd472b;height:20px;">' + good.rebateValue + '<img style="position:absolute;background-image:none;width:10px;left:0px;top:5px;" src="' + parseIcon(good.source) + '"></span><span style="font-weight:200;float:left;color:#ff5a00;font-size:14px;">￥' + good.finalPrice + '</span></div></a></div>');
                            else {
                                $("#ctx_" + i).append('<div class="list-item good"><a href="' +"newtab://goodstuff.qbao.com/goods?url="+ good.url + '" source='+good.source+' onclick="buryLink(this,2,' + good.id + ',0);return false;"><img class="lazyload" src="img/default.png" data-original="' + good.imgUrl + '" alt=""><div class=info_content style="margin-top:10px;line-height:20px"><span style="color:#35353f;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 2;font-size:13px;overflow: hidden;white-space:inherit;height:38px;">' + good.name + '</span><span style="position:relative;padding-left:20px;font-size:10px;color:#fd472b;height:20px;"><img style="position:absolute;background-image:none;width:12px;left:2px;top:4px;" src="' + parseIcon(good.source) + '">' + good.rebateValue + '</span><span style="color:#fd472b:font-size:12px;font-weight:200">￥' + good.finalPrice + '</span><span style="font-size:12px;color:#999">销量:' + good.orderNum + '</span><span style="position:absolute;right:0px;bottom:5px;"><img src="img/right_now.png" style="width:auto;height:16px;"></span></div></a></div>');
                            }
                        }
                        $("#ctx_" + i).append('<div style="clear:both"></div>');
                        var width=$("#ctx_" + i+" .list-item.good img.lazyload").width();
                        $("#ctx_" + i+" .list-item.good img.lazyload").height(width);
                    }
                    $(".nav-item:first-child a").addClass("active");
                    $(".time-item:first-child a").addClass("active");

                    $(window).on('scroll', function() {
                      var floor=$(".content").length;
                      $(".content").each(function() {
                        var _winScrollTop = $(window).scrollTop();
                        if(this.offsetTop+$(this).height()+30>_winScrollTop){

                          floor=$(this).attr("floor");
                          $("#nav .nav-item a").removeClass("active");
                          $("#nav .nav-item a").each(function() {
                            if($(this).attr("order")==floor)
                            $(this).addClass("active");
                          });
                          return false;
                        }
                      });
                    });


                    // $(".comment .nav-goods").html("");
                    // $(".comment .nav-goods").append('<div class="nav-item good"><a href="javascript:void(0)"><img class="lazyload" src="img/good1.png" alt=""><div class=info_content><span>商品名称</span><span>￥1213.00</span><span>waalla</span></div></a></div>');
                    $('.lazyload').picLazyLoad();
                    $(".body .content").each(function() {
                        obj.heightList.push($(this).height());
                    });

                    $("#nav .nav-item a").on("click", function() {
                        $("#nav .nav-item a").removeClass("active");
                        $(this).addClass("active");
                        scroll_to($(this).attr("scroll_to"));
                        QBFK.EventLog.sendMsg({
                            pageName: '113',
                            model: 'coupon_tab',
                            id: $(this).attr("order")
                        });
                    });
                    $("#time .time-item a").on("click", function() {
                        $("#time .time-item a").removeClass("active");
                        $(this).addClass("active");
                        show_date($(this).attr("date"));
                        QBFK.EventLog.sendMsg({
                            pageName: '113',
                            model: 'coupon_tab_data',
                            id: $(this).attr("order")
                        });
                    });

                    function scroll_to(i) {
                        var navHeight = obj.scroll_height;
                        var scroll_height = navHeight;
                        for (var num = 0; num < i; num++) {
                            scroll_height += i == 0 ? 0 : obj.heightList[num];
                        }
                        $('html,body').scrollTop(scroll_height + 85);
                    }

                    function show_date(date) {
                        var diabled=0;
                        if(date!=obj.first_date)diabled=1
                        $(".body .comment .nav-goods").hide(500);
                        $(".body .comment .nav-goods").html("");
                        var dateItem = $.grep(obj.flashSale, function(e, i) {
                            return e.date == date;
                        })
                        for (var i in dateItem[0].stuffs) {
                            var good = dateItem[0].stuffs[i];

                                $(".body .comment .nav-goods").append('<div class="nav-item good"><a linkable='+diabled+' href="' +"newtab://goodstuff.qbao.com/goods?url="+ good.url + '"source='+good.source+'  onclick="buryLink(this,1,' + good.id +','+diabled+');return false;"><img class="lazyload" src="img/default.png" data-original="' + good.imgUrl + '" alt=""><div class=info_content style="margin-top:15px;line-height:20px;"><span style="color:#35353f">' + good.name + '</span><span style="font-size:10px;color:#fd472b;position:relative;text-align:left;padding-left:20px;height:20px"><img style="background-image:none;width:12px;top:4px;position:absolute;left:0px;" src="' + parseIcon(good.source) + '">' + good.rebateValue + '</span><span style="font-weight:200">￥' + good.finalPrice + '</span><span style="font-size:12px;color:#999">销量' + good.orderNum + '</span><span style="position:absolute;right:0px;bottom:4px;"><img src="img/right_now.png" style="width:auto;height:16px;"></span></div></a></div>');
                            // $(".body .comment .nav-goods").append('<div class="nav-item good"><a href="' + good.url + '" onclick="buryLink(this.href,1,' + good.id + ');return false;"><img class="lazyload" src="img/default.png" data-original="' + good.imgUrl + '" alt=""><div class=info_content><span>' + good.rebateValue + '</span><span>' + good.name + '</span><span>券后￥' + good.finalPrice + '</span><span>销量' + good.orderNum + '</span></div></a></div>');
                            $(".body .comment .nav-goods").show(500);
                        }
                        var width=$(".comment .nav-goods img.lazyload").width();
                        $(".comment .nav-goods img.lazyload").height(width);
                        $('.lazyload').picLazyLoad();
                    }
                },
                error: function(xhr, type) {
                    alert('Ajax error!')
                }
            });
        }
    }
    function parseIcon(souce) {
        var src = "";
        switch (souce) {
            case 'taobao':
                src = "img/taobao.png";
                break;
            case 'tmall':
                src = "img/tmao.png";
                break;
            case 'jd':
                src = "img/jd.png";
                break;
        }
        return src;
    }
    active_obj.initData();
});
