import Vue from 'vue'
import Vuex from 'vuex'
import actions from './actions'
import getters from './getter'
Vue.use(Vuex)

const state = {
  user_name: localStorage.getItem('username'),
  user_role: localStorage.getItem('role'),
}

const mutations = {
  INIT_STORE (state, data) {
    state.user_role = data.user_role
    state.user_name = data.user_name
  },
  HANDLE_USER_NAME (stage, date) {
    state.user_name = date
  },
  HANDLE_USER_ROLE (state, date) {
    state.user_identify = date
  },

}

export default new Vuex.Store({
  state,
  mutations
})
