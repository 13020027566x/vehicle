import types from './types'

export default {
  [types.GET_SERVER_GRAPH] (state, payload) {
    state.graph = payload.data
    state.loading = false
  }
}
