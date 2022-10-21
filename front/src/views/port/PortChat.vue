<template>
  <div id="grid">
<<<<<<< HEAD
    <!-- ServerList -->
    <ServerList
    :chatRoomList="chatRoomList"
    :activeChatRoom="activeChatRoom"
    :userInfo="userInfo"
    @changeRoom="changeRoom"
    />
    <!-- UserInfo -->
    <UserInfo :userInfo="userInfo" />
    <!-- Title / ToDoList -->
    <ServerName
      :author="chatRoomInfo.userid"
      :userid="userInfo.id"
      :activeChatRoom="activeChatRoom"
    />
    <!-- Channel / UserList -->
    <ChannelList v-show="activeChatRoom > 1" />
    <ChannelList2
      v-show="activeChatRoom === 1"
      :dmUserList="dmUserList"
      :userInfo="userInfo"
      @changeRoom="changeRoom"
    />
    <!-- ChatRoom / DM-->
    <ChannelInfo
      v-show="dmInfo.id === 0 && activeChatRoom"
      :chatRoomInfo="chatRoomInfo"
    />
    <ChannelInfo
      v-show="dmInfo.id !== 0"
      :chatRoomInfo="dmInfo"
    />
    <!-- ChatRoom / DM / TodoList -->
    <!-- DM -->
    <ChannelData2
      v-if="dmInfo.id !== 0"
      :activeChatRoom="activeChatRoom"
      :chatRoomInfo="dmInfo"
    />
    <ChannelData
      v-show="dmInfo.id === 0 && activeChatRoom !== 1"
      :activeChatRoom="activeChatRoom"
      :chatRoomInfo="chatRoomInfo"
    />
    <!-- TODOLIST -->
    <TodoList
      v-show="dmInfo.id === 0 && activeChatRoom === 1"
    />
    <!-- On/Off UserList -->
    <UserList
      :onlineUserList="onlineUserList"
      :offlineUserList="offlineUserList"
      @changeRoom="changeRoom"
    />
=======
    <ServerList :chatRoomList="chatRoomList" :activeChatRoom="activeChatRoom" @changeRoom="changeRoom" :userInfo="userInfo"/>
    <ServerName v-show="activeChatRoom > 0" :author="chatRoomInfo.userid" :userid="userInfo.id"/>
    <ServerName2 v-show="activeChatRoom === 0" chatRoomName="다이렉트 메세지" />
    <ChannelList v-show="activeChatRoom > 0" />
    <ChannelList2 v-show="activeChatRoom === 0" />
    <ChannelInfo v-show="activeChatRoom" :chatRoomInfo="chatRoomInfo" />
    <ChannelData v-show="activeChatRoom" :userInfo="userInfo" :activeChatRoom="activeChatRoom" :chatRoomInfo="chatRoomInfo" />
    <ChannelData v-show="activeChatRoom === 0" :userInfo="userInfo" :activeChatRoom="activeChatRoom" :chatRoomInfo="dmInfo" />
    <UserInfo :userInfo="userInfo" />
    <UserList v-show="activeChatRoom" :onlineUserList="onlineUserList" :offlineUserList="offlineUserList" :userInfo="userInfo" @changeRoom="changeRoom" />
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
  </div>
</template>

<script>
// Componenets
import ServerList from '@/components/server-list/server-list'
import ServerName from '@/components/server-name/server-name'
import ChannelList from '@/components/channel-list/channel-list'
import ChannelList2 from '@/components/channel-list/channel-list2'
import UserInfo from '@/components/user-info/user-info'
import ChannelInfo from '@/components/channel-info/channel-info'
import ChannelData from '@/components/channel-data/channel-data'
import ChannelData2 from '@/components/channel-data/channel-data2'
import TodoList from '@/components/my-page/todo-list'
import UserList from '@/components/user-list/user-list'
import { useStore } from 'vuex'
import { computed } from 'vue'
import { useRoute } from 'vue-router'

