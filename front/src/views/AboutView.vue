<template>
  <div class="about">
    <router-link to="/">Home</router-link>
    <h1>로그인회원 정보 : {{ userInfo }}</h1>
    <button @click="getUser()">get username!!</button>
    <h1>회원명 : {{ userName}}</h1>
    <button @click="getUserList()">getUserList</button>
    <h1>회원리스트 : {{ userList }}</h1>
  </div>
</template>
<script>
import $axios from 'axios'
export default {
  data () {
    return {
      userInfo: [],
      userList: [],
      userName: ''
    }
  },
  created () {
    console.log('created ===========')
    const thisVal = this
    $axios
      .get('/user/userInfo')
      .then(function (response) {
        console.log('/userInfo', response)
        console.log('헤더 : ', response.headers)
        console.log('data : ', response.data)
        console.log('data type : ', typeof response.data)
        thisVal.userInfo = response.data
      })
      .catch(function (error) {
        thisVal.userInfo = '로그인 하세요'
        console.log(error)
      })
  },
  methods: {
    getUserList: function () {
      const thisVal = this
      $axios
        .get('/user')
        .then(function (response) {
          console.log("get('/user')", response)
          console.log('data : ', response.data)
          console.log('data type : ', typeof response.data)
          thisVal.userList = response.data
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    getUser: function () {
      this.userName = this.userInfo.username
    }
  }
}
</script>
