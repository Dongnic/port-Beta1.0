<template>
  <div class="setting-menu">
<<<<<<< HEAD
    <div class="side-bar ">
      <div class="container">
        <div class="side-wrapper">
          <div class="side-menu">
            <div @click="goHome">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
                stroke-linecap="round"
                stroke-linejoin="round"
                viewBox="0 0 24 24"
              >
                <path d="M3 9l9-7 9 7v11a2 2 0 01-2 2H5a2 2 0 01-2-2z" />
                <path d="M9 22V12h6v10" />
              </svg>
              Home
            </div>
            <br/>
            <div class="side-menuItem" :class="{ active: isShow }">
              <accEditIcon />
              <div @click="toProfile" class="side-menu-text">
                프로필
              </div>
            </div>
            <div class="side-menuItem" :class="{ active: !isShow }">
              <cogIcon />
              <div @click="toSetting" class="side-menu-text">
                설정
=======
    <div class="side-bar">
      <div class="container" x-data="{ rightSide: false, leftSide: false }">
        <div class="left-side" :class="{ active: leftSide }">
          <div class="left-side-button" @click="leftSide = !leftSide">
            <svg
              viewBox="0 0 24 24"
              stroke="currentColor"
              stroke-width="2"
              fill="none"
              stroke-linecap="round"
              stroke-linejoin="round"
            >
              <line x1="3" y1="12" x2="21" y2="12"></line>
              <line x1="3" y1="6" x2="21" y2="6"></line>
              <line x1="3" y1="18" x2="21" y2="18"></line>
            </svg>
            <svg
              stroke="currentColor"
              stroke-width="2"
              fill="none"
              stroke-linecap="round"
              stroke-linejoin="round"
              viewBox="0 0 24 24"
            >
              <path d="M19 12H5M12 19l-7-7 7-7" />
            </svg>
          </div>
          <div class="side-wrapper">
            <div class="side-menu">
              <div
                  @click="goHome"
                >
              <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  viewBox="0 0 24 24"
                >
                  <path d="M3 9l9-7 9 7v11a2 2 0 01-2 2H5a2 2 0 01-2-2z" />
                  <path d="M9 22V12h6v10" />
                </svg>
                Home
              </div>
              <div class="side-menuItem" :class="{ active:isShow }">
                <div
                @click="toProfile"
                >
                <accEditIcon />
                  프로필
                </div>
              </div>
              <div class="side-menuItem" :class="{ active:!isShow }">
                <div
                @click="toSetting"
                >
                <cogIcon />
                  설정
                </div>
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
              </div>
            </div>
          </div>
        </div>
<<<<<<< HEAD
        <div class="main-container profile-set-background">
=======
        <div class="main-container">
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
          <MyProfile v-show="isShow" />
          <MySetting v-show="!isShow" />
        </div>
      </div>
    </div>
    <UserInfo />
  </div>
</template>

<script>
import MyProfile from './my-profile.vue'
import MySetting from './my-setting.vue'
import UserInfo from '../user-info/user-info.vue'
import accEditIcon from 'vue-material-design-icons/AccountEditOutline'
import cogIcon from 'vue-material-design-icons/CogOutline'

export default {
  name: 'SettingsMenu',
  components: {
    MyProfile,
    MySetting,
    UserInfo,
    accEditIcon,
    cogIcon
  },
  props: {
    isSettings: Boolean
  },
  data () {
    return {
      isShow: true
    }
  },
  methods: {
    toSetting () {
      this.isShow = false
    },
    toProfile () {
      this.isShow = true
    },
    goHome () {
      this.$emit('changeSettings')
    }
  }
}
</script>

<<<<<<< HEAD
<style lang="scss" scoped>
@import "@/assets/scss/mypage.scss";
</style>

<style lang="scss" scoped>
.container {
  background-color: var(--tertiary);
}
.profile-set-background {
  background-color: var(--secondary);
}
</style>

<style scoped>
.side-menuItem .active {
  color: #fff;
}
.side-menu-text {
margin-left: 10px;
margin-top: 5px;
=======
<style lang="sass" scoped>
@import "@/assets/scss/mypage.scss"
</style>

<style scoped>
.side-menuItem .active{
    color: #fff;
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
}
</style>
