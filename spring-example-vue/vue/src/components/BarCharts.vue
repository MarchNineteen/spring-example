<template>
  <div style="display: flex;height: 500px;width: 100%;align-items: center;justify-content: center;">
    <chart ref="dschart" :options="polar" style="margin-top: 20px"></chart>
  </div>
</template>

<script>
  import ECharts from 'vue-echarts/components/ECharts.vue'
  import 'echarts/theme/dark'

  export default {
    name: "Echarts",
    components: {
      'chart': ECharts
    },
    methods: {},
    data: function () {
      return {
        polar: {
          xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'bar'
          }]
        }
      }
    }
  }
</script>


<style scoped>

</style>