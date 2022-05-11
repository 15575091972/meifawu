// pages/yijianfankui/fankui.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    texts: "至少5个字",
    min: 5, //最少字数
    max: 520, //最多字数 (根据自己需求改变) 
    max2: 11,
    input_question: '',
    input_phone: '',
    nick_name:''
  },

  //字数限制  
  inputs: function (e) {
    // 获取输入框的内容
    var value = e.detail.value;
    // 获取输入框内容的长度
    var len = parseInt(value.length);
    this.setData({
      input_question: value
    })
    console.log('获取的意见:' + this.data.input_question)
    //最少字数限制
    if (len < this.data.min)
      this.setData({
        texts: "至少5个字"
      })
    else if (len >= this.data.min)
      this.setData({
        texts: "感谢！您的反馈与意见我们会悉心听取~"
      })

    //最多字数限制
    if (len > this.data.max) return;
    // 当输入框内容的长度大于最大长度限制（max)时，终止setData()的执行
    this.setData({
      currentWordNumber: len //当前字数  
    });
  },
  //获取输入的电话号码
  input_phone: function (e) {
    var phone = e.detail.value
    this.setData({
      input_phone: phone
    })
    console.log('获取的电话：' + this.data.input_phone)
  },
  submit_advice: function () {
    var len = parseInt(this.data.input_question.length)
    if (this.data.input_question == '' ){
      wx.showToast({
        title: '你还没有填写意见！',
        icon: 'none',
        duration: 2000 //持续的时间
      })
    }
    else if(len<5){
      wx.showToast({
        title: '你填写的意见太短啦！',
        icon: 'none',
        duration: 2000 //持续的时间
      })
    }
     else {
      wx.showModal({
        title: '意见反馈',
        content: '确认要提交你的意见吗？',
        success: res => {
          if (res.confirm) {
            console.log('点击确认操作')
            console.log(this.data.input_question)
            console.log(this.data.input_phone)
            //将建议存储到数据库
            wx.request({
              url: 'http://h40421346u.wicp.vip:18232/addAdvice', //接口名称  
              method: "POST",
              data: {
                nickName: this.data.nick_name,
                adviceDetail: this.data.input_question,
                phone: this.data.input_phone
              },
              header: {
                'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
              },
              success: (res) => {
                console.log(res.data)
              }
            })
          } else {
            console.log('点击取消操作')
          }
        }
      })
    }


  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.setData({
        nick_name:options.nick_name
      })
      console.log(this.data.nick_name)
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