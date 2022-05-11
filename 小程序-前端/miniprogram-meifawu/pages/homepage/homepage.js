// pages/homepage/homepage.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
      indicatorDots: false,
      vertical: true,
      autoplay: true,
      interval: 3000,
      duration: 1000,
      circular: true,
      gongshis: []
    },
  
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
      wx.request({
        url: 'http://h40421346u.wicp.vip:18232/getAllPublicity', //接口名称  
        method: "GET",
        header: {
          'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
        },
        success: res => {
          console.log(res.data)
          this.setData({
            gongshis: res.data.data
            //res代表success函数的事件对，data是固定的，list是数组
          })
          console.log(res.data.data[0].publicityName)
        }
      })
    },
    //点击跳转到详情页面
    getDetail: function (event) {
      var name =  event.currentTarget.dataset.name
      console.log("event")
      console.log(name)
      wx.navigateTo({
        url: '../zhuye_function/meifawu_gongshi/meifawu_gongshi?publicityId='+name,
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
        wx.request({
          url: 'http://h40421346u.wicp.vip:18232/getAllPublicity', //接口名称  
          method: "GET",
          header: {
            'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
          },
          success: res => {
            console.log(res.data)
            this.setData({
              gongshis: res.data.data
              //res代表success函数的事件对，data是固定的，list是数组
            })
            console.log(res.data.data[0].publicityName)
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
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {
  
    },
  
    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {
  
    },
  
    myclick_location: function (options) {
      wx.navigateTo({
        url: '../zhuye_function/meifawu_location/meifawu_location'
      })
    },
  
    myclick_jianjie: function (options) {
      wx.navigateTo({
        url: '../zhuye_function/meifawu_jianjie/meifawu_jianjie'
      })
    },
    myclick_gongshi: function (options) {
      wx.navigateTo({
        url: '../zhuye_function/meifawu_gongshi/meifawu_gongshi'
      })
    },
    myclick_state: function (options) {
      wx.navigateTo({
        url: '../zhuye_function/meifawu_state/meifawu_state'
      })
    }
  })