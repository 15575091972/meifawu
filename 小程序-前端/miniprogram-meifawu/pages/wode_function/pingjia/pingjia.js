const app = getApp()
Page({
  data: {
    texts:"至少5个字",
    min:5,//最少字数
    max: 300, //最多字数 (根据自己需求改变,
    currentWordNumber:0,
    evaluate_contant: ['整洁', '速度', '耐心',],
    stars: [0, 1, 2, 3, 4],
    normalSrc: '/images/icon/rate_gray.png',
    selectedSrc: '/images/icon/rate_light.png',
    halfSrc: '/images/icon/rate_half.png',
    score: 1,
    scores: [3.5, 0, 0],
    image_address:'http://h40421346u.wicp.vip:18232/',
    image_name:'',
    environment:0,
    skill:0,
    serve:0,
    content:'',
    nick_name:''
  },
 
  //字数限制  
  inputs: function (e) {
    // 获取输入框的内容
    var value = e.detail.value;
    console.log(value)
    this.setData({
      content:value
    })
    // 获取输入框内容的长度
    var len = parseInt(value.length);
    //最少字数限制
    if(len <= this.data.min) 
        this.setData({
          texts: "加油，够5个字可以得20积分哦"
        })
    else if(len > this.data.min)
    this.setData({
      texts: " "
    })
    //最多字数限制
    if(len > this.data.max) return;
        // 当输入框内容的长度大于最大长度限制（max)时，终止setData()的执行
        this.setData({
           currentWordNumber: len //当前字数  
        });
        console.log(this.data.currentWordNumber)
  }
  ,
  // 提交事件
  submit_evaluate: function () {
    var name = app.globalData.nick_name
    this.setData({
      environment:this.data.scores[0],
      skill:this.data.scores[1],
      serve:this.data.scores[2],
      nick_name:name
    })
    console.log('用户昵称:' + this.data.nick_name)
    console.log('环境评价得分:' + this.data.environment)
    console.log('技术评价得分:' + this.data.skill)
    console.log('服务评价得分:' + this.data.serve)
    console.log('文本域输入的内容:'+this.data.content)
    console.log('图片的网络路径'+this.data.image_address+this.data.image_name)
    //网络请求上传数据
    wx.request({
      url: 'http://h40421346u.wicp.vip:18232/addAssess',
      method:"POST",
      data:{
        nickName:this.data.nick_name,
        assessLevelEnvironment:this.data.environment,
        assessLevelSkill:this.data.skill,
        assessLevelServe:this.data.serve,
        assessContent:this.data.content,
        assessPicture:this.data.image_address+this.data.image_name
      }, 
       header: {
        'content-type': 'application/json' // 默认值（固定，我开发过程中还没有遇到需要修改header的）     
      },
      success:(res)=>{
          console.log('评价添加成功')
          wx.showToast({
            title: '评价成功！',
            icon: 'success',
            duration: 1000 //持续的时间
          })
    
          setTimeout(function () {
            wx.switchTab({
              url: '/pages/wodepage/wode'
            })
          }, 1500)

      }

    })
  },

  //点击左边,半颗星
  selectLeft: function (e) {
    var score = e.currentTarget.dataset.score
    if (this.data.score == 0.5 && e.currentTarget.dataset.score == 0.5) {
      score = 0;
    }
    this.data.scores[e.currentTarget.dataset.idx] = score,
      this.setData({
        scores: this.data.scores,
        score: score
      })
  },
  //点击右边,整颗星
  selectRight: function (e) {
    var score = e.currentTarget.dataset.score
    this.data.scores[e.currentTarget.dataset.idx] = score,
      this.setData({
        scores: this.data.scores,
        score: score
      })
  },
  /**
   * 上传图片方法
   */
  upload: function () {
    let that = this;
    wx.chooseImage({
      count: 9, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: res => {
        wx.showToast({
          title: '正在上传...',
          icon: 'loading',
          mask: true,
          duration: 1000
        })
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        let tempFilePaths = res.tempFilePaths;

        that.setData({
          tempFilePaths: tempFilePaths
        })
        /**
         * 上传完成后把文件上传到服务器
         */
        var count = 0;
        for (var i = 0, h = tempFilePaths.length; i < h; i++) {
          //上传文件
            wx.uploadFile({
              url:'http://h40421346u.wicp.vip:18232/fileupload',
              filePath: tempFilePaths[i],
              name: 'file',
              header: {
                "Content-Type": "multipart/form-data"
              },
              success:  (res)=> {
                console.log(res)
                console.log(res.data)
                this.setData({
                  image_name:res.data
                })
                count++;
                //如果是最后一张,则隐藏等待中  
                if (count == tempFilePaths.length) {
                  wx.hideToast();
                }

              },
              fail: function (res) {
                wx.hideToast();
                wx.showModal({
                  title: '错误提示',
                  content: '上传图片失败',
                  showCancel: false,
                  success: function (res) { }
                })
              }
            });
        }

      }
    })
  },
  /**
   * 预览图片方法
   */
  listenerButtonPreviewImage: function (e) {
    let index = e.target.dataset.index;
    let that = this;
    console.log(that.data.tempFilePaths[index]);
    console.log(that.data.tempFilePaths);
    wx.previewImage({
      current: that.data.tempFilePaths[index],
      urls: that.data.tempFilePaths,
      //这根本就不走
      success: function (res) {
        //console.log(res);
      },
      //也根本不走
      fail: function () {
        //console.log('fail')
      }
    })
  },
  /**
   * 长按删除图片
   */
  deleteImage: function (e) {
    var that = this;
    var tempFilePaths = that.data.tempFilePaths;
    var index = e.currentTarget.dataset.index;//获取当前长按图片下标
    wx.showModal({
      title: '提示',
      content: '确定要删除此图片吗？',
      success: function (res) {
        if (res.confirm) {
          console.log('确定');
          tempFilePaths.splice(index, 1);
        } else if (res.cancel) {
          console.log('取消');
          return false;
        }
        that.setData({
          tempFilePaths
        });
      }
    })
  }
})
