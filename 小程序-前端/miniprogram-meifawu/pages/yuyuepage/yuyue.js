const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        select_date: false, //日期下拉框伸展标志
        select_time: false, //时间下拉框伸展标志
        select_barber: false, //理发师下拉框伸展标志
        select_project: false, //服务项目下拉框伸展标志
        date_onedata: '请你选择日期', //日期下拉框初始数据
        time_onedata: '请选择时间段', //时间下拉框初始数据
        barber_onedata: '请选择理发师',
        project_onedata: '选择服务项目',
        mydates: [],
        mybarbers: [],
        myproject: [],
        date_input: '', //传递给下一个页面的参数
        time_input: '',
        barber_input: '',
        project_input: '',
        name_input: '',
        phone_input: '',
        demand_input: '',
        texts: "你有要求，我们就有服务！",
        min: 0, //最少字数
        max: 300, //最多字数 (根据自己需求改变) 
    },
    //改变日期下拉菜单的伸展状态
    bindShowMsg_date() {
        this.setData({
            select_date: !this.data.select_date
        })
    },
    //日期下拉框判断选择了哪一条数据
    mySelect_date(e) {
        var name = e.currentTarget.dataset.name
        this.setData({
            date_onedata: name,
            select_date: false
        })
        console.log(name)
    },
    //改变时间段下拉菜单的伸展状态
    bindShowMsg_time() {
        this.setData({
            select_time: !this.data.select_time
        })
    },
    //时间段下拉框判断选择了哪一条数据
    mySelect_time(e) {
        var name = e.currentTarget.dataset.name
        this.setData({
            time_onedata: name,
            select_time: false
        })
        console.log(name)
    },


    //改变理发师菜单的伸展状态
    bindShowMsg_barber() {
        this.setData({
            select_barber: !this.data.select_barber
        })
    },
    //理发师下拉框判断选择了哪一条数据
    mySelect_barber(e) {
        var name = e.currentTarget.dataset.name
        this.setData({
            barber_onedata: name,
            select_barber: false
        })
        console.log('你选择了理发师：', name)
    },

    //改变服务项目菜单的伸展状态
    bindShowMsg_project() {
        this.setData({
            select_project: !this.data.select_project
        })
    },
    //服务项目下拉框判断选择了哪一条数据
    mySelect_project(e) {
        var name = e.currentTarget.dataset.name
        this.setData({
            project_onedata: name,
            select_project: false
        })
        console.log('你选择了服务项目：', name)
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        //项目全局变量中获取用户昵称，若昵称为空，跳转到提示登录页面
        if(app.globalData.nick_name==''){
            wx.navigateTo({
              url: '/pages/yuyue_function/judgelogin/judge',
            })
        }
        //计算今天、明天、后天的日期
        this.jisuan_date();

        //请求全部理发师信息
        wx.request({
                url: 'http://h40421346u.wicp.vip:18232/getAllBarber', //接口名称-所有理发师
                data: {

                },
                header: {
                    'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                },
                success: (res) => {
                    console.log(res.data)
                    this.setData({
                        mybarbers: res.data.data
                        //res代表success函数的事件对，data是固定的，list是数组
                    })
                    // console.log(this.data.mybarbers[1].barberName)
                }
            }),

            //请求全部价目表中的服务项目信息
            wx.request({
                url: 'http://h40421346u.wicp.vip:18232/getAllProject', //接口名称-所有理发师
                data: {

                },
                header: {
                    'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                },
                success: (res) => {
                    console.log(res.data)
                    this.setData({
                        myproject: res.data.data
                        //res代表success函数的事件对，data是固定的，list是数组
                    })
                    //  console.log(this.data.myproject[1].pricelistWay)
                }
            })
        
    },
    //监听用户下拉动作的函数

    onPullDownRefresh: function () {
        wx.showNavigationBarLoading() //在标题栏中显示加载
        //下拉刷新时所调用的函数
        this.updateBlogs() //重新加载数据
        //模拟加载  1秒
        setTimeout(function () {
            // complete
            wx.hideNavigationBarLoading() //完成停止加载
            wx.stopPullDownRefresh() //停止下拉刷新
        }, 1000);
    },
    //下拉刷新重新请求数据
    updateBlogs: function () {
        var that = this
        wx.request({
            url: 'http://h40421346u.wicp.vip:18232/getAllBarber', //接口名称-所有理发师
            data: {

            },
            header: {
                'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
            },
            success: (res) => {
                console.log(res.data)
                this.setData({
                    mybarbers: res.data.data
                    //res代表success函数的事件对，data是固定的，list是数组
                })
                console.log("用户下拉刷新，已经重新请求数据")
                //文本框输入的信息
                console.log('选择的服务日期为' + this.data.date_onedata)
                console.log('选择的服务时间为' + this.data.time_onedata)
                console.log('选择的美发师为' + this.data.barber_onedata)
                console.log('选择的服务项目为' + this.data.project_onedata)
                console.log('输入的姓名为:' + this.data.name_input)
                console.log('输入的电话为:' + this.data.phone_input)
                console.log('输入的需求：' + this.data.demand_input)
            }
        })
    },
    inputvalue_name: function (e) {
        this.setData({
            name_input: e.detail.value
        })
    },
    inputvalue_phone: function (e) {
        this.setData({
            phone_input: e.detail.value
        })
    },
    //文本域的相关函数
    //字数限制  
    inputs: function (e) {
        this.setData({
            demand_input: e.detail.value
        })
        // 获取输入框的内容
        var value = e.detail.value;
        // 获取输入框内容的长度
        var len = parseInt(value.length);
        //最少字数限制
        if (len <= this.data.min)
            this.setData({
                texts: "你在打字，我们在倾听......"
            })
        else if (len > this.data.min)
            this.setData({
                texts: " "
            })
        //最多字数限制
        if (len > this.data.max) return;
        // 当输入框内容的长度大于最大长度限制（max)时，终止setData()的执行
        this.setData({
            currentWordNumber: len //当前字数  
        });
        // console.log(len)
    },
    //跳转页面并且传递参数
    myclick_dingdan: function () {
        wx.navigateTo({
            url: '../yuyue_function/orderpage/order?time_input=' + this.data.time_onedata +
                '&barber_input=' + this.data.barber_onedata +
                '&project_input=' + this.data.project_onedata +
                '&name_input=' + this.data.name_input +
                '&phone_input=' + this.data.phone_input +
                '&demand_input=' + this.data.demand_input+
                '&date_input='+this.data.date_onedata
        })
    },
    jisuan_date() {
        //今天的时间
        var day1 = new Date();
        day1.setTime(day1.getTime());
        var s1 = day1.getFullYear() + "-" + (day1.getMonth() + 1) + "-" + day1.getDate();

        //明天的时间
        var day2 = new Date();
        day2.setTime(day2.getTime() + 24 * 60 * 60 * 1000);
        var s2 = day2.getFullYear() + "-" + (day2.getMonth() + 1) + "-" + day2.getDate();

        //后天的时间
        var day3 = new Date();
        day3.setTime(day3.getTime() + 24 * 60 * 60 * 1000 * 2);
        var s3 = day3.getFullYear() + "-" + (day3.getMonth() + 1) + "-" + day3.getDate();
        var array = [s1,s2,s3]
        this.setData({
            mydates:array
        })
        console.log(this.data.mydates)
        //时间的格式为 xxxx-xx-xx 即 年-月-日， 小程序picker中经常可以用到
        console.log('今天的时间：' + s1)
        console.log('明天的时间：' + s2)
        console.log('后天的时间：' + s3)
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
        if(app.globalData.nick_name==''){
            wx.switchTab({
              url: '/pages/wodepage/wode',
            })
        }
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})