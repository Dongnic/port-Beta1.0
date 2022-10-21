<template>
  <v-menu offset-y>
    <template v-slot:activator="{ props }">
      <div class="user-role"
        v-bind="props"
        @focus="focused = true"
        @blur="focused = false"
        :class="{'focused' : focused}"
        :tabindex="no+1"
        >
              <!-- v-bind:class
        클래스명을 값에 따라 그때 그때 다르게 적용할 때 사용.
        v-bind:class="{ condition(조건식) }으로 사용"
        {적용될 클래스명 : true or false의 조건식}
        결론 = isBot이 트루이면 클래스명이 botAvatar이므로
        그에 맞는 css적용.-->
<<<<<<< HEAD
        <div class="avatar" :class="{ botAvatar: isBot }" :style="{ 'background-Image' : 'url(' + profileimage + ')' }">
          <v-badge
            bordered
            dot
            bottom
            :color="isOnline === 1 ? '#00ff55' : isOnline === 2 ? '#ff3535': '#c0c0c0'"
            offset-x="-31"
            offset-y="10"
            overlap
            :class="{'userBadge': isOnline}"
          ></v-badge>
        </div>
        <!-- String타입의 닉네임 출력 -->
        <strong>{{ nickName }}</strong>
        <!-- v-if로 isBot이 true이면 bot출력. <-------이란 뜻인가요??????? -->
        <div class="bot" v-if="isBot">bot</div>
      </div>
    </template>
    <v-list-item
      v-for="(item, index) in items"
      :key="index"
      class="settings"
      :prepend-icon="item.icon"
      :value="title"
    >
      <v-list-item-title style="cursor: pointer;" @click="change(index)">{{ item.title }}</v-list-item-title>
    </v-list-item>
  </v-menu>
  <v-dialog
        v-model="isInfo"
      >
      <UserProfile
        :isInfo="isInfo"
        :otherInfo="otherInfo"
        @changeIsInfo="changeIsInfo"
      />
      </v-dialog>
=======
    <div class="avatar" :class="{ botAvatar: isBot }" :style="{ 'background-Image' : 'url(' + profileimage + ')' }">
      <v-badge
        bordered
        dot
        bottom
        :color="isOnline ? '#00ff55' : '#ff3535'"
        offset-x="-31"
        offset-y="10"
        overlap
        :class="{'userBadge': isOnline}"
      ></v-badge>
    </div>
    <!-- String타입의 닉네임 출력 -->
    <strong>{{ nickName }}</strong>
    <!-- v-if로 isBot이 true이면 bot출력. <-------이란 뜻인가요??????? -->
    <div class="bot" v-if="isBot">bot</div>
  </div>
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
</template>

<script>
import { useStore } from 'vuex'
import { computed } from 'vue'
import $axios from 'axios'
import UserProfile from '@/components/user-info/user-profile'
// 상위 compo로 부터 String타입의 닉네임과 isBot값을 받음.
export default {
  setup () {
    const store = useStore()
    const userInfo = computed(() => store.getters['module1/getUserInfo'])
    return { userInfo }
  },
  components: {
    UserProfile
  },
  props: {
    isBot: Boolean,
    nickName: String,
    profileimage: String,
    isOnline: Boolean,
    no: Number,
    userid: Number
  },
  data: () => ({
    items: [
      { title: '1:1 채팅', icon: 'mdi-message-reply' },
      { title: '유저정보', icon: 'mdi-account' }
    ],
    focused: false,
    otherInfo: [],
    isInfo: false
  }),
  methods: {
    change (index) {
      // 1:1 채팅
      if (index === 0) {
        this.toDM()
      // 마이 페이지
      } else if (index === 1) {
        this.toInfo()
      }
    },
    toDM () {
      if (this.userid === this.userInfo.id) {
        alert('자신에게는 DM을 할 수 없습니다.')
      }
      // console.log('toDM')
      // console.log('otherid : ', this.userid)
      // console.log('userid : ', this.userInfo.id)
      const data = {
        userid: this.userInfo.id,
        otherid: this.userid
      }
      $axios
        .post('/api/chat/DM', data)
        .then((response) => {
          alert('DM 성공')
          this.$store.dispatch('module1/setDmInfo', response.data)
          this.$emit('changeRoom', 1)
        })
        .catch((error) => {
          console.log(error)
        })
    },
    toInfo () {
      if (this.userid === this.userInfo.id) {
        alert('설정 - 마이페이지를 이용해주세요')
      } else {
        $axios
          .get('/user/' + this.userid)
          .then((response) => {
            this.otherInfo = response.data
            this.isInfo = !this.isInfo
          })
          .catch((error) => {
            console.log(error)
          })
      }
    },
    changeIsInfo () {
      this.isInfo = false
    }
  }
}
</script>

<style lang="scss" scoped>
.user-role {
  display: flex;
  margin-top: 5px;
  padding: 5px;
  display: flex;
  align-items: center;
  cursor: pointer;
  border-radius: 5px;
  background: transparent;
  transition: all ease-out 0.2s;

  &:hover {
    background-color: rgba(255, 255, 255, 0.1);
  }

  strong {
    margin-left: 13px;
    font-weight: 500;
    color: var(--white);
    opacity: 0.7;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
  }

  .bot {
    margin-left: 9px;
    background-color: var(--discord);
    color: var(--white);
    padding: 4px 5px;
    border-radius: 4px;
    text-transform: uppercase;
    font-size: 11px;
    font-weight: bold;
  }

  .avatar {
    width: 32px;
    height: 32px;
    flex-shrink: 0;
    background-color: var(--primary);
    border-radius: 50px;
    background-size: cover;
  }

  .botAvatar {
    background-color: var(--mention-detail);
  }
}
</style>
<style scoped>
.userBadge {
    animation:blink 2s ease-in-out infinite alternate;
}
@keyframes blink{
    0% {opacity:0.4;}
    100% {opacity:1;}
}
<<<<<<< HEAD
.settings{
  background: rgb(var(--v-theme-surface));
  width: 150px;
  margin-left: 30px;
}
.settings:hover{
    background-color: var(--quinary);
}
.focused{
    background: rgb(var(--v-theme-surface));
}
=======
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
</style>
