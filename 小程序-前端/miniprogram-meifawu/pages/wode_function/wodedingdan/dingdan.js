
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        nick_name:'',
        avatarUrl:'',
        myorders:[],
    },
  
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.setData({
            nick_name:app.globalData.nick_name,
            avatarUrl:app.globalData.avatarUrl
        })
        console.log(this.data.nick_name)
        console.log(this.data.avatarUrl)
        //检索订单数据
        wx.request({
            url: 'http://h40421346u.wicp.vip:18232/getOrderByNickName/'+this.data.nick_name, //接口名称  
            method: "GET",
            data: {
            },
            header: {
              'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
            },
            success: (res) => {
              console.log(res.data)
              this.setData({
                  myorders:res.data.data
              })
            }
          })
    },
    //评价
    myevaluate:function(e){
        console.log('用户点击了评价按钮')
        //判断当前选中的订单是否已经完成，若完成则可评价，否则不可
        var orderId =  e.currentTarget.dataset.name
        console.log(orderId)
        //根据orderId查询当前选中订单的订单信息
        wx.request({
            url: 'http://h40421346u.wicp.vip:18232/getOrderById/'+orderId, //接口名称  
            method: "GET",
            data: {
            },
            header: {
              'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
            },
            success: (res) => {
              console.log(res.data)
              console.log(res.data.data)
              console.log(res.data.data[0])
              console.log(res.data.data[0].orderState)
              var orderState = res.data.data[0].orderState
              //若订单未完成
              if(orderState == '未完成'){
                wx.showToast({
                    title: '亲，订单还未完成！',
                    icon: 'none',
                    duration: 1000 //持续的时间
                  })
              }
              //订单完成，跳转至评价界面
              else{
                  wx.navigateTo({
                    url: '/pages/wode_function/pingjia/pingjia',
                  })
              }

            }
          })
        
    },
    //再来一单,直接跳转至预约界面
    myrepeat:function(){
        wx.switchTab({
          url: '/pages/yuyuepage/yuyue',
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