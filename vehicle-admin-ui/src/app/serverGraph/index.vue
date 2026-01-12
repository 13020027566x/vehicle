<template>
  <div style="width:100%;height:100%;" id="server_graph"></div>
</template>

<script>
import { mapState } from 'vuex'
import * as echarts from 'echarts'
import serverGraphData from '@/mock/data/server-graph'

export default {
  name: 'serverGraph',
  computed: mapState([
    'serverGraphStore'
  ]),
  data () {
    return {
      //
    }
  },
  mounted () {
    this.$nextTick(() => {
      let chartDom = document.getElementById('server_graph')
      let myChart = echarts.init(chartDom)

      myChart.showLoading()

      let graph = serverGraphData
      graph.nodes.forEach(function (node) {
        node.label = {
          show: node.value > 20
        }
      })

      myChart.hideLoading()

      let option = {
        title: {
          text: 'Finhub Server Graph',
          subtext: 'Circular layout',
          top: 'bottom',
          left: 'right'
        },
        tooltip: {},
        legend: [
          {
            data: graph.categories.map(function (a) {
              return a.name
            })
          }
        ],
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [
          {
            type: 'graph',
            layout: 'circular',
            circular: {
              rotateLabel: true
            },
            data: graph.nodes,
            links: graph.links,
            categories: graph.categories,
            roam: true,
            label: {
              position: 'right',
              formatter: '{b}'
            },
            edgeSymbol: ['circle', 'arrow'],
            edgeSymbolSize: [3, 8],
            scaleLimit: {
              min: 0.4,
              max: 2
            },
            lineStyle: {
              color: 'source',
              curveness: 0.3
            },
            emphasis: {
              focus: 'adjacency',
              lineStyle: {
                width: 6
              }
            }
          }
        ]
      }
      myChart.setOption(option)

      option && myChart.setOption(option)
    })
  }
}
</script>
