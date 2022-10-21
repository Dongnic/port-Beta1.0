<template>
  <div class="container dark-mode-background">
    <div class="profile">
      <div class="avatar" :style="{ 'background-Image' : 'url(' + userInfo.profileimage + ')' }">
        <v-badge
        bordered
        dot
        bottom
        color="#00ff55"
        offset-x="-31"
        offset-y="10"
        overlap
        class="userBadge"
      ></v-badge>
      </div>
      <div class="user-data">
        <!-- 콧수염표현식으로 userName의 값을 화면에 출력 -->
        <strong>{{ userInfo.username }}</strong>
        <span>#{{ userInfo.id }}</span>
      </div>
    </div>
    <div class="actions">
      <!-- 각 컴포넌트(이미지-아이콘)의 사이즈 지정 -->
      <!-- <Mic :size="20" />
      <HeadPhones :size="20" /> -->
      <div class="text-center setting-dropdown">
          <v-menu :location="location">
            <template v-slot:activator="{ props }">
              <Settings
              :size="20"
              v-bind="props" />
            </template>
            <v-list class="v-list-background">
              <v-list-item
                v-for="(item, index) in items"
                :key="index"
                class="settings"
              >
                <v-list-item-title style="cursor: pointer;" @click="change(index)">{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
    </div>
    <v-dialog
        v-model="isSettings"
      >
      <SettingsMenu
        :isSettings="isSettings"
        @changeSettings="changeSettings"
      />
      </v-dialog>
  </div>
</template>

<script>
// import Mic from 'vue-material-design-icons/Microphone'
// import HeadPhones from 'vue-material-design-icons/Headphones'
import Settings from 'vue-material-design-icons/CogOutline'
import SettingsMenu from '@/components/my-page/settings-menu'

export default {
  // 상위컴포넌트로부터 userName의 값(String타입)을 받는다.
  props: {
    userInfo: Object
  },
  components: {
    Settings,
    SettingsMenu
  },
  data: () => ({
    items: [
      { title: '설정' },
      { title: '퇴장' }
    ],
    location: 'top',
    isSettings: false
  }),
  methods: {
    changeSettings () {
      this.isSettings = false
    },
    change (value) {
<<<<<<< HEAD
      // console.log(' click : ', value)
=======
      console.log(' click : ', value)
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
      // 설정
      if (value === 0) {
        this.isSettings = !this.isSettings
      // 퇴장
      } else if (value === 1) {
        // this.$emit('changeRoom', 5000)
        this.$store.dispatch('module1/changeActiveChatRoom', 1)
        this.$store.dispatch('module1/getChatRoomList', this.userInfo.id)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  grid-area: ui;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background-color: var(--quaternary);
  box-shadow: rgba(0, 0, 0, 0.9) 0 1px 0 0;
}
.profile {
  display: flex;
  align-items: center;
}
.avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background-color: var(--font);
  background-size: cover;
}
.user-data {
  display: flex;
  flex-direction: column;
  margin-left: 8px;
  font-size: 13px;
  strong {
    display: block;
    color: var(--font);
    letter-spacing: 1px;
  }
  span {
    color: var(--font);
  }
}
.actions {
  color: var(--font);
  span {
    cursor: pointer;
    transition: 0.2s;
    &:hover {
      color: var(--white);
    }
  }
}
.settings:hover{
    background-color: var(--quinary);
}
.v-list-background {
  background-color: var(--quinary) !important;
}
</style>
<style scoped>
.userBadge {
    animation:blink 2s ease-in-out infinite alternate;
}
@keyframes blink{
    0% {opacity:0.2;}
    100% {opacity:1;}
}
.v-dialog{
  max-height: calc(90% - 48px);
  max-width: calc(90% - 48px);
}
</style>
