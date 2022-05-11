const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        select_date: false, //日期下拉框伸展标志
        select_time: false, //时间下拉框伸展标志
        select_barber: false, //理发师下拉框伸展标志
        select_project: false, //服务项目下拉框伸展标志
        date_onedata: '请选择日期', //日期下拉框初始数据
        time_onedata: '请选择时间段', //时间下拉框初始数据
        barber_onedata: '请选择理发师',
        project_onedata: '选择服务项目',
    },
    //改变日期下拉菜单的伸展状态
    bindShowMsg_date() {
        this.setData({
            select_date: !this.data.select_date
        })
    },
    //日期下拉框判断选择了哪一条数据
    mySelect_date(e) {
        var name = e.currentTarget.dataset.name
        this.setData({
            date_onedata: name,
            select_date: false
        })
        console.log(name)
    },
    //改变时间段下拉菜单的伸展状态
    bindShowMsg_time() {
        this.setData({
            select_time: !this.data.select_time
        })
    },
    //时间段下拉框判断选择了哪一条数据
    mySelect_time(e) {
        var name = e.currentTarget.dataset.name
        this.setData({
            time_onedata: name,
            select_time: false
        })
        console.log(name)
    },


    //改变理发师菜单的伸展状态
    bindShowMsg_barber() {
        this.setData({
            select_barber: !this.data.select_barber
        })
    },
    //理发师下拉框判断选择了哪一条数据
    mySelect_barber(e) {
        var name = e.currentTarget.dataset.name
        this.setData({
            barber_onedata: name,
            select_barber: false
        })
        console.log('你选择了理发师：', name)
    },

    //改变服务项目菜单的伸展状态
    bindShowMsg_project() {
        this.setData({
            select_project: !this.data.select_project
        })
    },
    //服务项目下拉框判断选择了哪一条数据
    mySelect_project(e) {
        var name = e.currentTarget.dataset.name
        this.setData({
            project_onedata: name,
            select_project: false
        })
        console.log('你选择了服务项目：', name)
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
	},
        
    inputvalue_name: function (e) {
        this.setData({
            name_input: e.detail.value
        })
    },
    inputvalue_phone: function (e) {
        this.setData({
            phone_input: e.detail.value
        })
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

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