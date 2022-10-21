<template>
  <div class="container">
    <!-- ServerButton은 채널 버튼
         isHome = 홈 로고
         hasNotifications = 알림유무
         mentions = 읽지 않은 메세지 갯수
         selected = 현재 선택한 채널
    -->
    <HomeButton  />
    <div class="separator"></div>
    <div id="server">
      <server-button-3
        v-for="(chatRoom, index) in chatRoomList"
        :key="index"
        :no="chatRoom.id"
        :chatRoomInfo="chatRoom"
        :selected="chatRoom.id === activeChatRoom"
        @changeRoom="changeRoom"
      >
      </server-button-3>
      <!-- 채널 생성버튼 -->
      <v-btn
        class="add-button"
        fab
        dark
        color="cyan accent-2"
        @click="dialog = !dialog"
      >
        <v-icon>mdi-plus</v-icon>
      </v-btn>
      <!-- 채널 나가기 -->
      <!-- <v-btn
        class="add-button"
        fab
        dark
        color="red accent-2"
        @click="outRoom"
      >
        <v-icon>mdi-minus</v-icon>
      </v-btn> -->
      <!-- 방 생성 모달창 -->
      <v-dialog
        v-model="dialog"
      >
        <!-- 전체 감싸는 카드  -->
      <v-card
      class="v-card"
          color="blue-black-darken-1"
          dark
          :loading="isUpdating"
        >
          <template v-slot:progress>
            <v-progress-linear
              absolute
              color="black-lighten-3"
              height="4"
              indeterminate
            ></v-progress-linear>
          </template>
          <br><div class="createTitle"><h3>방 생성</h3></div><br>
          <input type="file" @change="selectFile" accept="image/*" ref="fileRef" hidden/>
          <div class="images" v-if="imageLink.length > 0">
            <div v-for="(imglink, index) in imageLink" :key="index" class="image imgdiv">
              <img :src="imglink" class="titleImageBox" @click="$refs.fileRef.click" />
            </div>
          </div>
          <button v-else @click="$refs.fileRef.click" type="button">선택</button>
          <!-- <img style="{'height':'200px', 'background-size': 'cover'}" :src="imgLink"> -->
            <v-row>
              <v-col class="text-end" cols="12">
                <v-menu location="bottom start" origin="overlap" transition="slide-y-transition">
                </v-menu>
              </v-col>
              <v-row
                class="pa-4"
                align="center"
                justify="center"
              >
                <v-col class="text-center">
                  <h4 class="text-h5">
                    {{ title }}
                  </h4>
                  <span class="text-grey-lighten-1">{{ discribe }}</span>
                </v-col>
              </v-row>
            </v-row>
          <!-- </v-img> -->
          <v-form>
            <v-container>
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    v-model="title"
                    :disabled="isUpdating"
                    filled
                    label="방 이름"
                    placeholder="방 이름을 입력해주세요"
                    class="txt-field-color"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" md="6">
                  <v-text-field
                    v-model="discribe"
                    :disabled="isUpdating"
                    filled
                    label="방 설명"
                    placeholder="방 설명을 입력해주세요"
                    class="txt-field-color"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-autocomplete
                    v-model="inviteList"
                    :disabled="isUpdating"
                    :items="userList"
                    filled
                    chips
                    closable-chips
                    clearable
                    label="초대"
                    item-title="name"
                    item-value="id"
                    multiple
                    class="txt-field-color"
                  >
                    <template v-slot:chip="{ props, item }">
                      <v-chip
                        v-bind="props"
                        :prepend-avatar="item.raw.profileimage"
                        :text="item.raw.username"
                      ></v-chip>
                    </template>
                    <template v-slot:item="{ props, item }">
                      <v-list-item v-if="typeof item.raw !== 'object'" v-bind="props"></v-list-item>
                      <v-list-item
                        v-else
                        v-bind="props"
                        :prepend-avatar="item.raw.profileimage"
                        :title="item.raw.username"
                        :subtitle="item.raw.provider"
                      ></v-list-item>
                    </template>
                  </v-autocomplete>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              :loading="isUpdating"
              prepend-icon="mdi-update"
              @click="createChatRoom"
              class="crateButton"
            >
              생성
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>
<script>
import HomeButton from './home-button.vue'
import ServerButton3 from './server-button3.vue'
import $axios from 'axios'
import { useStore } from 'vuex'
import { computed } from 'vue'
export default {
  setup () {
    const store = useStore()
    const getUserList = () => store.dispatch('module1/getUserList')
    getUserList()
    const userList = computed(() => store.getters['module1/getUserList'])
    return { userList, getUserList }
  },
  props: {
    chatRoomList: Array,
    activeChatRoom: Number,
    userInfo: Object
  },
  data () {
    return {
      autoUpdate: true,
      isUpdating: false,
      dialog: false,
      title: '',
      discribe: '',
      inviteList: [],
      filedatas: [],
      imageLink: ['https://i.esdrop.com/d/f/14rMlVHaTh/diKJSu5rRf.png']
    }
  },
  components: {
    HomeButton,
    ServerButton3
  },
  watch: {
    activeChatRoom (newTab) {
      // console.log('chatRoom: ' + newTab)
    },
    dialog () {
      this.getUserList()
    }
  },
  computed: {
    backendUrl () {
      return process.env.VUE_APP_BACKEND_URL
    },
    websocketUrl () {
      return process.env.VUE_APP_WEBSOCKET_URL
    }
  },
  methods: {
    changeRoom (no) {
      // console.log(' list changeRoom ', no)
      this.$emit('changeRoom', no)
    },
    selectFile (event) {
      this.imageLink = []
      this.filedatas = []
      for (const file of event.target.files) {
        const url = URL.createObjectURL(file)
        this.imageLink.push(url)
        this.filedatas.push('files', file)
      }
      // console.log('imageLink : ', this.imageLink)
    },
    // 채팅방 생성
    createChatRoom: function () {
      // console.log('createChatRoom')
      // console.log('title : ', this.title)
      // console.log('id : ', this.userInfo.id)
      if (this.title == null) {
        alert('필수값 누락')
        return
      }
      if (this.userInfo.id == null) {
        alert('로그인 하세요')
        return
      }
      const data = {
        title: this.title,
        discribe: this.discribe,
        userid: this.userInfo.id,
        inviteList: this.inviteList
      }
      // console.log(data)
      $axios
        .post('/api/chat/room', data)
        .then((response) => {
          alert('방을 만들었습니다.')
          this.dialog = false
          // console.log('생성된 방번호(data) : ', response.data)
          // console.log('data type : ', typeof response.data)
          const formData = new FormData()
          for (const file of this.filedatas) {
            formData.append('files', file)
          }
          const newRoomid = response.data
          formData.append('chatroomid', response.data)
          $axios.post(`${this.backendUrl}/roomImage`, formData, {
            headers: { 'Content-Type': 'multipart/form-data' }
          }).then((response) => {
            console.log(' 업로드 한 파일 데이터 : ', response.data)
            // console.log(this.files)
            this.$store.dispatch('module1/getChatRoomList', this.userInfo.id)
            this.imagesLink = []
            // this.fetchFiles()
            this.changeRoom(newRoomid)
          }).catch(error => {
            alert(error.message)
          })
        })
        .catch((error) => {
          console.log(error)
        })
    },
    outRoom () {
      if (confirm('정말 방에서 나가시겠습니까?')) {
        $axios
          .delete('/api/chat/room/' + this.activeChatRoom + '/' + this.userInfo.id)
          .then(response => {
          // console.log(' 삭제결과 : ', response.data)
            alert(response.data)
            this.$store.dispatch('module1/getChatRoomList', this.userInfo.id)
          })
          .catch(error => {
            console.log(' DELETE FAIL : ', error)
          })
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  grid-area: sl;
  background-color: var(--tertiary);
  padding: 11px 0;
  max-height: 100vh;
  overflow-y: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
}
.add-button {
  display: flex;
  flex-shrink: 0;
  align-items: center;
  justify-content: center;
  width: 48px ;
  height: 48px ;
  margin-bottom: 8px;
  border-radius: 50%;
  background-color: var(--primary);
  cursor: pointer;
  position: relative;
  transition: 0.2s;
  &.active,
  &:hover {
    border-radius: 16px;
    background-color: var(--discord);
  }
}
.v-btn--size-default {
  min-width: 48px !important;
}
.separator {
  width: 32px;
  margin-bottom: 8px;
  border-bottom: solid 2px var(--font);
}
.titleImageBox{
  width: 400px;
  height: 400px;
  border-radius: 50%;
  background-size: cover;
}
.imgdiv, .createTitle{
  text-align: center;
}
.v-dialog{
  max-height: calc(70% - 48px);
  width: calc(70% - 48px);
  max-width: calc(1000px);
}
.v-card{
  border-radius: 20px;
}
.crateButton{
  background: var(--discord);
  margin-right: 15px;
}
.v-dialog .v-overlay__content>.v-card {
  background-color: var(--primary);
}
.createTitle, .txt-field-color {
  color: var(--font);
}
</style>
