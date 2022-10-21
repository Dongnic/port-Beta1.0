<template>
  <div>
    <h1>테스트 페이지</h1>
    <router-link to="/">Home</router-link>
    | UserName | <br/> {{ userName }} <br/>
    | UserInfo | <br/> {{ userInfo }} <br/>
    | Router Token | <br/> {{$route.query.token}} <br/>
    | Store Token | <br/> {{ jwtToken }} <br/>
    | Data Token | <br/> {{ thisToken }} <br />
    <button @click="changeName">Change UserName</button><br/>
    <button @click="loadUser">Get UserName</button><br/>
    <button @click="setToken">Set Data Token</button><br/>

    <form @submit.prevent="createRoom()">
      <input type="text" name="title" v-model.trim="title" placeholder="제목 입력" minlength="1" required/>
      <button type="submit">채팅방 생성</button>
    </form>
    <button type="button" @click="getRooms">채팅리스트 불러오기</button>
    | Chatrooms | {{chatrooms}}
    <form @submit.prevent="sendMessage()">
      <input type="text" name="title" v-model.trim="title" placeholder="채팅 입력" minlength="1" required/>
      <button type="submit">채팅 입력</button>
    </form>
    <button type="button" @click="resetTitle">입력창 리셋</button>
  </div>
</template>
<script>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'
import $axios from 'axios'

export default {
  setup () {
    const store = useStore()
    // state는 moduleName으로 쪼개서 들어간다.
    const thisToken = ref('')
    const title = ref('')
    const chatrooms = ref([])
    const userName = computed(() => store.getters['module1/getUserName'])
    const userInfo = computed(() => store.getters['module1/getUserInfo'])
    const jwtToken = computed(() => store.getters['module1/getJwtToken'])
    // getters와 mutation은 전역으로 들어가서 store.getters.Counter.time2가 아니라 store.getters.time2이다
    function resetTitle () { title.value = '' }
    const changeName = () => store.dispatch('module1/changeUserName', userName.value)
    const loadUser = () => store.dispatch('module1/loadUser')
    function setToken () { thisToken.value = jwtToken }
    // token()
    return { changeName, loadUser, userName, userInfo, jwtToken, setToken, title, chatrooms, resetTitle }
  },
  mounted () {
    console.log('this.$route.query.token', this.$route.query.token)

    this.$store.dispatch('module1/changeJwtToken', this.$route.query.token)
    this.loadUser()
  },

  methods: {
    // 채팅방 생성
    createRoom: function () {
      console.log('createRoom')
      console.log('title : ', this.title)
      console.log('id : ', this.userInfo.id)
      if (this.title == null) {
        alert('필수값 누락')
        return
      }
      if (this.userInfo.id == null) {
        alert('로그인 하세요')
        return
      }
      // const vm = this
      const data = {
        title: this.title,
        userid: this.userInfo.id
      }
      console.log(data)
      const vm = this
      $axios
        .post('/api/chat/room', data)
        .then(function (response) {
          alert('CREATE SUCESS')
          vm.resetTitle()
          console.log('생성된 방번호(data) : ', response.data)
          console.log('data type : ', typeof response.data)
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    // 채팅방 리스트 불러오기
    getRooms: function () {
      console.log('getRooms')
      if (this.userInfo.id == null) {
        alert('로그인 하세요')
        return
      }
      const userid = this.userInfo.id
      console.log('userid : ', userid)
      const vm = this
      $axios
        .get('/api/chat/rooms/' + userid)
        .then(function (response) {
          alert('LOAD SUCESS')
          console.log('data : ', response.data)
          console.log('data type : ', typeof response.data)
          vm.chatrooms.value = response.data
          vm.chatrooms = response.data
        })
        .catch(function (error) {
          console.log(error)
        })
    }
  }
}
</script>
<style scoped>
button{
  background-color: chocolate;
}
</style>
