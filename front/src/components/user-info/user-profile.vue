<template>
    <div class="main">
      <h2 class="theme-text">{{otherInfo.username}}님의 프로필</h2>
      <hr />
      <div class="main-background">
        <div class="input-area">
          <div class="flex-column">
            <div class="input-area-box">
              <h3 class="theme-text">계정</h3>
              {{otherInfo.email}}
            </div>
            <div class="input-area-box">
              <h3 class="theme-text">소개글</h3>
              <textarea
              v-model="text"
              readonly
              ></textarea>
            </div>
            <div class="input-area-box">
              <h3 class="theme-text">가입정보</h3>
              {{otherInfo.provider}}
            </div>
          </div>
        </div>
        <div class="input-right">
          <div class="inner-right"
          :style="{ 'background-Image' : 'url(' + otherInfo.profileimage + ')' }"
          style="background-size:cover;"
          >
          </div>
        </div>
        <v-btn
        class="v-btn-custom"
         @click="changeIsInfo"
         elevation="1"
         large
        >닫기</v-btn>
      </div>
    </div>
  </template>

<script>
// import img from '../../assets/images/profile.png'

export default {
  data () {
    return {
      content: '안녕하세요 저는 ',
      limit: 200
    }
  },
  props: {
    isInfo: Boolean,
    otherInfo: Object
  },
  computed: {
    text () {
      return '안녕하세요 저는 ' + this.otherInfo.username + '이에요~'
    }
  },
  methods: {
    // replaceByDefault (e) {
    //   e.target.src = img
    // },
    check: function () {
      this.content = this.content.substr(0, this.limit)
    },
    removeImg: function () {
      const defaultImg = this.$refs.image
      this.image = defaultImg
    },
    changeIsInfo () {
      this.$emit('changeIsInfo')
    }
  }
}
</script>

  <style lang="scss" scoped>
  @import "@/assets/scss/mypage.scss";
  </style>

  <style lang="scss" scoped>
  .main{
      align-items: center;
      justify-content: center;
    }
    .main-background {
        background-color: var(--primary);
        max-height: calc(50% - 48px);
        max-width: calc(50% - 48px);
  }
  .theme-text {
    color: var(--font);
  }
  </style>

  <style scoped>
  textarea {
    resize: none;
    width: 100%;
    height: 8.25em;
    background-color: var(--text-area);
    border-radius: 10px;
    padding: 8px 8px 8px;
    border: 1px solid;
    border-color: white;;
    opacity: 0.8;
    color: var(--font);
  }
  .v-btn-custom {
    position: absolute;
    right: 30%;
    bottom: 5%;
    background-color: var(--discord);
  }
  </style>
