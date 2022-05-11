Page({
  data: {
    productInfo: {}
  },
  //上传图片
  uploadImage: function () {
    var that = this;
   
    wx.chooseImage({
      count: 1,  //最多可以选择的图片总数
      sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths;
        //启动上传等待中...
        wx.showToast({
          title: '正在上传...',
          icon: 'loading',
          mask: true,
          duration: 10000
        })
   
          wx.uploadFile({
            url: 'http://h40421346u.wicp.vip:18232/fileupload',
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
            },
            header: {
              "Content-Type": "multipart/form-data"
            },
            success: function (res) {
              var data = JSON.parse(res.data);
              //服务器返回格式: { "Catalog": "testFolder", "FileName": "1.jpg", "Url": "https://test.com/1.jpg" }
              console.log(data);
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
    });
  }
  })
  