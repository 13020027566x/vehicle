import types from './types'

export default {
  [types.PAGE_LOG] (state, payload) {
    state.items = payload.data.items
    state.total = payload.data.totalCount
    state.loading = false
  },
  [types.SHOW_LOG] (state, payload) {
    state.item = payload.data.item
  },
  [types.PAGE_LOG_LOADING] (state, payload) {
    state.loading = true
  }
}
