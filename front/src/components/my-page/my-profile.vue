<template>
  <div class="main">
<<<<<<< HEAD
    <h2 class="theme-text">프로필</h2>
=======
    <h2>프로필</h2>
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    <hr />
    <div class="main-background">
      <div class="input-area">
        <div class="flex-column">
          <div class="input-area-box">
<<<<<<< HEAD
            <h3 class="theme-text">프로필 사진</h3>
            <v-file-input
              label="File input"
              ref="fileRef"
              v-model="imagedata"
              accept="image/*"
              @change="selectFile"
            />
          </div>
          <div class="input-area-box">
            <h3 class="theme-text">소개글</h3>
=======
            <h3>프로필 사진</h3>
            <v-file-input
              label="File input"
              ref="image"
              v-model="imagedata"
              accept="image/*"
              @change="uploadImg()"
              @click="removeImg()"
            />
          </div>
          <div class="input-area-box">
            <h3>소개글</h3>
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
            <textarea
            placeholder="소개글을 입력해주세요"
            v-model="content"
            v-on:input="check"
            ></textarea>
            <p :class="{help: true, 'is-danger': remaining==0}">{{instruction}}</p>
          </div>
          <div class="input-area-box">
<<<<<<< HEAD
            <h3 class="theme-text">상태</h3>
            <v-select
            v-model="status"
            :items="items"
=======
            <h3>상태</h3>
            <v-select
            v-model="status"
            :items="items"
            filled
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
            dense
          ></v-select>
          </div>
        </div>
      </div>
      <div class="input-right">
<<<<<<< HEAD
        <div v-if="image === null" class="inner-right"
          :style="{ 'background-Image' : 'url(' + userInfo.profileimage + ')' }"
          style="background-size:cover;" @click="$refs.fileRef.click"
          >
          </div>
        <div v-else class="inner-right"
          :style="{ 'background-Image' : 'url(' + image + ')' }"
          style="background-size:cover;" @click="$refs.fileRef.click"
          >
          </div>
      </div>
=======
        <div class="inner-right">
          <img v-if="imagedata != ''" :src="image" style="width: 80%; height: 80%;"/>
        </div>
      </div>
      <v-btn
       @click="updateProfile"
       elevation="1"
       large
      >수정</v-btn>
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    </div>
  </div>
</template>

<script>
// import img from '../../assets/images/profile.png'
<<<<<<< HEAD
import { useStore } from 'vuex'
import { computed } from 'vue'
export default {
  setup () {
    const store = useStore()
    const userInfo = computed(() => store.getters['module1/getUserInfo'])
    return { userInfo }
  },
  data () {
    return {
      content: '',
      image: null,
      imagedata: '',
      status: '온라인',
=======

export default {
  data () {
    return {
      content: '',
      image: '',
      imagedata: '',
      status: '',
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
      items: ['온라인', '자리비움', '고스트'],
      limit: 200
    }
  },
  props: {
    isShow: Boolean
  },
  computed: {
    instruction () {
      return this.content === ''
<<<<<<< HEAD
        ? '최대 ' + this.limit + ' 글자'
        : this.remaining + '/' + this.limit + ' 글자'
=======
        ? 'limit: ' + this.limit + ' characters'
        : 'remaining ' + this.remaining + ' characters'
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    },
    remaining () {
      return this.limit - this.content.length
    }
  },
  methods: {
<<<<<<< HEAD
    selectFile (event) {
      for (const file of event.target.files) {
        const url = URL.createObjectURL(file)
        this.image = url
      }
=======
    uploadImg () {
      console.log('들어왔다')
      const image = this.$refs.image.files[0]

      const url = URL.createObjectURL(image)
      this.image = url
      console.log(url)
      console.log(this.image)
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    },
    // replaceByDefault (e) {
    //   e.target.src = img
    // },
    check: function () {
      this.content = this.content.substr(0, this.limit)
    },
<<<<<<< HEAD
    updateProfile () {
      // console.log(this.imagedata)
      // console.log(this.content)
      // console.log(this.status)
=======
    removeImg: function () {
      const defaultImg = this.$refs.image
      this.image = defaultImg
    },
    updateProfile () {
      console.log(this.imagedata)
      console.log(this.content)
      console.log(this.status)
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    }
  }
}
</script>

<<<<<<< HEAD
<style lang="scss" scoped>
@import "@/assets/scss/mypage.scss";
</style>

<style lang="scss" scoped>
.main-background {
  background-color: var(--primary);
}
.theme-text {
  color: var(--font);
}
=======
<style lang="sass" scoped>
@import "@/assets/scss/mypage.scss"
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
</style>

<style scoped>
textarea {
  resize: none;
  width: 100%;
  height: 8.25em;
<<<<<<< HEAD
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
  margin-left: 780px;
  margin-top: 590px;
  background-color: var(--discord);
=======
  background-color: #474b51;
  border-radius: 10px;
}
.v-btn {
  margin-bottom: 30px;
  margin-left: -30px;
  position: absolute;
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
}
</style>
