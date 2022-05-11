const util = require('../../../utils/util')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user_balance: '',
    select_jine: '',
    fuhao: '',
    fuhao_init: '',
    showModalStatus: false,
    qita_jine: '',
    nick_name: '',
    user_category: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    console.log(options.user_balance)
    this.setData({
      user_balance: options.user_balance,
      user_category: options.user_category,
      nick_name: options.nick_name,
      fuhao_init: '¥'
    })
    console.log('用户余额：' + this.data.user_balance)
    console.log('用户昵称：' + this.data.nick_name)
    console.log('用户当前身份：' + this.data.user_category)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  select_money: function (e) {
    console.log(e.currentTarget.dataset.name)
    var select_money = e.currentTarget.dataset.name
    this.setData({
      select_jine: select_money,
      fuhao: '¥'
    })
    console.log('用户选择的金额：' + this.data.select_jine)
  },

  chongzhi_submit: function () {
    if (this.data.select_jine == '') {
      //若没有选择金额，弹出警示文字
      wx.showToast({
        title: '请先选择充值的金额！',
        icon: 'none',
        duration: 2000 //持续的时间
      })
    } else {
      console.log('用户最终要充值的金额：' + this.data.select_jine)
      wx.showModal({
        title: '确认要充值' + this.data.fuhao + this.data.select_jine + '吗？',
        content: '充值满100元即可成为会员！',
        success: res => {
          if (res.confirm) {
            console.log('点击确认操作')
            //得出用户充值后的总余额
            var chongzhi_money = this.data.select_jine
            var init_money = this.data.user_balance
            chongzhi_money = parseInt(chongzhi_money)
            init_money = parseInt(init_money)
            console.log(typeof chongzhi_money)
            console.log(typeof init_money)
            var current_money = chongzhi_money + init_money
            console.log('用户现在的余额:' + current_money)
            //若用户当前不是会员
            if (this.data.user_category != '会员') {
              //若此次充值金额小于100则不能成为会员
              if (chongzhi_money < 100) {
                wx.request({
                  url: 'http://h40421346u.wicp.vip:18232/updateUserByName', //接口名称  
                  method: "POST",
                  data: {
                    nickName: this.data.nick_name,
                    userCategory: '非会员',
                    userBalance: current_money
                  },
                  header: {
                    'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                  },
                  success: (res) => {
                    //充值后将记录加入充值记录表
                    console.log(res.data)
                    //获取当前日期和时间
                    var dateandtime = util.formatTime(new Date());
                    console.log('当前的日期和时间:' + dateandtime)
                    wx.request({
                      url: 'http://h40421346u.wicp.vip:18232/addInvestrecord', //接口名称  
                      method: "POST",
                      data: {
                        nickName: this.data.nick_name,
                        investDate: dateandtime,
                        investMoney: this.data.select_jine
                      },
                      header: {
                        'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                      },
                      success: (res) => {
                        console.log(res)
                        //充值后将记录加入充值记录表
                      }


                    })
                  }
                })

              }
              //若此次充值金额大于100则能成为会员
              else {
                wx.request({
                  url: 'http://h40421346u.wicp.vip:18232/updateUserByName', //接口名称  
                  method: "POST",
                  data: {
                    nickName: this.data.nick_name,
                    userCategory: '会员',
                    userBalance: current_money
                  },
                  header: {
                    'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                  },
                  success: (res) => {
                    //充值后将记录加入充值记录表
                    console.log(res.data)
                    //获取当前日期和时间
                    var dateandtime = util.formatTime(new Date());
                    console.log('当前的日期和时间:' + dateandtime)
                    wx.request({
                      url: 'http://h40421346u.wicp.vip:18232/addInvestrecord', //接口名称  
                      method: "POST",
                      data: {
                        nickName: this.data.nick_name,
                        investDate: dateandtime,
                        investMoney: this.data.select_jine
                      },
                      header: {
                        'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                      },
                      success: (res) => {
                        console.log(res)
                        //充值后将记录加入充值记录表
                      }
                    })
                  }
                })
              }

            }
            //若用户当前是会员
            else {
              wx.request({
                url: 'http://h40421346u.wicp.vip:18232/updateUserByName', //接口名称  
                method: "POST",
                data: {
                  nickName: this.data.nick_name,
                  userCategory: '会员',
                  userBalance: current_money
                },
                header: {
                  'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                },
                success: (res) => {
                  //充值后将记录加入充值记录表
                  console.log(res.data)
                  //获取当前日期和时间
                  var dateandtime = util.formatTime(new Date());
                  console.log('当前的日期和时间:' + dateandtime)
                  wx.request({
                    url: 'http://h40421346u.wicp.vip:18232/addInvestrecord', //接口名称  
                    method: "POST",
                    data: {
                      nickName: this.data.nick_name,
                      investDate: dateandtime,
                      investMoney: this.data.select_jine
                    },
                    header: {
                      'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                    },
                    success: (res) => {
                      console.log(res)
                      //充值后将记录加入充值记录表
                    }


                  })
                }
              })
            }
            //充值成功后返回我的界面
            wx.showToast({
              title: '充值成功！',
              icon: 'success',
              duration: 1000 //持续的时间
            })

            setTimeout(function () {
              wx.switchTab({
                url: '/pages/wodepage/wode'
              })
            }, 1500)

          } else {
            console.log('点击取消操作')
          }
        }
      })
    }


  },
  showDialogBtn: function () {
    this.setData({
      showModal: true
    })
  },
  /**
   * 弹出框蒙层截断touchmove事件
   */
  preventTouchMove: function () {},
  /**
   * 隐藏模态对话框
   */
  hideModal: function () {
    this.setData({
      showModal: false
    });
  },
  /**
   * 对话框取消按钮点击事件
   */
  onCancel: function () {
    this.hideModal();
  },
  /**
   * 对话框确认按钮点击事件
   */
  onConfirm: function () {
    if (this.data.qita_jine == '') {
      //若没有输入金额，弹出警示文字
      wx.showToast({
        title: '请先选择充值的金额！',
        icon: 'none',
        duration: 1000 //持续的时间
      })
    } else {
      //得出用户充值后的总余额
      var chongzhi_money = this.data.qita_jine
      var init_money = this.data.user_balance
      chongzhi_money = parseInt(chongzhi_money)
      init_money = parseInt(init_money)
      console.log(typeof chongzhi_money)
      console.log(typeof init_money)
      var current_money = chongzhi_money + init_money
      console.log('用户现在的余额:' + current_money)

      //若用户当前不是会员
      if (this.data.user_category != '会员') {
        //若此次充值金额小于100则不能成为会员
        if (chongzhi_money < 100) {
          wx.request({
            url: 'http://h40421346u.wicp.vip:18232/updateUserByName', //接口名称  
            method: "POST",
            data: {
              nickName: this.data.nick_name,
              userCategory: '非会员',
              userBalance: current_money
            },
            header: {
              'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
            },
            success: (res) => {
              //充值后将记录加入充值记录表
              console.log(res.data)
              //获取当前日期和时间
              var dateandtime = util.formatTime(new Date());
              console.log('当前的日期和时间:' + dateandtime)
              wx.request({
                url: 'http://h40421346u.wicp.vip:18232/addInvestrecord', //接口名称  
                method: "POST",
                data: {
                  nickName: this.data.nick_name,
                  investDate: dateandtime,
                  investMoney: this.data.qita_jine
                },
                header: {
                  'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                },
                success: (res) => {
                  console.log(res)
                  //充值后将记录加入充值记录表
                }


              })
            }
          })

        }
        //若此次充值金额大于100则能成为会员
        else {
          wx.request({
            url: 'http://h40421346u.wicp.vip:18232/updateUserByName', //接口名称  
            method: "POST",
            data: {
              nickName: this.data.nick_name,
              userCategory: '会员',
              userBalance: current_money
            },
            header: {
              'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
            },
            success: (res) => {
              //充值后将记录加入充值记录表
              console.log(res.data)
              //获取当前日期和时间
              var dateandtime = util.formatTime(new Date());
              console.log('当前的日期和时间:' + dateandtime)
              wx.request({
                url: 'http://h40421346u.wicp.vip:18232/addInvestrecord', //接口名称  
                method: "POST",
                data: {
                  nickName: this.data.nick_name,
                  investDate: dateandtime,
                  investMoney: this.data.qita_jine
                },
                header: {
                  'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
                },
                success: (res) => {
                  console.log(res)
                  //充值后将记录加入充值记录表
                }


              })
            }
          })
        }

      }
      //若用户当前是会员
      else {
        wx.request({
          url: 'http://h40421346u.wicp.vip:18232/updateUserByName', //接口名称  
          method: "POST",
          data: {
            nickName: this.data.nick_name,
            userCategory: '会员',
            userBalance: current_money
          },
          header: {
            'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
          },
          success: (res) => {
            //充值后将记录加入充值记录表
            console.log(res.data)
            //获取当前日期和时间
            var dateandtime = util.formatTime(new Date());
            console.log('当前的日期和时间:' + dateandtime)
            wx.request({
              url: 'http://h40421346u.wicp.vip:18232/addInvestrecord', //接口名称  
              method: "POST",
              data: {
                nickName: this.data.nick_name,
                investDate: dateandtime,
                investMoney: this.data.qita_jine
              },
              header: {
                'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
              },
              success: (res) => {
                console.log(res)
                //充值后将记录加入充值记录表
              }


            })
          }
        })
      }

      //充值成功后返回我的界面
      wx.showToast({
        title: '充值成功！',
        icon: 'success',
        duration: 1000 //持续的时间
      })

      setTimeout(function () {
        wx.switchTab({
          url: '/pages/wodepage/wode'
        })
      }, 1500)

      this.hideModal();
      console.log('成功充值' + this.data.qita_jine + '元')

    }

  },
  inputChange: function (e) {
    console.log(e.detail.value)
    this.setData({
      qita_jine: e.detail.value
    })
    console.log('用户自己输入的金额' + this.data.qita_jine)
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