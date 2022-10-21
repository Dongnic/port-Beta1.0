<template>
  <div class="main">
<<<<<<< HEAD
    <h2 class="theme-text">설정</h2>
=======
    <h2>설정</h2>
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    <div class="main-background">
      <div class="input-area">
        <div class="flex-column">
          <div class="input-area-box">
<<<<<<< HEAD
            <h3 class="theme-text">디스플레이 테마</h3>
            <div class="mode-toggle" @click="modeToggle" :class="darkDark">
              <div class="toggle">
                <div id="dark-mode" type="checkbox"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- <v-btn
      class="v-btn-custom"
      @click="updateSetting"
      elevation="1"
      large
      >수정</v-btn> -->
=======
            <h3>디스플레이</h3>
            <v-radio-group v-model="displayMode" displaymode>
              <v-radio label="라이트 모드" value="lightmode" v-model="lightmode"></v-radio>
              <v-radio label="다크 모드" value="darkmode" v-model="darkmode"></v-radio>
            </v-radio-group>
          </div>
          <div class="input-area-box">
            <h3>알림</h3>
            <v-radio-group v-model="alertonoff" alertonoff>
              <v-radio label="켜기" value="on"></v-radio>
              <v-radio label="끄기" value="off"></v-radio>
            </v-radio-group>
          </div>
        </div>
      </div>
      <v-btn
      @click="updateSetting"
      elevation="1"
      large class="v-btn-custom"
      >수정</v-btn>
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    </div>
  </div>
</template>

<script>
// import img from '../../assets/images/profile.png'

export default {
  data () {
    return {
      displayMode: null,
      alertonoff: null,
<<<<<<< HEAD
      darkMode: true
=======
      lightmode: '',
      darkmode: ''
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    }
  },
  props: {
    isShow: Boolean
  },
  methods: {
    updateSetting () {
<<<<<<< HEAD
      // console.log(this.displayMode)
      // console.log(this.alertonoff)
    },
    // dark () {
    //   document.querySelector('#app').classList.add('dark-mode')
    //   this.darkMode = true
    //   this.$emit('dark')
    // },

    // light () {
    //   document.querySelector('#app').classList.remove('dark-mode')
    //   this.darkMode = false
    //   this.$emit('light')
    // },

    modeToggle () {
      // 저장된 값이 없다면 시스템 설정을 기준으로 함
      const currentTheme = localStorage.getItem('theme') ||
        (
          window.matchMedia('(prefers-color-scheme: dark)').matches
            ? 'dark'
            : 'light'
        )
      const newTheme = currentTheme === 'dark' ? 'light' : 'dark'

      // 최상위 엘리먼트에 설정, 로컬 스토리지에 설정을 저장
      document.documentElement.setAttribute('data-theme', newTheme)
      localStorage.setItem('theme', newTheme)
    }
  },
  computed: {
    darkDark () {
      return this.darkMode && 'darkmode-toggled'
=======
      console.log(this.displayMode)
      console.log(this.alertonoff)
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    }
  }
}
</script>

<<<<<<< HEAD
<style lang="scss" scoped>
@import "@/assets/scss/mypage.scss";
@import "@/assets/styles/global.scss";
</style>

<style lang="scss" scoped>
.main-background {
  background-color: var(--primary);
}
.theme-text {
  color: var(--font);
}
.v-btn-custom {
  position: absolute;
  margin-left: 780px;
  margin-top: 590px;
  background-color: var(--discord);
}
.v-radio-text {
  color: var(--font);
}
</style>
=======
<style scoped>
.v-btn-custom {
  bottom: 50px;
    right: 60px;
    position: absolute;
}
</style>
<style lang="sass" scoped>
@import "@/assets/scss/mypage.scss"
</style>
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
