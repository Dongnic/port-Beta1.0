// store/modules/module1.js
import $axios from 'axios'
export const module1 = {
  namespaced: true,
  state: {
    jwtToken: 'no',
    userInfo: [],
    dmInfo: [],
    userList: [],
    onlineUserList: [],
    offlineUserList: [],
    otherUserList: [],
    dmUserList: [],
    chatRoomList: [],
    chatRoomInfo: [{ title: 'Home' }],
    dmUserList: [],
    dmInfo: { id: 0 },
    chatMessageList: [],
    activeChatRoom: 1,
    activeChannel: 0
  },
  mutations: {
    SET_JWT_TOKEN (state, value) {
      state.jwtToken = value
    },
    SET_USER_INFO (state, value) {
      state.userInfo = value
    },
    SET_DM_INFO (state, value) {
      state.dmInfo = value
    },
    SET_USER_LIST (state, value) {
      state.userList = value
    },
    SET_ONLINEUSER_LIST (state, value) {
      state.onlineUserList = value
    },
    SET_OFFLINEUSER_LIST (state, value) {
      state.offlineUserList = value
    },
    SET_OTHERUSER_LIST (state, value) {
      state.otherUserList = value
    },
    SET_DMUSER_LIST (state, value) {
      state.dmUserList = value
    },
    SET_CHATROOM_LIST (state, value) {
      state.chatRoomList = value
    },
    SET_CHATROOM_INFO (state, value) {
      state.chatRoomInfo = value
    },
    SET_DMUSER_LIST (state, value) {
      state.dmUserList = value
    },
    SET_DM_INFO (state, value) {
      state.dmInfo = value
    },
    SET_CHATMESSAGE_LIST (state, value) {
      state.chatMessageList = value
    },
    ADD_CHATMESSAGE_LIST (state, value) {
      state.chatMessageList.push(value)
    },
    SET_ACTIVE_CHATROOM (state, value) {
      state.activeChatRoom = value
    },
    SET_ACTIVE_CHANNEL (state, value) {
      state.activeChannel = value
    }
  },
  actions: {
    changeJwtToken ({ commit, dispatch }, value) {
      // console.log('=======changeJwtToken==========')
      // console.log(value)
      commit('SET_JWT_TOKEN', value)
      dispatch('getUserInfo')
    },
    changeActiveChatRoom ({ commit, dispatch }, value) {
      commit('SET_ACTIVE_CHATROOM', value)
      dispatch('getChatRoomInfo', value)
      // dispatch('getOnlineUserList', value)
      // dispatch('getOfflineUserList', value)
    },
<<<<<<< HEAD
    setDmInfo ({ commit }, value) {
=======
    setDMInfo ({ commit }, value) {
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
      commit('SET_DM_INFO', value)
    },
    addChatMessage ({ commit }, value) {
      commit('ADD_CHATMESSAGE_LIST', value)
    },
    // context = { state, getters, commit, dispatch, ... }
    // 선택 예시 { commit, getters } commit.함수
    // 전체 예시 (context) - context.commit.함수
    getUserInfo ({ commit, state, dispatch }) {
      // console.log('=======getUserInfo==========')
      // console.log('state.jwtToken', state.jwtToken)
      const config = {
        headers: {
          Authorization: 'Bearer ' + state.jwtToken
        }
      }
      $axios
        .get('/user/userInfo', config)
        .then(function (response) {
          // console.log(' ### $axios /user/userInfo', response)
          // console.log('UserInfo data : ', response.data)
          commit('SET_USER_INFO', response.data)
          dispatch('getChatRoomList', response.data.id)
          dispatch('getDmUserList', response.data.id)
          // 채널 정보 가져오기
        })
        .catch(function (error) {
          console.log('ERROR getUserInfo : ', error)
          commit('SET_USER_NAME', '로그인안함')
        })
    },
    getUserList ({ commit }) {
      // console.log('=======getUserList==========')
      $axios
        .get('/user')
        .then(function (response) {
          // console.log('### $axios /user', response)
          // console.log('userList data : ', response.data)
          commit('SET_USER_LIST', response.data)
        })
        .catch(function (error) {
          console.log('ERROR getUserList : ', error)
        })
    },
    getOnlineUserList ({ commit }, value) {
      // console.log('=======getOnlineUserList==========')
      $axios
        .get('/user/online/' + value)
        .then(function (response) {
          // console.log('### $axios /user/online/' + value, response)
          // console.log('onlineUserList data : ', response.data)
          commit('SET_ONLINEUSER_LIST', response.data)
        })
        .catch(function (error) {
          console.log('ERROR getOnlineUserList : ', error)
        })
    },
    getOfflineUserList ({ commit }, value) {
      // console.log('=======getOfflineUserList==========')
      $axios
        .get('/user/offline/' + value)
        .then(function (response) {
          // console.log('### $axios /user/offline/' + value, response)
          // console.log('offlineUserList data : ', response.data)
          commit('SET_OFFLINEUSER_LIST', response.data)
        })
        .catch(function (error) {
          console.log('ERROR getOfflineUserList : ', error)
        })
    },
    getOtherUserList ({ commit }, value) {
      // console.log('=======getOtherUserList==========')
      $axios
        .get('/user/other/' + value)
        .then(function (response) {
          // console.log('### $axios /user/other/' + value, response)
          // console.log('otherUserList data : ', response.data)
          commit('SET_OTHERUSER_LIST', response.data)
        })
        .catch(function (error) {
          console.log('ERROR getOtherUserList : ', error)
        })
    },
    getDmUserList ({ commit }, value) {
<<<<<<< HEAD
      // console.log('=======getDmUserList==========')
      $axios
        .get('/user/DM/' + value)
        .then(function (response) {
          // console.log('### $axios /user/DM/' + value, response)
          // console.log('dmUserList data : ', response.data)
=======
      console.log('=======getDmUserList==========')
      $axios
        .get('/user/DM/' + value)
        .then(function (response) {
          console.log('### $axios /user/DM/' + value, response)
          console.log('dmUserList data : ', response.data)
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
          commit('SET_DMUSER_LIST', response.data)
        })
        .catch(function (error) {
          console.log('ERROR getDmUserList : ', error)
        })
    },
    getChatRoomList ({ commit }, value) {
      // console.log('=======getChatRoomList==========')
      // console.log(' getChatRoomList value : ', value)
      $axios
        .get('/api/chat/rooms/' + value)
        .then(function (response) {
          // console.log('### $axios /api/chat/rooms/' + value, response)
          // console.log('ChatRoomList data : ', response.data)
          commit('SET_CHATROOM_LIST', response.data)
        })
        .catch(function (error) {
          console.log('ERROR getChatRoomList : ', error)
        })
    },
    getChatRoomInfo ({ commit, dispatch }, value) {
      // console.log('=======getChatRoomInfo==========')
      $axios
        .get('/api/chat/room/' + value)
        .then(function (response) {
          // console.log('### $axios /api/chat/room/' + value, response)
          // console.log('ChatRoomInfo data : ', response.data)
          commit('SET_CHATROOM_INFO', response.data)
          dispatch('getChatMessageList', response.data.id)
        })
        .catch(function (error) {
          console.log('ERROR getChatRoomInfo : ', error)
        })
    },
    getChatMessageList ({ commit }, value) {
      commit('SET_CHATMESSAGE_LIST', '')
      $axios
        .get('/api/chat/room/message/' + value)
        .then(function (response) {
          // console.log('### $axios /api/chat/room/message' + value, response)
          // console.log('ChatMessageList data : ', response.data)
          if (response.data.length > 0) { commit('SET_CHATMESSAGE_LIST', response.data) }
        })
        .catch(function (error) {
          console.log('ERROR getChatMessageList : ', error)
        })
    }
  },
  getters: {
    getJwtToken (state) {
      // console.log(state.jwtToken)
      return state.jwtToken
    },
    getUserInfo (state) {
      // console.log(state.userInfo)
      return state.userInfo
    },
    getUserList (state) {
      // console.log(state.userList)
      return state.userList
    },
    getOnlineUserList (state) {
      // console.log(state.onlineUserList)
      return state.onlineUserList
    },
    getOfflineUserList (state) {
      // console.log(state.offlineUserList)
      return state.offlineUserList
    },
    getOtherUserList (state) {
      // console.log(state.otherUserList)
      return state.otherUserList
    },
    getDmUserList (state) {
      console.log(state.dmUserList)
      return state.dmUserList
    },
    getChatRoomList (state) {
      // console.log(state.chatRoomList)
      return state.chatRoomList
    },
    getChatRoomInfo (state) {
      // console.log(state.chatRoomInfo)
      return state.chatRoomInfo
    },
<<<<<<< HEAD
    getDmUserList (state) {
      // console.log(state.dmUserList)
      return state.dmUserList
    },
    getDmInfo (state) {
      // console.log(state.dmInfo)
=======
    getDmInfo (state) {
      console.log(state.dmInfo)
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
      return state.dmInfo
    },
    getChatMessageList (state) {
      // console.log(state.chatMessageList)
      return state.chatMessageList
    },
    getActiveChatRoom (state) {
      // console.log(state.activeChatRoom)
      return state.activeChatRoom
    },
    getActiveChannel (state) {
      // console.log(state.activeChannel)
      return state.activeChannel
    }
  }
}
