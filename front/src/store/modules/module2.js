// store/modules/module2.js
export const module2 = {
  namespaced: true,
  state: () => ({
    count: 0
  }),
  mutations: {
    increment (state) {
      state.count++
    }
  },
  getters: {
    doubleCount (state) {
      return state.count * 2
    }
  },
  actions: {
    incrementIfOddOnRootSum (state, commit, rootState) {
      if ((state.count + rootState.count) % 2 === 1) {
        commit('increment')
      }
    }
  }
}
