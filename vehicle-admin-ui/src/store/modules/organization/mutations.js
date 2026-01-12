import types from './types'

export default {
  [types.PAGE_ORGANIZATION] (state, payload) {
    state.items = payload.data.items
    state.total = payload.data.totalCount
    state.loading = false
  },
  [types.LIST_ORGANIZATION] (state, payload) {
    state.items = payload.data.items
  },
  [types.SHOW_ORGANIZATION] (state, payload) {
    state.item = payload.data.item
  },
  [types.PAGE_ORGANIZATION_LOADING] (state, payload) {
    state.loading = true
  }
}
