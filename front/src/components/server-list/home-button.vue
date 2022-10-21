<template>
  <div class="server-button" @click="this.$router.push('/loginJoin')">
    <img :src="require('@/assets/images/port-logo.png')" alt="logo" />
  </div>
</template>

<script>
export default {
  props: {
  },
  method: {
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

  cursor: pointer;
  position: relative;
  transition: 0.2s;
  background-color: var(--tertiary);

  &.active,
  &:hover {
    border-radius: 16px;
    background-color: var(--secondary) !important;
  }
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
</style>
