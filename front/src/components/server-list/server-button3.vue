<template>
  <div
    class="server-button tab-button"
    :class="{
      'serverButton-hasNotifications': chatRoomInfo.mentions > 0,
      active: selected
    }"
    :style="chatRoomInfo.roomimage==null ? '':{'background-image' : 'url(' + `${backendUrl}/image/${chatRoomInfo.roomimage}` + ')' }"
    @click="changeRoom"
  >
    <v-tooltip
      activator="parent"
      location="end"
    >{{chatRoomInfo.title}}</v-tooltip>
    <div v-if="chatRoomInfo.mentions && !selected" class="mentions">{{ chatRoomInfo.mentions }}</div>
  </div>
</template>

<script>
export default {
  props: {
    selected: Boolean,
    isHome: Boolean,
    hasNotifications: Boolean,
    mentions: Number,
    no: Number,
    chatRoomInfo: Object
  },
  computed: {
    backendUrl () {
      return process.env.VUE_APP_BACKEND_URL
    }
  },
  methods: {
    changeRoom () {
      // console.log('changeRoom 클릭')
      this.$emit('changeRoom', this.no)
    }
  }
}
</script>

<style lang="scss" scoped>
.server-button {
  display: flex;
  flex-shrink: 0;
  align-items: center;
  justify-content: center;

  width: 48px;
  height: 48px;
  margin-bottom: 8px;
  border-radius: 50%;
  background-size: cover;
  background-color: var(--primary);
  cursor: pointer;
  position: relative;
  transition: 0.2s;

  &.active,
  &:hover {
    border-radius: 16px;
    background-color: var(--discord);
  }

  img {
    width: 44px;
    height: 44px;
    color: #fff;
    transition: 0.1s;
    &.active,
    &:hover {
      width: 48px;
      height: 48px;
    }
  }

  .mentions {
    background-color: var(--notification);

    width: auto;
    height: 24px;
    padding: 0 4px;
    bottom: -4px;
    right: -4px;

    animation:blink 1s ease-in-out infinite alternate;

    position: absolute;
    border-radius: 12px;
    border: solid 4px var(--tertiary);

    text-align: right;
    font-size: 13px;
    font-weight: bold;
    color: var(--white);
  }
}
@keyframes blink{
    0% {opacity:0.4;}
    100% {opacity:1;}
}
.serverButton-hasNotifications, .active {
  &::before {
    content: "";
    display: block;

    width: 9px;
    height: 9px;

    background-color: var(--white);
    position: absolute;
    left: -17px;
    top: calc(50% - 4.5px);
    border-radius: 50%;

    transition: 0.2s;
  }

  &:hover,
  &.active {
    &::before {
      content: "";
      width: 9px;
      height: 30px;
      top: calc(30% - 4.5px);
      border-radius: 15%;
    }
  }
}
.serverButton-hasNotifications:hover {
  &::before {
    content: "";
    width: 9px;
    height: 40px;
    top: calc(20% - 4.5px);
    border-radius: 15%;
  }
}

.serverButton-isHome {
  background-color: var(--tertiary);

  &.active,
  &:hover {
    background-color: var(--secondary) !important;
  }
}
.active {
    &::before {
      content: "";
      width: 9px;
      height: 30px;
      top: calc(30% - 4.5px);
      border-radius: 15%;
    }
}
</style>
