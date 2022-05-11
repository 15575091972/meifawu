// pages/meifawu_gongshi/meifawu_gongshi.js
Page({

  /**
   * 组件的初始数据
   */
  data: {
    publicityId:'',
    mygongshi:[]
  },
  onLoad: function (options) {
    console.log('11111')
    console.log(options)
    //设置上一个页面传过来的参数
    this.setData({
      publicityId: options.publicityId,
    })
    wx.request({
      url: 'http://h40421346u.wicp.vip:18232/getPublicityById/'+ this.data.publicityId,
      header: {
        'content-type': 'application/json'
      },
      success:res=> {
        console.log('请求成功')
        console.log(res.data.data)
        this.setData({
          mygongshi: res.data.data,
        })
      }
    })
  }
})