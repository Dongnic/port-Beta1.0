<template>
  <div class="container" :class="{ active: selected }" @click="changeChannel">
    <div class="channel-info">
      <HashTagIcon class="hashIcon" :size="20" />
      <div>Server {{ channelName }}</div>
    </div>
    <div class="channel-actions">
      <!-- 초대아이콘 클래스 -->
      <InviteIcon class="icon-actions" :size="16" />
      <!-- 채널방 설정 클래스 -->
      <SettingIcon class="icon-actions" :size="16" />
    </div>
    <!--  -->
  </div>
</template>
<script>
import HashTagIcon from 'vue-material-design-icons/Pound'
import InviteIcon from 'vue-material-design-icons/AccountPlus'
import SettingIcon from 'vue-material-design-icons/CogOutline'

export default {
  components: {
    HashTagIcon,
    InviteIcon,
    SettingIcon
  },
  props: {
    channelName: Number,
    selected: Boolean,
    activeChannel: Number
  },
  methods: {
    changeChannel () {
      this.$emit('changeChannel', this.channelName)
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;

  cursor: pointer;
  border-radius: 5px;
  margin-bottom: 5px;
  padding: 5px;

  background-color: transparent;
  transition: all ease-out 0.2s;

  div {
    display: flex;
    justify-content: center;
  }

  .channel-info {
    div {
      color: var(--senary);
      margin-left: 5px;
    }
    .hashIcon {
      color: var(--symbol);
    }
  }

  .channel-actions {
    display: none;
    .icon-actions {
      color: var(--symbol);
      transition: all ease-out 0.2s;
      margin-left: 4px;
      &:hover {
        color: var(--white);
      }
    }
  }

  &.active,
  &:hover {
    background-color: var(--quinary);
    .channel-info {
      div {
        color: var(--white);
      }
    }
    .channel-actions {
      display: flex;
    }
  }
}
</style>
