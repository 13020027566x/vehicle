import types from './types'

export default {
  [types.LIST_DICTIONARY] (state, payload) {
    state.dictionarys = payload.data.items
  },
  [types.PAGE_DICTIONARY] (state, payload) {
    state.items = payload.data.items
    state.total = payload.data.totalCount
    state.loading = false
  },
  [types.SHOW_DICTIONARY] (state, payload) {
    state.item = payload.data.item
  },
  [types.LIST_DICTIONARY_TYPE] (state, payload) {
    state.dictionaryTypes = payload.data.items
  },
  [types.PAGE_DICTIONARY_LOADING] (state, payload) {
    state.loading = true
  }
}
