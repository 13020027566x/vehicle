import types from './types'

export default {
  [types.PAGE_USER] (state, payload) {
    state.items = payload.data.items
    state.total = payload.data.totalCount
    state.loading = false
  },
  [types.PAGE_USER_ROLE] (state, payload) {
    state.roleItems = payload.data.items
    state.roleTotal = payload.data.totalCount
    state.roleLoading = false
  },
  [types.LIST_USER_ROLE] (state, payload) {
    state.userRoles = payload.data.items
  },
  [types.LIST_USER_ORGANIZATION] (state, payload) {
    state.userOrganizationItems = payload.data.items
  },
  [types.SHOW_USER] (state, payload) {
    state.item = payload.data.item
  },
  [types.PAGE_USER_LOADING] (state, payload) {
    state.loading = true
  },
  [types.ROLE_LOADING] (state, payload) {
    state.roleLoading = true
  }
}
