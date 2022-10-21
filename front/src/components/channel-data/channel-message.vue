<template>

  <!-- 현재 유저 -->
  <div v-if="isMe" class="channel-message myMessage" :class="{ hasMention: hasMention }" >
    <div class="message">
      <div class="body mybody">
          <slot />
      </div>
      <span class="date">{{ date }}</span>
    </div>
  </div>

  <!-- 타 유저 -->
  <div v-else class="channel-message" :class="{ hasMention: hasMention }" >
    <div class="avatar" :class="{ botAvatar: isBot }" :style="{ 'background-Image' : 'url(' + profileimage + ')' }" />
    <div class="message">
      <div class="user">
        <strong>{{ authorName }}</strong>
        <span class="bot" v-if="isBot">bot</span>
      </div>
      <div class="body">
          <slot />
        <span class="date">{{ date }}</span>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: 'channel-message',
  props: {
    authorName: String,
    profileimage: String,
    date: String,
    hasMention: Boolean,
    isBot: Boolean,
    isMe: String
  }
}
</script>

<style lang="scss" scoped>
.channel-message {
  box-sizing: content-box;
  display: flex;
  padding: 8px 16px;
  margin-right: 4px;
  background-color: transparent;
  margin-top: 5px;
  word-break: break-word;

  &.hasMention {
    background-color: var(--mention-message);
    border-left: solid 2px var(--mention-detail);
  }
  &:hover {
    background-color: var(--tertiary);
    .avatar {
      transition: all 0.2s;
      background-color: var(--primary);
    }
  }
}

.avatar {
  top: 5px;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: var(--secondary);
  flex-shrink: 0;
  background-size: cover;
  &.botAvatar {
    background-color: var(--mention-detail);
  }
}

.message {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-left: 17px;

  strong {
    color: var(--font);
    font-size: 16px;
    letter-spacing: 1px;
  }
  .date {
    margin-left: 6px;
    margin-right: 6px;
    color: var(--font);
    font-size: 13px;
  }
  .body {
    color: var(--font);
    text-align: left;
    font-size: 16px;
    margin-top: 5px;
  }
  .bot {
    margin-left: 9px;
    background-color: var(--discord);
    padding: 4px 5px;
    text-transform: uppercase;
    font-size: 11px;
    font-weight: bold;
    color: var(--font);
    border-radius: 4px;
  }

}
.myMessage{
  margin: 0 0 0 auto !important;
  &:hover {
    background-color: var(--tertiary);
    .avatar {
      transition: all 0.2s;
      background-color: var(--primary);
    }
  }
}
.onRight{
  margin: 0 0 0 auto !important;
}
.hide{
  display: none !important;
}
.mybody{
  text-align: right !important;
}
</style>
