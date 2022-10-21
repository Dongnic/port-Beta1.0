<template>
<div class="login-Msg">
    <div class="login-loginMsg">
        <div class="login-textcontent" :class="{'visibility': !isLogin}">
            <p class="login-title">Don't have an account?</p>
            <p>Sign up to save all your graph.</p>
            <button id="login-switch1" class="login-button" @click="toJoin">Sign Up</button>
        </div>
    </div>
    <div class="login-signupMsg" :class="{'visibility': isLogin}">
        <div class="login-textconten login-textcontent-signup">
            <p class="login-title">Have an account?</p>
            <p>Log in to see all your collection.</p>
            <button id="login-switch2" class="login-button" @click="toLogin">LOG IN</button>
        </div>
    </div>
</div>
</template>
<script>
export default {
  name: 'LoginMsg',
  props: {
    isLogin: Boolean
  },
  methods: {
    toLogin () {
      console.log('toLogin')
      this.$emit('switchLogin', true)
    },
    toJoin () {
      console.log('toJoin')
      this.$emit('switchLogin', false)
    }
  }
}
</script>
<style scoped>
.login-Msg{
  background-color: #404040;
    width: 100%;
    height: 100%;
    position: absolute;
    transform: translateY(-50%);
    top: 50%;
    display: inline-flex !important;
}
#login-switch1:hover{
    cursor: pointer;
    background-color: red;
}
.login-button:hover{
    cursor: pointer;
    background-color: red;
}
.login-loginMsg, .login-signupMsg{
width: 50%;
height: 100%;
font-size: 15px;
box-sizing: border-box;
}

.login-loginMsg .login-title,
.login-signupMsg .login-title{
font-weight: 300;
font-size: 23px;
}

.login-loginMsg p,
.login-signupMsg p {
font-weight: 100;
}

.login-textcontent{
color:white;
margin-top:30%;
margin-left: 10%;
}
.login-textcontent-signup{
color:white;
margin-top:30%;
margin-left: 20%;
}

.login-loginMsg button,
.login-signupMsg button {
background-color: #404040;
border: 2px solid white;
border-radius: 10px;
color:white;
font-size: 12px;
box-sizing: content-box;
font-weight: 300;
padding:10px;
margin-top: 20px;
}
.login-loginMsg, .login-signupMsg{
/*opacity: 1;*/
transition: opacity .8s ease-in-out;
}
</style>
