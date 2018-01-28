var QBFK= {
            Util:{
                getDevice:function(){
                    if(navigator.userAgent.match(/Android/i)) 
                    return 'android'
                        else if(navigator.userAgent.match(/iPhone|iPad|iPod/i))
                    return 'ios'
                    else 
                        return navigator.userAgent.toString()
                }
            },
            EventLog:{
                BASE_DOMAIN:'dig.qbao.com',
                BASE_PATH:'/wap-site/images/',
                BASE_IMG:'goods_stuff.jpg',
                sendMsg:function(params){
                    var i = new Image();
                    i.src = '//' + this.BASE_DOMAIN + this.BASE_PATH + this.BASE_IMG + this.formatParams(Object.assign({},QBFK.EventLog.baseParams(params),{ts:new Date().getTime()}));
                },
                formatParams:function(params){
                    var s = "";
                    for(var p in params){
                        s += (s.length > 0 ? '&' : '?') + p + '=' + params[p];
                    }
                    return s;
                },
                baseParams:function(params){
                    var ret = { }
                    var deviceId = params.deviceId || ''
                    var userId = params.userId || ''
                    if(QBFK.Util.getDevice() === 'android' && typeof QBaoJSBridge != 'undefined'){
                        deviceId = QBaoJSBridge.getDeviceId()
                        userId = QBaoJSBridge.getUserId()
                    }
                    else if(QBFK.Util.getDevice() === 'ios' && typeof ioswebview != 'undefined'){
                        deviceId = ioswebview.getDeviceId()
                        userId = ioswebview.getUserId()
                    }
                    
                    ret.userId =  userId //用户ID
                    ret.pageName = params.pageName || 101 //页面名称 101:聚好货、102: 热卖好货、103:钱宝自营、104:聚好店、105:我的定制、106聚好货活动页、107Banner活动页、108频道活动页、109我的好货、110店铺活动页
                    ret.model = params.model || 'default' //模块名称
                    ret.type = params.type || 'click' //类型 page, click

                    ret.id = params.id || 0 //商品Id
                    ret.deviceId = deviceId //设备号
                    ret.device = QBFK.Util.getDevice() //设备
                    ret.platform = 'qbao_stuff' //(平台名称: qbao_stuff)

                    return ret
                }
            }
        };