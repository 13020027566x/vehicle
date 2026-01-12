import types from './types'

export default {
  [types.PAGE_ROLE] (state, payload) {
    state.items = payload.data.items
    state.total = payload.data.totalCount
    state.loading = false
  },
  [types.LIST_ROLE_PERMISSION] (state, payload) {
    state.rolePermissionItems = payload.data.items
  },
  [types.SHOW_ROLE] (state, payload) {
    state.item = payload.data.item
  },
  [types.PAGE_ROLE_LOADING] (state, payload) {
    state.loading = true
  }
}
