<template>
  <span class="mention">
    <slot />
  </span>
</template>

<style lang="scss" scoped>
.mention {
  color: var(--link);
  cursor: pointer;
  margin-right: 6px;
  animation:blink 0.8s ease-in-out infinite alternate;
  &:hover {
    text-decoration: underline;
  }
}
@keyframes blink{
    0% {opacity:0.2;}
    100% {opacity:1;}
}
</style>
