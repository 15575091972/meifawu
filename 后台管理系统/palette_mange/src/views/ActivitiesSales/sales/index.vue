<template>
  <!--为echarts准备一个具备大小的容器dom-->
  <div id="main" style="width: 1000px;height: 400px;" />
</template>

<script>
import * as echarts from 'echarts'
export default {
  name: '',
  data() {
    return {
      charts: '',
      /*  opinion: ["1", "3", "3", "4", "5"],*/
      opinionData: ['3', '2', '4', '4', '5', '12', '7']
    }
  },
  // 调用
  mounted() {
    this.$nextTick(function() {
      this.drawLine('main')
    })
  },
  methods: {
    drawLine(id) {
      this.charts = echarts.init(document.getElementById(id))
      this.charts.setOption({
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['近一周收益']
        },
        grid: {
          left: '2%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },

        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['1', '2', '3', '4', '5', '6', '7']

        },
        yAxis: {
          type: 'value'
        },

        series: [{
          name: '近一周收益',
          type: 'line',
          stack: '总量',
          data: this.opinionData
        }]
      })
    }
  }
}
</script>
<style scoped>
    * {
        margin: 0;
        padding: 0;
        list-style: none;
    }
</style>