export default {
  name: 'PortChat',
  setup () {
    console.log('================= PortChat setup() ==================')
    const store = useStore()
    const route = useRoute()

    const setToken = () => store.dispatch('module1/changeJwtToken', route.query.token)
    setToken()
    const getOnlineUserList = () => store.dispatch('module1/getOnlineUserList', 1)
    const getOfflineUserList = () => store.dispatch('module1/getOfflineUserList', 1)
    const getChatRoomInfo = () => store.dispatch('module1/getChatRoomInfo', 1)
    const getUserList = () => store.dispatch('module1/getUserList')
    getUserList()
    getOnlineUserList()
    getOfflineUserList()
    getChatRoomInfo()
    const jwtToken = computed(() => store.getters['module1/getJwtToken'])
    const userList = computed(() => store.getters['module1/getUserList'])
    const userInfo = computed(() => store.getters['module1/getUserInfo'])
    const onlineUserList = computed(() => store.getters['module1/getOnlineUserList'])
    const offlineUserList = computed(() => store.getters['module1/getOfflineUserList'])
    const dmUserList = computed(() => store.getters['module1/getDmUserList'])
    const chatRoomList = computed(() => store.getters['module1/getChatRoomList'])
    const chatRoomInfo = computed(() => store.getters['module1/getChatRoomInfo'])
<<<<<<< HEAD
    const dmUserList = computed(() => store.getters['module1/getDmUserList'])
=======
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    const dmInfo = computed(() => store.getters['module1/getDmInfo'])
    const activeChatRoom = computed(() => store.getters['module1/getActiveChatRoom'])

<<<<<<< HEAD
    // console.log('userInfo : ', userInfo)
    // console.log('userList : ', userList)
    // console.log('onlineUserList : ', onlineUserList)
    // console.log('offlineUserList : ', offlineUserList)
    // console.log('chatRoomList : ', chatRoomList)
    // console.log('chatRoomInfo : ', chatRoomInfo)
    // console.log('dmUserList : ', dmUserList)
    // console.log('dmInfo : ', dmInfo)
    // console.log('activeChatRoom : ', activeChatRoom)
=======
    console.log('jwtToken : ', jwtToken)
    console.log('userInfo : ', userInfo)
    console.log('onlineUserList : ', onlineUserList)
    console.log('offlineUserList : ', offlineUserList)
    console.log('dmUserList : ', dmUserList)
    console.log('chatRoomList : ', chatRoomList)
    console.log('chatRoomInfo : ', chatRoomInfo)
    console.log('dmInfo : ', dmInfo)
    console.log('activeChatRoom : ', activeChatRoom)
    console.log('activeChannel : ', activeChannel)
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    return {
      jwtToken,
      userList,
      userInfo,
      chatRoomList,
      chatRoomInfo,
<<<<<<< HEAD
      dmUserList,
      dmInfo,
      onlineUserList,
      offlineUserList,
      activeChatRoom
=======
      dmInfo,
      activeChatRoom,
      activeChannel,
      onlineUserList,
      offlineUserList,
      dmUserList,
      getChatRoomInfo
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    }
  },
  mounted () {
    // console.log('=========mounted==========')
    // console.log('= mounted = this.$route.query.token : ', this.$route.query.token)
  },
  components: {
    ServerList,
    ServerName,
    ChannelList,
    ChannelList2,
    UserInfo,
    ChannelInfo,
    ChannelData,
    ChannelData2,
    UserList,
    TodoList
  },
  methods: {
    changeRoom (no) {
      console.log(' PortChat changeRoom ', no)
      const uid = this.userInfo.id
      if (no === 1) {
        console.log('no = 1')
        this.$store.dispatch('module1/getDmUserList', uid)
        this.$store.dispatch('module1/changeActiveChatRoom', this.dmInfo.id)
      } else {
        console.log('no != 1')
        this.$store.dispatch('module1/setDmInfo', { id: 0 })
        this.$store.dispatch('module1/changeActiveChatRoom', no)
      }
      console('changeroom 끝')
      this.$store.dispatch('module1/getChatRoomList', this.userInfo.id)
<<<<<<< HEAD
      this.$store.dispatch('module1/getChatRoomInfo', no)
    }
  },
  watch: {
    activeChatRoom () {
      console('현재 activeChatRoom : ', this.activeChatRoom)
      this.$store.dispatch('module1/getOnlineUserList', this.activeChatRoom)
      this.$store.dispatch('module1/getOfflineUserList', this.activeChatRoom)
      this.$store.dispatch('module1/getChatRoomInfo', this.activeChatRoom)
=======
      if (no === 0) this.$store.dispatch('module1/getDmUserList', this.userInfo.id)
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    }
  }
}
</script>

<style scoped>
#grid {
  display: grid;
  grid-template-columns: 71px 240px auto 240px;
  grid-template-rows: 46px auto 52px;
  grid-template-areas:
    "sl sn ci ci"
    "sl cl cd ul"
    "sl ui cd ul";

  height: 100vh;
  background-color: var(--primary);
}
</style>
