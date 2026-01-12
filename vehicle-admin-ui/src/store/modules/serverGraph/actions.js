import types from './types'
import Model from '../../../models/serverGraph'

export default {
  /**
   * 获取服务拓扑 JSON
   */
  getServerGraph ({ commit }, options) {
    commit(types.GET_SERVER_GRAPH, {})
    return new Model().addPath('master/servers.json').GET(options).then((res) => {
      commit(types.GET_SERVER_GRAPH, {
        data: res
      })
    })
  }
}
