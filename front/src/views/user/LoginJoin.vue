<template>
  <div class="login-container">
      <div class="login-backbox">
        <LoginMsg :isLogin="isLogin" @switchLogin="switchLogin"></LoginMsg>
      </div>
      <div class="login-frontbox" :class="{'moving' : !isLogin}">
        <Login v-show="isLogin"></Login>
        <Join v-show="!isLogin"></Join>
      </div>
  </div>

</template>

<script>
import Login from './LoginPage.vue'
import Join from './JoinPage.vue'
import LoginMsg from './loginMsg/LoginMsg.vue'
export default {
  name: 'LoginJoin',
  components: {
    LoginMsg,
    Login,
    Join
  },
  data () {
    return {
      isLogin: true
    }
  },
  methods: {
    switchLogin (Key) {
      this.isLogin = Key
    }
  }
}
</script>
<style lang="sass">
@import "@/assets/scss/loginjoin.scss"
</style>
<style scoped>
  .login-container{
    /*border:1px solid white;*/
    width: 600px;
    height: 350px;
    position: absolute;
    top:50%;
    left:50%;
    transform: translate(-50%, -50%);
    display: inline-flex;
  }
  .login-backbox{
    background-color: #404040;
    width: 100%;
    height: 80%;
    position: absolute;
    transform: translate(0,-50%);
    top:50%;
    display: inline;
  }

  .login-frontbox{
    background-color: white;
    border-radius: 20px;
    height: 100%;
    width: 50%;
    z-index: 10;
    position: absolute;
    right:0;
    margin-right: 3%;
    margin-left: 3%;
    transition: right .8s ease-in-out;
  }

  .moving{
    right:45%;
  }
</style>
