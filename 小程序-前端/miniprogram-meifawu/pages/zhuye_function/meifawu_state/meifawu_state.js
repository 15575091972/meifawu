// pages/zhuye_function/meifawu_state/meifawu_state.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        barber_total_count:0
    },
    myclick_barbermessage:function(){
        wx.navigateTo({
          url: '/pages/yuyue_function/barber_message/barber_message',
        })
    },
    myclick_timedetail:function(){
        wx.navigateTo({
          url: '/pages/zhuye_function/meifawu_timedetail/meifawu_timedetail',
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        wx.request({
            url: 'http://h40421346u.wicp.vip:18232/getAllBarberForCount',
            data:{
      
            },
            method:"GET",
            success:(res)=>{
                console.log(res.data.data)
                this.setData({
                    barber_total_count:res.data.data
                })
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
        wx.request({
            url: 'http://h40421346u.wicp.vip:18232/getAllBarberForCount',
            data:{
      
            },
            method:"GET",
            success:(res)=>{
                console.log(res.data.data)
                this.setData({
                    barber_total_count:res.data.data
                })
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

    }
})