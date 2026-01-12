import types from './types'

export default {
  [types.PAGE_RESOURCE] (state, payload) {
    state.items = payload.data.items
    state.total = payload.data.totalCount
    state.loading = false
  },
  [types.LIST_RESOURCE] (state, payload) {
    state.items = payload.data.items
  },
  [types.SHOW_RESOURCE] (state, payload) {
    state.item = payload.data.item
  },
  [types.PAGE_RESOURCE_LOADING] (state, payload) {
    state.loading = true
  }
}
