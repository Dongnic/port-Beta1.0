<template>
  <div class="list-container">
    <div class="category">
      <div>Channel List</div>
      <div class="category-icon-div">
        <AddCategoryIcon class="category-icon" :size="21" @click="addChannel" />
        <REMOVECategoryIcon class="category-icon" :size="21" @click="removeChannel" />
      </div>
    </div>
    <ChannelButton
      v-for="index in channelCount"
      :key="index"
      :channelName="index"
      :activeChannel="activeChannel"
      :selected="index === activeChannel"
      @changeChannel="changeChannel"
    />
  </div>
</template>

<script>
import AddCategoryIcon from 'vue-material-design-icons/Plus'
import REMOVECategoryIcon from 'vue-material-design-icons/Minus'
import ChannelButton from './channel-button'

export default {
  components: {
    AddCategoryIcon,
    ChannelButton,
    REMOVECategoryIcon
  },
  data () {
    return {
      channelCount: 3,
      activeChannel: 1
    }
  },
  methods: {
    addChannel () {
      this.channelCount++
    },
    removeChannel () {
      if (this.channelCount > 1) this.channelCount--
      if (this.channelCount < this.activeChannel) this.activeChannel = this.channelCount
    },
    changeChannel (no) {
      this.activeChannel = no
    }
  }
}
</script>

<style lang="scss" scoped>
.list-container {
  grid-area: cl;
  display: flex;
  flex-direction: column;

  padding: 24px 10px 0 16px;
  background-color: var(--secondary);
  overflow-y: scroll;

  &::-webkit-scrollbar {
    width: 8px;
  }

  &::-webkit-scrollbar-thumb {
    background-color: var(--tertiary);
    border-radius: 4px;
  }

  &::-webkit-scrollbar-track {
    background-color: var(--secondary);
  }
}

.category {
  display: flex;
  align-items: center;
  justify-content: space-between;

  margin-bottom: 6px;
  color: var(--font);
  font-size: 12px;
  font-weight: 500;
  text-transform: uppercase;

  .category-icon {
    color: var(--symbol);
    cursor: pointer;
    &:hover {
      color: var(--white);
      transition: all 0.2s;
    }
  }
}
</style>
