const app = getApp()
var temp;
Page({
    /**
     * 页面的初始数据
     */
    data: {
        date: '',
        time: '',
        barber: '',
        project: '',
        name_output: '',
        phone: '',
        demand: '',
        barber_level: '',
        order_price: 0,
        test: '',
        nick_name: '',
        user_balance: 0, //用户余额
        t1State:'',
        t2State:'',
        t3State:'',
        t4State:'',
        t5State:''
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        console.log('开始加载初始数据')
        console.log(options)
        this.setData({
            nick_name: app.globalData.nick_name
        })

        //设置上一个页面传过来的参数
        this.setData({
            date: options.date_input, //日期
            time: options.time_input, //时间
            barber: options.barber_input, //理发师
            project: options.project_input, //服务项目
            name_output: options.name_input, //名字
            phone: options.phone_input, //电话
            demand: options.demand_input //其他要求
        })
        console.log('日期：' + this.data.date)
        console.log('时间：' + this.data.time)
        console.log('理发师：' + this.data.barber)
        console.log('服务项目：' + this.data.project)
        console.log('名字：' + this.data.name_output)
        console.log('电话：' + this.data.phone)
        console.log('其他要求：' + this.data.demand)
        //根据理发师名字查询理发师的身份
        wx.request({
            url: 'http://h40421346u.wicp.vip:18232/getBarberByName/' + this.data.barber, //接口名称-所有理发师
            data: {

            },
            method: "GET",
            header: {
                'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
            },
            success: res => {
                this.setData({
                    barber_level: res.data.data[0].barberLevel
                })
                //根据服务项目和理发师等级得出订单金额
                wx.request({
                    url: 'http://h40421346u.wicp.vip:18232/getPriceByBarberLevelAndProject/' + this.data.barber_level + '/' + this.data.project, //接口名称-所有理发师
                    data: {

                    },
                    method: "GET",
                    header: {
                        'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                    },
                    success: (res) => {
                        console.log(res.data)
                        this.temp = res.data.data
                        this.setData({
                            //此时的order_price为object类型
                            order_price: res.data.data[0]
                        })
                        console.log('用户订单金额:' + this.data.order_price)
                        console.log('应该付金额' + this.temp)
                    }
                })
            }
        })
    },
    myclick_confirm: function () {
        var yuyue_flag = 0
        //首先判断当前理发师是否处于空闲状态
        wx.request({
            url: 'http://h40421346u.wicp.vip:18232/getBarberStateByBarberName/' + this.data.barber + '/' + this.data.date,
            method: 'GET',
            data: {},
            header: {
                'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
            },
            success: (res) => {
                this.setData({
                    t1State:res.data.data[0].t1State,
                    t2State:res.data.data[0].t2State,
                    t3State:res.data.data[0].t3State,
                    t4State:res.data.data[0].t4State,
                    t5State:res.data.data[0].t5State
                })
                console.log('理发师t1的空闲状态:' + res.data.data[0].t1State)
                console.log('用户选择的时间段:' + this.data.time)
                console.log('yuyue_flag:' + this.data.time)
                if (this.data.time == '8:00-10:00' && res.data.data[0].t1State == '空闲') {
                    yuyue_flag = 1
                    this.data.t1State = '繁忙'
                } else if (this.data.time == '10:00-12:00' && res.data.data[0].t2State == '空闲') {
                    yuyue_flag = 1
                    this.data.t1State = '繁忙'
                } else if (this.data.time == '14:00-16:00' && res.data.data[0].t3State == '空闲') {
                    yuyue_flag = 1
                    this.data.t1State = '繁忙'
                } else if (this.data.time == '16:00-18:00' && res.data.data[0].t4State == '空闲') {
                    yuyue_flag = 1
                    this.data.t1State = '繁忙'
                } else if (this.data.time == '20:00-22:00' && res.data.data[0].t5State == '空闲') {
                    yuyue_flag = 1
                    this.data.t1State = '繁忙'
                }
                console.log('yuyue_flag:' +yuyue_flag)
                //若美发师处于空闲状态
                if (yuyue_flag == 1) {
                    console.log('temp:' + this.temp[0])
                    console.log(typeof this.temp[0])
                    //按照昵称查询账户，在账户上扣除相应的金额
                    console.log('账户需要扣除的金额:' + this.data.order_price)
                    //根据昵称查询用户信息
                    wx.request({
                        url: 'http://h40421346u.wicp.vip:18232/findUserByNickName/' + this.data.nick_name, //接口名称-所有理发师
                        data: {},
                        header: {
                            'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                        },
                        success: (res) => {
                            console.log(res.data)
                            this.setData({
                                user_balance: res.data.data.userBalance
                            })
                            console.log('用户余额' + this.data.user_balance)
                            //计算扣除价格后的用户余额，若小于0则预约失败
                            var balance = this.data.user_balance - this.data.order_price
                            if (balance < 0) {
                                //余额不足，提示预约失败，余额不足
                                wx.showToast({
                                    title: '确认失败，余额不足',
                                    icon: 'none',
                                    duration: 2000 //持续的时间
                                })
                            } else {
        
                                //更新用余额后,将订单加入数据库，提示预约成功，还要将理发师状态变为繁忙状态
                                wx.request({
                                    url: 'http://h40421346u.wicp.vip:18232/updateUserBalanceByNickName', //接口名称  
                                    method: "POST",
                                    data: {
                                        nickName: this.data.nick_name,
                                        userBalance: balance
                                    },
                                    header: {
                                        'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                                    },
                                    success: (res) => {
                                        console.log(res.data)
                                        //订单入库
                                        //数据库中添加数据
        
                                        console.log('选择的理发师名字' + this.data.barber)
                                        wx.request({
                                            url: 'http://h40421346u.wicp.vip:18232/addOrder', //接口名称-所有理发师
                                            data: {
                                                nickName: this.data.nick_name,
                                                userName: this.data.name_output,
                                                appointmentDate: this.data.date,
                                                timeslot: this.data.time,
                                                barberName: this.data.barber,
                                                orderDetail: this.data.project,
                                                phone: this.data.phone,
                                                otherDemand: this.data.demand,
                                                orderPrice: this.temp[0]
                                            },
                                            method: "POST",
                                            header: {
                                                'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                                            },
                                            success: (res) => {
                                                console.log('订单数据添加成功')
                                                console.log(res.data.data)
                                                //将此理发师的状态变为繁忙状态
                                                wx.request({
                                                    url: 'http://h40421346u.wicp.vip:18232/updateStateByBarberName', //接口名称  
                                                    method: "POST",
                                                    data: {
                                                        theDate: this.data.date,
                                                        barberName: this.data.barber,
                                                        t1State:this.data.t1State,
                                                        t2State:this.data.t2State,
                                                        t3State:this.data.t3State,
                                                        t4State:this.data.t4State,
                                                        t5State:this.data.t5State
                                                    },
                                                    header: {
                                                        'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                                                    },
                                                    success: (res) => {
                                                        console.log(res.data)
                                                        console.log('理发师状态已经改变')
                                                    }
                                                })

                                            }
                                        })
                                        //提示加跳转
                                        console.log('订单确认成功，成功加入数据库')
                                        wx.showToast({
                                            title: '预约成功！',
                                            icon: 'success',
                                            duration: 1000 //持续的时间
                                        })
        
                                        setTimeout(function () {
                                            wx.switchTab({
                                                url: '/pages/homepage/homepage'
                                            })
                                        }, 1500)
        
                                    }
                                })
                            }
                        }
                    })
                } 
                //若美发师处于繁忙状态
                else {
                    //若没有选择金额，弹出警示文字
                    console.log('空闲标志flag：'+yuyue_flag)
                    wx.showToast({
                        title: '亲，理发师繁忙奥！请预约其他时间段或美发师',
                        icon: 'none',
                        duration: 1000 //持续的时间
                    })
                }
            }
        })





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
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

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