//获取项目的全局对象
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    canIUseGetUserProfile: false,
    canIUseOpenData: false,
    state_code: '', //状态码
    nick_name: '',
    header_url: '',
    user_message: {},
    user_balance: '',
    fuhao:'',
    user_category:''
    // canIUseOpenData: wx.canIUse('open-data.type.userAvatarUrl') && wx.canIUse('open-data.type.userNickName') // 如需尝试获取用户信息可改为false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //在页面加载时检测是否可以获取用户信息，若可以则将canIUseGetUserProfile设置为true
    if (wx.getUserProfile) {
      this.setData({
        canIUseGetUserProfile: true
      })
    }
  },
  //可以获取信息就会显示按钮，点击即可获取头像和用户昵称
  getUserProfile(e) {
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认，开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '展示用户信息', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        console.log(res)
        //打印头像地址和用户昵称
        console.log(res.userInfo.nickName)
        console.log(res.userInfo.avatarUrl)
        console.log('赋值前的全局昵称:'+app.globalData.nick_name)
        console.log('赋值前的全局头像地址:'+app.globalData.avatarUrl)
        //将昵称和头像地址赋值给项目全局变量
        app.globalData.nick_name=res.userInfo.nickName
        app.globalData.avatarUrl=res.userInfo.avatarUrl
        console.log('赋值后的全局昵称:'+app.globalData.nick_name)
        console.log('赋值后的全局头像地址:'+app.globalData.nick_name)
        console.log(app.globalData.avatarUrl)
        //设置昵称和头像为获取到的用户信息
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true,
          nick_name: res.userInfo.nickName,
          header_url: res.userInfo.avatarUrl
        })
        //首先要判断用户的信息已经在数据库中，查询数据库中是否具有用户昵称
        wx.request({
          url: 'http://h40421346u.wicp.vip:18232/validateStatus/' + this.data.nick_name, //接口名称  
          data: {},
          header: {
            'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
          },
          success: (res) => {
            console.log('打印后端端返回的数据' + res.data.data)
            console.log('打印状态码' + res.data.code)
            this.setData({
              state_code: res.data.code //给状态码赋值，后面作为判断的依据
              //res代表success函数的事件对，data是固定的，list是数组
            })
            console.log('获取后端返回的状态码：' + this.data.state_code)
            //1.若状态码为0，,则说明用户之前已经使用过本小程序，则直接调取用户信息
            if (this.data.state_code == 0) {
              console.log('你是本小程序的老用户啦!')
              //根据用户昵称在数据库中获取信息复制给user_message中
              wx.request({
                url: 'http://h40421346u.wicp.vip:18232/findUserByNickName/' + this.data.nick_name, //接口名称  
                data: {},
                header: {
                  'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                },
                success: (res) => {
                  console.log(res.data)
                  console.log(res.data.data)
                  console.log(res.data.data)
                  this.setData({
                    user_message: res.data.data,
                    user_category:res.data.data.userCategory,
                    user_balance: res.data.data.userBalance,
                    fuhao:'¥'
                    //res代表success函数的事件对，data是固定的，list是数组
                  })
                  console.log('获取的数据库中的用户昵称：' + this.data.user_message.nickName)
                  console.log(this.data.userBalance)
                }
              })
            }
            //2.若状态码为-1，说明用户是第一次使用小程序，则获取用户信息后通过后端存入数据库，然后再调取信息
            else if (this.data.state_code == -1) {
              console.log('你是第一次使用本小程序奥~')
              wx.request({
                url: 'http://h40421346u.wicp.vip:18232/addUser', //写自己的服务器
                header: {
                  "Content-Type": 'application/json'
                },
                method: "POST",
                data: {
                  nickName: this.data.nick_name,
                  headerUrl: this.data.header_url,
                },
                success: (res) => {
                  console.log("用户添加成功")
                  console.log('开始查询用户信息')

                  wx.request({
                    url: 'http://h40421346u.wicp.vip:18232/findUserByNickName/' + this.data.nick_name, //接口名称  
                    data: {},
                    header: {
                      'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                    },
                    success: (res) => {
                      console.log(res.data)
                      console.log(res.data.data)
                      this.setData({
                        user_message: res.data.data,
                        user_category:res.data.data.userCategory,
                        user_balance: res.data.data.userBalance,
                        fuhao:'¥'
                        //res代表success函数的事件对，data是固定的，list是数组
                      })
                      console.log('获取的数据库中的用户昵称：' + this.data.user_message.nickName)
                    }
                  })

                },
                fail: function () {
                  console.log("用户添加失败")
                }
              })

            }

          }
        })


      }
    })

  },
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

  //下拉刷新
  updateBlogs: function () {
    wx.request({
      url: 'http://h40421346u.wicp.vip:18232/findUserByNickName/' + this.data.nick_name, //接口名称  
      data: {},
      header: {
        'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
      },
      success: (res) => {
        console.log(res.data)
        console.log(res.data.data)
        this.setData({
          user_message: res.data.data,
          user_category:res.data.data.userCategory,
          user_balance:res.data.data.userBalance,
          fuhao:'¥'
          //res代表success函数的事件对，data是固定的，list是数组
        })
        console.log('获取的数据库中的用户昵称：' + this.data.user_message.nickName)
      }
    })
  },
  myclick_chongzhi: function () {
    if(app.globalData.nick_name==''){
      wx.showToast({
        title: '请先登录呀！',
        icon: 'none',
        duration: 1000 //持续的时间
      })
    }
    else{
      console.log(this.data.user_balance)
      console.log('跳转带参数-昵称：'+this.data.nick_name)
      wx.navigateTo({
        url: '/pages/wode_function/huiyuanchongzhi/chongzhi?user_balance='+this.data.user_balance
        +'&nick_name='+this.data.nick_name
        +'&user_category='+this.data.user_category
      })
    }
  },
  myclick_chongzhijilu:function(){
    if(app.globalData.nick_name==''){
      wx.showToast({
        title: '请先登录呀！',
        icon: 'none',
        duration: 1000 //持续的时间
      })
    }
    else{
      wx.navigateTo({
        url: '/pages/wode_function/chongzhijilu/chongzhijilu',
      })
    }
  },
  myclick_xiaofeijilu:function(){
    if(app.globalData.nick_name==''){
      wx.showToast({
        title: '请先登录呀！',
        icon: 'none',
        duration: 1000 //持续的时间
      })
    }
    else{
      wx.navigateTo({
        url: '/pages/wode_function/xiaofeijilu/xiaofeijilu',
      })
    }
  },
  myclikc_dingdan: function () {
    if(app.globalData.nick_name==''){
      wx.showToast({
        title: '请先登录呀！',
        icon: 'none',
        duration: 1000 //持续的时间
      })
    }
    else{
      wx.navigateTo({
        url: '/pages/wode_function/wodedingdan/dingdan',
      })
    }
  },
  myclikc_fuwu: function () {
    if(app.globalData.nick_name==''){
      wx.showToast({
        title: '请先登录呀！',
        icon: 'none',
        duration: 1000 //持续的时间
      })
    }
    else{
      wx.navigateTo({
        url: '/pages/wode_function/wodefuwu/fuwu',
      })
    }

  },
  myclick_fankui: function () {
    if(app.globalData.nick_name==''){
      wx.showToast({
        title: '请先登录呀！',
        icon: 'none',
        duration: 1000 //持续的时间
      })
    }
    else{
      wx.navigateTo({
        url: '/pages/wode_function/yijianfankui/fankui?nick_name='+this.data.nick_name,
      })
    }
  },
  myclick_shezhi:function(){
    wx.navigateTo({
      url: '/pages/wode_function/shezhi/shezhi',
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